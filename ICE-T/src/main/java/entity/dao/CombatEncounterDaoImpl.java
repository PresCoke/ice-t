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
import entity.Tally;
import entity.Team;
import entity.Tuple;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public class CombatEncounterDaoImpl implements CombatEncounterDao {

	private static final Logger logger = Logger.getLogger(CombatEncounterDaoImpl.class);
	
	//TODO FIX that to get the names of the Combat Encounters only
	public List<Object[]>readAllCombatEncounters() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select CombatEncounter_id, CombatEncounter_name from CombatEncounter");
		List<Object[]> ces = q.list();
		return ces;
	}

	public int saveCombatEncounter(String name, String notes, int currentCreatureId,
			List<Rewards> rewards, Tally tally, List<Tuple> tuples, List<Team> teams) {
    	logger.debug("CombatEncounter " + name + " is about to be created in the database.");
    	Session session = null;
    	session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int combatEncounterID = -1;
        try {
            transaction = session.beginTransaction();
            logger.debug("Setting CE's attributes");
            CombatEncounter ce = new CombatEncounter(name, notes, currentCreatureId);
            //Set the rewards
            logger.debug("Setting CE's rewards");
            if(rewards != null && !rewards.isEmpty()){
	            for(Rewards r : rewards){
	            	ce.addRewards(r);
	            	r.setCombatEncounter(ce);
	            }
            }
            //Set the tally and the tuples
            logger.debug("Setting CE's tally and tuples");
            if (tally != null){
	            ce.setTally(tally);
	            tally.setCombatEncounter(ce);
	            tally.setTuples(tuples);
	            for (Tuple t : tuples){
	            	t.setTally(tally);
	            }
            }
            //Set the teams
           /*if (teams != null && !teams.isEmpty()){
                logger.debug("Setting CE's teams");
	            ce.setTeams(teams);
	            for (Team t : teams){
	            	t.setCombatEncounter(ce);
	            	if (t.getPlayers().size() != 0 && t.getTraphazards().size() == 0 && t.getMonsters().size() == 0) {
	    				t.editTeam(ce);
	            	} else if (t.getPlayers().size() != 0 && (t.getTraphazards().size() != 0 || t.getMonsters().size() != 0)) {
	    				t.editNPCTeam(ce, t.getMonsters());
	            	}
	            }  
	            logger.debug("Teams updated");
            }*/
            combatEncounterID = (Integer) session.save(ce);
            transaction.commit();
        	logger.info("CombatEncounter " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
        	combatEncounterID = -1;
            transaction.rollback();
            logger.fatal("Error while saving CombatEncounter " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return combatEncounterID;
	}

	public void updateCombatEncounter(int combatEncounterId, String name,
			String notes, int currentCreatureId) {
    	logger.debug("CombatEncounter " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CombatEncounter ce = (CombatEncounter) session.get(CombatEncounter.class, combatEncounterId);
            ce.setName(name);
            ce.setNotes(notes);
            ce.setCurrentCreatureId(currentCreatureId);
            transaction.commit();
        	logger.info("CombatEncounter " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating CombatEncounter " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

	public void deleteCombatEncounter(int combatEncounterId) {
		logger.debug("CombatEncounter " + combatEncounterId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            CombatEncounter ce = (CombatEncounter) session.get(CombatEncounter.class, combatEncounterId);
            List<Team> teams = ce.getTeams();
            logger.info("Deletion of Combat encounter " + ce.getName());
            session.delete(ce);
            logger.debug("Update of teams and traps");
            for (Team t : teams){
                t.setCombatEncounter(null);
            }
            transaction.commit();
        	logger.info("CombatEncounter " + combatEncounterId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting CombatEncounter " + combatEncounterId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }	
	}
	
	public CombatEncounter getCombatEncounter(int combatEncounterId){
		logger.debug("CombatEncounter " + combatEncounterId + " is about to be retrieved from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        CombatEncounter ce = null;
        try {
            transaction = session.beginTransaction();
            ce = (CombatEncounter) session.get(CombatEncounter.class, combatEncounterId);
            
            TeamDao tDAO = new TeamDaoImpl();
            List<Team> t = tDAO.getAllPlayerTeamsIn(ce.getId());
            
            TallyDao tlDAO = new TallyDaoImpl();
            Tally tl = tlDAO.getTally(combatEncounterId).get(0);
            
            TupleDao tpDAO = new TupleDaoImpl();
            List<Tuple> tp = tpDAO.getTuple(tl.getId());
            
            RewardsDao rwDAO = new RewardsDaoImpl();
            List<Rewards> rw = rwDAO.getRewards(combatEncounterId);
            
            ce.setTeams(t);
            tl.setTuples(tp);
            ce.setTally(tl);
            ce.setRewards(rw);
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while retrieving CombatEncounter " + combatEncounterId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }	
        return ce;
	}

	public boolean doesEncounterExist(int thisID) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select * from CombatEncounter where CombatEncounter.CombatEncounter_id = " + thisID);
		List<Object[]> ces = q.list();
		if (ces.size() == 0) {
			logger.debug("Combat Encounter " + Integer.toString(thisID) + " does not exist" );
			return false;
		} else {
			return true;
		}
	}

}
