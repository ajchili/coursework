SELECT Location,
SUM(CASE WHEN Quarter = '1' THEN UnitSold ELSE 0 END) AS 'Q1',
SUM(CASE WHEN Quarter = '2' THEN UnitSold ELSE 0 END) AS 'Q2',
SUM(CASE WHEN Quarter = '3' THEN UnitSold ELSE 0 END) AS 'Q3',
SUM(CASE WHEN Quarter = '4' THEN UnitSold ELSE 0 END) AS 'Q4'
From QuarterlySalesData Group BY Location;