CREATE TABLE users_cool (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    address_street VARCHAR(255),
    address_suite VARCHAR(255),
    address_city VARCHAR(255),
    address_zipcode VARCHAR(255),
    geo_lat VARCHAR(255),
    geo_lng VARCHAR(255),
    phone VARCHAR(255),
    website VARCHAR(255),
    company_name VARCHAR(255),
    company_catch_phrase VARCHAR(255),
    company_bs VARCHAR(255)
);