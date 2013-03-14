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

import entity.dao.*;

/**
 * CombatEncounter Class
 * @author TimHP
 *
 */
@Entity
@Table(name="CombatEncounter")
public class CombatEncounter implements EntityM {
	
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
	//if it's a new encounter; the current Creature's id is -1
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
	private List<TrapHazard> trapsInCe = new ArrayList<TrapHazard>();
	@Transient
	//Players + monsters
	private List<Object> creaturesInCe = new ArrayList<Object>();
	
	@Transient
	private int XPbudget = 0;
	

	/**
	 * Default constructor
	 */
	public CombatEncounter() {
		id = -1;
		this.name = "A Combat Encounter";
		this.teams = new ArrayList<Team>();
		this.notes = "";
		this.rewards = new ArrayList<Rewards>();
		this.currentCreatureId = -1;
		this.tally = new Tally();
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
	
	public List<Monster> getMonstersInCe() {
		return monstersInCe;
	}

	public void setMonstersInCe(List<Monster> monstersInCe) {
		this.monstersInCe = monstersInCe;
	}
	
	public List<TrapHazard> getTrapsInCe() {
		return this.trapsInCe;
	}
	
	public void setTrapsInCe(List<TrapHazard> traps) {
		this.trapsInCe = traps;
	}
	
	public List<Object> getCreaturesInCe() {
		return creaturesInCe;
	}

	public void setCreaturesInCe(List<Object> creaturesInCe) {
		this.creaturesInCe = creaturesInCe;
	}
	/**
	 * Get all the creatures in the combat encounter and sort them out descending
	 * Assumes all teams have been retrieved. (Addendum: My (James) re-writing of the TeamDaoImpl
	 * may make the dao's moot)
	 * @return list of creatures sorted
	 */
	public List<Object> organizeCreaturesIntoRespectiveLists(){	
    	logger.info("Retrieving all the teams attached to the Combat Encounter " + getName());
    	monstersInCe = new ArrayList<Monster>(0);
    	playersInCe =  new ArrayList<Player>(0);
		creaturesInCe = new ArrayList<Object>(0);
		List<Team> teams = this.getTeams();
    	logger.info("Retrieving all the creatures in the Combat Encounter " + getName());
		for (Team t : teams){
			List<Player> players = t.getPlayers();
			List<Monster> monsters = t.getMonsters();
			List<TrapHazard> traps = t.getTraphazards();
			
			if(players == null || players.isEmpty()){
				for (Monster m : monsters){
					monstersInCe.add(m);
				}
				for (TrapHazard th : traps) {
					trapsInCe.add(th);
				}
			} else {
				for (Player p : players){
					playersInCe.add(p);
				}
			}
		}
		//Sorting players
    	//logger.info("Sorting the players");
		//Collections.sort(playersInCe);
		//Sorting all the creatures (players & monsters)
    	logger.info("Sorting all the creatures in the game");
    	creaturesInCe.addAll(playersInCe);
    	creaturesInCe.addAll(monstersInCe);
		/*for (int index = 0; index < teams.size(); index++) {
			
			List<Player> players = teams.get(index).getPlayers();
			if (players != null && !players.isEmpty()) {
				for (int p_index = 0; p_index < players.size(); p_index++) {
					creaturesInCe.add(players.get(p_index));
				}
				continue;
			} else {
				List<Monster> monsters = teams.get(index).getMonsters();
				for (int m_index = 0; m_index < monsters.size(); m_index++) {
					creaturesInCe.add(monsters.get(m_index));
				}
			}
		}*/
		Object key;
		for (int j = 1, i = 0; j < creaturesInCe.size(); j++) {
			key = creaturesInCe.get(j);
			if (key instanceof Player) {
				i = j - 1;
				while (i >= 0) {
					if (creaturesInCe.get(i) instanceof Player) {
						if ( ((Player) creaturesInCe.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					} else if (creaturesInCe.get(i) instanceof Monster) {
						if ( ((Monster) creaturesInCe.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					}
					creaturesInCe.set(i+1, creaturesInCe.get(i));
					i = i - 1;
				}
				creaturesInCe.set(i+1, key);
			} else if (key instanceof Monster) {
				i = j - 1;
				while (i >= 0) {
					if (creaturesInCe.get(i) instanceof Player) {
						if ( ((Player) creaturesInCe.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					} else if (creaturesInCe.get(i) instanceof Monster) {
						if ( ((Monster) creaturesInCe.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					}
					creaturesInCe.set(i+1, creaturesInCe.get(i));
					i = i - 1;
				}
				creaturesInCe.set(i+1, key);
			}
		}
		return creaturesInCe;
	}
	/*
	 * Methods save, edit, remove and getAll
	 */
	public int save() {
		logger.info("Saving Combat Encounter " + getName());
    	this.getTally().setName(this.getName());
    	
    	CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
    	int id = ceDao.saveCombatEncounter(getName(), getNotes(), getCurrentCreatureId(), getRewards(),
				getTally(), getTally().getTuples(), getTeams());
    	
    	TeamDao tmDAO = new TeamDaoImpl();
		for (Team t : getTeams()) {
			if (t.getPlayers().size() != 0 && t.getTraphazards().size() == 0 && t.getMonsters().size() == 0) {
				tmDAO.updateTeam(t.getId(), ceDao.getCombatEncounter(id), t.getName(), t.getPlayers());
			} else if (t.getPlayers().size() != 0 && (t.getTraphazards().size() != 0 || t.getMonsters().size() != 0)) {
				tmDAO.updateNPCteam(t.getId(), ceDao.getCombatEncounter(id), t.getName(), t.getMonsters(), t.getTraphazards());
			}
		}
		PlayerDao pDAO = new PlayerDaoImpl();
		for (Player p : playersInCe) {
			pDAO.updatePlayer(p, p.getTeam());
		}
		MonsterDao mDAO = new MonsterDaoImpl();
		for (Monster m : monstersInCe) {
			mDAO.updateMonster(m, m.getTeam());
		}
		return id;
	}

	public int edit() {
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
        
        //Update Combat encounter
    	logger.info("Editing Combat Encounter " + getName());
    	ceDao.updateCombatEncounter(getId(), getName(), getNotes(), getCurrentCreatureId());
    	
    	  //Set the teams
    	logger.info("Setting CE's teams");
    	TeamDao tmDAO = new TeamDaoImpl();
        if(ceDB.getTeams() != null && !ceDB.getTeams().isEmpty()){
        	for (Team t : ceDB.getTeams()){
        		t.setCombatEncounter(null);
        	}
        }
        if(getTeams() != null && !getTeams().isEmpty()){
        	for (Team t : getTeams()){
        		if (t.getPlayers().size() != 0 && t.getMonsters().size() == 0 && t.getTraphazards().size() == 0) {
        			tmDAO.updateTeam(t.getId(), ceDB, t.getName(), t.getPlayers());
        		} else if (t.getPlayers().size() == 0 && (t.getMonsters().size() != 0 || t.getTraphazards().size() != 0) ) {
        			tmDAO.updateNPCteam(t.getId(), ceDB, t.getName(), t.getMonsters(), t.getTraphazards());
        		}
        	}
        }
        PlayerDao pDAO = new PlayerDaoImpl();
		for (Player p : playersInCe) {
			pDAO.updatePlayer(p, p.getTeam());
		}
		MonsterDao mDAO = new MonsterDaoImpl();
		for (Monster m : monstersInCe) {
			mDAO.updateMonster(m, m.getTeam());
		}
    	
    	return this.id;
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

	public void removePlayer(Player value) {
		this.playersInCe.remove(value);
		this.creaturesInCe.remove(value);
	}

	public void removeMonster(Monster value) {
		this.monstersInCe.remove(value);
		this.creaturesInCe.remove(value);
		for (Team t: getTeams()) {
			if (t.getMonsters().contains(value) ) {
				t.getMonsters().remove(value);
				//this might already be handled...
				value.remove();
			}
			if (t.getMonsters().isEmpty() && t.getTraphazards().isEmpty()) {
				t.remove();
			}
		}
	}

	public void removeTrapHazard(TrapHazard value) {
		this.trapsInCe.remove(value);
		for (Team t: getTeams()) {
			if (t.getTraphazards().contains(value)) {
				t.getTraphazards().remove(value);
			}
		}
	}


}

