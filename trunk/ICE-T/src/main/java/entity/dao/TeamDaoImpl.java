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
import entity.Player;
import entity.Team;
import entity.TrapHazard;
import entity.CombatEncounter;

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
    	logger.info("Retrieval of all teams in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select Team_id, Team_name from Team");
		List<Object[]> teams = q.list();
		return teams;
	}

	public int saveTeam(String name, List<Player> players) {
    	logger.debug("Team " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int teamID = -1;
        try {
            transaction = session.beginTransaction();
            Team t = new Team(name);
            for (Player p : players){
                Player player = (Player) session.get(Player.class, p.getId());
                t.addPlayer(player);
            }
            teamID = (Integer) session.save(t);
        	logger.info("Team " + name + " was successfully saved in the database.");
        	logger.debug("Setting players' team");
        	Team team = (Team) session.get(Team.class, teamID);
        	for (Player c : players){
                Player p = (Player) session.get(Player.class, c.getId());
                p.setTeam(team);
                session.update(p);
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
	

	public void updateTeam(int teamId, String name, List<Player> players) {
    	logger.debug("Team " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            t.setName(name);
            logger.debug("Modifying previous players' team");
            List<Player> playersDB = t.getPlayers();
            for (Player p : playersDB){
                Player player = (Player) session.get(Player.class, p.getId());
                player.setTeam(null);
                session.update(player);
            } 
            logger.debug("Modifying previous players' team done");
            t.removeAllPlayers();
            session.update(t);
            logger.debug("Setting team's players and players' team");
            for (Player p : players){
                Player player = (Player) session.get(Player.class, p.getId());
                t.addPlayer(player);
                player.setTeam(t);
                session.update(player);
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

	public int saveNPCteam(String name, List<Monster> monsters,
			List<TrapHazard> traphazards) {
		logger.debug("NPC Team " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int teamID = -1;
        try {
            transaction = session.beginTransaction();
            Team t = new Team(name);
            for (Monster m : monsters){
                Monster monster = (Monster) session.get(Monster.class, m.getId());
                t.addMonster(monster);
            }
            teamID = (Integer) session.save(t);
        	logger.info("Team " + name + " was successfully saved in the database.");
            //Set the monsters
        	logger.debug("Setting monsters");
        	Team team = (Team) session.get(Team.class, teamID);
        	for (Monster m : monsters){
                Monster monster = (Monster) session.get(Monster.class, m.getId());
                monster.setTeam(team);
                session.update(monster);
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
			List<Monster> monsters, List<TrapHazard> traphazards) {
    	logger.debug("NPC Team " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            t.setName(name);
            //Set the trapHazards
            logger.debug("Removing traps from team, so that they can be added again in a different method...");
            for (TrapHazard th : t.getTraphazards()){
                th.setTeam(null);
                session.update(th);
            }
            logger.debug("Traps updated");
            transaction.commit();
        	logger.info("NPC Team " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
        	e.printStackTrace();
            transaction.rollback();
            logger.fatal("Error while updating NPC Team " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}

	public void deleteNPCTeam(int teamId) {
		logger.debug("NPC Team " + teamId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Team t = (Team) session.get(Team.class, teamId);
            //Update monsters
            logger.info("Update of monsters");
            MonsterDao mDao = new MonsterDaoImpl();
            for (Monster m : t.getMonsters()){
                mDao.deleteMonster(m.getId());
            }
            t.removeAllMonsters();
            //Update Traps/Hazards
            logger.info("Update of traps/hazards");
            for (TrapHazard th : t.getTraphazards()){
                th.setTeam(null);
                session.update(th);
            }
            //Deletion of the team
            logger.info("Deletion of NPC team " + t.getName());
            session.delete(t);
            transaction.commit();
        	logger.info("NPC Team " + teamId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting NPC Team " + teamId + " in the database --- " + e.getMessage());
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
            logger.info("Update of players");
            for (Player p : t.getPlayers()){
                Player player = (Player) session.get(Player.class, p.getId());
                player.setTeam(null);
                session.update(player);
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
        	logger.info("Getting all players, monsters and traps belonging to team: "+teamId+".");
//        	Query q = session.createQuery("FROM Player WHERE Team_ID=:value");
//    		q.setParameter("value", teamId);
        	PlayerDao pDAO = new PlayerDaoImpl();
    		List<Player> p = pDAO.getPlayersInTeam(teamId);
        	logger.info(p.size() + " Players retrieved from DB");
//        	q = session.createQuery("FROM Monster WHERE Team_ID=:value");
//        	q.setParameter("value", teamId);
        	MonsterDao mDAO = new MonsterDaoImpl();
        	List<Monster> m = mDAO.getMonstersInTeam(teamId);
        	logger.info(m.size() + " Monsters retrieved from DB");
//        	q = session.createQuery("FROM TrapHazard WHERE Team_ID=:value");
//        	q.setParameter("value", teamId);
        	TrapHazardDao thDAO = new TrapHazardDaoImpl();
        	List<TrapHazard> th = thDAO.getAllTrapHazardsInTeam(teamId);
        	logger.info(th.size() + " TrapHazards retrieved from DB");
        	
        	t.setPlayers(p);
            t.setMonsters(m);
            t.setTraphazards(th);
        	
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while retrieving Team " + teamId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
		return t;
	}

	public List<Team> getAllPlayerTeamsIn(int thisCombatEncounter) {
    	logger.info("Retrieval of all teams in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("FROM Team WHERE CombatEncounter_id=:value");
		q.setParameter("value", thisCombatEncounter);
		List<Team> teams = q.list();
		return teams;
	}

	public void updateTeam(int teamId, CombatEncounter encounter, String name,
			List<Player> players) {
		logger.debug("Team " + name
				+ " is about to be updated in the database.");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Team t = (Team) session.get(Team.class, teamId);
			t.setName(name);
			t.setCombatEncounter(encounter);
			logger.debug("Modifying previous players' team");
			List<Player> playersDB = t.getPlayers();
			for (Player p : playersDB) {
				Player player = (Player) session.get(Player.class, p.getId());
				player.setTeam(null);
				session.update(player);
			}
			logger.debug("Modifying previous players' team done");
			t.removeAllPlayers();
			session.update(t);
			logger.debug("Setting team's players and players' team");
			for (Player p : players) {
				Player player = (Player) session.get(Player.class, p.getId());
				t.addPlayer(player);
				player.setTeam(t);
				session.update(player);
			}
			transaction.commit();
			logger.info("Team " + name
					+ " was successfully updated in the database.");
		} catch (HibernateException e) {
			transaction.rollback();
			logger.fatal("Error while updating Team " + name
					+ " in the database --- " + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void updateNPCteam(int teamId, CombatEncounter encounter,
			String name, List<Monster> monsters, List<TrapHazard> traphazards) {
		logger.debug("NPC Team " + name
				+ " is about to be updated in the database.");
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Team t = (Team) session.get(Team.class, teamId);
			t.setName(name);
			t.setCombatEncounter(encounter);
			// Set the monsters
			logger.debug("Setting NPC team's monsters and monsters' team");
			for (Monster m : monsters) {
				Monster monster = (Monster) session.get(Monster.class,
						m.getId());
				t.addMonster(monster);
				monster.setTeam(t);
				session.update(monster);
			}
			// Set the traps/hazards
			logger.debug("Modifying previous traps/hazards' team");
			List<TrapHazard> trapHazardsDB = t.getTraphazards();
			for (TrapHazard th : trapHazardsDB) {
				TrapHazard traphazard = (TrapHazard) session.get(
						TrapHazard.class, th.getId());
				traphazard.setTeam(null);
				session.update(traphazard);
			}
			logger.debug("Modifying previous traps/hazards' team done");
			t.removeAllTrapHazards();
			session.update(t);
			logger.debug("Setting NPC team of the traps/hazards and traps/hazards' team");
			for (TrapHazard th : traphazards) {
				TrapHazard traphazard = (TrapHazard) session.get(
						TrapHazard.class, th.getId());
				t.addTrapHazard(traphazard);
				traphazard.setTeam(t);
				session.update(traphazard);
			}
			transaction.commit();
			logger.info("NPC Team " + name
					+ " was successfully updated in the database.");
		} catch (HibernateException e) {
			transaction.rollback();
			logger.fatal("Error while updating NPC Team " + name
					+ " in the database --- " + e.getMessage());
		} finally {
			session.close();
		}
	}

}
