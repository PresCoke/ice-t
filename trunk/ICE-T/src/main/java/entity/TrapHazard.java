package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * TrapHazard Class
 * @author TimHP
 *
 */
@Entity
@Table(name="TrapHazard")
public class TrapHazard extends EntityM {

    @Column(name="TrapHazard_id")
    private int id;	
	
	@Column(name="avoidance")
	private int avoidance;
	
	@Column(name="levelTH")
	private int level;
	
	@Column(name="skill")
	private String skill;
	
	@Column(name="triggers")
	private String triggers;
	
	@Column(name="valueTH")
	private int value;
	
	@Column(name="xp")
	private int xp;
	
	@Column(name="difficultyLevel")
	private int difficultyLevel;
	
	@Column(name="counterMeasureDescription")
	private String counterMeasureDescription;
	
	//Enum
	@Transient
	private EntityEnum.T_Type type;
	@Transient
	private EntityEnum.T_Role role;
	@Transient
	private EntityEnum.T_CounterMeasureSkill counterMeasureSkill;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;


	/**
	 * Default constructor
	 */
	public TrapHazard() {
		super();
	}

	
	/**
	 * Constructor
	 * @param name
	 */
	public TrapHazard(String name) {
		super(name);
	}

	
	/**
	 * Getters & Setters
	 */
	public int getAvoidance() {
		return avoidance;
	}

	public void setAvoidance(int avoidance) {
		this.avoidance = avoidance;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getTriggers() {
		return triggers;
	}

	public void setTriggers(String triggers) {
		this.triggers = triggers;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public EntityEnum.T_Type getType() {
		return type;
	}

	public void setType(EntityEnum.T_Type type) {
		this.type = type;
	}

	public EntityEnum.T_Role getRole() {
		return role;
	}

	public void setRole(EntityEnum.T_Role role) {
		this.role = role;
	}


	public EntityEnum.T_CounterMeasureSkill getCounterMeasureSkill() {
		return counterMeasureSkill;
	}


	public void setCounterMeasureSkill(
			EntityEnum.T_CounterMeasureSkill counterMeasureSkill) {
		this.counterMeasureSkill = counterMeasureSkill;
	}


	public int getDifficultyLevel() {
		return difficultyLevel;
	}


	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}


	public String getCounterMeasureDescription() {
		return counterMeasureDescription;
	}


	public void setCounterMeasureDescription(String counterMeasureDescription) {
		this.counterMeasureDescription = counterMeasureDescription;
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