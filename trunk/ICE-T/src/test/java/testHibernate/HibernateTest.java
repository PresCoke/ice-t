package testHibernate;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;


public class HibernateTest {

	private static final Logger logger = Logger.getLogger(HibernateTest.class);
	
	public static void main(String[] args) throws HibernateException {
		CreatureDao cDao = new CreatureDaoImpl();
		cDao.readAllCreatures(); 
	}
 
}
