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

public class ViewAllUserChanges {

private DatabaseConnectionService dbService = null;
	
	public ViewAllUserChanges(DatabaseConnectionService dbService) {
		this.dbService = dbService;
	}
	
	
	public boolean userInputtedInformation(String username) {
		String func = "select* from UserInputtedInformation(?)";
		int uid = this.getUserID(username);
		return this.execIDFunc(func, uid);
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
	
	public boolean execIDFunc(String func, int id1) {
		try {
			// Using table-valued parameter with a SQLServerCallableStatement. 			
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(func);
			ResultSet result = cstmt.executeQuery();
			if(result.equals(null)) {
				System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + id1);
				return false;
			}
			 // It creates and displays the table
		    JTable table = new JTable(buildTableModel(result));
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1:" + id1);
			return false;
		}
	}
	
	//Code for following method thanks to Paul Vargas 
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}


	public void showInputsTable(String usern) {
		// TODO Auto-generated method stub
		
	}
}
