package entity;

/**
 * CharacterSheet Class
 * @author TimHP
 *
 */
public class CharacterSheet extends Entity {

	//Skills
	private int acrobatics;
	private int athletics;
	private int arcana;
	private int bluff;
	private int diplomacy;
	private int dungeoneering;
	private int endurance;
	private int heal;
	private int history;
	private int insight;
	private int intimidate;
	private int nature;
	private int perception;
	private int religion;
	private int stealth;
	private int streetwise;
	private int thievery;
	
	//Defenses
	private int AC;
	private int REF;
	private int FORT;
	private int WILL;
	
	//Health
	private int maxHP;
	private int bloodied;
	private int surgesValue;
	private int surgesPerDay;
	
	//Ability scores
	private int STR;
	private int CON;
	private int INT;
	private int DEX;
	private int WIS;
	private int CHAR;
		
	//Other
	private int level;
	private int XP;
	private String raceFeatures;
	private int speed;
	private int resistanceValue;
	private int initiative;
	private String languages;
	private String misc;
	private EntityEnum.CS_Role role;
	private EntityEnum.CS_Size size;
	private EntityEnum.CS_Resistance_Type resistanceType;
	private EntityEnum.CS_Monster_Origin monsterOrigin;
	private EntityEnum.CS_Monster_Type monsterType;
	private String keywords;
	private String powerSource;
		
	/**
	 * Constructor
	 * @param name
	 */
	public CharacterSheet(String name) {
		super(name);
	}

	//Getters & Setters
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
	}

	public int getBloodied() {
		return bloodied;
	}

	public void setBloodied(int bloodied) {
		this.bloodied = bloodied;
	}

	public int getSurgesValue() {
		return surgesValue;
	}

	public void setSurgesValue(int surgesValue) {
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
}
