package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.EntityEnum;
import entity.EntityEnum.T_CounterMeasureSkill;
import entity.EntityEnum.T_Role;
import entity.EntityEnum.T_Type;
import entity.TrapHazard;

/**
 * DAO of a trap or a hazard
 * @author TimHP
 *
 */
public class TrapHazardDaoImpl implements TrapHazardDao {

	private static final Logger logger = Logger.getLogger(TrapHazardDaoImpl.class);
	
	public void readAllTrapHazards() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from TrapHazard");
		
		@SuppressWarnings("unchecked")
		List<TrapHazard> ths = q.list();
		
		for (TrapHazard th : ths) {
			logger.debug("Name = " + th.getName() + " - DifficultyLevel = " + th.getDifficultyLevel());
		}		
	}

	public int saveTrapHazard(String name, int avoidance, int level,
			T_CounterMeasureSkill skill, String triggers, int value, int xp,
			int difficultyLevel, String counterMeasureDescription, T_Type type,
			T_Role role, T_CounterMeasureSkill counterMeasureSkill) {

    	logger.debug("TrapHazard " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int trapHazardID = -1;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = new TrapHazard(name);
            th.setAvoidance(avoidance);
            th.setLevel(level);
            th.setSkill(skill);
            th.setTriggers(triggers);
            th.setValue(value);
            th.setXp(xp);
            th.setDifficultyLevel(difficultyLevel);
            th.setCounterMeasureDescription(counterMeasureDescription);
            th.setType(type);
            th.setRole(role);
            th.setCounterMeasureSkill(counterMeasureSkill);
            trapHazardID = (Integer) session.save(th);
            transaction.commit();
        	logger.info("TrapHazard " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving TrapHazard " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return trapHazardID;
	}

	public void updateTrapHazard(int trapHazardId, String name, int avoidance,
			int level, T_CounterMeasureSkill skill, String triggers, int value, int xp,
			int difficultyLevel, String counterMeasureDescription, T_Type type,
			T_Role role, T_CounterMeasureSkill counterMeasureSkill) {

    	logger.debug("TrapHazard " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = (TrapHazard) session.get(TrapHazard.class, trapHazardId); 
            th.setName(name);
            th.setAvoidance(avoidance);
            th.setLevel(level);
            th.setSkill(skill);
            th.setTriggers(triggers);
            th.setValue(value);
            th.setXp(xp);
            th.setDifficultyLevel(difficultyLevel);
            th.setCounterMeasureDescription(counterMeasureDescription);
            th.setType(type);
            th.setRole(role);
            th.setCounterMeasureSkill(counterMeasureSkill);
            transaction.commit();
        	logger.info("TrapHazard " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating TrapHazard " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}

	public void deleteTrapHazard(int trapHazardId) {
		logger.debug("TrapHazard " + trapHazardId + " is about to be removed from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = (TrapHazard) session.get(TrapHazard.class, trapHazardId);
            logger.info("Deletion of trapHazard " + th.getName() + " associated to the combat encounter " + th.getCombatEncounter().getName());
            session.delete(th);
            transaction.commit();
        	logger.info("TrapHazard " + trapHazardId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting TrapHazard " + trapHazardId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
		
	}

}
