CREATE PROCEDURE [DeleteQuestItem] 
	@QuestItemID int
AS

IF (@QuestItemID IS NULL)
BEGIN
	RAISERROR(N'Quest item ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [QuestItems] i
	WHERE i.ID = @QuestItemID) IS NULL)
BEGIN
	RAISERROR(N'Quest item does not exist!',1,1);
	RETURN 1;
END

Delete From QuestItems WHERE ID = @QuestItemID
RAISERROR(N'Deleted Quest Item successfully!',0,1);
RETURN 0;
