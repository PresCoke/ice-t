package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.MonsterDao;
import entity.dao.MonsterDaoImpl;
import entity.dao.TeamDao;
import entity.dao.TeamDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;

/**
 * Team Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Team")
public class Team implements EntityM {

	private static final Logger logger = Logger.getLogger(Team.class);
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Team_id")
    private int id;	
    
	@Column(name="Team_name")
	private String name;

	//Associations
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Player> players;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Monster> monsters;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<TrapHazard> traphazards;
	
	
	/**
	 * Default constructor
	 */
	public Team() {
		this.name= "";
		this.players = new ArrayList<Player>();
		this.monsters = new ArrayList<Monster>();
		this.traphazards = new ArrayList<TrapHazard>();
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		this.name=name;
		this.players = new ArrayList<Player>();
		this.monsters = new ArrayList<Monster>();
		this.traphazards = new ArrayList<TrapHazard>();
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public void removeAllPlayers() {
		this.players.removeAll(players);
	}
	
	public void addPlayer(Player addThisPlayer) {
		this.players.add(addThisPlayer);
	}
	
	public Player getPlayerAt(int index) {
		return this.players.get(index);
	}
	
	public Player removePlayerAt(int index) throws IndexOutOfBoundsException {
		return this.players.remove(index);
	}
	
	public boolean removePlayer(Player thisPlayer) {
		return this.players.remove(thisPlayer);
	}
	
	public int getNumberOfPlayers() {
		return this.players.size();
	}
	
	public int getIndexOf (Player thisPlayer){
		int index = 0;
		for (Player p : players){
			if (p.getPlayerName().equals(thisPlayer.getPlayerName())){
				break;
			}
			index++;
		}
		return index;
	}
	
	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}
	
	public void removeAllMonsters() {
		this.monsters.removeAll(monsters);
	}
	
	public void addMonster(Monster addThisMonster) {
		this.monsters.add(addThisMonster);
	}
	
	public Monster getMonsterAt(int index) {
		return this.monsters.get(index);
	}
	
	public Monster removeMonsterAt(int index) throws IndexOutOfBoundsException {
		return this.monsters.remove(index);
	}
	
	public boolean removeMonster(Monster thisMonster) {
		return this.monsters.remove(thisMonster);
	}
	
	public int getNumberOfMonsters() {
		return this.monsters.size();
	}
	
	public int getIndexOf (Monster thisMonster){
		int index = 0;
		for (Monster m : monsters){
			if (m.getMonsterName().equals(thisMonster.getMonsterName())){
				break;
			}
			index++;
		}
		return index;
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
	
	public int getNumberOfTrapsHazards() {
		return this.traphazards.size();
	}
	public void resetCreatureStats() {
		if (!players.isEmpty() && players != null) {
			for (Player p : players) {
				p.resetCharacterStats();
			}
		} else if (!monsters.isEmpty() && monsters != null) {
			for (Monster m : monsters) {
				m.resetMonsterStats();
			}
		}
	}
	

	/**
	 * Other functions
	 */
	public int save() {
    	logger.info("Saving Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		return tDao.saveTeam(getName(), getPlayers());
	}
	
	public int saveNPC(List<Monster> monsters) {
    	logger.info("Saving Monsters before saving the team.");
    	/*if(monsters != null && !monsters.isEmpty()){
			List<Monster> monstersDB = mDao.getMonstersByName(monsters.get(0).getMonsterName());
			this.setMonsters(monstersDB);
    	}*/
    	logger.info("Saving NPC Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
    	int id = tDao.saveNPCteam(getName(), getMonsters(), getTraphazards());
    	this.setId(id);
    	
    	
    	MonsterDao mDao = new MonsterDaoImpl();
    	for (Monster m : monsters){
    		int m_id = mDao.saveMonsterInTeam(m.getMonsterName(), m.getCurrentHP(), m.getCurrentHealSurges(), m.getInitiative(),
    			m.isSecondWind(), m.getTempHP(), m.getCharacterSheet(), this);
    		m.setId(m_id);
    	}
		return id;
	}

	public int edit() {
    	logger.info("Editing Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.updateTeam(getId(), getName(), getPlayers());
		return 1;
	}
	
	public int editTeam(CombatEncounter ce) {
		logger.info("Editing Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
    	tDao.updateTeam(getId(), ce, getName(), getPlayers());
		return 1;
	}
	
	public int editNPC(List<Monster> monsters) {
    	logger.info("Updating Monsters before updating the team.");
		MonsterDao mDao = new MonsterDaoImpl();
		List<Monster> monstersDB = mDao.getMonstersInTeam(getId());
		for (Monster m : monstersDB){
			mDao.deleteMonster(m.getId());
		}
		if ( (this.getMonsters() != null && !this.getMonsters().isEmpty()) || 
				(this.getTraphazards() != null && !this.getTraphazards().isEmpty()) ) {
			for (Monster m : monsters) {
				mDao.saveMonsterInTeam(m.getMonsterName(), m.getCurrentHP(),
						m.getCurrentHealSurges(), m.getInitiative(),
						m.isSecondWind(), m.getTempHP(), m.getCharacterSheet(),
						this);
			}
			logger.info("Updating NPC Team " + getName());
			TeamDao tDao = new TeamDaoImpl();
			tDao.updateNPCteam(getId(), getName(), getMonsters(),
					getTraphazards());
			TrapHazardDao thDao = new TrapHazardDaoImpl();
			for (TrapHazard th : getTraphazards()){
				thDao.updateTrapHazardInTeam(th.getId(), th.getName(), th.getAvoidance(), th.getLevel(), th.getAvoidanceSkill(), th.getTriggers(),
					th.getXp(), th.getDifficultyLevel(), th.getCounterMeasureDescription(), th.getType(), th.getRole(), th.getCounterMeasureSkill(),
					this);
			}			
			return 1;
		} else {
			this.removeNPC();
			return -1;
		}
	}
	
	public int editNPCTeam(CombatEncounter ce, List<Monster> monsters) {
		logger.info("Updating Monsters before updating the team.");
		MonsterDao mDao = new MonsterDaoImpl();
		List<Monster> monstersDB = mDao.getMonstersInTeam(getId());
		for (Monster m : monstersDB){
			mDao.deleteMonster(m.getId());
		}
		if ( (this.getMonsters() != null && !this.getMonsters().isEmpty()) || 
				(this.getTraphazards() != null && !this.getTraphazards().isEmpty()) ) {
			for (Monster m : monsters) {
				int id = mDao.saveMonsterInTeam(m.getMonsterName(), m.getCurrentHP(),
						m.getCurrentHealSurges(), m.getInitiative(),
						m.isSecondWind(), m.getTempHP(), m.getCharacterSheet(),
						this);
				m.setId(id);
			}
			logger.info("Updating NPC Team " + getName());
			TeamDao tDao = new TeamDaoImpl();
			tDao.updateNPCteam(getId(), ce, getName(), getMonsters(),
					getTraphazards());
			TrapHazardDao thDao = new TrapHazardDaoImpl();
			for (TrapHazard th : getTraphazards()){
				thDao.updateTrapHazardInTeam(th.getId(), th.getName(), th.getAvoidance(), th.getLevel(), th.getAvoidanceSkill(), th.getTriggers(),
					th.getXp(), th.getDifficultyLevel(), th.getCounterMeasureDescription(), th.getType(), th.getRole(), th.getCounterMeasureSkill(),
					this);
			}			
			return 1;
		} else {
			this.removeNPC();
			return -1;
		}
	}

	public void remove() {
    	logger.info("Removing Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.deleteTeam(getId());
	}
	
	public void removeNPC() {
    	logger.info("Removing Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
    	tDao.updateNPCteam(getId(), null, getName(), getMonsters(), getTraphazards());
		tDao.deleteNPCTeam(getId());
	}

	public List<Object[]> getAll() {
    	logger.info("Getting all teams in database");
    	TeamDao tDao = new TeamDaoImpl();
		return tDao.readAllTeams();
	}

}
