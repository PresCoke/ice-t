package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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

import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.MonsterDao;
import entity.dao.MonsterDaoImpl;
import entity.dao.PlayerDao;
import entity.dao.PlayerDaoImpl;
import entity.dao.RewardsDao;
import entity.dao.RewardsDaoImpl;
import entity.dao.TallyDao;
import entity.dao.TallyDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;
import entity.dao.TupleDao;
import entity.dao.TupleDaoImpl;

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
	//if it's the GM's turn, the current Creature's id is -1
	private int currentCreatureId;
	
	//Associations
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "combatEncounter", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Rewards> rewards;
	
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
	private List<Player> playersInCe = new ArrayList<Player>();
	
	@Transient
	private List<Monster> monstersInCe = new ArrayList<Monster>();
	
	@Transient
	//Players + monsters
	private List<Object> creaturesInCe = new ArrayList<Object>();
	
	@Transient
	int XPbudget = 0;
	

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

	public List<Player> getPlayersInCE() {
		return playersInCe;
	}

	public void setPlayersInCE(List<Player> playersInCe) {
		this.playersInCe = playersInCe;
	}
	
	public List<Player> getPlayersInCe() {
		return playersInCe;
	}

	public void setPlayersInCe(List<Player> playersInCe) {
		this.playersInCe = playersInCe;
	}

	public List<Object> getCreaturesInCe() {
		return creaturesInCe;
	}

	public void setCreaturesInCe(List<Object> creaturesInCe) {
		this.creaturesInCe = creaturesInCe;
	}
	

	/**
	 * Other functions
	 */
	
	/**
	 * Get all the creatures in the combat encounter and sort them out descending
	 * @return list of creatures sorted
	 */
	public List<Object> organizeCreaturesByInitiative(){	
    	logger.info("Retrieving all the teams attached to the Combat Encounter " + getName());
		creaturesInCe = new ArrayList<Object>();
		List<Team> teams = this.getTeams();
    	logger.info("Retrieving all the creatures in the Combat Encounter " + getName());
		for (Team t : teams){
			PlayerDao pDao = new PlayerDaoImpl();
			List<Player> players = pDao.getPlayersInTeam(t.getId());
			MonsterDao mDao = new MonsterDaoImpl();
			List<Monster> monsters = mDao.getMonstersInTeam(t.getId());
			if(players == null || players.isEmpty()){
				for (Monster m : monsters){
					monstersInCe.add(m);
				}
			} else {
				for (Player p : players){
					playersInCe.add(p);
				}
			}
		}
		//Sorting players
    	logger.info("Sorting the players");
		Collections.sort(playersInCe);
		//Sorting all the creatures (players & monsters)
    	logger.info("Sorting all the creatures in the game");
    	if (monstersInCe == null || monstersInCe.isEmpty()){
    		for (Player p : playersInCe){
    			creaturesInCe.add(p);
    		}
    	} else {
    		int gameMasterInitiative = monstersInCe.get(0).getInitiative();
    		int index = 0;
    		for (int i = 0; i<playersInCe.size(); i++){
    			Player p = playersInCe.get(i);
    			creaturesInCe.add(p);
    			if (p.getInitiative()<gameMasterInitiative){
    				index = i;
    			}
    		}
    		for (Monster m : monstersInCe){
    			creaturesInCe.add(index, m);
    		}
    	}
    	
		return creaturesInCe;
	}

	/**
	 * Organize the creatures in the way they were the last time 
	 * the CE was played
	 * @return list of creatures sorted
	 */
	public List<Object> organizeCreaturesAfterLoadingCE(){	
		//Retrieving all the creatures in the combat encounter and sort them out
		this.organizeCreaturesByInitiative();
		
		//Sort the creatures to get them the way they were last time
		if (currentCreatureId == -1){
	    	logger.info("The currentCreatureId is null so it was the Game Master's turn when the game was last saved.");
	    	Object o = creaturesInCe.get(0);
	    	while(o instanceof Player){
				Player p = (Player) creaturesInCe.get(0);
		    	logger.info("The creature is the player " + p.getPlayerName());
				this.finishTurn();
				o = creaturesInCe.get(0);
	    	}
		} else {
			int size = creaturesInCe.size();
	    	Object o = creaturesInCe.get(0);
	    	do{
				if (o instanceof Player){
					Player p = (Player) o;
			    	logger.info("The creature is the player " + p.getPlayerName());
					if (p.getId() == currentCreatureId){
						break;
					} else {
						this.finishTurn();
					}
					o = creaturesInCe.get(0);
				} else {
					Monster m = (Monster) o;
			    	logger.info("The creature is the monster " + m.getMonsterName());
			    	this.finishTurn();
			    	o = creaturesInCe.get(0);
				}
				size --;
	    	}while(size>0);
	    }
		
		return creaturesInCe;
	}
	
	/**
	 * Take the first creature in the list of creatures playing and put it at the end of the list
	 * If the creature is a monster, all the monsters are put at the end of the list
	 */
	public void finishTurn(){
		Object current = creaturesInCe.get(0);
		if (current instanceof Player){
			creaturesInCe.remove(0);
			creaturesInCe.add(current);	
		} else {
			Object currentMonster = creaturesInCe.get(0);
			while(currentMonster instanceof Monster){
				creaturesInCe.remove(0);
				creaturesInCe.add(currentMonster);
				currentMonster = creaturesInCe.get(0);
			}
		}
	}
	
	/**
	 * Generate a random encounter, that is to say a random team of monsters
	 * @return list of creatures and traps
	 */
	public List<Object> generateRandomEncounter(){
		
		//Retrieving all the creatures' level in the combat encounter
    	logger.info("Retrieving all the creatures in the CE");
		List<Integer> levels = new ArrayList<Integer>();
		for (Player c : playersInCe){
			levels.add(c.getCharacterSheet().getId());
		}
		
		//Choosing Encounter level
    	logger.info("Choosing a level for the random encounter");
		int creaturesNumber = levels.size();
		int sum = 0;
		for (int level : levels){
			sum += level;
		}
		Random random = new Random();
		int r = random.nextInt(3) - random.nextInt(3);
		int levelEncounter = sum/creaturesNumber + r;
		if (levelEncounter < 1){
			levelEncounter = 1;
		}
		XPbudget = levelEncounter*250;

		//Getting all NPC creatures and traps in database that would suit the XPbudget
		List<Object> npcs = new ArrayList<Object>();
    	logger.info("Getting all NPC creatures that would suit the XP budget");
		MonsterDao mDao = new MonsterDaoImpl();
		List<Monster> monsters = new ArrayList<Monster>();
		List<Monster> allMonsters = mDao.getAllMonsters();
		for (Monster m : allMonsters){
			if(m.getCharacterSheet().getXP()<=XPbudget){
				monsters.add(m);
			}
		}
    	logger.info("Getting all traps that would suit the XP budget");
		TrapHazardDao thDao = new TrapHazardDaoImpl();
		List<TrapHazard> trapHazards = new ArrayList<TrapHazard>();
		List<TrapHazard> trapHazardsAll = thDao.getAllTrapHazards();
		for (TrapHazard th : trapHazardsAll){
			if(th.getXp()<=XPbudget){
				trapHazards.add(th);
			}
		}
		
		//Creating the list
    	logger.info("Adding the NPC creatures and the traps in the list");
    	for (Monster c : monsters){
    		npcs.add(c);
    	}
    	for (TrapHazard th : trapHazards){
    		npcs.add(th);
    	}				
		return npcs;
	}
	
	/**
	 * Allow the game master to roll a D20 automatically
	 * @return
	 */
	public int autoRoll(){
		Random random = new Random();
		int d = random.nextInt(21);
		return d;
	}

	/*
	 * Methods save, edit, remove and getAll
	 */
	//TODO test that shit
	public void save() {
    	logger.info("Saving Combat Encounter " + getName());
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	ceDao.saveCombatEncounter(getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams());
	}

	public void edit() {
		CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
		CombatEncounter ceDB = ceDao.getCombatEncounter(getId());
		
        //Set the rewards
        logger.debug("Deleting previous CE's rewards");
        RewardsDao rDao = new RewardsDaoImpl();
        if(ceDB.getRewards() != null && !ceDB.getRewards().isEmpty()){
            for (Rewards r : ceDB.getRewards()){
            	rDao.deleteRewards(r.getId());
            } 
        }
        logger.debug("Setting CE's new rewards");
        if(rewards != null && !rewards.isEmpty()){
            for(Rewards r : getRewards()){
            	rDao.saveRewards(r.getXP(), r.getTreasure(), getId());
            }
        }
        
        //Set the tally and the tuples
        logger.debug("Deleting previous CE's tally and tuples");
		TallyDao tallyDao = new TallyDaoImpl();
		TupleDao tupleDao = new TupleDaoImpl();
        if (ceDB.getTally() != null){
			tallyDao.deleteTally(ceDB.getTally().getId());
        }
        logger.debug("Setting CE's tally and tuples");
        if (getTally() != null){
        	Tally t = getTally();
        	int tallyId = tallyDao.saveTally(t.getName(), getId());
        	if (t.getTuples() != null && !t.getTuples().isEmpty()){
        		List<Tuple> tuples = t.getTuples();
        		for (Tuple tuple : tuples){
        			tupleDao.saveTuple(tuple.getName(), tuple.getValue1(), tuple.getValue2(), tallyId);
        		}
        	}
        } 
        
        //Set the teams
    	logger.info("Setting CE's teams");
        if(ceDB.getTeams() != null && !ceDB.getTeams().isEmpty()){
        	for (Team t : ceDB.getTeams()){
        		t.setCombatEncounter(null);
        	}
        }
        if(getTeams() != null && !getTeams().isEmpty()){
        	for (Team t : getTeams()){
        		t.setCombatEncounter(this);
        	}
        }
        
        //Update Combat encounter
    	logger.info("Editing Combat Encounter " + getName());
    	ceDao.updateCombatEncounter(getId(), getName(), getNotes(), getCurrentCreatureId());		
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

