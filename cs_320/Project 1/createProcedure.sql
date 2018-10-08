USE productInfo;

DROP PROCEDURE IF EXISTS `CreateWalmartProduct`;

DELIMITER %%

CREATE PROCEDURE `CreateWalmartProduct` (
name char(60),
cost decimal(5, 2),
store_id int(11)
)
BEGIN
INSERT INTO productinfo (name, cost, store_id)
VALUES (name, cost, store_id);
END%%

DELIMITER ;
