USE [LoZUTracker]
GO

/****** Object:  StoredProcedure [dbo].[AddBoss]    Script Date: 1/21/2022 12:02:06 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddBoss](
	@EnemyID_1 int,
	@GameID_2 int
)
AS

IF(@EnemyID_1 IS NULL OR @GameID_2 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @EnemyID_1) = 0
BEGIN
	PRINT 'Boss does not already exist as an enemy'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID_2) = 0
BEGIN
	PRINT 'Game does not exist'
	RETURN 3
END

INSERT INTO Boss(EnemyID, GameID)
VALUES(@EnemyID_1, @GameID_2)
PRINT 'Boss added successfully!'
RETURN 0

GO

