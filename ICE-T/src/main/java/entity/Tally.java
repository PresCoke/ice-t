package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tally Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Tally")
public class Tally {

	@Id
	@GeneratedValue
	@Column(name="name")
	private String name;
	

	/**
	 * Constructor
	 * @param name
	 */
	public Tally(String name) {
		this.name = name;
	}

	/**
	 * Getters & Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
