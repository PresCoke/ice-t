package mediator;

//-- Project Imports --//
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.App_Root;

import resource.*;

/**
 * Mediator
 * 
 * @author jamesbegg
 *
 */

public class Mediator {
	
	String propertiesURL;
	EmbeddedDBManager dbMgr;
	
	public Mediator (String machineProperties) {
		/*
		 * Pre: Application started, machine/user specific properties are passed as file URL
		 * Post: Mediator created, but does not create or start anything else
		 */
		
		propertiesURL = machineProperties;
	}
	
	public int start () {
		/*
		 * Pre: Application has just started
		 * Post: Resource and Entity packages are initialized and ready to be used.
		 */
		//dbMgr = new EmbeddedDBManager(propertiesURL);
		//dbMgr.start();
		
		return -1;
	}
	
	public int close () {
		/*
		 * Pre: the application is closing
		 * Post:the mediator has closed all open resources and all entities have been saved in their current state.
		 */
		
		//dbMgr.close();
		return -1;
	
	}

	public List<Object[]> getUniqueEntityIDsForType(String selectedEntityType) {
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		
		if (selectedEntityType == entityNames.getString("CharacterSheet_entity")) {
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			//List<Object[]> names = null;
			
			return cs_dao.readAllCharacterSheets();
			
		} else {
			/*String [] empty = {"Puppies", "Kittens", "Rainbows" };//{"Shit", "Fuck", "Cunt"}; 
			return (empty);*/
			return null;
		}
	}

	public Object getEntityOfID(int selectedEntityId, String selectedEntityType) {
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		if (selectedEntityType == entityNames.getString("CharacterSheet_entity")) {
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			entity.CharacterSheet cs = cs_dao.getCharacterSheets(selectedEntityId);
			return cs;
		} else {
			return null;
		}
	}
}
