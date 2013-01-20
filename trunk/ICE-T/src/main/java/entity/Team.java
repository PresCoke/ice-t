package entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Team Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Team")
public class Team extends EntityM {

	//Association
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Creature")
	private ArrayList<Creature> creatures;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		super(name);
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
