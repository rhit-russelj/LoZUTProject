USE LoZUTracker
GO

CREATE PROCEDURE UpdateQuestItem(
	@QuestItemID int,
	@Purpose varchar(200)
)
AS

IF(@QuestItemID IS NULL)
BEGIN
	PRINT 'QuestItem ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Item WHERE ID = @QuestItemID) = 0
BEGIN
	PRINT 'This QuestItem does not already exist as an Item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM QuestItems WHERE ID = @QuestItemID) = 0
BEGIN
	PRINT 'This QuestItem does not exist'
	RETURN 3
END

UPDATE QuestItems
SET Purpose = @Purpose
WHERE ID = @QuestItemID

RETURN 0
GO