USE productinfo;

-- Cartesian
SELECT * FROM customerinfo JOIN productinfo;

-- Left
SELECT * FROM customerinfo LEFT JOIN productinfo ON customerinfo.id = productinfo.id;

-- Right
SELECT * FROM customerinfo RIGHT JOIN productinfo ON customerinfo.id = productinfo.id;

-- Union
SELECT * FROM customerinfo LEFT JOIN productinfo ON customerinfo.id = productinfo.id
UNION
SELECT * FROM customerinfo RIGHT JOIN productinfo ON customerinfo.id = productinfo.id;
