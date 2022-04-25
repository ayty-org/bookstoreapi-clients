CREATE TABLE clients(
    id SERIAL8 PRIMARY KEY,
    uuid uuid UNIQUE NOT NULL DEFAULT uuid_generate_v4(),
    name VARCHAR(60) NOT NULL,
    age INT NOT NULL,
    telephone VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    gender VARCHAR(15) NOT NULL
);