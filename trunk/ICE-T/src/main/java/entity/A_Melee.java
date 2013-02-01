package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Attack Melee Class
 * @author TimHP
 *
 */
@Entity
@Table(name="A_Melee")
public class A_Melee extends Attack_Type {

    @Column(name="A_Melee_id")
    private int id;
	
	@Column(name="melee_reach")
	private int melee_reach;
	
	/**
	 * Default constructor
	 */
	public A_Melee() {
	}

	
	/**
	 * Getters and Setters
	 */
	public int getReach() {
		return melee_reach;
	}

	public void setReach(int reach) {
		this.melee_reach = reach;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}