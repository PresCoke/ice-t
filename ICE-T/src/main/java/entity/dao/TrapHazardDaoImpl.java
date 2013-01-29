package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import resource.HibernateUtil;
import entity.CombatEncounter;
import entity.TrapHazard;

/**
 * DAO of a trap or a hazard
 * @author TimHP
 *
 */
public class TrapHazardDaoImpl implements TrapHazardDao {

	private static final Logger logger = Logger.getLogger(CreatureDaoImpl.class);
	
	public void readAllTrapHazards() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from TrapHazard");
		
		@SuppressWarnings("unchecked")
		List<TrapHazard> ths = q.list();
		
		for (TrapHazard th : ths) {
			logger.debug("Name = " + th.getName() + " - DifficultyLevel = " + th.getDifficultyLevel());
		}		
	}

}
