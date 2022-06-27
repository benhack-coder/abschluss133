CREATE TABLE person
(
    id        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    birthday  DATE         NOT NULL,
    email     VARCHAR(100) NOT NULL,
    gender    VARCHAR(1)   NOT NULL,
    firstname VARCHAR(100) NOT NULL,
    lastname  VARCHAR(100) NOT NULL
)