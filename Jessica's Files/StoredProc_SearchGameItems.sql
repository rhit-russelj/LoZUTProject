CREATE PROCEDURE SearchGameItems
(
	@GameID int
)
AS
BEGIN
	if(@GameID is null)
	BEGIN
			RAISERROR('Game ID cannot be null.', 1, 1)
			RETURN 1
	END

	SELECT g.Name as [Game Name], i.ID as [Item ID], e.Name as [Item Name]
	FROM Quest q
	JOIN QuestItems i on q.ItemID = i.ID 
	JOIN Item e on i.ID = e.ID 
	JOIN Game g on g.ID = @GameID

END