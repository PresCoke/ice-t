package bean.combat;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

import controller.App_Root;
import entity.TrapHazard;

public class TrapBean implements Bean, TableCellRenderer {

	private TrapHazard theTrap;
	private AttackBean theAttackBean;
	
	public TrapBean() {
		
	}
	
	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean hasFocus) {
		/*
		 * [Name] - [Level] [Role]
		 * [Trap|Hazard] - [XP] XP
		 * [Description]
		 * Avoidance: [Avoidance Skill] - [Avoidance DC]
		 * Trigger: [Trigger]
		 * CounterMeasure: [CounterMeasure Skill] - [CounterMMeasure DC]
		 * [Counter Measure Description]
		 * 
		 * [Attack]
		 */
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle trap_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		TrapHazard aTrap = null;
		if (theValue instanceof TrapHazard) {
			aTrap = (TrapHazard) theValue;
		} else if (theValue instanceof TrapBean) {
			aTrap = (TrapHazard)( (TrapBean) theValue ).getEntity();
		} else {
			return (new JPanel());
		}
		
		String name = aTrap.getName(), level = Integer.toString(aTrap.getLevel()), role = "";
		String type = "", xp = Integer.toString(aTrap.getXp());
		String description = ""; //TODO: add Description to trap hazard
		String avoidance_skill = "", avoidance_dc = Integer.toString(aTrap.getAvoidance());
		String trigger = aTrap.getTriggers();
		
		AttackBean attack_bean = new AttackBean();
		attack_bean.createPanelFrom(aTrap.getAttack());
		JEditorPane aAttack_panel = (JEditorPane) attack_bean.getListCellRendererComponent(null, aTrap.getAttack(), 
																						   0, false, false);
		String cmSkill = ""; String cmDC = Integer.toString(aTrap.getDifficultyLevel());
		String cmDescription = aTrap.getCounterMeasureDescription();
		
		switch (aTrap.getRole()) {
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
		
		switch (aTrap.getType()) {
		case trap:
			type = trap_l10n.getString("Trap_trap"); break;
		case hazard:
			type = trap_l10n.getString("Hazard_trap"); break;
		}
		
		switch (aTrap.getAvoidanceSkill()) {
		case acrobatics:
			avoidance_skill = trap_l10n.getString("ACRO_trap"); break;
		case athletics:
			avoidance_skill = trap_l10n.getString("ATHL_trap"); break;
		case arcana:
			avoidance_skill = trap_l10n.getString("ARCA_trap"); break;
		case bluff:
			avoidance_skill = trap_l10n.getString("BLUF_trap"); break;
		case diplomacy:
			avoidance_skill = trap_l10n.getString("DIPL_trap"); break;
		case dungeoneering:
			avoidance_skill = trap_l10n.getString("DUNG_trap"); break;
		case endurance:
			avoidance_skill = trap_l10n.getString("ENDU_trap"); break;
		case heal:
			avoidance_skill = trap_l10n.getString("HEAL_trap"); break;
		case history:
			avoidance_skill = trap_l10n.getString("HIST_trap"); break;
		case insight:
			avoidance_skill = trap_l10n.getString("INSI_trap"); break;
		case intimidate:
			avoidance_skill = trap_l10n.getString("INTI_trap"); break;
		case nature:
			avoidance_skill = trap_l10n.getString("NATU_trap"); break;
		case perception:
			avoidance_skill = trap_l10n.getString("PERC_trap"); break;
		case religion:
			avoidance_skill = trap_l10n.getString("RELI_trap"); break;
		case stealth:
			avoidance_skill = trap_l10n.getString("STEA_trap"); break;
		case streetwise:
			avoidance_skill = trap_l10n.getString("STRE_trap"); break;
		case thievery:
			avoidance_skill = trap_l10n.getString("THIE_trap"); break;
		}
		
		switch (aTrap.getCounterMeasureSkill()) {
		case acrobatics:
			cmSkill = trap_l10n.getString("ACRO_trap"); break;
		case athletics:
			cmSkill = trap_l10n.getString("ATHL_trap"); break;
		case arcana:
			cmSkill = trap_l10n.getString("ARCA_trap"); break;
		case bluff:
			cmSkill = trap_l10n.getString("BLUF_trap"); break;
		case diplomacy:
			cmSkill = trap_l10n.getString("DIPL_trap"); break;
		case dungeoneering:
			cmSkill = trap_l10n.getString("DUNG_trap"); break;
		case endurance:
			cmSkill = trap_l10n.getString("ENDU_trap"); break;
		case heal:
			cmSkill = trap_l10n.getString("HEAL_trap"); break;
		case history:
			cmSkill = trap_l10n.getString("HIST_trap"); break;
		case insight:
			cmSkill = trap_l10n.getString("INSI_trap"); break;
		case intimidate:
			cmSkill = trap_l10n.getString("INTI_trap"); break;
		case nature:
			cmSkill = trap_l10n.getString("NATU_trap"); break;
		case perception:
			cmSkill = trap_l10n.getString("PERC_trap"); break;
		case religion:
			cmSkill = trap_l10n.getString("RELI_trap"); break;
		case stealth:
			cmSkill = trap_l10n.getString("STEA_trap"); break;
		case streetwise:
			cmSkill = trap_l10n.getString("STRE_trap"); break;
		case thievery:
			cmSkill = trap_l10n.getString("THIE_trap"); break;
		}
		
		JEditorPane trap_text = new JEditorPane();
		trap_text.setText(
				name+" - "+level+" "+role+"\n"+
				type+" - "+xp+" XP\n"+
				description+"\n"+
				"Avoidance: "+avoidance_skill+" "+avoidance_dc+"/n"+
				"Trigger: "+trigger+"\n"+
				"Counter Measure: "+cmSkill+" "+cmDC+"\n"+
				cmDescription
				);
		JPanel theFinalPanel = new JPanel();
		theFinalPanel.setLayout( new BoxLayout(theFinalPanel, BoxLayout.PAGE_AXIS) );
		theFinalPanel.add(trap_text);
		theFinalPanel.add(aAttack_panel);
		if (isSelected) {
			theFinalPanel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		return theFinalPanel;
	}
	
	public Component getTableCellRendererComponent(JTable theTable, Object theValue,
			boolean isSelected, boolean hasFocus, int row, int column) {
		/*
		 * [Name] - [Level] [Role]
		 * [Trap|Hazard] - [XP] XP
		 * [Description]
		 * Avoidance: [Avoidance Skill] - [Avoidance DC]
		 * Trigger: [Trigger]
		 * CounterMeasure: [CounterMeasure Skill] - [CounterMMeasure DC]
		 * [Counter Measure Description]
		 * 
		 * [Attack]
		 */
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle trap_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		TrapHazard aTrap = null;
		if (theValue instanceof TrapHazard) {
			aTrap = (TrapHazard) theValue;
		} else if (theValue instanceof TrapBean) {
			aTrap = (TrapHazard)( (TrapBean) theValue ).getEntity();
		} else {
			return (new JPanel());
		}
		
		String name = aTrap.getName(), level = Integer.toString(aTrap.getLevel()), role = "";
		String type = "", xp = Integer.toString(aTrap.getXp());
		String description = ""; //TODO: add Description to trap hazard
		String avoidance_skill = "", avoidance_dc = Integer.toString(aTrap.getAvoidance());
		String trigger = aTrap.getTriggers();
		
		AttackBean attack_bean = new AttackBean();
		attack_bean.createPanelFrom(aTrap.getAttack());
		JEditorPane aAttack_panel = (JEditorPane) attack_bean.getListCellRendererComponent(null, aTrap.getAttack(), 
																						   0, false, false);
		String cmSkill = ""; String cmDC = Integer.toString(aTrap.getDifficultyLevel());
		String cmDescription = aTrap.getCounterMeasureDescription();
		
		switch (aTrap.getRole()) {
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
		
		switch (aTrap.getType()) {
		case trap:
			type = trap_l10n.getString("Trap_trap"); break;
		case hazard:
			type = trap_l10n.getString("Hazard_trap"); break;
		}
		
		switch (aTrap.getAvoidanceSkill()) {
		case acrobatics:
			avoidance_skill = trap_l10n.getString("ACRO_trap"); break;
		case athletics:
			avoidance_skill = trap_l10n.getString("ATHL_trap"); break;
		case arcana:
			avoidance_skill = trap_l10n.getString("ARCA_trap"); break;
		case bluff:
			avoidance_skill = trap_l10n.getString("BLUF_trap"); break;
		case diplomacy:
			avoidance_skill = trap_l10n.getString("DIPL_trap"); break;
		case dungeoneering:
			avoidance_skill = trap_l10n.getString("DUNG_trap"); break;
		case endurance:
			avoidance_skill = trap_l10n.getString("ENDU_trap"); break;
		case heal:
			avoidance_skill = trap_l10n.getString("HEAL_trap"); break;
		case history:
			avoidance_skill = trap_l10n.getString("HIST_trap"); break;
		case insight:
			avoidance_skill = trap_l10n.getString("INSI_trap"); break;
		case intimidate:
			avoidance_skill = trap_l10n.getString("INTI_trap"); break;
		case nature:
			avoidance_skill = trap_l10n.getString("NATU_trap"); break;
		case perception:
			avoidance_skill = trap_l10n.getString("PERC_trap"); break;
		case religion:
			avoidance_skill = trap_l10n.getString("RELI_trap"); break;
		case stealth:
			avoidance_skill = trap_l10n.getString("STEA_trap"); break;
		case streetwise:
			avoidance_skill = trap_l10n.getString("STRE_trap"); break;
		case thievery:
			avoidance_skill = trap_l10n.getString("THIE_trap"); break;
		}
		
		switch (aTrap.getCounterMeasureSkill()) {
		case acrobatics:
			cmSkill = trap_l10n.getString("ACRO_trap"); break;
		case athletics:
			cmSkill = trap_l10n.getString("ATHL_trap"); break;
		case arcana:
			cmSkill = trap_l10n.getString("ARCA_trap"); break;
		case bluff:
			cmSkill = trap_l10n.getString("BLUF_trap"); break;
		case diplomacy:
			cmSkill = trap_l10n.getString("DIPL_trap"); break;
		case dungeoneering:
			cmSkill = trap_l10n.getString("DUNG_trap"); break;
		case endurance:
			cmSkill = trap_l10n.getString("ENDU_trap"); break;
		case heal:
			cmSkill = trap_l10n.getString("HEAL_trap"); break;
		case history:
			cmSkill = trap_l10n.getString("HIST_trap"); break;
		case insight:
			cmSkill = trap_l10n.getString("INSI_trap"); break;
		case intimidate:
			cmSkill = trap_l10n.getString("INTI_trap"); break;
		case nature:
			cmSkill = trap_l10n.getString("NATU_trap"); break;
		case perception:
			cmSkill = trap_l10n.getString("PERC_trap"); break;
		case religion:
			cmSkill = trap_l10n.getString("RELI_trap"); break;
		case stealth:
			cmSkill = trap_l10n.getString("STEA_trap"); break;
		case streetwise:
			cmSkill = trap_l10n.getString("STRE_trap"); break;
		case thievery:
			cmSkill = trap_l10n.getString("THIE_trap"); break;
		}
		
		JEditorPane trap_text = new JEditorPane();
		trap_text.setText(
				name+" - "+level+" "+role+"\n"+
				type+" - "+xp+" XP\n"+
				description+"\n"+
				"Avoidance: "+avoidance_skill+" "+avoidance_dc+"/n"+
				"Trigger: "+trigger+"\n"+
				"Counter Measure: "+cmSkill+" "+cmDC+"\n"+
				cmDescription
				);
		JPanel theFinalPanel = new JPanel();
		theFinalPanel.setLayout( new BoxLayout(theFinalPanel, BoxLayout.PAGE_AXIS) );
		theFinalPanel.add(trap_text);
		theFinalPanel.add(aAttack_panel);
		if (isSelected) {
			theFinalPanel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		return theFinalPanel;
	}
	
	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof TrapHazard) {
			theTrap = (TrapHazard) thisEntity;
			theAttackBean = new AttackBean();
			theAttackBean.createPanelFrom(theTrap.getAttack());
		} else {
			theTrap = new TrapHazard();
			theAttackBean = new AttackBean();
		}
		
	}

	public Object getEntity() {
		return theTrap;
	}
	
}
