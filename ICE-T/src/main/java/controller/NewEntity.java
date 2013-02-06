package controller;
import java.util.Enumeration;
import java.util.ResourceBundle;

import entity.CharacterSheet;
import entity.TrapHazard;
import bean.forms.*;

public class NewEntity {

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
		case 2://Team
			empty_entity = new TrapHazardForm();
			break;
		case 3://Combat Encounter
			empty_entity = null; break;
		case 4://Effect
			empty_entity = new EffectForm();
			break;
		case 5://Trap/Hazard
			empty_entity = new TeamForm();
			break;
		}
		
		return empty_entity;
	}

	public void saveEntity() {
		//TODO: FIX THIS
		if (empty_entity.getEntity() instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) empty_entity.getEntity();
			cs.save();
		} else if (empty_entity.getEntity() instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) empty_entity.getEntity();
			th.save();
		}
		
	}


	public String[] getEntityTypeNames() {
		// TODO HARD-CODED SIZE!!!!!
		entity_names = new String[6];
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		Enumeration<String> entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entity_names[index] = entityNames.getString(key);
		}
		
		return entity_names;
	}

}
