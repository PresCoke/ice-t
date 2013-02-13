package controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import bean.forms.*;
import entity.*;

public class EditEntity {
	
	FormBean editableEntity;
	Map<String, Integer> entityID_list;
	

	public String[] getEntityTypeNames() {
		//TODO: remove hard-coded size
		String[] entity_names = new String[6];
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityTypeName", App_Root.language_locale);
		Enumeration<String> entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entity_names[index] = entityNames.getString(key);
		}
		
		return entity_names;
	}

	public String[] getEntityNamesOfType(String selectedEntityType) {
		//TODO: I'm thinking going through App_Root then to the mediator and making the request to the database like that
		List<Object[]> temp = App_Root.resource_mediator.getUniqueEntityIDsForType(selectedEntityType);
		List<String> names = new ArrayList<String>();
		entityID_list = new HashMap<String, Integer>();
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
		if (entityType == entityNames.getString("CharacterSheet_entity")) {
			editableEntity = new CharacterSheetForm();
		}
		
		return editableEntity.createPanelFromExistingEntity(theEntity);
	}

	public void saveEntity() {
		if (editableEntity.getEntity() instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) editableEntity.getEntity();
			cs.edit();
		} else if (editableEntity.getEntity() instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) editableEntity.getEntity();
			th.save();
		}
	}

	public void removeEntity() {
		if (editableEntity.getEntity() instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) editableEntity.getEntity();
			cs.remove();
		} else if (editableEntity.getEntity() instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) editableEntity.getEntity();
			th.remove();
		}
	}

}
