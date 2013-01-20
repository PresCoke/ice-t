package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attack Close Class
 * @author TimHP
 *
 */
@Entity
@Table(name="A_Close")
public class A_Close extends Attack_Type {

	@Id
	@GeneratedValue
	@Column(name="closeType")
	private EntityEnum.A_Use_Type closeType;
	
	@Column(name="size")
	private int size;
	

	/**
	 * Default Constructor
	 */
	public A_Close() {
	}

	/**
	 * Getters & Setters
	 */
	public EntityEnum.A_Use_Type getCloseType() {
		return closeType;
	}

	public void setCloseType(EntityEnum.A_Use_Type closeType) {
		this.closeType = closeType;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
