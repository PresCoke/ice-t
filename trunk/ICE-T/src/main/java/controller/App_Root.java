package controller;

//-- Project Imports --//
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import mediator.*;
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
	public static GenerateRandomEncounter gre_controller;
	public static Locale language_locale;
	private static String dbPath;
	private static Root_Window mainWindow;
	
	public static void main(String[] args) {
		
		try {
			java.util.Properties props = new java.util.Properties();       
			java.io.File f = new java.io.File("src/main/resources/filters/ApplicationSettings.properties"); 
			java.io.FileInputStream fis;
			fis = new java.io.FileInputStream(f);
			
			props.load(fis);
			language_locale = new Locale(props.getProperty("Language"));
			dbPath = props.getProperty("DbPath");
			
			fis.close();
			
		} catch (Exception e) {
			Exception_Window.showException(e);
			language_locale = new Locale("en_CA");
			dbPath = "";
		}

		
		
		resource_mediator = new Mediator(dbPath);
		resource_mediator.start();
		
		help_controller = new Help();
		combat_controller = new Combat();
		editEntity_controller = new EditEntity();
		newEntity_controller = new NewEntity();
		welcome_controller = new Welcome();
		
		mainWindow = new Root_Window();
		mainWindow.start();
		
	}
	
	
	public static void exit() {
		resource_mediator.close();
				
		System.exit(0);
	}


	public static void changePreferences() {
		try {
			java.util.Properties props = new java.util.Properties();       
			java.io.File f = new java.io.File("src/main/resources/filters/ApplicationSettings.properties"); 
			java.io.FileInputStream fis = new java.io.FileInputStream(f);
			props.load(fis);

			language_locale = new Locale(props.getProperty("Language"));
			dbPath = props.getProperty("DbPath");
			
			fis.close();
			
			if (mainWindow != null) {
				mainWindow.close();
			}
			mainWindow = new Root_Window();
			mainWindow.start();
			
			resource_mediator.close();
			resource_mediator = new Mediator(dbPath);
			resource_mediator.start();
			
		} catch (Exception e) {
			Exception_Window.showException(e);	
		}
	}

}
