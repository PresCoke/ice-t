package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Effect Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Effect")
public class Effect extends EntityM {
	
	@Column(name="duration")
	private EntityEnum.E_Duration duration;
	
	@Column(name="changes")
	private String changes;
	
	@Column(name="damage")
	private int damage;
	
	@Column(name="metrics")
	private String metrics;
		
	/**
	 * Constructor
	 * @param name
	 */
	public Effect(String name) {
		super(name);
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

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

	
}
