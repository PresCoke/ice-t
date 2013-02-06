package entity.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.CombatEncounter;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public class CombatEncounterDaoImpl implements CombatEncounterDao {

	private static final Logger logger = Logger.getLogger(CombatEncounterDaoImpl.class);
	
	public void readAllCombatEncounters() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from CombatEncounter");
		
		/*-- WTF @SuppressWarnings("unchecked") --*/
		List<CombatEncounter> ces = q.list();
		
		for (CombatEncounter ce : ces) {
			logger.debug("Name = " + ce.getName() + " - Notes = " + ce.getNotes());
		}	
	}

	public int saveCombatEncounter(String name, String notes) {
    	logger.debug("CombatEncounter " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int combatEncounterID = -1;
        try {
            transaction = session.beginTransaction();
            CombatEncounter ce = new CombatEncounter(name);
            ce.setNotes(notes);
            combatEncounterID = (Integer) session.save(ce);
            transaction.commit();
        	logger.info("CombatEncounter " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving CombatEncounter " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return combatEncounterID;
	}

	public void updateCombatEncounter(int combatEncounterId, String name,
			String notes) {
    	logger.debug("CombatEncounter " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CombatEncounter ce = (CombatEncounter) session.get(CombatEncounter.class, combatEncounterId);
            ce.setName(name);
            ce.setNotes(notes);
            transaction.commit();
        	logger.info("CombatEncounter " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating CombatEncounter " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

	public void deleteCombatEncounter(int combatEncounterId) {
		logger.debug("CombatEncounter " + combatEncounterId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CombatEncounter ce = (CombatEncounter) session.get(CombatEncounter.class, combatEncounterId);
            logger.info("Deletion of Combat encounter " + ce.getName());
            session.delete(ce);
            transaction.commit();
        	logger.info("CombatEncounter " + combatEncounterId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting CombatEncounter " + combatEncounterId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }	
	}
}
