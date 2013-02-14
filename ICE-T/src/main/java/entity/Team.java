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
	private List<Player> creatures;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<TrapHazard> traphazards;
	
	
	/**
	 * Default constructor
	 */
	public Team() {
		this.name= "";
		this.creatures = new ArrayList<Player>();
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		this.name=name;
		this.creatures = new ArrayList<Player>();
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
	
	public List<Player> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<Player> creatures) {
		this.creatures = creatures;
	}
	
	public void removeAllCreatures() {
		this.creatures.removeAll(creatures);
	}
	
	public void addCreature(Player addThisCreature) {
		this.creatures.add(addThisCreature);
	}
	
	public Player getCreatureAt(int index) {
		return this.creatures.get(index);
	}
	
	public Player removeCreatureAt(int index) throws IndexOutOfBoundsException {
		return this.creatures.remove(index);
	}
	
	public boolean removeCreature(Player thisCreature) {
		return this.creatures.remove(thisCreature);
	}
	
	public int getNumberOfCreatures() {
		return this.creatures.size();
	}
	
	public int getIndexOf (Player thisCreature){
		int index = 0;
		for (Player c : creatures){
			if (c.getPlayerName().equals(thisCreature.getPlayerName())){
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
		tDao.saveTeam(getName(), getCreatures());
	}

	public void edit() {
    	logger.info("Editing Combat Encounter " + getName());
    	TeamDao tDao = new TeamDaoImpl();
		tDao.updateTeam(getId(), getName(), getCreatures());
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
