package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.CharacterSheet;
import entity.EntityEnum.CS_Monster_Origin;
import entity.EntityEnum.CS_Monster_Type;
import entity.EntityEnum.CS_Role;
import entity.EntityEnum.CS_Size;

/**
 * DAO of a character sheet
 * @author TimHP
 *
 */
public class CharacterSheetDaoImpl implements CharacterSheetDao {

	private static final Logger logger = Logger.getLogger(CreatureDaoImpl.class);
	
	public CharacterSheetDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void readAllCharacterSheets() {
		// TODO Auto-generated method stub
		
	}

	public int saveCharacterSheet(String name, int acrobatics, int athletics,
			int arcana, int bluff, int diplomacy, int dungeoneering,
			int endurance, int heal, int history, int insight, int intimidate,
			int nature, int perception, int religion, int stealth,
			int streetwise, int thievery, int AC, int REF, int FORT, int WILL,
			int maxHP, int surgesPerDay, int STR, int CON, int INT, int DEX, 
			int WIS, int CHAR, int level, int XP, String raceFeatures, int speed,
			int initiative, String languages, String misc, String keywords, 
			String powerSource,	CS_Role role, CS_Size size, 
			CS_Monster_Origin monsterOrigin, CS_Monster_Type monsterType) {
		
		logger.debug("Character Sheet " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int characterSheetID = -1;
        try {
            transaction = session.beginTransaction();
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
            characterSheetID = (Integer) session.save(cs);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving character sheet " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Character Sheet " + name + " was successfully saved in the database.");
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
			CS_Monster_Origin monsterOrigin, CS_Monster_Type monsterType) {
		
		logger.debug("Character Sheet " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CharacterSheet cs = (CharacterSheet) session.get(CharacterSheet.class, characterSheetId);
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
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Character Sheet " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Character Sheet " + name + " was successfully updated in the database.");
            session.close();
        }
		
	}
	

	public void deleteCharacterSheet(int characterSheetId) {
		logger.debug("Character Sheet " + characterSheetId + " is about to be deleted from the database.");
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CharacterSheet cs= (CharacterSheet) session.get(CharacterSheet.class, characterSheetId);
            session.delete(cs);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting creature " + characterSheetId + " in the database", e.getCause());
        } finally {
        	logger.info("Creature " + characterSheetId + " was successfully removed from the database.");
            session.close();
        }
	
	}

}
