package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import org.apache.log4j.Logger;

import bean.forms.*;
import entity.*;

public class EditEntity {
	
	private static final Logger logger = Logger.getLogger(EditEntity.class);
	
	FormBean editableEntity;
	Map<String, Integer> entityID_list;
	

	public String[] getEntityTypeNames() {
		ArrayList<String> entity_names = new ArrayList<String>();
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		Enumeration<String> entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entity_names.add(entityNames.getString(key));
		}
		
		return entity_names.toArray(new String[0]);
	}

	public String[] getEntityNamesOfType(String selectedEntityType) {
		List<Object[]> temp = App_Root.resource_mediator.getUniqueEntityIDsForType(selectedEntityType);
		List<String> names = new ArrayList<String>();
		entityID_list = new HashMap<String, Integer>();
		if (temp == null) {
			return(new String[0]);
		}
		for (int index = 0; index < temp.size(); index++) {
			int value = (Integer) temp.get(index)[0];
			String key = (String) temp.get(index)[1];
			names.add(key);
			entityID_list.put(key, value);
		}
		return names.toArray(new String[0]);
	}

	public JPanel getEntityPanelOfName(String selectedEntityName, String entityType) {
		//TODO: I'm thinking going through App_Root then to the mediator and making the request to the database like that
		int entityID = entityID_list.get(selectedEntityName);
		Object theEntity = App_Root.resource_mediator.getEntityOfID(entityID, entityType);
		
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		if (entityType.equals(entityNames.getString("CharacterSheet_entity"))) {
			editableEntity = new CharacterSheetForm();
		} else if (entityType.equals(entityNames.getString("Monster_entity"))) {
			editableEntity = new MonsterSheetForm();
		} else if (entityType.equals(entityNames.getString("TrapHazard_entity"))) {
			editableEntity = new TrapHazardForm();
		} else if (entityType.equals(entityNames.getString("Team_entity"))) {
			editableEntity = new TeamForm();
		} else if ( entityType.equals(entityNames.getString("CombatEncounter_entity")) ) {
			editableEntity = new CombatEncounterForm();
		}
		
		return editableEntity.createPanelFromExistingEntity( theEntity );
	}

	public void saveEntity() {
		Object theEntity = editableEntity.getEntity();
		if (theEntity instanceof Player) {
			Player pl = (Player) theEntity;
			CharacterSheet cs = pl.getCharacterSheet();
			cs.edit();
			pl.edit();
		} else if (theEntity instanceof CharacterSheet){
			CharacterSheet cs = (CharacterSheet) theEntity;
			cs.edit();
		} else if (theEntity instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) theEntity;
			th.edit();
		} else if (theEntity instanceof Team) {
			//TODO: test that shit
			Team tm = (Team) theEntity;
			boolean player_not_empty_null = tm.getPlayers() != null && !tm.getPlayers().isEmpty();
			boolean monster_empty_null = tm.getMonsters() == null || tm.getMonsters().isEmpty();
			boolean trap_empty_null = tm.getTraphazards() == null || tm.getTraphazards().isEmpty();
			if (player_not_empty_null && monster_empty_null && trap_empty_null){
		    	logger.info("Team of players named " + tm.getName() + " is about to be updated in the database.");
				tm.edit();
			} else {
		    	logger.info("Team of NPCs named " + tm.getName() + " is about to be saved in the database so the monsters must be saved in the database first.");
		    	List<Monster> monsters = tm.getMonsters();
				tm.editNPC(monsters);		    	
			}
		}
	}

	public void removeEntity() {
		Object theEntity = editableEntity.getEntity();
		if (theEntity instanceof Player) {
			Player pl = (Player) theEntity;
			CharacterSheet cs = pl.getCharacterSheet();
			cs.remove();
		} else if (theEntity instanceof CharacterSheet){
			CharacterSheet cs = (CharacterSheet) theEntity;
			cs.remove();
		} else if (theEntity instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) theEntity;
			th.remove();
		} else if (theEntity instanceof Team) {
			//TODO: test that shit
			Team tm = (Team) theEntity;
			boolean player_not_empty_null = tm.getPlayers() != null && !tm.getPlayers().isEmpty();
			boolean monster_empty_null = tm.getMonsters() == null || tm.getMonsters().isEmpty();
			boolean trap_empty_null = tm.getTraphazards() == null || tm.getTraphazards().isEmpty();
			if (player_not_empty_null && monster_empty_null && trap_empty_null){
		    	logger.info("Team of players named " + tm.getName() + " is about to be removed in the database.");
				tm.remove();
			} else {
		    	logger.info("Team of NPCs named " + tm.getName() + " is about to be removed in the database.");
				tm.removeNPC();		    	
			}
		} else if (theEntity instanceof Monster){
			Monster m = (Monster) theEntity;
			CharacterSheet cs = m.getCharacterSheet();
			cs.remove();
		} else if (theEntity instanceof CombatEncounter) {
			CombatEncounter ce = (CombatEncounter) theEntity;
			ce.remove();
		}
	}

	public boolean validateEntity() {
		return editableEntity.validateEntity();
	}

}
