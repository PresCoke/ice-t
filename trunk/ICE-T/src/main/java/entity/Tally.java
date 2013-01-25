package entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Tally Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Tally")
public class Tally {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Tally_id")
    private int id;
	
	@Id
	@Column(name="name")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	//@JoinTable(name="Tuple")
	private Set<Tuple> tuple;

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

	public Set<Tuple> getTuple() {
		return tuple;
	}

	public void setTuple(Set<Tuple> tuple) {
		this.tuple = tuple;
	}
	
}
