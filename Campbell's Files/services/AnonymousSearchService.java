package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;

public class AnonymousSearchService {

	private DatabaseConnectionService dbService = null;
	
	public AnonymousSearchService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public ArrayList<String> getGames(){
		return this.execUserQuery("SELECT * FROM GeneralGameInformation");
	}
	
	public ArrayList<String> getQuests(){
		return this.execUserQuery("SELECT * FROM GeneralQuestInformation");
	}
	
	public ArrayList<String> execUserQuery(String query){
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			result = this.formatResultSet(rs, query);
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
		
	}
	
	public ArrayList<String> formatResultSet(ResultSet rs, String query){
		ArrayList<String> result = new ArrayList<String>();
		try {
			if(query.contains("GeneralGameInformation")) {
				while(rs.next()) {
					result.add(rs.getString("Name") + ", " +
							rs.getString("System") + ", " +
							rs.getString("PublishYear") + ", " + 
							rs.getString("TimelineEra") + ", " +
							rs.getString("TimelineNumber"));
				}
			} else if(query.contains("GeneralQuestInformation")) {
				while(rs.next()) {
					result.add(rs.getString("Name") + ", " +
							rs.getString("System") + ", " +
							rs.getString("PublishYear") + ", " + 
							rs.getString("TimelineEra") + ", " +
							rs.getString("TimelineNumber"));
				}
			} else {
				System.err.println("Invalid query in AnonymousSearchService!");
			}
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
	
	
	public JTable grabUserJTable(String viewName) {
		//grab data
		ArrayList<String []> tempdata = new ArrayList<String []>();
		String query = "SELECT * FROM " + viewName;
		PreparedStatement stmt;
		
		//uninitialized for additional error handling
		int rownum = -1;
		switch(viewName) {
			case "GeneralGameInformation":
				rownum = 5;
				break;
			case "GeneralQuestInformation":
				rownum = 4;
				break;
			default:
				//
				return null;
		}
		
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
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
			System.err.println("Something went wrong getting the search views data!");
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
			case "GeneralGameInformation":
				String[] temp1 = {"Game Name", "System", "Publish Year", "Timeline Era", "Timeline Order"};
				columnNames = temp1;
				break;
			case "GeneralQuestInformation":
				String[] temp2 = {"Quest Name", "Objective", "Storyline", "Game Name"};
				columnNames = temp2;
				break;
			default:
				break;
		}
		//create and return JTable
		return new JTable(data, columnNames);
	}
	
}//get names of tables to add to drop down on bottom
//Make a view for the tables and pull from there
//Put all stuff in JTable with column names (manual) and get data from Server using a view or table
