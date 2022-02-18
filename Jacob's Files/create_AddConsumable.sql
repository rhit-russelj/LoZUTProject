USE LoZUTracker
GO

CREATE PROCEDURE AddConsumable(
	@ConsumableID int,
	@Effect varchar(50),
	@Strength int,
	@Type varchar(50)
)
AS
IF(@ConsumableID IS NULL OR @Effect IS NULL OR @Strength IS NULL OR @Type IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Consumable WHERE ID = @ConsumableID) > 0
BEGIN
	PRINT 'This consumable already exists'
	RETURN 2
END

IF(@Strength < 1)
BEGIN
	PRINT 'Strength must be a positive value'
	RETURN 3
END

INSERT INTO Consumable(ID, Effect, Strength, [Type])
VALUES(@ConsumableID, @Effect, @Strength, @Type)

RETURN 0
GO