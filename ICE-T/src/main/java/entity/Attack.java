package entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Attack Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Attack")
public class Attack {
	
	//Target
	@Column(name="primaryTarget")
	private String primaryTarget;
	
	@Column(name="secondaryTarget")
	private String secondaryTarget;
	
	//Keywords
	@Id
	@GeneratedValue
	@Column(name="name")
	private String name;
	
	@Column(name="accessories")
	private String accessories;
	
	@Column(name="powerSource")
	private String powerSource;
	
	@Column(name="effectType")
	private EntityEnum.A_Effect_Type effectType;
	
	@Column(name="damageType")
	private EntityEnum.CS_Resistance_Type damageType;
	
	@Column(name="frequency")
	private int frequency;
	
	@Column(name="ability")
	private EntityEnum.A_Ability ability;
	
	@Column(name="hit")
	private String hit;
	
	@Column(name="miss")
	private String miss;
	
	@Column(name="defense")
	private EntityEnum.A_Defense defense;
	
	@Column(name="sustain")
	private EntityEnum.A_Sustain sustain;
	
	@Column(name="action")
	private EntityEnum.A_Action action;
	
	@Column(name="useType")
	private EntityEnum.A_Use_Type useType;
	
	@Column(name="basic")
	private boolean basic;
	
	@Column(name="trigger")
	private String trigger;
	
	//Associations
	@OneToOne(cascade = CascadeType.ALL)
	private Attack_Type attackType;
	
	/**
	 * Default constructor
	 */
	public Attack() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getters & Setters
	 */
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
