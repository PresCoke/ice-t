package mediator;

//-- Project Imports --//
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.App_Root;
import entity.dao.EffectDaoImpl;

import resource.*;

/**
 * Mediator
 * 
 * @author jamesbegg
 *
 */

public class Mediator {
	
	/*
	 * 
	 * MAJOR TODO: make sure app handles when there is no DB present and tells user about it!
	 * 
	 */
	
	
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
			
		} else if (selectedEntityType == entityNames.getString("Monster_entity")) {
			
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			return cs_dao.readAllCharacterSheets();
			
		} else if (selectedEntityType == entityNames.getString("Team_entity")) {
			
			entity.dao.TeamDaoImpl te_dao = new entity.dao.TeamDaoImpl();
			return te_dao.readAllTeams();
		} else if (selectedEntityType == entityNames.getString("TrapHazard_entity")) {
			
			entity.dao.TrapHazardDaoImpl th_dao = new entity.dao.TrapHazardDaoImpl();
			return th_dao.readAllTrapHazards();
		} else {
			return null;
		}
	}

	public Object getEntityOfID(int selectedEntityId, String selectedEntityType) {
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		if (selectedEntityType == entityNames.getString("CharacterSheet_entity")) {
			
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			entity.CharacterSheet cs = cs_dao.getCharacterSheets(selectedEntityId);
			return cs;
		} else if (selectedEntityType == entityNames.getString("Monster_entity")) {
			
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			entity.CharacterSheet cs = cs_dao.getCharacterSheets(selectedEntityId);
			return cs;
			
		} else if (selectedEntityType == entityNames.getString("Team_entity")) {
			
			entity.dao.TeamDaoImpl te_dao = new entity.dao.TeamDaoImpl();
			entity.Team te = te_dao.getTeam(selectedEntityId);
			return te;
		} else if (selectedEntityType == entityNames.getString("TrapHazard_entity")) {
			
			entity.dao.TrapHazardDaoImpl th_dao = new entity.dao.TrapHazardDaoImpl();
			entity.TrapHazard th = th_dao.getTrapHazard(selectedEntityId);
			return th;
		} else {
			return null;
		}
	}
}
