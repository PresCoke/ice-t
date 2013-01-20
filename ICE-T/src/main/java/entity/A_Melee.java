package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attack Melee Class
 * @author TimHP
 *
 */
@Entity
@Table(name="A_Melee")
public class A_Melee extends Attack_Type {
	
	@Id
	@GeneratedValue
	@Column(name="reach")
	private int reach;
	
	/**
	 * Default constructor
	 */
	public A_Melee() {
	}

	
	/**
	 * Getters and Setters
	 */
	public int getReach() {
		return reach;
	}

	public void setReach(int reach) {
		this.reach = reach;
	}

}
