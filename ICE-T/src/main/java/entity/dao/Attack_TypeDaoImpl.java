package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.Attack_Type;
import entity.CharacterSheet;
import entity.Effect;
import entity.EntityEnum.E_Duration;

public class Attack_TypeDaoImpl implements Attack_TypeDao {

	private static final Logger logger = Logger.getLogger(CharacterSheetDaoImpl.class);
	
	public Attack_TypeDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public int saveAttackType(Attack_Type type) {
    	logger.debug("A new attack type attached to the attack " + type.getAttack().getAttackName() + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int attackTypeId = -1;
        try {
            transaction = session.beginTransaction();
            attackTypeId = (Integer) session.save(type);
            transaction.commit();
        	logger.info("A new attack type attached to the attack " + type.getAttack().getAttackName() + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving a new attack type attached to the attack " + type.getAttack().getAttackName() + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return attackTypeId;
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
