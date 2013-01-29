package entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Team Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Team")
public class Team extends EntityM {

    @Column(name="Team_id")
    private int id;	

	//Associations
	@ManyToOne
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;

//	@OneToMany(mappedBy = "team")
//	private Set<Creature> creatures;
	
	
	/**
	 * Default constructor
	 */
	public Team() {
		super();
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		super(name);
	}

	/**
	 * Getters & Setters
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CombatEncounter getCombatEncounter() {
		return combatEncounter;
	}

	public void setCombatEncounter(CombatEncounter combatEncounter) {
		this.combatEncounter = combatEncounter;
	}
	
//	public Set<Creature> getCreatures() {
//		return creatures;
//	}
//
//	public void setCreatures(Set<Creature> creatures) {
//		this.creatures = creatures;
//	}
	
	/**
	 * Other functions
	 */
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
