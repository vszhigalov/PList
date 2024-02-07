create database application;


use application;


-- Table 'dish'
CREATE TABLE dish
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name varchar(45) NOT NULL,
    chatId varchar(45) NOT NULL
);

-- Table 'products'
CREATE TABLE products
(
    ID     INT AUTO_INCREMENT PRIMARY KEY,
    name   varchar(45) NOT NULL,
    weight DOUBLE,
    count DOUBLE,
    dishId INT,
    FOREIGN KEY (dishId) REFERENCES dish (ID)
);