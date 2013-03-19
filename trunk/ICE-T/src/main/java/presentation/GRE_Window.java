package presentation;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import bean.combat.TeamRenderer;

import java.util.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.App_Root;
import controller.Combat;
import entity.Team;

public class GRE_Window implements ActionListener, MouseListener {
	private static final int num_teams = 4;
	private Combat controller_reference;
	private Combat_Tab parent_window;
	private JPanel GRE_panel;
	private JFrame GRE_window;
	private JButton addTeam_button, reRoll_button;
	private JPanel options_panel, team_panel;
	private List<DefaultListModel> teamSelection_models;
	private List<JList> teamSelection_lists;
	private List<Team> team_list;
	private String team_name;
	
	public GRE_Window (Combat controller, Combat_Tab parent) {
		java.util.Random random_generator = new java.util.Random();
		int alpha = random_generator.nextInt(26) + 65;
		int numeric = random_generator.nextInt(100);
		team_name = Character.toString((char) alpha)+numeric;
		controller_reference = controller;
		parent_window = parent;
		teamSelection_models = new ArrayList<DefaultListModel>(0);
		for (int index = 0; index < num_teams; index++) {
			teamSelection_models.add(new DefaultListModel() );
		}
		teamSelection_lists = new ArrayList<JList>(0);
		team_list = new ArrayList<Team>(0);
		
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
		ResourceBundle gre_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.CombatTab", controller.App_Root.language_locale);
		GRE_panel = new JPanel();
		options_panel = new JPanel();
		options_panel.setBorder( BorderFactory.createTitledBorder(gre_l10n.getString("Options_Title")) );
		team_panel = new JPanel();
		team_panel.setLayout( new BoxLayout(team_panel, BoxLayout.LINE_AXIS) );
		getRandomTeams();
		addTeam_button = new JButton(gre_l10n.getString("AddTeam_Button"));
		addTeam_button.addActionListener(this);
		reRoll_button = new JButton(gre_l10n.getString("ReRoll_Button"));
		reRoll_button.addActionListener(this);
		
		GroupLayout GRE_layout = new GroupLayout(GRE_panel);
		GRE_layout.setHorizontalGroup( GRE_layout.createParallelGroup( GroupLayout.Alignment.TRAILING ) 
				.addComponent(options_panel)
				.addComponent(team_panel)
				.addGroup( GRE_layout.createSequentialGroup()
						.addComponent(addTeam_button)
						.addComponent(reRoll_button))
				);
		GRE_layout.setVerticalGroup( GRE_layout.createSequentialGroup() 
				.addComponent(options_panel)
				.addComponent(team_panel)
				.addGroup( GRE_layout.createParallelGroup( GroupLayout.Alignment.CENTER)
						.addComponent(addTeam_button)
						.addComponent(reRoll_button))
				);
		GRE_panel.setLayout(GRE_layout);
	}
	
	private void getRandomTeams() {
		team_panel.removeAll();
		team_panel.add( Box.createHorizontalGlue() );
		team_list = controller_reference.generateRandomEncounterTeams(num_teams);
		teamSelection_models = createListModels();
		for (int  index = 0; index < num_teams; index++) {
			teamSelection_lists.add( new JList(teamSelection_models.get(index)) );
			teamSelection_lists.get(index).setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			teamSelection_lists.get(index).setLayoutOrientation(JList.VERTICAL);
			teamSelection_lists.get(index).setCellRenderer(new bean.combat.TeamRenderer());
			teamSelection_lists.get(index).addMouseListener(this);
			JScrollPane team_pane = new JScrollPane(teamSelection_lists.get(index));
			team_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			team_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			team_panel.add(team_pane);
			team_panel.add( Box.createHorizontalGlue() );
		}
		GRE_panel.revalidate();
		GRE_panel.repaint();
	}

	private List<DefaultListModel> createListModels() {
		for (int index = 0; index < num_teams; index++) {
			team_list.get(index).setName(team_name);
			teamSelection_models.get(index).removeAllElements();
			for (int m_index = 0; m_index<team_list.get(index).getMonsters().size(); m_index++) {
				teamSelection_models.get(index).addElement( team_list.get(index).getMonsterAt(m_index) );
			}
			for (int t_index = 0; t_index<team_list.get(index).getTraphazards().size(); t_index++) {
				teamSelection_models.get(index).addElement( team_list.get(index).getTrapHazardAt(t_index) );
			}
		}
		return teamSelection_models;
	}

	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		if (source == addTeam_button) {
			
			Team addingThis = new Team();
			boolean oneTeamSelected = false;
			for (int index = 0; index < num_teams; index++) {
				if (teamSelection_lists.get(index).getSelectedIndices().length > 0) {
					oneTeamSelected = true;
					addingThis =  team_list.get(index);
					break;
				}
					
			}
			if (oneTeamSelected) {
				int id = addingThis.saveNPC(addingThis.getMonsters());
				addingThis.setId(id);
				
				List<Team> addTeam = new ArrayList<Team>(0);
				addTeam.add(addingThis);
				controller_reference.addTheseTeamsToCE(addTeam);
				parent_window.updateTeamModel();
			
				GRE_window.setVisible(false);
				GRE_window.dispose();
			} else {
				return;
			}
		} else if (source == reRoll_button) {
			getRandomTeams();
		}
	}

	public void mouseClicked(MouseEvent me) {
		
	}

	public void mouseEntered(MouseEvent me) {
		
	}

	public void mouseExited(MouseEvent me) {
		
	}

	public void mousePressed(MouseEvent me) {
		Object source = me.getSource();
		JList theList;
		for (int index = 0; index < num_teams; index++) {
			theList = teamSelection_lists.get(index);
			if (source == theList) {
				theList.setSelectionInterval(0, teamSelection_models.get(index).getSize());
			} else {
				theList.removeSelectionInterval(0, teamSelection_models.get(index).getSize());
			}
		}
	}

	public void mouseReleased(MouseEvent me) {
		
	}

}
