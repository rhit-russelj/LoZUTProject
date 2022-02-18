package services;

import java.io.File;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		String servername = "titan.csse.rose-hulman.edu";
		String dbname = "LoZUTrackerDemo";
		DatabaseConnectionService dbcs = new DatabaseConnectionService(servername, dbname);
		
		String user = "garvinac";
		String pass = "sequelsql94";
		if(dbcs.connect("testuser1", user, pass)) {
			System.out.println("connection success");
		} else {
			System.out.println("connection failed");
			return;
		}
		
		//UserService us = new UserService(dbcs);
		
		
		ImportService is = new ImportService(dbcs);
		is.importDataFromDirectory();
		/*
		 * tests for userService
		 * 
		
		
		String username = "testuser1";
		System.out.println("");
		System.out.println("Games owned by " + username + ":");
		UserService us = new UserService(dbcs);
		ArrayList<String> list = us.getUserOwns(username);
		for(String s:list) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Dungeons completed by " + username + ":");
		ArrayList<String> list2 = us.getUserDungeons(username);
		for(String s:list2) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Bosses defeated by " + username + ":");
		ArrayList<String> list3 = us.getUserBosses(username);
		for(String s:list3) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Quests completed by " + username + ":");
		ArrayList<String> list4 = us.getUserQuests(username);
		for(String s:list4) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Items owned by " + username + ":");
		ArrayList<String> list5 = us.getUserItems(username);
		for(String s:list5) {
			System.out.println(s);
		}
		*/
		/*
		DropdownService dds = new DropdownService(dbcs);
		System.out.println("");
		System.out.println("Quests in system");
		ArrayList<String> list6 = dds.getRawQuests();
		for(String s:list6) {
			System.out.println(s);
		}
		*/
		
		/*
		System.out.println("");
		System.out.println("Dungeons in system");
		ArrayList<String> list7 = dds.getDungeons();
		for(String s:list7) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Bosses in system");
		ArrayList<String> list8 = dds.getBosses();
		for(String s:list8) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Quests in system");
		ArrayList<String> list9 = dds.getQuests();
		for(String s:list9) {
			System.out.println(s);
		}
		System.out.println("");
		System.out.println("Items in system");
		ArrayList<String> list10 = dds.getItems();
		for(String s:list10) {
			System.out.println(s);
		}
		
		//us.addToUserItems("user1", "Bottle", 99);
		 * 
		 */


	}

}
