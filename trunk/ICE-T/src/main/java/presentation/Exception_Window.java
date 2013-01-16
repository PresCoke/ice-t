package presentation;

/**
 * Exception Window
 * @author jamesbegg
 *
 */

import javax.swing.*;
import javax.swing.border.*;

import org.apache.commons.logging.impl.Log4JLogger;

import java.awt.*;
import java.awt.event.*;

public final class Exception_Window {
	
	static Exception exc;
	static JButton moveOn, logEx, showTrace;
	static JLabel message, stack_trace;
	
	public static void showException(Exception e) {
		
		exc = e;
		
		final JDialog exceptionFrame = new JDialog();
		
		moveOn = new JButton("Continue");
		moveOn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				exceptionFrame.dispose();
			}
			
		});
		
		logEx = new JButton("Log Exception");
		logEx.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				writeExceptionToLog(exc);
				exceptionFrame.dispose();
			}
			
		});
		
		showTrace = new JButton ("Show Stack Trace");
		showTrace.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//set visibility to opposite
				stack_trace.setVisible( !(stack_trace.isVisible()) );				
			}
			
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new FlowLayout() );
		buttonPanel.add(moveOn);
		buttonPanel.add(logEx);
		buttonPanel.add(showTrace);
		//buttonPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		JPanel messagePanel = new JPanel();
		messagePanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
		messagePanel.setLayout( new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS) );
		message = new JLabel( exc.getMessage() );
		messagePanel.add(message);
		stack_trace = new JLabel( exc.getStackTrace().toString() );
		stack_trace.setVisible(false);
		messagePanel.add(stack_trace);
		
		JPanel exceptionPanel = new JPanel();
		exceptionPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
		exceptionPanel.setLayout( new BoxLayout(exceptionPanel, BoxLayout.PAGE_AXIS) );
		exceptionPanel.add( messagePanel );
		exceptionPanel.add( new JSeparator() );
		exceptionPanel.add( buttonPanel );
		
		JComponent content = exceptionPanel;
		
		exceptionFrame.setTitle("An Error Has Occurred");
		exceptionFrame.setContentPane(content);
		exceptionFrame.setAlwaysOnTop(true);
		exceptionFrame.setResizable(false);
		exceptionFrame.pack();
		exceptionFrame.validate();
		exceptionFrame.setVisible(true);
		
	}
	
	private static void writeExceptionToLog(Exception e) {
		// TODO: implement this to write to log4j.xml
	}
	
}
