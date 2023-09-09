CREATE DATABASE company_management;
CREATE TABLE ADMIN (
    id BIGINT PRIMARY KEY auto_increment,
    username varchar(25),
    password varchar(25)
);
CREATE TABLE EMPLOYEES (
    employee_id BIGINT PRIMARY KEY auto_increment,
    name varchar(250),
    positionproducts varchar(250)
);
CREATE TABLE PRODUCTS (
    catalog_number BIGINT PRIMARY KEY auto_increment,
    name varchar(250),
    price BIGINT
);
