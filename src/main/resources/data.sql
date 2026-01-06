
DELETE FROM users_roles;
DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO users (username, password, age, first_name, last_name)
VALUES (
           'admin',
           '$2a$10$np6JlT3AR4n0e8HlLnthUOLqaKqhPrWnNvPNPN3oRthD3/555ywFm',
           40,
           'admin',
           'admin'
       );

INSERT INTO users_roles (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE username = 'admin'),
           (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
       );


INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users (username, password, age, first_name, last_name)
VALUES (
           'user',
           '$2a$10$mFzZ8AI/6uowM.pxBnhqMecy4vkeEpQhmvFyRTNiZOuHtQW6F6jsy',
           30,
           'user',
           'user'
       );

INSERT INTO users_roles (user_id, role_id)
VALUES (
           (SELECT id FROM users WHERE username = 'user'),
           (SELECT id FROM roles WHERE name = 'ROLE_USER')
       );
