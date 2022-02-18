package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import services.DatabaseConnectionService;
import services.UserService;
import services.DropdownService;

public class UserEditFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5920069310876549490L;
	
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private UserService uservice;
	private DropdownService ddservice;
	
	//TODO: Change this
	private String username = "testuser1";
	
	//Components
	private JButton logOutButton;
	private JButton viewButton;
	private JComboBox<String> gameSelect;
	private JComboBox<String> nameSelect;
	private JTextField quantitySelect;
	private JButton confirm;
	private ButtonGroup group;
	
	public UserEditFrame(DatabaseConnectionService dbcs) {
		this.dbcs  = dbcs;
		this.uservice = new UserService(dbcs);
		this.ddservice = new DropdownService(dbcs);
		
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
        navPanel.add(new JLabel("Editing " + this.username + "'s Profile"), labelcons);
        
        GridBagConstraints logoutcons = new GridBagConstraints();
        logoutcons.weightx = 0;
        logoutcons.anchor = GridBagConstraints.EAST;
        logoutcons.insets = new Insets(0,0,0,7); //right margin
        this.logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);
        navPanel.add(this.logOutButton, logoutcons);
        navPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        GridBagConstraints editcons = new GridBagConstraints();
        editcons.weightx = 0;
        editcons.anchor = GridBagConstraints.EAST;
        editcons.insets = new Insets(0,0,0,2); //right margin
        this.viewButton = new JButton("View");
        viewButton.addActionListener(this);
        navPanel.add(this.viewButton, editcons);
        
        this.add(navPanel, BorderLayout.NORTH);
        navPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // now lets define the default size of our window and its layout:
        setPreferredSize(new Dimension(720, 400));     // let's open the window with a default size of 720x400 pixels
        
        // the contentPane is the container that holds all our components
        getContentPane().add(splitPane);               // due to the GridLayout, our splitPane will now fill the whole window

        // let's configure our splitPane:
        splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(250);                    // the initial position of the divider is 250 (our window is 400 pixels high)
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"
        
        bottomPanel.setMinimumSize(new Dimension(470,470));
        topPanel.setMinimumSize(new Dimension(250,250));
        
        //Set up left side
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        group = new ButtonGroup();
        JRadioButton[] radioButtons = {
        		new JRadioButton("Add Owned Game"),
        		new JRadioButton("Add Completed Dungeon"),
        		new JRadioButton("Add Defeated Boss"),
        		new JRadioButton("Add Completed Quest"),
        		new JRadioButton("Add Collected Item"),
           		new JRadioButton("Delete Owned Game"),
        		new JRadioButton("Delete Completed Dungeon"),
        		new JRadioButton("Delete Defeated Boss"),
        		new JRadioButton("Delete Completed Quest"),
        		new JRadioButton("Delete Collected Item"),
        		new JRadioButton("Update Collected Item")
        };

        for(JRadioButton jb : radioButtons) {
        	jb.addActionListener(this);
        	jb.setActionCommand(jb.getText());
        	group.add(jb);
        	topPanel.add(jb);
        }
        
        //Set up right side
        bottomPanel.setLayout(new GridLayout(0,2,50,50));
        JLabel gameLabel = new JLabel("From Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
        bottomPanel.add(gameLabel);
        gameSelect = new JComboBox<String>();
        gameSelect.addActionListener(this);
        bottomPanel.add(gameSelect);
        
        JLabel nameLabel = new JLabel("With Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
        bottomPanel.add(nameLabel);
        nameSelect = new JComboBox<String>();
        nameSelect.addActionListener(this);
        bottomPanel.add(nameSelect);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
        bottomPanel.add(quantityLabel);
        quantitySelect = new JTextField();
        bottomPanel.add(quantitySelect);
        
        bottomPanel.add(new JLabel(""));
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        bottomPanel.add(confirm);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        
		quantitySelect.setText("");
		quantitySelect.setEditable(false);
		nameSelect.setEditable(false);
		
        this.add(splitPane);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		if(arg0.getSource() == this.viewButton) {
			this.dispose();
			UserViewFrame uvf = new UserViewFrame(this.dbcs);
		} else if (arg0.getSource() == this.logOutButton) {
			this.dbcs.closeConnection();
		    this.dispose();
		    StartUpFrame suf = new StartUpFrame();
		} else {
			
			//handle generating game dropdown
			if(arg0.getActionCommand() != null) {
				String command;
				
				if(arg0.getActionCommand().equals(group.getSelection().getActionCommand())) {
					command = arg0.getActionCommand();
					
					if(command.equals("Update Collected Item") || command.equals("Add Collected Item")) {
						quantitySelect.setEditable(true);
					} else {
						quantitySelect.setText("");
						quantitySelect.setEditable(false);
					}
					
					if(command.equals("Add Owned Game") || command.equals("Delete Owned Game")) {
						gameSelect.removeAllItems();
						for(String s: this.ddservice.getGames()) {
							gameSelect.addItem(s);
						}
						nameSelect.setEnabled(false);
					} else {
						gameSelect.removeAllItems();
						for(String s: this.uservice.getUserOwns(this.username)) {
							gameSelect.addItem(s.split(",")[1]);
						}
						//nameSelect.setEnabled(false);
					}
				}
			}
			
			//handle generating name dropdown
			if(arg0.getSource() == this.gameSelect) {
				if(this.gameSelect.getSelectedItem() != null) {
					switch(group.getSelection().getActionCommand()) {
						case "Add Collected Item":
								nameSelect.removeAllItems();
								for(String s: this.ddservice.getItemsOnGame(this.gameSelect.getSelectedItem().toString().trim())) {
									nameSelect.addItem(s);
								}
								nameSelect.setEnabled(true);
							break;
						
						case "Add Completed Quest":
							nameSelect.removeAllItems();
							for(String s: this.ddservice.getQuestsOnGame(this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Add Defeated Boss":
							nameSelect.removeAllItems();
							for(String s: this.ddservice.getBossesOnGame(this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Add Completed Dungeon":
							nameSelect.removeAllItems();
							for(String s: this.ddservice.getDungeonsOnGame(this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Delete Collected Item":
							nameSelect.removeAllItems();
							for(String s: this.uservice.getUserItemsOnGame(this.username, this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Delete Completed Quest":
							nameSelect.removeAllItems();
							for(String s: this.uservice.getUserQuestsOnGame(this.username, this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Delete Defeated Boss":
							nameSelect.removeAllItems();
							for(String s: this.uservice.getUserBossesOnGame(this.username, this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Delete Completed Dungeon":
							nameSelect.removeAllItems();
							for(String s: this.uservice.getUserDungeonsOnGame(this.username, this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
						case "Update Collected Item":
							nameSelect.removeAllItems();
							for(String s: this.uservice.getUserItemsOnGame(this.username, this.gameSelect.getSelectedItem().toString().trim())) {
								nameSelect.addItem(s);
							}
							nameSelect.setEnabled(true);
						break;
						
					}	
				}
			}	
			
			//handle confirm button
			if(arg0.getSource() == this.confirm) {
				String game = this.gameSelect.getSelectedItem().toString().trim();
				String name = this.nameSelect.isEnabled() ? this.nameSelect.getSelectedItem().toString().trim() : null;
				int quantity = this.quantitySelect.isEditable() ? Integer.parseInt(this.quantitySelect.getText()) : -1;
				
				//Here we go again
				switch(group.getSelection().getActionCommand()) {
					case "Add Owned Game":
						this.uservice.addToUserOwns(this.username, game);
					break;
					
					case "Add Collected Item":
						this.uservice.addToUserItems(this.username, game, name, quantity);
					break;
					
					case "Add Completed Quest":
						this.uservice.addToUserQuests(this.username, game, name);
					break;
					
					case "Add Defeated Boss":
						this.uservice.addToUserBosses(this.username, game, name);
					break;
					
					case "Add Completed Dungeon":
						this.uservice.addToUserDungeons(this.username, game, name);
					break;
					
					case "Delete Owned Game":
						this.uservice.deleteFromUserOwns(this.username, game);
					break;
					
					case "Delete Collected Item":
						this.uservice.deleteFromUserItems(this.username, game, name);
					break;
					
					case "Delete Completed Quest":
						this.uservice.deleteFromUserQuests(this.username, game, name);
					break;
					
					case "Delete Defeated Boss":
						this.uservice.deleteFromUserBosses(this.username, game, name);
					break;
					
					case "Delete Completed Dungeon":
						this.uservice.deleteFromUserDungeons(this.username, game, name);
					break;
					
					case "Update Collected Item":
						this.uservice.updateUserItems(this.username, game, name, quantity);
					break;
				
				}
				
			}
		}
	}
}
