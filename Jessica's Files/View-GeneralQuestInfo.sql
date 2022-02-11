CREATE VIEW GeneralQuestInformation AS
SELECT q.Name as 'Quest Name', q.Objective, q.Storyline, g.Name as 'Game Name'
FROM Quest as q
JOIN Game as g on g.ID = q.GameID