package entity;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * CombatEncounter Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Creature")
public class CombatEncounter{

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="notes")
	private String notes;
	
	//Associations
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="EntityM")
	private Set<Entity> entities;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Rewards")
	private Set<Rewards> rewards;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Tally tally;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Stats")
	private Set<Stats> stats;
	
	/**
	 * Default constructor
	 */
	public CombatEncounter() {
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public CombatEncounter(String name) {
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	/**
	 * Other functions
	 */
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

}

