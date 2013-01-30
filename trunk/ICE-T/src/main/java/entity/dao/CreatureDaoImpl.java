package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.Creature;

public class CreatureDaoImpl implements CreatureDao {

	private static final Logger logger = Logger.getLogger(CreatureDaoImpl.class);
	
	public void readAllCreatures() {
    	logger.info("Retrieval of all creatures in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Creature");
		
		@SuppressWarnings("unchecked")
		List<Creature> creatures = q.list();
		
		for (Creature c : creatures) {
			logger.info("Creature Name = " + c.getCharacterSheet().getName() + " - Player Name = "+ c.getPlayerName());
		}
	}

    public int saveCreature(String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP){
    	logger.debug("Creature " + playerName + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int creatureID = -1;
        try {
            transaction = session.beginTransaction();
            Creature c = new Creature(playerName);
            c.setCurrentHP(currentHP);
            c.setCurrentHealSurges(currentHealSurges);
            c.setCurrentLevel(currentLevel);
            c.setSecondWind(secondWind);
            c.setTempHP(tempHP);
            creatureID = (Integer) session.save(c);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving creature " + playerName + " in the database", e.getCause());
        } finally {
        	logger.info("Creature " + playerName + " was successfully saved in the database.");
            session.close();
        }
        return creatureID;
    }
 
 
    public void updateCreature(int creatureId, String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP){
    	logger.debug("Creature " + playerName + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Creature c = (Creature) session.get(Creature.class, creatureId);
            c.setPlayerName(playerName);
            c.setCurrentHP(currentHP);
            c.setCurrentHealSurges(currentHealSurges);
            c.setCurrentLevel(currentLevel);
            c.setSecondWind(secondWind);
            c.setTempHP(tempHP);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating creature " + playerName + " in the database", e.getCause());
        } finally {
        	logger.info("Creature " + playerName + " was successfully updated in the database.");
            session.close();
        }
    }
 
    public void deleteCreature(int creatureId){
		logger.debug("Creature " + creatureId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Creature c = (Creature) session.get(Creature.class, creatureId);
            logger.info("Deletion of Creature " + c.getPlayerName());
            session.delete(c);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting creature " + creatureId + " in the database", e.getCause());
        } finally {
        	logger.info("Creature " + creatureId + " was successfully removed from the database.");
            session.close();
        }
    }

}
