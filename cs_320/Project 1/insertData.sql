INSERT INTO storeInfo (number, name, numberOfEmployees)
VALUES (1111, 'The Corner Store', 4),
(2222, 'Walmart', 1000),
(3333, 'Office Depot', 50);

INSERT INTO productInfo (name, cost, store_id)
VALUES ('Keyboard', 100.00, 2),
('Mouse', 50.00, 2),
('Soap', 2.50, 1);

INSERT INTO customerInfo (firstName, lastName, email, product_id)
VALUES ('John', 'Doe', 'john@doe.com', 2),
('John', 'Doe', 'john@doe.com', 3),
('John', 'Doe', 'john@doe.com', 3),
('Jane', 'Doe', 'jane@doe.com', 1);