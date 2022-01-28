Create Procedure AddDefeated
	@UserID int,
	@BossID int
AS

DECLARE @boss AS int
SET @boss = (SELECT BossID From Defeated Where BossID = @BossID and UserID = @UserID)


IF(@UserID IS NULL or @BossID is NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@boss = @BossID)
BEGIN
	RAISERROR('User already defeated that boss before!', 1, 1)
END
ELSE
BEGIN
	INSERT INTO dbo.Defeated
	VALUES (@UserID, @BossID)
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END