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
import entity.Player;
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
	}
	

	public CharacterSheet getCharacterSheet(int characterSheetId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();		
		CharacterSheet cs = (CharacterSheet) session.get(CharacterSheet.class, characterSheetId);
		return cs;
	}

	public List<Object[]> readAllCharacterSheets() {
    	logger.info("Retrieval of all character sheets in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select CharacterSheet_id, CharacterSheet_name from CharacterSheet");
		List<Object[]> characterSheets = q.list();		
		return characterSheets;
	}
	
	public List<CharacterSheet> getAllCharacterSheets(){
    	logger.info("Retrieval of all character sheets in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from CharacterSheet");
		List<CharacterSheet> characterSheets = q.list();		
		return characterSheets;
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
			List<Attack_Type> attacksTypes, boolean isNPC) {
		
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
            cs.setNPC(isNPC);
            //Set the resistance(s)
            logger.debug("Setting character sheet's resistances");
            if(resistances != null && !resistances.isEmpty()){
	            for(Resistance r : resistances){
	            	cs.addResistance(r);
	            	r.setCharacterSheet(cs);
	            }
            }
            //Set the attack(s)
            if (attacks != null && attacksTypes != null){
	            if(attacks.size() != attacksTypes.size()){
	                logger.fatal("The list attacks (size = " + attacks.size() +") does not have the same length than attacksTypes (size = " + attacksTypes.size() + ")");
	            	throw new DAOException();
	            }
	            logger.debug("Setting character sheet's attacks");
	            int i = 0;
	            for (Attack a : attacks){
	            	Attack_Type t = attacksTypes.get(i);
            		a.setAttackType(t);
            		a.setCharacterSheet(cs);
            		t.setAttack(a);
            		cs.addAttack(a);
            		i++;
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
			List<Attack_Type> attacksTypes, boolean isNPC) {

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
            cs.setNPC(isNPC);
            //Set the resistance(s)
            logger.debug("Deleting previous character sheet's resistances");
            List<Resistance> resistancesDB = cs.getResistances();
            cs.removeAllResistances();
            for(Resistance r : cs.getResistances()){
            	ResistanceDao rDao = new ResistanceDaoImpl();
            	rDao.deleteResistance(r.getId());
            }
            session.update(cs);
            session.flush();
            logger.debug("Setting character sheet's resistances");
            for(Resistance r : resistances){
        		cs.addResistance(r);
        		r.setCharacterSheet(cs);
        	}
            //Set the attack(s)
            if (attacks != null && attacksTypes != null){
	            if(attacks.size() != attacksTypes.size()){
	                logger.fatal("The list attacks (size = " + attacks.size() +") does not have the same length than attacksTypes (size = " + attacksTypes.size() + ")");
	            	throw new DAOException();
	            }
	            logger.debug("Deleting previous character sheet's attacks");
	            List<Attack> attacksDB = cs.getAttacks();
	            cs.removeAllAttacks();
	            for(Attack a : attacksDB){
	            	AttackDao aDao = new AttackDaoImpl();
	            	aDao.deleteAttack(a.getId());
		            session.evict(a);
		            session.flush();
	            }
	            session.update(cs);
	            session.flush();
	            logger.debug("Setting new character sheet's attacks");
	            int i = 0;
	            for (Attack a : attacks){
	            	Attack_Type t = attacksTypes.get(i);
            		a.setAttackType(t);
            		a.setCharacterSheet(cs);
            		t.setAttack(a);
            		cs.addAttack(a);
            		i++;
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
