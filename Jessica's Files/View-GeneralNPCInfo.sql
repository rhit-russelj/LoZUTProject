Create VIEW GeneralNPCInformation AS
SELECT n.Name as 'NPC Name', n.Description as 'NPC Desc.', g.Name as 'Game Name'
FROM NPC as n
JOIN Game as g on g.ID = n.GameID