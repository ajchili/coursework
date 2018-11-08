USE [PAPHelpDesk]
GO
/****** Object:  StoredProcedure [dbo].[CreateUser]    Script Date: 11/7/2018 7:21:38 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description:	Creates a User.
-- =============================================
ALTER PROCEDURE [dbo].[CreateUser]
	@strUserName varchar(10),
	@strPassword varchar(100)
AS
BEGIN
	INSERT INTO tblUsers(
	"UserName",
	"Password",
	"CreateDate",
	"LastLoginDate"
	)
	VALUES (
	@strUserName,
	@strPassword,
	GETDATE(),
	GETDATE()
	);
END
