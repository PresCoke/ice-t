package entity;

import java.util.ArrayList;

/**
 * CombatEncounter Class
 * @author TimHP
 *
 */
public class CombatEncounter extends EntityM {

	private String notes;
	
	/**
	 * Constructor of the superClass
	 * @param name
	 */
	public CombatEncounter(String name) {
		super(name);
	}

	public void organizeCreaturesByInitiative(){
		//TODO
	}
	
	public void finishTurn(){
		//TODO
	}
	
	public ArrayList<Creature> generateRandomEncounter(){
		//TODO
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}

