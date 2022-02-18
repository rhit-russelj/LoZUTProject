import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import services.AnonymousSearchService;
import services.DatabaseConnectionService;
import services.DropdownService;
import services.UserService;

public class AnonUserSearchFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5920069310876549490L;
	
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private UserService uservice;
	private AnonymousSearchService aservice;
	
	private String username = "testuser1";
	
	//Components
	private JComboBox<String> dropdown;
	private JScrollPane scrollPane = null;
	private JButton userModeButton;
	private JButton viewButton;
	private JButton adminButton;
	
	public AnonUserSearchFrame(DatabaseConnectionService dbcs){
		//Set up connectivity
		this.dbcs = dbcs;
		this.uservice = new UserService(dbcs);
		this.aservice = new AnonymousSearchService(dbcs);
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
        navPanel.add(new JLabel("Anonymous Search"), labelcons);
        
        this.add(navPanel, BorderLayout.NORTH);
        
        setPreferredSize(new Dimension(720, 400));     // let's open the window with a default size of 720x400 pixels
        // the contentPane is the container that holds all our components
        getContentPane().add(splitPane);   
        
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
        
        GridBagConstraints usermodecons = new GridBagConstraints();
        usermodecons.weightx = 0;
        usermodecons.anchor = GridBagConstraints.EAST;
        usermodecons.insets = new Insets(0,0,0,2); //right margin
        this.userModeButton = new JButton("User Mode");
        this.userModeButton.addActionListener(this);
        navPanel.add(this.userModeButton, usermodecons);
        
        GridBagConstraints adminButtoncons = new GridBagConstraints();
        adminButtoncons.weightx = 0;
        adminButtoncons.anchor = GridBagConstraints.EAST;
        adminButtoncons.insets = new Insets(0,0,0,2); //right margin
        this.adminButton = new JButton("Admin Mode");
        this.adminButton.addActionListener(this);
        navPanel.add(this.adminButton, adminButtoncons);
        
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
        
        bottomPanel.setMinimumSize(new Dimension(0,0));
        topPanel.setMinimumSize(new Dimension(0,250));
        
        //Configure button
        this.viewButton = new JButton("View");
        
        viewButton.addActionListener(this);
        
        //Configure dropdown
        dropdown = new JComboBox<String>();
  
        dropdown.addItem("Games");
        dropdown.addItem("Quests");
        dropdown.addItem("Items");
        dropdown.addItem("Locations");
        dropdown.addItem("NPCs");
        
        dropdown.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        
        bottomPanel.add(dropdown, cons);
        bottomPanel.add(viewButton, cons2);
        
        //now that the dropdown works, we can switch the table to the selected option
        this.switchTable();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == this.userModeButton) {
		    this.dispose();
		    UserEditFrame uef = new UserEditFrame(this.dbcs);
		    uef.setVisible(true);
		} else if(arg0.getSource() == this.adminButton) {
			this.dispose();
			AdminEditFrame aef = new AdminEditFrame(this.dbcs);
			aef.setVisible(true);
		} else {
			if(arg0.getSource() == this.viewButton) {
				this.switchTable();
			}
		}
		
	}
	
	private void switchTable() {
		String viewToGet = (String) dropdown.getSelectedItem();
		switch(viewToGet) {
			case "Games":
				this.scrollPane.setViewportView(this.aservice.grabUserJTable("GeneralGameInformation"));
				break;
			case "Quests":
				this.scrollPane.setViewportView(this.aservice.grabUserJTable("GeneralQuestInformation"));
				break;
			case "Items":
				this.scrollPane.setViewportView(this.aservice.grabUserJTable("GeneralItemInformation"));
				break;
			case "Locations":
				this.scrollPane.setViewportView(this.aservice.grabUserJTable("GeneralLocationInformation"));
				break;
			case "NPCs":
				this.scrollPane.setViewportView(this.aservice.grabUserJTable("GeneralNPCInformation"));
				break;
			default:
				return;
		}
	}

}
