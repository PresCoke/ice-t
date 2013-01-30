package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	@OneToOne(mappedBy = "creature")
	private Stats stats;
	
	@ManyToOne
	@JoinColumn (name="CharacterSheet_id")
	private CharacterSheet characterSheet;
	
	@OneToMany(mappedBy = "creature")
	private Set<Attack> attacks;

	@OneToMany(mappedBy = "creature")
	private Set<Effect> effects;


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
	
	public Set<Effect> getEffects() {
		return effects;
	}

	public void setEffects(Set<Effect> effects) {
		this.effects = effects;
	}

	public CharacterSheet getCharacterSheet() {
		return characterSheet;
	}

	public void setCharacterSheet(CharacterSheet characterSheet) {
		this.characterSheet = characterSheet;
	}

	public Set<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(Set<Attack> attacks) {
		this.attacks = attacks;
	}
	
	
}
