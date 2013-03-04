package entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.PlayerDao;
import entity.dao.PlayerDaoImpl;

/**
 * Creature Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Player")
public class Player implements EntityM, Comparable<Player> {
	
	private static final Logger logger = Logger.getLogger(Player.class);

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Player_id")
    private int id;
	
	@Column(name="player_name")
	private String playerName;
	
	@Column(name="currentHP")
	private int currentHP;
	
	@Column(name="currentHealSurges")
	private int currentHealSurges;
	
	@Column(name="initiative")
	private int initiative;
	
	@Column(name="secondWind")
	private boolean secondWind;
	
	@Column(name="tempHP")
	private int tempHP;
	
	//Associations
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="Team_id")
	private Team team;
	
	@OneToOne(mappedBy = "player", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.PERSIST})
	private Stats stats;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="CharacterSheet_id")
	private CharacterSheet characterSheet;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "player", orphanRemoval = true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Effect> effects;



	/**
	 * Default constructor
	 */
	public Player() {
		playerName = "";
		currentHP = 0;
		currentHealSurges = 0;
		initiative = 0;
		secondWind = false;
		tempHP = 0;
	}

	/**
	 * Constructors
	 * @param name
	 */
	public Player(String playerName) {
		this.playerName = playerName;
		currentHP = 0;
		currentHealSurges = 0;
		initiative = 0;
		secondWind = false;
		tempHP = 0;
	}
	
	public Player(String name, CharacterSheet sheet) {
		playerName = name;
		characterSheet = sheet;
		currentHP = characterSheet.getMaxHP();
		currentHealSurges = characterSheet.getSurgesPerDay();
		initiative = characterSheet.getInitiative();
		secondWind = false;
		tempHP = 0;
	}
	
	public Player(CharacterSheet sheet) {
		playerName = sheet.getName();
		characterSheet = sheet;
		currentHP = characterSheet.getMaxHP();
		currentHealSurges = characterSheet.getSurgesPerDay();
		initiative = characterSheet.getInitiative();
		secondWind = false;
		tempHP = 0;
	}
	/**
	 * Getters & Setters
	 */
	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public int getCurrentHP() {
		return currentHP;
	}


	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}


	public int getCurrentHealSurges() {
		return currentHealSurges;
	}


	public void setCurrentHealSurges(int currentHealSurges) {
		this.currentHealSurges = currentHealSurges;
	}


	public int getInitiative() {
		return initiative;
	}


	public void setInitiative(int initiative) {
		this.initiative = characterSheet.getInitiative() + initiative;
	}


	public boolean isSecondWind() {
		return secondWind;
	}


	public void setSecondWind(boolean secondWind) {
		this.secondWind = secondWind;
	}

	
	public int getTempHP() {
		return tempHP;
	}


	public void setTempHP(int tempHP) {
		this.tempHP = tempHP;
	}
	
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}
	
	public List<Effect> getEffects() {
		return effects;
	}

	public void setEffects(List<Effect> effects) {
		this.effects = effects;
	}
	
	public void removeAllEffects() {
		this.effects.removeAll(effects);
	}
	
	public void addEffect(Effect addThisEffect) {
		this.effects.add(addThisEffect);
	}
	
	public Effect getEffectAt(int index) {
		return this.effects.get(index);
	}
	
	public Effect removeEffectAt(int index) throws IndexOutOfBoundsException {
		return this.effects.remove(index);
	}
	
	public boolean removeEffect(Effect thisEffect) {
		return this.effects.remove(thisEffect);
	}
	
	public int getNumberOfEffects() {
		return this.effects.size();
	}
	
	public int getIndexOf (Effect thisEffect){
		int index = 0;
		for (Effect e : effects){
			if (e.getName().equals(thisEffect.getName())){
				break;
			}
			index++;
		}
		return index;
	}
	

	public CharacterSheet getCharacterSheet() {
		return characterSheet;
	}

	public void setCharacterSheet(CharacterSheet characterSheet) {
		this.characterSheet = characterSheet;
	}

	/**
	 * Other functions
	 */
	public int save() {
    	logger.info("Saving Creature " + getPlayerName());
    	PlayerDao pDao = new PlayerDaoImpl();
    	return pDao.savePlayer(getPlayerName(), getCurrentHP(), getCurrentHealSurges(), getInitiative(),
				isSecondWind(), getTempHP(), getCharacterSheet());
		
	}

	public int edit() {
    	logger.info("Editing Creature " + getPlayerName());
    	PlayerDao pDao = new PlayerDaoImpl();
    	pDao.updatePlayer(getId(), getPlayerName(), getCurrentHP(), getCurrentHealSurges(), getInitiative(),
				isSecondWind(), getTempHP());
    	return 1;
	}

	public void remove() {
    	logger.info("Removing Creature " + getPlayerName());
    	PlayerDao pDao = new PlayerDaoImpl();
    	pDao.deletePlayer(getId());		
	}

	public List<Object[]> getAll() {
    	logger.info("Getting all Creatures in database");
    	PlayerDao pDao = new PlayerDaoImpl();
    	return pDao.readAllPlayers();
	}

	public int compareTo(Player other) {
        //Descending sorting
		int initiativeOther = other.getInitiative(); 
		int initiativeThis = this.getInitiative(); 
		if (initiativeOther < initiativeThis){
			return -1; 
		} else if(initiativeOther == initiativeThis){
			return 0; 
		} else {
			return 1; 
		}
	}
}
