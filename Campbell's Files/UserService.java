import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class UserService {
	
	private DatabaseConnectionService dbService = null;
	
	public UserService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<String> getUserOwns(String username){
		return this.execUserQuery("SELECT * FROM UserGames \n WHERE Username = ?;", 
							username);
	}
	
	public ArrayList<String> getUserDungeons(String username){
		return this.execUserQuery("SELECT * FROM UserDungeons \n WHERE Username = ?;", 
				username);
	}
	
	public ArrayList<String> getUserBosses(String username){
		return this.execUserQuery("SELECT * FROM UserBosses \n WHERE Username = ?;", 
				username);
	}
	
	public ArrayList<String> getUserQuests(String username){
		return this.execUserQuery("SELECT * FROM UserQuests \n WHERE Username = ?;", 
				username);
	}
	
	public ArrayList<String> getUserItems(String username){
		return this.execUserQuery("SELECT * FROM UserItems \n WHERE Username = ?;", 
				username);
	} 
	
	public boolean addToUserOwns(String username, String game) {
		String proc = "{? = call AddUserOwns(?,?)}";
		int uid = this.getUserID(username);
		int gid = this.getGameID(game);
		return this.execIDProc(proc, gid, uid);
	}
	
	public boolean addToUserDungeons(String username, String dungeon) {
		String proc = "{? = call AddToCompleted(?,?)}";
		int uid = this.getUserID(username);
		int did = this.getDungeonID(dungeon);
		return this.execIDProc(proc, uid, did);
	}
	
	public boolean addToUserBosses(String username, String boss) {
		String proc = "{? = call AddDefeated(?,?)}";
		int uid = this.getUserID(username);
		int bid = this.getBossID(boss);
		return this.execIDProc(proc, uid, bid);
	}
	
	public boolean addToUserQuests(String username, String quest) {
		String proc = "{? = call AddToCompletes(?,?)}";
		int uid = this.getUserID(username);
		int qid = this.getQuestID(quest);
		return this.execIDProc(proc, uid, qid);
	}
	
	public boolean addToUserItems(String username, String item, int quantity) {
		String proc = "{? = call AddUserItems(?,?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item);
		return this.execItemProc(proc, iid, uid, quantity);
	}
	
	public boolean deleteFromUserOwns(String username, String game) {
		String proc = "{? = call DeleteUserOwns(?,?)}";
		int uid = this.getUserID(username);
		int gid = this.getGameID(game);
		return this.execIDProc(proc, gid, uid);
	}
	
	public boolean deleteFromUserDungeons(String username, String dungeon) {
		String proc = "{? = call RemoveFromCompleted(?,?)}";
		int uid = this.getUserID(username);
		int did = this.getDungeonID(dungeon);
		return this.execIDProc(proc, uid, did);
	}
	
	public boolean deleteFromUserBosses(String username, String boss) {
		String proc = "{? = call DeleteDefeated(?,?)}";
		int uid = this.getUserID(username);
		int bid = this.getBossID(boss);
		return this.execIDProc(proc, uid, bid);
	}
	
	public boolean deleteFromUserQuests(String username, String quest) {
		String proc = "{? = call RemoveFromCompletes(?,?)}";
		int uid = this.getUserID(username);
		int qid = this.getQuestID(quest);
		return this.execIDProc(proc, uid, qid);
	}
	
	public boolean deleteFromUserItems(String username, String item) {
		String proc = "{? = call DeleteUserItems(?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item);
		return this.execIDProc(proc, iid, uid);
	}
	
	public boolean updateUserItems(String username, String item, int newQuantity) {
		String proc = "{? = call UpdateUserItems(?,?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item);
		return this.execItemProc(proc, iid, uid, newQuantity);
	}

	public int getUserID(String username) {
		String query = "SELECT ID FROM [User] \n WHERE Username=?";
		return this.execIDQuery(query, username);
	}
	
	public int getGameID(String game) {
		String query = "SELECT ID FROM [Game] \n WHERE Name=?";
		return this.execIDQuery(query, game);
	}
	
	public int getDungeonID(String dungeon) {
		String query = "SELECT Dungeon.ID FROM [Dungeon] \n JOIN [Location] ON Location.ID = Dungeon.ID \n WHERE Name=?";
		return this.execIDQuery(query, dungeon);
	}
	
	public int getBossID(String boss) {
		String query = "SELECT Boss.EnemyID FROM [Boss] \n JOIN Enemy ON Boss.EnemyID = Enemy.NPCID \n JOIN NPC ON Enemy.NPCID = NPC.ID \n WHERE Name=?";
		return this.execIDQuery(query, boss);
	}
	
	public int getQuestID(String quest) {
		String query = "SELECT ID FROM [Quest] \n WHERE Name=?";
		return this.execIDQuery(query, quest);
	}
	
	public int getItemID(String item) {
		String query = "SELECT ID FROM [Item] \n WHERE Name=?";
		return this.execIDQuery(query, item);
	}
	
	
	public ArrayList<String> execUserQuery(String query, String username){
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			result = this.formatResultSet(rs, query);
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int execIDQuery(String query, String name) {
		PreparedStatement stmt;
		try {
			int result = -1;
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if(query.contains("EnemyID")) {
				if(rs.next()) {
					//gets the first because ID should be unique
					result = rs.getInt("EnemyID");
				}
			} else {
				if(rs.next()) {
					//gets the first because ID should be unique
					result = rs.getInt("ID");
				}
			}
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public boolean execIDProc(String proc, int id1, int id2) {
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, id1);
			cstmt.setInt(3, id2);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + id1 + " ID2: " + id2);
				return false;
			}
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + id1 + " ID2: " + id2);
			return false;
		}
	}
	
	public boolean execItemProc(String proc, int uid, int itemid, int quantity) {
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, uid);
			cstmt.setInt(3, itemid);
			cstmt.setInt(4, quantity);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + uid + " ID2: " + itemid + " Quantity: " + quantity);
				return false;
			}
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + uid + " ID2: " + itemid + " Quantity: " + quantity);
			return false;
		}
	}
	
	public ArrayList<String> formatResultSet(ResultSet rs, String query){
		ArrayList<String> result = new ArrayList<String>();
		try {
			if(query.contains("UserGames")) {
				while(rs.next()) {
					result.add(rs.getString("Username") + ", " +
							rs.getString("Name") + ", " +
							rs.getString("PublishYear") + ", " + 
							rs.getString("System"));
				}
			} else if (query.contains("UserDungeons")) {
				while(rs.next()) {
					result.add(rs.getString("Username") + ", " +
							rs.getString("Dungeon") + ", " +
							rs.getString("Game"));
				}
			} else if (query.contains("UserQuests")) {
				while(rs.next()) {
					result.add(rs.getString("Username") + ", " +
							rs.getString("Quest") + ", " +
							rs.getString("Objective") + ", " +
							rs.getString("Game"));
				}
			} else if (query.contains("UserBosses")) {
				while(rs.next()) {
					result.add(rs.getString("Username") + ", " +
							rs.getString("Boss") + ", " +
							rs.getString("Description") + ", " +
							rs.getString("Game"));
				}
			} else if (query.contains("UserItems")) {
				while(rs.next()) {
					result.add(rs.getString("Username") + ", " +
							rs.getString("Item") + ", " +
							rs.getString("Description") + ", " +
							rs.getString("Quantity"));
				}
			} else {
					System.err.println("Invalid query in UserService!");
				}
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
}
