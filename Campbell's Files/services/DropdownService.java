package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DropdownService {
	
	private DatabaseConnectionService dbService = null;
	
	public DropdownService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<String> getGames(){
		return this.execNameQuery("SELECT [Name] FROM Game");
	}
	
	public ArrayList<String> getDungeons(){
		return this.execNameQuery("SELECT [Name] FROM Dungeon \n JOIN [Location] ON Location.ID = Dungeon.ID");
	}
	
	public ArrayList<String> getBosses(){
		return this.execNameQuery("SELECT [Name] FROM Boss \n JOIN Enemy ON Boss.EnemyID = Enemy.NPCID \n JOIN NPC ON Enemy.NPCID = NPC.ID");
	}
	
	public ArrayList<String> getQuests(){
		return this.execNameQuery("SELECT [Name] FROM Quest");
	}
	
	public ArrayList<String> getItems(){
		return this.execNameQuery("SELECT [Name] FROM Item");
	}
	
	public ArrayList<String> getDungeonsOnGame(String game){
		return this.execNameWithGameQuery("SELECT Location.Name FROM Dungeon \n JOIN [Location] ON Location.ID = Dungeon.ID \n JOIN Game ON Location.GameID = Game.ID \n WHERE Game.Name = ?", game);
	}

	public ArrayList<String> getBossesOnGame(String game){
		return this.execNameWithGameQuery("SELECT NPC.Name FROM Boss \n JOIN Enemy ON Boss.EnemyID = Enemy.NPCID \n JOIN NPC ON Enemy.NPCID = NPC.ID \n JOIN Game ON NPC.GameID = Game.ID \n WHERE Game.Name = ?", game);
	}
	
	public ArrayList<String> getQuestsOnGame(String game){
		return this.execNameWithGameQuery("SELECT Quest.Name \n FROM Quest \n JOIN Game ON Quest.GameID = Game.ID \n WHERE Game.Name = ?", game);
	}
	
	public ArrayList<String> getItemsOnGame(String game){
		return this.execNameWithGameQuery("SELECT Item.Name \n FROM Item \n JOIN Game ON Item.GameID = Game.ID \n WHERE Game.Name = ?", game);
	}
	
	public ArrayList<String> execNameQuery(String query){
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result.add(rs.getString("Name"));
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	private ArrayList<String> execNameWithGameQuery(String query, String game) {
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, game);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result.add(rs.getString(1));
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
}
