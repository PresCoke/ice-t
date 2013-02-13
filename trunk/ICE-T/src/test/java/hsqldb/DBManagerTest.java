package hsqldb;

import org.apache.log4j.Logger;

import resource.EmbeddedDBManager;
import testHibernate.HibernateTest;

public class DBManagerTest {

	private static final Logger logger = Logger.getLogger(HibernateTest.class);
	
	public static void main(String[] args) {
		EmbeddedDBManager manager = new EmbeddedDBManager();
		manager.start();
		manager.connect();
	}

}
