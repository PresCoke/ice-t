package entity.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import resource.HibernateUtil;

import entity.Player;
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

	public List<Object[]> readAllEffects() {
		logger.info("Retrieval of all effects in the database");
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createSQLQuery("Select Effect_id, Effect_name from Effect");
		List<Object[]> effects = q.list();
		return effects;
	}

	public List<Integer> saveEffect(String name, String changes, String metrics,
			E_Duration duration, List<Player> creatures) {
    	logger.debug("Effect " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Integer> effectsIds = new ArrayList<Integer>();
        try {
            transaction = session.beginTransaction();
            for (Player c : creatures){
                int effectId = -1;
	        	logger.debug("Setting the effect's attributes");
	            Effect e = new Effect(name);
	            e.setChanges(changes);
	            e.setMetrics(metrics);
	            e.setDuration(duration);
	        	logger.debug("Setting the effect's creatures");
	            Player creature = (Player) session.get(Player.class, c.getId());
	            e.setCreature(creature);
	            effectId = (Integer) session.save(e);
	            effectsIds.add(effectId);
	        	logger.debug("Setting the creatures' effect");
	        	creature.addEffect(e);
	        	session.update(creature);
            }
            transaction.commit();
        	logger.info("Effect " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Effect " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return effectsIds;
	}
	
	public void deleteEffects(List<Integer> effectsIds) {
		logger.debug("Effects whose ids are " + effectsIds.toString() + " are about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (int i : effectsIds){
	            Effect effect = (Effect) session.get(Effect.class, i);
	            session.delete(effect);
            }
            transaction.commit();
        	logger.info("Effects whose ids are " + effectsIds.toString() + " were successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while removing effects whose ids are " + effectsIds.toString() + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }	
	}

	public void deleteEffect(int effectId) {
		logger.debug("Effect " + effectId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Effect e = (Effect) session.get(Effect.class, effectId);
            logger.info("Deletion of Effect " + e.getName() + " number " + e.getId());
            session.delete(e);
            transaction.commit();
        	logger.info("Effect " + effectId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Effect " + effectId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	}
}
