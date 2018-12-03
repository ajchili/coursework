-- 2
SELECT count(*) FROM Customers;

-- 3
SELECT count(*) FROM Orders WHERE OrderDate BETWEEN '1996-07-01' AND '1996-08-01';

-- 4
SELECT * from Customers WHERE CustomerID=(SELECT CustomerID FROM Orders WHERE OrderID='10249');

-- 5
SELECT * FROM Products WHERE ProductID IN (SELECT ProductID FROM [Order Details] WHERE OrderID='10249');

--6
SELECT ProductID, ProductName, Suppliers.CompanyName AS SupplierName, Categories.CategoryName, UnitPrice, UnitsInStock
	FROM Products
	JOIN Suppliers ON Products.SupplierID = Suppliers.SupplierID
	JOIN Categories ON Products.CategoryID = Categories.CategoryID;

-- 7
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2019
-- Description:	Creates new customer.
-- =============================================
CREATE PROCEDURE CreateCustomer
	@strCompanyName varchar(40),
	@strContactName varchar(30),
	@strContactTitle varchar(30),
	@strAddress varchar(60),
	@strCity varchar(15),
	@strRegion varchar(15),
	@strPostalCode varchar(10),
	@strCounrty varchar(15),
	@strPhone varchar(24),
	@strFax varchar(24)
AS
BEGIN
    INSERT INTO Customers(
	"CompanyName",
	"ContactName",
	"ContactTitle",
	"Address",
	"City",
	"Region",
	"PostalCode",
	"Country",
	"Phone",
	"Fax"
	)
	VALUES (
	@strCompanyName,
	@strContactName,
	@strContactTitle,
	@strAddress,
	@strCity,
	@strRegion,
	@strPostalCode,
	@strCounrty,
	@strPhone,
	@strFax
	);
END
GO

-- 9
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2019
-- Description:	Allows customer to be edited.
-- =============================================
CREATE PROCEDURE EditCustomer
	@strCustomerID nchar(5),
	@strCompanyName varchar(40),
	@strContactName varchar(30),
	@strContactTitle varchar(30),
	@strAddress varchar(60),
	@strCity varchar(15),
	@strRegion varchar(15),
	@strPostalCode varchar(10),
	@strCounrty varchar(15),
	@strPhone varchar(24),
	@strFax varchar(24)
AS
BEGIN
	UPDATE Customers
	SET "CompanyName" = @strCompanyName,
	"ContactName" = @strContactName,
	"ContactTitle" = @strContactTitle,
	"Address" = @strAddress,
	"City" = @strCity,
	"Region" = @strRegion,
	"PostalCode" = @strPostalCode,
	"Country" = @strCounrty,
	"Phone" = @strPhone,
	"Fax" = @strFax
	WHERE Customers.CustomerID = @strCustomerID
END
GO

-- 10
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2019
-- Description:	Retruns orders within specified date, ordered newest to oldest.
-- =============================================
CREATE PROCEDURE GetOrdersBetweenDates
	@dateStart datetime,
	@dateEnd datetime
AS
BEGIN
	SELECT Orders.OrderID, OrderDate, [Order Details].ProductID,  [Order Details].Quantity, [Order Details].Discount, [Order Details].UnitPrice, Customers.CustomerID, Customers.CompanyName, Customers.ContactName, Products.ProductName, CONCAT(Employees.FirstName, ' ', Employees.LastName) AS EmployeeName
		FROM Orders
		JOIN [Order Details] ON Orders.OrderID = [Order Details].OrderID
		JOIN Customers ON Orders.CustomerID = Customers.CustomerID
		JOIN Products ON [Order Details].ProductID = Products.ProductID
		JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID
		WHERE OrderDate >= @dateStart AND OrderDate < @dateEnd
		ORDER BY OrderDate;
END
GO

