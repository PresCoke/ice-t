package bean.combat;

import java.util.List;
import java.util.ResourceBundle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;
import entity.Attack;
import entity.Effect;
import entity.Player;
import entity.Monster;
import entity.TrapHazard;

public class CreatureCombatDetailed {

	private Player thePlayer;
	private Monster theMonster;
	private TrapHazard theTrap;
//	private JSpinner currentHealth_field;
//	private JSpinner currentSurge_field;
//	private JSpinner tempHP_field;
//	private JSpinner currentInit_field;
//	private JCheckBox secondWind_field;
	
	public CreatureCombatDetailed() {
		
	}
	
	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof CreatureBeanShallow) {
			thisEntity = ((CreatureBeanShallow) thisEntity).getEntity();
		}
		if (thisEntity instanceof Player) {
			thePlayer = (Player) thisEntity;
			theMonster = null;
			theTrap = null;
		} else if (thisEntity instanceof Monster) {
			theMonster = (Monster) thisEntity;
			thePlayer = null;
			theTrap = null;
		} else if (thisEntity instanceof TrapHazard) {
			theTrap = (TrapHazard) thisEntity;
			theMonster = null;
			thePlayer = null;
		}
	}
	

	public Object getEntity() {
		if (thePlayer != null && theMonster == null && theTrap == null) {
			return thePlayer;
		} else if (thePlayer == null && theMonster != null && theTrap == null) {
			return theMonster;
		} else if (thePlayer == null && theMonster == null && theTrap != null) {
			return theTrap;
		} else {
			return null;
		}
		
	}
	
	public JPanel getPanel() {
		if (thePlayer != null && theMonster == null && theTrap == null) {
			return createPlayerPanel();
		} else if (thePlayer == null && theMonster != null && theTrap == null) {
			return createMonsterPanel();
		} else if (thePlayer == null && theMonster == null && theTrap != null) {
			return createTrapPanel();
		} else {
			return null;
		}
	}
	
	private JPanel createPlayerPanel() {
		/*
		 * Player: [Player Name] Character: [Character Name] Level: [Level] [XP] XP 
		 * [Size] [Role] [Glue]
		 * Initiative: [Initiative] Speed: [Speed]
		 * STR: [STR] ([STR MOD]) DEX: [DEX] ([DEX MOD]) WIS: [WIS] ([WIS MOD])
		 * CON: [CON] ([CON MOD]) INT: [INT] ([INT MOD]) CHA: [CHA] ([CHA MOD])
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * HP: [Current] Bloodied: [Bloodied] Second-Winded: [SecondWind]
		 * 
		 * [Attacks]
		 * 
		 * Skills: [in form [name value]]
		 * Class Features: [class features] 
		 * Misc: [Misc]
		 * Languages: [Languages]
		 * 
		 * [Effects]
		 */
		ResourceBundle player_l10n = ResourceBundle.getBundle("filters.beanGUI_l10n.Entity", App_Root.language_locale);
		JPanel player_panel = new JPanel();
		JLabel generalInfo_label;
		JLabel init_label;
		JSpinner init_field;
		JLabel speed_label;
		JLabel ability_defense_label;
		JPanel health_panel = new JPanel();
		JLabel currentHP_label;
		JSpinner currentHP_field;
		JLabel bloodied_label;
		JLabel secondWind_label;
		JCheckBox secondWindUsed_checkbox = new JCheckBox();
		JScrollPane attack_panel;
		JList  attack_list;
		DefaultListModel attack_model;
		String tooltip;
		JScrollPane effect_panel;
		JList effect_list;
		DefaultListModel effect_model;
		
		String size = "", role = "";
		switch (thePlayer.getCharacterSheet().getSize()) {
		case tiny:
			size = player_l10n.getString("Tiny_size"); break;
		case small:
			size = player_l10n.getString("Small_size"); break;
		case medium:
			size = player_l10n.getString("Medium_size"); break;
		case large:
			size = player_l10n.getString("Large_size"); break;
		case huge:
			size = player_l10n.getString("Huge_size"); break;
		case gargantuan:
			size = player_l10n.getString("Gargantuan_size"); break;
		}
		switch(thePlayer.getCharacterSheet().getRole()) {
		case striker:
			role = player_l10n.getString("Striker_role"); break;
		case controller:
			role = player_l10n.getString("Controller_role"); break;
		case defender:
			role = player_l10n.getString("Defender_role"); break;
		case leader:
			role = player_l10n.getString("Leader_role"); break;
		case artillery:
			role = player_l10n.getString("Artillery_role"); break;
		case brute:
			role = player_l10n.getString("Brute_role"); break;
		case lurker:
			role = player_l10n.getString("Lurker_role"); break;
		case skirmisher:
			role = player_l10n.getString("Skirmisher_role"); break;
		case soldier:
			role = player_l10n.getString("Soldier_role"); break;
		}
		
		generalInfo_label = new JLabel(player_l10n.getString("PlayerName_entity") + " " + thePlayer.getPlayerName() + " " +
									   player_l10n.getString("Name_entity") + " " + thePlayer.getCharacterSheet().getName() + " "+
									   player_l10n.getString("LVL_entity") + " " + thePlayer.getCharacterSheet().getLevel() + " "+
									   thePlayer.getCharacterSheet().getXP() + "XP\n" +
									   size + " " + role + "\n"
									   );
		init_label = new JLabel(player_l10n.getString("Init_entity"));
		init_field = new JSpinner( new SpinnerNumberModel(thePlayer.getInitiative(), 0, 100, 1));
		init_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int init = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				thePlayer.setInitiative(init);
			}
		});
		((JSpinner.DefaultEditor) init_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				int key = ke.getKeyChar() - '0';
				if (key < 0 || key > 9) {
					ke.consume();
				}
			}
		}); 
		
		speed_label = new JLabel(" "+player_l10n.getString("Speed_entity")+" "+thePlayer.getCharacterSheet().getSpeed());
		ability_defense_label = new JLabel(
		        player_l10n.getString("STR_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getSTR())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getSTR()/2) - 5)+")"+
				
				player_l10n.getString("CON_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getCON())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getCON()/2) - 5)+")"+
				
				player_l10n.getString("DEX_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getDEX())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getDEX()/2) - 5)+")\n"+
				
				player_l10n.getString("INT_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getINT())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getINT()/2) - 5)+")"+
				
				player_l10n.getString("WIS_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getWIS())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getWIS()/2) - 5)+")"+
				
				player_l10n.getString("CHA_entity")+" "+Integer.toString(thePlayer.getCharacterSheet().getCHAR())+" ("+Integer.toString( (thePlayer.getCharacterSheet().getCHAR()/2) - 5)+")\n"+
		        
				player_l10n.getString("AC_entity")+" "+thePlayer.getCharacterSheet().getAC()+" "+player_l10n.getString("FORT_entity")+" "+thePlayer.getCharacterSheet().getFORT()+" "+
				
				player_l10n.getString("REF_entity")+" "+thePlayer.getCharacterSheet().getREF()+" "+player_l10n.getString("WILL_entity")+" "+thePlayer.getCharacterSheet().getWILL()
				
				);
		
		secondWind_label = new JLabel(player_l10n.getString("SecondWind_creature"));
		secondWindUsed_checkbox.setSelected(thePlayer.isSecondWind());
		secondWindUsed_checkbox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Tricky as fuck might not work;
				thePlayer.setSecondWind( ((JCheckBox) ae.getSource()).isSelected() ); 
			}
		});
		currentHP_label = new JLabel(player_l10n.getString("CurrentHP_entity"));
		currentHP_field = new JSpinner (new SpinnerNumberModel(thePlayer.getCurrentHP(), -1*thePlayer.getCharacterSheet().getBloodied(), thePlayer.getCharacterSheet().getMaxHP(), 1) );
		currentHP_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int hp = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				thePlayer.setCurrentHP(hp);
			}
		});
		((JSpinner.DefaultEditor) currentHP_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				int key = ke.getKeyChar() - '0';
				if (key < 0 || key > 9) {
					ke.consume();
				}
			}
		});
		
		bloodied_label = new JLabel(player_l10n.getString("Bloodied_entity")+" "+thePlayer.getCharacterSheet().getBloodied());
		
		health_panel.setLayout(new BoxLayout(health_panel, BoxLayout.LINE_AXIS));
		health_panel.add(currentHP_label);
		health_panel.add(currentHP_field);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(bloodied_label);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(secondWind_label);
		health_panel.add(secondWindUsed_checkbox);
		
		attack_model = new DefaultListModel();
		List<Attack> list_O_attacks = thePlayer.getCharacterSheet().getAttacks();
		for (int index = 0; index < list_O_attacks.size(); index++) {
			attack_model.addElement(list_O_attacks.get(index));
		}
		attack_list = new JList(attack_model);
		attack_list.setCellRenderer( new AttackBean() );
		attack_panel = new JScrollPane(attack_list);
		attack_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		attack_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tooltip = "<html>";
		if (thePlayer.getCharacterSheet().getAcrobatics() > 0) {
			tooltip += player_l10n.getString("ACRO_trap") + " " + thePlayer.getCharacterSheet().getAcrobatics()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getAthletics() > 0) {
			tooltip += player_l10n.getString("ATHL_trap") + " " + thePlayer.getCharacterSheet().getAthletics()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getArcana() > 0) {
			tooltip += player_l10n.getString("ARCA_trap") + " " + thePlayer.getCharacterSheet().getArcana()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getBluff() > 0) {
			tooltip += player_l10n.getString("BLUF_trap") + " " + thePlayer.getCharacterSheet().getBluff()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getDiplomacy() > 0) {
			tooltip += player_l10n.getString("DIPL_trap") + " " + thePlayer.getCharacterSheet().getDiplomacy()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getDungeoneering() > 0) {
			tooltip += player_l10n.getString("DUNG_trap") + " " + thePlayer.getCharacterSheet().getDungeoneering()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getEndurance() > 0) {
			tooltip += player_l10n.getString("ENDU_trap") + " " + thePlayer.getCharacterSheet().getEndurance()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getHeal() > 0) {
			tooltip += player_l10n.getString("HEAL_trap") + " " + thePlayer.getCharacterSheet().getHeal()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getHistory() > 0) {
			tooltip += player_l10n.getString("HIST_trap") + " " + thePlayer.getCharacterSheet().getHistory()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getInsight() > 0) {
			tooltip += player_l10n.getString("INSI_trap") + " " + thePlayer.getCharacterSheet().getInsight()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getIntimidate() > 0) {
			tooltip += player_l10n.getString("INTI_trap") + " " + thePlayer.getCharacterSheet().getIntimidate()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getNature() > 0) {
			tooltip += player_l10n.getString("NATU_trap") + " " + thePlayer.getCharacterSheet().getNature()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getPerception() > 0) {
			tooltip += player_l10n.getString("PERC_trap") + " " + thePlayer.getCharacterSheet().getPerception()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getReligion() > 0) {
			tooltip += player_l10n.getString("RELI_trap") + " " + thePlayer.getCharacterSheet().getReligion()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getStealth() > 0) {
			tooltip += player_l10n.getString("STEA_trap") + " " + thePlayer.getCharacterSheet().getStealth()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getStreetwise() > 0) {
			tooltip += player_l10n.getString("STRE_trap") + " " + thePlayer.getCharacterSheet().getStreetwise()+"<br>";
		}
		if (thePlayer.getCharacterSheet().getThievery() > 0) {
			tooltip += player_l10n.getString("THIE_trap") + " " + thePlayer.getCharacterSheet().getThievery()+"<br>";
		}
		tooltip += "<br><br>";
		tooltip += thePlayer.getCharacterSheet().getRaceFeatures() + "<br>";
		tooltip += thePlayer.getCharacterSheet().getMisc() + "<br><br>";
		tooltip += thePlayer.getCharacterSheet().getLanguages() + "</html>";
		
		effect_model = new DefaultListModel();
		List<Effect> list_O_effects = thePlayer.getEffects();
		for (int index=0; index < list_O_effects.size(); index++) {
			effect_model.addElement( list_O_effects.get(index) );
		}
		effect_list = new JList(effect_model);
		effect_list.setCellRenderer(new EffectCombat());
		effect_panel = new JScrollPane(effect_list);
		effect_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		effect_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		GroupLayout player_layout = new GroupLayout(player_panel);
		player_layout.setHorizontalGroup( player_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(generalInfo_label)
				.addGroup( player_layout.createSequentialGroup()
						.addComponent(init_label)
						.addComponent(init_field)
						.addComponent(speed_label))
				.addComponent(ability_defense_label)
				.addComponent(attack_panel)
				.addComponent(effect_panel)
				);
		player_layout.setVerticalGroup( player_layout.createSequentialGroup()
				.addComponent(generalInfo_label)
				.addGroup( player_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(init_label)
						.addComponent(init_field)
						.addComponent(speed_label))
				.addComponent(ability_defense_label)
				.addComponent(attack_panel)
				.addComponent(effect_panel)
				);
		player_layout.linkSize(SwingConstants.VERTICAL, attack_panel, effect_panel);
				
		player_panel.setToolTipText(tooltip);
		player_panel.setLayout(player_layout);
		return player_panel;
	}
	private JPanel createMonsterPanel() {
		/*
		 * [Name] [Glue] Level [Level] [Role]
		 * [Size] [Origin] [Type] [Glue] [XP] XP
		 * Initiative: [Init] 
		 * HP: [Current] Bloodied: [Bloodied] Second-Winded: [SecondWind]
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * Speed: [Speed]
		 * 
		 * [Attacks]
		 * 
		 * Skills: [any greater than zero in form [name value]]
		 * STR: [STR] ([STR MOD]) DEX: [DEX] ([DEX MOD]) WIS: [WIS] ([WIS MOD])
		 * CON: [CON] ([CON MOD]) INT: [INT] ([INT MOD]) CHA: [CHA] ([CHA MOD])
		 * 
		 * Languages: [Languages]
		 * Misc: [Misc]
		 * 
		 * [Effects]
		 */
		
		ResourceBundle monster_l10n = ResourceBundle.getBundle("filters.beanGUI_l10n.Entity", App_Root.language_locale);
		JPanel monster_panel = new JPanel();
		JLabel generalInfo_label;
		JLabel init_label;
		JSpinner init_field;
		JLabel speed_label;
		JLabel ability_defense_label;
		JPanel health_panel = new JPanel();
		JLabel currentHP_label;
		JSpinner currentHP_field;
		JLabel bloodied_label;
		JLabel secondWind_label;
		JCheckBox secondWindUsed_checkbox = new JCheckBox();
		JScrollPane attack_panel;
		JList  attack_list;
		DefaultListModel attack_model;
		String tooltip;
		JScrollPane effect_panel;
		JList effect_list;
		DefaultListModel effect_model;
		
		String size = "", role = "", origin = "", type = "";
		switch (theMonster.getCharacterSheet().getSize()) {
		case tiny:
			size = monster_l10n.getString("Tiny_size"); break;
		case small:
			size = monster_l10n.getString("Small_size"); break;
		case medium:
			size = monster_l10n.getString("Medium_size"); break;
		case large:
			size = monster_l10n.getString("Large_size"); break;
		case huge:
			size = monster_l10n.getString("Huge_size"); break;
		case gargantuan:
			size = monster_l10n.getString("Gargantuan_size"); break;
		}
		switch(theMonster.getCharacterSheet().getRole()) {
		case striker:
			role = monster_l10n.getString("Striker_role"); break;
		case controller:
			role = monster_l10n.getString("Controller_role"); break;
		case defender:
			role = monster_l10n.getString("Defender_role"); break;
		case leader:
			role = monster_l10n.getString("Leader_role"); break;
		case artillery:
			role = monster_l10n.getString("Artillery_role"); break;
		case brute:
			role = monster_l10n.getString("Brute_role"); break;
		case lurker:
			role = monster_l10n.getString("Lurker_role"); break;
		case skirmisher:
			role = monster_l10n.getString("Skirmisher_role"); break;
		case soldier:
			role = monster_l10n.getString("Soldier_role"); break;
		}
		switch (theMonster.getCharacterSheet().getMonsterOrigin()) {
		case aberrant:
			origin = monster_l10n.getString("Aberrant_origin"); break;
		case elemental:
			origin = monster_l10n.getString("Elemental_origin"); break;
		case fey:
			origin = monster_l10n.getString("Fey_origin"); break;
		case immortal:
			origin = monster_l10n.getString("Immortal_origin"); break;
		case shadow:
			origin = monster_l10n.getString("Shadow_origin"); break;
		}
		switch (theMonster.getCharacterSheet().getMonsterType()) {
		case animate:
			type = monster_l10n.getString("Animate_monster"); break;
		case beast:
			type = monster_l10n.getString("Beast_monster"); break;
		case humanoid:
			type = monster_l10n.getString("Humanoid_monster"); break;
		case magicalBeast:
			type = monster_l10n.getString("MagicalBeast_monster"); break;
		}
		generalInfo_label = new JLabel(monster_l10n.getString("Name_entity") +" "+ theMonster.getCharacterSheet().getName() +
									   " "+monster_l10n.getString("LVL_entity")+" " + theMonster.getCharacterSheet().getLevel() +
									   " "+ role+"\n" +
									   size + " "  + origin+ " "+type+" "+ theMonster.getCharacterSheet().getXP() + " XP"
									   );
		init_label = new JLabel(monster_l10n.getString("Init_entity"));
		init_field = new JSpinner( new SpinnerNumberModel(theMonster.getInitiative(), 0, 100, 1));
		init_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int init = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				theMonster.setInitiative(init);
			}
		});
		((JSpinner.DefaultEditor) init_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				int key = ke.getKeyChar() - '0';
				if (key < 0 || key > 9) {
					ke.consume();
				}
			}
		}); 
		
		speed_label = new JLabel(" "+monster_l10n.getString("Speed_entity")+" "+theMonster.getCharacterSheet().getSpeed());
		ability_defense_label = new JLabel(
		        monster_l10n.getString("STR_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getSTR())+" ("+Integer.toString( (theMonster.getCharacterSheet().getSTR()/2) - 5)+")"+
				
				monster_l10n.getString("CON_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getCON())+" ("+Integer.toString( (theMonster.getCharacterSheet().getCON()/2) - 5)+")"+
				
				monster_l10n.getString("DEX_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getDEX())+" ("+Integer.toString( (theMonster.getCharacterSheet().getDEX()/2) - 5)+")\n"+
				
				monster_l10n.getString("INT_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getINT())+" ("+Integer.toString( (theMonster.getCharacterSheet().getINT()/2) - 5)+")"+
				
				monster_l10n.getString("WIS_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getWIS())+" ("+Integer.toString( (theMonster.getCharacterSheet().getWIS()/2) - 5)+")"+
				
				monster_l10n.getString("CHA_entity")+" "+Integer.toString(theMonster.getCharacterSheet().getCHAR())+" ("+Integer.toString( (theMonster.getCharacterSheet().getCHAR()/2) - 5)+")\n"+
		        
				monster_l10n.getString("AC_entity")+" "+theMonster.getCharacterSheet().getAC()+" "+monster_l10n.getString("FORT_entity")+" "+theMonster.getCharacterSheet().getFORT()+" "+
				
				monster_l10n.getString("REF_entity")+" "+theMonster.getCharacterSheet().getREF()+" "+monster_l10n.getString("WILL_entity")+" "+theMonster.getCharacterSheet().getWILL()
				
				);
		
		secondWind_label = new JLabel(monster_l10n.getString("SecondWind_creature"));
		secondWindUsed_checkbox.setSelected(theMonster.isSecondWind());
		secondWindUsed_checkbox.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Tricky as fuck might not work;
				theMonster.setSecondWind( ((JCheckBox) ae.getSource()).isSelected() ); 
			}
		});
		currentHP_label = new JLabel(monster_l10n.getString("CurrentHP_entity"));
		currentHP_field = new JSpinner (new SpinnerNumberModel(theMonster.getCurrentHP(), -1*theMonster.getCharacterSheet().getBloodied(), theMonster.getCharacterSheet().getMaxHP(), 1) );
		currentHP_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int hp = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				theMonster.setCurrentHP(hp);
			}
		});
		((JSpinner.DefaultEditor) currentHP_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				int key = ke.getKeyChar() - '0';
				if (key < 0 || key > 9) {
					ke.consume();
				}
			}
		});
		
		bloodied_label = new JLabel(monster_l10n.getString("Bloodied_entity")+" "+theMonster.getCharacterSheet().getBloodied());
		
		health_panel.setLayout(new BoxLayout(health_panel, BoxLayout.LINE_AXIS));
		health_panel.add(currentHP_label);
		health_panel.add(currentHP_field);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(bloodied_label);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(secondWind_label);
		health_panel.add(secondWindUsed_checkbox);
		
		attack_model = new DefaultListModel();
		List<Attack> list_O_attacks = theMonster.getCharacterSheet().getAttacks();
		for (int index = 0; index < list_O_attacks.size(); index++) {
			attack_model.addElement(list_O_attacks.get(index));
		}
		attack_list = new JList(attack_model);
		attack_list.setCellRenderer( new AttackBean() );
		attack_panel = new JScrollPane(attack_list);
		attack_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		attack_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		tooltip = "<html>";
		if (theMonster.getCharacterSheet().getAcrobatics() > 0) {
			tooltip += monster_l10n.getString("ACRO_trap") + " " + theMonster.getCharacterSheet().getAcrobatics()+"<br>";
		}
		if (theMonster.getCharacterSheet().getAthletics() > 0) {
			tooltip += monster_l10n.getString("ATHL_trap") + " " + theMonster.getCharacterSheet().getAthletics()+"<br>";
		}
		if (theMonster.getCharacterSheet().getArcana() > 0) {
			tooltip += monster_l10n.getString("ARCA_trap") + " " + theMonster.getCharacterSheet().getArcana()+"<br>";
		}
		if (theMonster.getCharacterSheet().getBluff() > 0) {
			tooltip += monster_l10n.getString("BLUF_trap") + " " + theMonster.getCharacterSheet().getBluff()+"<br>";
		}
		if (theMonster.getCharacterSheet().getDiplomacy() > 0) {
			tooltip += monster_l10n.getString("DIPL_trap") + " " + theMonster.getCharacterSheet().getDiplomacy()+"<br>";
		}
		if (theMonster.getCharacterSheet().getDungeoneering() > 0) {
			tooltip += monster_l10n.getString("DUNG_trap") + " " + theMonster.getCharacterSheet().getDungeoneering()+"<br>";
		}
		if (theMonster.getCharacterSheet().getEndurance() > 0) {
			tooltip += monster_l10n.getString("ENDU_trap") + " " + theMonster.getCharacterSheet().getEndurance()+"<br>";
		}
		if (theMonster.getCharacterSheet().getHeal() > 0) {
			tooltip += monster_l10n.getString("HEAL_trap") + " " + theMonster.getCharacterSheet().getHeal()+"<br>";
		}
		if (theMonster.getCharacterSheet().getHistory() > 0) {
			tooltip += monster_l10n.getString("HIST_trap") + " " + theMonster.getCharacterSheet().getHistory()+"<br>";
		}
		if (theMonster.getCharacterSheet().getInsight() > 0) {
			tooltip += monster_l10n.getString("INSI_trap") + " " + theMonster.getCharacterSheet().getInsight()+"<br>";
		}
		if (theMonster.getCharacterSheet().getIntimidate() > 0) {
			tooltip += monster_l10n.getString("INTI_trap") + " " + theMonster.getCharacterSheet().getIntimidate()+"<br>";
		}
		if (theMonster.getCharacterSheet().getNature() > 0) {
			tooltip += monster_l10n.getString("NATU_trap") + " " + theMonster.getCharacterSheet().getNature()+"<br>";
		}
		if (theMonster.getCharacterSheet().getPerception() > 0) {
			tooltip += monster_l10n.getString("PERC_trap") + " " + theMonster.getCharacterSheet().getPerception()+"<br>";
		}
		if (theMonster.getCharacterSheet().getReligion() > 0) {
			tooltip += monster_l10n.getString("RELI_trap") + " " + theMonster.getCharacterSheet().getReligion()+"<br>";
		}
		if (theMonster.getCharacterSheet().getStealth() > 0) {
			tooltip += monster_l10n.getString("STEA_trap") + " " + theMonster.getCharacterSheet().getStealth()+"<br>";
		}
		if (theMonster.getCharacterSheet().getStreetwise() > 0) {
			tooltip += monster_l10n.getString("STRE_trap") + " " + theMonster.getCharacterSheet().getStreetwise()+"<br>";
		}
		if (theMonster.getCharacterSheet().getThievery() > 0) {
			tooltip += monster_l10n.getString("THIE_trap") + " " + theMonster.getCharacterSheet().getThievery()+"<br>";
		}
		tooltip += "<br><br>";
		tooltip += theMonster.getCharacterSheet().getRaceFeatures() + "<br>";
		tooltip += theMonster.getCharacterSheet().getMisc() + "<br><br>";
		tooltip += theMonster.getCharacterSheet().getLanguages() + "</html>";
		
		effect_model = new DefaultListModel();
		List<Effect> list_O_effects = theMonster.getEffects();
		for (int index=0; index < list_O_effects.size(); index++) {
			effect_model.addElement( list_O_effects.get(index) );
		}
		effect_list = new JList(effect_model);
		effect_list.setCellRenderer(new EffectCombat());
		effect_panel = new JScrollPane(effect_list);
		effect_panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		effect_panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		GroupLayout monster_layout = new GroupLayout(monster_panel);
		monster_layout.setHorizontalGroup( monster_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(generalInfo_label)
				.addGroup( monster_layout.createSequentialGroup()
						.addComponent(init_label)
						.addComponent(init_field)
						.addComponent(speed_label))
				.addComponent(ability_defense_label)
				.addComponent(attack_panel)
				.addComponent(effect_panel)
				);
		monster_layout.setVerticalGroup( monster_layout.createSequentialGroup()
				.addComponent(generalInfo_label)
				.addGroup( monster_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(init_label)
						.addComponent(init_field)
						.addComponent(speed_label))
				.addComponent(ability_defense_label)
				.addComponent(attack_panel)
				.addComponent(effect_panel)
				);
		monster_layout.linkSize(SwingConstants.VERTICAL, attack_panel, effect_panel);		
		monster_panel.setToolTipText(tooltip);
		monster_panel.setLayout(monster_layout);
		
		return monster_panel;
	}
	private JPanel createTrapPanel() {
		/*
		 * [Name] [Glue] Level [Level] [Role]
		 * [Type] [Glue] [XP] XP
		 * [Avoid Skill] - [Avoid DC]
		 * Trigger: [Trigger]
		 * [Attack]
		 * 
		 * Countermeasure: [Countermeasure Skill] [Countermeasure DC]
		 * [Countermeasure Description]
		 */
		ResourceBundle trap_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		JPanel trap_panel = new JPanel();
		String role = "", type = "", avoidance_skill = "", cmSkill = "";
		AttackBean atk = new AttackBean();
		atk.createPanelFrom(theTrap.getAttack());
		switch (theTrap.getType()) {
		case trap:
			type = trap_l10n.getString("Trap_trap");
		case hazard:
			type = trap_l10n.getString("Hazard_trap");
		}
		switch (theTrap.getRole()) {
		case blaster:
			role = trap_l10n.getString("Blaster_trap"); break;
		case lurker:
			role = trap_l10n.getString("Lurker_trap"); break;
		case obstacle:
			role = trap_l10n.getString("Obstacle_trap"); break;
		case warder:
			role = trap_l10n.getString("Warder_trap"); break;
		case solo:
			role = trap_l10n.getString("Solo_trap"); break;
		case elite:
			role = trap_l10n.getString("Elite_trap"); break;
		}
		switch (theTrap.getAvoidanceSkill()) {
		case acrobatics:
			avoidance_skill = trap_l10n.getString("ACRO_trap");
			break;
		case athletics:
			avoidance_skill = trap_l10n.getString("ATHL_trap");
			break;
		case arcana:
			avoidance_skill = trap_l10n.getString("ARCA_trap");
			break;
		case bluff:
			avoidance_skill = trap_l10n.getString("BLUF_trap");
			break;
		case diplomacy:
			avoidance_skill = trap_l10n.getString("DIPL_trap");
			break;
		case dungeoneering:
			avoidance_skill = trap_l10n.getString("DUNG_trap");
			break;
		case endurance:
			avoidance_skill = trap_l10n.getString("ENDU_trap");
			break;
		case heal:
			avoidance_skill = trap_l10n.getString("HEAL_trap");
			break;
		case history:
			avoidance_skill = trap_l10n.getString("HIST_trap");
			break;
		case insight:
			avoidance_skill = trap_l10n.getString("INSI_trap");
			break;
		case intimidate:
			avoidance_skill = trap_l10n.getString("INTI_trap");
			break;
		case nature:
			avoidance_skill = trap_l10n.getString("NATU_trap");
			break;
		case perception:
			avoidance_skill = trap_l10n.getString("PERC_trap");
			break;
		case religion:
			avoidance_skill = trap_l10n.getString("RELI_trap");
			break;
		case stealth:
			avoidance_skill = trap_l10n.getString("STEA_trap");
			break;
		case streetwise:
			avoidance_skill = trap_l10n.getString("STRE_trap");
			break;
		case thievery:
			avoidance_skill = trap_l10n.getString("THIE_trap");
			break;
		}

		switch (theTrap.getCounterMeasureSkill()) {
		case acrobatics:
			cmSkill = trap_l10n.getString("ACRO_trap");
			break;
		case athletics:
			cmSkill = trap_l10n.getString("ATHL_trap");
			break;
		case arcana:
			cmSkill = trap_l10n.getString("ARCA_trap");
			break;
		case bluff:
			cmSkill = trap_l10n.getString("BLUF_trap");
			break;
		case diplomacy:
			cmSkill = trap_l10n.getString("DIPL_trap");
			break;
		case dungeoneering:
			cmSkill = trap_l10n.getString("DUNG_trap");
			break;
		case endurance:
			cmSkill = trap_l10n.getString("ENDU_trap");
			break;
		case heal:
			cmSkill = trap_l10n.getString("HEAL_trap");
			break;
		case history:
			cmSkill = trap_l10n.getString("HIST_trap");
			break;
		case insight:
			cmSkill = trap_l10n.getString("INSI_trap");
			break;
		case intimidate:
			cmSkill = trap_l10n.getString("INTI_trap");
			break;
		case nature:
			cmSkill = trap_l10n.getString("NATU_trap");
			break;
		case perception:
			cmSkill = trap_l10n.getString("PERC_trap");
			break;
		case religion:
			cmSkill = trap_l10n.getString("RELI_trap");
			break;
		case stealth:
			cmSkill = trap_l10n.getString("STEA_trap");
			break;
		case streetwise:
			cmSkill = trap_l10n.getString("STRE_trap");
			break;
		case thievery:
			cmSkill = trap_l10n.getString("THIE_trap");
			break;
		}
		JEditorPane trap_text = new JEditorPane();
		trap_text.setEditable(false);
		
		trap_text.setText( theTrap.getName()+ " "+trap_l10n.getString("LVL_entity")+theTrap.getLevel()+" "+role+"\n"+
						   type+ " "+theTrap.getXp() + " XP\n"+
						   avoidance_skill+" - "+Integer.toString(theTrap.getAvoidance())+"\n"+
						   trap_l10n.getString("Trigger_trap")+" "+theTrap.getTriggers()+"\n"+
						   trap_l10n.getString("CounterText_trap")+" "+cmSkill+" - "+theTrap.getDifficultyLevel()+"\n"+
						   theTrap.getCounterMeasureDescription()
				);
		trap_panel.setLayout( new BorderLayout() );
		trap_panel.add(trap_text, BorderLayout.CENTER);
		trap_panel.add(atk.getListCellRendererComponent(null, atk.getEntity(), 0, false, false), BorderLayout.PAGE_END);
		return trap_panel;
	}

}
