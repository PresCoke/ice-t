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
public class TeamForm implements FormBean, ActionListener {

	private static final int creatureTableDimension = 3;
	private Team theTeam;
	private JPanel teamForm_panel;
	private JPanel options_panel;
	private JCheckBox isNPC_checkbox;
	private JRadioButton displayCreature_radiobutton;
	private JRadioButton displayTraps_radiobutton;
	private JButton next_button, prev_button;
	private JButton add_button, sub_button;
	private JTable addableCreatures_table;
	private DefaultTableModel addableCreatures_model;
	private JList currentTeam_list;
	private DefaultListModel currentTeam_model;
	
	public TeamForm() {
		ResourceBundle team_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		teamForm_panel = new JPanel();
		
		addableCreatures_model = new DefaultTableModel(creatureTableDimension, creatureTableDimension) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		addableCreatures_model.setColumnCount(creatureTableDimension);
		addableCreatures_model.setRowCount(creatureTableDimension);
		getFirstCreatureTable(false);
		addableCreatures_table = new JTable(addableCreatures_model);
		addableCreatures_table.setRowHeight(80);
		addableCreatures_table.setPreferredSize( new Dimension(170*creatureTableDimension, 80*creatureTableDimension) );
		addableCreatures_table.setRowSelectionAllowed(false);
		addableCreatures_table.setColumnSelectionAllowed(false);
		addableCreatures_table.setCellSelectionEnabled(true);
		addableCreatures_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//addableCreatures_table.setDragEnabled(true);
		for (int index=0; index<addableCreatures_table.getColumnCount(); index++) {
			addableCreatures_table.getColumnModel().getColumn(index).setCellRenderer(new bean.combat.TeamRenderer());
			addableCreatures_table.getColumnModel().getColumn(index).setMinWidth(170);
		}
		
		//TODO: this is a really bad way to make these two buttons the same size
		next_button = new JButton("  "+team_l10n.getString("Next_team")+"  ");
		next_button.addActionListener(this);
		prev_button = new JButton(team_l10n.getString("Prev_team"));
		prev_button.addActionListener(this);
		
		add_button = new JButton(team_l10n.getString("Add_button"));
		add_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				for (int index = 0, x_index = 0, y_index = 0; index < creatureTableDimension*creatureTableDimension; index++) {
					if (addableCreatures_table.isCellSelected(y_index, x_index) ) {
						Object theValue = addableCreatures_table.getValueAt(y_index, x_index);
						currentTeam_model.addElement( theValue );
						if (theValue instanceof entity.Player) {
							theTeam.addPlayer((entity.Player) theValue);
						} else if (theValue instanceof entity.Monster) {
							theTeam.addMonster((entity.Monster) theValue);
						} else if (theValue instanceof entity.TrapHazard) {
							theTeam.addTrapHazard((entity.TrapHazard) theValue);
						}
						
					}
					if (x_index < creatureTableDimension) {
						x_index++;
					} else {
						y_index++;
						x_index = 0;
					}
				}
			}
		});
		
		sub_button = new JButton(team_l10n.getString("Remove_button"));
		sub_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				for (int index = 0; index < currentTeam_model.getSize(); index++) {
					if (currentTeam_list.isSelectedIndex(index) ) {
						Object theValue = currentTeam_model.remove(index);
						if (theValue instanceof entity.Player) {
							theTeam.removePlayer((entity.Player) theValue);
						} else if (theValue instanceof entity.Monster) {
							theTeam.removeMonster((entity.Monster) theValue);
						} else if (theValue instanceof entity.TrapHazard) {
							theTeam.removeTrapHazard((entity.TrapHazard) theValue);
						}
					}
				}
			}
		});
		
		isNPC_checkbox = new JCheckBox();
		displayTraps_radiobutton = new JRadioButton(team_l10n.getString("TrapDisplay_label"));
		displayCreature_radiobutton = new JRadioButton(team_l10n.getString("CreatureDisplay_label"));
		
		isNPC_checkbox.addActionListener(this);
		
		displayCreature_radiobutton.addActionListener(this);
		displayCreature_radiobutton.setSelected(true);
		
		displayTraps_radiobutton.addActionListener(this);
		displayTraps_radiobutton.setSelected(false);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(displayCreature_radiobutton);
		buttonGroup.add(displayTraps_radiobutton);
		
		options_panel = new JPanel();
		options_panel.setLayout( new BoxLayout(options_panel, BoxLayout.LINE_AXIS) );
		options_panel.setBorder( BorderFactory.createTitledBorder(team_l10n.getString("Options_title")) );
		options_panel.add( new JLabel(team_l10n.getString("NPCOnly_label")) );
		options_panel.add(isNPC_checkbox);
		options_panel.add(Box.createHorizontalGlue());
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
		for (index=0; index < theTeam.getTraphazards().size(); index++) {
			bean.combat.TrapBean theTrap = new bean.combat.TrapBean();
			theTrap.createPanelFrom(theTeam.getTrapHazardAt(index));
			currentTeam_model.addElement(theTrap);
		}
		
		currentTeam_list = new JList(currentTeam_model);
		currentTeam_list.setLayoutOrientation(JList.VERTICAL);
		currentTeam_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		currentTeam_list.setCellRenderer(new bean.combat.TeamRenderer());
		currentTeam_list.setPreferredSize(new Dimension(170, 0));
		currentTeam_list.setFixedCellWidth(170);
		currentTeam_list.setFixedCellHeight(80);
		//currentTeam_list.setDropMode(DropMode.ON);
		
		JScrollPane team_pane = new JScrollPane(currentTeam_list);
		team_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		team_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		JScrollPane creature_pane = new JScrollPane(addableCreatures_table);
		creature_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		creature_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//		JPanel creature_pane = new JPanel();
//		creature_pane.add(addableCreatures_table);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.PAGE_AXIS) );
		button_panel.add(next_button);
		button_panel.add(Box.createGlue());
		button_panel.add(prev_button);
		
		JPanel otherButton_panel = new JPanel();
		otherButton_panel.setLayout( new BoxLayout(otherButton_panel, BoxLayout.PAGE_AXIS) );
		otherButton_panel.add(add_button);
		otherButton_panel.add(Box.createGlue());
		otherButton_panel.add(sub_button);
		
//		teamForm_panel.setLayout( new BorderLayout() );
//		teamForm_panel.add(options_panel, BorderLayout.PAGE_START);
//		teamForm_panel.add(button_panel, BorderLayout.LINE_END);
//		teamForm_panel.add(creature_pane, BorderLayout.CENTER);
//		teamForm_panel.add(team_pane, BorderLayout.PAGE_END);
		
		GroupLayout teamForm_layout = new GroupLayout(teamForm_panel);
		teamForm_layout.setHorizontalGroup( teamForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(options_panel)
				.addGroup( teamForm_layout.createSequentialGroup()
						.addComponent(button_panel)
						.addComponent(creature_pane)
						.addComponent(otherButton_panel)
						.addComponent(team_pane))
				);
		teamForm_layout.setVerticalGroup( teamForm_layout.createSequentialGroup()
				.addComponent(options_panel)
				.addGroup( teamForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(button_panel)
						.addComponent(creature_pane)
						.addComponent(otherButton_panel)
						.addComponent(team_pane)
						)
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
										  "Team",
										  JOptionPane.WARNING_MESSAGE);
		}
		
		return isValidForm;
	}
	
	private void getFirstCreatureTable(boolean isNPC) {
		Object[][] theCreatures = TeamController.getFirstPage(false, false, creatureTableDimension);
		for (int index = 0, x_index=0, y_index=0; index < creatureTableDimension; index++) {
			addableCreatures_model.setValueAt(theCreatures[y_index][x_index], y_index, x_index);
			if (x_index < creatureTableDimension) {
				x_index++;
			} else {
				y_index++;
				x_index = 0;
			}
		}
	}

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		Object[][] newCreature = new Object[0][0];
		if (source == next_button || source == prev_button) {
			if (source == next_button) {
				newCreature = TeamController.getNextPage(
						isNPC_checkbox.isEnabled(),
						displayTraps_radiobutton.isEnabled(),
						creatureTableDimension);

			} else if (source == prev_button) {
				newCreature = TeamController.getPreviousPage(
						isNPC_checkbox.isEnabled(),
						displayTraps_radiobutton.isEnabled(),
						creatureTableDimension);
			}
			for (int index = 0, x_index=0, y_index=0; index < creatureTableDimension; index++) {
				addableCreatures_model.setValueAt(newCreature[y_index][x_index], y_index, x_index);
				if (x_index < creatureTableDimension) {
					x_index++;
				} else {
					y_index++;
					x_index = 0;
				}
			}
		} else if ( source == isNPC_checkbox) {
			boolean isNPCTeam = isNPC_checkbox.isSelected(); //get new state
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
			newCreature = TeamController.getFirstPage(isNPC_checkbox.isSelected(), displayTraps_radiobutton.isSelected(), creatureTableDimension);
			for (int index = 0, x_index=0, y_index=0; index < creatureTableDimension; index++) {
				addableCreatures_model.setValueAt(newCreature[y_index][x_index], y_index, x_index);
				if (x_index < creatureTableDimension) {
					x_index++;
				} else {
					y_index++;
					x_index = 0;
				}
			}
//			for (int index=0; index<addableCreatures_table.getColumnCount(); index++) {
//				addableCreatures_table.getColumnModel().getColumn(index).setCellRenderer(new bean.combat.CreatureBean());
//			}
		} else if (source == displayCreature_radiobutton) {
			newCreature = TeamController.getFirstPage(isNPC_checkbox.isSelected(), displayTraps_radiobutton.isSelected(), creatureTableDimension);
			for (int index = 0, x_index=0, y_index=0; index < creatureTableDimension; index++) {
				addableCreatures_model.setValueAt(newCreature[y_index][x_index], y_index, x_index);
				if (x_index < creatureTableDimension) {
					x_index++;
				} else {
					y_index++;
					x_index = 0;
				}
			}
//			for (int index=0; index<addableCreatures_table.getColumnCount(); index++) {
//				addableCreatures_table.getColumnModel().getColumn(index).setCellRenderer(new bean.combat.CreatureBean());
//			}
		} else if (source == displayTraps_radiobutton) {
			Object[][] newTraps = TeamController.getFirstPage(isNPC_checkbox.isSelected(), displayTraps_radiobutton.isSelected(), creatureTableDimension);
			for (int index = 0, x_index=0, y_index=0; index < creatureTableDimension; index++) {
				addableCreatures_model.setValueAt(newTraps[y_index][x_index], y_index, x_index);
				if (x_index < creatureTableDimension) {
					x_index++;
				} else {
					y_index++;
					x_index = 0;
				}
			}
//			for (int index=0; index<addableCreatures_table.getColumnCount(); index++) {
//				addableCreatures_table.getColumnModel().getColumn(index).setCellRenderer(new bean.combat.TrapBean());
//			}
		}

	}
}
