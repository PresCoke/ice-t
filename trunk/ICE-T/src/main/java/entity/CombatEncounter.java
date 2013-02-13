package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;

/**
 * CombatEncounter Class
 * @author TimHP
 *
 */
@Entity
@Table(name="CombatEncounter")
public class CombatEncounter implements EntityM{
	
	private static final Logger logger = Logger.getLogger(CombatEncounter.class);

	@Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="CombatEncounter_id")
    private int id;

	@Column(name="CombatEncounter_name")
	private String name;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="currentCreatureId")
	private int currentCreatureId;
	
	//Associations
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "combatEncounter", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Rewards> rewards;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "combatEncounter")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<TrapHazard> traphazards;

	@OneToOne(mappedBy = "combatEncounter", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private Tally tally;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "combatEncounter")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Team> teams;

	//DAO
	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
	

	/**
	 * Default constructor
	 */
	public CombatEncounter() {
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public CombatEncounter(String name, String notes, int currentCreatureId) {
		this.name = name;
		this.notes = notes;
		this.rewards = new ArrayList<Rewards>();
		this.currentCreatureId = currentCreatureId;
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
	
	public List<TrapHazard> getTraphazards() {
		return traphazards;
	}

	public void setTraphazards(List<TrapHazard> traphazards) {
		this.traphazards = traphazards;
	}
	
	public void removeAllTrapHazards() {
		this.traphazards.removeAll(traphazards);
	}
	
	public void addTrapHazard(TrapHazard addThisTrapHazard) {
		this.traphazards.add(addThisTrapHazard);
	}
	
	public TrapHazard getTrapHazardAt(int index) {
		return this.traphazards.get(index);
	}
	
	public TrapHazard removeTrapHazardAt(int index) throws IndexOutOfBoundsException {
		return this.traphazards.remove(index);
	}
	
	public boolean removeTrapHazard(TrapHazard thisTrapHazard) {
		return this.traphazards.remove(thisTrapHazard);
	}
		
	public List<Rewards> getRewards() {
		return rewards;
	}

	public void setRewards(List<Rewards> rewards) {
		this.rewards = rewards;
	}
	
	public void removeAllRewards() {
		this.rewards.removeAll(rewards);
	}
	
	public void addRewards(Rewards addThisRewards) {
		this.rewards.add(addThisRewards);
	}
	
	public Rewards getRewardsAt(int index) {
		return this.rewards.get(index);
	}
	
	public Rewards removeRewardsAt(int index) throws IndexOutOfBoundsException {
		return this.rewards.remove(index);
	}
	
	public boolean removeRewards(Attack thisRewards) {
		return this.rewards.remove(thisRewards);
	}
	
	public Tally getTally() {
		return tally;
	}

	public void setTally(Tally tally) {
		this.tally = tally;
	}
	
	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	public void removeAllTeams() {
		this.teams.removeAll(teams);
	}
	
	public void addTeams(Team addThisTeam) {
		this.teams.add(addThisTeam);
	}
	
	public Team getTeamAt(int index) {
		return this.teams.get(index);
	}
	
	public Team removeTeamAt(int index) throws IndexOutOfBoundsException {
		return this.teams.remove(index);
	}
	
	public boolean removeTeam(Attack thisTeam) {
		return this.teams.remove(thisTeam);
	}
	
	public int getCurrentCreatureId() {
		return currentCreatureId;
	}

	public void setCurrentCreatureId(int currentCreatureId) {
		this.currentCreatureId = currentCreatureId;
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

	public void save() {
    	logger.info("Saving Combat Encounter " + getName());
		ceDao.saveCombatEncounter(getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams(), getTraphazards());
		
	}

	public void edit() {
    	logger.info("Editing Combat Encounter " + getName());
		ceDao.updateCombatEncounter(getId(), getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams(), getTraphazards());		
	}

	public void remove() {
    	logger.info("Removing Combat Encounter " + getName());
		ceDao.deleteCombatEncounter(getId());			
	}

	public List<Object[]> getAll() {
    	logger.info("Getting all Combats Encounters in database");
		return ceDao.readAllCombatEncounters();
	}

}

