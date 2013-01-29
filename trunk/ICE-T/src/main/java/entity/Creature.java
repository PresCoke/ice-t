package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
//	@ManyToOne
//	@JoinColumn (name="Team_id")
//	private Team team;
	
//	@OneToOne(mappedBy = "creature")
//	private Stats stats;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	//@JoinTable(name="Attack")
//	private Set<Attack> attacks;
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	//@JoinTable(name="Effect")
//	private Set<Effect> effects;


	/**
	 * Default constructor
	 * @param name
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

	
//	public Team getTeam() {
//		return team;
//	}
//
//	public void setTeam(Team team) {
//		this.team = team;
//	}
	
}
