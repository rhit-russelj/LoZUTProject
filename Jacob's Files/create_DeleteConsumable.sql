USE LoZUTracker
GO

CREATE PROCEDURE DeleteConsumable(
	@ConsumableID int
)
AS

IF(@ConsumableID IS NULL)
BEGIN
	PRINT 'Consumable ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Consumable WHERE ID = @ConsumableID) = 0
BEGIN
	PRINT 'This Consumable does not exist'
	RETURN 2
END

DELETE FROM Consumable
WHERE ID = @ConsumableID

RETURN 0
GO