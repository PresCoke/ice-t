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
import entity.TrapHazard;

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

	public List<Object[]> readAllTeams() {
    	logger.info("Retrieval of all creatures in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("Select Team_id, Team_name from Team");
		List<Object[]> teams = q.list();
		return teams;
	}

	public int saveTeam(String name, List<Creature> creatures) {
    	logger.debug("Team " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int teamID = -1;
        try {
            transaction = session.beginTransaction();
            Team t = new Team(name);
            for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                t.addCreature(creature);
            }
            teamID = (Integer) session.save(t);
        	logger.info("Team " + name + " was successfully saved in the database.");
        	logger.debug("Setting creatures' team");
        	Team team = (Team) session.get(Team.class, teamID);
        	for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                creature.setTeam(team);
                session.update(creature);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return teamID;
	}

	public void updateTeam(int teamId, String name, List<Creature> creatures) {
    	logger.debug("Team " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            t.setName(name);
            logger.debug("Modifying previous creatures' team");
            List<Creature> creaturesDB = t.getCreatures();
            for (Creature c : creaturesDB){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                creature.setTeam(null);
                session.update(creature);
            } 
            logger.debug("Modifying previous creatures' team done");
            t.removeAllCreatures();
            session.update(t);
            logger.debug("Setting team's creatures and creatures' team");
            for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                t.addCreature(creature);
                creature.setTeam(t);
                session.update(creature);
            }
            transaction.commit();
        	logger.info("Team " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}
	
	
	public int saveNPCteam(String name, List<Creature> creatures,
			List<TrapHazard> traphazards) {
    	logger.debug("NPC Team " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int teamID = -1;
        try {
            transaction = session.beginTransaction();
            Team t = new Team(name);
            for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                t.addCreature(creature);
            }
            teamID = (Integer) session.save(t);
        	logger.info("Team " + name + " was successfully saved in the database.");
            //Set the creatures
        	logger.debug("Setting creatures");
        	Team team = (Team) session.get(Team.class, teamID);
        	for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                creature.setTeam(team);
                session.update(creature);
            }
            //Set the trapHazards
            logger.debug("Setting traps");
            team.setTraphazards(traphazards);
            for (TrapHazard th : traphazards){
            	th.setTeam(team);
            }
            logger.debug("Traps updated");
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving NPC Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return teamID;
	}

	public void updateNPCteam(int teamId, String name,
			List<Creature> creatures, List<TrapHazard> traphazards) {
    	logger.debug("NPC Team " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            t.setName(name);
            //Set the creatures
            logger.debug("Modifying previous creatures' team");
            List<Creature> creaturesDB = t.getCreatures();
            for (Creature c : creaturesDB){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                creature.setTeam(null);
                session.update(creature);
            } 
            logger.debug("Modifying previous creatures' team done");
            t.removeAllCreatures();
            session.update(t);
            logger.debug("Setting NPC team's creatures and creatures' team");
            for (Creature c : creatures){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                t.addCreature(creature);
                creature.setTeam(t);
                session.update(creature);
            }
            //Set the traps/hazards
            logger.debug("Modifying previous traps/hazards' team");
            List<TrapHazard> trapHazardsDB = t.getTraphazards();
            for (TrapHazard th : trapHazardsDB){
            	TrapHazard traphazard = (TrapHazard) session.get(TrapHazard.class, th.getId());
            	traphazard.setTeam(null);
                session.update(traphazard);
            } 
            logger.debug("Modifying previous traps/hazards' team done");
            t.removeAllTrapHazards();
            session.update(t);
            logger.debug("Setting NPC team of the traps/hazards and traps/hazards' team");
            for (TrapHazard th : traphazards){
            	TrapHazard traphazard = (TrapHazard) session.get(TrapHazard.class, th.getId());
                t.addTrapHazard(traphazard);
                traphazard.setTeam(t);
                session.update(traphazard);
            }
            transaction.commit();
        	logger.info("NPC Team " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating NPC Team " + name + " in the database --- " + e.getMessage());
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
            //Update creatures
            logger.info("Update of creatures");
            for (Creature c : t.getCreatures()){
                Creature creature = (Creature) session.get(Creature.class, c.getId());
                creature.setTeam(null);
                session.update(creature);
            }
            //Update Traps/Hazards
            logger.info("Update of traps/hazards");
            for (TrapHazard th : t.getTraphazards()){
                th.setTeam(null);
            }
            //Deletion of the team
            logger.info("Deletion of team " + t.getName());
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
	
	public Team getTeam(int teamId){
		logger.debug("Team " + teamId + " is about to be retrieved from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Team t = null;
        try {
            transaction = session.beginTransaction();
            t = (Team) session.get(Team.class, teamId);
        	logger.info("Team " + teamId + " was successfully retrieved from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while retrieving Team " + teamId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
		return t;
	}

}
