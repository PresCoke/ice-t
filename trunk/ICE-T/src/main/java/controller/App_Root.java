package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;

import org.apache.log4j.Logger;

import entity.CombatEncounter;

import mediator.*;
import presentation.*;

/**
 * App_Root
 * @author jamesbegg
 *
 */

public class App_Root
{

	private static final Logger logger = Logger.getLogger(App_Root.class);
	
	/**
	 * @param args
	 */
	
	public static Mediator resource_mediator;
	public static Help help_controller;
	public static Combat combat_controller;
	public static EditEntity editEntity_controller;
	public static NewEntity newEntity_controller;
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
			
		} catch (FileNotFoundException fe) {
            logger.fatal("Error while getting the file src/main/resources/filters/ApplicationSettings.properties --- " + fe.getMessage());
			Exception_Window.showException(fe);
			language_locale = new Locale("en_CA");
			dbPath = "";
		} catch (IOException ioe) {
            logger.fatal("Error while loading or closing the file ApplicationSettings.properties --- " + ioe.getMessage());
			Exception_Window.showException(ioe);
			language_locale = new Locale("en_CA");
			dbPath = "";
		}
		
		
		resource_mediator = new Mediator(dbPath);
		resource_mediator.start();
		
		help_controller = new Help();
		combat_controller = new Combat();
		combat_controller.setCombatEncounter(App_Root.getLastOpenEncounter());
		editEntity_controller = new EditEntity();
		newEntity_controller = new NewEntity();
//		welcome_controller = new Welcome();
		
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
            logger.fatal("Error while getting, loading or closing the nes preferences in the file ApplicationSettings.properties --- " + e.getMessage());
			Exception_Window.showException(e);	
		}
	}


	public static CombatEncounter getLastOpenEncounter() {
		int lastCE_id;
		try {
			java.util.Properties props = new java.util.Properties();
			java.io.File f = new java.io.File("src/main/resources/filters/ApplicationSettings.properties");
			java.io.FileInputStream fis = new java.io.FileInputStream(f);
			props.load(fis);
			
			lastCE_id = Integer.parseInt(props.getProperty("LastOpenCombatEncounter_ID"));
			
			fis.close();
			
		} catch (Exception e) {
			return new CombatEncounter();
		}
		
		if (lastCE_id < 0) {
			return new CombatEncounter();
		} else {
			CombatEncounter theCE = resource_mediator.getCombatEncounterOfID(lastCE_id);
			return theCE;
		}
	}


	public static void changeLastOpenEncounter(int CE_id) {
		try {
			java.util.Properties props = new java.util.Properties();
			java.io.File f = new java.io.File("src/main/resources/filters/ApplicationSettings.properties");
			java.io.FileInputStream fis = new java.io.FileInputStream(f);
			props.load(fis);
			
			props.setProperty("LastOpenCombatEncounter_ID", Integer.toString(CE_id));
			
			fis.close();
		} catch (Exception e) {
			
		}
		
	}

}
