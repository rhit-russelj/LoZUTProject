USE [LoZUTracker]
GO

/****** Object:  StoredProcedure [dbo].[AddDungeon]    Script Date: 1/21/2022 12:02:25 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddDungeon](
	@LocationID_1 int
)
AS

IF(@LocationID_1 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM [Location] WHERE [Location].ID = @LocationID_1) = 0
BEGIN
	PRINT 'Dungeon does not currently exist as a location'
	RETURN 2
END

INSERT INTO Dungeon(ID)
VALUES(@LocationID_1)
PRINT 'Dungeon added successfully!'
RETURN 0
GO

