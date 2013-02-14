package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.CharacterSheetDao;
import entity.dao.CharacterSheetDaoImpl;
import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;

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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "combatEncounter")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Team> teams;

	//Other
	@Transient
	private List<Creature> creaturesInCE;
	

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
	
	
	/*
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

	public List<Creature> getCreaturesInCE() {
		return creaturesInCE;
	}

	public void setCreaturesInCE(List<Creature> creaturesInCE) {
		this.creaturesInCE = creaturesInCE;
	}

	/**
	 * Other functions
	 */
	
	/**
	 * Get all the creatures in the combat encounter and sort them out descending
	 * @return list of creatures sorted
	 */
	public List<Creature> organizeCreaturesByInitiative(){	
		//Retrieving all the creatures in the combat encounter
		creaturesInCE = new ArrayList<Creature>();
		List<Team> teams = this.getTeams();
		CreatureDao cDao = new CreatureDaoImpl();
		for (Team t : teams){
			List<Creature> creatures = cDao.getCreaturesInTeam(t.getId());
			for (Creature c : creatures){
				creaturesInCE.add(c);
			}
		}
		//Sorting
		Collections.sort(creaturesInCE);
		return creaturesInCE;
	}

	/**
	 * Organize the creatures in the way they were the last time 
	 * the CE was played
	 * @return list of creatures sorted
	 */
	public List<Creature> organizeCreaturesAfterLoad(){	
		//Retrieving all the creatures in the combat encounter and sort them out
		this.organizeCreaturesByInitiative();
		
		//Sort the creatures to get them the way they were last time
		for (Creature c : creaturesInCE){
			if (c.getId() == currentCreatureId){
				break;
			} else {
				this.finishTurn();
			}
		}
		
		return creaturesInCE;
	}
	
	/**
	 * Take the first creature in the list of creatures playing and put it at the end of the list
	 */
	public void finishTurn(){
		Creature current = creaturesInCE.get(0);
		creaturesInCE.remove(0);
		creaturesInCE.add(current);	
	}
	
	/**
	 * Generate a random encounter, that is to say a random team of monsters
	 * @return list of character sheets
	 */
	public List<CharacterSheet> generateRandomEncounter(){
		
		//Retrieving all the creatures' level in the combat encounter
		List<Integer> levels = new ArrayList<Integer>();
		for (Creature c : creaturesInCE){
			levels.add(c.getCharacterSheet().getId());
		}
		
		//Choosing Encounter level
		int creaturesNumber = levels.size();
		int sum = 0;
		for (int level : levels){
			sum += level;
		}
		int random = 0; //TODO
		int levelEncounter = sum/creaturesNumber + random;
		int XPbudget = levelEncounter*250;
		
		//Getting all NPC creatures in database that would suit the XPbudget
		CharacterSheetDao csDao = new CharacterSheetDaoImpl();
		List<CharacterSheet> npcs = csDao.getAllCharacterSheets();
		for (int i = 0; i<=npcs.size(); i++){
			if(npcs.get(i).getXP()>XPbudget){
				npcs.remove(i);
			}
		}
				
		return npcs;
	}

	/*
	 * Methods save, edit, remove and getAll
	 */
	public void save() {
    	logger.info("Saving Combat Encounter " + getName());
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	ceDao.saveCombatEncounter(getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams(), getTraphazards());
	}

	public void edit() {
    	logger.info("Editing Combat Encounter " + getName());
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	ceDao.updateCombatEncounter(getId(), getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams(), getTraphazards());		
	}

	public void remove() {
    	logger.info("Removing Combat Encounter " + getName());
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	ceDao.deleteCombatEncounter(getId());			
	}

	public List<Object[]> getAll() {
    	logger.info("Getting all Combats Encounters in database");
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	return ceDao.readAllCombatEncounters();
	}

}

