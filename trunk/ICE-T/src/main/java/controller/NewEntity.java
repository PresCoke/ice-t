package controller;
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
		// TODO Auto-generated method stub
		String[] temp_HACK = {"Character Sheet", "Team", "Trap/Hazard", "Combat Encounter", "Effect"};
		return temp_HACK;
	}

}
