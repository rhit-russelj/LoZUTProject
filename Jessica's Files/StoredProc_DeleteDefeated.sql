CREATE PROCEDURE DeleteDefeated
	@UserID int,
	@BossID int
AS

IF(@UserID IS NULL OR @BossID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE
BEGIN
    DELETE FROM Defeated
	WHERE Defeated.BossID = @BossID AND Defeated.UserID = @UserID
    RAISERROR('Defeated was removed successfully', 0, 1);
    RETURN 0;
END