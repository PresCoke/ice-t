package bean.forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controller.App_Root;
import controller.TeamController;

import entity.Team;

/* 
 * BIG FUCKING TODO :: apparently the dao will only save player teams and NPC teams which can also have traps/hazards???
 */
public class TeamForm implements FormBean, ActionListener, ChangeListener, ItemListener {

	private static final int creatureTableDimension = 5;
	private Team theTeam;
	private JPanel teamForm_panel;
	private JPanel options_panel;
	private JCheckBox isNPC_checkbox;
	private JRadioButton displayCreature_radiobutton;
	private JRadioButton displayTraps_radiobutton;
	private JButton next_button, prev_button;
	private JTable addableCreatures_table;
	private DefaultTableModel addableCreatures_model;
	private JList currentTeam_list;
	private DefaultListModel currentTeam_model;
	
	public TeamForm() {
		ResourceBundle team_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		teamForm_panel = new JPanel();
		
		addableCreatures_model = new DefaultTableModel(5, 5);
		getFirstCreatureTable(false);
		addableCreatures_table = new JTable(addableCreatures_model);
		addableCreatures_table.setRowSelectionAllowed(false);
		addableCreatures_table.setColumnSelectionAllowed(false);
		addableCreatures_table.setCellSelectionEnabled(true);
		addableCreatures_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		//TODO: this is a really bad way to make these two buttons the same size
		next_button = new JButton("  "+team_l10n.getString("Next_team")+"  ");
		next_button.addActionListener(this);
		prev_button = new JButton(team_l10n.getString("Prev_team"));
		prev_button.addActionListener(this);
		
		isNPC_checkbox = new JCheckBox();
		isNPC_checkbox.addChangeListener(this);
		isNPC_checkbox.setEnabled(false);
		
		displayCreature_radiobutton = new JRadioButton(team_l10n.getString("CreatureDisplay_label"));
		displayCreature_radiobutton.addItemListener(this);
		displayCreature_radiobutton.setSelected(true);
		displayTraps_radiobutton = new JRadioButton(team_l10n.getString("TrapDisplay_label"));
		displayTraps_radiobutton.addItemListener(this);
		displayTraps_radiobutton.setSelected(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(displayCreature_radiobutton);
		buttonGroup.add(displayTraps_radiobutton);
		
		options_panel = new JPanel();
		options_panel.setBorder( BorderFactory.createTitledBorder(team_l10n.getString("Options_title")) );
		options_panel.add(isNPC_checkbox);
		options_panel.add(displayCreature_radiobutton);
		options_panel.add(displayTraps_radiobutton);
	}

	public JPanel createEntityPanel() {
		theTeam = new Team();
		
		createPanel();
		
		return teamForm_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof Team) {
			theTeam = (Team) usingThis;
		} else {
			theTeam = new Team();
		}
		createPanel();
		return teamForm_panel;
	}

	private void createPanel() {
		currentTeam_model = new DefaultListModel();
		int index = 0;
		for (index = 0; index < theTeam.getNumberOfMonsters(); index++) {
			bean.combat.CreatureBean theMonster = new bean.combat.CreatureBean(); 
			theMonster.createPanelFrom(theTeam.getMonsterAt(index));
			currentTeam_model.addElement(theMonster);
		}
		for (index = 0; index < theTeam.getNumberOfPlayers(); index++) {
			bean.combat.CreatureBean thePlayer = new bean.combat.CreatureBean();
			thePlayer.createPanelFrom(theTeam.getPlayerAt(index));
			currentTeam_model.addElement(thePlayer);
		}
		
		currentTeam_list = new JList(currentTeam_model);
		currentTeam_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		currentTeam_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		currentTeam_list.setCellRenderer(new bean.combat.CreatureBean());
		
		JScrollPane team_pane = new JScrollPane(currentTeam_list);
		team_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		team_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		JScrollPane creature_pane = new JScrollPane(addableCreatures_table);
		creature_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		creature_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.PAGE_AXIS) );
		button_panel.add(next_button);
		button_panel.add(Box.createGlue());
		button_panel.add(prev_button);
		
		GroupLayout teamForm_layout = new GroupLayout(teamForm_panel);
		teamForm_layout.setHorizontalGroup( teamForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(options_panel)
				.addGroup( teamForm_layout.createSequentialGroup()
						.addComponent(creature_pane)
						.addComponent(button_panel))
				.addComponent(team_pane)
				);
		teamForm_layout.setVerticalGroup( teamForm_layout.createSequentialGroup()
				.addComponent(options_panel)
				.addGroup( teamForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(creature_pane)
						.addComponent(button_panel))
				.addComponent(team_pane)
				);
		teamForm_layout.setAutoCreateGaps(true);
		//teamForm_layout.linkSize(SwingConstants.HORIZONTAL, next_button, prev_button);
		teamForm_panel.setLayout(teamForm_layout);
		
	}
	
	public Object getEntity() {
		return theTeam;
	}

	public boolean validateEntity() {
		boolean isValidForm = true;
		String invalidFieldString = "";
		
		if ( (theTeam.getNumberOfMonsters() + theTeam.getNumberOfPlayers() ) == 0) {
			isValidForm = false;
			invalidFieldString += "You must add at least one creature before saving.\n";
		}
		
		if (!isValidForm) {
			JOptionPane.showMessageDialog(teamForm_panel,
										  invalidFieldString,
										  "Character Sheet",
										  JOptionPane.WARNING_MESSAGE);
		}
		
		return isValidForm;
	}
	
	private void getFirstCreatureTable(boolean isNPC) {
		Object[][] theCreatures = TeamController.getFirstPage(false, false, creatureTableDimension);
		//Object[][] theCreatures = new Object[5][5];
		int index = 0;
		int maxRows = creatureTableDimension;
		for ( ; index < maxRows; index++) {
			addableCreatures_model.addRow(theCreatures[index]);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		Object[][] newCreature = new Object[0][0];
		if (source == next_button) {
			newCreature = TeamController.getNextPage(isNPC_checkbox.isEnabled(), displayTraps_radiobutton.isEnabled(), creatureTableDimension);

		} else if (source == prev_button) {
			newCreature = TeamController.getPreviousPage(isNPC_checkbox.isEnabled(), displayTraps_radiobutton.isEnabled(), creatureTableDimension);
		}
		int index = 0;
		int maxRows = addableCreatures_model.getRowCount();
		for (; index < maxRows; index++) {
			addableCreatures_model.removeRow(index);
			addableCreatures_model.insertRow(index, newCreature[index]);
		}

	}

	public void stateChanged(ChangeEvent ce) {
		Object source = ce.getSource();
		Object[][] newCreature = new Object[0][0];
		if ( source == isNPC_checkbox) {
			boolean isNPCTeam = isNPC_checkbox.isEnabled(); //get new state
			if (isNPCTeam && theTeam.getNumberOfPlayers() > 0) { // user wants a monster team instead
				int x = JOptionPane.showConfirmDialog(teamForm_panel,
						  "All players currently in the team will be removed.\n Do you wish to proceed.",//TODO: make french
						  "Team Form",
						  JOptionPane.WARNING_MESSAGE,
						  JOptionPane.OK_CANCEL_OPTION);
				if (x == JOptionPane.CANCEL_OPTION) {
					return;
				}
				theTeam.removeAllPlayers();
			} else if (isNPCTeam && theTeam.getNumberOfMonsters() > 0) { // user wants a player team instead
				int x = JOptionPane.showConfirmDialog(teamForm_panel,
						  "All monsters currently in the team will be removed.\n Do you wish to proceed.",//TODO: make french
						  "Team Form",
						  JOptionPane.WARNING_MESSAGE,
						  JOptionPane.OK_CANCEL_OPTION);
				if (x == JOptionPane.CANCEL_OPTION) {
					return;
				}
				theTeam.removeAllMonsters();
			}
			newCreature = TeamController.getFirstPage(isNPC_checkbox.isEnabled(), displayTraps_radiobutton.isEnabled(), creatureTableDimension);
			int index = 0;
			int maxRows = addableCreatures_model.getRowCount();
			for (; index < maxRows; index++) {
				addableCreatures_model.removeRow(index);
				addableCreatures_model.insertRow(index, newCreature[index]);
			}
		}
		
	}

	public void itemStateChanged(ItemEvent ie) {
		Object source = ie.getSource();
		if (source == displayCreature_radiobutton) {
			Object[][] newCreature = TeamController.getFirstPage(isNPC_checkbox.isEnabled(), displayTraps_radiobutton.isEnabled(), creatureTableDimension);
			int index = 0;
			int maxRows = addableCreatures_model.getRowCount();
			for (; index < maxRows; index++) {
				addableCreatures_model.removeRow(index);
				addableCreatures_model.insertRow(index, newCreature[index]);
			}
		} else if (source == displayTraps_radiobutton) {
			Object[][] newTraps = TeamController.getFirstPage(isNPC_checkbox.isEnabled(), displayTraps_radiobutton.isEnabled(), creatureTableDimension);
			int index = 0;
			int maxRows = addableCreatures_model.getRowCount();
			for (; index < maxRows; index++) {
				addableCreatures_model.removeRow(index);
				addableCreatures_model.insertRow(index, newTraps[index]);
			}
		}
		
	}

}
