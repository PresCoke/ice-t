package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * Effect Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Effect")
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
	
	@Column(name="damage")
	private int damage;
	
	@Column(name="metrics")
	private String metrics;
	
	//Enum
	@Column(name="duration")
	private EntityEnum.E_Duration duration;
		
	/**
	 * Constructor
	 * @param name
	 */
	public Effect(String name) {
		this.name=name;
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

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
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
