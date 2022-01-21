---------------------------------------------------------
--                                                     --
-- Creates all the tables for the LOZUTracker database --
--                                                     --
---------------------------------------------------------

--Square tables begin here (e.g. User)

USE LoZUTracker
GO
CREATE TABLE [User](
	ID int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(50),
	Username varchar(50) NOT NULL
)
GO
CREATE TABLE [Game](
	ID int IDENTITY(1,1) PRIMARY KEY,
	PublishYear int,
	Name varchar(50) NOT NULL,
	TimelineEra varchar(20),
	TimelineNumber int,
	System varchar(50)
)
GO
CREATE TABLE [NPC](
	ID int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(50),
	Description varchar(200),
	GameID int FOREIGN KEY REFERENCES Game(ID)
)
GO
CREATE TABLE [Quest](
	ID int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(50) NOT NULL,
	Objective varchar(200),
	Storyline varchar(50),
	Repeatable bit,
	ItemID int FOREIGN KEY REFERENCES Item(ID),
	NextQuestID int FOREIGN KEY REFERENCES Quest(ID),
	PreviousQuestID int FOREIGN KEY REFERENCES Quest(ID),
	GameID int FOREIGN KEY REFERENCES Game(ID),
	NPCID int FOREIGN KEY REFERENCES NPC(ID)
)
GO
CREATE TABLE [Item](
	ID int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(50) NOT NULL,
	Description varchar(200)
)
GO
CREATE TABLE [Consumable](
	ID int FOREIGN KEY REFERENCES Item(ID) PRIMARY KEY,
	Effect varchar(50),
	Strength int,
	Type varchar(50)
)
GO
CREATE TABLE [QuestItems](
	ID int FOREIGN KEY REFERENCES Item(ID) PRIMARY KEY,
	Purpose varchar(200)
)
GO
CREATE TABLE [Equipment](
	ID int FOREIGN KEY REFERENCES Item(ID) PRIMARY KEY,
	Type varchar(50)
)
GO
CREATE TABLE [Rupee](
	ID int FOREIGN KEY REFERENCES Item(ID) PRIMARY KEY,
	Color varchar(50),
	Value int
)
GO
CREATE TABLE [Location](
	ID int IDENTITY(1,1) PRIMARY KEY,
	Name varchar(50),
	GameID int FOREIGN KEY REFERENCES Game(ID)
)
GO
CREATE TABLE [Dungeon](
	ID int FOREIGN KEY REFERENCES Location(ID) PRIMARY KEY
)
GO
CREATE TABLE [Enemy](
	NPCID int FOREIGN KEY REFERENCES NPC(ID) PRIMARY KEY,
	SpawnAreas varchar(200),
	Attacks varchar(200),
	SpawnRestrictions varchar(200),
	Health int,
	GameID int FOREIGN KEY REFERENCES Game(ID)
)
GO
CREATE TABLE [Boss](
	EnemyID int FOREIGN KEY REFERENCES Enemy(NPCID) PRIMARY KEY,
	GameID int FOREIGN KEY REFERENCES Game(ID)
)
GO

--Diamond tables begin here (e.g. UserOwns)

CREATE TABLE [UserOwns](
	UserID int FOREIGN KEY REFERENCES [User](ID),
	GameID int FOREIGN KEY REFERENCES Game(ID),
	PRIMARY KEY(UserID, GameID)
)
GO
CREATE TABLE [EnemyDrops](
	EnemyID int FOREIGN KEY REFERENCES Enemy(NPCID),
	ItemID int FOREIGN KEY REFERENCES Item(ID),
	PRIMARY KEY(EnemyID, ItemID)
)
GO
CREATE TABLE [BossIn](
	BossID int FOREIGN KEY REFERENCES Boss(EnemyID),
	DungeonID int FOREIGN KEY REFERENCES Dungeon(ID),
	PRIMARY KEY(BossID, DungeonID)
)
GO
CREATE TABLE [FoundIn](
	ItemID int FOREIGN KEY REFERENCES Item(ID),
	LocationID int FOREIGN KEY REFERENCES [Location](ID),
	PRIMARY KEY(ItemID, LocationID)
)
GO
CREATE TABLE [Completed](
	UserID int FOREIGN KEY REFERENCES [User](ID),
	DungeonID int FOREIGN KEY REFERENCES Dungeon(ID),
	PRIMARY KEY(UserID, DungeonID)
)
GO
CREATE TABLE [Defeated](
	UserID int FOREIGN KEY REFERENCES [User](ID),
	BossID int FOREIGN KEY REFERENCES Boss(EnemyID),
	PRIMARY KEY(UserID, BossID)
)
GO
CREATE TABLE [HasNPC](
	NPCID int FOREIGN KEY REFERENCES NPC(ID),
	GameID int FOREIGN KEY REFERENCES Game(ID),
	PRIMARY KEY(NPCID, GameID)
)
GO
CREATE TABLE [HasLocation](
	LocationID int FOREIGN KEY REFERENCES [Location](ID),
	GameID int FOREIGN KEY REFERENCES Game(ID),
	PRIMARY KEY(LocationID, GameID)
)
GO
CREATE TABLE [HasItem](
	UserID int FOREIGN KEY REFERENCES [User](ID),
	ItemID int FOREIGN KEY REFERENCES Item(ID),
	PRIMARY KEY(UserID, ItemID),
	Quantity int
)
GO
CREATE TABLE [Completes](
	UserID int FOREIGN KEY REFERENCES [User](ID),
	QuestID int FOREIGN KEY REFERENCES Quest(ID),
	PRIMARY KEY(UserID, QuestID)
)
GO