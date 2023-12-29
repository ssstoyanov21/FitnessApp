import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FitnessService {

  private backendUrl = "http://localhost:8000/api"
  constructor(private httpClient: HttpClient) {
  }

  public getAllFitnesses():Observable<any>{
    return this.httpClient.get(`${this.backendUrl}/fitness/all`);
  }
}
