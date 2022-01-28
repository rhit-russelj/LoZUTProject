CREATE PROCEDURE [DeleteItemFoundInLocation] 
	@ItemID int,
	@GameID int,
	@LocationID int
AS

IF (@ItemID IS NULL)
BEGIN
	RAISERROR(N'Item ID cannot be null!',1,1);
	RETURN 1;
END

IF (@GameID IS NULL)
BEGIN
	RAISERROR(N'Game ID cannot be null!',1,1);
	RETURN 1;
END

IF (@LocationID IS NULL)
BEGIN
	RAISERROR(N'Location ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [Item] i
	WHERE i.ID = @ItemID) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END
IF((SELECT l.ID
	FROM [Location] l
	WHERE l.ID = @LocationID AND l.GameID = @GameID) IS NULL)
BEGIN
	RAISERROR(N'Location in game does not exist!',1,1);
	RETURN 1;
END

IF(NOT EXISTS (SELECT f.ItemID
	FROM [FoundIn] f
	WHERE f.ItemID = @ItemID AND f.LocationID = @LocationID))
BEGIN
	RAISERROR(N'There is not an item with that ID that already exists in that Location!',1,1);
	RETURN 1;
END

DELETE FROM FoundIn WHERE ItemID = @ItemID AND LocationID = @LocationID
RAISERROR(N'Deleted item from location successfully!',0,1);
RETURN 0;
