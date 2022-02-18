package services;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class LogInService {
	
	private DatabaseConnectionService dbService = null;
	private static String user;
	private static String password;
	
	public LogInService(DatabaseConnectionService dbService) {
		this.dbService = dbService;
		this.user = null;
	}
	
	public static String getPassword() {
		return password;
	}
	
	public static String getUser() {
		return user;
	}
	
	public boolean createUser(String user, String pass, String name, String game) {
		String proc = "{? = call AddUser(?,?,?,?,?)}";
		try {
			CallableStatement cstmt = this.dbService.getConnection().prepareCall(proc);
			cstmt.setString(2, user);
			cstmt.setString(3, pass);
			cstmt.setString(4, name);
			cstmt.setString(5, game);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();
			int result = cstmt.getInt(1);
			this.user = cstmt.getString(6);
			this.password = pass;
			if(result > 0) {
				System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1: " + user + " ID2: " + pass);
				return false;
			}
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("ERROR: Something went wrong in executing the stored procedure! ID1: " + user + " ID2: " + pass);
			return false;
		}
	}
	
	public boolean FindUser(String user, String pass) {
		String query = "SELECT * FROM [User] WHERE Username=? AND PasswordHash=?;";
		ArrayList<String> result = new ArrayList<String>();
		PreparedStatement stmt;
		try {
			stmt = this.dbService.getConnection().prepareStatement(query);
			stmt.setString(1, user);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			result = this.formatResultSet(rs, query);
			stmt.close();
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return !result.isEmpty();
	}
	
	public ArrayList<String> formatResultSet(ResultSet rs, String query){
		ArrayList<String> result = new ArrayList<String>();
		try {
			while(rs.next()) {
				result.add(rs.getString("Username") + ", " + rs.getString("PasswordHash"));
			}
		} catch (SQLException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}
}
