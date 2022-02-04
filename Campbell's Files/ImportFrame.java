import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import services.DatabaseConnectionService;
import services.ImportService;



public class ImportFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3830232306949427781L;
	private JComboBox<String> dropdown;
	private JButton button;
	private JFileChooser fc = new JFileChooser();
	
	private ImportService impservice = null;
	
	public ImportFrame(DatabaseConnectionService dbcs){
		//Set up connectivity
		this.impservice = new ImportService(dbcs);
		//Set up GUI
		//the dropdown looks bad at larger sizes
		this.setResizable(false);
		
        JSplitPane splitPane = new JSplitPane();

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        // in our bottom panel we want the text area and the input components
        JScrollPane scrollPane = new JScrollPane();  // this scrollPane is used to make the text area scrollable

        // now lets define the default size of our window and its layout:
        setPreferredSize(new Dimension(720, 400));     // let's open the window with a default size of 1080x720 pixels
        // the contentPane is the container that holds all our components
        getContentPane().setLayout(new GridLayout());  // the default GridLayout is like a grid with 1 column and 1 row,
        // we only add one element to the window itself
        getContentPane().add(splitPane);               // due to the GridLayout, our splitPane will now fill the whole window

        // let's configure our splitPane:
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window vertically
        splitPane.setDividerLocation(250);                    // the initial position of the divider is 200 (our window is 400 pixels high)
        splitPane.setTopComponent(topPanel);                  // at the top we want our "topPanel"
        splitPane.setBottomComponent(bottomPanel);            // and at the bottom we want our "bottomPanel"

        // our topPanel doesn't need anymore for this example. Whatever you want it to contain, you can add it here
        //bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));

        bottomPanel.add(scrollPane);                // first we add the scrollPane to the bottomPanel, so it is at the top
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
        JButton button = new JButton("Import");
        
        button.addActionListener(this);
        
        //Configure dropdown
        dropdown = new JComboBox<String>();
  
        dropdown.addItem("Games");
        dropdown.addItem("Quests");
        dropdown.addItem("Locations");
        dropdown.addItem("Dungeons");
        dropdown.addItem("NPCs");
        dropdown.addItem("Enemies");
        dropdown.addItem("Bosses");
        
        dropdown.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        
        bottomPanel.add(dropdown, cons);
        bottomPanel.add(button, cons2);
        

        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
			int returnVal = fc.showOpenDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				String selection = (String) dropdown.getSelectedItem();
				
				switch(selection) {
					case "Games":
						this.impservice.addGamesFromFile(file);
						break;
					case "Quests":
						this.impservice.addQuestsFromFile(file);
						break;
					case "Locations":
						this.impservice.addLocationsFromFile(file);
						break;
					case "Dungeons":
						this.impservice.addDungeonsFromFile(file);
						break;
					case "NPCs":
						this.impservice.addNPCSFromFile(file);
						break;
					case "Enemies":
						this.impservice.addEnemiesFromFile(file);
						break;
					case "Bosses":
						this.impservice.addBossesFromFile(file);
						break;
					default:
						//I don't want to throw an error because it shouldn't crash the program
						System.err.println("Invalid dropdown argument");
						break;
						
				}
			} else {
				System.out.println("Operation cancelled by user");
			}
			
	}
}
