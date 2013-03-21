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
	private TrapHazard theTrap; // can be null;
	private boolean isCurrentCreature;
	
	public CreatureBeanShallow() {
		
	}
	
	//remove object instantiation from within render call and instead put it somewhere else; create globals etc...
	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean hasFocus) {
		
		JPanel theFinalPane = new JPanel();
		theFinalPane.setBorder( BorderFactory.createEtchedBorder() );
		boolean isCurrentCreature = false;
		if (theValue instanceof TrapHazard) {
			theFinalPane = new JPanel();
			 //theFinalPane = createTrapPanel((TrapHazard) theValue);
		} else if (theValue instanceof TrapBean) {
			theFinalPane = new JPanel();
			//theFinalPane = createTrapPanel((TrapHazard)( (TrapBean) theValue ).getEntity());
		} else if (theValue instanceof Player) {
			CreatureBeanShallow aBean = new CreatureBeanShallow();
			aBean.createPanelFrom(theValue);
			theFinalPane = aBean.createPanel(isSelected);
		} else if (theValue instanceof Monster) {
			CreatureBeanShallow aBean = new CreatureBeanShallow();
			aBean.createPanelFrom(theValue);
			theFinalPane = aBean.createPanel(isSelected);
		} else if (theValue instanceof CreatureBeanShallow) {
			theFinalPane = ((CreatureBeanShallow) theValue).createPanel(isSelected);
		} else {
			return (new JPanel());
		}
		
		return theFinalPane;
	}
	
	private JPanel createPanel(boolean isSelected) {
		if (thePlayer != null && theMonster == null && theTrap == null) {
			return createPlayerPanel(isSelected, isCurrentCreature);
		} else if (thePlayer == null && theMonster != null  && theTrap == null) {
			return createMonsterPanel(isSelected, isCurrentCreature);
		} else if (thePlayer == null && theMonster == null  && theTrap != null) {
			return createTrapPanel(isSelected);
		} else {
			return new JPanel();
		}
	}
	
	private JPanel createMonsterPanel(boolean isSelected, boolean isCurrentCreature2) {
		/*
		 * [Name] - [LVL] [XP] XP
		 * Current HP: [HP] Bloodied: [isBloodied] SecondWinded: [SecondWind]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);

		CharacterSheet theSheet = theMonster.getCharacterSheet();
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), exp = Integer.toString(theSheet.getXP());
		String bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed());
		
		JLabel isSecondWindUsed_label = new JLabel(creat_l10n.getString("SecondWind_creature"));
		JCheckBox isSecondWindUsed_box = new JCheckBox();
		isSecondWindUsed_box.setSelected(theMonster.isSecondWind());
		
		JTextPane currentHP_field = new JTextPane();
		currentHP_field.setBorder( BorderFactory.createLoweredBevelBorder() );
		currentHP_field.setEditable(false);
		currentHP_field.setText( Integer.toString(theMonster.getCurrentHP()) );
		
		JTextPane initiative_field = new JTextPane();
		initiative_field.setBorder( BorderFactory.createLoweredBevelBorder() );
		initiative_field.setEditable(false);
		initiative_field.setText( Integer.toString(theMonster.getInitiative()) );

		JPanel aCreature_panel = new JPanel();
		aCreature_panel.setLayout( new BoxLayout(aCreature_panel, BoxLayout.PAGE_AXIS) );
		
		JLabel speed_label = new JLabel(" "+creat_l10n.getString("Speed_entity") + " "+ speed); 
		JLabel currentHP_label =  new JLabel(creat_l10n.getString("CurrentHP_entity")+" "); 
		JLabel bloodied_label = new JLabel(" "+creat_l10n.getString("Bloodied_entity") + " "+ bloodied); 
		JLabel initiative_label =  new JLabel(creat_l10n.getString("Init_entity") + " ");
		
		JPanel header_panel = new JPanel();
		header_panel.setLayout( new BoxLayout(header_panel, BoxLayout.LINE_AXIS));
		header_panel.add(new JLabel(name));
		header_panel.add(Box.createHorizontalGlue());
		header_panel.add( new JLabel(creat_l10n.getString("LVL_entity") + " " + level +" "+ exp + " XP") );
		
		JPanel hp_panel = new JPanel();
		hp_panel.setLayout( new BoxLayout(hp_panel, BoxLayout.LINE_AXIS));
		hp_panel.add(Box.createHorizontalGlue());
		hp_panel.add(bloodied_label);
		
		JPanel init_panel = new JPanel();
		init_panel.setLayout( new BoxLayout(init_panel, BoxLayout.LINE_AXIS));
		init_panel.add(Box.createHorizontalGlue());
		init_panel.add(speed_label);
		
		GroupLayout aCreature_layout = new GroupLayout(aCreature_panel);
		aCreature_layout.setHorizontalGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
				.addComponent(header_panel)
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(currentHP_label)
						.addComponent(currentHP_field)
						.addComponent(hp_panel))
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(isSecondWindUsed_label)
						.addComponent(isSecondWindUsed_box))
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(initiative_label)
						.addComponent(initiative_field)
						.addComponent(init_panel))	
				);
		aCreature_layout.setVerticalGroup( aCreature_layout.createSequentialGroup()
				.addComponent(header_panel)
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(currentHP_label)
						.addComponent(currentHP_field)
						.addComponent(hp_panel))
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(isSecondWindUsed_label)
						.addComponent(isSecondWindUsed_box))
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(initiative_label)
						.addComponent(initiative_field)
						.addComponent(init_panel))						
				);
		aCreature_layout.linkSize(currentHP_field, initiative_field);
		aCreature_panel.setLayout(aCreature_layout);
		
		
		if (isSelected && isCurrentCreature) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(new Color(0, 178, 238));
			header_panel.setBackground(new Color(0, 178, 238));
			hp_panel.setBackground(new Color(0, 178, 238));
			init_panel.setBackground(new Color(0, 178, 238));
		} else if (isSelected) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			header_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			hp_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			init_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		} else if (isCurrentCreature) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(new Color(0, 238, 0));
			header_panel.setBackground(new Color(0, 238, 0));
			hp_panel.setBackground(new Color(0, 238, 0));
			init_panel.setBackground(new Color(0, 238, 0));
		} else {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), 
							BorderFactory.createEmptyBorder(8, 8, 8, 8))
					)
				);
			aCreature_panel.setBackground(Color.WHITE);
			header_panel.setBackground(Color.WHITE);
			hp_panel.setBackground(Color.WHITE);
			init_panel.setBackground(Color.WHITE);
		}
		
		return aCreature_panel;
	}

	private JPanel createPlayerPanel(boolean isSelected, boolean isCurrentCreature2) {
		/*
		 * [Name] - [LVL] [XP] XP
		 * Current HP: [HP] Bloodied: [isBloodied] SecondWinded: [SecondWind]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);

		CharacterSheet theSheet = thePlayer.getCharacterSheet();
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), exp = Integer.toString(theSheet.getXP());
		String bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed());
		
		JLabel isSecondWindUsed_label = new JLabel(creat_l10n.getString("SecondWind_creature"));
		JCheckBox isSecondWindUsed_box = new JCheckBox();
		isSecondWindUsed_box.setSelected(thePlayer.isSecondWind());
		
		JTextPane currentHP_field = new JTextPane();
		currentHP_field.setBorder( BorderFactory.createLoweredBevelBorder() );
		currentHP_field.setEditable(false);
		currentHP_field.setText( Integer.toString(thePlayer.getCurrentHP()) );
		
		JLabel initiative_label = new JLabel(creat_l10n.getString("Init_entity") + " ");
		JTextPane initiative_field = new JTextPane();
		initiative_field.setBorder( BorderFactory.createLoweredBevelBorder() );
		initiative_field.setEditable(false);
		initiative_field.setText( Integer.toString(thePlayer.getInitiative()) );
		
		JPanel aCreature_panel = new JPanel();
		
		JLabel speed_label = new JLabel(" "+creat_l10n.getString("Speed_entity") + " "+ speed);
		JLabel bloodied_label = new JLabel(" "+creat_l10n.getString("Bloodied_entity") + " "+ bloodied);
		JLabel currentHP_label =  new JLabel(creat_l10n.getString("CurrentHP_entity")+" "); 
		
		JPanel header_panel = new JPanel();
		header_panel.setLayout( new BoxLayout(header_panel, BoxLayout.LINE_AXIS));
		header_panel.add(new JLabel(name));
		header_panel.add(Box.createHorizontalGlue());
		header_panel.add( new JLabel(creat_l10n.getString("LVL_entity") + " " + level +" "+ exp + " XP") );
		
		JPanel hp_panel = new JPanel();
		hp_panel.setLayout( new BoxLayout(hp_panel, BoxLayout.LINE_AXIS));
		hp_panel.add(Box.createHorizontalGlue());
		hp_panel.add(bloodied_label);
		
		JPanel init_panel = new JPanel();
		init_panel.setLayout( new BoxLayout(init_panel, BoxLayout.LINE_AXIS));
		init_panel.add(Box.createHorizontalGlue());
		init_panel.add(speed_label);
		
		GroupLayout aCreature_layout = new GroupLayout(aCreature_panel);
		aCreature_layout.setHorizontalGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.LEADING) 
				.addComponent(header_panel)
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(currentHP_label)
						.addComponent(currentHP_field)
						.addComponent(hp_panel))
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(isSecondWindUsed_label)
						.addComponent(isSecondWindUsed_box))
				.addGroup( aCreature_layout.createSequentialGroup()
						.addComponent(initiative_label)
						.addComponent(initiative_field)
						.addComponent(init_panel))	
				);
		aCreature_layout.setVerticalGroup( aCreature_layout.createSequentialGroup()
				.addComponent(header_panel)
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(currentHP_label)
						.addComponent(currentHP_field)
						.addComponent(hp_panel))
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(isSecondWindUsed_label)
						.addComponent(isSecondWindUsed_box))
				.addGroup( aCreature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(initiative_label)
						.addComponent(initiative_field)
						.addComponent(init_panel))						
				);
		aCreature_layout.linkSize(currentHP_field, initiative_field);
		aCreature_panel.setLayout(aCreature_layout);
		
		if (isSelected && isCurrentCreature) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(new Color(0, 178, 238));
			header_panel.setBackground(new Color(0, 178, 238));
			hp_panel.setBackground(new Color(0, 178, 238));
			init_panel.setBackground(new Color(0, 178, 238));
		} else if (isSelected) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			header_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			hp_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			init_panel.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		} else if (isCurrentCreature) {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.WHITE), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			
			aCreature_panel.setBackground(new Color(0, 238, 0));
			header_panel.setBackground(new Color(0, 238, 0));
			hp_panel.setBackground(new Color(0, 238, 0));
			init_panel.setBackground(new Color(0, 238, 0));
		} else {
			aCreature_panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), 
							BorderFactory.createEmptyBorder(8, 8, 8, 8))
					)
				);
			aCreature_panel.setBackground(Color.WHITE);
			header_panel.setBackground(Color.WHITE);
			hp_panel.setBackground(Color.WHITE);
			init_panel.setBackground(Color.WHITE);
		}
			
		return aCreature_panel;
	}

	private JPanel createTrapPanel(boolean isSelected) {
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

		TrapHazard aTrap = theTrap;

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
		JPanel trap_text_1 = new JPanel();
		trap_text_1.setLayout( new BoxLayout(trap_text_1, BoxLayout.LINE_AXIS) );
		trap_text_1.add(new JLabel(name) );
		trap_text_1.add(Box.createHorizontalGlue());
		trap_text_1.add( new JLabel(trap_l10n.getString("LVL_entity") + " " + level + " " + role) );
		
		JPanel trap_text_2 = new JPanel();
		trap_text_2.setLayout( new BoxLayout(trap_text_2, BoxLayout.LINE_AXIS) );
		trap_text_2.add(new JLabel(type) );
		trap_text_2.add(Box.createHorizontalGlue());
		trap_text_2.add( new JLabel(xp + " XP") );
		
		JPanel trap_text = new JPanel();
		trap_text.setLayout( new BoxLayout(trap_text, BoxLayout.PAGE_AXIS) );
		trap_text.add(trap_text_1);
		trap_text.add(trap_text_2);
		trap_text.add(new JLabel(description));
		trap_text.add(Box.createGlue());
		
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
		
		if (isSelected) {
			trap_text.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(5, 5, 5, 5, Color.BLACK), 
							BorderFactory.createEmptyBorder(5, 5, 5, 5))
					)
				);
			trap_text_1.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			trap_text_2.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
			trap_text.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		} else {
			trap_text.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.WHITE),
					BorderFactory.createCompoundBorder(
							BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK), 
							BorderFactory.createEmptyBorder(8, 8, 8, 8))
					)
				);
			trap_text_1.setBackground(Color.WHITE);
			trap_text_2.setBackground(Color.WHITE);
			trap_text.setBackground(Color.WHITE);
		}
		
		return trap_text;
	}
	
	public void createPanelFrom(Object thisEntity) {
		CharacterSheet theSheet;
		if (thisEntity instanceof Player) {
			thePlayer = (Player) thisEntity;
			theMonster = null;
			theTrap = null;
			theSheet = thePlayer.getCharacterSheet();
			//attacks = theSheet.getAttacks();
		} else if (thisEntity instanceof Monster) {
			theMonster = (Monster) thisEntity;
			thePlayer = null;
			theTrap = null;
			theSheet = theMonster.getCharacterSheet();
			//attacks = theSheet.getAttacks();
		} else if (thisEntity instanceof TrapHazard) {
			theMonster = null;
			thePlayer = null;
			theTrap = (TrapHazard) thisEntity;
			//attacks = new ArrayList<Attack>();
			//theSheet = new CharacterSheet();
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

	public void setIsCurrentCreature(boolean b) {
		this.isCurrentCreature = b;
	}

	public boolean isCurrentCreature() {
		// TODO Auto-generated method stub
		return isCurrentCreature;
	}

}
