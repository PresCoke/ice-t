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
import entity.Player;
import entity.Stats;
import entity.Team;

public class PlayerDaoImpl implements PlayerDao {

	private static final Logger logger = Logger.getLogger(PlayerDaoImpl.class);
	
	public List<Object[]> readAllPlayers() {
    	logger.info("Retrieval of all players in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select Player_id, player_name from Player");
		List<Object[]> players = q.list();
		return players;
	}
	
	public List<Player> getAllPlayers() {
    	logger.info("Retrieval of all players in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Player");
		List<Player> players = q.list();
		return players;
	}
	
	public List<Player> getPlayersInTeam(int teamId) {
    	logger.info("Retrieval of all players in the team " + teamId);
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Player where Team_id="+teamId);
		List<Player> players = q.list();
		return players;
	}
	
    public int savePlayer(String playerName, int currentHP, int currentHealSurges, 
    		int initiative, boolean secondWind, int tempHP, CharacterSheet characterSheet){
    	logger.debug("Player " + playerName + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int playerId = -1;
        try {
            transaction = session.beginTransaction();
            logger.debug("Setting player's attributes");
            Player p = new Player(playerName);
            p.setCurrentHP(currentHP);
            p.setCurrentHealSurges(currentHealSurges);
            p.setInitiative(initiative);
            p.setSecondWind(secondWind);
            p.setTempHP(tempHP);
            if (characterSheet.isNPC()){
                logger.error("The character sheet is not supposed to be associated to a Player but a Monster.");
            	throw new DAOException();
            }
            p.setCharacterSheet(characterSheet);
            logger.debug("Setting player's stats");
            Stats stats = new Stats();
            stats.setAssists(0);
            stats.setDeaths(0);
            stats.setHits(0);
            stats.setKills(0);
            stats.setMisses(0);
            stats.setPlayer(p);
            p.setStats(stats);
            playerId = (Integer) session.save(p);
            transaction.commit();
        	logger.info("Player " + playerName + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            logger.fatal("Error while saving player " + playerName + " in the database --- " + e.getMessage());
        } catch (DAOException e) {
            transaction.rollback();
            logger.fatal("Error while saving player " + playerName + " in the database.");
        } finally {
            session.close();
        }
        return playerId;
    }
 
 
    public void updatePlayer(int playerId, String playerName, int currentHP, int currentHealSurges,
    		int initiative, boolean secondWind, int tempHP){
    	logger.debug("Player " + playerName + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Player p = (Player) session.get(Player.class, playerId);
            p.setPlayerName(playerName);
            p.setCurrentHP(currentHP);
            p.setCurrentHealSurges(currentHealSurges);
            p.setInitiative(initiative);
            p.setSecondWind(secondWind);
            p.setTempHP(tempHP);
            transaction.commit();
        	logger.info("Player " + playerName + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating player " + playerName + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public void updatePlayer(Player player, Team team){
    	logger.debug("Player " + player.getPlayerName() + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Player p = (Player) session.get(Player.class, player.getId());
            p.setPlayerName(player.getPlayerName());
            p.setCurrentHP(player.getCurrentHP());
            p.setCurrentHealSurges(player.getCurrentHealSurges());
            p.setInitiative(player.getInitiative());
            p.setSecondWind(player.isSecondWind());
            p.setTempHP(player.getTempHP());
            p.setTeam(team);
            transaction.commit();
        	logger.info("Player " + player.getPlayerName() + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating player " + player.getPlayerName() + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
    }
 
    public void deletePlayer(int playerId){
		logger.debug("Player " + playerId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Player p = (Player) session.get(Player.class, playerId);
            logger.info("Deletion of player " + p.getPlayerName());
            session.delete(p);
            transaction.commit();
        	logger.info("Player " + playerId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting player " + playerId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
    }

	public Player getPlayerOfId(int playerId) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();		
		Player pl = (Player) session.get(Player.class, playerId);
		return pl;
	}

}
