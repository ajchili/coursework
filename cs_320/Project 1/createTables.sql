USE productInfo;

CREATE TABLE storeInfo (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
	number int NOT NULL,
	name char(60) NOT NULL,
	numberOfEmployees int NOT NULL
);

CREATE TABLE productInfo (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name char(60) NOT NULL,
	cost decimal(5, 2) NOT NULL,
	store_id int NOT NULL
);

CREATE TABLE customerInfo (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	firstName char(30) NOT NULL,
	lastName char(30) NOT NULL,
	email char(100) NOT NULL,
	product_id int NOT NULL
);
