package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.Effect;
import entity.EntityEnum.E_Duration;

/**
 * DAO of an effect
 * @author TimHP
 *
 */
public class EffectDaoImpl implements EffectDao {
	
	private static final Logger logger = Logger.getLogger(EffectDaoImpl.class);

	public EffectDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public void readAllEffects() {
		logger.info("Retrieval of all effects in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Effect");
		
		@SuppressWarnings("unchecked")
		List<Effect> effects = q.list();
		
		for (Effect e : effects) {
			logger.info("Effect Name = " + e.getName() + " - Damage = "+ e.getDamage());
		}
		
	}

	public int saveEffect(String name, String changes, int damage,
			String metrics, E_Duration duration) {
    	logger.debug("Effect " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int effectId = -1;
        try {
            transaction = session.beginTransaction();
            Effect e = new Effect(name);
            e.setChanges(changes);
            e.setDamage(damage);
            e.setMetrics(metrics);
            e.setDuration(duration);
            effectId = (Integer) session.save(e);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Effect " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Effect " + name + " was successfully saved in the database.");
            session.close();
        }
        return effectId;
	}

	public void updateTrapHazard(int effectId, String name, String changes,
			int damage, String metrics, E_Duration duration) {
    	logger.debug("Effect " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Effect e = (Effect) session.get(Effect.class, effectId);
            e.setName(name);
            e.setChanges(changes);
            e.setDamage(damage);
            e.setMetrics(metrics);
            e.setDuration(duration);
            effectId = (Integer) session.save(e);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Effect " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Effect " + name + " was successfully updated in the database.");
            session.close();
        }		
	}

	public void deleteTrapHazard(int effectId) {
		logger.debug("Effect " + effectId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Effect e = (Effect) session.get(Effect.class, effectId);
            logger.info("Deletion of Effect " + e.getName() + "associated to the creature " + e.getCreature().getPlayerName());
            session.delete(e);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Effect " + effectId + " in the database", e.getCause());
        } finally {
        	logger.info("Effect " + effectId + " was successfully removed from the database.");
            session.close();
        }
	}

}
