USE LoZUTracker
GO

CREATE PROCEDURE DeleteEnemy(
	@NPCID int,
	@GameID int
)
AS

IF(@NPCID IS NULL OR @GameID IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @NPCID AND GameID = @GameID) = 0
BEGIN
	PRINT 'This enemy does not exist for this given game'
	RETURN 2
END

DELETE FROM Enemy
WHERE NPCID = @NPCID
AND GameID = @GameID

RETURN 0
GO