package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.A_Area;
import entity.A_Close;
import entity.A_Melee;
import entity.A_Range;
import entity.Attack;
import entity.Attack_Type;
import entity.CharacterSheet;
import entity.EntityEnum.CS_Monster_Origin;
import entity.EntityEnum.CS_Monster_Type;
import entity.EntityEnum.CS_Role;
import entity.EntityEnum.CS_Size;
import entity.Resistance;

/**
 * DAO of a character sheet
 * @author TimHP
 *
 */
public class CharacterSheetDaoImpl implements CharacterSheetDao {

	private static final Logger logger = Logger.getLogger(CharacterSheetDaoImpl.class);
	
	public CharacterSheetDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void readAllCharacterSheets() {
    	logger.info("Retrieval of all character sheets in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from CharacterSheet");
		
		/*-- WTF @SuppressWarnings("unchecked") --*/
		List<CharacterSheet> characterSheets = q.list();
		
		for (CharacterSheet cs : characterSheets) {
			logger.info("Character Sheet Name = " + cs.getName());
		}
		
	}

	public int saveCharacterSheet(String name, int acrobatics, int athletics,
			int arcana, int bluff, int diplomacy, int dungeoneering,
			int endurance, int heal, int history, int insight, int intimidate,
			int nature, int perception, int religion, int stealth,
			int streetwise, int thievery, int AC, int REF, int FORT, int WILL,
			int maxHP, int surgesPerDay, int STR, int CON, int INT, int DEX,
			int WIS, int CHAR, int level, int XP, String raceFeatures,
			int speed, int initiative, String languages, String misc,
			String keywords, String powerSource, CS_Role role, CS_Size size,
			CS_Monster_Origin monsterOrigin, CS_Monster_Type monsterType,
			List<Resistance> resistances, List<Attack> attacks, 
			List<Attack_Type> attacksTypes) {
		
		logger.debug("Character Sheet " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int characterSheetID = -1;
        try {
            transaction = session.beginTransaction();
            //Set character sheet's attributes
            logger.debug("Setting character sheet's attributes");
            CharacterSheet cs = new CharacterSheet(name);
            cs.setAcrobatics(acrobatics);
            cs.setAthletics(athletics);
            cs.setArcana(arcana);
            cs.setBluff(bluff);
            cs.setDiplomacy(diplomacy);
            cs.setDungeoneering(dungeoneering);
            cs.setEndurance(endurance);
            cs.setHeal(heal);
            cs.setHistory(history);
            cs.setInsight(insight);
            cs.setIntimidate(intimidate);
            cs.setNature(nature);
            cs.setPerception(perception);
            cs.setReligion(religion);
            cs.setStealth(stealth);
            cs.setStreetwise(streetwise);
            cs.setThievery(thievery);
            cs.setAC(AC);
            cs.setREF(REF);
            cs.setFORT(FORT);
            cs.setWILL(WILL);
            cs.setMaxHP(maxHP);
            cs.setSurgesPerDay(surgesPerDay);
            cs.setSTR(STR);
            cs.setCON(CON);
            cs.setINT(INT);
            cs.setDEX(DEX);
            cs.setWIS(WIS);
            cs.setCHAR(CHAR);
            cs.setLevel(level);
            cs.setXP(XP);
            cs.setRaceFeatures(raceFeatures);
            cs.setSpeed(speed);
            cs.setInitiative(initiative);
            cs.setLanguages(languages);
            cs.setMisc(misc);
            cs.setKeywords(keywords);
            cs.setPowerSource(powerSource);
            cs.setRole(role);
            cs.setSize(size);
            cs.setMonsterOrigin(monsterOrigin);
            cs.setMonsterType(monsterType);
            //Set the resistance(s)
            logger.debug("Setting character sheet's resistances");
            if(resistances != null && !resistances.isEmpty()){
	            for(Resistance r : resistances){
	            	cs.addResistance(r);
	            	r.setCharacterSheet(cs);
	            }
            }
            //Set the attack(s)
            if (attacks != null && !attacks.isEmpty()){
	            if(attacks.size() != attacksTypes.size()){
	                logger.fatal("The list attacks (" + attacks.size() +") does not have the same length than attacksTypes (" + attacksTypes.size() + ")");
	            	throw new DAOException();
	            }
	            logger.debug("Setting character sheet's attacks");
	            for (Attack a : attacks){
	            	for (Attack_Type t : attacksTypes){
	            		a.setAttackType(t);
	            		t.setAttack(a);
	            		cs.addAttack(a);
	            		a.setCharacterSheet(cs);
	            	}
	            }  
            }
            //Save the character Sheet
            characterSheetID = (Integer) session.save(cs);
            transaction.commit();
        	logger.info("Character Sheet " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving character sheet " + name + " in the database --- " + e.getMessage());
        } catch (DAOException e) {
        	transaction.rollback();
        } finally {
            session.close();
        }
		return characterSheetID;
	}

	public void updateCharacterSheet(int characterSheetId, String name,
			int acrobatics, int athletics, int arcana, int bluff,
			int diplomacy, int dungeoneering, int endurance, int heal,
			int history, int insight, int intimidate, int nature,
			int perception, int religion, int stealth, int streetwise,
			int thievery, int AC, int REF, int FORT, int WILL, int maxHP,
			int surgesPerDay, int STR, int CON, int INT, int DEX, int WIS,
			int CHAR, int level, int XP, String raceFeatures, int speed,
			int initiative, String languages, String misc, String keywords,
			String powerSource, CS_Role role, CS_Size size,
			CS_Monster_Origin monsterOrigin, CS_Monster_Type monsterType,
			List<Resistance> resistances, List<Attack> attacks,
			List<Attack_Type> attacksTypes) {

		logger.debug("Character Sheet " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CharacterSheet cs = (CharacterSheet) session.get(CharacterSheet.class, characterSheetId);
            //Set character sheet's attributes
            logger.debug("Setting character sheet's attributes");
            cs.setName(name);
            cs.setAcrobatics(acrobatics);
            cs.setAthletics(athletics);
            cs.setArcana(arcana);
            cs.setBluff(bluff);
            cs.setDiplomacy(diplomacy);
            cs.setDungeoneering(dungeoneering);
            cs.setEndurance(endurance);
            cs.setHeal(heal);
            cs.setHistory(history);
            cs.setInsight(insight);
            cs.setIntimidate(intimidate);
            cs.setNature(nature);
            cs.setPerception(perception);
            cs.setReligion(religion);
            cs.setStealth(stealth);
            cs.setStreetwise(streetwise);
            cs.setThievery(thievery);
            cs.setAC(AC);
            cs.setREF(REF);
            cs.setFORT(FORT);
            cs.setWILL(WILL);
            cs.setMaxHP(maxHP);
            cs.setSurgesPerDay(surgesPerDay);
            cs.setSTR(STR);
            cs.setCON(CON);
            cs.setINT(INT);
            cs.setDEX(DEX);
            cs.setWIS(WIS);
            cs.setCHAR(CHAR);
            cs.setLevel(level);
            cs.setXP(XP);
            cs.setRaceFeatures(raceFeatures);
            cs.setSpeed(speed);
            cs.setInitiative(initiative);
            cs.setLanguages(languages);
            cs.setMisc(misc);
            cs.setKeywords(keywords);
            cs.setPowerSource(powerSource);
            cs.setRole(role);
            cs.setSize(size);
            cs.setMonsterOrigin(monsterOrigin);
            cs.setMonsterType(monsterType); 
            //Set the resistance(s)
            logger.debug("Setting character sheet's resistances");
            for(Resistance r : resistances){
            	if (cs.compareResistanceNames(r) != -1){
            		Resistance resistance = cs.getCharacter_resistances().get(cs.compareResistanceNames(r));
            		resistance.setResistanceType(r.getResistanceType());
            		resistance.setResistanceValue(r.getResistanceValue());
            		resistance.setCharacterSheet(cs);
            	} else {
            		cs.addResistance(r);
            		r.setCharacterSheet(cs);
            	}
            }
            //Set the attack(s)
            if(attacks.size() != attacksTypes.size()){
                logger.fatal("The list attacks (" + attacks.size() +") does not have the same length than attacksTypes (" + attacksTypes.size() + ")");
            	throw new DAOException();
            }
            logger.debug("Setting character sheet's attacks");
            for(Attack a : attacks){
            	for (Attack_Type t : attacksTypes){
	            	if (cs.compareAttacksNames(a) != -1){
	            		Attack attack = cs.getAttacks().get(cs.compareAttacksNames(a));          		
	            		attack.setPrimaryTarget(a.getPrimaryTarget());
	            		attack.setSecondaryTarget(a.getSecondaryTarget());
	            		attack.setAccessories(a.getAccessories());
	            		attack.setPowerSource(a.getPowerSource());
	            		attack.setFrequency(a.getFrequency());
	            		attack.setHit(a.getHit());
	            		attack.setMiss(a.getMiss());
	            		attack.setBasic(a.isBasic());
	            		attack.setTrigger(a.getTrigger());
	            		attack.setEffectType(a.getEffectType());
	            		attack.setAbility(a.getAbility());
	            		attack.setDamageType(a.getDamageType());
	            		attack.setDefense(a.getDefense());
	            		attack.setSustain(a.getSustain());
	            		attack.setAction(a.getAction());
	            		attack.setUseType(a.getUseType());          		
	            		if (attack.getAttackType() instanceof A_Area && t instanceof A_Area){
	            			A_Area previousType = (A_Area) attack.getAttackType();
	            			A_Area newType = (A_Area) t;
	            			previousType.setPersonal(newType.isPersonal());
	            			previousType.setArea_range(newType.getArea_range());
	            			previousType.setArea_size(newType.getArea_size());
	            			previousType.setArea_type(newType.getArea_type());
	            		} else if (attack.getAttackType() instanceof A_Close && t instanceof A_Close){
	            			A_Close previousType = (A_Close) attack.getAttackType();
	            			A_Close newType = (A_Close) t;
	            			previousType.setPersonal(newType.isPersonal());
	            			previousType.setCloseType(newType.getCloseType());
	            			previousType.setSize(newType.getSize());
	            		} else if (attack.getAttackType() instanceof A_Melee && t instanceof A_Melee){
	            			A_Melee previousType = (A_Melee) attack.getAttackType();
	            			A_Melee newType = (A_Melee) t;
	            			previousType.setPersonal(newType.isPersonal());
	            			previousType.setReach(newType.getReach());
	            		} else if (attack.getAttackType() instanceof A_Range && t instanceof A_Range){
	            			A_Range previousType = (A_Range) attack.getAttackType();
	            			A_Range newType = (A_Range) t;
	            			previousType.setPersonal(newType.isPersonal());
	            			previousType.setL_range(newType.getL_range());
	            			previousType.setS_range(newType.getS_range());
	            		} else {
	            			Attack_TypeDAO attacktypeDao = new Attack_TypeDAOImpl();
	            			attacktypeDao.deleteAttackType(attack.getAttackType().getId());
		            		logger.debug("coucou");
	            			session.flush();
		            		logger.debug("coucou2");
	            			session.evict(attack.getAttackType());
		            		logger.debug("coucou3");
	            			session.update(attack.getAttackType());
		            		logger.debug("coucou4");
	            			attack.setAttackType(t);
		            		logger.debug("coucou5");
		            		t.setAttack(attack);
		            		logger.debug("coucou6");
	            		}
	            		attack.setCharacterSheet(cs);
	            	} else {       		
	            		a.setAttackType(t);
	            		t.setAttack(a);
	            		cs.addAttack(a);
	            		a.setCharacterSheet(cs);
	            	}
            
            	}
            }
            transaction.commit();
        	logger.info("Character Sheet " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Character Sheet " + name + " in the database --- " + e.getMessage());
        } catch (DAOException e) {
            transaction.rollback();
        } finally {
            session.close();
        }
		
	}
	
	
	public void deleteCharacterSheet(int characterSheetId) {
		logger.debug("Character Sheet " + characterSheetId + " is about to be deleted from the database.");
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CharacterSheet cs = (CharacterSheet) session.get(CharacterSheet.class, characterSheetId);
            logger.info("Deletion of Character sheet " + cs.getName());
            session.delete(cs);
            transaction.commit();
        	logger.info("Character Sheet " + characterSheetId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Character Sheet " + characterSheetId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	
	}

}
