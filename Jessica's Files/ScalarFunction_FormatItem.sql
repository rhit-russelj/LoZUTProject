ALTER FUNCTION FormatGameItems
( 
	@description varchar(200),
	@itemName varchar(50)
)
RETURNS varchar(300)
AS 
BEGIN
	RETURN @itemName + ' - ' + @description
END