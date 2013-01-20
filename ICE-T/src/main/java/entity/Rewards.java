package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Rewards Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Rewards")
public class Rewards {

	@Column(name="XP")
	private int XP;
	@Column(name="treasure")
	private String treasure;
	
	/**
	 * Default constructor
	 */
	public Rewards() {
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
	
	

}
