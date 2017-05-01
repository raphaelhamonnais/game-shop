INSERT INTO USERS(user_login, user_email, user_passwd, user_last_name, user_first_name)
    VALUES ("customer1", "customer1@gmail.com", "Y6nw6nu5gFB5a2SehUgYRQ==", "last name", "first name");

INSERT INTO USERS(user_login, user_email, user_passwd, user_last_name, user_first_name)
  VALUES ("customer2", "customer2@gmail.com", "Y6nw6nu5gFB5a2SehUgYRQ==", "last name", "first name");

INSERT INTO USERS(user_login, user_email, user_passwd, user_last_name, user_first_name)
  VALUES ("admin1", "admin1@gmail.com", "Y6nw6nu5gFB5a2SehUgYRQ==", "last name", "first name");

INSERT INTO ROLES(role_name) VALUES ("customer");
INSERT INTO ROLES(role_name) VALUES ("admin");

INSERT INTO USER_ROLES(user_login, role_name) VALUES ("customer1", "customer");
INSERT INTO USER_ROLES(user_login, role_name) VALUES ("customer2", "customer");
INSERT INTO USER_ROLES(user_login, role_name) VALUES ("admin1", "admin");