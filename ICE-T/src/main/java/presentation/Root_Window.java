package presentation;

//-- Project Imports --//
import controller.*;
import bean.combat.*;
//-- Class Imports --//
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


/**
 * Root_Window
 * 
 * @author jamesbegg
 *
 */

public class Root_Window {
	
	JFrame main_window; // main application window
	
	Combat_Tab combat_tab; // holds all GUI related functions for the Combat tab
	Edit_Tab editEntity_tab; // holds all GUI related functions for the Edit tab
	Help_Tab help_tab; // holds all GUI related functions for the Help tab
	New_Tab newEntity_tab; // holds all GUI related functions for the New tab
	Welcome_Tab welcome_tab; // holds all GUI related functions for the Welcome tab
	
	public Root_Window() {
		main_window = new JFrame();
		
		welcome_tab = new Welcome_Tab(App_Root.welcome_controller); 
		newEntity_tab = new New_Tab(App_Root.newEntity_controller); 
		editEntity_tab = new Edit_Tab(App_Root.editEntity_controller); 
		combat_tab = new Combat_Tab(App_Root.combat_controller); 
		help_tab = new Help_Tab(App_Root.help_controller);
		
	}
	
	public int start () {
		/*
		 * Pre: Application has just started
		 * Post: Window Created and ready to accept 
		 */
		
		/*
		 * The following section of code will need to be implemented more fully
		 * currently all that has been implemented is when the window is closed the application closes.
		 */
		main_window.addWindowListener(new WindowListener(){

			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowClosed(WindowEvent arg0) {
				
				
				
			}

			public void windowClosing(WindowEvent arg0) {
				main_window.setVisible(false);
				App_Root.exit();
			}

			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			} 
			
		});
		
		
		/*
		 *  Creation and addition of tabs
		 */
		JTabbedPane major_tabs = new JTabbedPane();
		//This image icon is a hack... should be updated with custom icons and should double check for null values
		ImageIcon test_icon = new ImageIcon ("src/main/resources/new_hat.jpg");
		//TODO: create better icons
		major_tabs.addTab("Home", test_icon, welcome_tab.getPanel());
		major_tabs.addTab("New", test_icon, newEntity_tab.getPanel());
		major_tabs.addTab("Edit", test_icon, editEntity_tab.getPanel());
		major_tabs.addTab("Combat", test_icon, combat_tab.getPanel());
		major_tabs.addTab("Help", test_icon, help_tab.getPanel());
		
		JComponent content = major_tabs;
		
		main_window.setTitle("Interactive Combat Encounter Tool");
		//main_window.setDefaultLookAndFeelDecorated(true);
		main_window.setContentPane(content);
	
		main_window.setResizable(false);  //should be a property
	
		main_window.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize() ); // sets window size to the screen resolution
		main_window.pack();
		main_window.validate();
		main_window.setVisible(true);
		
		return 1;// I'm not sure what this value means
	}
}
