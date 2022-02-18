package frames;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import services.DatabaseConnectionService;
import services.LogInService;

public class LogInFrame extends JFrame implements ActionListener {
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private LogInService liservice;
	
	//User Data
	private static String username;
	private static String password;
			
	
	//Components
	private JPanel userInfo;
	private JTextField user;
	private JTextField passw;
	private JButton login;
	private JButton cancel;
	
	public LogInFrame(DatabaseConnectionService dbcs) {
		//Connectivity Set Up
		this.dbcs = dbcs;
		this.liservice = new LogInService(this.dbcs);
		if(dbcs.connect("testuser1", "henderae", "a1T20h8e5n14a1")) {
			System.out.println("Connection Success");
		} else {
			System.out.println("Connection Failed");
			return;
		}
		
		//Component Set Up
		this.userInfo = new JPanel();
		JLabel users = new JLabel("Username: ");
		this.user = new JTextField(15);
		JLabel pass = new JLabel("Password: ");
		this.passw = new JTextField(15);
		this.login = new JButton("Log In");
		this.login.addActionListener(this);
		this.cancel = new JButton("Cancel");
		this.cancel.addActionListener(this);
		
		//Component Combination
		this.add(userInfo);
		this.userInfo.add(users);
		this.userInfo.add(user);
		this.userInfo.add(Box.createRigidArea(new Dimension(10, 0)));
		this.userInfo.add(pass);
		this.userInfo.add(passw);
		this.userInfo.add(Box.createRigidArea(new Dimension(10, 0)));
		this.userInfo.add(login);
		this.userInfo.add(Box.createRigidArea(new Dimension(10, 0)));
		this.userInfo.add(cancel);
		
		//Formatting
		this.userInfo.setLayout(new BoxLayout(this.userInfo, BoxLayout.X_AXIS));
		this.userInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setSize(500, 80);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.cancel) {
			this.dbcs.closeConnection();
			this.dispose();
			StartUpFrame suf = new StartUpFrame();
		} else {
			boolean exists;
			username = this.user.getText();
			password = this.passw.getText();
			exists = this.liservice.FindUser(username, password);
			this.dbcs.closeConnection();
			if(exists) {
				this.dbcs.connect(username, "LegendZ", "Link2Zelda");
				this.dispose();
				UserViewFrame uvf = new UserViewFrame(this.dbcs);
			} else {
				JOptionPane warning = new JOptionPane();
				JOptionPane.showMessageDialog(new LogInFrame(this.dbcs), "Incorrect Log-In Information");
				warning.setVisible(true);
				this.dispose();
			}
		}
	}
}
