USE LoZUTracker
GO
CREATE PROCEDURE DeleteUserOwns
	@GameID int,
	@UserID int
AS
IF(@GameID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Arguments cannot be null', 1, 1);
	RETURN 1;
END
ELSE IF(EXISTS (SELECT *
		FROM UserOwns
		WHERE UserOwns.GameID = @GameID AND UserOwns.UserID = @UserID))
BEGIN
	DELETE FROM UserOwns
	WHERE UserOwns.GameID = @GameID AND UserOwns.UserID = @UserID
	RAISERROR('User owned game removed successfully', 0, 1);
	RETURN 0;
END
ELSE
BEGIN
	RAISERROR('Data does not exist in the table', 1, 1);
	RETURN 1;
END
GO