package testHibernate;

import org.hibernate.HibernateException;

import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;


public class HibernateTest {
	
	public static void main(String[] args) throws HibernateException {
//		CreatureDao cDao = new CreatureDaoImpl();
//		cDao.readAllCreatures();
		
		CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
		ceDao.readAllCombatEncounters();
		
//		TrapHazardDao thDao = new TrapHazardDaoImpl();
//		thDao.readAllTrapHazards();
	}
 
}
