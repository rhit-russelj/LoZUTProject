CREATE Procedure UpdateDefeated
	@UserID int,
	@BossID int,
	@NewBossID int
AS

DECLARE @boss AS int
SET @boss = (SELECT BossID From Defeated Where UserID = @UserID AND BossID = @BossID)


IF(@UserID IS NULL or @BossID is NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@boss = @NewBossID)
BEGIN
	RAISERROR('Nothing is updated', 1, 1)
END
ELSE
BEGIN
	UPDATE dbo.Defeated
	SET BossID = @NewBossID
	WHERE Defeated.UserID = @UserID AND Defeated.BossID = @BossID
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END