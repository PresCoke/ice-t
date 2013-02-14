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
		ArrayList<String> entity_names = new ArrayList<String>();
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l10n.EntityEditTypes", App_Root.language_locale);
		Enumeration<String> entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entity_names.add(entityNames.getString(key));
		}
		
		return entity_names.toArray(new String[0]);
	}

	public String[] getEntityNamesOfType(String selectedEntityType) {
		//TODO: I'm thinking going through App_Root then to the mediator and making the request to the database like that
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
			editableEntity = new CharacterSheetForm();
		} else if (entityType.equals(entityNames.getString("TrapHazard_entity"))) {
			editableEntity = new TrapHazardForm();
		} else if (entityType.equals(entityNames.getString("Effect_entity"))) {
			editableEntity = new EffectForm();
		} else if (entityType.equals(entityNames.getString("Team_entity"))) {
			editableEntity = new TeamForm();
		}
		
		return editableEntity.createPanelFromExistingEntity(theEntity);
	}

	public void saveEntity() {
		Object theEntity = editableEntity.getEntity();
		if (theEntity instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) theEntity;
			cs.edit();
		} else if (theEntity instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) theEntity;
			th.edit();
		} 
	}

	public void removeEntity() {
		Object theEntity = editableEntity.getEntity();
		if (theEntity instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) theEntity;
			cs.remove();
		} else if (theEntity instanceof TrapHazard) {
			TrapHazard th = (TrapHazard) theEntity;
			th.remove();
		}
	}

	public boolean validateEntity() {
		return editableEntity.validateEntity();
	}

}
