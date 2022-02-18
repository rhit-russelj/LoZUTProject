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
	
	public ArrayList<String> getRawGames(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM Game";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(4)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(5)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(6)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getRawQuests(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM Quest";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(4)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(5)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(6)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(7)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(8)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(9)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(10)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawDungeons(){
		ArrayList<String> result = new ArrayList<String>();
		String query = 	"SELECT Location.ID, [Name] FROM Dungeon \n JOIN [Location] ON Location.ID = Dungeon.ID";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawLocations(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM Location";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(3)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawBosses(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT Boss.EnemyID, NPC.Name, Boss.GameID, Boss.DungeonID FROM Boss JOIN NPC ON NPC.ID = Boss.EnemyID";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(3)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawEnemies(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT Enemy.NPCID, SpawnAreas, Attacks, SpawnRestrictions, Health, Enemy.GameID FROM Enemy JOIN NPC ON NPC.ID = Enemy.NPCID";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(4)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(5)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(6)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawNPCs(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM NPC";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(4)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawRupees(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM Rupee";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(3)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawConsumables(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT Consumable.ID, Name, Effect, Strength, Type FROM Consumable JOIN Item ON Item.ID = Consumable.ID";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(4)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(5)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String> getRawItems(){
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM Item";
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String addTo = "";
				addTo = addTo.concat(this.concatWithNulls("", rs.getInt(1)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(2)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getString(3)));
				addTo = addTo.concat(this.concatWithNulls(",", rs.getInt(4)));
				result.add(addTo);
			}
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	private String concatWithNulls(String s, Object concat) {
		if(concat == null) {
			concat = "";
		}
		return s.concat(concat.toString());
	}
	
	private ArrayList<String> execNameQuery(String query){
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
