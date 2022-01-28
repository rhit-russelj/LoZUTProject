Create Procedure AddItem
	@Name varchar(50),
	@Description varchar(200)
AS

IF (@Name IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

INSERT INTO [Item] ([Name], Description) 
	VALUES (@Name,@Description);
RAISERROR(N'Added Item successfully!',0,1);
RETURN 0;
