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
import entity.Rewards;

public class RewardsDaoImpl implements RewardsDao {
	
	private static final Logger logger = Logger.getLogger(RewardsDaoImpl.class);

	public RewardsDaoImpl() {
	}

	public int saveRewards(int XP, String treasure, int combatEncounterID) {
    	logger.debug("Rewards is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int rewardsID = -1;
        try {
            transaction = session.beginTransaction();
            Rewards r = new Rewards();
            r.setXP(XP);
            r.setTreasure(treasure);
            logger.debug("Association with a combat encounter");
            CombatEncounter ce = (CombatEncounter) session.load(CombatEncounter.class, combatEncounterID);
            logger.debug("CombatEncounter name = " + ce.getName());
        	r.setCombatEncounter(ce);
        	rewardsID = (Integer) session.save(r);
            transaction.commit();
        	logger.info("Rewards was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Rewards in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return rewardsID;
	}

	public void deleteRewards(int rewardsId) {
		logger.debug("Rewards " + rewardsId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Rewards r = (Rewards) session.get(Rewards.class, rewardsId);
            CombatEncounter ce = r.getCombatEncounter();
            logger.info("Deletion of rewards " + r.getId() + " associated to the combat encounter " + r.getCombatEncounter().getName());
            session.delete(r);
            logger.info("Updtate of the Combat encounter associated to the resistance removed.");
            session.saveOrUpdate(ce);
            transaction.commit();
        	logger.info("Rewards " + rewardsId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Rewards " + rewardsId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }			
	}

	public List<Rewards> getRewards(int combatEncounterId) {
		logger.info("Rewards belonging to Combat Ecounter: " + combatEncounterId
				+ " are about to be retrieved from the database.");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("FROM Rewards WHERE CombatEncounter_ID=:value");
		q.setParameter("value", combatEncounterId);
		return q.list();
	}

}
