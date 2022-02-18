USE LoZUTracker
GO

CREATE PROCEDURE AddQuestItem(
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
	PRINT 'QuestItem does not already exist as an Item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM QuestItems WHERE ID = @QuestItemID) > 0
BEGIN
	PRINT 'This QuestItem already exists'
	RETURN 3
END

INSERT INTO QuestItems(ID, Purpose)
VALUES(@QuestItemID, @Purpose)

RETURN 0
GO