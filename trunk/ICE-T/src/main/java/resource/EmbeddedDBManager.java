package resource;

public class EmbeddedDBManager {

	
	public EmbeddedDBManager(String args)
	{
		/*
		 * Pre: application just started, machine specific database properties are passed as file URL
		 * Post: EmbeddedDBManager created with appropriate properties, BUT NOT STARTED OR CONNECTED!
		 */
	}
	
	public int start ()
	{
		/*
		 * Pre: Application has just started
		 * Post: Database started, connected and ready for transactions
		 */
		return -1;
	}
	
	public int close()
	{
		/*
		 * Pre: Application is closing
		 * Post: the Database is saved and the connection has been closed
		 */
		
		return -1;
	}
}
