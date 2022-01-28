CREATE PROCEDURE [DeleteRupee] 
	@RupeeID int
AS

IF (@RupeeID IS NULL)
BEGIN
	RAISERROR(N'Item ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [Rupee] i
	WHERE i.ID = @RupeeID) IS NULL)
BEGIN
	RAISERROR(N'Rupee does not exist to be deleted!',1,1);
	RETURN 1;
END


DELETE FROM Rupee WHERE ID = @RupeeID
RAISERROR(N'Deleted Rupee successfully!',0,1);
RETURN 0;
