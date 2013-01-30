package entity;

import java.util.ArrayList;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * CombatEncounter Class
 * @author TimHP
 *
 */
@Entity
@Table(name="CombatEncounter")
public class CombatEncounter {

	@Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="CombatEncounter_id")
    private int id;

	@Column(name="CombatEncounter_name")
	private String name;
	
	@Column(name="notes")
	private String notes;
	
	//Associations
	@OneToMany(mappedBy = "combatEncounter")
	private Set<Rewards> rewards;
	
	@OneToMany(mappedBy = "combatEncounter")
	private Set<TrapHazard> traphazards;

	@OneToOne(mappedBy = "combatEncounter")
	private Tally tally;
	
	@OneToMany(mappedBy = "combatEncounter")
	private Set<Team> teams;


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
	
	public Set<TrapHazard> getTraphazards() {
		return traphazards;
	}

	public void setTraphazards(Set<TrapHazard> traphazards) {
		this.traphazards = traphazards;
	}
	
	public Set<Rewards> getRewards() {
		return rewards;
	}

	public void setRewards(Set<Rewards> rewards) {
		this.rewards = rewards;
	}
	
	public Tally getTally() {
		return tally;
	}

	public void setTally(Tally tally) {
		this.tally = tally;
	}
	
	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
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

