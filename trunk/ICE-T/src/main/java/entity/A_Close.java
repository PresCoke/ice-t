package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Attack Close Class
 * @author TimHP
 *
 */
@Entity
@Table(name="A_Close")
public class A_Close extends Attack_Type {

    @Column(name="A_Close_id")
    private int id;
    
	@Column(name="close_size")
	private int close_size;
	
	//Enum
	@Transient
	private EntityEnum.A_Use_Type closeType;
	

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
		return close_size;
	}

	public void setSize(int size) {
		this.close_size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}