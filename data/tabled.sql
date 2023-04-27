CREATE DATABASE manga_store;

USE manga_store;

create table customer(
    c_name varchar(255) not null,
    c_id varchar(255) PRIMARY KEY,
    c_address VARCHAR(255) not NULL,
    c_mail VARCHAR(255) NOT NULL,
    c_phone VARCHAR(255) NOT NULL
);

create table manga(
    ISBN VARCHAR(255) PRIMARY KEY,
    mangaka varchar(255) not null,
    title varchar(255) NOT null,
    genre varchar(255) NOT null,
    stock INT NOT null,
    price INT NOT NULL,
);

CREATE TABLE shopping_basket(
    basket_id VARCHAR(255) PRIMARY KEY,
    c_id varchar(255) Not NULL,
    Foreign Key (c_id) REFERENCES customer(c_id)
);

CREATE TABLE orders(
    num_books INT NOT NULL,
    ISBN VARCHAR(255) NOT NULL,
    basket_id VARCHAR(255) NOT NULL,
    Foreign Key (ISBN) REFERENCES manga(ISBN),
    Foreign Key (basket_id) REFERENCES shopping_basket(basket_id),
    Foreign Key (c_id) REFERENCES manga(c_id)
);

