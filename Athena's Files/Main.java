import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main {
	
	public static void main(String args[]) {
// Log-In Page
//		JFrame home = new JFrame();
//		home.setSize(800, 800);
//		JPanel userInfo = new JPanel();
//		JLabel user = new JLabel();
//		user.setText("Username:");
//		JTextField username = new JTextField(15);
//		JLabel pass = new JLabel();
//		pass.setText("Password:");
//		JTextField password = new JTextField(15);
//		JButton logIn = new JButton("Log In");
//		logIn.addActionListener(e -> {
//			System.out.println("Hi");
//		});
//		home.add(userInfo);
//		userInfo.add(user);
//		userInfo.add(username);
//		userInfo.add(pass);
//		userInfo.add(password);
//		userInfo.add(logIn);
//		home.show();
		
// User Account Page
		DatabaseConnectionService dcs = new DatabaseConnectionService("titan.csse.rose-hulman.edu", "LoZUTracker");
		dcs.connect("henderae", "a1T20h8e5n14a1");
		
		String usern = "user1";
		UserService us = new UserService(dcs);
		DropdownService ds = new DropdownService(dcs);

		JFrame account = new JFrame();
		account.setSize(800, 800);
		//User Info
		JPanel user = new JPanel();
		JLabel username = new JLabel();
		username.setText(usern);
		account.add(user, BorderLayout.NORTH);
		user.add(username);
		//Data Selection
		JPanel optionsBIG = new JPanel();
		JPanel options = new JPanel();
		JLabel games = new JLabel("Game:");
		JLabel bosses = new JLabel("Boss:");
		JComboBox game = new JComboBox();
		for(String item : ds.getGames()) {
			game.addItem(item);
		}
		JComboBox boss = new JComboBox();
		for(String item : ds.getBosses()) {
			boss.addItem(item);
		}
		optionsBIG.add(options);
		options.add(games);
		options.add(game);
		options.add(bosses);
		options.add(boss);
		account.add(optionsBIG,BorderLayout.CENTER);
		//Add Data
		JPanel addGame = new JPanel();
		JLabel newGame = new JLabel("Game:");
		JComboBox gameSelect = new JComboBox();
		for(String item : ds.getGames()) {
			gameSelect.addItem(item);
		}
		JButton addUserGame = new JButton("Add This To My Collection");
		addUserGame.addActionListener(e -> {
			us.addToUserOwns(usern, gameSelect.getSelectedItem().toString());
		});
		addGame.add(newGame);
		addGame.add(gameSelect);
		addGame.add(addUserGame);
		optionsBIG.add(addGame);
		//Delete Data
		JPanel deleteGame = new JPanel();
		JLabel ewGame = new JLabel("Game:");
		JComboBox gameSelect2 = new JComboBox();
		for(String item : ds.getGames()) {
			gameSelect2.addItem(item);
		}
		JButton removeUserGame = new JButton("Remove This From My Collection");
		removeUserGame.addActionListener(e -> {
			us.deleteFromUserOwns(usern, gameSelect2.getSelectedItem().toString());
		});
		deleteGame.add(ewGame);
		deleteGame.add(gameSelect2);
		deleteGame.add(removeUserGame);
		optionsBIG.add(deleteGame);
		//Update Items
		JPanel updateItem = new JPanel();
		JLabel item = new JLabel("Item:");
		JComboBox itemSelect = new JComboBox();
		for(String items : ds.getItems()) {
			itemSelect.addItem(items);
		}
		JLabel quant = new JLabel("New Quantity:");
		JTextField quantity = new JTextField(5);
		JButton changeItems = new JButton("Update My Collection");
		changeItems.addActionListener(e -> {
			us.updateUserItems(usern, itemSelect.getSelectedItem().toString(), Integer.valueOf(quantity.getText()));
		});
		optionsBIG.add(updateItem);
		updateItem.add(item);
		updateItem.add(itemSelect);
		updateItem.add(quant);
		updateItem.add(quantity);
		updateItem.add(changeItems);
		optionsBIG.setLayout(new BoxLayout(optionsBIG, BoxLayout.Y_AXIS));
		account.setVisible(true);
		
		
	}

}
