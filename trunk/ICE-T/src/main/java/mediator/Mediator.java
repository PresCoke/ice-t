package mediator;

import java.util.List;
import java.util.ResourceBundle;
import controller.App_Root;
import entity.CombatEncounter;
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
//		dbMgr.close();
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
			int currently_open_CE = App_Root.combat_controller.getID();
			List<Object[]> allTeams = te_dao.readAllTeams();
			for (int index = 0; index < allTeams.size(); index++) {
				if ( allTeams.get(index)[2] == null) {
					continue;
				}
				if ( ((Integer) allTeams.get(index)[2]) == currently_open_CE) {
					allTeams.remove(index);
				}
			}
			return allTeams;
		} else if (selectedEntityType.equals(entityNames.getString("TrapHazard_entity"))) {
			
			entity.dao.TrapHazardDaoImpl th_dao = new entity.dao.TrapHazardDaoImpl();
			return th_dao.readAllTrapHazards();
		} else if (selectedEntityType.equals(entityNames.getString("CombatEncounter_entity"))) {
			
			entity.dao.CombatEncounterDao ce_dao = new entity.dao.CombatEncounterDaoImpl();
			List<Object[]> allCEs = ce_dao.readAllCombatEncounters();
			int currently_open = App_Root.combat_controller.getID();
			for (int index = 0; index < allCEs.size(); index++) {
				if ( ((Integer) allCEs.get(index)[0]) == currently_open) {
					allCEs.remove(index);
				}
			}
			return allCEs;
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
		} else if (selectedEntityType.equals(entityNames.getString("CombatEncounter_entity"))) {
			
			entity.dao.CombatEncounterDao ce_dao = new entity.dao.CombatEncounterDaoImpl();
			entity.CombatEncounter ce = ce_dao.getCombatEncounter(selectedEntityId);
			return ce;
		} else {
			return null;
		}
	}

	public entity.CombatEncounter getCombatEncounterOfID(int thisID) {
		entity.dao.CombatEncounterDao ceDAO =  new entity.dao.CombatEncounterDaoImpl();
		if (ceDAO.doesEncounterExist(thisID)) {
			entity.CombatEncounter ce = ceDAO.getCombatEncounter(thisID);
			return ce;
		} else {
			return new entity.CombatEncounter();
		}
	}
}
