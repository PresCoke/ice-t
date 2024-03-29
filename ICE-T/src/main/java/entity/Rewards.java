package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Rewards Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Rewards")
public class Rewards {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Rewards_id")
    private int id;
	
	@Column(name="XP")
	private int XP;
	
	@Column(name="treasure")
	private String treasure;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;
	

	/**
	 * Default constructor
	 */
	public Rewards() {
		this.XP = 0;
		this.treasure = "";
	}

	/**
	 * Constructor
	 * @param XP
	 * @param treasure
	 */
	public Rewards(int XP, String treasure) {
		this.XP = XP;
		this.treasure = treasure;
	}
	
	/**
	 * Getters & Setters
	 */
	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public String getTreasure() {
		return treasure;
	}

	public void setTreasure(String treasure) {
		this.treasure = treasure;
	}
	
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

}
