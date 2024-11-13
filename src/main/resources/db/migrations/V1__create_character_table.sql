-- V1__create_character_table.sql

CREATE TABLE character_entity_main (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    power VARCHAR(255),
    occupation VARCHAR(255),
    origin VARCHAR(255),
    series VARCHAR(255),
    age INTEGER
);
