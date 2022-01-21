USE LoZUTracker
GO
CREATE PROCEDURE [AddQuest]
	@Name1 varchar(50),
	@Objective2 varchar(200),
	@Storyline3 varchar(50),
	@Repeatable4 bit = 0,
	@ItemID5 int = NULL,
	@NextQuestID6 int = NULL,
	@PreviousQuestID7 int = NULL,
	@GameID8 int,
	@NPCID9 int = NULL
AS
	IF(@Name1 IS NULL)
	BEGIN
		RAISERROR(N'Name cannot be null!',1,1);
		RETURN 1;
	END

	IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID8) IS NULL)
	BEGIN
		RAISERROR(N'Game does not exist!',1,1);
		RETURN 1;
	END

	--Next few branches make sure that a foreign key exists if and only if it is given
	IF(@ItemID5 IS NOT NULL)
	BEGIN
		IF((SELECT i.ID
		FROM [Item] i
		WHERE i.ID = @ItemID5) IS NULL)
		BEGIN
			RAISERROR(N'Item does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@NextQuestID6 IS NOT NULL)
	BEGIN
		IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @NextQuestID6) IS NULL)
		BEGIN
			RAISERROR(N'Next quest does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@PreviousQuestID7 IS NOT NULL)
	BEGIN
		IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @PreviousQuestID7) IS NULL)
		BEGIN
			RAISERROR(N'Previous quest does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@NPCID9 IS NOT NULL)
	BEGIN
		IF((SELECT n.ID
		FROM [NPC] n
		WHERE n.ID = @NPCID9) IS NULL)
		BEGIN
			RAISERROR(N'NPC does not exist!',1,1);
			RETURN 1;
		END
	END


	INSERT INTO [Quest]([Name],[Objective],[Storyline],[Repeatable],[ItemID],[NextQuestID],[PreviousQuestID],[GameID],[NPCID])
		VALUES (@Name1,@Objective2,@Storyline3,@Repeatable4,@ItemID5,@NextQuestID6,@PreviousQuestID7,@GameID8,@NPCID9);
	RAISERROR(N'Quest successfully added!',0,1);
	RETURN 0;
GO
CREATE PROCEDURE [UpdateQuest]
	@QuestID1 int,
	@Name2 varchar(50),
	@Objective3 varchar(200),
	@Storyline4 varchar(50),
	@Repeatable5 bit = 0,
	@ItemID6 int = NULL,
	@NextQuestID7 int = NULL,
	@PreviousQuestID8 int = NULL,
	@GameID9 int,
	@NPCID10 int
AS
	IF(@Name2 IS NULL)
	BEGIN
		RAISERROR(N'Name cannot be null!',1,1);
		RETURN 1;
	END

	UPDATE [Quest]
	SET [Name]=@Name2,[Objective]=@Objective3,[Storyline]=@Storyline4,[Repeatable]=@Repeatable5,[ItemID]=@ItemID6,
		[NextQuestID]=@NextQuestID7,[PreviousQuestID]=@PreviousQuestID8,[GameID]=@GameID9,[NPCID]=@NPCID10
	WHERE ID=@QuestID1;
	RAISERROR(N'Quest successfully updated!',0,1);
	RETURN 0;

GO

CREATE PROCEDURE [DeleteQuest]
	@QuestID1 int
AS

	IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @QuestID1) IS NULL)
	BEGIN
		RAISERROR(N'Quest does not exist!',1,1);
		RETURN 1;
	END

	DELETE FROM [Location]
		WHERE ID = @QuestID1;
	RAISERROR(N'Quest successfully deleted!',0,1);
	RETURN 0;
GO