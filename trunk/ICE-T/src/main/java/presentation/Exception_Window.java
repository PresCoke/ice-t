package presentation;

/**
 * Exception Window
 * @author jamesbegg
 *
 */

import javax.swing.*;
import javax.swing.border.*;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;

import controller.App_Root;
import entity.dao.CreatureDaoImpl;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

public final class Exception_Window {
	
	private static final Logger logger = Logger.getLogger(Exception_Window.class);
	
	static Exception exc;
	static JButton moveOn, logEx, showTrace;
	static JLabel message;
	static JEditorPane stack_trace;
	
	public static void showException(Exception e) {
		
		ResourceBundle exception_bundle_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.ExceptionWindow", App_Root.language_locale);
		exc = e;
		
		final JDialog exceptionFrame = new JDialog();
		
		moveOn = new JButton(exception_bundle_l10n.getString("Continue_Button"));
		moveOn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				exceptionFrame.dispose();
			}
			
		});
		
		logEx = new JButton(exception_bundle_l10n.getString("Log_Button"));
		logEx.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				writeExceptionToLog(stack_trace.getText());
				exceptionFrame.dispose();
			}
			
		});
		
		showTrace = new JButton (exception_bundle_l10n.getString("Trace_Button"));
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
		
		JScrollPane messagePanel = new JScrollPane();
		messagePanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) );
		messagePanel.setLayout( new BoxLayout(messagePanel, BoxLayout.PAGE_AXIS) );
		message = new JLabel( exc.getMessage() );
		messagePanel.add(message);
		stack_trace = new JEditorPane();
		stack_trace.setText( exc.getStackTrace().toString() );
		stack_trace.setVisible(false);
		messagePanel.add(stack_trace);
		
		JPanel exceptionPanel = new JPanel();
		exceptionPanel.setBorder( BorderFactory.createEtchedBorder(EtchedBorder.RAISED) );
		exceptionPanel.setLayout( new BoxLayout(exceptionPanel, BoxLayout.PAGE_AXIS) );
		exceptionPanel.add( messagePanel );
		exceptionPanel.add( new JSeparator() );
		exceptionPanel.add( buttonPanel );
		
		JComponent content = exceptionPanel;
		
		exceptionFrame.setTitle(exception_bundle_l10n.getString("ExceptionWindow_Title"));
		exceptionFrame.setContentPane(content);
		exceptionFrame.setAlwaysOnTop(true);
		exceptionFrame.setResizable(false);
		exceptionFrame.pack();
		exceptionFrame.validate();
		exceptionFrame.setVisible(true);
		
	}
	
	private static void writeExceptionToLog(String exc) {
		logger.debug(exc);
	}
	
}
