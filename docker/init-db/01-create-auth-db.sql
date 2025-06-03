CREATE DATABASE auth_db;
\c auth_db;

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(100) NOT NULL
);

INSERT INTO roles (id, descripcion) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, descripcion) VALUES (2, 'ROLE_ADMIN');