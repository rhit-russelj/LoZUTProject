USE LoZUTracker
GO
CREATE PROCEDURE [AddGame]
	@PublishYear1 int,
	@Name2 varchar(50),
	@TimelineEra3 varchar(20),
	@TimelineNumber4 int,
	@System5 varchar(50)
AS

IF (@Name2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

INSERT INTO [Game](PublishYear,[Name],TimelineEra,TimelineNumber,[System]) 
	VALUES (@PublishYear1, @Name2, @TimelineEra3, @TimelineNumber4, @System5);
RAISERROR(N'Added game successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [UpdateGame]
	@GameID1 int,
	@NewPublishYear2 int,
	@NewName3 varchar(50),
	@NewTimelineEra4 varchar(20),
	@NewTimelineNumber5 int,
	@NewSystem6 varchar(50)
AS

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID1) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

IF (@NewName3 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

UPDATE [Game]
SET PublishYear=@NewPublishYear2, [Name]=@NewName3, TimelineEra=@NewTimelineEra4, TimelineNumber=@NewTimelineNumber5,
	[System]=@NewSystem6
WHERE ID = @GameID1
RAISERROR(N'Updated game successfully!',0,1);
RETURN 0;
GO

CREATE PROCEDURE [DeleteGame]
	@GameID1 int
AS

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID1) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Location]
	WHERE ID = @GameID1;
RAISERROR(N'Game successfully deleted!',0,1);
RETURN 0;
GO