package controller;

//-- Project Imports --//
import mediator.*;
import entity.*;
import bean.*;
import presentation.*;

/**
 * App_Root
 * @author jamesbegg
 *
 */

public class App_Root
{

	
	/**
	 * @param args
	 */
	
	public static Mediator resource_mediator;
	public static Help help_controller;
	public static Combat combat_controller;
	public static EditEntity editEntity_controller;
	public static NewEntity newEntity_controller;
	public static Welcome welcome_controller;
	public static GenerateRandomEncounter gre_controller; //TODO: not currently instantiated
	
	public static void main(String[] args) {
		
		resource_mediator = new Mediator("properties");
		resource_mediator.start();
		
		help_controller = new Help();
		combat_controller = new Combat();
		editEntity_controller = new EditEntity();
		newEntity_controller = new NewEntity();
		welcome_controller = new Welcome();
		
		Root_Window mainWindow = new Root_Window();
		mainWindow.start();
		
	}
	
	
	public static void exit() {
		resource_mediator.close();
				
		System.exit(0);
	}

}
