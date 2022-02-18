USE LoZUTracker
GO

CREATE VIEW QuestDetails
AS
SELECT q.[Name], q.Objective, q.Storyline, q.[Repeatable], i.[Name] AS Item, pq.[Name] AS PreviousQuest, nq.[Name] AS NextQuest
FROM Quest q
JOIN Quest pq ON q.PreviousQuestID = pq.ID
JOIN Quest nq ON q.NextQuestID = nq.ID
JOIN Item i ON q.ItemID = i.ID