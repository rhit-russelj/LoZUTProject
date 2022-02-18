USE LoZUTracker
GO

CREATE PROCEDURE UpdateEquipment(
	@EquipmentID int,
	@Type varchar(50)
)
AS

IF(@EquipmentID IS NULL)
BEGIN
	PRINT 'Equipment ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Item WHERE ID = @EquipmentID) = 0
BEGIN
	PRINT 'This Equipment does not already exist as an Item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Equipment WHERE ID = @EquipmentID) = 0
BEGIN
	PRINT 'This Equipment does not exist'
	RETURN 3
END

UPDATE Equipment
SET [Type] = @Type
WHERE ID = @EquipmentID

RETURN 0
GO