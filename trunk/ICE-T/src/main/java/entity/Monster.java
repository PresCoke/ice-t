package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Monster")
public class Monster extends CharacterSheet {
	@Column(name="monsterOrigin")
	private EntityEnum.CS_Monster_Origin monsterOrigin; //should be in monster subclass
	@Column(name="monsterType")
	private EntityEnum.CS_Monster_Type monsterType; //should be in monster subclass
	@Column(name="keywords")
	private String keywords; //should be in monster subclass
	
	public Monster() {
		super();
		keywords = "";
	}
	
	public Monster(String name) {
		super(name);
		keywords = "";
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
	
	/*
	 * Super Getters and Setters
	 */
	public int getAcrobatics() {
		return acrobatics;
	}

	public void setAcrobatics(int acrobatics) {
		super.acrobatics = acrobatics;
	}

	public int getAthletics() {
		return athletics;
	}

	public void setAthletics(int athletics) {
		super.athletics = athletics;
	}

	public int getArcana() {
		return arcana;
	}

	public void setArcana(int arcana) {
		super.arcana = arcana;
	}

	public int getBluff() {
		return bluff;
	}

	public void setBluff(int bluff) {
		super.bluff = bluff;
	}

	public int getDiplomacy() {
		return diplomacy;
	}

	public void setDiplomacy(int diplomacy) {
		super.diplomacy = diplomacy;
	}

	public int getDungeoneering() {
		return dungeoneering;
	}

	public void setDungeoneering(int dungeoneering) {
		super.dungeoneering = dungeoneering;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		super.endurance = endurance;
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(int heal) {
		super.heal = heal;
	}

	public int gesupertory() {
		return history;
	}

	public void sesupertory(int history) {
		super.history = history;
	}

	public int getInsight() {
		return insight;
	}

	public void setInsight(int insight) {
		super.insight = insight;
	}

	public int getIntimidate() {
		return intimidate;
	}

	public void setIntimidate(int intimidate) {
		super.intimidate = intimidate;
	}

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		super.nature = nature;
	}

	public int getPerception() {
		return perception;
	}

	public void setPerception(int perception) {
		super.perception = perception;
	}

	public int getReligion() {
		return religion;
	}

	public void setReligion(int religion) {
		super.religion = religion;
	}

	public int getStealth() {
		return stealth;
	}

	public void setStealth(int stealth) {
		super.stealth = stealth;
	}

	public int getStreetwise() {
		return streetwise;
	}

	public void setStreetwise(int streetwise) {
		super.streetwise = streetwise;
	}

	public int getThievery() {
		return thievery;
	}

	public void setThievery(int thievery) {
		super.thievery = thievery;
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
		super.setMaxHP(maxHP);
	}

	public int getBloodied() {
		return bloodied;
	}
	
	public int getSurgesValue() {
		return surgesValue;
	}

	public int getSurgesPerDay() {
		return surgesPerDay;
	}

	public void setSurgesPerDay(int surgesPerDay) {
		super.surgesPerDay = surgesPerDay;
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
		super.level = level;
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
		super.raceFeatures = raceFeatures;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		super.speed = speed;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		super.initiative = initiative;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		super.languages = languages;
	}

	public String getMisc() {
		return misc;
	}

	public void setMisc(String misc) {
		super.misc = misc;
	}

	public EntityEnum.CS_Role getRole() {
		return role;
	}

	public void setRole(EntityEnum.CS_Role role) {
		super.role = role;
	}

	public EntityEnum.CS_Size getSize() {
		return size;
	}

	public void setSize(EntityEnum.CS_Size size) {
		super.size = size;
	}
	
	public void removeAllResistances() {
		super.character_resistances.removeAll(character_resistances);
	}
	
	public void addResistance(Resistance addThisResistance) {
		super.character_resistances.add(addThisResistance);
	}
	
	public Resistance getResistanceAt(int index) {
		return super.character_resistances.get(index);
	}
	
	public Resistance removeResistanceAt(int index) throws IndexOutOfBoundsException {
		return super.character_resistances.remove(index);
	}
	
	public List<Resistance> getRawResistanceList() {
		return super.character_resistances;
	}
	
	public int getNumberOfResistances() {
		return super.getNumberOfResistances();
	}
}