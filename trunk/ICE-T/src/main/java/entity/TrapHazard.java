package entity;

/**
 * TrapHazard Class
 * @author TimHP
 *
 */
public class TrapHazard extends Entity {

	private int avoidance;
	private int level;
	private String skill;
	private String triggers;
	private int value;
	private int xp;
	private EntityEnum.T_Type type;
	private EntityEnum.T_Role role;
	private EntityEnum.T_CounterMeasureSkill counterMeasureSkill;
	private int difficultyLevel;
	private String counterMeasureDescription;
	
	/**
	 * Constructor
	 * @param name
	 */
	public TrapHazard(String name) {
		super(name);
	}

	
	//Getters and Setters
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
