USE LoZUTracker
GO

CREATE PROCEDURE UpdateConsumable(
	@ConsumableID int,
	@Effect varchar(50),
	@Strength int,
	@Type varchar(50)
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

IF(@Strength < 1)
BEGIN
	PRINT 'Strength value must be positive'
	RETURN 3
END

UPDATE Consumable
SET Effect = @Effect,
	Strength = @Strength,
	[Type] = @Type
WHERE ID = @ConsumableID

RETURN 0
GO