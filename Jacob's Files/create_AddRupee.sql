USE [LoZUTracker]
GO
/****** Object:  StoredProcedure [dbo].[AddRupee]    Script Date: 1/28/2022 11:21:26 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[AddRupee](
	@RupeeID int,
	@Color varchar(50),
	@Value int
)
AS

IF(@RupeeID IS NULL)
BEGIN
	PRINT 'Rupee ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Rupee WHERE ID = @RupeeID) > 0
BEGIN
	PRINT 'This Rupee already exists'
	RETURN 2
END

INSERT INTO Rupee(ID, Color, [Value])
VALUES(@RupeeID, @Color, @Value)

RETURN 0
