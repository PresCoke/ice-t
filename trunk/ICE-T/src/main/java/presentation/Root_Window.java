package presentation;

//-- Project Imports --//
import controller.*;
import bean.*;
//-- Class Imports --//
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Root_Window {
	
	JFrame main_window;
	
	public Root_Window()
	{
		main_window = new JFrame();
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public int start ()
	{
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

			public void windowClosed(WindowEvent arg0)
			{
				
				App_Root.exit();
				
			}

			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
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
		
		
		//BEGIN HACK: FOR ILLUSTRATIVE PUPROSES ONLY
		JPanel panel1 = new JPanel();
		panel1.setAlignmentY(10);
		panel1.setLayout(new FlowLayout());
		panel1.add( new JLabel ("STUFF GOES HERE") );
		
		JComponent content = panel1;
		//END HACK: FOR ILLUSTRATIVE PUPROSES ONLY
		
		
		main_window.setTitle("Interactive Combat Encounter Tool");
		main_window.setContentPane(content);
		main_window.setAlwaysOnTop(true);
		main_window.setResizable(false);
		main_window.pack();
		main_window.validate();
		main_window.setVisible(true);
		
		return -1;
	}
}
