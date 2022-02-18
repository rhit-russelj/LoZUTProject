package frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.DatabaseConnectionService;
import services.DropdownService;
import services.LogInService;

public class CreateAccountFrame extends JFrame implements ActionListener {
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private DropdownService ddservice;
	private LogInService liservice;
			
	//User Data
	private static String username;
	private static String password;
		
	//Components
	private JPanel mainPanel;
	private JPanel userInput1;
	private JPanel userInput2;
	private JPanel userInput3;
	private JButton submit;
	private JButton cancel;
	private JTextField newUsername;
	private JTextField newPassword;
	private JTextField newName;
	private JComboBox games;

	public CreateAccountFrame (DatabaseConnectionService dbcs) {
		//Connectivity Set Up
		this.dbcs = dbcs;
		this.ddservice = new DropdownService(this.dbcs);
		this.liservice = new LogInService(this.dbcs);
		if(dbcs.connect("testuser1", "henderae", "a1T20h8e5n14a1")) {
			System.out.println("Connection Success");
		} else {
			System.out.println("Connection Failed");
			return;
		}
		
		//Component Set Up
		this.mainPanel = new JPanel();
		this.userInput1 = new JPanel();
		this.userInput2 = new JPanel();
		this.userInput3 = new JPanel();
		JLabel user = new JLabel("Username: ");
		this.newUsername = new JTextField(20);
		JLabel pass = new JLabel("Password: ");
		this.newPassword = new JTextField(30);
		JLabel name = new JLabel("Name: ");
		this.newName = new JTextField(20);
		JLabel firstGame = new JLabel("First Zelda Game: ");
		this.games = new JComboBox();
		for(String item : this.ddservice.getGames()) {
			this.games.addItem(item);
		}
		this.submit = new JButton("Create Account");
		this.submit.addActionListener(this);
		this.cancel = new JButton("Cancel");
		this.cancel.addActionListener(this);
		
		//Component Combination
		this.add(mainPanel);
		this.mainPanel.add(userInput1);
		this.mainPanel.add(userInput2);
		this.mainPanel.add(userInput3);
		this.userInput1.add(user);
		this.userInput1.add(newUsername);
		this.userInput1.add(pass);
		this.userInput1.add(newPassword);
		this.userInput2.add(name);
		this.userInput2.add(newName);
		this.userInput2.add(firstGame);
		this.userInput2.add(games);
		this.userInput3.add(this.submit);
		this.userInput3.add(this.cancel);
		
		//Formatting
		this.mainPanel.setLayout(new GridLayout(3,2));
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setSize(700, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == this.submit) {
				this.liservice.createUser(this.newUsername.getText(), this.newPassword.getText(), this.newName.getText(), this.games.getSelectedItem().toString());
		    	this.username = this.liservice.getUser();
		    	this.password = this.liservice.getPassword();
		    	this.dbcs.closeConnection();
		    	if(dbcs.connect(this.username, "LegendZ", "Link2Zelda")) {
					System.out.println("Connection Success");
				} else {
					System.out.println("Connection Failed");
					return;
				}
		    	this.dispose();
		    	UserViewFrame uvf = new UserViewFrame(this.dbcs);
			}
			if(e.getSource() == this.cancel) {
				this.dbcs.closeConnection();
				this.dispose();
				StartUpFrame suf = new StartUpFrame();
			}
		}
	}

