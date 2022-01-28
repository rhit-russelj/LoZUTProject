CREATE PROCEDURE [DeleteEquipment] 
	@EquipmentID int
AS

IF (@EquipmentID IS NULL)
BEGIN
	RAISERROR(N'Equipment ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [Equipment] i
	WHERE i.ID = @EquipmentID) IS NULL)
BEGIN
	RAISERROR(N'Equipment does not exist!',1,1);
	RETURN 1;
END

DELETE FROM Equipment WHERE ID = @EquipmentID
RAISERROR(N'Deleted Equipment successfully!',0,1);
RETURN 0;
