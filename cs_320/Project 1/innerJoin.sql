SELECT customerinfo.email, productinfo.name, productinfo.cost
FROM customerinfo
INNER JOIN productinfo ON customerinfo.product_id=productinfo.id;