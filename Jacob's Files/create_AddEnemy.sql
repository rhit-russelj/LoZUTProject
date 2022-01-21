USE [LoZUTracker]
GO

/****** Object:  StoredProcedure [dbo].[AddEnemy]    Script Date: 1/21/2022 12:02:44 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddEnemy](
	@NPCID_1 int,
	@SpawnAreas_2 varchar(200),
	@Attacks_3 varchar(200),
	@SpawnRestrictions_4 varchar(200),
	@Health_5 int,
	@GameID_6 int
)
AS

IF(@NPCID_1 IS NULL OR @SpawnAreas_2 IS NULL OR @Attacks_3 IS NULL OR @SpawnRestrictions_4 IS NULL OR @Health_5 IS NULL OR @GameID_6 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM NPC WHERE ID = @NPCID_1) = 0
BEGIN
	PRINT 'This enemy does not exist already as an NPC'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID_6) = 0
BEGIN
	PRINT 'This game does not exist'
	RETURN 3
END

IF(@Health_5 < 1)
BEGIN
	PRINT 'Health value must be greater than zero'
	RETURN 4
END

INSERT INTO Enemy(NPCID, SpawnAreas, Attacks, SpawnRestrictions, Health, GameID)
VALUES (@NPCID_1, @SpawnAreas_2, @Attacks_3, @SpawnRestrictions_4, @Health_5, @GameID_6)
PRINT 'Enemy added successfully!'
RETURN 0

GO

