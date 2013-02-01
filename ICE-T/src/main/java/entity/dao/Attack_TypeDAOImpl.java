package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.Attack_Type;
import entity.CharacterSheet;

public class Attack_TypeDAOImpl implements Attack_TypeDAO {

	private static final Logger logger = Logger.getLogger(CharacterSheetDaoImpl.class);
	
	public Attack_TypeDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	public void deleteAttackType(int attackTypeId) {
		logger.debug("Attack Type " + attackTypeId + " is about to be deleted from the database.");
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Attack_Type at = (Attack_Type) session.get(Attack_Type.class, attackTypeId);
            logger.info("Deletion of Attack Type");
            session.delete(at);
            transaction.commit();
        	logger.info("Attack Type " + attackTypeId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Attack Type " + attackTypeId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
	
	}
}
