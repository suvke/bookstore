SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS category; 
DROP TABLE IF EXISTS book; 
DROP TABLE IF EXISTS app_user;
SET FOREIGN_KEY_CHECKS=1;


CREATE TABLE category
(categoryid BIGINT NOT NULL AUTO_INCREMENT 
, name VARCHAR(30) NOT NULL
, PRIMARY KEY (categoryid)
);


INSERT INTO category (name) 
VALUES ("Tietokirjallisuus")
, ("Kaunokirjallisuus");


CREATE TABLE book (
id BIGINT NOT NULL AUTO_INCREMENT
, title VARCHAR(30) NOT NULL
, author VARCHAR(30) NOT NULL
, publicationyear INT 
, isbn VARCHAR(15) NOT NULL
, price INT
, categoryid BIGINT
, PRIMARY KEY (id)
, FOREIGN KEY (categoryid) REFERENCES category(categoryid));

INSERT INTO book (title, author, publicationyear, isbn, bookcategoryprice, categoryid) 
VALUES ("Ohjelmointia", "Olli Ohjelmoija", 2020, "1234-A4B6", 25, 1), 
("Java-ohjelmointi", "Olga Ohjelmoija", 2019, "9876-ABCD", 15, 1);


CREATE TABLE app_user
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
, username VARCHAR(100) NOT NULL
, password VARCHAR(100) NOT NULL
, email  VARCHAR(100) NOT NULL
, role VARCHAR(100) NOT NULL
);

INSERT INTO app_user (username, password, email, role) 
VALUES ("user", "$2a$10$JKJTrFnQj0rt6q.iES.BDu/55YZ1mem9oSEkd/a6S4C2u4j1ybJN6", "user.user@email.com", "USER"), 
("admin", "$2a$10$UhIUkEuExmCh/onM725jOuk/Dy2sip43kFmlBsZ315BB4wLJVcf1u", "admin.admin@email.com", "ADMIN");


SELECT * FROM category;
SELECT * FROM book;
SELECT * FROM app_user;