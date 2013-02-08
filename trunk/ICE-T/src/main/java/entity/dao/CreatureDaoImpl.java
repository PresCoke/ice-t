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
import entity.Creature;
import entity.Stats;
import entity.Team;

public class CreatureDaoImpl implements CreatureDao {

	private static final Logger logger = Logger.getLogger(CreatureDaoImpl.class);
	
	public List<Creature> readAllCreatures() {
    	logger.info("Retrieval of all creatures in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Creature");
		
		List<Creature> creatures = q.list();
		
		return creatures;
	}

    public int saveCreature(String playerName, int currentHP, int currentHealSurges, 
    		int currentLevel, boolean secondWind, int tempHP, CharacterSheet characterSheet){
    	logger.debug("Creature " + playerName + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int creatureID = -1;
        try {
            transaction = session.beginTransaction();
            logger.debug("Setting creature's attributes");
            Creature c = new Creature(playerName);
            c.setCurrentHP(currentHP);
            c.setCurrentHealSurges(currentHealSurges);
            c.setCurrentLevel(currentLevel);
            c.setSecondWind(secondWind);
            c.setTempHP(tempHP);
            c.setCharacterSheet(characterSheet);
            logger.debug("Setting creature's stats");
            Stats stats = new Stats();
            stats.setAssists(0);
            stats.setDeaths(0);
            stats.setHits(0);
            stats.setKills(0);
            stats.setMisses(0);
            stats.setCreature(c);
            c.setStats(stats);
            creatureID = (Integer) session.save(c);
            transaction.commit();
        	logger.info("Creature " + playerName + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving creature " + playerName + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return creatureID;
    }
 
 
    public void updateCreature(int creatureId, String playerName, int currentHP, int currentHealSurges,
    		int currentLevel, boolean secondWind, int tempHP){
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
        	logger.info("Creature " + playerName + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating creature " + playerName + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public void updateCreature(Creature creature, Team team){
    	logger.debug("Creature " + creature.getPlayerName() + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Creature c = (Creature) session.get(Creature.class, creature.getId());
            c.setPlayerName(creature.getPlayerName());
            c.setCurrentHP(creature.getCurrentHP());
            c.setCurrentHealSurges(creature.getCurrentHealSurges());
            c.setCurrentLevel(creature.getCurrentLevel());
            c.setSecondWind(creature.isSecondWind());
            c.setTempHP(creature.getTempHP());
            c.setTeam(team);
            transaction.commit();
        	logger.info("Creature " + creature.getPlayerName() + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating creature " + creature.getPlayerName() + " in the database --- " + e.getMessage());
        } finally {
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
        	logger.info("Creature " + creatureId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting creature " + creatureId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
    }

}
