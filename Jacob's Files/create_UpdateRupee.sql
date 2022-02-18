USE LoZUTracker
GO

CREATE PROCEDURE UpdateRupee(
	@RupeeID int,
	@Color varchar(50),
	@Value int
)
AS

IF(@RupeeID IS NULL)
BEGIN
	PRINT 'Rupee ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Item WHERE ID = @RupeeID) = 0
BEGIN
	PRINT 'This Rupee does not already exist as an Item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Rupee WHERE ID = @RupeeID) = 0
BEGIN
	PRINT 'This Rupee does not exist'
	RETURN 3
END

UPDATE Rupee
SET Color = @Color,
	[Value] = @Value
WHERE ID = @RupeeID

RETURN 0
GO