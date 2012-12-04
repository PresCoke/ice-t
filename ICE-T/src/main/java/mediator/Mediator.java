package mediator;

//-- Project Imports --//
import resource.*;

public class Mediator {
	
	String propertiesURL;
	EmbeddedDBManager dbMgr;
	
	public Mediator (String args)
	{
		/*
		 * Pre: Application started, machine/user specific properties are passed as file URL
		 * Post: Mediator created, but does not create or start anything else
		 */
	}
	
	public int start ()
	{
		/*
		 * Pre: Application has just started
		 * Post: Resource and Entity packages are initialized and ready to be used.
		 */
		dbMgr = new EmbeddedDBManager(propertiesURL);
		dbMgr.start();
		
		return -1;
	}
	
	public int close ()
	{
		/*
		 * Pre: the application is closing
		 * Post:the mediator has closed all open resources and all entities have been saved in their current state.
		 */
		
		dbMgr.close();
		return -1;
	
	}
}
