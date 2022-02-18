package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;

public class ImportService {
	
	private String folderPath;
	private String[][] loadedData = null;
	private DatabaseConnectionService dbService = null;
	
	public ImportService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input the pathname for the folder with the necessary data...");
		try {
			this.folderPath = br.readLine();
		} catch (IOException e) {
			this.folderPath = null;
			e.printStackTrace();
		}
		
	}
	
	/*
	 * public methods begin here 
	 */
	
	public String[] getFilesInDirectory() {
	      File directoryPath = new File(this.folderPath);
	      
	      String contents[] = directoryPath.list();
	      /*
	      System.out.println("List of files and directories in the specified directory:");
	      for(int i=0; i<contents.length; i++) {
	         System.out.println(contents[i]);
	      }
	      */
	      return contents;
	}
	
	public boolean importDataFromDirectory() {
		try {
			//Inefficient becuase we have to keep an order in place:
			//1.Game
			//2.Everything else
			//3.Quest
			for(String s:this.getFilesInDirectory()) {
				File f = new File(this.folderPath + "\\" + s);
				System.out.println(s);
				if(s.contains("Game")) {
					if(!this.addGamesFromFile(f)) {
						System.err.println("Some games were not added correctly!");
					}
				}
			}
			for(String s:this.getFilesInDirectory()) {
				System.out.printf(s);
				File f = new File(this.folderPath + "\\" + s);
				if(s.contains("Location")) {
					if(!this.addLocationsFromFile(f)) {
						System.err.println("Some locations were not added correctly!");
					}
				} else if(s.contains("Dungeon")) {
					if(!this.addDungeonsFromFile(f)) {
						System.err.println("Some dungeons were not added correctly!");
					}
				} else if(s.contains("NPC")) {
					if(!this.addNPCSFromFile(f)) {
						System.err.println("Some NPCs were not added correctly!");
					}
				} else if(s.contains("Enem")) {
					if(!this.addEnemiesFromFile(f)) {
						System.err.println("Some enemies were not added correctly!");
					}
				} else if(s.contains("Boss")) {
					if(!this.addBossesFromFile(f)) {
						System.err.println("Some bosses were not added correctly!");
					}
				} else if(s.contains("Item")) {
					if(!this.addItemsFromFile(f)) {
						System.err.println("Some items were not added correctly!");
					}
				} else if(s.contains("Consumable")) {
					if(!this.addConsumablesFromFile(f)) {
						System.err.println("Some consumables were not added correctly!");
					}
				} else if(s.contains("Rupee")) {
					if(!this.addRupeesFromFile(f)) {
						System.err.println("Some rupees were not added correctly!");
					}
				}
			}
			for(String s:this.getFilesInDirectory()) {
				File f = new File(this.folderPath + "\\" + s);
				if(s.contains("Quest")) {
					if(!this.addQuestsFromFile(f)) {
						System.err.println("Some quests were not added correctly!");
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean addGamesFromFile(File f) {
		this.loadData(f);
		String proc = "{? = call AddGame(?,?,?,?,?)}";
		/*
		 * AddGame:
		 * 	@publishYear
		 * 	@name
		 * 	@timelineEra
		 * 	@timelineNumber
		 * 	@system
		 */
		String[] tableInfo = this.loadedData[0];
		int publishYearIndex = ImportService.findIndexOf(tableInfo, "PublishYear");
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int eraIndex = ImportService.findIndexOf(tableInfo, "Era");
		int timelineIndex = ImportService.findIndexOf(tableInfo, "Timeline");
		int systemIndex = ImportService.findIndexOf(tableInfo, "System");
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			try {
				CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
				cstmt.setInt(2, Integer.parseInt(currData[publishYearIndex]));
				cstmt.setString(3, currData[nameIndex]);
				cstmt.setString(4, currData[eraIndex]);
				cstmt.setInt(5, Integer.parseInt(currData[timelineIndex]));
				cstmt.setString(6, currData[systemIndex]);
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.execute();
				int result = cstmt.getInt(1);
				if(result > 0) {
					System.out.println("WARNING: Something went wrong in adding " + currData[nameIndex] + "!");
				} else {
					System.out.println("Successfully added " + currData[nameIndex] + "!");
				}
				System.out.println("continuing...");
			}catch (SQLException e) {
				e.printStackTrace();
				System.err.println("ERROR: Something went wrong in adding the games in this table!");
				return false;
			}catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("WARNING: End of file!");
				return true;
			}
		}
		
		return true;
	}
	
	public boolean addQuestsFromFile(File f) {
		this.loadData(f);
		String proc = "{? = call AddQuest(?,?,?,?,?,?,?,?,?)}";
		/*
		 * AddQuest:
		 * 	@name
		 * 	@objective
		 * 	@storyline
		 * 	@repeatable
		 * 	@item
		 * 	@nextquest
		 * 	@previousquest (deprecated)
		 * 	@game
		 * 	@npc
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int objIndex = ImportService.findIndexOf(tableInfo, "Objective");
		int storyIndex = ImportService.findIndexOf(tableInfo, "Storyline");
		int repeatableIndex = ImportService.findIndexOf(tableInfo, "Repeatable");
		int itemIndex = ImportService.findIndexOf(tableInfo, "GivesItem");
		int nextIndex = ImportService.findIndexOf(tableInfo, "NextQuest");
		//
		//
		int npcIndex = ImportService.findIndexOf(tableInfo, "RecievedFrom");
		//
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			try {
				CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
				cstmt.setString(2, currData[nameIndex]);
				cstmt.setString(3, currData[objIndex]);
				cstmt.setString(4, currData[storyIndex]);
				if(currData[repeatableIndex].equals("Yes")) {
					cstmt.setInt(5, 1);
				} else {
					cstmt.setInt(5, 0);
				}
				if(this.getItemID(currData[itemIndex]) > -1) {
					cstmt.setInt(6, this.getItemID(currData[itemIndex]));
				} else {
					cstmt.setNull(6, Types.NULL);
				}
				if(this.getQuestID(currData[nextIndex]) > -1) {
					cstmt.setInt(7, this.getQuestID(currData[nextIndex]));
				} else {
					cstmt.setNull(7, Types.NULL);
				}
				cstmt.setNull(8, Types.NULL);
				if(this.getGameIDFromNPC(currData[npcIndex]) > -1) {
					cstmt.setInt(9, this.getGameID(currData[gameIndex]));
				} else {
					cstmt.setNull(9, Types.NULL);
				}
				if(this.getNPCID(currData[npcIndex], currData[gameIndex]) > -1) {
					cstmt.setInt(10, this.getNPCID(currData[npcIndex], currData[gameIndex]));
				} else {
					cstmt.setNull(10, Types.NULL);
				}
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.execute();
				int result = cstmt.getInt(1);
				if(result > 0) {
					System.out.println("WARNING: Something went wrong in adding " + currData[nameIndex] + "!");
				} else {
					System.out.println("Successfully added " + currData[nameIndex] + "!");
				}
				System.out.println("continuing...");
			}catch (SQLException e) {
				e.printStackTrace();
				System.err.println("ERROR: Something went wrong in adding the quests in this table!");
				return false;
			}catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("WARNING: End of file!");
				return true;
			}
		}
		
		return true;
	}
	
	public boolean addLocationsFromFile(File f) {
		this.loadData(f);
		/*
		 * AddLocation:
		 * 	@name
		 * 	@game
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addLocation(currData[nameIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}

	public boolean addDungeonsFromFile(File f) {
		this.loadData(f);
		/*
		 * AddLocation:
		 * 	@locationID
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addDungeon(currData[nameIndex], currData[gameIndex])) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean addNPCSFromFile(File f) {
		this.loadData(f);
		/*
		 * AddNPC:
		 * 	@name
		 * 	@desc
		 * 	@game
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int descIndex = ImportService.findIndexOf(tableInfo, "Description");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addNPC(currData[nameIndex], currData[descIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}

	public boolean addEnemiesFromFile(File f) {
		this.loadData(f);
		/*
		 * AddEnemy:
		 * 	@ID
		 * 	@spawnarea
		 * 	@attacks
		 *  @health
		 *  @game
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int descIndex = ImportService.findIndexOf(tableInfo, "Description");
		int spawnIndex = ImportService.findIndexOf(tableInfo, "SpawnAreas");
		int attackIndex = ImportService.findIndexOf(tableInfo, "Attacks");
		int restrictIndex = ImportService.findIndexOf(tableInfo, "SpawnRestriction");
		int healthIndex = ImportService.findIndexOf(tableInfo, "Health");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addEnemy(currData[nameIndex], currData[descIndex], currData[spawnIndex], 
					currData[attackIndex], currData[restrictIndex], currData[healthIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}
	
	public boolean addBossesFromFile(File f) {
		this.loadData(f);
		String proc = "{? = call AddBoss(?,?,?)}";
		/*
		 * AddBoss:
		 * 	@enemyID
		 * 	@dungeonID
		 * 	@game
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int descIndex = ImportService.findIndexOf(tableInfo, "Description");
		int spawnIndex = ImportService.findIndexOf(tableInfo, "SpawnAreas");
		int attackIndex = ImportService.findIndexOf(tableInfo, "Attacks");
		int restrictIndex = ImportService.findIndexOf(tableInfo, "SpawnRestriction");
		int healthIndex = ImportService.findIndexOf(tableInfo, "Health");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		int dungeonIndex = ImportService.findIndexOf(tableInfo, "Dungeon");
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			try {
				CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
				
				if(this.getEnemyID(currData[nameIndex], currData[gameIndex]) == -1) {
					this.addEnemy(currData[nameIndex], currData[descIndex], currData[spawnIndex], 
							currData[attackIndex], currData[restrictIndex], currData[healthIndex], currData[gameIndex]);
				}
				cstmt.setInt(2,this.getEnemyID(currData[nameIndex], currData[gameIndex]));
				
				if(this.getDungeonID(currData[dungeonIndex], currData[gameIndex]) == -1) {
					this.addDungeon(currData[dungeonIndex], currData[gameIndex]);
				}
				cstmt.setInt(3,this.getDungeonID(currData[dungeonIndex], currData[gameIndex]));
				
				cstmt.setInt(4,this.getGameID(currData[gameIndex]));
				
				cstmt.registerOutParameter(1, Types.INTEGER);
				cstmt.execute();
				int result = cstmt.getInt(1);
				if(result > 0) {
					System.out.println("WARNING: Something went wrong in adding " + currData[nameIndex] + "!");
				} else {
					System.out.println("Successfully added " + currData[nameIndex] + "!");
				}
				System.out.println("continuing...");
			}catch (SQLException e) {
				e.printStackTrace();
				System.err.println("ERROR: Something went wrong in adding the bosses in this table!");
				return false;
			}catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
				System.err.println("WARNING: End of file!");
				return true;
			}
		}
		return true;
		
	}
	
	public boolean addItemsFromFile(File f){
		this.loadData(f);
		/*
		 * AddItem:
		 * 	@name
		 * 	@description
		 * 	@game
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int descIndex = ImportService.findIndexOf(tableInfo, "Description");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addItem(currData[nameIndex], currData[descIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}
	
	public boolean addRupeesFromFile(File f){
		this.loadData(f);
		/*
		 * AddItem:
		 * 	@ID
		 * 	@color
		 * 	@value
		 */
		String[] tableInfo = this.loadedData[0];
		int colorIndex = ImportService.findIndexOf(tableInfo, "Color");
		int valueIndex = ImportService.findIndexOf(tableInfo, "Value");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addRupee(currData[colorIndex], currData[valueIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}
	
	public boolean addConsumablesFromFile(File f){
		this.loadData(f);
		/*
		 * AddItem:
		 * 	@ID
		 * 	@effect
		 * 	@strength
		 * 	@type
		 */
		String[] tableInfo = this.loadedData[0];
		int nameIndex = ImportService.findIndexOf(tableInfo, "Name");
		int descIndex = ImportService.findIndexOf(tableInfo, "Description");
		int effectIndex = ImportService.findIndexOf(tableInfo, "Effect");
		int strIndex = ImportService.findIndexOf(tableInfo, "Strength");
		int typeIndex = ImportService.findIndexOf(tableInfo, "Type");
		int gameIndex = ImportService.findIndexOf(tableInfo, "Game");
		
		
		for(int i=1;i<this.loadedData.length;i++) {
			String[] currData = this.loadedData[i];
			if(!this.addConsumable(currData[nameIndex], currData[descIndex], currData[effectIndex], currData[strIndex], currData[typeIndex], currData[gameIndex])) {
				return false;
			}
			
		}
		return true;
	}
	
	public void loadData(File f) {
		try {
	      Scanner myReader = new Scanner(f);
	      ArrayList<String[]> readData = new ArrayList<String[]>();
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String[] toAdd = data.split(",");
	        readData.add(toAdd);
	      }
	      String[][] result= new String[readData.size()][];
	      for(int i=0;i < readData.size();i++) {
	    	  result[i] = readData.get(i);
	      }
	      myReader.close();
	      loadedData = result;
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	
	/*
	 * private methods begin here 
	 */
	
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
	
	private boolean addLocation(String name, String game) {
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
	
	private boolean addDungeon(String name, String game) {
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
			return true;
		}
		return true;
	}
	
	private boolean addNPC(String name, String desc, String game) {
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
	
	private boolean addEnemy(String name, String desc, String spawnAreas, String attacks, String spawnRestrictions, String health, String game) {
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
	
	/*
	 * getters and static methods 
	 */
	
	public String[][] getData() {
		return loadedData;
	}
	
	/**
	 * static method to find a String in a String[]
	 * @param arr
	 * @param s
	 * @return index of String s in arr
	 */
	public static int findIndexOf(String[] arr, String s) {
		for(int i=0;i<arr.length;i++) {
			if(arr[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}
	

}
