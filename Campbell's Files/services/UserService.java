package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JTable;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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
	
	public ArrayList<String> getUserDungeonsOnGame(String username, String game){
		//TODO
		return this.execUserGameQuery("SELECT Dungeon FROM UserDungeons \n WHERE Username = ? AND Game = ?;", 
				username, game);
	}
	
	public ArrayList<String> getUserBossesOnGame(String username, String game){
		//TODO
		return this.execUserGameQuery("SELECT Boss FROM UserBosses \n WHERE Username = ? AND Game = ?;", 
				username, game);
	}
	
	public ArrayList<String> getUserQuestsOnGame(String username, String game){
		//TODO
		return this.execUserGameQuery("SELECT Quest FROM UserQuests \n WHERE Username = ? AND Game = ?;", 
				username, game);
	}
	
	public ArrayList<String> getUserItemsOnGame(String username, String game){
		//TODO
		return this.execUserGameQuery("SELECT Item FROM UserItems \n WHERE Username = ? AND Game = ?;", 
				username, game);
	} 
	
	/**
	 * returns the names of a query owned by a user with a certain game
	 * @param string
	 * @param username
	 * @param game
	 * @return
	 */
	private ArrayList<String> execUserGameQuery(String query, String username, String game) {
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			stmt.setString(2, game);
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

	public JTable grabUserJTableFunction(String funcName, String username) {
		//grab data
		ArrayList<String []> tempdata = new ArrayList<String []>();
		CallableStatement cstmt;
		try {
			int userID = getUserID(username);
			String q = "SELECT * FROM " + funcName + "(" + userID + ")";
			cstmt = this.dbService.getConnection().prepareCall(q);
			System.out.println(q);
//	      cstmt.execute();
	      int rownum = 6;
	      ResultSet rv = cstmt.executeQuery();
			while(rv.next()) {
				String[] row = new String[rownum];
				for(int i=1;i<=rownum;i++) {
					row[i-1]=(rv.getString(i));
				}
				tempdata.add(row);
			}
			cstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		SQLServerCallableStatement pStmt;
//		try {
//			ResultSet rs = new ResultSet();
//			pStmt = (SQLServerCallableStatement) this.dbService.getConnection().prepareCall("exec usp_InsertCategories ?");
//			pStmt.setStructured(1, "dbo.CategoryTableType", rs);
//			pStmt.execute();  
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//			
//			
//		ArrayList<String []> tempdata = new ArrayList<String []>();
//		String query = "SELECT * FROM " + funcName + "(?)";
//		
//		PreparedStatement stmt;
//		
		int rownum = 6;
//		try {
//			stmt = this.dbService.getConnection().prepareStatement(query);
//			stmt.setString(1, String.valueOf(userID));
//			ResultSet rv = stmt.executeQuery();
//			while(rv.next()) {
//				String[] row = new String[rownum];
//				for(int i=1;i<=rownum;i++) {
//					row[i-1]=(rv.getString(i));
//				}
//				tempdata.add(row);
//			}
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.err.println("Something went wrong getting the user views data!");
//			return null;
//		}
		String[][] data = new String[tempdata.size()][rownum];
		for(int i=0;i<tempdata.size();i++) {
			data[i] = tempdata.get(i);
		}
		
		//grab column names
		String[] columnNames = {"Username", "Game Name", "Game System", "Item Name", "BossID", "Quest Name"};
		
		//create and return JTable
		return new JTable(data, columnNames);
	}
	
	
	
	/**
	 * returns a JTable object with the information needed for a specified user view
	 * @param viewName - name of the view
	 * @return a JTable object
	 */
	public JTable grabUserJTable(String viewName, String username) {
		//grab data
		ArrayList<String []> tempdata = new ArrayList<String []>();
		String query = "SELECT * FROM " + viewName + "\n WHERE Username = ?";
		PreparedStatement stmt;
		
		//uninitialized for additional error handling
		int rownum = -1;
		switch(viewName) {
			case "UserDungeons":
				rownum = 3;
				break;
			case "UserBosses":
			case "UserQuests":
			case "UserGames":
				rownum = 4;
				break;
			case "UserItems":
				rownum = 5;
				break;
			default:
				//
				return null;
		}
		
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				String[] row = new String[rownum];
				for(int i=1;i<=rownum;i++) {
					row[i-1]=(rs.getString(i));
				}
				tempdata.add(row);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Something went wrong getting the user views data!");
			return null;
		}
		//arraylist -> array
		String[][] data = new String[tempdata.size()][rownum];
		for(int i=0;i<tempdata.size();i++) {
			data[i] = tempdata.get(i);
		}
		
		//grab column names
		String[] columnNames = null;
		switch(viewName) {
			//there's probably a better way to do this
			case "UserDungeons":
				String[] temp = {"Username", "Dungeon", "Game"};
				columnNames = temp;
				break;
			case "UserBosses":
				String[] temp2 = {"Username", "Boss", "Description", "Game"};
				columnNames = temp2;
				break;
			case "UserQuests":
				String[] temp3 = {"Username", "Quest", "Objective", "Game"};
				columnNames = temp3;
				break;
			case "UserGames":
				String[] temp4 = {"Username", "Name", "System", "Publish Year"};
				columnNames = temp4;
				break;
			case "UserItems":
				String[] temp5 = {"Username", "Item", "Description", "Quantity", "Game"};
				columnNames = temp5;
				break;
			default:
				break;
		}
		//create and return JTable
		return new JTable(data, columnNames);
	}
	
	public boolean addToUserOwns(String username, String game) {
		String proc = "{? = call AddUserOwns(?,?)}";
		int uid = this.getUserID(username);
		int gid = this.getGameID(game);
		return this.execIDProc(proc, gid, uid);
	}
	
	public boolean addToUserDungeons(String username, String game, String dungeon) {
		String proc = "{? = call AddToCompleted(?,?)}";
		int uid = this.getUserID(username);
		int did = this.getDungeonID(dungeon, game);
		return this.execIDProc(proc, uid, did);
	}
	
	public boolean addToUserBosses(String username, String game, String boss) {
		String proc = "{? = call AddDefeated(?,?)}";
		int uid = this.getUserID(username);
		int bid = this.getBossID(boss, game);
		return this.execIDProc(proc, uid, bid);
	}
	
	public boolean addToUserQuests(String username, String game, String quest) {
		String proc = "{? = call AddToCompletes(?,?)}";
		int uid = this.getUserID(username);
		int qid = this.getQuestID(quest, game);
		return this.execIDProc(proc, uid, qid);
	}
	
	public boolean addToUserItems(String username, String game, String item, int quantity) {
		String proc = "{? = call AddUserItems(?,?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item, game);
		return this.execItemProc(proc, iid, uid, quantity);
	}
	
	public boolean deleteFromUserOwns(String username, String game) {
		String proc = "{? = call DeleteUserOwns(?,?)}";
		int uid = this.getUserID(username);
		int gid = this.getGameID(game);
		return this.execIDProc(proc, gid, uid);
	}
	
	public boolean deleteFromUserDungeons(String username, String game, String dungeon) {
		String proc = "{? = call RemoveFromCompleted(?,?)}";
		int uid = this.getUserID(username);
		int did = this.getDungeonID(dungeon, game);
		return this.execIDProc(proc, uid, did);
	}
	
	public boolean deleteFromUserBosses(String username, String game, String boss) {
		String proc = "{? = call DeleteDefeated(?,?)}";
		int uid = this.getUserID(username);
		int bid = this.getBossID(boss, game);
		return this.execIDProc(proc, uid, bid);
	}
	
	public boolean deleteFromUserQuests(String username, String game, String quest) {
		String proc = "{? = call RemoveFromCompletes(?,?)}";
		int uid = this.getUserID(username);
		int qid = this.getQuestID(quest, game);
		return this.execIDProc(proc, uid, qid);
	}
	
	public boolean deleteFromUserItems(String username, String game, String item) {
		String proc = "{? = call DeleteUserItems(?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item, game);
		return this.execIDProc(proc, iid, uid);
	}
	
	public boolean updateUserItems(String username, String game, String item, int newQuantity) {
		String proc = "{? = call UpdateUserItems(?,?,?)}";
		int uid = this.getUserID(username);
		int iid = this.getItemID(item, game);
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
	
	public int getDungeonID(String dungeon, String game) {
		String query = "SELECT Dungeon.ID FROM [Dungeon] \n JOIN [Location] ON Location.ID = Dungeon.ID \n WHERE Name=? AND GameID=?";
		return this.execIDQuery(query, dungeon, getGameID(game));
	}
	
	public int getBossID(String boss, String game) {
		String query = "SELECT Boss.EnemyID FROM [Boss] \n JOIN Enemy ON Boss.EnemyID = Enemy.NPCID \n JOIN NPC ON Enemy.NPCID = NPC.ID \n WHERE Name=? AND NPC.GameID=?";
		return this.execIDQuery(query, boss, getGameID(game));
	}
	
	public int getQuestID(String quest, String game) {
		String query = "SELECT ID FROM [Quest] \n WHERE Name=? AND GameID=?";
		return this.execIDQuery(query, quest, getGameID(game));
	}
	
	public int getItemID(String item, String game) {
		String query = "SELECT ID FROM [Item] \n WHERE Name=? AND GameID=?";
		return this.execIDQuery(query, item, getGameID(game));
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
	
	public int execIDQuery(String query, String name, int gid) {
		PreparedStatement stmt;
		try {
			int result = -1;
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, gid);
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
