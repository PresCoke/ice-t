package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.CharacterSheet;
import entity.Monster;
import entity.Team;

public class MonsterDaoImpl implements MonsterDao {
	
	private static final Logger logger = Logger.getLogger(MonsterDaoImpl.class);

	public MonsterDaoImpl() {
	}

	public List<Object[]> readAllMonsters() {
    	logger.info("Retrieval of all monsters in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select Monster_id, monster_name from Monster");
		List<Object[]> monsters = q.list();
		return monsters;
	}

	public List<Monster> getAllMonsters() {
    	logger.info("Retrieval of all monsters in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Monster");
		List<Monster> monsters = q.list();
		return monsters;
	}

	public List<Monster> getMonstersInTeam(int teamId) {
    	logger.info("Retrieval of all monsters in the team " + teamId);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Monster where Team_id="+teamId);
		List<Monster> monsters = q.list();
		return monsters;
	}
	
	public List<Monster> getMonstersByName(String monsterName) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();		
		Query q = session.createQuery("FROM Monster WHERE monster_name=:value");
		q.setParameter("value", monsterName);
		List<Monster> monsters = q.list();
		return monsters;
	}

	public int saveMonster(String monsterName, int currentHP,
			int currentHealSurges, int initiative, boolean secondWind,
			int tempHP, CharacterSheet characterSheet) {
    	logger.debug("Monster " + monsterName + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int monsterId = -1;
        try {
            transaction = session.beginTransaction();
            logger.debug("Setting monster's attributes");
            Monster m = new Monster(monsterName);
            m.setCurrentHP(currentHP);
            m.setCurrentHealSurges(currentHealSurges);
            m.setInitiative(initiative);
            m.setSecondWind(secondWind);
            m.setTempHP(tempHP);
            if (!characterSheet.isNPC()){
                logger.error("The character sheet is not supposed to be associated to a Monster but a Player.");
            	throw new DAOException();
            }
            m.setCharacterSheet(characterSheet);
            monsterId = (Integer) session.save(m);
            transaction.commit();
        	logger.info("Monster " + monsterName + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving monster " + monsterName + " in the database --- " + e.getMessage());
        } catch (DAOException e) {
            transaction.rollback();
            logger.fatal("Error while saving monster " + monsterName + " in the database.");
		} finally {
            session.close();
        }
        return monsterId;
	}

	public void updateMonster(int monsterId, String monsterName, int currentHP,
			int currentHealSurges, int initiative, boolean secondWind,
			int tempHP) {
		logger.debug("Monster " + monsterName + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Monster m = (Monster) session.get(Monster.class, monsterId);
            m.setMonsterName(monsterName);
            m.setCurrentHP(currentHP);
            m.setCurrentHealSurges(currentHealSurges);
            m.setInitiative(initiative);
            m.setSecondWind(secondWind);
            m.setTempHP(tempHP);
            transaction.commit();
        	logger.info("Monster " + monsterName + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating monster " + monsterName + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

	public void updateMonster(Monster monster, Team team) {
	  	logger.debug("Monster " + monster.getMonsterName() + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Monster m = (Monster) session.get(Monster.class, monster.getId());
            m.setMonsterName(monster.getMonsterName());
            m.setCurrentHP(monster.getCurrentHP());
            m.setCurrentHealSurges(monster.getCurrentHealSurges());
            m.setInitiative(monster.getInitiative());
            m.setSecondWind(monster.isSecondWind());
            m.setTempHP(monster.getTempHP());
            m.setTeam(team);
            transaction.commit();
        	logger.info("Monster " + monster.getMonsterName() + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating monster " + monster.getMonsterName() + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

	public void deleteMonster(int monsterId) {
		logger.debug("Monster " + monsterId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Monster m = (Monster) session.get(Monster.class, monsterId);
            logger.info("Deletion of monster " + m.getMonsterName());
            m.getCharacterSheet().removeMonster(m);
            session.delete(m);
            transaction.commit();
        	logger.info("Monster " + monsterId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting monster " + monsterId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

}
