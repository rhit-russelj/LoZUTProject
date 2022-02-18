USE master;
go 
Create database [LoZUTrackerDemo] on primary
(Name = Lab_Database,
	Size = 6MB, 
	Filegrowth = 15%,
	Maxsize = 30MB,
	Filename = 'D:\Database\MSSQL15.MSSQLSERVER\MSSQL\DATA\LoZUTrackerDemo.mdf')

log on
(Name = Lab_Log,
	Size = 3MB,
	Filegrowth = 15%,
	Maxsize = 30MB,
	Filename = 'D:\Database\MSSQL15.MSSQLSERVER\MSSQL\DATA\LoZUTrackerDemo.ldf');
go

USE [LoZUTrackerDemo]
GO

/****** Object:  Table [dbo].[Boss]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Boss](
	[EnemyID] [int] NOT NULL,
	[GameID] [int] NULL,
	[DungeonID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[EnemyID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Completed]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Completed](
	[UserID] [int] NOT NULL,
	[DungeonID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[DungeonID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Completes]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Completes](
	[UserID] [int] NOT NULL,
	[QuestID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[QuestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Consumable]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Consumable](
	[ID] [int] NOT NULL,
	[Effect] [varchar](50) NULL,
	[Strength] [int] NULL,
	[Type] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Defeated]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Defeated](
	[UserID] [int] NOT NULL,
	[BossID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[BossID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Dungeon]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Dungeon](
	[ID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Enemy]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Enemy](
	[NPCID] [int] NOT NULL,
	[SpawnAreas] [varchar](200) NULL,
	[Attacks] [varchar](200) NULL,
	[SpawnRestrictions] [varchar](200) NULL,
	[Health] [int] NULL,
	[GameID] [int] NOT NULL,
 CONSTRAINT [PK_Enemy] PRIMARY KEY CLUSTERED 
(
	[NPCID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[EnemyDrops]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[EnemyDrops](
	[EnemyID] [int] NOT NULL,
	[ItemID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[EnemyID] ASC,
	[ItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[FoundIn]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[FoundIn](
	[ItemID] [int] NOT NULL,
	[LocationID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ItemID] ASC,
	[LocationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Game]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Game](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[PublishYear] [int] NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[TimelineEra] [varchar](20) NULL,
	[TimelineNumber] [int] NULL,
	[System] [varchar](50) NULL,
 CONSTRAINT [PK__Game__3214EC2728313B03] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_Game] UNIQUE NONCLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[HasItem]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[HasItem](
	[UserID] [int] NOT NULL,
	[ItemID] [int] NOT NULL,
	[Quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[ItemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[HasLocation]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[HasLocation](
	[LocationID] [int] NOT NULL,
	[GameID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LocationID] ASC,
	[GameID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[HasNPC]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[HasNPC](
	[NPCID] [int] NOT NULL,
	[GameID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[NPCID] ASC,
	[GameID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Item]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Item](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Description] [varchar](200) NULL,
	[GameID] [int] NOT NULL,
 CONSTRAINT [PK__Item__3214EC27142937B0] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Location]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Location](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[GameID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[NPC]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[NPC](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Description] [varchar](200) NULL,
	[GameID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Quest]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Quest](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Objective] [varchar](200) NULL,
	[Storyline] [varchar](50) NULL,
	[Repeatable] [bit] NULL,
	[ItemID] [int] NULL,
	[NextQuestID] [int] NULL,
	[PreviousQuestID] [int] NULL,
	[GameID] [int] NULL,
	[NPCID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[Rupee]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Rupee](
	[ID] [int] NOT NULL,
	[Color] [varchar](50) NULL,
	[Value] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[User]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[User](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](20) NULL,
	[Username] [varchar](20) NOT NULL,
	[PasswordHash] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[UserOwns]    Script Date: 2/17/2022 9:55:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[UserOwns](
	[UserID] [int] NOT NULL,
	[GameID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC,
	[GameID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Item] ADD  CONSTRAINT [DF_Item_GameID]  DEFAULT ((0)) FOR [GameID]
GO

ALTER TABLE [dbo].[Boss]  WITH CHECK ADD  CONSTRAINT [FK__Boss__GameID__5535A963] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Boss] CHECK CONSTRAINT [FK__Boss__GameID__5535A963]
GO

ALTER TABLE [dbo].[Completed]  WITH CHECK ADD FOREIGN KEY([DungeonID])
REFERENCES [dbo].[Dungeon] ([ID])
GO

ALTER TABLE [dbo].[Completed]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO

ALTER TABLE [dbo].[Completes]  WITH CHECK ADD FOREIGN KEY([QuestID])
REFERENCES [dbo].[Quest] ([ID])
GO

ALTER TABLE [dbo].[Completes]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO

ALTER TABLE [dbo].[Consumable]  WITH CHECK ADD  CONSTRAINT [FK__Consumable__ID__3C69FB99] FOREIGN KEY([ID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[Consumable] CHECK CONSTRAINT [FK__Consumable__ID__3C69FB99]
GO

ALTER TABLE [dbo].[Defeated]  WITH CHECK ADD FOREIGN KEY([BossID])
REFERENCES [dbo].[Boss] ([EnemyID])
GO

ALTER TABLE [dbo].[Defeated]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO

ALTER TABLE [dbo].[Dungeon]  WITH CHECK ADD FOREIGN KEY([ID])
REFERENCES [dbo].[Location] ([ID])
GO

ALTER TABLE [dbo].[Enemy]  WITH CHECK ADD  CONSTRAINT [FK__Enemy__GameID__5165187F] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Enemy] CHECK CONSTRAINT [FK__Enemy__GameID__5165187F]
GO

ALTER TABLE [dbo].[Enemy]  WITH CHECK ADD  CONSTRAINT [FK__Enemy__NPCID__5070F446] FOREIGN KEY([NPCID])
REFERENCES [dbo].[NPC] ([ID])
GO

ALTER TABLE [dbo].[Enemy] CHECK CONSTRAINT [FK__Enemy__NPCID__5070F446]
GO

ALTER TABLE [dbo].[EnemyDrops]  WITH CHECK ADD  CONSTRAINT [FK__EnemyDrop__ItemI__5CD6CB2B] FOREIGN KEY([ItemID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[EnemyDrops] CHECK CONSTRAINT [FK__EnemyDrop__ItemI__5CD6CB2B]
GO

ALTER TABLE [dbo].[FoundIn]  WITH CHECK ADD  CONSTRAINT [FK__FoundIn__ItemID__6383C8BA] FOREIGN KEY([ItemID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[FoundIn] CHECK CONSTRAINT [FK__FoundIn__ItemID__6383C8BA]
GO

ALTER TABLE [dbo].[FoundIn]  WITH CHECK ADD FOREIGN KEY([LocationID])
REFERENCES [dbo].[Location] ([ID])
GO

ALTER TABLE [dbo].[Game]  WITH CHECK ADD  CONSTRAINT [FK_Game_Game] FOREIGN KEY([ID])
REFERENCES [dbo].[Game] ([ID])
GO

ALTER TABLE [dbo].[Game] CHECK CONSTRAINT [FK_Game_Game]
GO

ALTER TABLE [dbo].[HasItem]  WITH CHECK ADD  CONSTRAINT [FK__HasItem__ItemID__778AC167] FOREIGN KEY([ItemID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[HasItem] CHECK CONSTRAINT [FK__HasItem__ItemID__778AC167]
GO

ALTER TABLE [dbo].[HasItem]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO

ALTER TABLE [dbo].[HasLocation]  WITH CHECK ADD  CONSTRAINT [FK__HasLocati__GameI__73BA3083] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[HasLocation] CHECK CONSTRAINT [FK__HasLocati__GameI__73BA3083]
GO

ALTER TABLE [dbo].[HasLocation]  WITH CHECK ADD FOREIGN KEY([LocationID])
REFERENCES [dbo].[Location] ([ID])
GO

ALTER TABLE [dbo].[HasNPC]  WITH CHECK ADD  CONSTRAINT [FK__HasNPC__GameID__6FE99F9F] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[HasNPC] CHECK CONSTRAINT [FK__HasNPC__GameID__6FE99F9F]
GO

ALTER TABLE [dbo].[HasNPC]  WITH CHECK ADD FOREIGN KEY([NPCID])
REFERENCES [dbo].[NPC] ([ID])
GO

ALTER TABLE [dbo].[Item]  WITH CHECK ADD  CONSTRAINT [FK_Game_Item] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Item] CHECK CONSTRAINT [FK_Game_Item]
GO

ALTER TABLE [dbo].[Location]  WITH CHECK ADD  CONSTRAINT [FK__Location__GameID__47DBAE45] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Location] CHECK CONSTRAINT [FK__Location__GameID__47DBAE45]
GO

ALTER TABLE [dbo].[NPC]  WITH CHECK ADD  CONSTRAINT [FK__NPC__GameID__4D94879B] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[NPC] CHECK CONSTRAINT [FK__NPC__GameID__4D94879B]
GO

ALTER TABLE [dbo].[Quest]  WITH CHECK ADD  CONSTRAINT [FK__Quest__GameID__00200768] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Quest] CHECK CONSTRAINT [FK__Quest__GameID__00200768]
GO

ALTER TABLE [dbo].[Quest]  WITH CHECK ADD  CONSTRAINT [FK__Quest__ItemID__7D439ABD] FOREIGN KEY([ItemID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[Quest] CHECK CONSTRAINT [FK__Quest__ItemID__7D439ABD]
GO

ALTER TABLE [dbo].[Quest]  WITH CHECK ADD FOREIGN KEY([NextQuestID])
REFERENCES [dbo].[Quest] ([ID])
GO

ALTER TABLE [dbo].[Quest]  WITH CHECK ADD FOREIGN KEY([NPCID])
REFERENCES [dbo].[NPC] ([ID])
GO

ALTER TABLE [dbo].[Quest]  WITH CHECK ADD FOREIGN KEY([PreviousQuestID])
REFERENCES [dbo].[Quest] ([ID])
GO

ALTER TABLE [dbo].[Rupee]  WITH CHECK ADD  CONSTRAINT [FK__Rupee__ID__44FF419A] FOREIGN KEY([ID])
REFERENCES [dbo].[Item] ([ID])
GO

ALTER TABLE [dbo].[Rupee] CHECK CONSTRAINT [FK__Rupee__ID__44FF419A]
GO

ALTER TABLE [dbo].[UserOwns]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[User] ([ID])
GO

ALTER TABLE [dbo].[UserOwns]  WITH CHECK ADD  CONSTRAINT [FK_UserOwns_Game] FOREIGN KEY([GameID])
REFERENCES [dbo].[Game] ([ID])
GO

ALTER TABLE [dbo].[UserOwns] CHECK CONSTRAINT [FK_UserOwns_Game]
GO

/****** Object:  View [dbo].[BossDetails]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[BossDetails] AS
SELECT NPC.[Name] AS BossName, [Description], SpawnAreas, Attacks, SpawnRestrictions, Health, l.[Name] AS DungeonLoc
FROM Boss b
JOIN Enemy e ON e.NPCID = b.EnemyID
JOIN NPC ON NPC.ID = e.NPCID
JOIN Dungeon d ON d.ID = b.DungeonID
JOIN [Location] l ON l.ID = d.ID
GO

/****** Object:  View [dbo].[GeneralGameInformation]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[GeneralGameInformation] AS
SELECT Name, System, PublishYear, TimelineEra, TimelineNumber
FROM  Game
GO

/****** Object:  View [dbo].[GeneralItemInformation]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

Create VIEW [dbo].[GeneralItemInformation] AS
SELECT i.Name as 'Item Name', i.Description, g.Name as 'Game Name'
FROM Item as i
JOIN Game as g on g.ID = i.GameID
GO

/****** Object:  View [dbo].[GeneralLocationInformation]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

Create VIEW [dbo].[GeneralLocationInformation] AS
SELECT l.Name as 'Location', g.Name as 'Game Name'
FROM Location as l
JOIN Game as g on g.ID = l.GameID
GO

/****** Object:  View [dbo].[GeneralNPCInformation]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

Create VIEW [dbo].[GeneralNPCInformation] AS
SELECT n.Name as 'NPC Name', n.Description as 'NPC Desc.', g.Name as 'Game Name'
FROM NPC as n
JOIN Game as g on g.ID = n.GameID
GO

/****** Object:  View [dbo].[GeneralQuestInformation]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[GeneralQuestInformation] AS
SELECT q.Name as 'Quest Name', q.Objective, q.Storyline, g.Name as 'Game Name'
FROM Quest as q
JOIN Game as g on g.ID = q.GameID
GO

/****** Object:  View [dbo].[QuestDetails]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[QuestDetails]
AS
SELECT q.[Name], q.Objective, q.Storyline, q.[Repeatable], i.[Name] AS Item, pq.[Name] AS PreviousQuest, nq.[Name] AS NextQuest
FROM Quest q
JOIN Quest pq ON q.PreviousQuestID = pq.ID
JOIN Quest nq ON q.NextQuestID = nq.ID
JOIN Item i ON q.ItemID = i.ID
GO

/****** Object:  View [dbo].[UserBosses]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[UserBosses]
AS
SELECT Username, NPC.[Name] AS Boss, NPC.[Description], Game.[Name] AS Game FROM [User] u
LEFT JOIN Defeated ON Defeated.UserID = u.ID
JOIN Boss ON Defeated.BossID = Boss.EnemyID
JOIN Enemy ON Enemy.NPCID = Boss.EnemyID
JOIN NPC ON Enemy.NPCID = NPC.ID
JOIN Game ON Game.ID = NPC.GameID;
GO

/****** Object:  View [dbo].[UserDungeons]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE VIEW [dbo].[UserDungeons]
AS
SELECT Username, [Location].[Name] As [Dungeon], Game.[Name] AS Game FROM [User] u
LEFT JOIN Completed ON Completed.UserID = u.ID
JOIN Dungeon ON Dungeon.ID = Completed.DungeonID
JOIN [Location] ON [Location].ID = Dungeon.ID
JOIN Game ON Game.ID = [Location].GameID;
GO

/****** Object:  View [dbo].[UserGames]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[UserGames]
AS
SELECT Username, Game.[Name], [System], [PublishYear]  FROM [User] u
LEFT JOIN UserOwns uo ON uo.UserID = u.ID
JOIN Game ON uo.GameID = Game.ID;
GO

/****** Object:  View [dbo].[UserItems]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[UserItems]
AS
SELECT u.Username, dbo.Item.Name AS Item, dbo.Item.Description, dbo.HasItem.Quantity, dbo.Game.Name AS Game
FROM     dbo.[User] AS u LEFT OUTER JOIN
                  dbo.HasItem ON dbo.HasItem.UserID = u.ID INNER JOIN
                  dbo.Item ON dbo.Item.ID = dbo.HasItem.ItemID INNER JOIN
                  dbo.Game ON dbo.Item.GameID = dbo.Game.ID
GO

/****** Object:  View [dbo].[UserQuests]    Script Date: 2/17/2022 9:56:45 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


--CREATE VIEW UserOwns
--AS
--SELECT Username, Game.[Name], PublishYear, [System] FROM [User] u
--LEFT JOIN UserOwns ON UserOwns.UserID = u.ID
--JOIN Game ON Game.ID = UserOwns.GameID;
--GO

--CREATE VIEW UserDungeons
--AS
--SELECT Username, [Location].[Name], Game.[Name] AS Game FROM [User] u
--LEFT JOIN Completed ON Completed.UserID = u.ID
--JOIN Dungeon ON Dungeon.ID = Completed.DungeonID
--JOIN [Location] ON [Location].ID = Dungeon.ID
--JOIN Game ON Game.ID = [Location].GameID;
--GO

CREATE VIEW [dbo].[UserQuests]
AS
SELECT Username, Quest.[Name] AS Quest, Quest.Objective, Game.[Name] AS Game FROM [User] u
LEFT JOIN Completes ON Completes.UserID = u.ID
JOIN Quest ON Quest.ID = Completes.QuestID
JOIN Game ON Game.ID = Quest.GameID;
GO

EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPane1', @value=N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "u"
            Begin Extent = 
               Top = 7
               Left = 48
               Bottom = 170
               Right = 242
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "HasItem"
            Begin Extent = 
               Top = 175
               Left = 48
               Bottom = 316
               Right = 242
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Item"
            Begin Extent = 
               Top = 322
               Left = 48
               Bottom = 485
               Right = 242
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Game"
            Begin Extent = 
               Top = 7
               Left = 290
               Bottom = 170
               Right = 496
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 9
         Width = 284
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
         Width = 1200
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1176
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1356
         SortOrder = 1416
         GroupBy = 1356
         Filter = 1356
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'UserItems'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_DiagramPaneCount', @value=1 , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'VIEW',@level1name=N'UserItems'
GO

/****** Object:  StoredProcedure [dbo].[AddBoss]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddBoss](
	@EnemyID_1 int,
	@DungeonID int,
	@GameID_2 int
)
AS

IF(@EnemyID_1 IS NULL OR @GameID_2 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @EnemyID_1) = 0
BEGIN
	PRINT 'Boss does not already exist as an enemy'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID_2) = 0
BEGIN
	PRINT 'Game does not exist'
	RETURN 3
END

INSERT INTO Boss(EnemyID, GameID, DungeonID)
VALUES(@EnemyID_1, @GameID_2, @DungeonID)
PRINT 'Boss added successfully!'
RETURN 0

GO

/****** Object:  StoredProcedure [dbo].[AddConsumable]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddConsumable](
	@ConsumableID int,
	@Effect varchar(50),
	@Strength int,
	@Type varchar(50)
)
AS
IF(@ConsumableID IS NULL OR @Effect IS NULL OR @Strength IS NULL OR @Type IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Item WHERE Item.ID = @ConsumableID) = 0
BEGIN
	PRINT 'Consumable does not exist as an item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Consumable WHERE ID = @ConsumableID) > 0
BEGIN
	PRINT 'This consumable already exists'
	RETURN 2
END

IF(@Strength < 1)
BEGIN
	PRINT 'Strength must be a positive value'
	RETURN 3
END

INSERT INTO Consumable(ID, Effect, Strength, [Type])
VALUES(@ConsumableID, @Effect, @Strength, @Type)

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[AddDefeated]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[AddDefeated]
	@UserID int,
	@BossID int
AS

DECLARE @boss AS int
SET @boss = (SELECT BossID From Defeated Where BossID = @BossID and UserID = @UserID)


IF(@UserID IS NULL or @BossID is NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@boss = @BossID)
BEGIN
	RAISERROR('User already defeated that boss before!', 1, 1)
END
ELSE
BEGIN
	INSERT INTO dbo.Defeated
	VALUES (@UserID, @BossID)
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[AddDungeon]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddDungeon](
	@LocationID_1 int
)
AS

IF(@LocationID_1 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM [Location] WHERE [Location].ID = @LocationID_1) = 0
BEGIN
	PRINT 'Dungeon does not currently exist as a location'
	RETURN 2
END

INSERT INTO Dungeon(ID)
VALUES(@LocationID_1)
PRINT 'Dungeon added successfully!'
RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[AddEnemy]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddEnemy](
	@NPCID_1 int,
	@SpawnAreas_2 varchar(200),
	@Attacks_3 varchar(200),
	@SpawnRestrictions_4 varchar(200),
	@Health_5 int,
	@GameID_6 int
)
AS

IF(@NPCID_1 IS NULL OR @GameID_6 IS NULL)
BEGIN
	PRINT 'All fields must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM NPC WHERE ID = @NPCID_1) = 0
BEGIN
	PRINT 'This enemy does not exist already as an NPC'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID_6) = 0
BEGIN
	PRINT 'This game does not exist'
	RETURN 3
END

IF(@Health_5 < 1)
BEGIN
	PRINT 'Health value must be greater than zero'
	RETURN 4
END

INSERT INTO Enemy(NPCID, SpawnAreas, Attacks, SpawnRestrictions, Health, GameID)
VALUES (@NPCID_1, @SpawnAreas_2, @Attacks_3, @SpawnRestrictions_4, @Health_5, @GameID_6)
PRINT 'Enemy added successfully!'
RETURN 0

GO



/****** Object:  StoredProcedure [dbo].[AddGame]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddGame]
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

/****** Object:  StoredProcedure [dbo].[AddGameAndNPCs]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddGameAndNPCs]
(
	@GameName varchar(50),
	@PublishYear int,
	@TimelineEra varchar(20),
	@TimelineNumber int,
	@System varchar(50),
	@NPCName varchar(50),
	@NPCDescription varchar(200)
)
AS
BEGIN
	if(@GameName is null)
	BEGIN
			RAISERROR('Game name cannot be null.', 1, 1)
			RETURN 1
	END

	if(@PublishYear is null)
	BEGIN
			RAISERROR('Publish year cannot be null.', 1, 1)
			RETURN 1
	END

	if(@TimelineEra is null)
	BEGIN
			RAISERROR('Timeline era cannot be null.', 1, 1)
			RETURN 1
	END

	if(@TimelineNumber is null)
	BEGIN
			RAISERROR('Timeline Number cannot be null.', 1, 1)
			RETURN 1
	END

	if(@System is null)
	BEGIN
			RAISERROR('System cannot be null.', 1, 1)
			RETURN 1
	END

	if(@NPCName is null)
	BEGIN
			RAISERROR('NPC name cannot be null.', 1, 1)
			RETURN 1
	END

	if(@NPCDescription is null)
	BEGIN
			RAISERROR('NPC Description cannot be null.', 1, 1)
			RETURN 1
	END

	INSERT INTO [Game](PublishYear,[Name],TimelineEra,TimelineNumber,[System]) 
	VALUES (@PublishYear, @GameName, @TimelineEra, @TimelineNumber, @System);
	RAISERROR(N'Added game successfully!',0,1);
	DECLARE @gID int
	SET @gID = (SELECT ID FROM [Game] WHERE Game.Name = @GameName)

	INSERT INTO [NPC]([Name],[Description], [GameID]) VALUES (@NPCName, @NPCDescription, @gID)
	RAISERROR(N'Added NPC successfully!',0,1);
	RETURN 0;

END
GO

/****** Object:  StoredProcedure [dbo].[AddItem]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddItem]
	@Name1 varchar(50),
	@Description2 varchar(200),
	@GameID3 int
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID3) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

INSERT INTO Item([Name],[Description], [GameID]) VALUES (@Name1, @Description2, @GameID3)
RAISERROR(N'Item added successfully',0,1);
RETURN 0;
GO



/****** Object:  StoredProcedure [dbo].[AddLocation]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddLocation]
	@Name1 varchar(50),
	@GameID2 int
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID2) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

IF(EXISTS (SELECT l.[Name]
	FROM [Location] l
	WHERE l.GameID = @GameID2 AND l.[Name] = @Name1))
BEGIN
	RAISERROR(N'Location with that name already exists in that game!',1,1);
	RETURN 1;
END


INSERT INTO [Location]([Name],GameID) VALUES (@Name1, @GameID2)
RAISERROR(N'Added location successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[AddNPC]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddNPC]
	@Name1 varchar(50),
	@Description2 varchar(200),
	@GameID3 int
AS

IF (@Name1 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END



IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID3) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

IF(EXISTS (SELECT l.[Name]
	FROM [NPC] l
	WHERE l.GameID = @GameID3 AND l.[Name] = @Name1))
BEGIN
	RAISERROR(N'NPC with that name already exists in that game!',1,1);
	RETURN 1;
END


INSERT INTO [NPC]([Name],[Description],GameID) VALUES (@Name1, @Description2, @GameID3)
RAISERROR(N'Added NPC successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[AddQuest]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddQuest]
	@Name1 varchar(50),
	@Objective2 varchar(200),
	@Storyline3 varchar(50),
	@Repeatable4 bit = 0,
	@ItemID5 int = NULL,
	@NextQuestID6 int = NULL,
	@PreviousQuestID7 int = NULL,
	@GameID8 int,
	@NPCID9 int = NULL
AS
	IF(@Name1 IS NULL)
	BEGIN
		RAISERROR(N'Name cannot be null!',1,1);
		RETURN 1;
	END

	IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID8) IS NULL)
	BEGIN
		RAISERROR(N'Game does not exist!',1,1);
		RETURN 1;
	END

	IF((SELECT q.[Name]
		FROM [Quest] q
		WHERE q.GameID = @GameID8 AND q.[Name] = @Name1) IS NOT NULL)
	BEGIN
		RAISERROR(N'Quest with that name already exists in that game!',1,1);
		RETURN 1;
	END

	--Next few branches make sure that a foreign key exists if and only if it is given
	IF(@ItemID5 IS NOT NULL)
	BEGIN
		IF((SELECT i.ID
		FROM [Item] i
		WHERE i.ID = @ItemID5) IS NULL)
		BEGIN
			RAISERROR(N'Item does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@NextQuestID6 IS NOT NULL)
	BEGIN
		IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @NextQuestID6) IS NULL)
		BEGIN
			RAISERROR(N'Next quest does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@PreviousQuestID7 IS NOT NULL)
	BEGIN
		IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @PreviousQuestID7) IS NULL)
		BEGIN
			RAISERROR(N'Previous quest does not exist!',1,1);
			RETURN 1;
		END
	END

	IF(@NPCID9 IS NOT NULL)
	BEGIN
		IF((SELECT n.ID
		FROM [NPC] n
		WHERE n.ID = @NPCID9) IS NULL)
		BEGIN
			RAISERROR(N'NPC does not exist!',1,1);
			RETURN 1;
		END
	END


	INSERT INTO [Quest]([Name],[Objective],[Storyline],[Repeatable],[ItemID],[NextQuestID],[PreviousQuestID],[GameID],[NPCID])
		VALUES (@Name1,@Objective2,@Storyline3,@Repeatable4,@ItemID5,@NextQuestID6,@PreviousQuestID7,@GameID8,@NPCID9);
	RAISERROR(N'Quest successfully added!',0,1);
	RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[AddQuestItem]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

/****** Object:  StoredProcedure [dbo].[AddRupee]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddRupee](
	@RupeeID int,
	@Color varchar(50),
	@Value int
)
AS

IF(SELECT COUNT(*) FROM Item WHERE Item.ID = @RupeeID) = 0
BEGIN
	PRINT 'Rupee does not exist as an item'
	RETURN 2
END

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
GO

/****** Object:  StoredProcedure [dbo].[AddToCompleted]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddToCompleted]
	@UserID1 int,
	@DungeonID2 int
AS

IF ((SELECT u.ID
	FROM [User] u
	WHERE u.ID = @UserID1) IS NULL)
BEGIN
	RAISERROR(N'Username does not exist!',1,1);
	RETURN 1;
END

--Important because it makes sure that the dungeon is, in fact, a dungeon
IF ((SELECT d.ID
	FROM [Dungeon] d
	WHERE d.ID = @DungeonID2) IS NULL)
BEGIN
	RAISERROR(N'Dungeon does not exist!',1,1);
	RETURN 1;
END

INSERT INTO [Completed](UserID, DungeonID) VALUES (@UserID1, @DungeonID2)
RAISERROR(N'Added to completed list of dungeons!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[AddToCompletes]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[AddToCompletes](
	@UserID_1 int,
	@QuestID_2 int
)
AS

IF(SELECT COUNT(*) FROM [User] WHERE ID = @UserID_1) = 0
BEGIN
	PRINT 'User does not exist'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Quest WHERE ID = @QuestID_2) = 0
BEGIN
	PRINT 'Quest does not exist'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Completes WHERE UserID = @UserID_1 AND QuestID = @QuestID_2) > 0
BEGIN
	PRINT 'User has already completed this quest'
	RETURN 3
END

INSERT INTO Completes(UserID, QuestID)
VALUES(@UserID_1, @QuestID_2)
PRINT 'User has successfully completed the quest!'
RETURN 0

GO

/****** Object:  StoredProcedure [dbo].[AddUser]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddUser]
	@Username varchar(20),
	@Name varchar(20),
	@HashedPassword varchar(50),
	@FirstGameName varchar(50),
	@SignInUser varchar(20) OUTPUT
AS
--Check parameters
IF (@Username IS NULL OR @HashedPassword IS NULL)
BEGIN
	RAISERROR(N'Username/password cannot be empty',1,1);
	RETURN 1;
END
--Check existence of account
ELSE IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @Username) IS NOT NULL)
BEGIN
	RAISERROR(N'Username is not unique',1,1);
	RETURN 1;
END
ELSE
BEGIN
	DECLARE @FirstGameID int
	--Get Game ID for owns relationship
	SELECT @FirstGameID = (SELECT [Game].ID
						   FROM Game
						   WHERE Lower([Game].[Name]) = @FirstGameName)
	--Insert into user so that we can access UserID
	INSERT INTO [User](Username, [Name], PasswordHash)
	VALUES (@Username, @Name, @HashedPassword);
	SET @SignInUser = @Username
	--Get user ID for owns relationship
	DECLARE @UserID int
	SELECT @UserID = SCOPE_IDENTITY()
	INSERT INTO [UserOwns](UserID, GameID)
	VALUES (@UserID, @FirstGameID)
	--Return Results
	RAISERROR(N'User created successfully!', 0, 1);
	RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[AddUserItems]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddUserItems]
    @ItemID int,
    @UserID int,
	@Quantity int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE IF(@Quantity < 1 OR @Quantity IS NULL)
BEGIN
    RAISERROR('Quantity of an item cannot be less than 1', 0, 1);
    RETURN 0;
END
ELSE
BEGIN
    INSERT INTO HasItem(UserID, ItemID, Quantity)
    VALUES(@UserID, @ItemID, @Quantity);
    RAISERROR('User items added successfully', 0, 1);
    RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[AddUserOwns]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[AddUserOwns]
	@GameID int,
	@UserID int
AS
IF(@GameID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1);
	RETURN 1;
END
ELSE
BEGIN
	INSERT INTO UserOwns(UserID, GameID)
	VALUES(@UserID, @GameID);
	RAISERROR('User owned game added successfully', 0, 1);
	RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[DeleteBoss]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteBoss](
	@EnemyID int
)
AS
IF(@EnemyID IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Boss WHERE EnemyID = @EnemyID) = 0
BEGIN
	PRINT 'This boss does not exist in the given game'
	RETURN 2
END

DELETE FROM Boss
WHERE EnemyID = @EnemyID

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[DeleteConsumable]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteConsumable](
	@ConsumableID int
)
AS

IF(@ConsumableID IS NULL)
BEGIN
	PRINT 'Consumable ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Consumable WHERE ID = @ConsumableID) = 0
BEGIN
	PRINT 'This Consumable does not exist'
	RETURN 2
END

DELETE FROM Consumable
WHERE ID = @ConsumableID

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[DeleteDefeated]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[DeleteDefeated]
	@UserID int,
	@BossID int
AS

IF(@UserID IS NULL OR @BossID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE
BEGIN
    DELETE FROM Defeated
	WHERE Defeated.BossID = @BossID AND Defeated.UserID = @UserID
    RAISERROR('Defeated was removed successfully', 0, 1);
    RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[DeleteDungeon]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteDungeon]
(
	@DungeonID int
)
AS
IF(@DungeonID IS NULL)
BEGIN
	PRINT 'DungeonID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Dungeon WHERE ID = @DungeonID) = 0
BEGIN
	PRINT 'The given dungeon does not exist'
	RETURN 2
END

DELETE FROM Dungeon
WHERE ID = @DungeonID

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[DeleteEnemy]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteEnemy](
	@NPCID int
)
AS

IF(@NPCID IS NULL)
BEGIN
	PRINT 'All parameters must be filled'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @NPCID) = 0
BEGIN
	PRINT 'This enemy does not exist for this given game'
	RETURN 2
END

DELETE FROM Enemy
WHERE NPCID = @NPCID

RETURN 0
GO



/****** Object:  StoredProcedure [dbo].[DeleteGame]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteGame]
	@GameID1 int
AS

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @GameID1) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Game]
	WHERE ID = @GameID1;
RAISERROR(N'Game successfully deleted!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[DeleteItem]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteItem]
	@ItemID1 int
AS

IF((SELECT i.ID
	FROM [Item] i
	WHERE i.ID = @ItemID1) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Item]
	WHERE ID = @ItemID1;
RAISERROR(N'Item successfully deleted!',0,1);
RETURN 0;

GO

/****** Object:  StoredProcedure [dbo].[DeleteLocation]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteLocation]
	@LocationID1 int
AS

IF((SELECT l.ID
	FROM [Location] l
	WHERE l.ID = @LocationID1) IS NULL)
BEGIN
	RAISERROR(N'Location does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [Location]
	WHERE ID = @LocationID1;
RAISERROR(N'Location successfully deleted!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[DeleteNPC]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteNPC]
	@NPCID1 int
AS

IF((SELECT n.ID
	FROM [NPC] n
	WHERE n.ID = @NPCID1) IS NULL)
BEGIN
	RAISERROR(N'NPC does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [NPC]
	WHERE ID = @NPCID1;
RAISERROR(N'NPC successfully deleted!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[DeleteQuest]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteQuest]
	@QuestID1 int
AS

	IF((SELECT q.ID
		FROM [Quest] q
		WHERE q.ID = @QuestID1) IS NULL)
	BEGIN
		RAISERROR(N'Quest does not exist!',1,1);
		RETURN 1;
	END

	DELETE FROM [Quest]
		WHERE ID = @QuestID1;
	RAISERROR(N'Quest successfully deleted!',0,1);
	RETURN 0;
GO



/****** Object:  StoredProcedure [dbo].[DeleteRupee]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[DeleteRupee] 
	@RupeeID int
AS

IF (@RupeeID IS NULL)
BEGIN
	RAISERROR(N'Item ID cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT i.ID
	FROM [Rupee] i
	WHERE i.ID = @RupeeID) IS NULL)
BEGIN
	RAISERROR(N'Rupee does not exist to be deleted!',1,1);
	RETURN 1;
END


DELETE FROM Rupee WHERE ID = @RupeeID
RAISERROR(N'Deleted Rupee successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[DeleteUser]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[DeleteUser]
	@Username1 varchar(20) = NULL,
	@UserID2 int
AS

IF (@UserID2 IS NULL)
BEGIN
	RAISERROR(N'Given user is null!',1,1);
	RETURN 1;
END

IF (@Username1 IS NULL)
BEGIN
	SET @Username1 = (SELECT Username
					 FROM [User] u
					 WHERE u.ID = @UserID2);
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @Username1) IS NULL)
BEGIN
	RAISERROR(N'User does not exist!',1,1);
	RETURN 1;
END

DELETE FROM [User]
	WHERE Username = @Username1;
RAISERROR(N'User deleted successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[DeleteUserItems]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[DeleteUserItems]
    @ItemID int,
    @UserID int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
    RAISERROR('Foreign keys cannot be null', 1, 1);
    RETURN 1;
END
ELSE
BEGIN
    DELETE FROM HasItem
	WHERE HasItem.ItemID = @ItemID AND HasItem.UserID = @UserID
    RAISERROR('User items removed successfully', 0, 1);
    RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[DeleteUserOwns]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[DeleteUserOwns]
	@GameID int,
	@UserID int
AS
IF(@GameID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Arguments cannot be null', 1, 1);
	RETURN 1;
END
ELSE IF(EXISTS (SELECT *
		FROM UserOwns
		WHERE UserOwns.GameID = @GameID AND UserOwns.UserID = @UserID))
BEGIN
	DELETE FROM UserOwns
	WHERE UserOwns.GameID = @GameID AND UserOwns.UserID = @UserID
	RAISERROR('User owned game removed successfully', 0, 1);
	RETURN 0;
END
ELSE
BEGIN
	RAISERROR('Data does not exist in the table', 1, 1);
	RETURN 1;
END
GO

/****** Object:  StoredProcedure [dbo].[GetBossDetails]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[GetBossDetails]
AS
SELECT * FROM BossDetails
GO

/****** Object:  StoredProcedure [dbo].[GetQuestDetails]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[GetQuestDetails]
AS
SELECT * FROM QuestDetails
GO

/****** Object:  StoredProcedure [dbo].[RemoveFromCompleted]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[RemoveFromCompleted]
	@UserID1 int,
	@DungeonID2 int
AS

IF ((SELECT c.UserID
	FROM [Completed] c
	WHERE c.UserID = @UserID1 AND c.DungeonID = @DungeonID2) IS NULL)
BEGIN
	RAISERROR(N'User has not completed this dungeon!',1,1);
	RETURN 1;
END

DELETE FROM [Completed]
	WHERE UserID = @UserID1 AND DungeonID = @DungeonID2;
RAISERROR(N'Removed from list of completed dungeons!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[RemoveFromCompletes]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[RemoveFromCompletes](
	@UserID_1 int,
	@QuestID_2 int
)
AS

IF(SELECT COUNT(*) FROM [User] WHERE ID = @UserID_1) = 0
BEGIN
	PRINT 'User does not exist'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Quest WHERE ID = @QuestID_2) = 0
BEGIN
	PRINT 'Quest does not exist'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Completes WHERE UserID = @UserID_1 AND QuestID = @QuestID_2) = 0
BEGIN
	PRINT 'User has not completed this quest'
	RETURN 3
END

DELETE FROM Completes
WHERE UserID = @UserID_1 AND QuestID = @QuestID_2;
PRINT 'Removed from list of completed quests'
RETURN 0

GO

/****** Object:  StoredProcedure [dbo].[SearchGameItems]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SearchGameItems]
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
GO

/****** Object:  StoredProcedure [dbo].[UpdateConsumable]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateConsumable](
	@ConsumableID int,
	@Effect varchar(50),
	@Strength int,
	@Type varchar(50)
)
AS
IF(@ConsumableID IS NULL)
BEGIN
	PRINT 'Consumable ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Consumable WHERE ID = @ConsumableID) = 0
BEGIN
	PRINT 'This Consumable does not exist'
	RETURN 2
END

IF(@Strength < 1)
BEGIN
	PRINT 'Strength value must be positive'
	RETURN 3
END

UPDATE Consumable
SET Effect = @Effect,
	Strength = @Strength,
	[Type] = @Type
WHERE ID = @ConsumableID

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[UpdateDefeated]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE Procedure [dbo].[UpdateDefeated]
	@UserID int,
	@BossID int,
	@NewBossID int
AS

DECLARE @boss AS int
SET @boss = (SELECT BossID From Defeated Where UserID = @UserID AND BossID = @BossID)


IF(@UserID IS NULL or @BossID is NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@boss = @NewBossID)
BEGIN
	RAISERROR('Nothing is updated', 1, 1)
END
ELSE
BEGIN
	UPDATE dbo.Defeated
	SET BossID = @NewBossID
	WHERE Defeated.UserID = @UserID AND Defeated.BossID = @BossID
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END
GO

/****** Object:  StoredProcedure [dbo].[UpdateEnemy]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateEnemy](
	@NPCID int,
	@SpawnAreas varchar(200),
	@SpawnRestrictions varchar(200),
	@Health int,
	@GameID int
)
AS
IF(@NPCID IS NULL)
BEGIN
	PRINT 'NPCID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Enemy WHERE NPCID = @NPCID) = 0
BEGIN
	PRINT 'This enemy does not exist in the table'
	RETURN 2
END

IF(@Health < 1)
BEGIN
	PRINT 'Health must be a positive value'
	RETURN 3
END

IF(SELECT COUNT(*) FROM Game WHERE ID = @GameID) = 0
BEGIN
	PRINT 'This GameID does not match any IDs for the available games'
	RETURN 4
END

UPDATE Enemy
SET SpawnAreas = @SpawnAreas,
	SpawnRestrictions = @SpawnRestrictions,
	Health = @Health
WHERE NPCID = @NPCID;

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[UpdateGame]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateGame]
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

/****** Object:  StoredProcedure [dbo].[UpdateItem]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateItem]
	@ItemID1 int,
	@NewName2 varchar(50),
	@NewDescription3 varchar(200),
	@NewGame4 int
AS

IF((SELECT i.ID
	FROM [Item] i
	WHERE i.ID = @ItemID1) IS NULL)
BEGIN
	RAISERROR(N'Item does not exist!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @NewGame4) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

UPDATE [Item]
SET [Name]=@NewName2, [Description]=@NewDescription3
WHERE ID = @ItemID1
RAISERROR(N'Updated item successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[UpdateLocation]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateLocation]
	@LocationID1 int,
	@NewName2 varchar(50),
	@NewGameID3 int
AS

IF((SELECT l.ID
	FROM [Location] l
	WHERE l.ID = @LocationID1) IS NULL)
BEGIN
	RAISERROR(N'Location does not exist!',1,1);
	RETURN 1;
END

IF(EXISTS (SELECT l.[Name]
	FROM [Location] l
	WHERE l.GameID = @NewGameID3 AND l.[Name] = @NewName2))
BEGIN
	RAISERROR(N'Location with that name already exists in that game!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @NewGameID3) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

UPDATE [Location]
SET [Name]=@NewName2, GameID=@NewGameID3
WHERE ID = @LocationID1
RAISERROR(N'Updated location successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[UpdateNPC]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateNPC]
	@NPCID1 int,
	@NewName2 varchar(20),
	@NewDescription3 varchar(200),
	@NewGameID4 int
AS

IF((SELECT n.ID
	FROM [NPC] n
	WHERE n.ID = @NPCID1) IS NULL)
BEGIN
	RAISERROR(N'NPC does not exist!',1,1);
	RETURN 1;
END

IF (@NewName2 IS NULL)
BEGIN
	RAISERROR(N'Name cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT g.ID
	FROM [Game] g
	WHERE g.ID = @NewGameID4) IS NULL)
BEGIN
	RAISERROR(N'Game does not exist!',1,1);
	RETURN 1;
END

IF(EXISTS (SELECT l.[Name]
	FROM [NPC] l
	WHERE l.GameID = @NewGameID4 AND l.[Name] = @NewName2))
BEGIN
	RAISERROR(N'NPC with that name already exists in that game!',1,1);
	RETURN 1;
END

UPDATE [NPC]
SET [Name]=@NewName2, [Description]=@NewDescription3, GameID=@NewGameID4
WHERE ID = @NPCID1
RAISERROR(N'Updated NPC successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[UpdateQuest]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[UpdateQuest]
	@QuestID1 int,
	@Name2 varchar(50),
	@Objective3 varchar(200),
	@Storyline4 varchar(50),
	@Repeatable5 bit = 0,
	@ItemID6 int = NULL,
	@NextQuestID7 int = NULL,
	@PreviousQuestID8 int = NULL,
	@GameID9 int,
	@NPCID10 int
AS
	IF(@Name2 IS NULL)
	BEGIN
		RAISERROR(N'Name cannot be null!',1,1);
		RETURN 1;
	END

	
	IF((SELECT q.[Name]
		FROM [Quest] q
		WHERE q.GameID = @GameID9 AND q.ID <> @QuestID1 AND q.[Name] = @Name2) IS NOT NULL)
	BEGIN
		RAISERROR(N'Quest with that name already exists in that game!',1,1);
		RETURN 1;
	END

	UPDATE [Quest]
	SET [Name]=@Name2,[Objective]=@Objective3,[Storyline]=@Storyline4,[Repeatable]=@Repeatable5,[ItemID]=@ItemID6,
		[NextQuestID]=@NextQuestID7,[PreviousQuestID]=@PreviousQuestID8,[GameID]=@GameID9,[NPCID]=@NPCID10
	WHERE ID=@QuestID1;
	RAISERROR(N'Quest successfully updated!',0,1);
	RETURN 0;

GO


/****** Object:  StoredProcedure [dbo].[UpdateRupee]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateRupee](
	@RupeeID int,
	--@Color varchar(50),
	@Value int
)
AS

IF(@RupeeID IS NULL)
BEGIN
	PRINT 'Rupee ID cannot be null'
	RETURN 1
END

IF(SELECT COUNT(*) FROM Item WHERE ID = @RupeeID) = 0
BEGIN
	PRINT 'This Rupee does not already exist as an Item'
	RETURN 2
END

IF(SELECT COUNT(*) FROM Rupee WHERE ID = @RupeeID) = 0
BEGIN
	PRINT 'This Rupee does not exist'
	RETURN 3
END

UPDATE Rupee
--SET Color = @Color,
SET	[Value] = @Value
WHERE ID = @RupeeID

RETURN 0
GO

/****** Object:  StoredProcedure [dbo].[UpdateUser]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


CREATE PROCEDURE [dbo].[UpdateUser]
	@UserID1 int,
	@NewUsername2 varchar(20),
	@NewName3 varchar(20)
AS

IF (@NewUsername2 IS NULL)
BEGIN
	RAISERROR(N'Username cannot be null!',1,1);
	RETURN 1;
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.Username = @NewUsername2) IS NOT NULL)
BEGIN
	RAISERROR(N'Username already exists!',1,1);
	RETURN 1;
END

IF((SELECT u.ID
	FROM [User] u
	WHERE u.ID = @UserID1) IS NULL)
BEGIN
	RAISERROR(N'User does not exist!',1,1);
	RETURN 1;
END

UPDATE [User]
SET Username=@NewUsername2, [Name]=@NewName3
WHERE ID = @UserID1
RAISERROR(N'Updated user successfully!',0,1);
RETURN 0;
GO

/****** Object:  StoredProcedure [dbo].[UpdateUserItems]    Script Date: 2/17/2022 9:59:06 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[UpdateUserItems]
	@ItemID int,
	@UserID int,
	@Quantity int
AS
IF(@ItemID IS NULL OR @UserID IS NULL)
BEGIN
	RAISERROR('Foreign keys cannot be null', 1, 1)
	RETURN 1;
END
ELSE IF(@Quantity < 1 OR @Quantity IS NULL)
BEGIN
	RAISERROR('Quantity of an item cannot be less than 1', 1, 1)
END
ELSE
BEGIN
	UPDATE HasItem
	SET HasItem.Quantity = @Quantity
	WHERE HasItem.UserID = @UserID AND HasItem.ItemID = @ItemID
	RAISERROR('Item quantity has been updated!', 0, 1)
	RETURN 0;
END
GO

/****** Object:  UserDefinedFunction [dbo].[FormatGameItems]    Script Date: 2/17/2022 10:33:49 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE FUNCTION [dbo].[FormatGameItems]
( 
	@description varchar(200),
	@itemName varchar(50)
)
RETURNS varchar(300)
AS 
BEGIN
	RETURN @itemName + ' - ' + @description
END
GO


/****** Object:  User [dbo]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [dbo] FOR LOGIN [russelj] WITH DEFAULT_SCHEMA=[dbo]
GO

/****** Object:  User [garvinac]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [garvinac] FOR LOGIN [garvinac] WITH DEFAULT_SCHEMA=[dbo]
GO

/****** Object:  User [guest]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [guest] WITH DEFAULT_SCHEMA=[guest]
GO

/****** Object:  User [henderae]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [henderae] FOR LOGIN [henderae] WITH DEFAULT_SCHEMA=[dbo]
GO

/****** Object:  User [INFORMATION_SCHEMA]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [INFORMATION_SCHEMA]
GO

/****** Object:  User [ritenojb]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [ritenojb] FOR LOGIN [ritenojb] WITH DEFAULT_SCHEMA=[dbo]
GO

/****** Object:  User [sys]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [sys]
GO

/****** Object:  User [User]    Script Date: 2/17/2022 10:00:21 PM ******/
CREATE USER [User] FOR LOGIN [LegendZ] WITH DEFAULT_SCHEMA=[dbo]
GO