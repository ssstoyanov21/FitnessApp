package com.gym.gym.service;


import com.gym.gym.dto.ClientDTO;
import com.gym.gym.dto.Requests.CreateClientRequest;
import com.gym.gym.dto.Requests.LoginRequest;
import com.gym.gym.dto.Requests.UpdateClientRequest;
import com.gym.gym.dto.Responses.*;
import com.gym.gym.entity.Client;
import com.gym.gym.enums.Role;
import com.gym.gym.mapper.ClientMapper;
import com.gym.gym.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public GetAllClientsResponse getAllClients() {
        List<Client> clientsEntity = clientRepository.findByRoleIsNot(Role.ROLE_ADMIN);// durpame vsichki klienti koitot ne sa admini
        List<ClientDTO> clientDTOS = clientsEntity.stream().map(clientMapper::toClientDTO)
                .collect(Collectors.toList());
        GetAllClientsResponse response = new GetAllClientsResponse();
        response.setClients(clientDTOS);
        return response;
    }

    public GetClientResponse getClientById(Long id) {//id, validirame,dali id e po-golqmo ot 0
        GetClientResponse response = new GetClientResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Client> client = clientRepository.findById(id);//razreshava stoinosta da e 0
        if (client.isEmpty()) {
            response.setErrorMessage("Missing user with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        response.setClient(Optional.of(clientMapper.toClientDTO(client.get())));//2 casta
        return response;
    }


    public CreateClientResponse createClient(CreateClientRequest request) {
        CreateClientResponse response = new CreateClientResponse();
        if( isExistingEmail(request.getEmail())){
            response.setErrorMessage("This email is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
        String hashedPassword = encoder.encode(request.getPassword());
        request.setPassword(hashedPassword);
        long result = clientRepository.save(clientMapper.toEntity(request)).getId();
        response.setId(Optional.of(result));
        return response;
    }

    public BaseResponse updateClient(Long id, UpdateClientRequest request) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            response.setErrorMessage("Missing user with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        if(!(request.getEmail().equals(client.get().getEmail())) && isExistingEmail(request.getEmail())){
            response.setErrorMessage("This email is existing.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }

        Client clientToEdit = client.get();
        clientToEdit.setEmail(request.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(14);
        String hashedPassword = encoder.encode(request.getPassword());
        clientToEdit.setPassword(hashedPassword);

        clientToEdit.setFirstName(request.getFirstName());
        clientToEdit.setLastName(request.getLastName());
        clientToEdit.setAge(request.getAge());
        clientToEdit.setKg(request.getKg());
        clientRepository.save(clientToEdit);

        return response;
    }
    private boolean isExistingEmail (String email){
        return clientRepository.existsByEmail(email);
    }

    public BaseResponse deleteClient(Long id) {
        BaseResponse response = new BaseResponse();
        if (id <= 0) {
            response.setErrorMessage("Invalid ID.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            response.setErrorMessage("Missing user with current ID..");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        clientRepository.delete(client.get());
        return response;
    }
    public LoginResponse login(LoginRequest request){
        LoginResponse response = new LoginResponse();
        Optional<Client> client = clientRepository.findByEmail(request.getEmail());
        if (client.isEmpty() || !isExistingEmail(client.get().getEmail())) {
            response.setErrorMessage("Missing client with this Email.");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());//vrusha 400 error
            return response;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        boolean matches = passwordEncoder.matches(request.getPassword(), client.get().getPassword());//sravnqva parolata i sravnqva da li e kato tazi ot bazata
        if(!matches){
            response.setErrorMessage("Invalid password");
            response.setHasError(true);
            response.setStatusCode(HttpStatus.BAD_REQUEST.value());
            return response;
        }
        response.setRole(Optional.of(client.get().getRole()));
        return response;

    }

}
