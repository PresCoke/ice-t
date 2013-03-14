package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

import entity.*;
import controller.App_Root;
import controller.Combat;

public class AddTeam_Window implements ActionListener {
	
	private JFrame addTeam_window;
	private JPanel addTeam_panel;
	private List<Object[]> entity_names_and_IDs;
	private DefaultListModel addableTeam_model;
	private JList addableTeam_list;
	private JButton addTeam_button;
	private JButton cancel_button;
	private Combat controller_reference;
	private Combat_Tab parent_window;
	private int num_teams;
	
	public AddTeam_Window (Combat combat_controller, Combat_Tab parent) {
		controller_reference = combat_controller;
		parent_window = parent;
	}
	
	public void showFrame() {
		this.createPanel();
		addTeam_window = new JFrame();
		addTeam_window.setContentPane(addTeam_panel);
		addTeam_window.pack();
		addTeam_window.validate();
		if (num_teams == 0) {
			addTeam_window.dispose();
			return;
		}
		addTeam_window.setVisible(true);
	}
	
	private void createPanel() {
		ResourceBundle team_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.CombatTab", App_Root.language_locale);
		
		addTeam_panel = new JPanel();
		addTeam_button = new JButton(team_l10n.getString("AddTeam_Button"));
		addTeam_button.addActionListener(this);
		cancel_button = new JButton(team_l10n.getString("Cancel_button"));
		cancel_button.addActionListener(this);
		
		addableTeam_model = new DefaultListModel();
		entity_names_and_IDs = App_Root.resource_mediator.getUniqueEntityIDsForType(
				ResourceBundle.getBundle("filters.MainGUI_l10n.EntityTypeName", App_Root.language_locale)
				.getString("Team_entity") );
		num_teams = entity_names_and_IDs.size();
		for (int index = 0; index < entity_names_and_IDs.size(); index++) {
			addableTeam_model.addElement(entity_names_and_IDs.get(index)[1]);
		}
		addableTeam_list = new JList(addableTeam_model);
		addableTeam_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		addableTeam_list.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane addableTeam_pane = new JScrollPane(addableTeam_list);
		addableTeam_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		addableTeam_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		GroupLayout addTeam_layout = new GroupLayout(addTeam_panel);
		addTeam_layout.setHorizontalGroup( addTeam_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(addableTeam_pane)
				.addGroup( addTeam_layout.createSequentialGroup()
						.addComponent(addTeam_button)
						.addComponent(cancel_button))
				);
		addTeam_layout.setVerticalGroup( addTeam_layout.createSequentialGroup()
				.addComponent(addableTeam_pane)
				.addGroup( addTeam_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(addTeam_button)
						.addComponent(cancel_button))
				);
		addTeam_panel.setLayout(addTeam_layout);
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		if (source == cancel_button) {
			addTeam_window.setVisible(false);
			addTeam_window.dispose();
		} else if (source == addTeam_button) {
			List<Team> addingTheseTeams = new ArrayList<Team>();
			String entity_type_name = ResourceBundle.getBundle("filters.MainGUI_l10n.EntityTypeName", App_Root.language_locale).getString("Team_entity");
			int[] selected_indices = addableTeam_list.getSelectedIndices();
			if (selected_indices.length == 0) {
				//TODO: something to handle that eventuality;
				return;
			}
			for (int index = 0; index < selected_indices.length; index++) {
				int id = (Integer) entity_names_and_IDs.get(selected_indices[index])[0];
				Team addingThis =  (Team) App_Root.resource_mediator.getEntityOfID(id, entity_type_name);
				addingThis.resetCreatureStats();
				addingTheseTeams.add(addingThis);
			}
			controller_reference.addTheseTeamsToCE(addingTheseTeams);
			parent_window.updateTeamModel();
			
			addTeam_window.setVisible(false);
			addTeam_window.dispose();
		}
	}
}
