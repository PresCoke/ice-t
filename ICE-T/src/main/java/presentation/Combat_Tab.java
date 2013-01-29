package presentation;

import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;
import controller.Combat;

public class Combat_Tab implements ActionListener, ListSelectionListener {

	private JPanel combat_panel;
	private JButton autoRoll_button, organizeInitiative_button, finishTurn_button,
	 /*The buttons below might work better as menus that expand up and allow drag and dropping of commonly use entities (maybe a search bar like safari)*/
					addTeam_button, addEffect_button, addCreature_button;
	private JList creature_list;
	private JList effect_list; /*Not sure about this: 1) have a list of effects for each creature
													  2) have one list of effects for every creature and just take the effects needed
													  3) have one list of effects that is repopulated for every creature */
	
	private JEditorPane storyNotes_pane, currentCreature_pane, selectedCreature_pane;
	private JTable gmTally_table;
	private Object[][] table_data;
	private Combat controller_reference;
	
	/* THIS IS A JPANEL THAT CAN BE RENDERED IN A LIST!!*/
	public class creatureCell_renderer extends JPanel implements ListCellRenderer {
		
		public creatureCell_renderer() {
			
		}

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			//TODO: implement this further
			if (/*value instanceof entity.Creature*/ true) {
				/*Do drawing of creature*/
			} else/* if (value instanceof entity.Effect)*/ {
				/*Do drawing of effect*/
			}
			
			return null;
		}
		
	}
	
	public Combat_Tab(Combat combat_controller) {
		// TODO Auto-generated constructor stub
		controller_reference = combat_controller;
	}

	public Component getPanel() {
		/* Start Hack
		 * - this behaviour may be better implemented by inheriting from component
		 *   or creating a custom class with a super getPanel() method.
		 */
		
//		combat_tab = new JPanel();
//		combat_tab.add( new JTextField("COMBAT") );
		
		this.createPanel();
		
		return combat_panel;	
	}

	public void actionPerformed(ActionEvent aevt) {
		// TODO Auto-generated method stub
		
	}

	public void valueChanged(ListSelectionEvent lsevt) {
		// TODO Auto-generated method stub
		
	}
	
	private void createPanel() {
		this.combat_panel = new JPanel();
		this.combat_panel.setLayout( new GridBagLayout() );
		this.combat_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) );
		
		ResourceBundle combatTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.CombatTab", App_Root.language_locale);
		
		// creature organization, and effects
		JPanel initiative_panel = new JPanel();
		initiative_panel.setLayout( new GridBagLayout() );
		initiative_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.LINE_AXIS) );
		autoRoll_button = new JButton(combatTab_l10n.getString("AutoRoll_Button"));
		autoRoll_button.addActionListener(this);
		//autoRoll_button.setAlignmentX(Component.LEFT_ALIGNMENT);
		organizeInitiative_button = new JButton(combatTab_l10n.getString("OrganizeInitiative_Button"));
		organizeInitiative_button.addActionListener(this);
		//organizeInitiative_button.setAlignmentX(Component.LEFT_ALIGNMENT);
		finishTurn_button = new JButton(combatTab_l10n.getString("FinishTurn_Button"));
		finishTurn_button.addActionListener(this);
		//finishTurn_button.setAlignmentX(Component.LEFT_ALIGNMENT);
		button_panel.add(finishTurn_button);
		button_panel.add(autoRoll_button);
		button_panel.add(organizeInitiative_button);
		
		//find way to populate this!!!
		//TODO: this needs to be populated in a useful manner
		creature_list = new JList();
		creature_list.addListSelectionListener(this);
		creature_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane creature_pane = new JScrollPane(creature_list);
		creature_pane.setBorder( BorderFactory.createEtchedBorder() );
		creature_pane.setPreferredSize( new Dimension(0, 50) );
		
		effect_list = new JList();
		effect_list.addListSelectionListener(this);
		effect_list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane effect_pane = new JScrollPane(effect_list);
		effect_pane.setBorder( BorderFactory.createEtchedBorder() );
		effect_pane.setPreferredSize( new Dimension(0, 50) );
		
		GridBagConstraints interiorButton_constraints = new GridBagConstraints();
		//interiorButton_constraints.fill = GridBagConstraints.BOTH;
		interiorButton_constraints.gridx = 0; interiorButton_constraints.gridy = 0;
		interiorButton_constraints.weightx = 0.0; interiorButton_constraints.weighty = 0.0;
		interiorButton_constraints.anchor = GridBagConstraints.NORTHWEST;
		initiative_panel.add(button_panel, interiorButton_constraints);
		
		GridBagConstraints creature_constraints = new GridBagConstraints();
		creature_constraints.fill = GridBagConstraints.BOTH;
		creature_constraints.gridx = 0; creature_constraints.gridy = 1;
		creature_constraints.gridwidth = 3;
		creature_constraints.weightx = 1.0; creature_constraints.weighty = 0.1;
		initiative_panel.add(creature_pane, creature_constraints);
		
		GridBagConstraints effect_constraints = new GridBagConstraints();
		effect_constraints.fill = GridBagConstraints.BOTH;
		effect_constraints.gridx = 0; effect_constraints.gridy = 2;
		effect_constraints.gridwidth = 3;
		effect_constraints.weightx = 1.0; effect_constraints.weighty = 0.1;
		initiative_panel.add(effect_pane, effect_constraints);
		
		JPanel menu_panel = new JPanel();
		addTeam_button = new JButton(combatTab_l10n.getString("AddTeam_Button"));
		addTeam_button.addActionListener(this);
		menu_panel.add(addTeam_button);
		addEffect_button = new JButton(combatTab_l10n.getString("AddEffect_Button"));
		addEffect_button.addActionListener(this);
		menu_panel.add(addEffect_button);
		addCreature_button = new JButton(combatTab_l10n.getString("AddCreature_Button"));
		addCreature_button.addActionListener(this);
		menu_panel.add(addCreature_button);
		
		JPanel story_panel = new JPanel();
		story_panel.setLayout( new BoxLayout(story_panel, BoxLayout.PAGE_AXIS) );
		storyNotes_pane = new JEditorPane();
		storyNotes_pane.setBorder( BorderFactory.createEtchedBorder() );
		JPanel tempStoryNotes_panel = new JPanel();
		tempStoryNotes_panel.setLayout( new BorderLayout() );
		tempStoryNotes_panel.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY), combatTab_l10n.getString("Story_Title"))
				) );
		tempStoryNotes_panel.add(storyNotes_pane, BorderLayout.CENTER);
		table_data = this.setTableData();
		String[] columnNames = {combatTab_l10n.getString("TupleColName_Title"),
								combatTab_l10n.getString("TupleColSucc_Title"),
								combatTab_l10n.getString("TupleColFail_Title")};
		gmTally_table = new JTable(table_data, columnNames);
		gmTally_table.setBorder( BorderFactory.createEtchedBorder() );
		JPanel table_container = new JPanel();
		table_container.setLayout( new BorderLayout() );
		//table_container.setBorder( BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY), combatTab_l11n.getString("Tally_Title")) );
		table_container.add(gmTally_table.getTableHeader(), BorderLayout.PAGE_START);
		table_container.add(gmTally_table, BorderLayout.CENTER);
		story_panel.add(tempStoryNotes_panel);
		story_panel.add(table_container);
		
		GridBagConstraints initiativePanel_constraints = new GridBagConstraints();
		initiativePanel_constraints.fill = GridBagConstraints.BOTH;
		initiativePanel_constraints.gridx = 0; initiativePanel_constraints.gridy = 0;
		initiativePanel_constraints.gridwidth = 2;
		initiativePanel_constraints.weightx = 0.6; initiativePanel_constraints.weighty = 0.2;
		combat_panel.add(initiative_panel, initiativePanel_constraints);		
		
		GridBagConstraints menuPanel_constraints = new GridBagConstraints();
		menuPanel_constraints.gridx = 0; menuPanel_constraints.gridy = 2;
		menuPanel_constraints.weightx = 0.0; menuPanel_constraints.weighty = 0.0;
		menuPanel_constraints.anchor = GridBagConstraints.SOUTHWEST;
		combat_panel.add(menu_panel, menuPanel_constraints);
		
		GridBagConstraints storyPanel_constraints = new GridBagConstraints();
		storyPanel_constraints.fill = GridBagConstraints.BOTH;
		storyPanel_constraints.gridx = 2; storyPanel_constraints.gridy = 0;
		storyPanel_constraints.gridheight = 2;
		storyPanel_constraints.weightx = 0.1; storyPanel_constraints.weighty = 1.0;
		combat_panel.add(story_panel, storyPanel_constraints);
		
		JPanel characterSheet_panel = new JPanel();
		characterSheet_panel.setLayout( new GridLayout(1, 2, 5, 0) );
		//TODO: need better population 
		currentCreature_pane = new JEditorPane(); currentCreature_pane.setText("current"); //TODO: should be creatures name
		currentCreature_pane.setOpaque(false);
		currentCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 0),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		//TODO:
		selectedCreature_pane = new JEditorPane(); selectedCreature_pane.setText("selected"); //TODO: should be creatures name
		selectedCreature_pane.setOpaque(false);
		selectedCreature_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 2, 5),
				BorderFactory.createLineBorder(Color.GRAY) 					) );
		characterSheet_panel.add(currentCreature_pane);
		characterSheet_panel.add(selectedCreature_pane);
		GridBagConstraints characterSheet_constraints = new GridBagConstraints();
		characterSheet_constraints.fill = GridBagConstraints.BOTH;
		characterSheet_constraints.gridx = 0; characterSheet_constraints.gridy = 1;
		characterSheet_constraints.gridwidth = 2;
		characterSheet_constraints.weightx = 0.9; characterSheet_constraints.weighty = 0.8;
		combat_panel.add(characterSheet_panel, characterSheet_constraints);
		
	}

	private Object[][] setTableData() {
		// TODO make this actually work
		Object[][] temp = { { "Tevish", new Integer(1), new Integer(3)},
							{ "Jaks", new Integer(5), new Integer(1)  },
							{ "Natar", new Integer(3), new Integer(2) } };
		return temp;
	}
	
	

}
