package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * EntityM Class
 * @author TimHP and James Begg
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="EntityM")
public abstract class EntityM {

	@Id
	@GeneratedValue
	@Column(name="EntityM_id")
	private int id;
	
	@Id
	@Column(name="name")
	private String name;
	
	/**
	 * Default constructor
	 */
	public EntityM(){
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public EntityM(String name){
		this.name = name;
	}
	
	/**
	 * Save an entity
	 */
	public abstract void save();
	
	/**
	 * Edit an entity
	 */
	public abstract void edit();
	
	/**
	 * Remove an entity
	 */
	public abstract void remove();

	/**
	 * Getters & Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
}
