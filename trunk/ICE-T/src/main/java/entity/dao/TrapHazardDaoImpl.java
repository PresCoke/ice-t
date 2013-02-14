package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.A_Area;
import entity.A_Close;
import entity.A_Melee;
import entity.A_Range;
import entity.Attack;
import entity.Attack_Type;
import entity.EntityEnum.T_CounterMeasureSkill;
import entity.EntityEnum.T_Role;
import entity.EntityEnum.T_Type;
import entity.TrapHazard;

/**
 * DAO of a trap or a hazard
 * @author TimHP
 *
 */
public class TrapHazardDaoImpl implements TrapHazardDao {

	private static final Logger logger = Logger.getLogger(TrapHazardDaoImpl.class);
	
	public List<Object[]> readAllTrapHazards() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select TrapHazard_id, TrapHazard_name from TrapHazard");
		List<Object[]> ths = q.list();
		return ths;
	}
	
	public List<TrapHazard> getAllTrapHazards() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from TrapHazard");
		List<TrapHazard> ths = q.list();
		return ths;
	}

	public int saveTrapHazard(String name, int avoidance, int level,
			T_CounterMeasureSkill skill, String triggers, int xp,
			int difficultyLevel, String counterMeasureDescription, T_Type type,
			T_Role role, T_CounterMeasureSkill counterMeasureSkill, Attack attack,
			Attack_Type atype) {

    	logger.debug("TrapHazard " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int trapHazardID = -1;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = new TrapHazard(name);
            th.setAvoidance(avoidance);
            th.setLevel(level);
            th.setAvoidanceSkill(skill);
            th.setTriggers(triggers);
            th.setXp(xp);
            th.setDifficultyLevel(difficultyLevel);
            th.setCounterMeasureDescription(counterMeasureDescription);
            th.setType(type);
            th.setRole(role);
            th.setCounterMeasureSkill(counterMeasureSkill);
            th.setAttack(attack);
            attack.setTrap(th);
            attack.setAttackType(atype);
            atype.setAttack(attack);
            trapHazardID = (Integer) session.save(th);
            transaction.commit();
        	logger.info("TrapHazard " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving TrapHazard " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return trapHazardID;
	}

	public void updateTrapHazard(int trapHazardId, String name, int avoidance,
			int level, T_CounterMeasureSkill skill, String triggers, int xp,
			int difficultyLevel, String counterMeasureDescription, T_Type type,
			T_Role role, T_CounterMeasureSkill counterMeasureSkill, 
			Attack attack, Attack_Type atype) {

    	logger.debug("TrapHazard " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = (TrapHazard) session.get(TrapHazard.class, trapHazardId); 
            logger.debug("Setting trap's attributes");
            th.setName(name);
            th.setAvoidance(avoidance);
            th.setLevel(level);
            th.setAvoidanceSkill(skill);
            th.setTriggers(triggers);
            th.setXp(xp);
            th.setDifficultyLevel(difficultyLevel);
            th.setCounterMeasureDescription(counterMeasureDescription);
            th.setType(type);
            th.setRole(role);
            th.setCounterMeasureSkill(counterMeasureSkill);
            logger.debug("Setting trap's attack");
            Attack a = th.getAttack();
            a.setAttackName(attack.getAttackName());
    		a.setPrimaryTarget(attack.getPrimaryTarget());
    		a.setSecondaryTarget(attack.getSecondaryTarget());
    		a.setAccessories(attack.getAccessories());
    		a.setPowerSource(attack.getPowerSource());
    		a.setFrequency(attack.getFrequency());
    		a.setHit(attack.getHit());
    		a.setMiss(attack.getMiss());
    		a.setBasic(attack.isBasic());
    		a.setTrigger(attack.getTrigger());
    		a.setEffectType(attack.getEffectType());
    		a.setAbility(attack.getAbility());
    		a.setDamageType(attack.getDamageType());
    		a.setDefense(attack.getDefense());
    		a.setSustain(attack.getSustain());
    		a.setAction(attack.getAction());
    		a.setUseType(attack.getUseType());
            logger.debug("Setting trap's attack type");
    		if (a.getAttackType() instanceof A_Area && atype instanceof A_Area){
    			A_Area previousType = (A_Area) a.getAttackType();
    			A_Area newType = (A_Area) atype;
    			previousType.setPersonal(newType.isPersonal());
    			previousType.setArea_range(newType.getArea_range());
    			previousType.setArea_size(newType.getArea_size());
    			previousType.setArea_type(newType.getArea_type());
    		} else if (a.getAttackType() instanceof A_Close && atype instanceof A_Close){
    			A_Close previousType = (A_Close) a.getAttackType();
    			A_Close newType = (A_Close) atype;
    			previousType.setPersonal(newType.isPersonal());
    			previousType.setCloseType(newType.getCloseType());
    			previousType.setSize(newType.getSize());
    		} else if (a.getAttackType() instanceof A_Melee && atype instanceof A_Melee){
    			A_Melee previousType = (A_Melee) a.getAttackType();
    			A_Melee newType = (A_Melee) atype;
    			previousType.setPersonal(newType.isPersonal());
    			previousType.setReach(newType.getReach());
    		} else if (a.getAttackType() instanceof A_Range && atype instanceof A_Range){
    			A_Range previousType = (A_Range) a.getAttackType();
    			A_Range newType = (A_Range) atype;
    			previousType.setPersonal(newType.isPersonal());
    			previousType.setL_range(newType.getL_range());
    			previousType.setS_range(newType.getS_range());
    		} else {
    			Attack_TypeDao attacktypeDao = new Attack_TypeDaoImpl();
    			attacktypeDao.deleteAttackType(a.getAttackType().getId());
    			atype.setAttack(a);
    			attacktypeDao.saveAttackType(atype);
    			session.flush();
    			session.evict(a.getAttackType());
    			a.setAttackType(atype);
    		}
            transaction.commit();
        	logger.info("TrapHazard " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating TrapHazard " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}

	public void deleteTrapHazard(int trapHazardId) {
		logger.debug("TrapHazard " + trapHazardId + " is about to be removed from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            TrapHazard th = (TrapHazard) session.get(TrapHazard.class, trapHazardId);
            logger.info("Deletion of trapHazard " + th.getName());
            session.delete(th);
            transaction.commit();
        	logger.info("TrapHazard " + trapHazardId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting TrapHazard " + trapHazardId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
		
	}
	
	public TrapHazard getTrapHazard(int trapHazardId){
		logger.debug("TrapHazard " + trapHazardId + " is about to be retrieved from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        TrapHazard th = null;
        try {
            transaction = session.beginTransaction();
            th = (TrapHazard) session.get(TrapHazard.class, trapHazardId);
        	logger.info("TrapHazard " + trapHazardId + " was successfully retrieved from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while retrieving TrapHazard " + trapHazardId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return th;
	}


}
