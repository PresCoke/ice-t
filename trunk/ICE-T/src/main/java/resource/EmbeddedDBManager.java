package resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.hsqldb.Server;


/**
 * EmbeddedDBManager
 * @author TimHP
 *
 */
public class EmbeddedDBManager {
	
	private static final Logger logger = Logger.getLogger(EmbeddedDBManager.class);
	
	//Server & Connection
	private Server icetServer;
	private Connection icetDBConnection;
	
	//SQL
	private File icetSQLStatements;
	private boolean wasCreated;

	/**
	 * Pre: application just started, machine specific database properties are passed as file URL
	 * Post: EmbeddedDBManager created with appropriate properties, BUT NOT STARTED OR CONNECTED!
	 */
	public EmbeddedDBManager(String dbPath) {
		//Setting the server
		icetSQLStatements = new File(dbPath+".script");
		icetServer = new Server();
		icetServer.setLogWriter(null);
		icetServer.setSilent(true);
		icetServer.setDatabaseName(0, "icetdb");
		
		//Check if the tables were created before
		if (!icetSQLStatements.exists()){
            logger.info("The database does not exist.");
			wasCreated = false;
		} else {
            logger.info("The database already exists.");
			wasCreated = true;
		}
		icetServer.setDatabasePath(0, "file:"+dbPath);
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
			        "jdbc:hsqldb:file:src/main/resources/database/icetproperties/icetdb", "SA", "");
            result = 1;
            if (!wasCreated){
                logger.info("Creation of the tables in the database.");
                File icetSQLScript = new File("src/main/resources/database/icetdbFinal.sql");
               
                //Batch creation
                logger.info("Batch Creation");
                String script = "";
                icetDBConnection.setAutoCommit(false);
                Statement sqlScript = icetDBConnection.createStatement
                		(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
               
                //Reading the sql script 
                logger.info("Reading the sql script");
                try {
                	//Opening the script to read
                	BufferedReader br = new BufferedReader(new FileReader(icetSQLScript)); 
                    String line = null;
                    while ((line = br.readLine()) != null) {
                		script += line;
					}	
                	//Closing the sql script
					br.close();
				} catch (FileNotFoundException e) {
		            logger.fatal("File " + icetSQLScript.getName() + " was not found --- " + e.getMessage());
				} catch (IOException e) {
		            logger.fatal("File " + icetSQLScript.getName() + " was not found --- " + e.getMessage());
				}
                
                //Adding the tables to the batch
                logger.info("Adding the tables to the batch");
                if (script != null && !script.isEmpty()){
	                String[] tables = script.split(";");
	                for(String t : tables){
	                    logger.info(t);
	                	sqlScript.addBatch(t);
	                }
                }else{
                	if (script == null){
                    	logger.error("The string script was null");
                	} else {
                		logger.error("The string script was empty");
                	}
                }
                
                //Batch execution
                logger.info("Batch is about to be executed.");
                sqlScript.executeBatch();    
                icetDBConnection.commit();
                logger.info("Batch executed.");
            }
		} catch (ClassNotFoundException e) {
            logger.fatal("Error while getting class org.hsqldb.jdbcDriver --- " + e.getMessage());
		} catch (SQLException e) {
            logger.fatal("Error while creating the database --- " + e.getMessage());
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
