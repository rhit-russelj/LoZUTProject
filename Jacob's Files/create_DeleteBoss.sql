USE LoZUTracker
GO

CREATE PROCEDURE DeleteBoss(
	@EnemyID int,
	@GameID int
)
AS
IF(@EnemyID IS NULL OR @GameID IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Boss WHERE EnemyID = @EnemyID AND GameID = @GameID) = 0
BEGIN
	PRINT 'This boss does not exist in the given game'
	RETURN 2
END

DELETE FROM Boss
WHERE EnemyID = @EnemyID
AND GameID = @GameID

RETURN 0
GO