USE LoZUTracker
GO
CREATE PROCEDURE [AddLocation]
	@Name1 varchar(20),
	@GameID2 int
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID2) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END


INSERT INTO [Location]([Name],GameID) VALUES (@Name1, @GameID2)
RAISERROR(N'Updated location successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [UpdateLocation]
	@LocationID1 int,
	@NewName2 varchar(20),
	@NewGameID3 int
AS

IF((SELECT l.ID
	FROM [Location] l
	WHERE l.ID = @LocationID1) IS NULL)
BEGIN
	RAISERROR(N'Location does not exist!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @NewGameID3) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

UPDATE [Location]
SET [Name]=@NewName2, GameID=@NewGameID3
WHERE ID = @LocationID1
RAISERROR(N'Updated location successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [DeleteLocation]
	@LocationID1 int
AS

IF((SELECT l.ID
	FROM [Location] l
	WHERE l.ID = @LocationID1) IS NULL)
BEGIN
	RAISERROR(N'Location does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Location]
	WHERE ID = @LocationID1;
RAISERROR(N'Location successfully deleted!',0,1);
RETURN 0;
GO