package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.Tally;
import entity.Tuple;

public class TupleDaoImpl implements TupleDao {

	private static final Logger logger = Logger.getLogger(TupleDaoImpl.class);
	
	public TupleDaoImpl() {
	}

	public int saveTuple(String name, int value1, int value2, int tallyId) {
    	logger.debug("Tuple is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int tupleID = -1;
        try {
            transaction = session.beginTransaction();
            Tuple t = new Tuple(name, value1, value2);
            logger.debug("Association with a tally");
            Tally tally = (Tally) session.load(Tally.class, tallyId);
            logger.debug("Tally name = " + tally.getName());
        	t.setTally(tally);
        	tupleID = (Integer) session.save(t);
            transaction.commit();
        	logger.info("Tuple was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Tuple in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return tupleID;
	}

}
