package controller;

//-- Project Imports --//
import mediator.*;
import entity.*;
import bean.*;
import presentation.*;

// TODO FINISH
public class App_Root
{

	
	/**
	 * @param args
	 */
	
	public static Mediator resourceMediator;
	
	public static void main(String[] args)
	{
		Root_Window mainWindow = new Root_Window();
		mainWindow.start();
		
		resourceMediator = new Mediator("properties");
		resourceMediator.start();
		
	}
	
	
	public static void exit()
	{
		resourceMediator.close();
		
		System.exit(0);
	}

}
