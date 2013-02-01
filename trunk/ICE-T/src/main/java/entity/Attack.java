package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import entity.EntityEnum.A_Ability;
import entity.EntityEnum.A_Action;
import entity.EntityEnum.A_Defense;
import entity.EntityEnum.A_Effect_Type;
import entity.EntityEnum.A_Sustain;
import entity.EntityEnum.A_Use_Type;
import entity.EntityEnum.CS_Resistance_Type;

/**
 * Attack Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Attack")
public class Attack {

    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Attack_id")
    private int id;
    
    @Column(name="Attack_name")
    private String attackName;
	
	//Target
	@Column(name="primaryTarget")
	private String primaryTarget;
	@Column(name="secondaryTarget")
	private String secondaryTarget;
	
	//Keywords	
	@Column(name="accessories")
	private String accessories;
	@Column(name="powerSource")
	private String powerSource;
	@Column(name="frequency")
	private int frequency;	
	@Column(name="hit")
	private String hit;
	@Column(name="miss")
	private String miss;	
	@Column(name="basic")
	private boolean basic;
	@Column(name="triggers")
	private String trigger;
	
	//Enum
	@Column(name="effectType")
	private EntityEnum.A_Effect_Type effectType;
	@Column(name="ability")
	private EntityEnum.A_Ability ability;
	@Column(name="damageType")
	private EntityEnum.CS_Resistance_Type damageType;
	@Column(name="defense")
	private EntityEnum.A_Defense defense;
	@Column(name="sustain")
	private EntityEnum.A_Sustain sustain;
	@Column(name="action")
	private EntityEnum.A_Action action;
	@Column(name="useType")
	private EntityEnum.A_Use_Type useType;
	
	//Associations
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="CharacterSheet_id")
	private CharacterSheet characterSheet;
	
	@OneToOne(mappedBy="attack", orphanRemoval=true)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
		org.hibernate.annotations.CascadeType.PERSIST})
	private Attack_Type attackType;
	
	
	/**
	 * Default constructor
	 */
	public Attack() {
		this.attackName = "";
		this.ability = A_Ability.STR;
		this.accessories = "";
		this.action = A_Action.standard;
		this.attackType = new Attack_Type();
		this.basic = false;
		this.damageType = CS_Resistance_Type.acid;
		this.defense = A_Defense.AC;
		this.effectType = A_Effect_Type.charm;
		this.frequency = 0;
		this.hit = "";
		this.miss = "";
		this.powerSource = "";
		this.primaryTarget = "";
		this.secondaryTarget = "";
		this.sustain = A_Sustain.NONE;
		this.trigger = "";
		this.useType = A_Use_Type.atWill;
	}
	
	/**
	 * Constructor
	 * @param attackName
	 */
	public Attack(String attackName) {
		this.attackName = attackName;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public CharacterSheet getCharacterSheet() {
		return characterSheet;
	}

	public void setCharacterSheet(CharacterSheet characterSheet) {
		this.characterSheet = characterSheet;
	}

	public String getAttackName() {
		return attackName;
	}

	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}

	public Attack_Type getAttackType() {
		return attackType;
	}

	public void setAttackType(Attack_Type attackType) {
		this.attackType = attackType;
	}
}
