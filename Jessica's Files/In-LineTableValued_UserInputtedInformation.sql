Alter FUNCTION UserInputtedInformation
(
	@userID int
)
RETURNS table
AS 
RETURN 
(
	SELECT DISTINCT u.[Name] as Username, g.[Name] as 'Game Name', g.System as 'Game System', i.Name as 'Item Name', d.BossID as BossID, q.[Name] as 'Quest Name'
	FROM [User] u
	JOIN [UserOwns] o on o.UserID = u.ID
	JOIN [Game] g on g.ID = o.GameID
	JOIN [HasItem] h on h.UserID = u.ID
	JOIN [Item] i on i.ID = h.ItemID
	JOIN [Defeated] d on d.UserID = u.ID
	JOIN [Completes] c on c.UserID = u.ID
	JOIN [Quest] q on q.ID = c.QuestID
	WHERE c.UserID = @userID
)