package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * CharacterSheet Class
 * @author TimHP and James Begg
 *
 */
@Entity
@Table(name="CharacterSheet")
public class CharacterSheet implements EntityM {
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="CharacterSheet_id")
    private int id;

	@Column(name="CharacterSheet_name")
	private String name;
	
	//Skills
	@Column(name="acrobatics")
	protected int acrobatics;
	@Column(name="athletics")
	protected int athletics;
	@Column(name="arcana")
	protected int arcana;
	@Column(name="bluff")
	protected int bluff;
	@Column(name="diplomacy")
	protected int diplomacy;
	@Column(name="dungeoneering")
	protected int dungeoneering;
	@Column(name="endurance")
	protected int endurance;
	@Column(name="heal")
	protected int heal;
	@Column(name="history")
	protected int history;
	@Column(name="insight")
	protected int insight;
	@Column(name="intimidate")
	protected int intimidate;
	@Column(name="nature")
	protected int nature;
	@Column(name="perception")
	protected int perception;
	@Column(name="religion")
	protected int religion;
	@Column(name="stealth")
	protected int stealth;
	@Column(name="streetwise")
	protected int streetwise;
	@Column(name="thievery")
	protected int thievery;
	
	//Defenses
	@Column(name="AC")
	protected int AC;
	@Column(name="RE")
	protected int REF;
	@Column(name="FORT")
	protected int FORT;
	@Column(name="WILL")
	protected int WILL;
	
	//Health
	@Column(name="maxHP")
	protected int maxHP;
	@Column(name="bloodied")
	protected int bloodied;
	@Column(name="surgesValue")
	protected int surgesValue;
	@Column(name="surgesPerDay")
	protected int surgesPerDay;
	
	//Ability scores
	@Column(name="STR")
	protected int STR;
	@Column(name="CON")
	protected int CON;
	@Column(name="INTE")
	protected int INT;
	@Column(name="DEX")
	protected int DEX;
	@Column(name="WIS")
	protected int WIS;
	@Column(name="CHARA")
	protected int CHAR;
		
	//Other
	@Column(name="levelCS")
	protected int level;
	@Column(name="XP")
	protected int XP;
	@Column(name="raceFeatures")
	protected String raceFeatures;
	@Column(name="speed")
	protected int speed;
	@Column(name="initiative")
	protected int initiative;
	@Column(name="languages")
	protected String languages;
	@Column(name="misc")
	protected String misc;
	@Column(name="keywords")
	private String keywords;
	@Column(name="powerSource")
	private String powerSource;

	//Enum
	@Column(name="roleCS")
	protected EntityEnum.CS_Role role;
	@Column(name="sizeCS")
	protected EntityEnum.CS_Size size;
	@Column(name="monsterOriginCS")
	private EntityEnum.CS_Monster_Origin monsterOrigin;
	@Column(name="monsterTypeCS")
	private EntityEnum.CS_Monster_Type monsterType; 
	
	//Associations
	@OneToMany(mappedBy = "characterSheet")
	private Set<Creature> creatures;
	
	@OneToMany(mappedBy = "characterSheet")
	private Set<Attack> attacks;
		
	/*In the database resistance will reference Character Sheet*/
	@OneToMany(mappedBy = "characterSheet")
	private List<Resistance> character_resistances;

	/**
	 * Default constructor
	 */
	public CharacterSheet() {
		/*Abilities*/
		this.STR = 0;
		this.CON = 0;
		this.DEX = 0;
		this.INT = 0;
		this.WIS = 0;
		this.CHAR = 0;
		/*Defenses*/
		this.AC = 0;
		this.REF = 0;
		this.FORT = 0;
		this.WILL = 0;
		/*Skills*/
		this.acrobatics = 0;
		this.athletics = 0;
		this.arcana = 0;
		this.bluff = 0;
		this.diplomacy = 0;
		this.dungeoneering = 0;
		this.endurance = 0;
		this.heal = 0;
		this.history = 0;
		this.insight = 0;
		this.intimidate = 0;
		this.nature = 0;
		this.perception = 0;
		this.religion = 0;
		this.stealth = 0;
		this.streetwise = 0;
		this.thievery = 0;
		/*Health*/
		this.setMaxHP(0);
		this.surgesPerDay = 0;
		/*Other*/
		this.level = 0;
		this.XP = 0;
		this.raceFeatures = "";
		this.speed = 0;
		this.initiative = 0;
		this.languages = "";
		this.misc = "";
		this.character_resistances = new ArrayList<Resistance>();
	}
	
	/**
	 * Constructor
	 * @param name
	 */
	public CharacterSheet(String name) {
		this.name = name;
		
		/*Abilities*/
		this.STR = 0;
		this.CON = 0;
		this.DEX = 0;
		this.INT = 0;
		this.WIS = 0;
		this.CHAR = 0;
		/*Defenses*/
		this.AC = 0;
		this.REF = 0;
		this.FORT = 0;
		this.WILL = 0;
		/*Skills*/
		this.acrobatics = 0;
		this.athletics = 0;
		this.arcana = 0;
		this.bluff = 0;
		this.diplomacy = 0;
		this.dungeoneering = 0;
		this.endurance = 0;
		this.heal = 0;
		this.history = 0;
		this.insight = 0;
		this.intimidate = 0;
		this.nature = 0;
		this.perception = 0;
		this.religion = 0;
		this.stealth = 0;
		this.streetwise = 0;
		this.thievery = 0;
		/*Health*/
		this.setMaxHP(0);
		this.surgesPerDay = 0;
		/*Other*/
		this.level = 0;
		this.XP = 0;
		this.raceFeatures = "";
		this.speed = 0;
		this.initiative = 0;
		this.languages = "";
		this.misc = "";
		this.character_resistances = new ArrayList<Resistance>();
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
	
	public void removeAllResistances() {
		this.character_resistances.removeAll(character_resistances);
	}
	
	public void addResistance(Resistance addThisResistance) {
		this.character_resistances.add(addThisResistance);
	}
	
	public Resistance getResistanceAt(int index) {
		return this.character_resistances.get(index);
	}
	
	public Resistance removeResistanceAt(int index) throws IndexOutOfBoundsException {
		return this.character_resistances.remove(index);
	}
	
	public boolean removeResistance(Resistance thisResistance) {
		return this.character_resistances.remove(thisResistance);
	}
	
	public List<Resistance> getRawResistanceList() {
		return this.character_resistances;
	}
	
	public int getNumberOfResistances() {
		return this.character_resistances.size();
	}
	
	public String getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(String powerSource) {
		this.powerSource = powerSource;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Resistance> getCharacter_resistances() {
		return character_resistances;
	}

	public void setCharacter_resistances(List<Resistance> character_resistances) {
		this.character_resistances = character_resistances;
	}
	
	public Set<Creature> getCreatures() {
		return creatures;
	}

	public void setCreatures(Set<Creature> creatures) {
		this.creatures = creatures;
	}
	
	public Set<Attack> getAttacks() {
		return attacks;
	}

	public void setAttacks(Set<Attack> attacks) {
		this.attacks = attacks;
	}

	/**
	 * Other Functions
	 */
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
