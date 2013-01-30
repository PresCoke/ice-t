package entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	
	@Column(name="name")
	private String name;
	
	//Associations
	@OneToOne
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;
	
	@OneToMany(mappedBy = "tally")
	private Set<Tuple> tuples;


	/**
	 * Default constructor
	 */
	public Tally() {
	}
	
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CombatEncounter getCombatEncounter() {
		return combatEncounter;
	}

	public void setCombatEncounter(CombatEncounter combatEncounter) {
		this.combatEncounter = combatEncounter;
	}
	
	public Set<Tuple> getTuples() {
		return tuples;
	}

	public void setTuples(Set<Tuple> tuples) {
		this.tuples = tuples;
	}
}
