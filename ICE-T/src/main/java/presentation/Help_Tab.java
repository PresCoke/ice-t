package presentation;

/**
 * Help Tab
 * @author jamesbegg
 *
 */

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import controller.Help;

public class Help_Tab implements ListSelectionListener {

	private JPanel help_panel;
	private JList helpTopic_list;
	private JEditorPane helpDisplay_pane;
	private Help controller_reference;
	
	public Help_Tab(Help help_controller) {
		// TODO Auto-generated constructor stub
		controller_reference = help_controller;
	}

	public Component getPanel() {
		/* Start Hack
		 * - this behaviour may be better implemented by inheriting from component
		 *   or creating a custom class with a super getPanel() method.
		 */
		
//		help_panel = new JPanel();
//		help_panel.add( new JTextField("HELP") );
		
		this.createPanel();
		
		return help_panel;
	}
	
	private void createPanel() {
		help_panel = new JPanel();
		help_panel.setLayout( new GridBagLayout() );
		help_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) );
		
		helpTopic_list = new JList( this.populateHelpTopicList() );
		helpTopic_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		helpTopic_list.setLayoutOrientation( JList.VERTICAL );
		helpTopic_list.addListSelectionListener(this);
		
		JScrollPane helpList_pane = new JScrollPane(helpTopic_list);
		helpList_pane.setBorder( BorderFactory.createEtchedBorder() );
		helpList_pane.setPreferredSize( new Dimension(150, 0) ); // TODO: find better way then to use absolute values
		helpList_pane.setMinimumSize( new Dimension(150, 0) );
		
		JPanel list_panel = new JPanel();
		list_panel.setLayout( new BoxLayout(list_panel, BoxLayout.LINE_AXIS) );
		list_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		list_panel.add(helpList_pane);
		
		helpDisplay_pane = new JEditorPane();
		helpDisplay_pane.setOpaque(false);
		helpDisplay_pane.setEditable(false);
		helpDisplay_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createLineBorder(Color.GRAY) )
				);
		
		GridBagConstraints list_constraints = new GridBagConstraints();
		list_constraints.fill = GridBagConstraints.BOTH;
		list_constraints.gridx = 0;
		list_constraints.gridy = 0;
		list_constraints.gridheight = 3;
		list_constraints.weightx = 0.5;
		list_constraints.weighty = 1.0;
		help_panel.add(list_panel, list_constraints);
		
		GridBagConstraints topic_constraints = new GridBagConstraints();
		topic_constraints.fill = GridBagConstraints.BOTH;
		topic_constraints.gridx = 1;
		topic_constraints.gridy = 0;
		topic_constraints.gridheight = 3;
		topic_constraints.gridwidth = 2;
		topic_constraints.weightx = 0.5;
		topic_constraints.weighty = 1.0;
		help_panel.add(helpDisplay_pane, topic_constraints);
		
		
	}

	public void valueChanged(ListSelectionEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getValueIsAdjusting() == false && this.helpTopic_list.getSelectedIndex() != -1) {
			this.getSelectedHelpTopic( (String) this.helpTopic_list.getSelectedValue() );
		}
		
	}
	
	private DefaultListModel populateHelpTopicList() {
		DefaultListModel tempHelp_list = new DefaultListModel();
		
		//TODO: implement properly
		tempHelp_list.addElement("How to not be a douche");
		tempHelp_list.addElement("How to add Creatures");
		tempHelp_list.addElement("How to play D&D");
		
		return tempHelp_list; 
	}
	
	private void getSelectedHelpTopic(String selectedTopic) {
		helpDisplay_pane.setText(selectedTopic);
		
		TitledBorder temp_border = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY) );
		temp_border.setTitle(selectedTopic);
		temp_border.setTitleJustification(TitledBorder.CENTER);
		
		helpDisplay_pane.setBorder(temp_border);
	}

}
