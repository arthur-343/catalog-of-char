CREATE SEQUENCE character_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE character (
    id bigint PRIMARY KEY DEFAULT nextval('character_seq'),
    name VARCHAR(255) NOT NULL,
    power VARCHAR(255),
    occupation VARCHAR(255),
    origin VARCHAR(255),
    series VARCHAR(255),
    age INTEGER
);
