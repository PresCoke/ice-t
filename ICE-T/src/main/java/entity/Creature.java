package entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * Creature Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Creature")
public class Creature {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Creature_id")
    private int id;
	
	@Column(name="player_name")
	private String playerName;
	
	@Column(name="currentHP")
	private int currentHP;
	
	@Column(name="currentHealSurges")
	private int currentHealSurges;
	
	@Column(name="currentLevel")
	private int currentLevel;
	
	@Column(name="secondWind")
	private boolean secondWind;
	
	@Column(name="tempHP")
	private int tempHP;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="Team_id")
	private Team team;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "creature")
	private Stats stats;
	
	@ManyToOne
    @JoinColumn(name="CharacterSheet_id", referencedColumnName="CharacterSheet_id")
//    @JoinColumns({
//        @JoinColumn(name="CharacterSheet_id", referencedColumnName="CharacterSheet_id"),
//        @JoinColumn(name="CharacterSheet_name", referencedColumnName="CharacterSheet_name")
//    })
	private CharacterSheet characterSheet;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "creature", orphanRemoval = true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.DELETE})
	private List<Effect> effects;


	/**
	 * Default constructor
	 */
	public Creature() {
	}

	/**
	 * Constructors
	 * @param name
	 */
	public Creature(String playerName) {
		this.playerName = playerName;
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


	public int getCurrentLevel() {
		return currentLevel;
	}


	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
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

	public CharacterSheet getCharacterSheet() {
		return characterSheet;
	}

	public void setCharacterSheet(CharacterSheet characterSheet) {
		this.characterSheet = characterSheet;
	}	
}