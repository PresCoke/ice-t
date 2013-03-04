package mediator;

import java.util.List;
import java.util.ResourceBundle;
import controller.App_Root;
import resource.*;

/**
 * Mediator
 * @author jamesbegg & TimHP
 */
public class Mediator {
	
//	private static final Logger logger = Logger.getLogger(Mediator.class);
	
	/*
	 * 
	 * MAJOR TODO: make sure app handles when there is no DB present and tells user about it!
	 * 
	 */
	
	
	String propertiesURL;
	EmbeddedDBManager dbMgr;
	
	/**
	 * Pre: Application started, machine/user specific properties are passed as file URL
	 * Post: Mediator created, but does not create or start anything else
	 * @param machineProperties
	 */
	public Mediator (String machineProperties) {
		propertiesURL = machineProperties;
	}
	
	/**
	 * Pre: Application has just started
	 * Post: Resource and Entity packages are initialized and ready to be used.
	 * @return
	 */
	public int start () {
//		dbMgr = new EmbeddedDBManager(propertiesURL);
//		dbMgr.start();
//		dbMgr.connect();
		return -1;
	}
	
	/**
	 * Pre: the application is closing
	 * Post:the mediator has closed all open resources and all entities have been saved in their current state.	
	 * @return
	 */
	public int close () {
		//dbMgr.close();
		return -1;
	}
	//TODO: change anything that uses a character sheet to get a Player instead...
	public List<Object[]> getUniqueEntityIDsForType(String selectedEntityType) {
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		
		if (selectedEntityType.equals(entityNames.getString("CharacterSheet_entity"))) {
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			//List<Object[]> names = null;
			
			return cs_dao.readAllPlayers();
			
		} else if (selectedEntityType.equals(entityNames.getString("Monster_entity"))) {
			
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			return cs_dao.readAllMonsters();
			
		} else if (selectedEntityType.equals(entityNames.getString("Team_entity"))) {
			
			entity.dao.TeamDaoImpl te_dao = new entity.dao.TeamDaoImpl();
			return te_dao.readAllTeams();
		} else if (selectedEntityType.equals(entityNames.getString("TrapHazard_entity"))) {
			
			entity.dao.TrapHazardDaoImpl th_dao = new entity.dao.TrapHazardDaoImpl();
			return th_dao.readAllTrapHazards();
		} else {
			return null;
		}
	}

	public Object getEntityOfID(int selectedEntityId, String selectedEntityType) {
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		if (selectedEntityType.equals(entityNames.getString("CharacterSheet_entity"))) {
			
			entity.dao.PlayerDao pl_dao = new entity.dao.PlayerDaoImpl();
			entity.Player pl = pl_dao.getPlayerOfId(selectedEntityId);
			return pl;
		} else if (selectedEntityType.equals(entityNames.getString("Monster_entity"))) {
			
			entity.dao.CharacterSheetDaoImpl cs_dao = new entity.dao.CharacterSheetDaoImpl();
			entity.CharacterSheet cs = cs_dao.getCharacterSheet(selectedEntityId);
			return cs;
			
		} else if (selectedEntityType.equals(entityNames.getString("Team_entity"))) {
			
			entity.dao.TeamDaoImpl te_dao = new entity.dao.TeamDaoImpl();
			entity.Team te = te_dao.getTeam(selectedEntityId);
			return te;
		} else if (selectedEntityType.equals(entityNames.getString("TrapHazard_entity"))) {
			
			entity.dao.TrapHazardDaoImpl th_dao = new entity.dao.TrapHazardDaoImpl();
			entity.TrapHazard th = th_dao.getTrapHazard(selectedEntityId);
			return th;
		} else {
			return null;
		}
	}

	public entity.CombatEncounter getCombatEncounterOfID(int thisID) {
		entity.dao.CombatEncounterDao ceDAO =  new entity.dao.CombatEncounterDaoImpl();
		entity.CombatEncounter ce = ceDAO.getCombatEncounter(thisID);
		return ce;
	}
}
