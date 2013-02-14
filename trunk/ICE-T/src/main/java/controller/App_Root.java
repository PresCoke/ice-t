package controller;

//-- Project Imports --//
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
	public static GenerateRandomEncounter gre_controller; //TODO: not currently instantiated
	public static Locale language_locale;
	private static Root_Window mainWindow;
	
	public static void main(String[] args) {
		
		language_locale = new Locale("en_CA");
		
		resource_mediator = new Mediator("filters/ApplicationSettings");
		resource_mediator.start();
		
		help_controller = new Help();
		combat_controller = new Combat();
		editEntity_controller = new EditEntity();
		newEntity_controller = new NewEntity();
		welcome_controller = new Welcome();
		
		changePreferences();
		
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
			
//			java.net.URL url = ClassLoader.getSystemResource("filters.ApplicationSettings.properties");
//			props.load(url.openStream());
			language_locale = new Locale(props.getProperty("Language"));
			
			fis.close();
			
			if (mainWindow != null) {
				mainWindow.close();
			}
			mainWindow = new Root_Window();
			mainWindow.start();
			
		} catch (Exception e) {
			//Exception_Window.showException(e);	
		}
	}

}
