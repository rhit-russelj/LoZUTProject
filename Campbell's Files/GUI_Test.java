import services.DatabaseConnectionService;

public class GUI_Test {
	public static void main(String[] args) {
		String servername = "titan.csse.rose-hulman.edu";
		String dbname = "LoZUTracker";
		DatabaseConnectionService dbcs = new DatabaseConnectionService(servername, dbname);
		
		String user = "garvinac";
		String pass = "sequelsql94";
		if(dbcs.connect("testuser1", user, pass)) {
			System.out.println("connection success");
		} else {
			System.out.println("connection failed");
			return;
		}
		
		//new UserViewFrame(dbcs);
		//new UserEditFrame(dbcs);
		new AnonUserSearchFrame(dbcs);
		
	}
		
}
