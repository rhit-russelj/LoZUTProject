USE LoZUTracker
GO
CREATE PROCEDURE dbo.AddUserOwns
	@GameID int,
	@UserID int
AS
IF(@GameID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1);
	RETURN 1;
END
ELSE
BEGIN
	INSERT INTO UserOwns(UserID, GameID)
	VALUES(@UserID, @GameID);
	RAISERROR('User owned game added successfully', 0, 1);
	RETURN 0;
END
GO