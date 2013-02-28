package bean.combat;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

import controller.App_Root;
import entity.Attack;
import entity.TrapHazard;

public class TrapBean extends Bean implements TableCellRenderer {

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
				description+"\n"
				);
		String attack_summary = "";
		Attack attacks = aTrap.getAttack();
		attack_summary = attacks.getAttackName();
		switch (attacks.getAction()) {
		case standard:
			attack_summary+= trap_l10n.getString("Standard_attack"); break;
		case move:
			attack_summary+= trap_l10n.getString("Move_attack"); break;
		case minor:
			attack_summary+= trap_l10n.getString("Minor_attack"); break;
		case free:
			attack_summary+= trap_l10n.getString("Free_attack"); break;
						}
		attack_summary += "; ";
		switch (attacks.getUseType()) {
		case atWill:
			attack_summary += trap_l10n.getString("AtWill_attack"); break;
		case encounter:
			attack_summary += trap_l10n.getString("Encounter_attack"); break;
		case daily:
			attack_summary += trap_l10n.getString("Daily_attack"); break;
		case recharge:
			attack_summary += trap_l10n.getString("Recharge_attack"); break;
		}
		attack_summary += ") * " + attacks.getAccessories() + "\n";
		switch (attacks.getAbility()) {//TODO: makes absolutley no sense for a trap!?
		case STR:
			attack_summary += trap_l10n.getString("STR_attack"); break;
		case CON:
			attack_summary += trap_l10n.getString("CON_attack"); break;
		case INT:
			attack_summary += trap_l10n.getString("INT_attack"); break;
		case DEX:
			attack_summary += trap_l10n.getString("DEX_attack"); break;
		case WIS:
			attack_summary += trap_l10n.getString("WIS_attack"); break;
		case CHAR:
			attack_summary += trap_l10n.getString("CHA_attack"); break;
		}
		attack_summary += " vs. ";
		switch (attacks.getDefense()) {
		case AC:
			attack_summary += trap_l10n.getString("AC_attack"); break;
		case REF:
			attack_summary += trap_l10n.getString("REF_attack"); break;
		case FORT:
			attack_summary += trap_l10n.getString("FORT_attack"); break;
		case WILL:
			attack_summary += trap_l10n.getString("WILL_attack"); break;
		}
		attack_summary += "; "+attacks.getHit();
		trap_text.setToolTipText("<html>"+trap_l10n.getString("AvoidSkill_trap")+avoidance_skill+" "+avoidance_dc+"<br>"+
				 trap_l10n.getString("Trigger_trap")+trigger+"<br>"+
				 trap_l10n.getString("CounterText_trap")+cmSkill+" "+cmDC+"<br>"+
				 cmDescription+"<br>"+attack_summary+"</html");
		if (isSelected) {
			trap_text.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		return trap_text;
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
				description+"\n"
				);
		String attack_summary = "";
		Attack attacks = aTrap.getAttack();
		attack_summary = attacks.getAttackName();
		switch (attacks.getAction()) {
		case standard:
			attack_summary+= trap_l10n.getString("Standard_attack"); break;
		case move:
			attack_summary+= trap_l10n.getString("Move_attack"); break;
		case minor:
			attack_summary+= trap_l10n.getString("Minor_attack"); break;
		case free:
			attack_summary+= trap_l10n.getString("Free_attack"); break;
						}
		attack_summary += "; ";
		switch (attacks.getUseType()) {
		case atWill:
			attack_summary += trap_l10n.getString("AtWill_attack"); break;
		case encounter:
			attack_summary += trap_l10n.getString("Encounter_attack"); break;
		case daily:
			attack_summary += trap_l10n.getString("Daily_attack"); break;
		case recharge:
			attack_summary += trap_l10n.getString("Recharge_attack"); break;
		}
		attack_summary += ") * " + attacks.getAccessories() + "\n";
		switch (attacks.getAbility()) {//TODO: makes absolutley no sense for a trap!?
		case STR:
			attack_summary += trap_l10n.getString("STR_attack"); break;
		case CON:
			attack_summary += trap_l10n.getString("CON_attack"); break;
		case INT:
			attack_summary += trap_l10n.getString("INT_attack"); break;
		case DEX:
			attack_summary += trap_l10n.getString("DEX_attack"); break;
		case WIS:
			attack_summary += trap_l10n.getString("WIS_attack"); break;
		case CHAR:
			attack_summary += trap_l10n.getString("CHA_attack"); break;
		}
		attack_summary += " vs. ";
		switch (attacks.getDefense()) {
		case AC:
			attack_summary += trap_l10n.getString("AC_attack"); break;
		case REF:
			attack_summary += trap_l10n.getString("REF_attack"); break;
		case FORT:
			attack_summary += trap_l10n.getString("FORT_attack"); break;
		case WILL:
			attack_summary += trap_l10n.getString("WILL_attack"); break;
		}
		attack_summary += "; "+attacks.getHit();
		trap_text.setToolTipText("<html>"+trap_l10n.getString("AvoidSkill_trap")+avoidance_skill+" "+avoidance_dc+"<br>"+
									 trap_l10n.getString("Trigger_trap")+trigger+"<br>"+
									 trap_l10n.getString("CounterText_trap")+cmSkill+" "+cmDC+"<br>"+
									 cmDescription+"<br>"+attack_summary+"</html");
		if (isSelected) {
			trap_text.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		return trap_text;
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
