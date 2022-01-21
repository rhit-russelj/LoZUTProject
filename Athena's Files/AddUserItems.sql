USE LoZUTracker
GO
CREATE PROCEDURE AddUserItems
    @ItemID int,
    @UserID int,
	@Quantity int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE IF(@Quantity < 1 OR @Quantity IS NULL)
BEGIN
    RAISERROR('Quantity of an item cannot be less than 1', 0, 1);
    RETURN 0;
END
ELSE
BEGIN
    INSERT INTO HasItem(UserID, ItemID, Quantity)
    VALUES(@UserID, @ItemID, @Quantity);
    RAISERROR('User items added successfully', 0, 1);
    RETURN 0;
END
GO