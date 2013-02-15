package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.CombatEncounter;
import entity.Tally;

public class TallyDaoImpl implements TallyDao {
	
	private static final Logger logger = Logger.getLogger(TallyDaoImpl.class);

	public TallyDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public int saveTally (String name, int combatEncounterId) {
    	logger.debug("A new tally named " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int tallyId = -1;
        try {
            transaction = session.beginTransaction();
            Tally t = new Tally(name);
            logger.debug("Association with a combat encounter");
            CombatEncounter ce = (CombatEncounter) session.load(CombatEncounter.class, combatEncounterId);
            t.setCombatEncounter(ce);
            tallyId = (Integer) session.save(t);
            transaction.commit();
        	logger.info("A new tally named " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving a new tally " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return tallyId;
	}
	
	public void deleteTally(int tallyId) {
		logger.debug("Tally " + tallyId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Tally t = (Tally) session.get(Tally.class, tallyId);
            logger.info("Deletion of Tally " + t.getName());
            session.delete(t);
            transaction.commit();
        	logger.info("Tally " + tallyId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Tally " + tallyId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

}
