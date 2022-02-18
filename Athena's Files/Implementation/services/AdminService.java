package services;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AdminService {
	
	private DatabaseConnectionService dbService;
	
	public AdminService(DatabaseConnectionService dbcs) {
		this.dbService = dbcs;
	}
	
	/*
	 * Admin add method begin here
	 */
	
	public boolean addGame(String name, String year, String timeline, String era, String system) {
		String proc = "{? = call AddGame(?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(year));
			cstmt.setString(3, name);
			cstmt.setString(4, era);
			cstmt.setInt(5, Integer.parseInt(timeline));
			cstmt.setString(6, system);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}
		return true;
	}
	
	public boolean addQuest(String name, String objective, String storyline, String repeatable, String givesItem, String nextQuest, String recievedFrom, String game) {
		String proc = "{? = call AddQuest(?,?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setString(2, name);
			cstmt.setString(3, objective);
			cstmt.setString(4, storyline);
			if(repeatable.equals("Yes")) {
				cstmt.setInt(5, 1);
			} else {
				cstmt.setInt(5, 0);
			}
			if(this.getItemID(givesItem) > -1) {
				cstmt.setInt(6, this.getItemID(givesItem));
			} else {
				cstmt.setNull(6, Types.NULL);
			}
			if(this.getQuestID(nextQuest) > -1) {
				cstmt.setInt(7, this.getQuestID(nextQuest));
			} else {
				cstmt.setNull(7, Types.NULL);
			}
			cstmt.setNull(8, Types.NULL);
			if(this.getGameIDFromNPC(recievedFrom) > -1) {
				cstmt.setInt(9, this.getGameID(game));
			} else {
				cstmt.setNull(9, Types.NULL);
			}
			if(this.getNPCID(recievedFrom, game) > -1) {
				cstmt.setInt(10, this.getNPCID(recievedFrom, game));
			} else {
				cstmt.setNull(10, Types.NULL);
			}
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the quests in this table!");
			return false;
		}
	
	return true;
}
	
	public boolean addLocation(String name, String game) {
		String proc = "{? = call AddLocation(?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setString(2, name);
			if(this.getGameID(game) > -1) {
				cstmt.setInt(3, this.getGameID(game));
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.execute();
				int result = cstmt.getInt(1);
				if(result > 0) {
					System.out.println("WARNING: Something went wrong in adding " + name + "!");
				} else {
					System.out.println("Successfully added " + name + "!");
				}
			} else {
				System.err.println("Game " + game + " does not currently exist!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.err.println("ERROR: End of file!");
			//does not return because EoF may be cause by unused rows
		}
		return true;
	}
	
	public boolean addDungeon(String name, String game) {
		String proc = "{? = call AddDungeon(?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			//Is the location not in the database?
			if(this.getLocationID(name, game) == -1) {
				this.addLocation(name, game);
			}
			
			cstmt.setInt(2, this.getLocationID(name, game));
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.err.println("WARNING: End of file!");
		}
		return true;
	}
	
	public boolean addNPC(String name, String desc, String game) {
		String proc = "{? = call AddNPC(?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setString(2, name);
			cstmt.setString(3, desc);
			if(this.getGameID(game) > -1) {
				cstmt.setInt(4, this.getGameID(game));
			} else {
				System.err.println("Game " + game + " does not currently exist!");
				cstmt.setNull(4, Types.NULL);
			}
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.err.println("ERROR: End of file!");
			//does not return because EoF may be cause by unused rows
		}
		return true;
	}
	
	public boolean addEnemy(String name, String desc, String spawnAreas, String attacks, String spawnRestrictions, String health, String game) {
		String proc = "{? = call AddEnemy(?,?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			
			if(this.getNPCID(name, game) == -1) {
				this.addNPC(name, desc, game);
			}
			cstmt.setInt(2, this.getNPCID(name, game));
			cstmt.setString(3, spawnAreas);
			cstmt.setString(4, attacks);
			cstmt.setString(5, spawnRestrictions);
			cstmt.setInt(6, Integer.parseInt(health));
			cstmt.setInt(7, this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			System.err.println("ERROR: End of file!");
			//does not return because EoF may be cause by unused rows
		}
		return true;
	}
	
	public boolean addBoss(String name, String desc, String spawnAreas, String attacks, String spawnRestrictions, String health, String game, String dungeon) {
		String proc = "{? = call AddBoss(?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			
			if(this.getEnemyID(name, game) == -1) {
				this.addEnemy(name, desc, spawnAreas, attacks, spawnRestrictions, health, game);
			}
			if(this.getDungeonID(name, game) == -1) {
				this.addDungeon(dungeon, game);
			}
			cstmt.setInt(2, this.getEnemyID(name, game));
			cstmt.setInt(3,this.getDungeonID(dungeon, game));
			cstmt.setInt(4,this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}
		return true;
	}
	
	public boolean addItem(String name, String desc, String game) {
		String proc = "{? = call AddItem(?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setString(2, name);
			cstmt.setString(3, desc);
			cstmt.setInt(4,this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}
		return true;
	}
	
	public boolean addRupee(String color, String value, String game) {
		String proc = "{? = call AddRupee(?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			if(this.getItemID(color + " Rupee") == -1 ) {
				this.addItem(color + " Rupee", "A " + color + " rupee", game);
			}
			cstmt.setInt(2, this.getItemID(color + " Rupee"));
			cstmt.setString(3, color);
			cstmt.setInt(4, Integer.parseInt(value));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + color + " Rupee" + "!");
			} else {
				System.out.println("Successfully added " + color + " Rupee" + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}
		return true;
	}
	
	public boolean addConsumable(String name, String desc, String effect, String strength, String type, String game) {
		String proc = "{? = call AddConsumable(?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			if(this.getItemID(name) == -1 ) {
				this.addItem(name, desc, game);
			}
			cstmt.setInt(2, this.getItemID(name));
			cstmt.setString(3, effect);
			cstmt.setInt(4, Integer.parseInt(strength));
			cstmt.setString(5, type);
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in adding " + name + "!");
			} else {
				System.out.println("Successfully added " + name + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in adding the games in this table!");
			return false;
		}
		return true;
	}
	
	/*
	 * Admin update methods begin here
	 */
	
	public boolean updateGame(String id, String name, String year, String era, String timeline, String system) {
		String proc = "{? = call UpdateGame(?,?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setInt(3, Integer.parseInt(year));
			cstmt.setString(4, name);
			cstmt.setString(5, era);
			cstmt.setInt(6, Integer.parseInt(timeline));
			cstmt.setString(7, system);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + name + "!");
			} else {
				System.out.println("Successfully updated " + name + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the games in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateQuest(String id, String name, String objective, String storyline, String repeatable, String item, String nextQuest, String npc, String game) {
		String proc = "{? = call UpdateQuest(?,?,?,?,?,?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  name);
			cstmt.setString(4, objective);
			cstmt.setString(5, storyline);
			if(repeatable.equals("Yes"))
				cstmt.setInt(6, 1);
			else
				cstmt.setInt(6, 0);
			if(this.getItemID(item) > -1) {
				cstmt.setInt(7, this.getItemID(item));
			} else {
				cstmt.setNull(7, Types.NULL);
			}
			if(this.getQuestID(nextQuest) > -1) {
				cstmt.setInt(8, this.getQuestID(nextQuest));
			} else {
				cstmt.setNull(8, Types.NULL);
			}
			//
			cstmt.setNull(9, Types.NULL);
			//
			cstmt.setInt(10, this.getGameID(game));
			if(this.getNPCID(npc, game) > -1) {
				cstmt.setInt(11, this.getNPCID(npc, game));
			} else {
				cstmt.setNull(11, Types.NULL);
			}
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + name + "!");
			} else {
				System.out.println("Successfully updating " + name + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the quests in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateLocation(String id, String name, String game) {
		String proc = "{? = call UpdateLocation(?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  name);
			cstmt.setInt(4, this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + name + "!");
			} else {
				System.out.println("Successfully updating " + name + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the locations in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateDungeon() {
		//what would you update?
		//
		return false;
	}
	
	public boolean updateBoss() {
		//what would you update?
		//
		return false;
	}
	
	public boolean updateEnemy(String id, String areas, String restrict, String health, String game) {
		String proc = "{? = call UpdateEnemy(?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  areas);
			cstmt.setString(4, restrict);
			cstmt.setInt(5, Integer.parseInt(health));
			cstmt.setInt(6, this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + id + "!");
			} else {
				System.out.println("Successfully updating " + id + "!");
				this.updateNPC(id, this.getNPCName(id), this.getNPCDesc(id) ,game);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the enemies in this table!");
			return false;
		}
		return true;
	}

	public boolean updateNPC(String id, String name, String desc, String game) {
		String proc = "{? = call UpdateNPC(?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  name);
			cstmt.setString(4, desc);
			cstmt.setInt(5, this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + name + "!");
			} else {
				System.out.println("Successfully updating " + name + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the NPCs in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateRupee(String id,  String value) {
		String proc = "{? = call UpdateRupee(?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  value);
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating Rupee with id " + id);
			} else {
				System.out.println("Successfully updating Rupee with id " + id);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the rupees in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateConsumable(String id, String effect, String strength, String type) {
		String proc = "{? = call UpdateConsumable(?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  effect);
			cstmt.setInt(4, Integer.parseInt(strength));
			cstmt.setString(5,  type);
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + id + "!");
			} else {
				System.out.println("Successfully updating " + id + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the consumables in this table!");
			return false;
		}
		return true;
	}
	
	public boolean updateItem(String id, String name, String desc, String game) {
		String proc = "{? = call UpdateItem(?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, Integer.parseInt(id));
			cstmt.setString(3,  name);
			cstmt.setString(4, desc);
			cstmt.setInt(5,  this.getGameID(game));
			
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in updating " + name + "!");
			} else {
				System.out.println("Successfully updating " + name + "!");
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in updating the items in this table!");
			return false;
		}
		return true;
	}
	
	/*
	 * Admin delete methods begin here
	 */
	
	public boolean deleteGame(String id) {
		String proc = "{? = call DeleteGame(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteBoss(String id) {
		String proc = "{? = call DeleteBoss(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteEnemy(String id) {
		String proc = "{? = call DeleteEnemy(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteNPC(String id) {
		String proc = "{? = call DeleteNPC(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteDungeon(String id) {
		String proc = "{? = call DeleteDungeon(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteLocation(String id) {
		String proc = "{? = call DeleteLocation(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteQuest(String id) {
		String proc = "{? = call DeleteQuest(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteRupee(String id) {
		String proc = "{? = call DeleteRupee(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteConsumable(String id) {
		String proc = "{? = call DeleteConsumable(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	public boolean deleteItem(String id) {
		String proc = "{? = call DeleteItem(?)}";
		return this.execIDProc(proc, Integer.parseInt(id));
	}
	
	/*
	 * Private methods begin here
	 */
	
	private int getGameID(String game) {
		String query = "SELECT ID FROM [Game] \n WHERE Name=?";
		return this.execIDQuery(query, game);
	}
	
	private int getItemID(String item) {
		String query = "SELECT ID FROM [Item] \n WHERE Name=?";
		return this.execIDQuery(query, item);
	}
	
	private int getNPCID(String npc, String game) {
		String query = "SELECT ID FROM [NPC] \n WHERE Name=? AND GameID=?";
		return this.execIDQuery(query, npc, this.getGameID(game));
	}
	
	//May seem redundant, but it returns -1 if there is no enemy entry
	private int getEnemyID(String npc, String game) {
		String query = "SELECT NPCID FROM [Enemy] \n WHERE NPCID=?";
		return this.execIDQuery(query, this.getNPCID(npc, game));
	}
	
	//May seem redundant, but it returns -1 if there is no dungeon entry
	private int getDungeonID(String dungeon, String game) {
		String query = "SELECT ID FROM [Dungeon] \n WHERE ID=?";
		return this.execIDQuery(query, this.getLocationID(dungeon, game));
	}
	
	private int getQuestID(String quest) {
		String query = "SELECT ID FROM [Quest] \n WHERE Name=?";
		return this.execIDQuery(query, quest);
	}
	
	private int getLocationID(String loc, String game) {
		String query = "SELECT ID FROM [Location] \n WHERE Name=? AND GameID=?";
		return this.execIDQuery(query, loc, this.getGameID(game));
	}
	
	private int getGameIDFromNPC (String npc) {
		String query = "SELECT GameID FROM NPC \n WHERE NPC.[Name] = ?";
		return this.execIDQuery(query, npc);
	}
	
	/**
	 * exec a stored proc with ID param
	 * primarily used for deletion
	 * @param proc
	 * @param id
	 * @return
	 */
	private boolean execIDProc(String proc, int id) {
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setInt(2, id);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int result = cstmt.getInt(1);
			if(result > 0) {
				System.out.println("WARNING: Something went wrong in deleting " + id + "!");
			} else {
				System.out.println("Successfully deleted " + id + "!");
			}
			System.out.println("continuing...");
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in deleting the games in this table!");
			return false;
		}
		return true;
	}
	
	/**
	 * exec a query to get an ID
	 * @param query
	 * @param name
	 * @return ID
	 */
	private int execIDQuery(String query, String name) {
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
			} else if(query.contains("GameID")) {
				if(rs.next()) {
					//gets the first because ID should be unique
					result = rs.getInt("GameID");
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
	
	private String getNPCDesc(String id) {
		PreparedStatement stmt;
		try {
			String result = "";
			stmt = this.dbService.getConnection().prepareStatement("SELECT Description FROM NPC WHERE NPC.ID = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getNPCName(String id) {
		PreparedStatement stmt;
		try {
			String result = "";
			stmt = this.dbService.getConnection().prepareStatement("SELECT Name FROM NPC WHERE NPC.ID = ?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				result = rs.getString(1);
			}
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * execIDQuery for checking if an ID entry is in a table
	 * @param query
	 * @param param
	 * @return ID
	 */
	private int execIDQuery(String query, int param){
		PreparedStatement stmt;
		try {
			int result = -1;
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setInt(1, param);
			ResultSet rs = stmt.executeQuery();
			if(query.contains("NPCID")) {
				if(rs.next()) {
					//gets the first because ID should be unique
					result = rs.getInt("NPCID");
				}
			}else {
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
	
	/**
	 * execIDQuery for queries that require another ID param
	 * @param query
	 * @param name
	 * @param param
	 * @return ID
	 */
	private int execIDQuery(String query, String name, int param) {
		PreparedStatement stmt;
		try {
			int result = -1;
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, name);
			stmt.setInt(2, param);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				//gets the first because ID should be unique
				result = rs.getInt("ID");
			}
			stmt.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}
