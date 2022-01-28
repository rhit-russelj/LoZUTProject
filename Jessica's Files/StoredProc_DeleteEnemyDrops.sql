CREATE PROCEDURE [DeleteEnemyDrops] 
	@EnemyID int,
	@ItemID int
AS

IF (@ItemID IS NULL)
BEGIN
	RAISERROR(N'Item ID cannot be null!',1,1);
	RETURN 1;
END

IF (@EnemyID IS NULL)
BEGIN
	RAISERROR(N'Enemy ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [Item] i
	WHERE i.ID = @ItemID) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

IF((SELECT e.NPCID
	FROM [Enemy] e
	WHERE e.NPCID = @EnemyID) IS NULL)
BEGIN
	RAISERROR(N'Enemy does not exist!',1,1);
	RETURN 1;
END

IF(NOT EXISTS (SELECT f.ItemID
	FROM [EnemyDrops] f
	WHERE f.EnemyID = @EnemyID AND f.ItemID = @ItemID))
BEGIN
	RAISERROR(N'There is not an item with that ID that already exists for this enemy!',1,1);
	RETURN 1;
END

DELETE FROM EnemyDrops Where EnemyID = @EnemyID AND ItemID = @ItemID
RAISERROR(N'Added item drop for enemy successfully!',0,1);
RETURN 0;
