Alter PROCEDURE AddGameAndNPCs
(
	@GameName varchar(50),
	@PublishYear int,
	@TimelineEra varchar(20),
	@TimelineNumber int,
	@System varchar(50),
	@NPCName varchar(50),
	@NPCDescription varchar(200)
)
AS
BEGIN
	if(@GameName is null)
	BEGIN
			RAISERROR('Game name cannot be null.', 1, 1)
			RETURN 1
	END

	if(@PublishYear is null)
	BEGIN
			RAISERROR('Publish year cannot be null.', 1, 1)
			RETURN 1
	END

	if(@TimelineEra is null)
	BEGIN
			RAISERROR('Timeline era cannot be null.', 1, 1)
			RETURN 1
	END

	if(@TimelineNumber is null)
	BEGIN
			RAISERROR('Timeline Number cannot be null.', 1, 1)
			RETURN 1
	END

	if(@System is null)
	BEGIN
			RAISERROR('System cannot be null.', 1, 1)
			RETURN 1
	END

	if(@NPCName is null)
	BEGIN
			RAISERROR('NPC name cannot be null.', 1, 1)
			RETURN 1
	END

	if(@NPCDescription is null)
	BEGIN
			RAISERROR('NPC Description cannot be null.', 1, 1)
			RETURN 1
	END

	INSERT INTO [Game](PublishYear,[Name],TimelineEra,TimelineNumber,[System]) 
	VALUES (@PublishYear, @GameName, @TimelineEra, @TimelineNumber, @System);
	RAISERROR(N'Added game successfully!',0,1);
	DECLARE @gID int
	SET @gID = (SELECT ID FROM [Game] WHERE Game.Name = @GameName)

	INSERT INTO [NPC]([Name],[Description], [GameID]) VALUES (@NPCName, @NPCDescription, @gID)
	RAISERROR(N'Added NPC successfully!',0,1);
	RETURN 0;

END