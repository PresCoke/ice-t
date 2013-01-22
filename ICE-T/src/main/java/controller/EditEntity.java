package controller;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class EditEntity {

	public String[] getEntityTypeNames() {
		String[] entity_names = new String[5];
		ResourceBundle entityNames = ResourceBundle.getBundle("filters.mainGUI_l11n.EntityTypeName", App_Root.language_locale);
		Enumeration entityName_keys = entityNames.getKeys();
		for (int index = 0; entityName_keys.hasMoreElements(); index++) {
			String key = (String) entityName_keys.nextElement();
			entity_names[index] = entityNames.getString(key);
		}
		
		return entity_names;
	}

}
