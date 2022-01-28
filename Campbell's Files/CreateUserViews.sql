-------------------------------------------------------------
--                                                         --
-- Creates all the user views for the LoZUTracker database --
--                                                         --
-------------------------------------------------------------
USE LoZUTracker
GO

CREATE VIEW UserOwns
AS
SELECT Username, Game.[Name] AS [Game], PublishYear, [System] FROM [User] u
LEFT JOIN UserOwns ON UserOwns.UserID = u.ID
JOIN Game ON Game.ID = UserOwns.GameID;
GO

CREATE VIEW UserDungeons
AS
SELECT Username, [Location].[Name] As [Dungeon], Game.[Name] AS Game FROM [User] u
LEFT JOIN Completed ON Completed.UserID = u.ID
JOIN Dungeon ON Dungeon.ID = Completed.DungeonID
JOIN [Location] ON [Location].ID = Dungeon.ID
JOIN Game ON Game.ID = [Location].GameID;
GO

CREATE VIEW UserQuests
AS
SELECT Username, Quest.[Name] AS Quest, Quest.Objective, Game.[Name] AS Game FROM [User] u
LEFT JOIN Completes ON Completes.UserID = u.ID
JOIN Quest ON Quest.ID = Completes.QuestID
JOIN Game ON Game.ID = Quest.GameID;
GO

CREATE VIEW UserBosses
AS
SELECT Username, NPC.[Name] AS Boss, NPC.[Description], Game.[Name] AS Game FROM [User] u
LEFT JOIN Defeated ON Defeated.UserID = u.ID
JOIN Boss ON Defeated.BossID = Boss.EnemyID
JOIN Enemy ON Enemy.NPCID = Boss.EnemyID
JOIN NPC ON Enemy.NPCID = NPC.ID
JOIN Game ON Game.ID = NPC.GameID;
GO

CREATE VIEW UserItems
AS
SELECT Username, Item.[Name] AS Item, [Description], Quantity FROM [User] u
LEFT JOIN HasItem ON HasItem.UserID = u.ID
JOIN Item ON Item.ID = HasItem.ItemID;