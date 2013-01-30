package entity.dao;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import resource.HibernateUtil;
import entity.Attack;
import entity.CombatEncounter;
import entity.Creature;
import entity.Effect;
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
		for (CombatEncounter ce : ces) {
			logger.debug("CE Name = " + ce.getName());
			Set<Team> teams = ce.getTeams();
			for (Team t : teams){
				logger.debug("Team associated : Name = " + t.getName());
				Set<Creature> creatures = t.getCreatures();
				for (Creature c : creatures){	
					logger.debug("Creature associated : Player_Name = " + c.getPlayerName() + " - HP = " + c.getCurrentHP());
					logger.debug("Stats associated : Kills = " + c.getStats().getKills() + " - Assists = " + c.getStats().getAssists());
					Set<Effect> effects = c.getEffects();
					for (Effect e : effects){
						logger.debug("Effects associated : Name = " + e.getName()  + " - Damage = " + e.getDamage());
					}
					logger.debug("CharacterSheet associated : Name = " + c.getCharacterSheet().getName() + " - Bluff =  " + c.getCharacterSheet().getBluff());
					logger.debug("Resistance associated : Name = " + c.getCharacterSheet().getResistanceAt(0).getName() + " - Value =  " + c.getCharacterSheet().getResistanceAt(0).getResistanceValue());
					Set<Attack> attacks = c.getAttacks();
					for (Attack a : attacks){
						logger.debug("Attack associated : Primary Target = " + a.getPrimaryTarget() + " - frequency = " + a.getFrequency());
					}

				}
			}
		}
	}
}
