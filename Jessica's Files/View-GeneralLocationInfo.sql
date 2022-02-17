Create VIEW GeneralLocationInformation AS
SELECT l.Name as 'Location', g.Name as 'Game Name'
FROM Location as l
JOIN Game as g on g.ID = l.GameID