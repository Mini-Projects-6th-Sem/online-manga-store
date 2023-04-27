CREATE DATABASE manga_store;

USE manga_store;

create table customer(
    c_name varchar(255) not null,
    c_id varchar(255) PRIMARY KEY,
    c_address VARCHAR(255) not NULL,
    c_mail VARCHAR(255) NOT NULL,
    c_phone VARCHAR(255) NOT NULL
);

create table mangaka(
    auth_name varchar(255) primary key,
    email varchar(255) not null
);
CREATE TABLE publisher(
    p_name VARCHAR(255) PRIMARY KEY,
    p_address VARCHAR(255) NOT NULL,
    p_mail VARCHAR(255) NOT NULL,
    p_phone VARCHAR(255) NOT NULL
);

CREATE TABLE warehouse(
    S_id VARCHAR(255) PRIMARY KEY,
    S_num INT NOT NULL,
    S_lot VARCHAR(255) NOT NULL
);

create table manga(
    ISBN VARCHAR(255) PRIMARY KEY,
    author varchar(255) not null,
    Foreign Key (author) REFERENCES mangaka(auth_name),
    title varchar(255) NOT null,
    price FLOAT NOT NULL,
    issue VARCHAR(255) NOT NULL,
    volume INT NOT NULL,
    published varchar(255) Not null,
    Foreign Key (published) REFERENCES publisher (p_name),
    S_id VARCHAR(255) NOT NULL,
    Foreign Key (S_id) REFERENCES warehouse (S_id)
);

CREATE TABLE shopping_basket(
    basket_id VARCHAR(255) PRIMARY KEY,
    c_id varchar(255) Not NULL,
    Foreign Key (c_id) REFERENCES customer(c_id)
);

CREATE TABLE container(
    num_books INT NOT NULL,
    ISBN VARCHAR(255) NOT NULL,
    basket_id VARCHAR(255) NOT NULL,
    Foreign Key (ISBN) REFERENCES manga(ISBN),
    Foreign Key (basket_id) REFERENCES shopping_basket(basket_id)
);

