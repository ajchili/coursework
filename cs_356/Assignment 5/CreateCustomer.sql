SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description:	Inserts user into database based on provided data.
-- =============================================
CREATE PROCEDURE CreateCustomer
	@strFirstName varchar(30),
	@strLastName varchar(30),
	@strAddress varchar(100),
	@strCity varchar(30),
	@strState char(2),
	@strZipCode varchar(10),
	@strPhone char(12),
	@strPhoneType varchar(10),
	@strEmaiLAddress varchar(100),
	@strUserName varchar(30),
	@strPassword varchar(20)
AS
BEGIN
	INSERT INTO tblCustomers(
	"FirstName",
	"LastName",
	"Address",
	"City",
	"State",
	"ZipCode",
	"Phone",
	"PhoneType",
	"EmailAddress",
	"UserName",
	"Password"
	)
	VALUES (
	@strFirstName,
	@strLastName,
	@strAddress,
	@strCity,
	@strState,
	@strZipCode,
	@strPhone,
	@strPhoneType,
	@strEmaiLAddress,
	@strUserName,
	@strPassword
	);
END
GO
