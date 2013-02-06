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
import entity.Team;

/**
 * DAO of a team
 * @author TimHP
 *
 */
public class TeamDaoImpl implements TeamDao {

	private static final Logger logger = Logger.getLogger(TeamDaoImpl.class);

	public TeamDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void readAllTeams() {
    	logger.info("Retrieval of all creatures in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Team");
		
		/*-- WTF @SuppressWarnings("unchecked") --*/
		List<Team> teams = q.list();
		
		for (Team t : teams) {
			logger.info("Team Name = " + t.getName());
		}		
	}

	public int saveTeam(String name) {
    	logger.debug("Team " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int teamID = -1;
        try {
            transaction = session.beginTransaction();
            Creature c = new Creature(name);
            teamID = (Integer) session.save(c);
            transaction.commit();
        	logger.info("Team " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return teamID;
	}

	public void updateTeam(int teamId, String name) {
    	logger.debug("Team " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            t.setName(name);
            transaction.commit();
        	logger.info("Team " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}

	public void deleteTeam(int teamId) {
		logger.debug("Team " + teamId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            logger.info("Deletion of team " + t.getName() + " associated to the combat encounter " + t.getCombatEncounter().getName());
            session.delete(t);
            transaction.commit();
        	logger.info("Team " + teamId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Team " + teamId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

}
