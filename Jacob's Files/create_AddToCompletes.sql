USE [LoZUTracker]
GO

/****** Object:  StoredProcedure [dbo].[AddToCompletes]    Script Date: 1/21/2022 12:03:03 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddToCompletes](
	@UserID_1 int,
	@QuestID_2 int
)
AS

IF(SELECT COUNT(*) FROM [User] WHERE ID = @UserID_1) = 0
BEGIN
	PRINT 'User does not exist'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Quest WHERE ID = @QuestID_2) = 0
BEGIN
	PRINT 'Quest does not exist'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Completes WHERE UserID = @UserID_1 AND QuestID = @QuestID_2) > 0
BEGIN
	PRINT 'User has already completed this quest'
	RETURN 3
END

INSERT INTO Completes(UserID, QuestID)
VALUES(@UserID_1, @QuestID_2)
PRINT 'User has successfully completed the quest!'
RETURN 0

GO

