SELECT Location, '1' AS Quarter, 'Q1' as UnitSold FROM QuarterlySalesDataPivot
UNION
SELECT Location, '2' AS Quarter, 'Q2' as UnitSold FROM QuarterlySalesDataPivot
UNION
SELECT Location, '3' AS Quarter, 'Q3' as UnitsSold FROM QuarterlySalesDataPivot
UNION
SELECT Location, '4' AS Quarter, 'Q4' as UnitsSold FROM QuarterlySalesDataPivot;