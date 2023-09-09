CREATE DATABASE company_management;
CREATE TABLE ADMIN (
    id BIGINT PRIMARY KEY auto_increment,
    username varchar(250),
    password varchar(250)
);
CREATE TABLE EMPLOYEE (
    employee_id BIGINT PRIMARY KEY auto_increment,
    firstname varchar(250),
    surname varchar(250),
    position varchar(250)
);
CREATE TABLE PRODUCT (
    catalog_number BIGINT PRIMARY KEY auto_increment,
    category varchar(250),
    price BIGINT,
    quantity BIGINT
);
ALTER TABLE employee
CHANGE name firstname varchar(250);

ALTER TABLE employee
ADD surname varchar(250);

ALTER TABLE product
ADD quantity varchar(250);
