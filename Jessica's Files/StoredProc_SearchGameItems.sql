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

	SELECT g.Name as [Game Name], e.ID as [Item ID], e.Name as [Item Name]
	FROM Item e
	JOIN Game g on g.ID = e.GameID

END