-- password is asd213
insert into gym.client (create_at, email, first_name, last_name, password, role, age)
values (localtime(), 'admin', 'admin', 'admin', '$2a$14$HIEPv2SEN5FjG668dUMOveOlqJBMIxes5uUmEfL7nNoBHMxpU.4oy', 1, 0)