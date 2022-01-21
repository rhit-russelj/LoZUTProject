USE LoZUTracker
GO
CREATE PROCEDURE DeleteUserItems
    @ItemID int,
    @UserID int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE
BEGIN
    DELETE FROM HasItem
	WHERE HasItem.ItemID = @ItemID AND HasItem.UserID = @UserID
    RAISERROR('User items removed successfully', 0, 1);
    RETURN 0;
END
GO


