package entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Character")
public class Character extends CharacterSheet {
	@Column(name="playerName")
	private String playerName;
	@Column(name="powerSource")
	private String powerSource;
	
	public Character() {
		super();
		this.playerName = "";
		this.powerSource = "";
	}
	
	public Character(String name) {
		super(name);
		this.playerName = "";
		this.powerSource = "";
	}
	
	public void setPlayerName(String player) {
		this.playerName = player;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}
	
	/*
	 * Super getters & Setters
	 */
	public int getAcrobatics() {
		return super.acrobatics;
	}

	public void setAcrobatics(int acrobatics) {
		super.acrobatics = acrobatics;
	}

	public int getAthletics() {
		return super.athletics;
	}

	public void setAthletics(int athletics) {
		super.athletics = athletics;
	}

	public int getArcana() {
		return super.arcana;
	}

	public void setArcana(int arcana) {
		super.arcana = arcana;
	}

	public int getBluff() {
		return super.bluff;
	}

	public void setBluff(int bluff) {
		super.bluff = bluff;
	}

	public int getDiplomacy() {
		return super.diplomacy;
	}

	public void setDiplomacy(int diplomacy) {
		super.diplomacy = diplomacy;
	}

	public int getDungeoneering() {
		return super.dungeoneering;
	}

	public void setDungeoneering(int dungeoneering) {
		super.dungeoneering = dungeoneering;
	}

	public int getEndurance() {
		return super.endurance;
	}

	public void setEndurance(int endurance) {
		super.endurance = endurance;
	}

	public int getHeal() {
		return super.heal;
	}

	public void setHeal(int heal) {
		super.heal = heal;
	}

	public int gesupertory() {
		return super.history;
	}

	public void sesupertory(int history) {
		super.history = history;
	}

	public int getInsight() {
		return super.insight;
	}

	public void setInsight(int insight) {
		super.insight = insight;
	}

	public int getIntimidate() {
		return super.intimidate;
	}

	public void setIntimidate(int intimidate) {
		super.intimidate = intimidate;
	}

	public int getNature() {
		return super.nature;
	}

	public void setNature(int nature) {
		super.nature = nature;
	}

	public int getPerception() {
		return super.perception;
	}

	public void setPerception(int perception) {
		super.perception = perception;
	}

	public int getReligion() {
		return super.religion;
	}

	public void setReligion(int religion) {
		super.religion = religion;
	}

	public int getStealth() {
		return super.stealth;
	}

	public void setStealth(int stealth) {
		super.stealth = stealth;
	}

	public int getStreetwise() {
		return super.streetwise;
	}

	public void setStreetwise(int streetwise) {
		super.streetwise = streetwise;
	}

	public int getThievery() {
		return super.thievery;
	}

	public void setThievery(int thievery) {
		super.thievery = thievery;
	}

	public int getAC() {
		return super.AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}

	public int getREF() {
		return super.REF;
	}

	public void setREF(int rEF) {
		REF = rEF;
	}

	public int getFORT() {
		return super.FORT;
	}

	public void setFORT(int fORT) {
		FORT = fORT;
	}

	public int getWILL() {
		return super.WILL;
	}

	public void setWILL(int wILL) {
		WILL = wILL;
	}

	public int getMaxHP() {
		return super.maxHP;
	}

	public void setMaxHP(int maxHP) {
		super.setMaxHP(maxHP);
	}

	public int getBloodied() {
		return super.bloodied;
	}

		public int getSurgesValue() {
		return super.surgesValue;
	}

	public int getSurgesPerDay() {
		return super.surgesPerDay;
	}

	public void setSurgesPerDay(int surgesPerDay) {
		super.surgesPerDay = surgesPerDay;
	}

	public int getSTR() {
		return super.STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getCON() {
		return super.CON;
	}

	public void setCON(int cON) {
		CON = cON;
	}

	public int getINT() {
		return super.INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}

	public int getDEX() {
		return super.DEX;
	}

	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getWIS() {
		return super.WIS;
	}

	public void setWIS(int wIS) {
		WIS = wIS;
	}

	public int getCHAR() {
		return super.CHAR;
	}

	public void setCHAR(int cHAR) {
		CHAR = cHAR;
	}

	public int getLevel() {
		return super.level;
	}

	public void setLevel(int level) {
		super.level = level;
	}

	public int getXP() {
		return super.XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public String getRaceFeatures() {
		return super.raceFeatures;
	}

	public void setRaceFeatures(String raceFeatures) {
		super.raceFeatures = raceFeatures;
	}

	public int getSpeed() {
		return super.speed;
	}

	public void setSpeed(int speed) {
		super.speed = speed;
	}

	public int getInitiative() {
		return super.initiative;
	}

	public void setInitiative(int initiative) {
		super.initiative = initiative;
	}

	public String getLanguages() {
		return super.languages;
	}

	public void setLanguages(String languages) {
		super.languages = languages;
	}

	public String getMisc() {
		return super.misc;
	}

	public void setMisc(String misc) {
		super.misc = misc;
	}

	public EntityEnum.CS_Role getRole() {
		return super.role;
	}

	public void setRole(EntityEnum.CS_Role role) {
		super.role = role;
	}

	public EntityEnum.CS_Size getSize() {
		return super.size;
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
	
	public boolean removeResistance(Resistance thisResistance) {
		return super.removeResistance(thisResistance);
	}
	
	public List<Resistance> getRawResistanceList() {
		return super.character_resistances;
	}

	public int getNumberOfResistances() {
		return super.getNumberOfResistances();
	}
	
}
