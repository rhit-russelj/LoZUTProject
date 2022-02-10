USE LoZUTracker
GO
CREATE PROCEDURE [AddItem]
	@Name1 varchar(50),
	@Description2 varchar(200)
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

INSERT INTO Item([Name],[Description]) VALUES (@Name1, @Description2)
RAISERROR(N'Item added successfully',0,1);
RETURN 0;
GO

CREATE PROCEDURE [UpdateItem]
	@ItemID1 int,
	@NewName2 varchar(50),
	@NewDescription3 varchar(200)
AS

IF((SELECT i.ID
	FROM [Location] i
	WHERE i.ID = @ItemID1) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

UPDATE [Item]
SET [Name]=@NewName2, [Description]=@NewDescription3
WHERE ID = @ItemID1
RAISERROR(N'Updated item successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [DeleteItem]
	@ItemID1 int
AS

IF((SELECT i.ID
	FROM [Location] i
	WHERE i.ID = @ItemID1) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Item]
	WHERE ID = @ItemID1;
RAISERROR(N'Item successfully deleted!',0,1);
RETURN 0;

GO




