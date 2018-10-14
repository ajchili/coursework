USE productInfo;

SELECT id, name, numberOfEmployees
CASE WHEN numberOfEmployees < 10 THEN 'HIRE'
WHEN numberOfEmployees > 10 AND numberOfEmployees < 100 THEN 'Manage'
WHEN numberOfEmployees > 100 THEN 'Fire'
END as ACTION
FROM storeinfo;