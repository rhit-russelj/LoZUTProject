import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import services.DatabaseConnectionService;

public class ViewAllUserChanges {

private DatabaseConnectionService dbService = null;
	
	public ViewAllUserChanges(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	public int getUserID(String username) {
		String query = "SELECT ID FROM [User] \n WHERE Username=?";
		return this.execIDQuery(query, username);
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

	public void showInputsTable(String usern) {
		// TODO Auto-generated method stub
		
	}
}
