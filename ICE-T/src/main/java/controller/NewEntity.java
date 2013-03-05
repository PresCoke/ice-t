package controller;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import entity.*;
import bean.forms.*;

public class NewEntity {

	private static final Logger logger = Logger.getLogger(NewEntity.class);
	
	FormBean empty_entity;
	String[] entity_names;
	
	
	public FormBean getEmptyEntityBeanOfType(String entityType) {
		empty_entity = null;
		int entity_index = 0;
		for (int index = 0; index<entity_names.length; index++) {
			if (entityType == entity_names[index]) {
				entity_index = index;
			}
		}
		switch (entity_index) {
		case 0://Character
			empty_entity = new CharacterSheetForm();
			break;
		case 1://Monster
			empty_entity = new MonsterSheetForm();
			break;
		case 2://TrapHazard
			empty_entity = new TrapHazardForm();
			break;
		case 3://Combat Encounter
			empty_entity = null; break;
		case 4://Team
			empty_entity = new TeamForm();
			break;
		}
		
		return empty_entity;
	}

	public void saveEntity() {
		Object theEntity = empty_entity.getEntity();
		if (theEntity instanceof Player) {
			Player pl = (Player) theEntity;
			CharacterSheet cs = pl.getCharacterSheet();
			cs.save(pl);
		} else if (theEntity instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) theEntity;
			cs.save();		
		} else if (theEntity instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) theEntity;
			th.save();
		} else if (theEntity instanceof Effect) {
			/*TODO: lol what about this: ie. the need for a list of creatures*/
			Effect ef = (Effect) theEntity;
			ef.save(null);
		} else if (theEntity instanceof Team) {
			Team tm = (Team) theEntity;
			boolean player_not_empty_null = tm.getPlayers() != null && !tm.getPlayers().isEmpty();
			boolean monster_empty_null = tm.getMonsters() == null || tm.getMonsters().isEmpty();
			boolean trap_empty_null = tm.getTraphazards() == null || tm.getTraphazards().isEmpty();
			if (player_not_empty_null && monster_empty_null && trap_empty_null){
		    	logger.info("Team of players named " + tm.getName() + " is about to be saved in the database.");
				tm.save();
			} else {
		    	logger.info("Team of NPCs named " + tm.getName() + " is about to be saved in the database so the monsters must be saved in the database first.");
		    	List<Monster> monsters = tm.getMonsters();
				tm.saveNPC(monsters);		    	
			}
		}
		
	}


	public String[] getEntityTypeNames() {
		ArrayList<String> entities = new ArrayList<String>();
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		Enumeration<String> entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entities.add(entityNames.getString(key));
		}
		
		entity_names = entities.toArray(new String[0]);
		
		return entity_names;
	}

	public boolean isValidEntity() {
		return empty_entity.validateEntity();
	}

}
