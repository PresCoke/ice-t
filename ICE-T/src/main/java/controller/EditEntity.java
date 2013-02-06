package controller;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import bean.forms.*;
import entity.*;

public class EditEntity {
	
	FormBean editableEntity;

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
		return App_Root.resource_mediator.getUniqueEntityIDsForType(selectedEntityType);
	}

	public JPanel getEntityPanelOfName(String selectedEntityName) {
		//TODO: I'm thinking going through App_Root then to the mediator and making the request to the database like that
		
		Object theEntity = App_Root.resource_mediator.getEntityOfID(selectedEntityName);
		return editableEntity.createPanelFromExistingEntity(theEntity);
	}

	public void saveEntity() {
		if (editableEntity.getEntity() instanceof CharacterSheet) {
			CharacterSheet cs = (CharacterSheet) editableEntity.getEntity();
			cs.save();
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
