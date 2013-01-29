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
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Creature");
		
		@SuppressWarnings("unchecked")
		List<Creature> creatures = q.list();
		
		for (Creature c : creatures) {
			logger.debug("Name = " + c.getPlayerName() + " - HP = " + c.getCurrentHP());
		}
	}

    public Long saveCreature(String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long creatureID = null;
        try {
            transaction = session.beginTransaction();
            Creature c = new Creature(playerName);
            c.setCurrentHP(currentHP);
            c.setCurrentHealSurges(currentHealSurges);
            c.setCurrentLevel(currentLevel);
            c.setSecondWind(secondWind);
            c.setTempHP(tempHP);
            creatureID = (Long) session.save(c);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving creature " + playerName + " in the database", e.getCause());
        } finally {
            session.close();
        }
        return creatureID;
    }
 
 
    public void updateCity(Long creatureId, String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP){
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
            session.close();
        }
    }
 
    public void deleteCity(Long creatureId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Creature c = (Creature) session.get(Creature.class, creatureId);
            session.delete(c);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting creature " + creatureId + " in the database", e.getCause());
        } finally {
            session.close();
        }
    }

}
