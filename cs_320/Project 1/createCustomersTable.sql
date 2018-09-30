USE productInfo;

CREATE TABLE MyCustomers (
  CustomerID int AUTO_INCREMENT NOT NULL,
  FirstName varchar(50) NOT NULL,
  LastName varchar(50) NOT NULL,
  AddressLine1 varchar(50) NOT NULL,
  City varchar(50) NOT NULL,
  State char(2) NOT NULL,
  EmailAddress varchar(100),
  DateAdded DATETIME NOT NULL,
  PRIMARY KEY(CustomerID)
 ) ENGINE=INNODB;