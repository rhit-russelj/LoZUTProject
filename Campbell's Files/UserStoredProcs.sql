--TODO: Documentation
USE LoZUTracker
GO
CREATE PROCEDURE [AddUser]
	@Username1 varchar(20),
	@Name2 varchar(20)
AS

IF (@Username1 IS NULL)
BEGIN
	RAISERROR(N'Username cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @Username1) IS NOT NULL)
BEGIN
	RAISERROR(N'Username already exists!',1,1);
	RETURN 1;
END

INSERT INTO [User](Username, [Name]) VALUES (@Username1, @Name2);
RAISERROR(N'User created successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [UpdateUser]
	@UserID1 int,
	@NewUsername2 varchar(20),
	@NewName3 varchar(20)
AS

IF (@NewUsername2 IS NULL)
BEGIN
	RAISERROR(N'Username cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @NewUsername2) IS NOT NULL)
BEGIN
	RAISERROR(N'Username already exists!',1,1);
	RETURN 1;
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.ID = @UserID1) IS NULL)
BEGIN
	RAISERROR(N'User does not exist!',1,1);
	RETURN 1;
END

UPDATE [User]
SET Username=@NewUsername2, [Name]=@NewName3
WHERE ID = @UserID1
RAISERROR(N'Updated user successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [DeleteUser]
	@Username1 varchar(20) = NULL,
	@UserID2 int
AS

IF (@UserID2 IS NULL)
BEGIN
	RAISERROR(N'Given user is null!',1,1);
	RETURN 1;
END

IF (@Username1 IS NULL)
BEGIN
	SET @Username1 = (SELECT Username
					 FROM [User] u
					 WHERE u.ID = @UserID2);
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @Username1) IS NULL)
BEGIN
	RAISERROR(N'User does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [User]
	WHERE Username = @Username1;
RAISERROR(N'User deleted successfully!',0,1);
RETURN 0;
GO
