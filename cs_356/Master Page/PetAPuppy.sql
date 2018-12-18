CREATE DATABASE PetAPuppyKirinPatel;
GO

USE PetAPuppyKirinPatel;

CREATE TABLE Users (
	id int NOT NULL IDENTITY,
	firstName varchar(50),
	lastName varchar(50),
	username varchar(30) NOT NULL,
	email varchar(255),
	password varchar(60),
    lastLogin datetime,
	PRIMARY KEY (id)
);

INSERT INTO Users (username, password)
VALUES ('kirinpatel', 'testing123!@#');
INSERT INTO Users (firstName, lastName, username, password)
VALUES ('Todd', 'Wolfe', 'instructor', 'testing123!@#');
INSERT INTO Users (firstName, lastName, username, email, password)
VALUES ('Kirin', 'Patel', 'kirinp', 'kirinpatel@gmail.com', 'testing123!@#');

CREATE TABLE Tickets (
	id int NOT NULL IDENTITY,
	title varchar(255) NOT NULL,
	description varchar(255),
	currentState int NOT NULL,
	ticketPriority int NOT NULL,
	dateCreated datetime NOT NULL,
	dateUpdated datetime NOT NULL,
	dateResolved datetime,
    submitter varchar(100) NOT NULL,
    assignedUser int,
	resolutionDetails varchar(255),
	PRIMARY KEY (id)
);

INSERT INTO Tickets (title, description, currentState, ticketPriority, dateCreated, dateUpdated, submitter)
VALUES ('Test Ticket', 'Testing the ticket system', 1, 0, GETDATE(), GETDATE(), 'kirinpatel@gmail.com');
INSERT INTO Tickets (title, description, currentState, ticketPriority, dateCreated, dateUpdated, dateResolved, submitter)
VALUES ('Loss of hand', 'Petting puppies causes my hands to explode????', -1, 2, GETDATE(), GETDATE(), GETDATE(), 'dumbo@dummy.com');
INSERT INTO Tickets (title, currentState, ticketPriority, dateCreated, dateUpdated, submitter)
VALUES ('Test Ticket 2', 1, 1, GETDATE(), GETDATE(), 'kirinpatel@gmail.com');
INSERT INTO Tickets (title, description, currentState, ticketPriority, dateCreated, dateUpdated, submitter)
VALUES ('Test Ticket 3', 'Testing the ticket system', 2, 2, GETDATE(), GETDATE(), 'kirinpatel@gmail.com');
INSERT INTO Tickets (title, description, currentState, ticketPriority, dateCreated, dateUpdated, submitter)
VALUES ('Test Ticket 4', 'Testing the ticket system', 3, 2, GETDATE(), GETDATE(), 'kirinpatel@gmail.com');

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/16/2018
-- Description:	Upates user.
-- =============================================
CREATE PROCEDURE EditUser
	@intId int,
	@strFirstName varchar(50),
	@strLastName varchar(50),
	@strUsername varchar(30),
	@strEmail varchar(255),
	@strPassword varchar(60)
AS
BEGIN
	UPDATE Users
	SET
	firstName = @strFirstName,
	lastName = @strLastName,
	username = @strUsername,
	email = @strEmail,
	password = @strPassword
	WHERE id = @intId
	SELECT COUNT(*) FROM USERS WHERE id = @intId
END
GO

-- OLD PROCEDURES
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description:	User login procedure. Will login user if one exists, updating their LastLoginDate.
-- =============================================
CREATE PROCEDURE LoginUser
	@strUserName varchar(30),
	@strPassword varchar(60)
AS
BEGIN
	UPDATE Users
	SET lastLogin = GETDATE()
	WHERE username = @strUserName AND password = @strPassword
	SELECT COUNT(*) FROM USERS WHERE username = @strUserName AND password = @strPassword
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 2018-10-09
-- Description:	Creates a User.
-- =============================================
CREATE PROCEDURE CreateUser
	@strUserName varchar(30),
	@strPassword varchar(60)
AS
BEGIN
	INSERT INTO Users (
	"username",
	"password",
    "lastLogin"
	)
	VALUES (
	@strUserName,
	@strPassword,
    GETDATE()
	);
END


-- END

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2018
-- Description:	Checks to see if user exists with provided username and password.
-- =============================================
CREATE PROCEDURE CheckForUser
	@strUsername varchar(30),
	@strPassword varchar(60)
AS
BEGIN
	SELECT * from Users WHERE username = @strUsername AND password = @strPassword;
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description:	Checks to see if user exists with provided username.
-- =============================================
CREATE PROCEDURE DoesUserExist
	@strUsername varchar(30)
AS
BEGIN
	SELECT * from Users WHERE username = @strUsername;
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description: Activate user.
-- =============================================
CREATE PROCEDURE ActivateUser
	@intId int,
	@strPassword varchar(60)
AS
BEGIN
	UPDATE Users
	SET
	password = @strPassword
	WHERE
	id = @intId;
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description:	Deactivates user.
-- =============================================
CREATE PROCEDURE DeactivateUser
	@intId int
AS
BEGIN
	UPDATE Users
	SET
	password = NULL
	WHERE
	id = @intId;
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2018
-- Description:	Creates new ticket.
-- =============================================
CREATE PROCEDURE CreateTicket
	@strTitle varchar(255),
	@strDescription varchar(255),
	@intCurrentState int,
	@intPriority int,
	@dateResolved datetime,
    @strSubmitter varchar(100),
    @intAssignedUser int
AS
BEGIN
	SET NOCOUNT OFF;
    INSERT INTO Tickets(
	"title",
	"description",
	"currentState",
	"ticketPriority",
	"dateCreated",
	"dateUpdated",
	"dateResolved",
    "submitter",
    "assignedUser"
	)
	VALUES (
	@strTitle,
	@strDescription,
	@intCurrentState,
	@intPriority,
	GETDATE(),
	GETDATE(),
	@dateResolved,
    @strSubmitter,
    @intAssignedUser
	);
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2018
-- Description:	Allows ticket to be edited.
-- =============================================
CREATE PROCEDURE EditTicket
	@intID int,
	@strTitle varchar(255),
	@strDescription varchar(255),
	@intCurrentState int,
	@intPriority int,
    @strSubmitter varchar(100),
    @intAssignedUser int
AS
BEGIN
	SET NOCOUNT OFF;
	UPDATE Tickets
	SET 
	"title" = @strTitle,
	"description" = @strDescription,
	"currentState" = @intCurrentState,
	"ticketPriority" = @intPriority,
	"dateUpdated" = GETDATE(),
    "submitter" = @strSubmitter,
    "assignedUser" = @intAssignedUser
	WHERE id = @intID
END
GO


SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2018
-- Description:	Allows ticket to be resolved.
-- =============================================
CREATE PROCEDURE ResolveTicket
	@intID int,
	@strTitle varchar(255),
	@strDescription varchar(255),
	@intCurrentState int,
	@intPriority int,
	@dateResolved datetime,
    @strSubmitter varchar(100),
    @intAssignedUser int,
	@strResolutionDetails varchar(255)
AS
BEGIN
	SET NOCOUNT OFF;
	UPDATE Tickets
	SET 
	"title" = @strTitle,
	"description" = @strDescription,
	"currentState" = @intCurrentState,
	"ticketPriority" = @intPriority,
	"dateUpdated" = GETDATE(),
	"dateResolved" = @dateResolved,
    "submitter" = @strSubmitter,
    "assignedUser" = @intAssignedUser,
	"resolutionDetails" = @strResolutionDetails
	WHERE id = @intID
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/04/2018
-- Description:	Returns all tickets.
-- =============================================
CREATE PROCEDURE GetAllTickets
AS
BEGIN
	SELECT * from Tickets
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description:	Returns all tickets at specified priority.
-- =============================================
CREATE PROCEDURE GetAllTicketsAtPriority
	@intPriority int
AS
BEGIN
	SELECT * from Tickets
	WHERE ticketPriority = @intPriority
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/04/2018
-- Description:	Returns specified ticket.
-- =============================================
CREATE PROCEDURE GetTicket
	@intID int
AS
BEGIN
	SELECT * from Tickets WHERE id = @intID
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/04/2018
-- Description:	Returns all tickets associated with a submitter.
-- =============================================
CREATE PROCEDURE GetTickets
    @strSubmitter varchar(100)
AS
BEGIN
	SELECT * from Tickets WHERE submitter = @strSubmitter
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description:	Returns all resolved tickets associated with a user.
-- =============================================
CREATE PROCEDURE GetMyResolvedTickets
    @intAssignedUser int
AS
BEGIN
	SELECT * from Tickets WHERE assignedUser = @intAssignedUser AND dateResolved IS NOT NULL
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 12/18/2018
-- Description:	Returns all users.
-- =============================================
CREATE PROCEDURE GetAllUsers
AS
BEGIN
	SELECT * from Users
END
GO