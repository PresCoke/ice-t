package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/**
 * TrapHazard Class
 * @author TimHP
 *
 */
@Entity
@Table(name="TrapHazard")
public class TrapHazard implements EntityM {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="TrapHazard_id")
    private int id;	
	
	@Column(name="TrapHazard_name")
	private String name;
	
	@Column(name="avoidance")
	private int avoidance;
	
	@Column(name="levelTH")
	private int level;
	
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
	@Column(name="typeTH")
	private EntityEnum.T_Type type;
	
	@Column(name="roleTH")
	private EntityEnum.T_Role role;
	
	@Column(name="counterMeasureSkillTH")
	private EntityEnum.T_CounterMeasureSkill counterMeasureSkill;
	
	@Column(name="avoidanceSkill")
	private EntityEnum.T_CounterMeasureSkill avoidanceSkill;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="CombatEncounter_id")
	private CombatEncounter combatEncounter;
	
	@OneToOne(mappedBy="trap")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.PERSIST})
	private Attack attack;


	/**
	 * Default constructor
	 */
	public TrapHazard() {
		this.name="";
		this.avoidance=0;
		this.attack = new Attack();
		this.avoidanceSkill = EntityEnum.T_CounterMeasureSkill.acrobatics;
		this.counterMeasureDescription = "";
		this.counterMeasureSkill = EntityEnum.T_CounterMeasureSkill.acrobatics;
		this.difficultyLevel = 0;
		this.level = 0;
		this.role = EntityEnum.T_Role.blaster;
		this.triggers = "";
		this.type = EntityEnum.T_Type.hazard;
		this.value = 0;
		this.xp = 0;
	}

	
	/**
	 * Constructor
	 * @param name
	 */
	public TrapHazard(String name) {
		this.name=name;
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

	public EntityEnum.T_CounterMeasureSkill getSkill() {
		return avoidanceSkill;
	}

	public void setSkill(EntityEnum.T_CounterMeasureSkill skill) {
		this.avoidanceSkill = skill;
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


	public void setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill counterMeasureSkill) {
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
	
	
	public Attack getAttack() {
		return attack;
	}


	public void setAttack(Attack attack) {
		this.attack = attack;
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
