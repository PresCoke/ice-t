package entity;

import java.util.ArrayList;

/**
 * Team Class
 * @author TimHP
 *
 */
public class Team extends Entity {

	private ArrayList<Creature> creatures;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		super(name);
	}

}
