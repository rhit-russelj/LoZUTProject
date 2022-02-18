USE LoZUTracker
GO

CREATE PROCEDURE DeleteDungeon
(
	@DungeonID int
)
AS
IF(@DungeonID IS NULL)
BEGIN
	PRINT 'DungeonID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Dungeon WHERE ID = @DungeonID) = 0
BEGIN
	PRINT 'The given dungeon does not exist'
	RETURN 2
END

DELETE FROM Dungeon
WHERE ID = @DungeonID

RETURN 0
GO