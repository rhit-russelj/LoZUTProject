USE LoZUTracker
GO

CREATE VIEW BossDetails AS
SELECT NPC.[Name] AS BossName, [Description], SpawnAreas, Attacks, SpawnRestrictions, Health, l.[Name] AS DungeonLoc
FROM Boss b
JOIN Enemy e ON e.NPCID = b.EnemyID
JOIN NPC ON NPC.ID = e.NPCID
JOIN Dungeon d ON d.ID = b.DungeonID
JOIN [Location] l ON l.ID = d.ID