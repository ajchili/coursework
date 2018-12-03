CREATE DATABASE PetAPuppyKirinPatel;

CREATE TABLE Users (
	id int NOT NULL AUTO_INCREMENT,
	firstName varchar(50),
	lastName varchar(50),
	username varchar(30) NOT NULL,
	email varchar(255),
	password varchar(60) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO Users (username, password)
VALUES ('kirinpatel', 'extremelyStrongPassword');
INSERT INTO Users (firstName, lastName, username, password)
VALUES ('Todd', 'Wolfe', 'instructor', 'password');
INSERT INTO Users (firstName, lastName, username, email, password)
VALUES ('Kirin', 'Patel', 'kirinpatel2', 'kirinpatel@gmail.com', 'extremelyStrongerPassword123');

CREATE TABLE Tickets (
	id int NOT NULL AUTO_INCREMENT,
	title varchar(255) NOT NULL,
	description varchar(255),
	userID int, -- Person who MAY be assigned the ticket
	submitDate datetime NOT NULL,
	completeDate datetime,
	currentState varchar(60) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO Tickets (title, description, userID, submitDate, currentState)
VALUES ('Test Ticket', 'Testing the ticket system', 1, GETDATE, 'backlog');
INSERT INTO Tickets (title, description, submitDate, currentState)
VALUES ('Loss of hand', 'Petting puppies causes my hands to explode????', GETDATE, 'userError');
INSERT INTO Tickets (title, description, submitDate, currentState)
VALUES ('CAN I GET SOME HELP????', 'I LOST MY HANDS', GETDATE, 'duplicate');
INSERT INTO Tickets (title, submitDate, currentState)
VALUES ('Late assignment', GETDATE, 'backlog');
INSERT INTO Tickets (title, submitDate, currentState)
VALUES ('Help dogs recover from Thanksgiving feast', GETDATE, 'inProgress');

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2019
-- Description:	Checks to see if user exists with provided username and password.
-- =============================================
CREATE PROCEDURE CheckForUser
	@strUsername varchar(50),
	@strPassword varchar(50),
	@dateEnd datetime
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
-- Create date: 11/28/2019
-- Description:	Creates new ticket.
-- =============================================
CREATE PROCEDURE CreateTicket
	@strTitle varchar(40),
	@strDescription varchar(30),
	@intUserID int,
	@dateSubmitDate datetime,
	@dateCompleteDate datetime,
	@strCurrentState varchar(60)
AS
BEGIN
    INSERT INTO Tickets(
	"title",
	"description",
	"userID",
	"submitDate",
	"completeDate",
	"currentState"
	)
	VALUES (
	@strTitle,
	@strDescription,
	@intUserID,
	@dateSubmitDate,
	@dateCompleteDate,
	@strCurrentState
	);
END
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Kirin Patel
-- Create date: 11/28/2019
-- Description:	Allows ticket to be edited.
-- =============================================
CREATE PROCEDURE EditTicket
	@intID int,
	@strTitle varchar(40),
	@strDescription varchar(30),
	@intUserID int,
	@dateSubmitDate datetime,
	@dateCompleteDate datetime,
	@strCurrentState varchar(60)
AS
BEGIN
	UPDATE Ticket
	SET 
	"title" = @strTitle,
	"description" = @strDescription,
	"userID" = @intUserID,
	"submitDate" = @dateSubmitDate,
	"completeDate" = @dateCompleteDate,
	"currentState" = @strCurrentState
	WHERE id = @intID
END
GO