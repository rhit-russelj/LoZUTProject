USE LoZUTracker
GO

CREATE PROCEDURE UpdateEnemy(
	@NPCID int,
	@SpawnAreas varchar(200),
	@SpawnRestrictions varchar(200),
	@Health int,
	@GameID int
)
AS
IF(@NPCID IS NULL)
BEGIN
	PRINT 'NPCID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @NPCID) = 0
BEGIN
	PRINT 'This enemy does not exist in the table'
	RETURN 2
END

IF(@Health < 1)
BEGIN
	PRINT 'Health must be a positive value'
	RETURN 3
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID) = 0
BEGIN
	PRINT 'This GameID does not match any IDs for the available games'
	RETURN 4
END

UPDATE Enemy
SET SpawnAreas = @SpawnAreas,
	SpawnRestrictions = @SpawnRestrictions,
	Health = @Health
WHERE NPCID = @NPCID
AND GameID = @GameID

RETURN 0
GO
