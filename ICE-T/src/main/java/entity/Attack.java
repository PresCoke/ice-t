package entity;

public class Attack {
	
	//Target
	private String primaryTarget;
	private String secondaryTarget;
	
	//Keywords
	private String accessories;
	private String powerSource;
	private EntityEnum.A_Effect_Type effectType;
	private EntityEnum.CS_Resistance_Type damageType;
	private int frequency;
	private String name;
	private EntityEnum.A_Ability ability;
	private String hit;
	private String miss;
	private EntityEnum.A_Defense defense;
	private EntityEnum.A_Sustain sustain;
	private EntityEnum.A_Action action;
	private EntityEnum.A_Use_Type useType;
	private boolean basic;
	private String trigger;
	
	public Attack() {
		// TODO Auto-generated constructor stub
	}

	//Getters & Setters
	public String getPrimaryTarget() {
		return primaryTarget;
	}

	public void setPrimaryTarget(String primaryTarget) {
		this.primaryTarget = primaryTarget;
	}

	public String getSecondaryTarget() {
		return secondaryTarget;
	}

	public void setSecondaryTarget(String secondaryTarget) {
		this.secondaryTarget = secondaryTarget;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}

	public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}

	public EntityEnum.A_Effect_Type getEffectType() {
		return effectType;
	}

	public void setEffectType(EntityEnum.A_Effect_Type effectType) {
		this.effectType = effectType;
	}

	public EntityEnum.CS_Resistance_Type getDamageType() {
		return damageType;
	}

	public void setDamageType(EntityEnum.CS_Resistance_Type damageType) {
		this.damageType = damageType;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityEnum.A_Ability getAbility() {
		return ability;
	}

	public void setAbility(EntityEnum.A_Ability ability) {
		this.ability = ability;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getMiss() {
		return miss;
	}

	public void setMiss(String miss) {
		this.miss = miss;
	}

	public EntityEnum.A_Defense getDefense() {
		return defense;
	}

	public void setDefense(EntityEnum.A_Defense defense) {
		this.defense = defense;
	}

	public EntityEnum.A_Sustain getSustain() {
		return sustain;
	}

	public void setSustain(EntityEnum.A_Sustain sustain) {
		this.sustain = sustain;
	}

	public EntityEnum.A_Action getAction() {
		return action;
	}

	public void setAction(EntityEnum.A_Action action) {
		this.action = action;
	}

	public EntityEnum.A_Use_Type getUseType() {
		return useType;
	}

	public void setUseType(EntityEnum.A_Use_Type useType) {
		this.useType = useType;
	}

	public boolean isBasic() {
		return basic;
	}

	public void setBasic(boolean basic) {
		this.basic = basic;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}
}
