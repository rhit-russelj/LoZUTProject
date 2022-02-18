package services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	private String databaseName;
	private String serverName;
	
	private String currentUsername;

	public DatabaseConnectionService(String serverName, String databaseName) {
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String actualUsername, String user, String pass) {
		this.currentUsername = actualUsername;
		String finalURL = SampleURL
							.replace("${dbServer}", this.serverName)
							.replace("${dbName}", this.databaseName)
							.replace("${user}", user)
							.replace("${pass}", pass);
		try {
			this.connection = DriverManager.getConnection(finalURL);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public String getUsername() {
		return this.currentUsername;
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
