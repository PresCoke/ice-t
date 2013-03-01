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
	

	/**
	 * Other functions
	 */
	public void save() {
    	logger.info("Saving Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.saveTeam(getName(), getPlayers());
	}
	
	public void saveNPC(List<Monster> monsters) {
    	logger.info("Saving Monsters before saving the team.");
		MonsterDao mDao = new MonsterDaoImpl();
    	for (Monster m : monsters){
    		mDao.saveMonster(m.getMonsterName(), m.getCurrentHP(), m.getCurrentHealSurges(), m.getInitiative(),
    			m.isSecondWind(), m.getTempHP(), m.getCharacterSheet());
    	}
		List<Monster> monstersDB = mDao.getMonstersByName(monsters.get(0).getMonsterName());
		this.setMonsters(monstersDB);
    	logger.info("Saving NPC Team " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.saveNPCteam(getName(), getMonsters(), getTraphazards());
	}

	public void edit() {
    	logger.info("Editing Combat Encounter " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.updateTeam(getId(), getName(), getPlayers());
	}

	public void remove() {
    	logger.info("Removing Combat Encounter " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.deleteTeam(getId());
	}

	public List<Object[]> getAll() {
    	logger.info("Getting all teams in database");
    	TeamDao tDao = new TeamDaoImpl();
		return tDao.readAllTeams();
	}

}
