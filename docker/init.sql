CREATE SCHEMA IF NOT EXISTS contacts_schema;
CREATE TABLE contacts_schema.contact
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR (65) NOT NULL,
    last_name VARCHAR (65),
    email VARCHAR (65) NOT NULL,
    phone VARCHAR (65)
);
