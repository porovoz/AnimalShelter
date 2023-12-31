-- liquibase formatted sql

-- changeset sokol:1
CREATE TABLE dog_owner (
    id BIGINT NOT NULL generated by default as identity PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    age INTEGER,
    address VARCHAR(255),
    phone_number VARCHAR(255) NOT NULL,
    chat_id BIGINT,
    status INTEGER
);