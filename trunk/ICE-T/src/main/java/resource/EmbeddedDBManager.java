package resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import java.io.File;
import org.hsqldb.Server;


/**
 * EmbeddedDBManager
 * @author TimHP
 *
 */
public class EmbeddedDBManager {
	
	private static final Logger logger = Logger.getLogger(EmbeddedDBManager.class);
	
	private Server icetServer;
	private Connection icetDBConnection;
	private File icetSQLStatements;
	
	// TODO setup logging to log4j!!
	
	/**
	 * Pre: application just started, machine specific database properties are passed as file URL
	 * Post: EmbeddedDBManager created with appropriate properties, BUT NOT STARTED OR CONNECTED!
	 */
	public EmbeddedDBManager() {
		//Setting the server
		icetServer = new Server();
		icetServer.setDatabaseName(0, "icetdb");
		icetServer.setDatabasePath(0, "file:/src/main/resources/database/icetproperties/icetdb");
	}
	
	
	/**
	 * Pre: Application has just started
	 * Post: Server started
	 */
	public int start () { 
		icetServer.start();		
		return 1;
	}
	
	
	/**
	 * Pre: this.start() has been called.
	 * Post: a HSQLDB connection is setup
	 * @return  -1 if starting connection failed and +1 if it succeeded
	 */
	public int connect() {
		int result = -1; 
        try {
			Class.forName("org.hsqldb.jdbcDriver");
			icetDBConnection = DriverManager.getConnection(  
			        "jdbc:hsqldb:hsql://localhost:9001/icetdb;ifexists=true", "SA", "");
            result = 1;
		} catch (ClassNotFoundException e) {
            logger.fatal("Error while getting class org.hsqldb.jdbcDriver --- " + e.getMessage());
		} catch (SQLException e) {
            logger.info("The database does not exist; let's create it.");
		}  
		return result;
	}
	
	
	/**
	 * Pre: Application is closing
	 * Post: the Database is saved and the connection has been closed
	 * @return  -1 if closing server or connection failed and +1 if it succeeded
	 */
	public int close() {
		int result = -1; 
		// Closing the connection
        try{
			if (icetDBConnection != null) {
				icetDBConnection.close();
			} else {
	            logger.error("The DB connection could not be closed because it is null.");
	            return result;
			}
        }catch(SQLException e){
            logger.fatal("Error while closing DB connection --- " + e.getMessage());
        }
        // Closing the server
        if (icetServer != null) {
        	icetServer.stop();
        	result = 1;
        } else {
            logger.error("The server could not be stopped because it is null.");
            return result;
        }
		return result;
	}
}
