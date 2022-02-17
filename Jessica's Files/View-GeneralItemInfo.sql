Create VIEW GeneralItemInformation AS
SELECT i.Name as 'Item Name', i.Description, g.Name as 'Game Name'
FROM Item as i
JOIN Game as g on g.ID = i.GameID