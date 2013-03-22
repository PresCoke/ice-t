package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

import controller.App_Root;
import controller.Combat;
import entity.*;
import bean.combat.*;

//TODO: add delete key listener, if the creature list has focus and a cell or more then one cell is selected then remove it.
public class Combat_Tab implements ActionListener, ListSelectionListener {

	private JPanel combat_panel;
	private JButton autoRoll_button, organizeInitiative_button, finishTurn_button,
	 /*The buttons below might work better as menus that expand up and allow drag and dropping of commonly use entities (maybe a search bar like safari)*/
					addTeam_button, addEffect_button, GRE_button;
	private DefaultListModel creature_model;
	private JList creature_list;	
	private JEditorPane storyNotes_pane;
	private JPanel currentCreature_pane, selectedCreature_pane;
	private JTable gmTally_table;
	private DefaultTableModel gmTally_model;
	private Combat controller_reference;
	private JButton addTuple_button, removeTuple_button;
	
	public Combat_Tab(Combat combat_controller) {
		ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
		controller_reference = combat_controller;
		
		combat_panel = new JPanel();
		combat_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) );
		
		autoRoll_button = new JButton(combatTab_l10n.getString("AutoRoll_Button"));
		autoRoll_button.addActionListener(this);
		organizeInitiative_button = new JButton(combatTab_l10n.getString("OrganizeInitiative_Button"));
		organizeInitiative_button.addActionListener(this);
		finishTurn_button = new JButton(combatTab_l10n.getString("FinishTurn_Button"));
		finishTurn_button.addActionListener(this);
		
		addTeam_button = new JButton(combatTab_l10n.getString("AddTeam_Button"));
		addTeam_button.addActionListener(this);
		addEffect_button = new JButton(combatTab_l10n.getString("AddEffect_Button"));
		addEffect_button.addActionListener(this);
		
		addTuple_button = new JButton(combatTab_l10n.getString("AddTuple_Button"));
		addTuple_button.addActionListener(this);
		removeTuple_button = new JButton(combatTab_l10n.getString("RemoveTuple_Button"));
		removeTuple_button.addActionListener(this);
		
		GRE_button = new JButton(combatTab_l10n.getString("GRE_Button"));
		GRE_button.addActionListener(this);
				
		storyNotes_pane = new JEditorPane();
		storyNotes_pane.setBorder( BorderFactory.createEtchedBorder() );
		
//		currentCreature_pane = new JScrollPane();
//		currentCreature_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		currentCreature_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		currentCreature_pane = new JPanel();
		currentCreature_pane.setOpaque(false);
		currentCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 0),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		currentCreature_pane.setLayout( new BorderLayout() );
		
//		selectedCreature_pane = new JScrollPane();
//		selectedCreature_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		selectedCreature_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		selectedCreature_pane = new JPanel();
		selectedCreature_pane.setOpaque(false);
		selectedCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 5),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		selectedCreature_pane.setLayout( new BorderLayout() );
		
	}

	public Component getAndCreatePanel() {
		this.createPanel();
		
		return combat_panel;	
	}
	
	public Component getPanel() {
		return combat_panel;
	}
	
//	private void initializePanel() {
//		CombatEncounter theEncounter = controller_reference.getCombatEncounter();
//		
//		storyNotes_pane.setText(theEncounter.getNotes());
//		storyNotes_pane.addKeyListener( new KeyListener() {
//			public void keyPressed(KeyEvent ke) {
//				
//			}
//			public void keyReleased(KeyEvent ke) {
//				
//			}
//			public void keyTyped(KeyEvent ke) {
//				
//				controller_reference.setStoryNotes( ((JEditorPane) ke.getSource()).getText() );
//			}
//		});
//		
//		
//		creature_model = new DefaultListModel();
////		List<Object> creatures = theEncounter.getCreaturesInCe();
//		List<Team> creatures = theEncounter.getTeams();
//		for (int index = 0; index < creatures.size(); index++) {
////			creature_model.addElement(creatures.get(index));
//			List<Player> players = creatures.get(index).getPlayers();
//			//TODO: assign team a colour!
//			if (players != null && !players.isEmpty()) {
//				for (int p_index = 0; p_index < players.size(); p_index++) {
//					CreatureBeanShallow aBean = new CreatureBeanShallow();
//					aBean.createPanelFrom(players.get(p_index));
//					creature_model.addElement(aBean);
//				}
//				continue;
//			} else {
//				List<TrapHazard> traps = creatures.get(index).getTraphazards();
//				List<Monster> monsters = creatures.get(index).getMonsters();
//				for (int m_index = 0; m_index < monsters.size(); m_index++) {
//					CreatureBeanShallow aBean = new CreatureBeanShallow();
//					aBean.createPanelFrom(monsters.get(m_index));
//					creature_model.addElement(aBean);
//				}
//				for (int t_index = 0; t_index < traps.size(); t_index++) {
//					creature_model.addElement(traps.get(t_index));
//				}
//			}
//		}
//		creature_list = new JList(creature_model);
//		creature_list.addListSelectionListener(this);
//		creature_list.setLayoutOrientation(JList.VERTICAL);
//		creature_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		//TODO change renderer
//		creature_list.setCellRenderer(new CreatureBeanShallow());
//		creature_list.setPreferredSize(new Dimension(170, 0));
//		creature_list.setFixedCellWidth(140);
//		creature_list.setFixedCellHeight(100);
//		
//		creature_list.addKeyListener( new KeyListener() {
//			public void keyPressed(KeyEvent ke) {
//				
//			}
//			public void keyReleased(KeyEvent ke) {
//				
//			}
//			public void keyTyped(KeyEvent ke) {
//				char key = ke.getKeyChar();
//				if (key == '\b'/*|| delete key for those computers which have it...*/) {
//					int[] selected_indices = creature_list.getSelectedIndices(); 
//					if (selected_indices.length < 0) {
//						return;
//					}
//					List<Object> selected_values = new ArrayList<Object>();
//					for (int index = 0; index < selected_indices.length; index++) {
//						selected_values.add(creature_model.get(selected_indices[index]));
//					}
//					List<Object> removed_creatures = new ArrayList<Object>();
//					for (int index = 0; index<selected_values.size(); index++) {
//						int value_index = creature_model.indexOf(selected_values.get(index));
//						removed_creatures.add( ((CreatureBeanShallow) creature_model.get(value_index)).getEntity());
//						//below may cause problems...
//						creature_model.remove(value_index);
//					}
//					controller_reference.removeTheseCreatures(removed_creatures);
//				}
//			}
//		});
//		
//	}
	
	private void createPanel() {
		ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
		
		CombatEncounter theEncounter = controller_reference.getCombatEncounter();
		
		storyNotes_pane.setText(theEncounter.getNotes());
		storyNotes_pane.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				
				controller_reference.setStoryNotes( ((JEditorPane) ke.getSource()).getText() );
			}
		});
		
		
		creature_model = new DefaultListModel();
		creature_model = controller_reference.updateCreaturesInCE(creature_model);
		int currentId = controller_reference.getCurrentCreatureId();
		if (currentId >= 0) {
			((CreatureBeanShallow) creature_model.get(currentId)).setIsCurrentCreature(true);
			Object theCreature = ((CreatureBeanShallow) creature_model.get(controller_reference.getCurrentCreatureId())).getEntity();
			CreatureCombatDetailed theBean = new CreatureCombatDetailed();
			theBean.createPanelFrom(theCreature);
			currentCreature_pane.removeAll();
			currentCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
		}
		
		/*List<Object> creatures = theEncounter.getCreaturesInCe();
		for (int index = 0; index < creatures.size(); index++) {
			List<Player> players = creatures.get(index).getPlayers();
			//TODO: assign team a colour!
			if (players != null && !players.isEmpty()) {
				for (int p_index = 0; p_index < players.size(); p_index++) {
					CreatureBeanShallow aBean = new CreatureBeanShallow();
					aBean.createPanelFrom(players.get(p_index));
					creature_model.addElement(aBean);
				}
				continue;
			} else {
				List<TrapHazard> traps = creatures.get(index).getTraphazards();
				List<Monster> monsters = creatures.get(index).getMonsters();
				for (int m_index = 0; m_index < monsters.size(); m_index++) {
					CreatureBeanShallow aBean = new CreatureBeanShallow();
					aBean.createPanelFrom(monsters.get(m_index));
					creature_model.addElement(aBean);
				}
				for (int t_index = 0; t_index < traps.size(); t_index++) {
					creature_model.addElement(traps.get(t_index));
				}
			}
		}*/
		creature_list = new JList(creature_model);
		creature_list.addListSelectionListener(this);
		creature_list.setLayoutOrientation(JList.VERTICAL);
		creature_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//TODO change renderer
		creature_list.setCellRenderer(new CreatureBeanShallow());
		//creature_list.setPreferredSize(new Dimension(170, 0));
		creature_list.setFixedCellWidth(140);
		creature_list.setFixedCellHeight(100);
		
		creature_list.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == '\b'/*|| delete key for those computers which have it...*/) {
					int[] selected_indices = creature_list.getSelectedIndices(); 
					if (selected_indices.length < 0) {
						return;
					}
					List<Object> selected_values = new ArrayList<Object>();
					for (int index = 0; index < selected_indices.length; index++) {
						selected_values.add(creature_model.get(selected_indices[index]));
					}
					List<Object> removed_creatures = new ArrayList<Object>();
					for (int index = 0; index<selected_values.size(); index++) {
						int value_index = creature_model.indexOf(selected_values.get(index));
						removed_creatures.add( ((CreatureBeanShallow) creature_model.get(value_index)).getEntity());
						//below may cause problems...
						creature_model.remove(value_index);
					}
					controller_reference.removeTheseCreatures(removed_creatures);
				}
			}
		});
		
		// creature organization, and effects
		JPanel initiative_panel = new JPanel();
		initiative_panel.setLayout( new GridBagLayout() );
		initiative_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.LINE_AXIS) );
		button_panel.add(finishTurn_button);
		button_panel.add(autoRoll_button);
		button_panel.add(organizeInitiative_button);
		button_panel.add( Box.createHorizontalGlue() );
		button_panel.add(GRE_button);
		button_panel.add(addTeam_button);
		button_panel.add(addEffect_button);
		
		JScrollPane creature_pane = new JScrollPane(creature_list);
		creature_pane.setBorder( BorderFactory.createEtchedBorder() );
		int screen_width = (int) Math.round( Toolkit.getDefaultToolkit().getScreenSize().getWidth() );
		int screen_height = (int) Math.round( Toolkit.getDefaultToolkit().getScreenSize().getHeight() );
		creature_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		creature_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		creature_pane.setPreferredSize( new Dimension( 3*screen_width/20, 0 ) );
		creature_pane.setMinimumSize( new Dimension( 3*screen_width/20, 0 ) );

		JPanel tempStoryNotes_panel = new JPanel();
		tempStoryNotes_panel.setLayout( new BorderLayout() );
		tempStoryNotes_panel.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY), combatTab_l10n.getString("Story_Title"))
				) );
		tempStoryNotes_panel.add(storyNotes_pane, BorderLayout.CENTER);
		tempStoryNotes_panel.setPreferredSize( new Dimension( 4*screen_width/20, 4*screen_height/8 ) );
		tempStoryNotes_panel.setMaximumSize( new Dimension( 4*screen_width/20, 4*screen_height/8 ) );
		
		String[] columnNames = {combatTab_l10n.getString("TupleColName_Title"),
				combatTab_l10n.getString("TupleColSucc_Title"),
				combatTab_l10n.getString("TupleColFail_Title")};
		gmTally_model = new DefaultTableModel();
		gmTally_model.setDataVector(this.setTableData(controller_reference.getCombatEncounter()), columnNames);
		
		gmTally_table = new JTable(gmTally_model);
		gmTally_table.setBorder( BorderFactory.createEtchedBorder() );
		gmTally_table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent tme) {
				int row = tme.getFirstRow();
				int column = tme.getColumn();
				if (column == 0) {
					String name = (String) gmTally_model.getValueAt(row, column);
					controller_reference.changeTupleName(row, name);
				} else if (column == 1) {
					try {
						int value1 = Integer.parseInt((String) gmTally_model.getValueAt(row, column));
						controller_reference.changeTupleValue1(row, value1);
					} catch (Exception e) {
						gmTally_model.setValueAt("", row, column);
						controller_reference.changeTupleValue1(row, 0);
					}
				} else if (column == 2) {
					try {
						int value2 = Integer.parseInt((String) gmTally_model.getValueAt(row, column));
						controller_reference.changeTupleValue2(row, value2);
					} catch (Exception e) {
						gmTally_model.setValueAt("", row, column);
						controller_reference.changeTupleValue2(row, 0);
					}
				}
							
			}			
		});
		JPanel addTuple_panel = new JPanel();
		addTuple_panel.setLayout( new BoxLayout(addTuple_panel, BoxLayout.LINE_AXIS) );
		addTuple_panel.add(removeTuple_button);
		addTuple_panel.add(Box.createHorizontalGlue());
		addTuple_panel.add(addTuple_button);
		JPanel table_container = new JPanel();
		table_container.setLayout( new BorderLayout() );
		table_container.add(gmTally_table.getTableHeader(), BorderLayout.PAGE_START);
		table_container.add(gmTally_table, BorderLayout.CENTER);
		table_container.add(addTuple_panel, BorderLayout.PAGE_END);
		table_container.setPreferredSize( new Dimension( 4*screen_width/20, 3*screen_height/8 ) );
		table_container.setMaximumSize( new Dimension( 4*screen_width/20, 3*screen_height/8 ) );
		
		JPanel characterSheet_panel = new JPanel();
		characterSheet_panel.setLayout( new GridLayout(1, 2, 5, 0) );
		characterSheet_panel.add(currentCreature_pane);
		characterSheet_panel.add(selectedCreature_pane);
		characterSheet_panel.setPreferredSize( new Dimension(10*screen_width/20, 7*screen_height/8) );
		characterSheet_panel.setMinimumSize( new Dimension(10*screen_width/20, 7*screen_height/8) );
		
		GroupLayout combat_layout = new GroupLayout(combat_panel);
		combat_layout.setHorizontalGroup( combat_layout.createParallelGroup( GroupLayout.Alignment.LEADING)
						.addComponent(button_panel)
						.addGroup( combat_layout.createSequentialGroup()
								.addComponent(creature_pane)
								.addComponent(characterSheet_panel)
								.addGroup( combat_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
										.addComponent(tempStoryNotes_panel)
										.addComponent(table_container)))
				);
		combat_layout.setVerticalGroup( combat_layout.createSequentialGroup()
						.addComponent(button_panel)
						.addGroup(combat_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(creature_pane)
						.addComponent(characterSheet_panel)
						.addGroup( combat_layout.createSequentialGroup()
								.addComponent(tempStoryNotes_panel)
								.addComponent(table_container)))
				);
		combat_layout.linkSize(SwingConstants.VERTICAL, creature_pane, characterSheet_panel);
		combat_panel.setLayout(combat_layout);
	}

	private Object[][] setTableData(CombatEncounter theEncounter) {
		int num_tuples = theEncounter.getTally().getTuples().size();
		Object[][] tabletuples = new Object[num_tuples][3];
		List<Tuple> tuples  = theEncounter.getTally().getTuples();
		for (int index = 0; index < num_tuples; index++) {
			tabletuples[index][0] = tuples.get(index).getName();
			tabletuples[index][1] = tuples.get(index).getValue1();
			tabletuples[index][2] = tuples.get(index).getValue2();
		}
		return tabletuples;
	}
	
	public void actionPerformed(ActionEvent aevt) {
		Object source = aevt.getSource();
		
		if (source == autoRoll_button) {
			int [] selected_cells = creature_list.getSelectedIndices();
			Object creature;
			for (int index = 0; index < selected_cells.length; index++) {
				creature = creature_model.get(selected_cells[index]);
				if (creature instanceof CreatureBeanShallow) {
					creature = ((CreatureBeanShallow) creature).getEntity();
				}
				if (creature instanceof Player) {
					((Player) creature).setInitiative(controller_reference.autoRoll() + 
							((Player) creature).getCharacterSheet().getInitiative() );
				} else if (creature instanceof Monster) {
					((Monster) creature).setInitiative(controller_reference.autoRoll() + 
							((Monster) creature).getCharacterSheet().getInitiative() );
				}
			}
			this.revalidatePanel();
		} else if (source == organizeInitiative_button) {
			creature_model = controller_reference.organizeCreaturesByInitiative(creature_model);
			int index = controller_reference.finishTurn();
			if (index-1 >= 0) {
				((CreatureBeanShallow) creature_model.get(index - 1)).setIsCurrentCreature(false);
			} else {
				((CreatureBeanShallow) creature_model.lastElement() ).setIsCurrentCreature(false);
			}
			
			index = controller_reference.resetCurrentCreatureTurn();
			((CreatureBeanShallow) creature_model.get(index)).setIsCurrentCreature(true);
			creature_list.ensureIndexIsVisible(index);
			
			Object theCreature = ((CreatureBeanShallow) creature_model.get(index)).getEntity();
			CreatureCombatDetailed theBean = new CreatureCombatDetailed();
			theBean.createPanelFrom(theCreature);
			currentCreature_pane.removeAll();
			currentCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
			
			this.revalidatePanel();
		} else if (source == finishTurn_button) {
			int index = controller_reference.finishTurn();
			((CreatureBeanShallow) creature_model.get(index)).setIsCurrentCreature(true);
			creature_list.ensureIndexIsVisible(index);
			if (index-1 >= 0) {
				((CreatureBeanShallow) creature_model.get(index - 1)).setIsCurrentCreature(false);
			} else {
				((CreatureBeanShallow) creature_model.lastElement() ).setIsCurrentCreature(false);
			}
			
			Object theCreature = ((CreatureBeanShallow) creature_model.get(index)).getEntity();
			CreatureCombatDetailed theBean = new CreatureCombatDetailed();
			theBean.createPanelFrom(theCreature);
			currentCreature_pane.removeAll();
			currentCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
			
			this.revalidatePanel();
			
		} else if (source == addTeam_button) {
			AddTeam_Window team_add_window = new AddTeam_Window(controller_reference, this);
			team_add_window.showFrame();
			creature_model = controller_reference.updateCreaturesInCE(creature_model);
		} else if (source == addEffect_button) {
			if (creature_list.getSelectedIndices().length <= 0) {
				ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
				JOptionPane.showMessageDialog(combat_panel,
											  combatTab_l10n.getString("Effect_Warning"),
											  "Effect",
											  JOptionPane.WARNING_MESSAGE);
				return;
			}
			AddEffect_Window effect_add_window = new AddEffect_Window(controller_reference, this);
			effect_add_window.showFrame();
		} else if (source == addTuple_button) {
			Object[] new_tuple = controller_reference.addTuple();
			gmTally_model.addRow(new_tuple);
		} else if (source == removeTuple_button) {
			int[] selected_rows = gmTally_table.getSelectedRows();
			controller_reference.removeTuples(selected_rows);
			for (int index=0; index < selected_rows.length; index++) {
				gmTally_model.removeRow( selected_rows[index] );
			}
		} else if (source == GRE_button) {
			if (controller_reference.getCombatEncounter().getPlayersInCE().size() > 0) {
				GRE_Window GRE_window = new GRE_Window(controller_reference, this);
				GRE_window.showFrame();
			} else {
				ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
				JOptionPane.showMessageDialog(combat_panel,
						  combatTab_l10n.getString("GRE_Warning"),
						  combatTab_l10n.getString("GRE_Button"),
						  JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}

	public void revalidatePanel() {
		combat_panel.revalidate();
		combat_panel.repaint();
	}

	public void valueChanged(ListSelectionEvent lsevt) {
		int first_index = creature_list.getSelectedIndex();
		if (first_index < 0) {
			return;
		}/* else if (creature_list.isSelectedIndex(first_index)) {
			creature_list.setSelectedIndex(first_index);
		}*/
		Object theCreature = ((CreatureBeanShallow) creature_model.get(first_index) );
		if ( ((CreatureBeanShallow) theCreature).isCurrentCreature() ) {
			selectedCreature_pane.removeAll();
			revalidatePanel();
			return;
		}
		theCreature = ((CreatureBeanShallow) theCreature).getEntity();
		CreatureCombatDetailed theBean = new CreatureCombatDetailed();
		theBean.createPanelFrom(theCreature);
		selectedCreature_pane.removeAll();
		selectedCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
		revalidatePanel();
	}

	public void updateTeamModel() {
		creature_model = controller_reference.updateCreaturesInCE(creature_model);
	}

	public void addThisEffectToSelectedCreatures(Object entity) {
		Effect theEffect = (Effect) entity;
		
		int[] selectedCreatures = creature_list.getSelectedIndices();
		for (int index = 0; index < selectedCreatures.length; index++) {
			Object theCreature = creature_model.get(selectedCreatures[index]);
			if (theCreature instanceof CreatureBeanShallow) {
				theCreature = ((CreatureBeanShallow) theCreature).getEntity();
			}
			if (theCreature instanceof Player) {
				((Player) theCreature).addEffect(theEffect);
			} else if (theCreature instanceof Monster) {
				((Monster) theCreature).addEffect(theEffect);
			}
		}
		
		Object theCreature = ((CreatureBeanShallow) creature_model.get(controller_reference.getCurrentCreatureId())).getEntity();
		CreatureCombatDetailed theBean = new CreatureCombatDetailed();
		theBean.createPanelFrom(theCreature);
		currentCreature_pane.removeAll();
		currentCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
		
		theCreature = ((CreatureBeanShallow) creature_model.get(creature_list.getSelectedIndex())).getEntity();
		theBean = new CreatureCombatDetailed();
		theBean.createPanelFrom(theCreature);
		selectedCreature_pane.removeAll();
		selectedCreature_pane.add(theBean.getPanel(), BorderLayout.CENTER);
		
		this.revalidatePanel();
	}
	

}
