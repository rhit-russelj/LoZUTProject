package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import services.DatabaseConnectionService;
import services.UserService;

public class UserViewFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3830232306949427781L;
	
	//Components
	private JComboBox<String> dropdown;
	private JScrollPane scrollPane = null;
	private JButton viewButton;
	private JButton logOut;
	private JButton editButton;
	private JButton viewAllInputsButton;
	
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private UserService uservice = null;
	
	//TODO: Change this
	private String username = "testuser1";
	
	public UserViewFrame(DatabaseConnectionService dbcs){
		//Set up connectivity
		this.dbcs = dbcs;
		this.uservice = new UserService(dbcs);
		//Set up GUI
		//the dropdown looks bad at larger sizes
		this.setResizable(false);
		
        JSplitPane splitPane = new JSplitPane();

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel navPanel = new JPanel();
        
        navPanel.setLayout(new GridBagLayout());
        GridBagConstraints labelcons = new GridBagConstraints();
        labelcons.weightx = 1;
        labelcons.anchor = GridBagConstraints.WEST;
        labelcons.insets = new Insets(0,10,0,0); //left margin
        navPanel.add(new JLabel(this.username + "'s Profile"), labelcons);
        
        GridBagConstraints logOutcons = new GridBagConstraints();
        logOutcons.weightx = 0;
        logOutcons.anchor = GridBagConstraints.EAST;
        logOutcons.insets = new Insets(0,0,0,7); //right margin
        this.logOut = new JButton("Log Out");
        this.logOut.addActionListener(this);
        navPanel.add(this.logOut, logOutcons);
        navPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        GridBagConstraints editcons = new GridBagConstraints();
        editcons.weightx = 0;
        editcons.anchor = GridBagConstraints.EAST;
        editcons.insets = new Insets(0,0,0,2); //right margin
        this.editButton = new JButton("Edit");
        this.editButton.addActionListener(this);
        navPanel.add(this.editButton, editcons);
        navPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        this.add(navPanel, BorderLayout.NORTH);
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // now lets define the default size of our window and its layout:
        setPreferredSize(new Dimension(720, 400));     // let's open the window with a default size of 720x400 pixels
        // the contentPane is the container that holds all our components
        getContentPane().add(splitPane);               // due to the GridLayout, our splitPane will now fill the whole window

        // let's configure our splitPane:
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window vertically
        splitPane.setDividerLocation(250);                    // the initial position of the divider is 250 (our window is 400 pixels high)
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"

        topPanel.setLayout(new GridBagLayout());
        GridBagConstraints tablecons = new GridBagConstraints();
        tablecons.fill = GridBagConstraints.BOTH;
        tablecons.weightx = 1;
        tablecons.weighty = 1;
        tablecons.anchor = GridBagConstraints.NORTH;
        
        this.scrollPane = new JScrollPane();
     
        topPanel.add(scrollPane, tablecons);

        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        bottomPanel.setLayout(new GridBagLayout());
        GridBagConstraints cons = new GridBagConstraints();
        cons.fill = GridBagConstraints.HORIZONTAL;
        cons.weightx = 0.2;
        
        GridBagConstraints cons2 = new GridBagConstraints();
        cons2.fill = GridBagConstraints.HORIZONTAL;
        cons2.weightx = 0.1;
        
        GridBagConstraints cons3 = new GridBagConstraints();
        cons3.fill = GridBagConstraints.HORIZONTAL;
        cons3.weightx = 0.1;
        
        bottomPanel.setMinimumSize(new Dimension(0,0));
        topPanel.setMinimumSize(new Dimension(0,250));
        
        //Configure button
        this.viewButton = new JButton("View");
        this.viewAllInputsButton = new JButton("View all inputs");
        
        viewButton.addActionListener(this);
        viewAllInputsButton.addActionListener(this);
        
        //Configure dropdown
        dropdown = new JComboBox<String>();
  
        dropdown.addItem("Games");
        dropdown.addItem("Quests");
        dropdown.addItem("Items");
        dropdown.addItem("Dungeons");
        dropdown.addItem("Bosses");
        
        dropdown.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        
        bottomPanel.add(dropdown, cons);
        bottomPanel.add(viewButton, cons2);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(viewAllInputsButton, cons3);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //now that the dropdown works, we can switch the table to the selected option
        this.switchTable();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.editButton) {
		    this.dispose();
		    UserEditFrame uef = new UserEditFrame(this.dbcs);
		} else if(arg0.getSource() == this.viewAllInputsButton) {
			this.funcChangeTable();
		} else if(arg0.getSource() == this.logOut) {
		    this.dbcs.closeConnection();
			this.dispose();
		    StartUpFrame suf = new StartUpFrame();
		} else if(arg0.getSource() == this.viewButton) {
				this.switchTable();
		}
	}
			
	
	private void funcChangeTable() {
		this.scrollPane.setViewportView(this.uservice.grabUserJTableFunction("UserInputtedInformation", this.username));
	}

	private void switchTable() {
		String viewToGet = (String) dropdown.getSelectedItem();
		switch(viewToGet) {
			case "Games":
				this.scrollPane.setViewportView(this.uservice.grabUserJTable("UserGames", this.username));
				break;
			case "Quests":
				this.scrollPane.setViewportView(this.uservice.grabUserJTable("UserQuests", this.username));
				break;
			case "Items":
				this.scrollPane.setViewportView(this.uservice.grabUserJTable("UserItems", this.username));
				break;
			case "Dungeons":
				this.scrollPane.setViewportView(this.uservice.grabUserJTable("UserDungeons", this.username));
				break;
			case "Bosses":
				this.scrollPane.setViewportView(this.uservice.grabUserJTable("UserBosses", this.username));
				break;
			default:
				return;
		}
	}
}
