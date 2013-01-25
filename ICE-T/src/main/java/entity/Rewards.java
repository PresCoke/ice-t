package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
