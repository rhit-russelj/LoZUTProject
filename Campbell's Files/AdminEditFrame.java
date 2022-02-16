import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.*;

import services.DatabaseConnectionService;
import services.DropdownService;
import services.AdminService;

public class AdminEditFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7893039059344469013L;
	
	//Connectivity Services
	private DatabaseConnectionService dbcs;
	private DropdownService ddservice;
	private AdminService aservice;

	//Components
	private JButton viewButton;
	private ButtonGroup group;
	private ButtonGroup editModeGroup;
	private JPanel buttonPanel;
	
	private JComboBox<String> deleteDropdown;
	private JComboBox[] updateDropdown = new JComboBox[8];
	private JButton[] confirm = new JButton[21];
	private JTextField textfield1;
	private JTextField textfield2;
	private JTextField textfield3;
	private JTextField textfield4;
	private JTextField textfield5;
	private JTextField textfield6;
	private JTextField textfield7;
	private JTextField textfield8;
	
	//Various Panels
	private JPanel bottomPanel;
	private JPanel[] rightPanels = new JPanel[21];
	
	public AdminEditFrame(DatabaseConnectionService dbcs) {
		this.dbcs  = dbcs;
		this.ddservice = new DropdownService(dbcs);
		this.aservice = new AdminService(dbcs);
		
		this.setResizable(false);
		
	    JSplitPane splitPane = new JSplitPane();
	
	    JPanel topPanel = new JPanel();
	    this.bottomPanel = new JPanel();
		JPanel navPanel = new JPanel();
		
		navPanel.setLayout(new GridBagLayout());
	    GridBagConstraints labelcons = new GridBagConstraints();
	    labelcons.weightx = 1;
	    labelcons.anchor = GridBagConstraints.WEST;
	    labelcons.insets = new Insets(0,10,0,0); //left margin
	    navPanel.add(new JLabel("Editing Database"), labelcons);
	    
	    GridBagConstraints editcons = new GridBagConstraints();
	    editcons.weightx = 0;
	    editcons.anchor = GridBagConstraints.EAST;
	    editcons.insets = new Insets(0,0,0,2); //right margin
	    this.viewButton = new JButton("View");
	    viewButton.addActionListener(this);
	    navPanel.add(this.viewButton, editcons);
	    
	    this.add(navPanel, BorderLayout.NORTH);
	    
	    // now lets define the default size of our window and its layout:
	    setPreferredSize(new Dimension(720, 400));     // let's open the window with a default size of 720x400 pixels
	    
	    // the contentPane is the container that holds all our components
	    getContentPane().add(splitPane);               // due to the GridLayout, our splitPane will now fill the whole window
	
	    // let's configure our splitPane:
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  // we want it to split the window vertically
	    splitPane.setDividerLocation(250);                    // the initial position of the divider is 250 (our window is 400 pixels high)
	    splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
	    splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"
	    
	    bottomPanel.setMinimumSize(new Dimension(470,470));
	    topPanel.setMinimumSize(new Dimension(250,250));
	    
	    //Set up left side
	    topPanel.setLayout(new BorderLayout());
	    
	    JPanel editModePanel = new JPanel();
	    editModePanel.setLayout(new FlowLayout());
	    
	    editModeGroup = new ButtonGroup();
	    
	    JRadioButton a = new JRadioButton("Add");
    	a.addActionListener(this);
    	a.setActionCommand(a.getText());
	    editModeGroup.add(a);
	    editModePanel.add(a);
	    
	    JRadioButton e = new JRadioButton("Update");
    	e.addActionListener(this);
    	e.setActionCommand(e.getText());
	    editModeGroup.add(e);
	    editModePanel.add(e);
	    
	    JRadioButton d = new JRadioButton("Delete");
    	d.addActionListener(this);
    	d.setActionCommand(d.getText());
	    editModeGroup.add(d);
	    editModePanel.add(d);	 
	    
	    editModeGroup.setSelected(a.getModel(), true);
	    
	    editModePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	    editModePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    topPanel.add(editModePanel, BorderLayout.NORTH);
	    
	    this.buttonPanel = new JPanel();
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
	    
	    this.switchButtonSet(0);
	    
	    buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    topPanel.add(buttonPanel);
	    
	    //Make panels for the right side
	    /*
	     * Panel 0
	     */
	    this.rightPanels[0] = new JPanel();
		this.rightPanels[0].setLayout(new GridLayout(0,2,0,0));
        JLabel nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[0].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[0].add(textfield1);
        
        JLabel yearLabel = new JLabel("New Entry Year:");
        yearLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[0].add(yearLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[0].add(textfield2);
        
        JLabel timelineLabel = new JLabel("New Entry Timeline Number:");
        timelineLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[0].add(timelineLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[0].add(textfield3);
        
        JLabel eraLabel = new JLabel("New Entry Era:");
        eraLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[0].add(eraLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[0].add(textfield4);
        
        JLabel systemLabel = new JLabel("New Entry System:");
        systemLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[0].add(systemLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[0].add(textfield5);
        
        this.rightPanels[0].add(new JLabel(""));
        confirm[0] = new JButton("Confirm");
        confirm[0].addActionListener(this);
        this.rightPanels[0].add(confirm[0]);
        this.rightPanels[0].setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        /*
         * Panel 1
         */
	    this.rightPanels[1] = new JPanel();
		this.rightPanels[1].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[1].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[1].add(textfield1);
        
        JLabel gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[1].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[1].add(textfield2);
        
        this.rightPanels[1].add(new JLabel(""));
        confirm[1] = new JButton("Confirm");
        confirm[1].addActionListener(this);
        this.rightPanels[1].add(confirm[1]);
        this.rightPanels[1].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));

        
        /*
         * Panel 2
         */
	    this.rightPanels[2] = new JPanel();
		this.rightPanels[2].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[2].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[2].add(textfield1);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[2].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[2].add(textfield2);
        
        this.rightPanels[2].add(new JLabel(""));
        confirm[2] = new JButton("Confirm");
        confirm[2].addActionListener(this);
        this.rightPanels[2].add(confirm[2]);
        this.rightPanels[2].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
        /*
         * Panel 3
         */
	    this.rightPanels[3] = new JPanel();
		this.rightPanels[3].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[3].add(textfield1);
        
        nameLabel = new JLabel("New Entry Objective:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[3].add(textfield2);
        
        nameLabel = new JLabel("New Entry Storyline:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[3].add(textfield3);
        
        nameLabel = new JLabel("New Entry Is Repeatable:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[3].add(textfield4);
        
        nameLabel = new JLabel("New Entry Gives Item:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[3].add(textfield5);
        
        nameLabel = new JLabel("New Entry Next Quest:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[3].add(textfield6);
        
        nameLabel = new JLabel("New Entry Received From:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield7 = new JTextField();
        textfield7.addActionListener(this);
        this.rightPanels[3].add(textfield7);
        
        nameLabel = new JLabel("New Entry Game:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[3].add(nameLabel);
        textfield8 = new JTextField();
        textfield8.addActionListener(this);
        this.rightPanels[3].add(textfield8);
        
        this.rightPanels[3].add(new JLabel(""));
        confirm[3] = new JButton("Confirm");
        confirm[3].addActionListener(this);
        this.rightPanels[3].add(confirm[3]);
        
        /*
         * Panel 4
         */
	    this.rightPanels[4] = new JPanel();
		this.rightPanels[4].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[4].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[4].add(textfield1);
        
        gameLabel = new JLabel("New Entry Description:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[4].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[4].add(textfield2);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[4].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[4].add(textfield3);
        
        this.rightPanels[4].add(new JLabel(""));
        confirm[4] = new JButton("Confirm");
        confirm[4].addActionListener(this);
        this.rightPanels[4].add(confirm[4]);
        this.rightPanels[4].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
        /*
         * Panel 5
         */
        this.rightPanels[5] = new JPanel();
		this.rightPanels[5].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[5].add(textfield1);
        
        nameLabel = new JLabel("New Entry Description:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[5].add(textfield2);
        
        nameLabel = new JLabel("New Entry Spawn Areas:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[5].add(textfield3);
        
        nameLabel = new JLabel("New Entry Attacks Description:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[5].add(textfield4);
        
        nameLabel = new JLabel("New Entry Spawn Restrictions:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[5].add(textfield5);
        
        nameLabel = new JLabel("New Entry Health:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[5].add(textfield6);
        
        nameLabel = new JLabel("New Entry Game:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[5].add(nameLabel);
        textfield7 = new JTextField();
        textfield7.addActionListener(this);
        this.rightPanels[5].add(textfield7);
        
        this.rightPanels[5].add(new JLabel(""));
        confirm[5] = new JButton("Confirm");
        confirm[5].addActionListener(this);
        this.rightPanels[5].add(confirm[5]);
        
        /*
         * Panel 6
         */
        this.rightPanels[6] = new JPanel();
		this.rightPanels[6].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[6].add(textfield1);
        
        nameLabel = new JLabel("New Entry Description:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[6].add(textfield2);
        
        nameLabel = new JLabel("New Entry Spawn Areas:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[6].add(textfield3);
        
        nameLabel = new JLabel("New Entry Attacks Description:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[6].add(textfield4);
        
        nameLabel = new JLabel("New Entry Spawn Restrictions:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[6].add(textfield5);
        
        nameLabel = new JLabel("New Entry Health:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[6].add(textfield6);
        
        nameLabel = new JLabel("New Entry Game:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield7 = new JTextField();
        textfield7.addActionListener(this);
        this.rightPanels[6].add(textfield7);
        
        nameLabel = new JLabel("New Entry Dungeon:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[6].add(nameLabel);
        textfield8 = new JTextField();
        textfield8.addActionListener(this);
        this.rightPanels[6].add(textfield8);
        
        this.rightPanels[6].add(new JLabel(""));
        confirm[6] = new JButton("Confirm");
        confirm[6].addActionListener(this);
        this.rightPanels[6].add(confirm[6]);
        
        /*
         * Panel 7
         */
	    this.rightPanels[7] = new JPanel();
		this.rightPanels[7].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[7].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[7].add(textfield1);
        
        gameLabel = new JLabel("New Entry Description:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[7].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[7].add(textfield2);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[7].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[7].add(textfield3);
        
        this.rightPanels[7].add(new JLabel(""));
        confirm[7] = new JButton("Confirm");
        confirm[7].addActionListener(this);
        this.rightPanels[7].add(confirm[7]);
        this.rightPanels[7].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
        /*
         * Panel 8
         */
	    this.rightPanels[8] = new JPanel();
		this.rightPanels[8].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Color:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[8].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[8].add(textfield1);
        
        gameLabel = new JLabel("New Entry Value:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[8].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[8].add(textfield2);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[8].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[8].add(textfield3);
        
        this.rightPanels[8].add(new JLabel(""));
        confirm[8] = new JButton("Confirm");
        confirm[8].addActionListener(this);
        this.rightPanels[8].add(confirm[8]);
        this.rightPanels[8].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
        /*
         * Panel 9
         */
        this.rightPanels[9] = new JPanel();
		this.rightPanels[9].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[9].add(textfield1);
        
        nameLabel = new JLabel("New Entry Description:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[9].add(textfield2);
        
        nameLabel = new JLabel("New Entry Effect:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[9].add(textfield3);
        
        nameLabel = new JLabel("New Entry Strength:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[9].add(textfield4);
        
        nameLabel = new JLabel("New Entry Type:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[9].add(textfield5);
        
        nameLabel = new JLabel("New Entry Game:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[9].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[9].add(textfield6);
        
        this.rightPanels[9].add(new JLabel(""));
        confirm[9] = new JButton("Confirm");
        confirm[9].addActionListener(this);
        this.rightPanels[9].add(confirm[9]);
        
        /*
         * Panel 10
         */
	    this.rightPanels[10] = new JPanel();
		this.rightPanels[10].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        updateDropdown[0] = new JComboBox<String>();
        updateDropdown[0].addActionListener(this);
        this.rightPanels[10].add(updateDropdown[0]);
        
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[10].add(textfield2);
        
        nameLabel = new JLabel("New Entry Year:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[10].add(textfield3);
        
        nameLabel = new JLabel("New Entry Era:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[10].add(textfield4);
        
        nameLabel = new JLabel("New Entry Timeline Number:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[10].add(textfield5);
        
        nameLabel = new JLabel("New Entry System:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[10].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[10].add(textfield6);
        
        this.rightPanels[10].add(new JLabel(""));
        confirm[10] = new JButton("Confirm");
        confirm[10].addActionListener(this);
        this.rightPanels[10].add(confirm[10]);
        this.rightPanels[10].setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        
        /*
         * Panel 11
         */
	    this.rightPanels[11] = new JPanel();
		this.rightPanels[11].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[11].add(nameLabel);
        updateDropdown[1] = new JComboBox<String>();
        updateDropdown[1].addActionListener(this);
        this.rightPanels[11].add(updateDropdown[1]);
        
        gameLabel = new JLabel("New Entry Name:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[11].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[11].add(textfield2);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[11].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[11].add(textfield3);
        
        this.rightPanels[11].add(new JLabel(""));
        confirm[11] = new JButton("Confirm");
        confirm[11].addActionListener(this);
        this.rightPanels[11].add(confirm[11]);
        this.rightPanels[11].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
        /*
         * Panel 12
         */
        //Unused
        this.rightPanels[12] = new JPanel();
        
        /*
         * Panel 13
         */
	    this.rightPanels[13] = new JPanel();
		this.rightPanels[13].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        updateDropdown[2] = new JComboBox<String>();
        updateDropdown[2].addActionListener(this);
        this.rightPanels[13].add(updateDropdown[2]);
		
        nameLabel = new JLabel("New Entry Name:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield1 = new JTextField();
        textfield1.addActionListener(this);
        this.rightPanels[13].add(textfield1);
        
        nameLabel = new JLabel("New Entry Objective:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[13].add(textfield2);
        
        nameLabel = new JLabel("New Entry Storyline:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[13].add(textfield3);
        
        nameLabel = new JLabel("New Entry Is Repeatable:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[13].add(textfield4);
        
        nameLabel = new JLabel("New Entry Gives Item:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[13].add(textfield5);
        
        nameLabel = new JLabel("New Entry Next Quest:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield6 = new JTextField();
        textfield6.addActionListener(this);
        this.rightPanels[13].add(textfield6);
        
        nameLabel = new JLabel("New Entry Received From:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield7 = new JTextField();
        textfield7.addActionListener(this);
        this.rightPanels[13].add(textfield7);
        
        nameLabel = new JLabel("New Entry Game:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[13].add(nameLabel);
        textfield8 = new JTextField();
        textfield8.addActionListener(this);
        this.rightPanels[13].add(textfield8);
        
        this.rightPanels[13].add(new JLabel(""));
        confirm[13] = new JButton("Confirm");
        confirm[13].addActionListener(this);
        this.rightPanels[13].add(confirm[13]);
        
        /*
         * Panel 14
         */
	    this.rightPanels[14] = new JPanel();
		this.rightPanels[14].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[14].add(nameLabel);
        updateDropdown[3] = new JComboBox<String>();
        updateDropdown[3].addActionListener(this);
        this.rightPanels[14].add(updateDropdown[3]);
        
        gameLabel = new JLabel("New Entry Name:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[14].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[14].add(textfield2);
        
        gameLabel = new JLabel("New Entry Description:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[14].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[14].add(textfield3);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[14].add(gameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[14].add(textfield4);
        
        this.rightPanels[14].add(new JLabel(""));
        confirm[14] = new JButton("Confirm");
        confirm[14].addActionListener(this);
        this.rightPanels[14].add(confirm[14]);
        this.rightPanels[14].setBorder(BorderFactory.createEmptyBorder(75, 0, 75, 0));
        
        /*
         * Panel 15
         */
	    this.rightPanels[15] = new JPanel();
		this.rightPanels[15].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[15].add(nameLabel);
        updateDropdown[4] = new JComboBox<String>();
        updateDropdown[4].addActionListener(this);
        this.rightPanels[15].add(updateDropdown[4]);
        
        gameLabel = new JLabel("New Entry Spawn Areas:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[15].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[15].add(textfield2);
        
        gameLabel = new JLabel("New Entry Spawn Restrictions:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[15].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[15].add(textfield3);
        
        gameLabel = new JLabel("New Entry Health:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[15].add(gameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[15].add(textfield4);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        this.rightPanels[15].add(gameLabel);
        textfield5 = new JTextField();
        textfield5.addActionListener(this);
        this.rightPanels[15].add(textfield5);
        
        this.rightPanels[15].add(new JLabel(""));
        confirm[15] = new JButton("Confirm");
        confirm[15].addActionListener(this);
        this.rightPanels[15].add(confirm[15]);
        this.rightPanels[15].setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));
        
        /*
         * Panel 16
         */
        //Unused
        this.rightPanels[16] = new JPanel();
        
        /*
         * Panel 17
         */
	    this.rightPanels[17] = new JPanel();
		this.rightPanels[17].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[17].add(nameLabel);
        updateDropdown[5] = new JComboBox<String>();
        updateDropdown[5].addActionListener(this);
        this.rightPanels[17].add(updateDropdown[5]);
        
        gameLabel = new JLabel("New Entry Name:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[17].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[17].add(textfield2);
        
        gameLabel = new JLabel("New Entry Description:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[17].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[17].add(textfield3);
        
        gameLabel = new JLabel("New Entry Game:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[17].add(gameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[17].add(textfield4);
        
        this.rightPanels[17].add(new JLabel(""));
        confirm[17] = new JButton("Confirm");
        confirm[17].addActionListener(this);
        this.rightPanels[17].add(confirm[17]);
        this.rightPanels[17].setBorder(BorderFactory.createEmptyBorder(75, 0, 75, 0));
        
        /*
         * Panel 18
         */
	    this.rightPanels[18] = new JPanel();
		this.rightPanels[18].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[18].add(nameLabel);
        updateDropdown[6] = new JComboBox<String>();
        updateDropdown[6].addActionListener(this);
        this.rightPanels[18].add(updateDropdown[6]);
        
        gameLabel = new JLabel("New Entry Effect:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[18].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[18].add(textfield2);
        
        gameLabel = new JLabel("New Entry Strength:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[18].add(gameLabel);
        textfield3 = new JTextField();
        textfield3.addActionListener(this);
        this.rightPanels[18].add(textfield3);
        
        gameLabel = new JLabel("New Entry Type:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[18].add(gameLabel);
        textfield4 = new JTextField();
        textfield4.addActionListener(this);
        this.rightPanels[18].add(textfield4);
        
        this.rightPanels[18].add(new JLabel(""));
        confirm[18] = new JButton("Confirm");
        confirm[18].addActionListener(this);
        this.rightPanels[18].add(confirm[18]);
        this.rightPanels[18].setBorder(BorderFactory.createEmptyBorder(75, 0, 75, 0));
        
        /*
         * Panel 19
         */
	    this.rightPanels[19] = new JPanel();
		this.rightPanels[19].setLayout(new GridLayout(0,2,0,0));
        nameLabel = new JLabel("Update Entry:");
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[19].add(nameLabel);
        updateDropdown[7] = new JComboBox<String>();
        updateDropdown[7].addActionListener(this);
        this.rightPanels[19].add(updateDropdown[7]);
        
        gameLabel = new JLabel("New Entry Value:");
        gameLabel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
        this.rightPanels[19].add(gameLabel);
        textfield2 = new JTextField();
        textfield2.addActionListener(this);
        this.rightPanels[19].add(textfield2);
        
        this.rightPanels[19].add(new JLabel(""));
        confirm[19] = new JButton("Confirm");
        confirm[19].addActionListener(this);
        this.rightPanels[19].add(confirm[19]);
        this.rightPanels[19].setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
        
	    /*
	     * Panel 20
	     */
		this.rightPanels[20] = new JPanel();
		this.rightPanels[20].setLayout(new GridLayout(0,2,0,100));
        JLabel deleteLabel = new JLabel("Delete Entry:");
        deleteLabel.setBorder(BorderFactory.createEmptyBorder(0, 75, 0, 0));
        this.rightPanels[20].add(deleteLabel);
        deleteDropdown = new JComboBox<String>();
        deleteDropdown.addActionListener(this);
        this.rightPanels[20].add(deleteDropdown);
        
        this.rightPanels[20].add(new JLabel(""));
        confirm[20] = new JButton("Confirm");
        confirm[20].addActionListener(this);
        this.rightPanels[20].add(confirm[20]);
        this.rightPanels[20].setBorder(BorderFactory.createEmptyBorder(75, 0, 75, 0));
        
        this.addItemsToUpdateDropdowns();
        bottomPanel.setLayout(new BorderLayout());
		
	    this.add(splitPane);
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand() != null) {
			String command;
			
			if(arg0.getActionCommand().equals(editModeGroup.getSelection().getActionCommand())) {
				command = arg0.getActionCommand();
			
				if(command.equals("Add")) {
					this.switchButtonSet(0);
				} else if (command.equals("Update")){
					this.switchButtonSet(1);
				} else if (command.equals("Delete")){
					this.switchButtonSet(2);
				}
				
			}
			else if(group.getSelection() == null) {
				return;
			}
			else if(arg0.getActionCommand().equals(group.getSelection().getActionCommand())) {
				command = arg0.getActionCommand();
				
				switch (command){
					case "Add Game":
						setRightPanel(0);
						break;
					case "Add Location":
						setRightPanel(1);
						break;
					case "Add Dungeon":
						setRightPanel(2);
						break;
					case "Add Quest":
						setRightPanel(3);
						break;
					case "Add NPC":
						setRightPanel(4);
						break;
					case "Add Enemy":
						setRightPanel(5);
						break;
					case "Add Boss":
						setRightPanel(6);
						break;
					case "Add Item":
						setRightPanel(7);
						break;
					case "Add Consumable":
						setRightPanel(9);
						break;
					case "Add Rupee":
						setRightPanel(8);
						break;
						
					case "Update Game":
						setRightPanel(10);
						break;
					case "Update Location":
						setRightPanel(11);
						break;
					case "Update Quest":
						setRightPanel(13);
						break;
					case "Update NPC":
						setRightPanel(14);
						break;
					case "Update Enemy":
						setRightPanel(15);
						break;
					case "Update Item":
						setRightPanel(17);
						break;
					case "Update Consumable":
						setRightPanel(18);
						break;
					case "Update Rupee":
						setRightPanel(19);
						break;
					
					case "Delete Game":
						setRightPanel(20);
						break;	
					case "Delete Location":
						setRightPanel(21);
						break;	
					case "Delete Dungeon":
						setRightPanel(22);
						break;	
					case "Delete Quest":
						setRightPanel(23);
						break;	
					case "Delete NPC":
						setRightPanel(24);
						break;	
					case "Delete Enemy":
						setRightPanel(25);
						break;	
					case "Delete Boss":
						setRightPanel(26);
						break;	
					case "Delete Item":
						setRightPanel(27);
						break;	
					case "Delete Consumable":
						setRightPanel(28);
						break;	
					case "Delete Rupee":
						setRightPanel(29);
						break;	
					default:
						setRightPanel(-1);
						break;
						
				}
			}
		}
		
		//I should have not hard-coded all the panels. This is super janky code.
		for(int i=0;i<this.confirm.length;i++) {
			if(arg0.getSource() == this.confirm[i]) {
				if(i < 20) {
					//We can make our select based off i
					String[] args = new String[8];
					int offset = (i < 10) ? 1 : 3;
					for(int compNum=0;compNum<this.rightPanels[i].getComponentCount();compNum++) {
						try {
							args[compNum] = ((JTextField)this.rightPanels[i].getComponent((compNum*2)+offset)).getText();
						} catch (ClassCastException e){
							//We are hitting the 'confirm' button so it's fine to break
							break;
						}
					}
					for(String s: args) {
						System.out.println(s);
					}
					switch (i) {
						case 0:
							//add game
							this.aservice.addGame(args[0], args[1], args[2], args[3], args[4]);
							break;
						case 1:
							//add location
							this.aservice.addLocation(args[0], args[1]);
							break;
						case 2:
							//add dungeon
							this.aservice.addDungeon(args[0], args[1]);
							break;
						case 3:
							//add quest
							this.aservice.addQuest(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
							break;
						case 4:
							//add npc
							this.aservice.addNPC(args[0], args[1], args[2]);
							break;
						case 5:
							//add enemy
							this.aservice.addEnemy(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
							break;
						case 6:
							//add boss
							this.aservice.addBoss(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
							break;
						case 7:
							//add item
							this.aservice.addItem(args[0], args[1], args[2]);
							break;
						case 8:
							//add rupee
							this.aservice.addRupee(args[0], args[1], args[2]);
							break;
						case 9:
							//add consumable
							this.aservice.addConsumable(args[0], args[1], args[2], args[3], args[4], args[5]);
							break;
						case 10:
							//update game
							String id = this.parseID((String) this.updateDropdown[0].getSelectedItem());
							this.aservice.updateGame(id, args[0], args[1], args[2], args[3], args[4]);
							break;
						case 11:
							//update location
							String id2 = this.parseID((String) this.updateDropdown[1].getSelectedItem());
							this.aservice.updateLocation(id2, args[0], args[1]);
							break;
						case 13:
							//update quest
							String id3 = this.parseID((String) this.updateDropdown[2].getSelectedItem());
							this.aservice.updateQuest(id3, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
							break;
						case 14:
							//update npc
							String id4 = this.parseID((String) this.updateDropdown[3].getSelectedItem());
							this.aservice.updateNPC(id4, args[0], args[1], args[2]);
							break;
						case 15:
							//update enemy
							String id5 = this.parseID((String) this.updateDropdown[4].getSelectedItem());
							this.aservice.updateEnemy(id5, args[0], args[1], args[2], args[3]);
							break;
						case 17:
							//update item
							String id6 = this.parseID((String) this.updateDropdown[5].getSelectedItem());
							this.aservice.updateItem(id6, args[0], args[1], args[2]);
							break;
						case 18:
							//update consumable
							String id7 = this.parseID((String) this.updateDropdown[6].getSelectedItem());
							this.aservice.updateConsumable(id7, args[0], args[1], args[2]);
							break;
						case 19:
							//update rupee
							String id8 = this.parseID((String) this.updateDropdown[7].getSelectedItem());
							this.aservice.updateRupee(id8, args[0]);
							break;
						default:
					}
				} else {
					//We have to make our selection based off the radio buttons
					String command = group.getSelection().getActionCommand();
					String id = this.parseID((String) this.deleteDropdown.getSelectedItem());
					switch (command){
						case "Delete Game":
							this.aservice.deleteGame(id);
							break;	
						case "Delete Location":
							this.aservice.deleteLocation(id);
							break;	
						case "Delete Dungeon":
							this.aservice.deleteDungeon(id);
							break;	
						case "Delete Quest":
							this.aservice.deleteQuest(id);
							break;	
						case "Delete NPC":
							this.aservice.deleteNPC(id);
							break;	
						case "Delete Enemy":
							this.aservice.deleteEnemy(id);
							break;	
						case "Delete Boss":
							this.aservice.deleteBoss(id);
							break;	
						case "Delete Item":
							this.aservice.deleteItem(id);
							break;	
						case "Delete Consumable":
							this.aservice.deleteConsumable(id);
							break;	
						case "Delete Rupee":
							this.aservice.deleteRupee(id);
							break;	
						default:
							break;
					}
				}
			}
		}
		
	}
	
	/**
	 * 
	 */
	private void setRightPanel(int num) {
		bottomPanel.removeAll();
		if(num >= 20) {
	        bottomPanel.add(this.rightPanels[20]);
	        this.deleteDropdown.removeAllItems();
	        //remake delete dropdown
	        switch (num - 20) {
	        	case 0:
	        		for(String s:this.ddservice.getRawGames()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 1:
	        		for(String s:this.ddservice.getRawLocations()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 2:
	        		for(String s:this.ddservice.getRawDungeons()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 3:
	        		for(String s:this.ddservice.getRawQuests()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 4:
	        		for(String s:this.ddservice.getRawNPCs()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 5:
	        		for(String s:this.ddservice.getRawEnemies()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 6:
	        		for(String s:this.ddservice.getRawBosses()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 7:
	        		for(String s:this.ddservice.getRawItems()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        	case 8:
	        		for(String s:this.ddservice.getRawConsumables()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	case 9:
	        		for(String s:this.ddservice.getRawRupees()) {
	        			this.deleteDropdown.addItem(s);
	        		}
	        		break;
	        	default:
	        		break;
	        }
	        	
		} else if (num == -1){
			System.err.println("Invalid panel index");
		} else {
			//Refreshes update dropdowns
			this.addItemsToUpdateDropdowns();
	        bottomPanel.add(this.rightPanels[num]);
		}
	    bottomPanel.revalidate();
	    bottomPanel.repaint();
	}
	
	/**
	 * 
	 * @param type: 0 -> add, 1 -> update, 2 -> delete
	 */
	private void switchButtonSet(int type) {
		

	    JRadioButton[] addRadioButtons = {
	    		//Add
	    		new JRadioButton("Add Game"),
	    		new JRadioButton("Add Location"),
	    		new JRadioButton("Add Dungeon"),
	    		new JRadioButton("Add Quest"),
	    		new JRadioButton("Add NPC"),
	    		new JRadioButton("Add Enemy"),
	    		new JRadioButton("Add Boss"),
	    		new JRadioButton("Add Item"),
	    		new JRadioButton("Add Consumable"),
	    		new JRadioButton("Add Rupee")

	    };
	    
	    JRadioButton[] updateRadioButtons = {
	    		//Update
	    		//what to do with dungeon and boss?
	    		new JRadioButton("Update Game"),
	    		new JRadioButton("Update Location"),
	    		//new JRadioButton("Update Dungeon"),
	    		new JRadioButton("Update Quest"),
	    		new JRadioButton("Update NPC"),
	    		new JRadioButton("Update Enemy"),
	    		//new JRadioButton("Update Boss"),
	    		new JRadioButton("Update Item"),
	    		new JRadioButton("Update Consumable"),
	    		new JRadioButton("Update Rupee")
	    };
	    
	    JRadioButton[] deleteRadioButtons = {
	    		//Delete
	    		new JRadioButton("Delete Game"),
	    		new JRadioButton("Delete Location"),
	    		new JRadioButton("Delete Dungeon"),
	    		new JRadioButton("Delete Quest"),
	    		new JRadioButton("Delete NPC"),
	    		new JRadioButton("Delete Enemy"),
	    		new JRadioButton("Delete Boss"),
	    		new JRadioButton("Delete Item"),
	    		new JRadioButton("Delete Consumable"),
	    		new JRadioButton("Delete Rupee")
	    };
	    
	    group = new ButtonGroup();
	    buttonPanel.removeAll();
	    switch(type) {
		    case 0:
			    for(JRadioButton jb : addRadioButtons) {
			    	jb.addActionListener(this);
			    	jb.setActionCommand(jb.getText());
			    	group.add(jb);
			    	buttonPanel.add(jb);
			    }
			    break;
		    case 1:
			    for(JRadioButton jb : updateRadioButtons) {
			    	jb.addActionListener(this);
			    	jb.setActionCommand(jb.getText());
			    	group.add(jb);
			    	buttonPanel.add(jb);
			    }
			    break;
		    case 2:
			    for(JRadioButton jb : deleteRadioButtons) {
			    	jb.addActionListener(this);
			    	jb.setActionCommand(jb.getText());
			    	group.add(jb);
			    	buttonPanel.add(jb);
			    }
			    break;

	    }
	    buttonPanel.revalidate();
	    buttonPanel.repaint();
	}
	
	private void addItemsToUpdateDropdowns() {
		for(int i=0;i<this.updateDropdown.length;i++) {
			this.updateDropdown[i].removeAllItems();
	        switch (i) {
	        	case 0:
	        		for(String s:this.ddservice.getRawGames()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 1:
	        		for(String s:this.ddservice.getRawLocations()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 2:
	        		for(String s:this.ddservice.getRawQuests()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 3:
	        		for(String s:this.ddservice.getRawNPCs()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 4:
	        		for(String s:this.ddservice.getRawEnemies()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 5:
	        		for(String s:this.ddservice.getRawItems()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        	case 6:
	        		for(String s:this.ddservice.getRawConsumables()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	case 7:
	        		for(String s:this.ddservice.getRawRupees()) {
	        			this.updateDropdown[i].addItem(s);
	        		}
	        		break;
	        	default:
	        		break;
	        }
        	
        }
	}
	
	private String parseID(String s) {
		return s.split(",")[0];
	}
}
