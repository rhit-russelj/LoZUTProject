package frames;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import services.DatabaseConnectionService;

public class StartUpFrame extends JFrame implements ActionListener {
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	
	//Components
	private JPanel mainPanel;
	private JButton createAccount;
	private JButton logIn;
	private JButton adminLogIn;
	private JButton anonView;
	
	public StartUpFrame() {
		this.dbcs = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "LoZUTracker");
		
		this.mainPanel = new JPanel();
		this.add(this.mainPanel);
		this.createAccount = new JButton("Create Account");
		this.createAccount.addActionListener(this);
		this.logIn = new JButton("Log-In");
		this.logIn.addActionListener(this);
		this.adminLogIn = new JButton("Admin Log-In");
		this.adminLogIn.addActionListener(this);
		this.anonView = new JButton("View Anonymously");
		this.anonView.addActionListener(this);
		
		//Component Combination
		this.mainPanel.add(this.createAccount);
		this.mainPanel.add(this.logIn);
		this.mainPanel.add(this.adminLogIn);
		this.mainPanel.add(this.anonView);
		
		//Formatting
		GridLayout layout = new GridLayout(1, 4);
		layout.setHgap(20);
		this.mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.mainPanel.setLayout(layout);
		this.setSize(800, 200);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.createAccount) {
			CreateAccountFrame cav = new CreateAccountFrame(this.dbcs);
		    this.dispose();
		} else if(e.getSource() == this.logIn) {
			LogInFrame lif = new LogInFrame(this.dbcs);
			this.dispose();
		} else if(e.getSource() == this.anonView) {
			AnonUserSearchFrame ausf = new AnonUserSearchFrame(this.dbcs);
			this.dispose();
		} else {
			AdminLogInFrame alif = new AdminLogInFrame(this.dbcs);
			this.dispose();
		}
	}
}
