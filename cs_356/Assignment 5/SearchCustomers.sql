USE [webProject]
GO
/****** Object:  StoredProcedure [dbo].[SearchCustomers]    Script Date: 10/9/2018 7:09:35 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description: Search all customers in database based on given input
-- =============================================
ALTER PROCEDURE [dbo].[SearchCustomers]
	@strCustomerID int,
	@strLastName varchar(30),
	@strUserName varchar(30),
	@strEmaiLAddress varchar(100),
	@strPhone varchar(20)
AS
BEGIN
	SELECT * FROM tblCustomers WHERE
	-- Searches for provided customer ID, if none is provided, all
	-- customers are provided to next query
	(CustomerID = @strCustomerID OR @strCustomerID IS NULL) AND
	-- Searches for provided last name, if none is provided, all
	-- queried customers are provided to next query
	(LastName = @strLastName OR @strLastName IS NULL) AND
	-- Searches for provided username, if none is provided, all
	-- queried customers are provided to next query
	(Username = @strUserName OR @strUserName IS NULL) AND
	-- Searches for provided email address, if none is provided, all
	-- queried customers are provided to next query
	(EmaiLAddress = @strEmaiLAddress OR @strEmaiLAddress IS NULL) AND
	-- Searches for provided phone, if none is provided, all
	-- queried customers are provided to next query
	(Phone = @strPhone OR @strPhone IS NULL);
END
