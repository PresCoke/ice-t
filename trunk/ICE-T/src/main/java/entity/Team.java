package entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * Team Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Team")
public class Team implements EntityM {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Team_id")
    private int id;	
    
	@Column(name="Team_name")
	private String name;

	//Associations
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
		org.hibernate.annotations.CascadeType.PERSIST})
	private List<Creature> creatures;
	
	
	/**
	 * Default constructor
	 */
	public Team() {
		this.name= "";
		this.creatures = new ArrayList<Creature>();
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Team(String name) {
		this.name=name;
		this.creatures = new ArrayList<Creature>();
	}

	/**
	 * Getters & Setters
	 */
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(List<Creature> creatures) {
		this.creatures = creatures;
	}
	
	public void removeAllCreatures() {
		this.creatures.removeAll(creatures);
	}
	
	public void addCreature(Creature addThisCreature) {
		this.creatures.add(addThisCreature);
	}
	
	public Creature getCreatureAt(int index) {
		return this.creatures.get(index);
	}
	
	public Creature removeCreatureAt(int index) throws IndexOutOfBoundsException {
		return this.creatures.remove(index);
	}
	
	public boolean removeCreature(Creature thisCreature) {
		return this.creatures.remove(thisCreature);
	}
	
	public int getNumberOfCreatures() {
		return this.creatures.size();
	}
	
	public int getIndexOf (Creature thisCreature){
		int index = 0;
		for (Creature c : creatures){
			if (c.getPlayerName().equals(thisCreature.getPlayerName())){
				break;
			}
			index++;
		}
		return index;
	}
	


	/**
	 * Other functions
	 */
	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void edit() {
		// TODO Auto-generated method stub
		
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
