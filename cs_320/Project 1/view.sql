USE productinfo;

DROP VIEW IF EXISTS `ProductsInStores`;

CREATE VIEW `ProductsInStores` AS
SELECT productinfo.name as 'Product Name', productinfo.cost, storeinfo.id as 'Store ID', storeinfo.name as 'Store Name'
FROM productinfo JOIN storeinfo ON productinfo.store_id = storeinfo.id;

SELECT * FROM ProductsInStores;