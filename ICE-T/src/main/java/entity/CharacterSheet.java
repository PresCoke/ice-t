package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * CharacterSheet Class
 * @author TimHP and James Begg
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="CharacterSheet")
public class CharacterSheet extends EntityM {

	//Skills
	@Column(name="acrobatics")
	private int acrobatics;
	@Column(name="athletics")
	private int athletics;
	@Column(name="arcana")
	private int arcana;
	@Column(name="bluff")
	private int bluff;
	@Column(name="diplomacy")
	private int diplomacy;
	@Column(name="dungeoneering")
	private int dungeoneering;
	@Column(name="endurance")
	private int endurance;
	@Column(name="heal")
	private int heal;
	@Column(name="history")
	private int history;
	@Column(name="insight")
	private int insight;
	@Column(name="intimidate")
	private int intimidate;
	@Column(name="nature")
	private int nature;
	@Column(name="perception")
	private int perception;
	@Column(name="religion")
	private int religion;
	@Column(name="stealth")
	private int stealth;
	@Column(name="streetwise")
	private int streetwise;
	@Column(name="thievery")
	private int thievery;
	
	//Defenses
	@Column(name="AC")
	private int AC;
	@Column(name="REF")
	private int REF;
	@Column(name="FORT")
	private int FORT;
	@Column(name="WILL")
	private int WILL;
	
	//Health
	@Column(name="maxHP")
	private int maxHP;
	@Column(name="bloodied")
	private int bloodied;
	@Column(name="surgesValue")
	private int surgesValue;
	@Column(name="surgesPerDay")
	private int surgesPerDay;
	
	//Ability scores
	@Column(name="STR")
	private int STR;
	@Column(name="CON")
	private int CON;
	@Column(name="INT")
	private int INT;
	@Column(name="DEX")
	private int DEX;
	@Column(name="WIS")
	private int WIS;
	@Column(name="CHAR")
	private int CHAR;
		
	//Other
	@Column(name="level")
	private int level;
	@Column(name="XP")
	private int XP;
	@Column(name="raceFeatures")
	private String raceFeatures;
	@Column(name="speed")
	private int speed;
	@Column(name="resistanceValue")
	private int resistanceValue;
	@Column(name="initiative")
	private int initiative;
	@Column(name="languages")
	private String languages;
	@Column(name="misc")
	private String misc;
	@Column(name="role")
	private EntityEnum.CS_Role role;
	@Column(name="size")
	private EntityEnum.CS_Size size;
	@Column(name="resistanceType")
	private EntityEnum.CS_Resistance_Type resistanceType;
	@Column(name="monsterOrigin")
	private EntityEnum.CS_Monster_Origin monsterOrigin;
	@Column(name="monsterType")
	private EntityEnum.CS_Monster_Type monsterType;
	@Column(name="keywords")
	private String keywords;
	@Column(name="powerSource")
	private String powerSource;
		

	/**
	 * Default constructor
	 */
	public CharacterSheet() {
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public CharacterSheet(String name) {
		super(name);
	}

	/**
	 * Getters & Setters
	 */
	public int getAcrobatics() {
		return acrobatics;
	}

	public void setAcrobatics(int acrobatics) {
		this.acrobatics = acrobatics;
	}

	public int getAthletics() {
		return athletics;
	}

	public void setAthletics(int athletics) {
		this.athletics = athletics;
	}

	public int getArcana() {
		return arcana;
	}

	public void setArcana(int arcana) {
		this.arcana = arcana;
	}

	public int getBluff() {
		return bluff;
	}

	public void setBluff(int bluff) {
		this.bluff = bluff;
	}

	public int getDiplomacy() {
		return diplomacy;
	}

	public void setDiplomacy(int diplomacy) {
		this.diplomacy = diplomacy;
	}

	public int getDungeoneering() {
		return dungeoneering;
	}

	public void setDungeoneering(int dungeoneering) {
		this.dungeoneering = dungeoneering;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(int heal) {
		this.heal = heal;
	}

	public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public int getInsight() {
		return insight;
	}

	public void setInsight(int insight) {
		this.insight = insight;
	}

	public int getIntimidate() {
		return intimidate;
	}

	public void setIntimidate(int intimidate) {
		this.intimidate = intimidate;
	}

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	public int getPerception() {
		return perception;
	}

	public void setPerception(int perception) {
		this.perception = perception;
	}

	public int getReligion() {
		return religion;
	}

	public void setReligion(int religion) {
		this.religion = religion;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		this.stealth = stealth;
	}

	public int getStreetwise() {
		return streetwise;
	}

	public void setStreetwise(int streetwise) {
		this.streetwise = streetwise;
	}

	public int getThievery() {
		return thievery;
	}

	public void setThievery(int thievery) {
		this.thievery = thievery;
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}

	public int getREF() {
		return REF;
	}

	public void setREF(int rEF) {
		REF = rEF;
	}

	public int getFORT() {
		return FORT;
	}

	public void setFORT(int fORT) {
		FORT = fORT;
	}

	public int getWILL() {
		return WILL;
	}

	public void setWILL(int wILL) {
		WILL = wILL;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
		this.setBloodied(maxHP / 2); /* @Author: James Begg - bloodied is ALWAYS 1/2 max health */
		this.setSurgesValue(maxHP / 4); /*@Author:James Begg- bloodied is ALWAYS 1/4 max health */
	}

	public int getBloodied() {
		return bloodied;
	}

	private void setBloodied(int bloodied) { /* @Author: James Begg - set visibility to private */
		this.bloodied = bloodied;
	}

	public int getSurgesValue() {
		return surgesValue;
	}

	private void setSurgesValue(int surgesValue) { /* @Author: James Begg - set visibility to private */
		this.surgesValue = surgesValue;
	}

	public int getSurgesPerDay() {
		return surgesPerDay;
	}

	public void setSurgesPerDay(int surgesPerDay) {
		this.surgesPerDay = surgesPerDay;
	}

	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getCON() {
		return CON;
	}

	public void setCON(int cON) {
		CON = cON;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getDEX() {
		return DEX;
	}

	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getWIS() {
		return WIS;
	}

	public void setWIS(int wIS) {
		WIS = wIS;
	}

	public int getCHAR() {
		return CHAR;
	}

	public void setCHAR(int cHAR) {
		CHAR = cHAR;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public String getRaceFeatures() {
		return raceFeatures;
	}

	public void setRaceFeatures(String raceFeatures) {
		this.raceFeatures = raceFeatures;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getResistanceValue() {
		return resistanceValue;
	}

	public void setResistanceValue(int resistanceValue) {
		this.resistanceValue = resistanceValue;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		this.misc = misc;
	}

	public EntityEnum.CS_Role getRole() {
		return role;
	}

	public void setRole(EntityEnum.CS_Role role) {
		this.role = role;
	}

	public EntityEnum.CS_Size getSize() {
		return size;
	}

	public void setSize(EntityEnum.CS_Size size) {
		this.size = size;
	}

	public EntityEnum.CS_Resistance_Type getResistanceType() {
		return resistanceType;
	}

	public void setResistanceType(EntityEnum.CS_Resistance_Type resistanceType) {
		this.resistanceType = resistanceType;
	}

	public EntityEnum.CS_Monster_Origin getMonsterOrigin() {
		return monsterOrigin;
	}

	public void setMonsterOrigin(EntityEnum.CS_Monster_Origin monsterOrigin) {
		this.monsterOrigin = monsterOrigin;
	}

	public EntityEnum.CS_Monster_Type getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(EntityEnum.CS_Monster_Type monsterType) {
		this.monsterType = monsterType;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}

	
	public String toHTML() {
		// TODO Auto-generated method stub
		return null;
	}

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
