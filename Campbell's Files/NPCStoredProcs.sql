USE LoZUTracker
GO
CREATE PROCEDURE [AddNPC]
	@Name1 varchar(50),
	@Description2 varchar(200),
	@GameID3 int
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID3) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END


INSERT INTO [NPC]([Name],[Description],GameID) VALUES (@Name1, @Description2, @GameID3)
RAISERROR(N'Added NPC successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [UpdateNPC]
	@NPCID1 int,
	@NewName2 varchar(20),
	@NewDescription3 varchar(200),
	@NewGameID4 int
AS

IF((SELECT n.ID
	FROM [NPC] n
	WHERE n.ID = @NPCID1) IS NULL)
BEGIN
	RAISERROR(N'NPC does not exist!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @NewGameID4) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

UPDATE [NPC]
SET [Name]=@NewName2, [Description]=@NewDescription3, GameID=@NewGameID4
WHERE ID = @NPCID1
RAISERROR(N'Updated NPC successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [DeleteNPC]
	@NPCID1 int
AS

IF((SELECT n.ID
	FROM [NPC] n
	WHERE n.ID = @NPCID1) IS NULL)
BEGIN
	RAISERROR(N'NPC does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [NPC]
	WHERE ID = @NPCID1;
RAISERROR(N'NPC successfully deleted!',0,1);
RETURN 0;
GO
