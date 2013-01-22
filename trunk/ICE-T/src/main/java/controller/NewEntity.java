package controller;
import java.util.Enumeration;
import java.util.ResourceBundle;

import entity.*;

public class NewEntity {

	EntityM empty_entity;
	
	
	public String getEmptyEntityBeanOfType(String entityType) {
		// TODO Auto-generated method stub
		/*switch (entityType) {
		
		}*/
		
		return "FUCK YOU!";
	}

	public boolean saveEntity(String validated_html) {
		// TODO Auto-generated method stub
		scrape_and_store(validated_html);
		return false;
	}

	private void scrape_and_store(String validated_html) {
		// TODO: scrape useful data put it into empty_entity
		
		empty_entity.save();
		
	}

	public String[] getEntityTypeNames() {
		// TODO HARD-CODED SIZE!!!!!
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
