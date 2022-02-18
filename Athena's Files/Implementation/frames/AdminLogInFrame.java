package frames;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class AdminLogInFrame extends JFrame implements ActionListener {
	//Connectivity Services
	private DatabaseConnectionService dbcs;

	//User Data
	private static String username;
	private static String password;	
		
	//Components
	private JPanel userInfo;
	private JTextField user;
	private JTextField passw;
	private JButton login;
	private JButton cancel;
	
	public AdminLogInFrame(DatabaseConnectionService dbcs) {
		//Connectivity Set Up
		this.dbcs = dbcs;
		
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
			this.dispose();
			StartUpFrame suf = new StartUpFrame();
		} else {
			username = this.user.getText();
			password = this.passw.getText();
			if(dbcs.connect("testuser1", username, password)) {
				this.dispose();
				AdminEditFrame aef = new AdminEditFrame(this.dbcs);
			} else {
				this.dispose();
				JOptionPane warning = new JOptionPane();
				JOptionPane.showMessageDialog(new LogInFrame(this.dbcs), "Incorrect Log-In Information");
				warning.setVisible(true);
			}
		}
	}

}
