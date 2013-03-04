package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;
	
	@OneToMany(mappedBy = "tally", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.DELETE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Tuple> tuples;


	/**
	 * Default constructor
	 */
	public Tally() {
		this.name = "";
		this.tuples = new ArrayList<Tuple>();
		this.tuples.add(new Tuple());
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Tally(String name) {
		this.name = name;
		this.tuples = new ArrayList<Tuple>();
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
	
	public List<Tuple> getTuples() {
		return tuples;
	}

	public void setTuples(List<Tuple> tuples) {
		this.tuples = tuples;
	}
}
