package presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Combat;

public class GRE_Window implements ActionListener {
	
	private Combat controller_reference;
	private Combat_Tab parent_window;
	private JPanel GRE_panel;
	private JFrame GRE_window;
	
	public GRE_Window (Combat controller, Combat_Tab parent) {
		controller_reference = controller;
		parent_window = parent;
	}
	
	public void showFrame() {
		this.createPanel();
		
		GRE_window = new JFrame();
		GRE_window.setContentPane(GRE_panel);
		GRE_window.pack();
		GRE_window.validate();
		GRE_window.setVisible(true);
	}
	
	private void createPanel() {
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
	}

}
