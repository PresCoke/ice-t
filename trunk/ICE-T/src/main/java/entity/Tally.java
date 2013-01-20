package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="Tuple")
	private Tuple tuple;

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
