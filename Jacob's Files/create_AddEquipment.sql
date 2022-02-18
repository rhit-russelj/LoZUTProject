USE LoZUTracker
GO

CREATE PROCEDURE AddEquipment(
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

IF(SELECT COUNT(*) FROM Equipment WHERE ID = @EquipmentID) > 0
BEGIN
	PRINT 'This piece of Equipment already exists'
	RETURN 3
END

INSERT INTO Equipment(ID, [Type])
VALUES(@EquipmentID, @Type)

RETURN 0
GO