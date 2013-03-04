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
					addTeam_button, addEffect_button, addCreature_button;
	private DefaultListModel creature_model;
	private JList creature_list;	
	private JEditorPane storyNotes_pane;
	private JPanel currentCreature_pane, selectedCreature_pane;
	private JTable gmTally_table;
	private DefaultTableModel gmTally_model;
	private Combat controller_reference;
	
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
		addCreature_button = new JButton(combatTab_l10n.getString("AddCreature_Button"));
		addCreature_button.addActionListener(this);
				
		storyNotes_pane = new JEditorPane();
		storyNotes_pane.setBorder( BorderFactory.createEtchedBorder() );
		
		currentCreature_pane = new JPanel();
		currentCreature_pane.setOpaque(false);
		currentCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 0),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		
		selectedCreature_pane = new JPanel();
		selectedCreature_pane.setOpaque(false);
		selectedCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 5),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		
	}

	public Component getPanel() {
		this.initializePanel();
		this.createPanel();
		
		return combat_panel;	
	}
	
	private void initializePanel() {
		CombatEncounter theEncounter = controller_reference.getCombatEncounter();
		
		storyNotes_pane.setText(theEncounter.getNotes());
		
		
		creature_model = new DefaultListModel();
//		List<Object> creatures = theEncounter.getCreaturesInCe();
		List<Team> creatures = theEncounter.getTeams();
		for (int index = 0; index < creatures.size(); index++) {
//			creature_model.addElement(creatures.get(index));
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
		}
		creature_list = new JList(creature_model);
		creature_list.addListSelectionListener(this);
		creature_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		creature_list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//TODO change renderer
		creature_list.setCellRenderer(new bean.combat.TeamRenderer());
		creature_list.setPreferredSize(new Dimension(170, 0));
		creature_list.setFixedCellWidth(170);
		creature_list.setFixedCellHeight(80);
		
		creature_list.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == KeyEvent.VK_DELETE) {
					if (creature_list.getSelectedIndex() < 0) {
						return;
					}
					List<Object> selected_values = new ArrayList<Object>();
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
		
	}
	
	private void createPanel() {
		ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
		
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
		
		JScrollPane creature_pane = new JScrollPane(creature_list);
		creature_pane.setBorder( BorderFactory.createEtchedBorder() );
		int screen_width = (int) Math.round( Toolkit.getDefaultToolkit().getScreenSize().getWidth() );
		int screen_height = (int) Math.round( Toolkit.getDefaultToolkit().getScreenSize().getHeight() );
		creature_pane.setPreferredSize( new Dimension( 3*screen_width/4, screen_height/20 ) );
		creature_pane.setMinimumSize( new Dimension( 3*screen_width/4, screen_height/20 ) );
		
		JPanel menu_panel = new JPanel();
		menu_panel.setLayout( new BoxLayout(menu_panel, BoxLayout.LINE_AXIS) );
		menu_panel.add(addTeam_button);
		menu_panel.add(addEffect_button);
		menu_panel.add(addCreature_button);
		menu_panel.add(Box.createHorizontalGlue());
		
		//JPanel story_panel = new JPanel();
		//story_panel.setLayout( new BoxLayout(story_panel, BoxLayout.PAGE_AXIS) );
		JPanel tempStoryNotes_panel = new JPanel();
		tempStoryNotes_panel.setLayout( new BorderLayout() );
		tempStoryNotes_panel.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY), combatTab_l10n.getString("Story_Title"))
				) );
		tempStoryNotes_panel.add(storyNotes_pane, BorderLayout.CENTER);
		
		String[] columnNames = {combatTab_l10n.getString("TupleColName_Title"),
				combatTab_l10n.getString("TupleColSucc_Title"),
				combatTab_l10n.getString("TupleColFail_Title")};
		gmTally_model = new DefaultTableModel();
		gmTally_model.setDataVector(this.setTableData(controller_reference.getCombatEncounter()), columnNames);
		//TODO: figure out column names
		gmTally_table = new JTable(gmTally_model);
		gmTally_table.setBorder( BorderFactory.createEtchedBorder() );
		JPanel table_container = new JPanel();
		table_container.setLayout( new BorderLayout() );
		//table_container.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY), combatTab_l11n.getString("Tally_Title")) );
		table_container.add(gmTally_table.getTableHeader(), BorderLayout.PAGE_START);
		table_container.add(gmTally_table, BorderLayout.CENTER);
		//story_panel.add(tempStoryNotes_panel);
		//story_panel.add(table_container);
		
		JPanel characterSheet_panel = new JPanel();
		characterSheet_panel.setLayout( new GridLayout(1, 2, 5, 0) );
		characterSheet_panel.add(currentCreature_pane);
		characterSheet_panel.add(selectedCreature_pane);
		characterSheet_panel.setPreferredSize( new Dimension(3*screen_width/4, 10*screen_height/20) );
		characterSheet_panel.setMinimumSize( new Dimension(3*screen_width/4, 10*screen_height/20) );
		
		GroupLayout combat_layout = new GroupLayout(combat_panel);
		combat_layout.setHorizontalGroup( combat_layout.createSequentialGroup()
				.addGroup( combat_layout.createParallelGroup( GroupLayout.Alignment.LEADING)
						.addComponent(button_panel)
						.addComponent(creature_pane)
						.addComponent(characterSheet_panel)
						.addComponent(menu_panel)
						)
				
				.addGroup( combat_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(tempStoryNotes_panel)
						.addComponent(table_container))
				);
		combat_layout.setVerticalGroup( combat_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( combat_layout.createSequentialGroup()
						.addComponent(button_panel)
						.addComponent(creature_pane)
						.addComponent(characterSheet_panel)
						.addComponent(menu_panel)
						)
				
				.addGroup( combat_layout.createSequentialGroup()
						.addComponent(tempStoryNotes_panel)
						.addComponent(table_container))
				);
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
				if (creature instanceof Player) {
					((Player) creature).setInitiative(controller_reference.autoRoll());
				} else if (creature instanceof Monster) {
					((Monster) creature).setInitiative(controller_reference.autoRoll());
				}
			}
		} else if (source == organizeInitiative_button) {
			creature_model = controller_reference.organizeCreaturesByInitiative();
		} else if (source == finishTurn_button) {
			((CreatureBeanShallow) creature_model.get(controller_reference.getCombatEncounter().getCurrentCreatureId()))
				.setIsCurrentCreature(false);
			int index = controller_reference.finishTurn();
			((CreatureBeanShallow) creature_model.get(index)).setIsCurrentCreature(true);
			creature_list.ensureIndexIsVisible(index);
			
		} else if (source == addTeam_button) {
		} else if (source == addEffect_button) {
		} else if (source == addCreature_button) {
		}
		
	}

	public void valueChanged(ListSelectionEvent lsevt) {
		
	}
	

}
