--TODO: Documentation
USE LoZUTracker
GO
CREATE PROCEDURE [AddToCompleted]
	@UserID1 int,
	@DungeonID2 int
AS

IF ((SELECT u.ID
	FROM [User] u
	WHERE u.ID = @UserID1) IS NULL)
BEGIN
	RAISERROR(N'Username does not exist!',1,1);
	RETURN 1;
END

--Important because it makes sure that the dungeon is, in fact, a dungeon
IF ((SELECT d.ID
	FROM [Dungeon] d
	WHERE d.ID = @DungeonID2) IS NULL)
BEGIN
	RAISERROR(N'Dungeon does not exist!',1,1);
	RETURN 1;
END

INSERT INTO [Completed](UserID, DungeonID) VALUES (@UserID1, @DungeonID2)
RAISERROR(N'Added to completed list of dungeons!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [RemoveFromCompleted]
	@UserID1 int,
	@DungeonID2 int
AS

IF ((SELECT c.UserID
	FROM [Completed] c
	WHERE c.UserID = @UserID1 AND c.DungeonID = @DungeonID2) IS NULL)
BEGIN
	RAISERROR(N'User has not completed this dungeon!',1,1);
	RETURN 1;
END

DELETE FROM [Completed]
	WHERE UserID = @UserID1 AND DungeonID = @DungeonID2;
RAISERROR(N'Removed from list of completed dungeons!',0,1);
RETURN 0;
GO

