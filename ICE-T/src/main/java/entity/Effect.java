package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.EffectDao;
import entity.dao.EffectDaoImpl;

/**
 * Effect Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Effect")
//TODO: add effect type... maybe...
public class Effect {
	
	private static final Logger logger = Logger.getLogger(Effect.class);

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
	private Player creature;



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
	
	public Player getCreature() {
		return creature;
	}

	public void setCreature(Player creature) {
		this.creature = creature;
	}

	
	/**
	 * Other functions
	 */
	public void save(List<Player> creatures) {
    	logger.info("Saving Effect " + getName());
    	EffectDao eDao = new EffectDaoImpl();
    	eDao.saveEffect(getName(), getChanges(), getMetrics(), getDuration(), creatures);
	}

	public void remove(List<Integer> ids) {
    	logger.info("Removing Effect " + getName());
    	EffectDao eDao = new EffectDaoImpl();
    	eDao.deleteEffects(ids);	
	}
	
	public List<Object[]> getAll(){
    	logger.info("Getting all effects in database");
    	EffectDao eDao = new EffectDaoImpl();
    	return eDao.readAllEffects();
	}
	
	
}
