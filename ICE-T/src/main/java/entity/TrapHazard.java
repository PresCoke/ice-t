package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.dao.AttackDao;
import entity.dao.AttackDaoImpl;
import entity.dao.Attack_TypeDao;
import entity.dao.Attack_TypeDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;

/**
 * TrapHazard Class
 * @author TimHP
 *
 */
@Entity
@Table(name="TrapHazard")
public class TrapHazard implements EntityM {
	
	private static final Logger logger = Logger.getLogger(TrapHazard.class);
	//TODO: add description variable
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
	
	@Column(name="xp")
	private int xp;
	
	@Column(name="counterMeasuredifficultyLevel")
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
	@OneToOne(mappedBy="trap", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.PERSIST})
	private Attack attack;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="Team_id")
	private Team team;

	
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
		this.level = 1;
		this.role = EntityEnum.T_Role.blaster;
		this.triggers = "";
		this.type = EntityEnum.T_Type.hazard;
		this.xp = 0;
	}

	
	/**
	 * Constructor
	 * @param name
	 */
	public TrapHazard(String name) {
		this.name=name;
	}

	
	/*
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

	public EntityEnum.T_CounterMeasureSkill getAvoidanceSkill() {
		return avoidanceSkill;
	}

	public void setAvoidanceSkill(EntityEnum.T_CounterMeasureSkill avoidanceSkill) {
		this.avoidanceSkill = avoidanceSkill;
	}

	public String getTriggers() {
		return triggers;
	}

	public void setTriggers(String triggers) {
		this.triggers = triggers;
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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}


	/**
	 * Other functions
	 */
	public int save() {
		logger.info("Saving TrapHazard " + this.getName());
		TrapHazardDao thDao = new TrapHazardDaoImpl();
		return thDao.saveTrapHazard(getName(), getAvoidance(), getLevel(), getAvoidanceSkill(), getTriggers(),getXp(),
				getDifficultyLevel(), getCounterMeasureDescription(), getType(), getRole(), getCounterMeasureSkill(),
				getAttack(), getAttack().getAttackType());
	}


	public int edit() {
		TrapHazardDao thDao = new TrapHazardDaoImpl();
		TrapHazard thDB = thDao.getTrapHazard(getId());
        logger.debug("Deleting previous traphazard's attack");
    	AttackDao aDao = new AttackDaoImpl();
        aDao.deleteAttackFromTrap(thDB.getAttack().getId());
        logger.debug("Setting new traphazard's attack");
        Attack a = this.getAttack();
        Attack_TypeDao atDao = new Attack_TypeDaoImpl();
   		int attackId = aDao.saveAttackForTrap(a.getAttackName(), a.getPrimaryTarget(), a.getSecondaryTarget(), a.getAccessories(), a.getPowerSource(),
    				a.getFrequency(), a.getHit(), a.getMiss(), a.isBasic(), a.getTrigger(), a.getEffectType(), a.getAbility(),
    				a.getDamageType(), a.getDefense(), a.getSustain(), a.getAction(), a.getUseType(), getId());
        logger.debug("Setting attack's type");
    	atDao.saveAttackType(a.getAttackType(), attackId);
		logger.info("Editing TrapHazard " + this.getName());
		thDao.updateTrapHazard(getId(), getName(), getAvoidance(), getLevel(), getAvoidanceSkill(), getTriggers(),getXp(),
				getDifficultyLevel(), getCounterMeasureDescription(), getType(), getRole(), getCounterMeasureSkill());
		return 1;
		
	}

	public void remove() {
		logger.info("Removing TrapHazard " + this.getName());
		TrapHazardDao thDao = new TrapHazardDaoImpl();
		thDao.deleteTrapHazard(getId());		
	}


	public List<Object[]> getAll() {
    	logger.info("Getting all traps in database");
    	TrapHazardDao thDao = new TrapHazardDaoImpl();
		return thDao.readAllTrapHazards();
	}

}
