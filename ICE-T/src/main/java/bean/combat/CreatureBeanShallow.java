package bean.combat;

import java.util.ResourceBundle;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
import entity.*;

import controller.App_Root;

public class CreatureBeanShallow extends Bean {
	
	//TODO: add initiative JSpinner!
	
	private Player thePlayer; //can be null;
	private Monster theMonster; //can be null;
	private boolean isCurrentCreature;
	
	public CreatureBeanShallow() {
		
	}

	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean hasFocus) {
		
		JPanel theFinalPane = new JPanel();
		boolean isCurrentCreature = false;
		if (theValue instanceof TrapHazard) {
			 theFinalPane = createTrapPanel((TrapHazard) theValue);
		} else if (theValue instanceof TrapBean) {
			theFinalPane = createTrapPanel((TrapHazard)( (TrapBean) theValue ).getEntity());
		} else if (theValue instanceof Player) {
			theFinalPane = createPlayerPanel( ((Player) theValue).getCharacterSheet(), (Player) theValue );
		} else if (theValue instanceof Monster) {
			theFinalPane = createMonsterPanel( ((Monster) theValue).getCharacterSheet(), (Monster) theValue );
		} else if (theValue instanceof CreatureBeanShallow) {
			isCurrentCreature = ((CreatureBeanShallow) theValue).isCurrentCreature;
			Object aCreature = ( (CreatureBeanShallow) theValue ).getEntity();
			if (aCreature instanceof Player) {
				theFinalPane = createPlayerPanel( ((Player) aCreature).getCharacterSheet(), (Player) aCreature );
			} else if (aCreature instanceof Monster) {
				theFinalPane = createMonsterPanel( ((Monster) aCreature).getCharacterSheet(), (Monster) aCreature );
			}
		} else {
			return (new JPanel());
		}
		if (isCurrentCreature) {
			theFinalPane.setBackground(Color.GREEN);
		}
		if (isSelected) {
			theFinalPane.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		}
		
		return theFinalPane;
	}
	
	private JPanel createMonsterPanel(CharacterSheet characterSheet, Monster theValue) {
		/*
		 * [Name] - [LVL] [XP] XP
		 * Current HP: [HP] Bloodied: [isBloodied] SecondWinded: [SecondWind]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);

		CharacterSheet theSheet = characterSheet;
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), exp = Integer.toString(theSheet.getXP());
		String bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed()), initiative = Integer.toString(theValue.getInitiative());
		
		JLabel isSecondWindUsed_label = new JLabel(creat_l10n.getString("SecondWind_creature"));
		JCheckBox isSecondWindUsed_box = new JCheckBox();
		isSecondWindUsed_box.setSelected(theValue.isSecondWind());
		isSecondWindUsed_box.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Tricky as fuck might not work;
				thePlayer.setSecondWind( ((JCheckBox) ae.getSource()).isSelected() ); 
			}
		});
		JSpinner currentHP_field = new JSpinner (new SpinnerNumberModel(theValue.getCurrentHP(), -1*theSheet.getBloodied(), theSheet.getMaxHP(), 1) );
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
		currentHP_field.setValue(theValue.getCurrentHP());
		JSpinner initiative_field = new JSpinner( new SpinnerNumberModel(theValue.getInitiative(), 0, 100, 1));
		initiative_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int init = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				theMonster.setInitiative(init);
			}
		});
		((JSpinner.DefaultEditor) initiative_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
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
		initiative_field.setValue(theValue.getCurrentHP());

		JPanel aCreature_panel = new JPanel();
		aCreature_panel.setLayout( new BoxLayout(aCreature_panel, BoxLayout.PAGE_AXIS) );
		aCreature_panel.add(new JLabel(
				name + " - " + creat_l10n.getString("LVL_entity") + " " + level + exp + " XP\n"
				));
		
		JPanel health_panel = new JPanel();
		health_panel.setLayout(new BoxLayout(health_panel, BoxLayout.LINE_AXIS));
		health_panel.add( new JLabel(creat_l10n.getString("CurrentHP_entity")) );
		health_panel.add(currentHP_field);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(new JLabel(creat_l10n.getString("Bloodied_entity") + " "+ bloodied));
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(isSecondWindUsed_label);
		health_panel.add(isSecondWindUsed_box);
		
		JPanel extra_panel = new JPanel();
		extra_panel.setLayout( new BoxLayout(extra_panel, BoxLayout.LINE_AXIS));
		extra_panel.add( new JLabel(creat_l10n.getString("Init_entity") + " ") );
		extra_panel.add(initiative_field);
		extra_panel.add(Box.createHorizontalGlue());
		extra_panel.add( new JLabel(creat_l10n.getString("Speed_entity") + " "+ speed) );
		
		aCreature_panel.add(health_panel);
		aCreature_panel.add(extra_panel);
		
		return aCreature_panel;
	}

	private JPanel createPlayerPanel(CharacterSheet characterSheet, Player theValue) {
		/*
		 * [Name] - [LVL] [XP] XP
		 * Current HP: [HP] Bloodied: [isBloodied] SecondWinded: [SecondWind]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);

		CharacterSheet theSheet = characterSheet;
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), exp = Integer.toString(theSheet.getXP());
		String bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed()), initiative = Integer.toString(theValue.getInitiative());
		
		JLabel isSecondWindUsed_label = new JLabel(creat_l10n.getString("SecondWind_creature"));
		JCheckBox isSecondWindUsed_box = new JCheckBox();
		isSecondWindUsed_box.setSelected(theValue.isSecondWind());
		isSecondWindUsed_box.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//Tricky as fuck might not work;
				thePlayer.setSecondWind( ((JCheckBox) ae.getSource()).isSelected() ); 
			}
		});
		JSpinner currentHP_field = new JSpinner (new SpinnerNumberModel(theValue.getCurrentHP(), -1*theSheet.getBloodied(), theSheet.getMaxHP(), 1) );
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
		currentHP_field.setValue(theValue.getCurrentHP());
		JSpinner initiative_field = new JSpinner( new SpinnerNumberModel(theValue.getInitiative(), 0, 100, 1));
		initiative_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int init = (Integer) ((JSpinner) arg0.getSource()).getValue();
				//TODO: tricky as fuck may not work!!!
				thePlayer.setInitiative(init);
			}
		});
		((JSpinner.DefaultEditor) initiative_field.getEditor()).getTextField().addKeyListener( new KeyListener() {
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
		initiative_field.setValue(theValue.getCurrentHP());

		JPanel aCreature_panel = new JPanel();
		aCreature_panel.setLayout( new BoxLayout(aCreature_panel, BoxLayout.PAGE_AXIS) );
		aCreature_panel.add(new JLabel(
				name + " - " + creat_l10n.getString("LVL_entity") + " " + level + exp + " XP\n"
				));
		
		JPanel health_panel = new JPanel();
		health_panel.setLayout(new BoxLayout(health_panel, BoxLayout.LINE_AXIS));
		health_panel.add( new JLabel(creat_l10n.getString("CurrentHP_entity")) );
		health_panel.add(currentHP_field);
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(new JLabel(creat_l10n.getString("Bloodied_entity") + " "+ bloodied));
		health_panel.add(Box.createHorizontalGlue());
		health_panel.add(isSecondWindUsed_label);
		health_panel.add(isSecondWindUsed_box);
		
		JPanel extra_panel = new JPanel();
		extra_panel.setLayout( new BoxLayout(extra_panel, BoxLayout.LINE_AXIS));
		extra_panel.add( new JLabel(creat_l10n.getString("Init_entity") + " ") );
		extra_panel.add(initiative_field);
		extra_panel.add(Box.createHorizontalGlue());
		extra_panel.add( new JLabel(creat_l10n.getString("Speed_entity") + " "+ speed) );
		
		aCreature_panel.add(health_panel);
		aCreature_panel.add(extra_panel);
		
		return aCreature_panel;
	}

	private JPanel createTrapPanel(TrapHazard theValue) {
		/*
		 * [Name] - [Level] [Role] [Trap|Hazard] - [XP] XP [Description]
		 * Avoidance: [Avoidance Skill] - [Avoidance DC] Trigger: [Trigger]
		 * CounterMeasure: [CounterMeasure Skill] - [CounterMMeasure DC]
		 * [Counter Measure Description]
		 * 
		 * [Attack]
		 */
		ResourceBundle trap_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);

		TrapHazard aTrap = theValue;

		String name = aTrap.getName(), level = Integer.toString(aTrap
				.getLevel()), role = "";
		String type = "", xp = Integer.toString(aTrap.getXp());
		String description = ""; // TODO: add Description to trap hazard
		String avoidance_skill = "", avoidance_dc = Integer.toString(aTrap
				.getAvoidance());
		String trigger = aTrap.getTriggers();

		AttackBean attack_bean = new AttackBean();
		attack_bean.createPanelFrom(aTrap.getAttack());
		String cmSkill = "";
		String cmDC = Integer.toString(aTrap.getDifficultyLevel());
		String cmDescription = aTrap.getCounterMeasureDescription();

		switch (aTrap.getRole()) {
		case blaster:
			role = trap_l10n.getString("Blaster_trap");
			break;
		case lurker:
			role = trap_l10n.getString("Lurker_trap");
			break;
		case obstacle:
			role = trap_l10n.getString("Obstacle_trap");
			break;
		case warder:
			role = trap_l10n.getString("Warder_trap");
			break;
		case solo:
			role = trap_l10n.getString("Solo_trap");
			break;
		case elite:
			role = trap_l10n.getString("Elite_trap");
			break;
		}

		switch (aTrap.getType()) {
		case trap:
			type = trap_l10n.getString("Trap_trap");
			break;
		case hazard:
			type = trap_l10n.getString("Hazard_trap");
			break;
		}

		switch (aTrap.getAvoidanceSkill()) {
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

		switch (aTrap.getCounterMeasureSkill()) {
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

		JPanel trap_text = new JPanel();
		trap_text.add( new JLabel(name + " - " + level + " " + role + "\n" + type
				+ " - " + xp + " XP\n" + description + "\n") );
		String attack_summary = "";
		Attack attacks = aTrap.getAttack();
		attack_summary = attacks.getAttackName();
		switch (attacks.getAction()) {
		case standard:
			attack_summary += trap_l10n.getString("Standard_attack");
			break;
		case move:
			attack_summary += trap_l10n.getString("Move_attack");
			break;
		case minor:
			attack_summary += trap_l10n.getString("Minor_attack");
			break;
		case free:
			attack_summary += trap_l10n.getString("Free_attack");
			break;
		}
		attack_summary += "; ";
		switch (attacks.getUseType()) {
		case atWill:
			attack_summary += trap_l10n.getString("AtWill_attack");
			break;
		case encounter:
			attack_summary += trap_l10n.getString("Encounter_attack");
			break;
		case daily:
			attack_summary += trap_l10n.getString("Daily_attack");
			break;
		case recharge:
			attack_summary += trap_l10n.getString("Recharge_attack");
			break;
		}
		attack_summary += ") * " + attacks.getAccessories() + "\n";
		switch (attacks.getAbility()) {// TODO: makes absolutley no sense for a
										// trap!?
		case STR:
			attack_summary += trap_l10n.getString("STR_attack");
			break;
		case CON:
			attack_summary += trap_l10n.getString("CON_attack");
			break;
		case INT:
			attack_summary += trap_l10n.getString("INT_attack");
			break;
		case DEX:
			attack_summary += trap_l10n.getString("DEX_attack");
			break;
		case WIS:
			attack_summary += trap_l10n.getString("WIS_attack");
			break;
		case CHAR:
			attack_summary += trap_l10n.getString("CHA_attack");
			break;
		}
		attack_summary += " vs. ";
		switch (attacks.getDefense()) {
		case AC:
			attack_summary += trap_l10n.getString("AC_attack");
			break;
		case REF:
			attack_summary += trap_l10n.getString("REF_attack");
			break;
		case FORT:
			attack_summary += trap_l10n.getString("FORT_attack");
			break;
		case WILL:
			attack_summary += trap_l10n.getString("WILL_attack");
			break;
		}
		attack_summary += "; " + attacks.getHit();
		trap_text.setToolTipText("<html>"
				+ trap_l10n.getString("AvoidSkill_trap") + avoidance_skill
				+ " " + avoidance_dc + "<br>"
				+ trap_l10n.getString("Trigger_trap") + trigger + "<br>"
				+ trap_l10n.getString("CounterText_trap") + cmSkill + " "
				+ cmDC + "<br>" + cmDescription + "<br>" + attack_summary
				+ "</html");
		return trap_text;
	}
	
	public void createPanelFrom(Object thisEntity) {
		CharacterSheet theSheet;
		if (thisEntity instanceof Player) {
			thePlayer = (Player) thisEntity;
			theMonster = null;
			theSheet = thePlayer.getCharacterSheet();
			//attacks = theSheet.getAttacks();
		} else if (thisEntity instanceof Monster) {
			theMonster = (Monster) thisEntity;
			thePlayer = null;
			theSheet = theMonster.getCharacterSheet();
			//attacks = theSheet.getAttacks();
		} else {
			theMonster = new Monster();
			thePlayer = null;
			//attacks = new ArrayList<Attack>();
			theSheet = new CharacterSheet();
		}
		

	}

	public Object getEntity() {
		if (thePlayer != null) {
			return thePlayer;
		} else if (theMonster != null) {
			return theMonster;
		} else {
			return null;
		}
	}

	public void setIsCurrentCreature(boolean b) {
		this.isCurrentCreature = b;
	}

}
