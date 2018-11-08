USE [PAPHelpDesk]
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description:	User login procedure. Will login user if one exists, updating their LastLoginDate.
-- =============================================
ALTER PROCEDURE [dbo].[LoginUser]
	@strUserName varchar(10),
	@strPassword varchar(100)
AS
BEGIN
	UPDATE tblUsers
	SET LastLoginDate = GETDATE()
	WHERE UserName = @strUserName AND Password = @strPassword;
END
