package entity;

/**
 * Effect Class
 * @author TimHP
 *
 */
public class Effect extends Entity {
	
	private EntityEnum.E_Duration duration;
	private String changes;
	private int damage;
	private String metrics;
		
	/**
	 * Constructor
	 * @param name
	 */
	public Effect(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	//Getters & Setters
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

	
}
