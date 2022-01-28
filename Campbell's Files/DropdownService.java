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
}
