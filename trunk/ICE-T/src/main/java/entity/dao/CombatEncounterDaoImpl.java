package entity.dao;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import resource.HibernateUtil;
import entity.CombatEncounter;
import entity.Creature;
import entity.EntityM;
import entity.Rewards;
import entity.Stats;
import entity.Tally;
import entity.Team;
import entity.TrapHazard;
import entity.Tuple;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public class CombatEncounterDaoImpl implements CombatEncounterDao {

	private static final Logger logger = Logger.getLogger(CreatureDaoImpl.class);
	
	public void readAllCombatEncounters() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from CombatEncounter");
		
		@SuppressWarnings("unchecked")
		List<CombatEncounter> ces = q.list();
		
//		for (CombatEncounter ce : ces) {
//			logger.debug("Name = " + ce.getName() + " - Notes = " + ce.getNotes());
//		}	
//		
//		for (CombatEncounter ce : ces) {
//			Set<TrapHazard> traps = ce.getTraphazards();
//			for (TrapHazard th : traps){
//				logger.debug("CE Name = " + ce.getName());
//				logger.debug("Traps associated = " + th.getName() + " - Triggers = " + th.getTriggers());
//			}
//		}	
//		
//		for (CombatEncounter ce : ces) {
//			Set<Rewards> rewards = ce.getRewards();
//			for (Rewards r : rewards){
//				logger.debug("CE Name = " + ce.getName());
//				logger.debug("Treasure associated = " + r.getTreasure() + " - XP = " + r.getXP());
//			}
//		}
//		
//		for (CombatEncounter ce : ces) {
//			Tally t = ce.getTally();
//			logger.debug("CE Name = " + ce.getName());
//			logger.debug("Tally associated : Name = " + t.getName());
//			Set<Tuple> tuples = t.getTuples();
//			for (Tuple tu : tuples){
//				logger.debug("Tuple associated : Name = " + tu.getName() + " - Value1 = " + tu.getValue1() + " - Value2 = " + tu.getValue2());
//			}
//		}
//		
//		for (CombatEncounter ce : ces) {
//			logger.debug("CE Name = " + ce.getName());
//			Set<Stats> stats = ce.getStats();
//			for (Stats s : stats){
//				logger.debug("Stats associated : Deaths = " + s.getDeaths() + " - Assists = " + s.getAssists());
//			}
//		}
		
		
		for (CombatEncounter ce : ces) {
			logger.debug("CE Name = " + ce.getName());
			Set<Team> teams = ce.getTeams();
			for (Team t : teams){
				logger.debug("Team associated : Name = " + t.getName());
//				Set<Creature> creatures = t.getCreatures();
//				logger.debug("liste vide ?? " + creatures.isEmpty());
//				for (Creature c : creatures){	
//					logger.debug("Creature associated : Name = " + c.getName() + " - Nature = " + c.getNature() + " - HP = " + c.getCurrentHP());
//				}
			}
		}
	}
}
