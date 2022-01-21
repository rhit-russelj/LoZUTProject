USE LoZUTracker
GO
CREATE PROCEDURE UpdateUserItems
	@ItemID int,
	@UserID int,
	@Quantity int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@Quantity < 1 OR @Quantity IS NULL)
BEGIN
	RAISERROR('Quantity of an item cannot be less than 1', 1, 1)
END
ELSE
BEGIN
	UPDATE HasItem
	SET HasItem.Quantity = @Quantity
	WHERE HasItem.UserID = @UserID AND HasItem.ItemID = @ItemID
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END