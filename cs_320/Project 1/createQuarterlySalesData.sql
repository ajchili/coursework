use webdev;

CREATE TABLE QuarterlySalesData(
Location varchar(10) NOT NULL,
Quarter int NOT NULL,
UnitSold int NOT NULL
) ENGINE = INNODB;