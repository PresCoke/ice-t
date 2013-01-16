package resource;


//-- Class Imports --//
import org.hsqldb.persist.HsqlProperties;
import org.hsqldb.*;
import org.hsqldb.util.*;
import org.hsqldb.Server;

/**
 * EmbeddedDBManager
 * 
 * @author jamesbegg
 *
 */

public class EmbeddedDBManager {

	
	Server iceTServer;
	//Connection iceTDBConnection;
	
	HsqlProperties db_Properties;
	
	public EmbeddedDBManager(String dbName, String dbPath) {
		/*
		 * Pre: application just started, machine specific database properties are passed as file URL
		 * Post: EmbeddedDBManager created with appropriate properties, BUT NOT STARTED OR CONNECTED!
		 */
		/*
		iceTServer = new Server();
		
		// TODO setup logging to log4j!!
		
		iceTServer.setDatabaseName(0, dbName);
		iceTServer.setDatabasePath(0, dbPath);
		*/
		
		
		//db_Properties = machineProperties;
	}
	
	public int start () {
		/*
		 * Pre: Application has just started
		 * Post: Database started, connected and ready for transactions
		 */
		return -1;
	}
	
	
	public int close() {
		/*
		 * Pre: Application is closing
		 * Post: the Database is saved and the connection has been closed
		 */
		
		return -1;
	}
	
	private int connect() {
		/*
		 * Pre: this.start() has been called.
		 * Post: a HSQLDB connection is setup
		 */
		return -1;
	}
}
