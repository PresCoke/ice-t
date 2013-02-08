package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Effect Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Effect")
//TODO: add effect type... maybe...
public class Effect implements EntityM {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Effect_id")
    private int id;	
	
	@Column(name="Effect_name")
	private String name;
	
	@Column(name="changes")
	private String changes;
	
	@Column(name="metrics")
	private String metrics;
	
	//Enum
	@Column(name="duration")
	private EntityEnum.E_Duration duration;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="Creature_id")
	private Creature creature;


	/**
	 * Default constructor
	 */
	public Effect() {
		this.name="";
		this.changes="";
		this.metrics="";
		this.duration = EntityEnum.E_Duration.endOfTheEncounter;
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public Effect(String name) {
		this.name=name;
		this.changes="";
		this.metrics="";
		this.duration = EntityEnum.E_Duration.endOfTheEncounter;
	}

	
	/**
	 * Getters & Setters
	 */
	public EntityEnum.E_Duration getDuration() {
		return duration;
	}

	public void setDuration(EntityEnum.E_Duration duration) {
		this.duration = duration;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public String getMetrics() {
		return metrics;
	}

	public void setMetrics(String metrics) {
		this.metrics = metrics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Creature getCreature() {
		return creature;
	}

	public void setCreature(Creature creature) {
		this.creature = creature;
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
