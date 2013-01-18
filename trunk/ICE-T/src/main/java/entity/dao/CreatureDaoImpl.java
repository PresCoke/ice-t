package entity.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.Creature;
import entity.HibernateUtil;

public class CreatureDaoImpl implements CreatureDao {

	public void readAllCreatures() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Query q = session.createQuery("from Creature");
		
		@SuppressWarnings("unchecked")
		List<Creature> creatures = q.list();
		
		for (Creature c : creatures) {
			System.out.println("Name = " + c.getName() + " - HP = " + c.getCurrentHP());
		}
	}


}
