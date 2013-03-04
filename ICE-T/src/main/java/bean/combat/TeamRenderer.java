package bean.combat;

import java.awt.*;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import controller.App_Root;
import entity.*;

public class TeamRenderer implements ListCellRenderer, TableCellRenderer {

	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean hasFocus) {
		JEditorPane theFinalPane = new JEditorPane();
		if (theValue instanceof TrapHazard) {
			 theFinalPane = createTrapPanel((TrapHazard) theValue);
		} else if (theValue instanceof TrapBean) {
			theFinalPane = createTrapPanel((TrapHazard)( (TrapBean) theValue ).getEntity());
		} else if (theValue instanceof Player) {
			theFinalPane = createPlayerPanel( ((Player) theValue).getCharacterSheet() );
		} else if (theValue instanceof Monster) {
			theFinalPane = createMonsterPanel( ((Monster) theValue).getCharacterSheet() );
		} else if (theValue instanceof CreatureBeanShallow) {
			Object aCreature = ( (CreatureBeanShallow) theValue ).getEntity();
			if (aCreature instanceof Player) {
				theFinalPane = createPlayerPanel( ((Player) aCreature).getCharacterSheet() );
			} else if (aCreature instanceof Monster) {
				theFinalPane = createMonsterPanel( ((Monster) aCreature).getCharacterSheet() );
			}
		} else {
			return (new JPanel());
		}
		if (isSelected) {
			theFinalPane.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		}
		return theFinalPane;
	}

	public Component getTableCellRendererComponent(JTable theTable, Object theValue,
			boolean isSelected, boolean hasFocus, int row, int col) {
		JEditorPane theFinalPane = new JEditorPane();
		if (theValue instanceof TrapHazard) {
			 theFinalPane = createTrapPanel((TrapHazard) theValue);
		} else if (theValue instanceof TrapBean) {
			theFinalPane = createTrapPanel((TrapHazard)( (TrapBean) theValue ).getEntity());
		} else if (theValue instanceof Player) {
			theFinalPane = createPlayerPanel( ((Player) theValue).getCharacterSheet() );
		} else if (theValue instanceof Monster) {
			theFinalPane = createMonsterPanel( ((Monster) theValue).getCharacterSheet() );
		} else if (theValue instanceof CreatureBeanShallow) {
			Object aCreature = ( (CreatureBeanShallow) theValue ).getEntity();
			if (aCreature instanceof Player) {
				theFinalPane = createPlayerPanel( ((Player) aCreature).getCharacterSheet() );
			} else if (aCreature instanceof Monster) {
				theFinalPane = createMonsterPanel( ((Monster) aCreature).getCharacterSheet() );
			}
		} else {
			return (new JPanel());
		}
		
		if (isSelected) {
			theFinalPane.setBackground(javax.swing.UIManager.getDefaults().getColor("List.selectionBackground"));
		}
		return theFinalPane;
	}
	
	private JEditorPane createTrapPanel(TrapHazard entity) {
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
		ResourceBundle trap_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		TrapHazard aTrap = entity;
		
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
		return trap_text;
	}

	private JEditorPane createMonsterPanel(CharacterSheet characterSheet) {
		/* TODO: Have tooltip that shows more info... and mods
		 * [Name] - [LVL] [XP] XP
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * STR: [STR] CON: [CON] DEX: [DEX]
		 * INT: [INT] WIS: [WIS] CHA: [CHA]
		 * 
		 * HP: [HP] Bloodied: [Bloodied]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		CharacterSheet theSheet = characterSheet;
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), role = "";
		String size = "", origin = "", monsterType = "", exp = Integer.toString(theSheet.getXP());
		String AC = Integer.toString(theSheet.getAC()), FORT = Integer.toString(theSheet.getFORT()), REF = Integer.toString(theSheet.getREF()), WILL = Integer.toString(theSheet.getWILL());
		
		String hp = Integer.toString(theSheet.getMaxHP()), bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed()), initiative = Integer.toString(theSheet.getInitiative());
		
		switch (theSheet.getRole()) {
		case striker:
			role = creat_l10n.getString("Striker_role"); break;
		case controller:
			role = creat_l10n.getString("Controller_role"); break;
		case defender:
			role = creat_l10n.getString("Defender_role"); break;
		case leader:
			role = creat_l10n.getString("Leader_role"); break;
		case artillery:
			role = creat_l10n.getString("Artillery_role"); break;
		case brute:
			role = creat_l10n.getString("Brute_role"); break;
		case lurker:
			role = creat_l10n.getString("Lurker_role"); break;
		case skirmisher:
			role = creat_l10n.getString("Skirmisher_role"); break;
		case soldier:
			role = creat_l10n.getString("Soldier_role"); break;
		}
		switch (theSheet.getSize()) {
		case tiny:
			size = creat_l10n.getString("Tiny_size"); break;
		case small:
			size = creat_l10n.getString("Small_size"); break;
		case medium:
			size = creat_l10n.getString("Medium_size"); break;
		case large:
			size = creat_l10n.getString("Large_size"); break;
		case huge:
			size = creat_l10n.getString("Huge_size"); break;
		case gargantuan:
			size = creat_l10n.getString("Gargantuan_size"); break;
		}
		if (theSheet.isNPC()) {
			switch (theSheet.getMonsterOrigin()) {
			case aberrant:
				origin = creat_l10n.getString("Aberrant_origin");
				break;
			case elemental:
				origin = creat_l10n.getString("Elemental_origin");
				break;
			case fey:
				origin = creat_l10n.getString("Fey_origin");
				break;
			case immortal:
				origin = creat_l10n.getString("Immortal_origin");
				break;
			case shadow:
				origin = creat_l10n.getString("Shadow_origin");
				break;
			}
			switch (theSheet.getMonsterType()) {
			case animate:
				monsterType = creat_l10n.getString("Animate_monster");
				break;
			case beast:
				monsterType = creat_l10n.getString("Beast_monster");
				break;
			case humanoid:
				monsterType = creat_l10n.getString("Humanoid_monster");
				break;
			case magicalBeast:
				monsterType = creat_l10n.getString("MagicalBeast_monster");
				break;
			}
		}
		
		JEditorPane aCreature_panel = new JEditorPane();
		aCreature_panel.setText(
				name + " - "+creat_l10n.getString("LVL_entity")+" " + level +" "+ role +"\n"+
				size + " " + origin + " " + monsterType+ " " + exp + " XP\n"+
				creat_l10n.getString("Init_entity")+" " + initiative + " "+creat_l10n.getString("Speed_entity")+" "+ speed + "\n"+
				creat_l10n.getString("MAXHP_entity")+ " "+ hp + " "+creat_l10n.getString("Bloodied_entity")+" " + bloodied
				);
		
		String STR = Integer.toString(theSheet.getSTR()), str_mod = Integer.toString( (theSheet.getSTR()/2) - 5); 
		String CON = Integer.toString(theSheet.getCON()), con_mod = Integer.toString( (theSheet.getCON()/2) - 5);
		String DEX = Integer.toString(theSheet.getDEX()), dex_mod = Integer.toString( (theSheet.getDEX()/2) - 5);
		String INT = Integer.toString(theSheet.getINT()), int_mod = Integer.toString( (theSheet.getINT()/2) - 5);
		String WIS = Integer.toString(theSheet.getWIS()), wis_mod = Integer.toString( (theSheet.getWIS()/2) - 5);
		String CHA = Integer.toString(theSheet.getCHAR()),cha_mod = Integer.toString( (theSheet.getCHAR()/2) - 5);
		String tooltip = "<html>";
		tooltip += creat_l10n.getString("STR_entity")+" "+STR+" ("+str_mod+")"+
				
				creat_l10n.getString("CON_entity")+" "+CON+" ("+con_mod+")"+
				
				creat_l10n.getString("DEX_entity")+" "+DEX+" ("+dex_mod+")<br>"+
				
				creat_l10n.getString("INT_entity")+" "+INT+" ("+int_mod+")"+
				
				creat_l10n.getString("WIS_entity")+" "+WIS+" ("+wis_mod+")"+
				
				creat_l10n.getString("CHA_entity")+" "+CHA+" ("+cha_mod+")<br>";
		tooltip += creat_l10n.getString("AC_entity")+" "+AC+" "+creat_l10n.getString("FORT_entity")+" "+FORT+" "+creat_l10n.getString("REF_entity")+" "+REF+" "+creat_l10n.getString("WILL_entity")+" "+WILL+"<br>";
		List<Attack> attacks = theSheet.getAttacks();
		for (int array_index=0; array_index<attacks.size(); array_index++) {
			/*
			 * [Name] ([Action], [Use]) * [Accessory]
			 * [Abl] vs. [Def]; [hit]
			 */
			String attack_summary = new String();
			attack_summary = attacks.get(array_index).getAttackName();
			switch (attacks.get(array_index).getAction()) {
			case standard:
				attack_summary+= creat_l10n.getString("Standard_attack"); break;
			case move:
				attack_summary+= creat_l10n.getString("Move_attack"); break;
			case minor:
				attack_summary+= creat_l10n.getString("Minor_attack"); break;
			case free:
				attack_summary+= creat_l10n.getString("Free_attack"); break;
							}
			attack_summary += "; ";
			switch (attacks.get(array_index).getUseType()) {
			case atWill:
				attack_summary += creat_l10n.getString("AtWill_attack"); break;
			case encounter:
				attack_summary += creat_l10n.getString("Encounter_attack"); break;
			case daily:
				attack_summary += creat_l10n.getString("Daily_attack"); break;
			case recharge:
				attack_summary += creat_l10n.getString("Recharge_attack"); break;
			}
			attack_summary += ") * " + attacks.get(array_index).getAccessories() + "<br>";
			switch (attacks.get(array_index).getAbility()) {
			case STR:
				attack_summary += creat_l10n.getString("STR_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getSTR()/2) - 5) +")"; break;
			case CON:
				attack_summary += creat_l10n.getString("CON_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getCON()/2) - 5) +")"; break;
			case INT:
				attack_summary += creat_l10n.getString("INT_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getINT()/2) - 5) +")"; break;
			case DEX:
				attack_summary += creat_l10n.getString("DEX_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getDEX()/2) - 5) +")"; break;
			case WIS:
				attack_summary += creat_l10n.getString("WIS_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getWIS()/2) - 5) +")";  break;
			case CHAR:
				attack_summary += creat_l10n.getString("CHA_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getCHAR()/2) - 5) +")"; break;
			}
			attack_summary += " vs. ";
			switch (attacks.get(array_index).getDefense()) {
			case AC:
				attack_summary += creat_l10n.getString("AC_attack"); break;
			case REF:
				attack_summary += creat_l10n.getString("REF_attack"); break;
			case FORT:
				attack_summary += creat_l10n.getString("FORT_attack"); break;
			case WILL:
				attack_summary += creat_l10n.getString("WILL_attack"); break;
			}
			attack_summary += "; "+attacks.get(array_index).getHit();
			
			tooltip += attack_summary;
			
		}
	
		aCreature_panel.setToolTipText(tooltip+"</html>");
		return aCreature_panel;
	}

	private JEditorPane createPlayerPanel(CharacterSheet characterSheet) {
		/* TODO: Have tooltip that shows more info... and mods
		 * [Name] - [LVL] [XP] XP
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * STR: [STR] CON: [CON] DEX: [DEX]
		 * INT: [INT] WIS: [WIS] CHA: [CHA]
		 * 
		 * HP: [HP] Bloodied: [Bloodied]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		ResourceBundle creat_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		CharacterSheet theSheet = characterSheet;
		String name = theSheet.getName(), level = Integer.toString(theSheet.getLevel()), role = "";
		String size = "", origin = "", monsterType = "", exp = Integer.toString(theSheet.getXP());
		String AC = Integer.toString(theSheet.getAC()), FORT = Integer.toString(theSheet.getFORT()), REF = Integer.toString(theSheet.getREF()), WILL = Integer.toString(theSheet.getWILL());
		
		String hp = Integer.toString(theSheet.getMaxHP()), bloodied = Integer.toString(theSheet.getBloodied());
		String speed = Integer.toString(theSheet.getSpeed()), initiative = Integer.toString(theSheet.getInitiative());
		
		switch (theSheet.getRole()) {
		case striker:
			role = creat_l10n.getString("Striker_role"); break;
		case controller:
			role = creat_l10n.getString("Controller_role"); break;
		case defender:
			role = creat_l10n.getString("Defender_role"); break;
		case leader:
			role = creat_l10n.getString("Leader_role"); break;
		case artillery:
			role = creat_l10n.getString("Artillery_role"); break;
		case brute:
			role = creat_l10n.getString("Brute_role"); break;
		case lurker:
			role = creat_l10n.getString("Lurker_role"); break;
		case skirmisher:
			role = creat_l10n.getString("Skirmisher_role"); break;
		case soldier:
			role = creat_l10n.getString("Soldier_role"); break;
		}
		switch (theSheet.getSize()) {
		case tiny:
			size = creat_l10n.getString("Tiny_size"); break;
		case small:
			size = creat_l10n.getString("Small_size"); break;
		case medium:
			size = creat_l10n.getString("Medium_size"); break;
		case large:
			size = creat_l10n.getString("Large_size"); break;
		case huge:
			size = creat_l10n.getString("Huge_size"); break;
		case gargantuan:
			size = creat_l10n.getString("Gargantuan_size"); break;
		}
		
		JEditorPane aCreature_panel = new JEditorPane();
		aCreature_panel.setText(
				name + " - "+creat_l10n.getString("LVL_entity")+" " + level +" "+ role +"\n"+
				size + " " + origin + " " + monsterType+ " " + exp + " XP\n"+
				creat_l10n.getString("Init_entity")+" " + initiative + " "+creat_l10n.getString("Speed_entity")+" "+ speed + "\n"+
				creat_l10n.getString("MAXHP_entity")+ " "+ hp + " "+creat_l10n.getString("Bloodied_entity")+" " + bloodied
				);
		
		String STR = Integer.toString(theSheet.getSTR()), str_mod = Integer.toString( (theSheet.getSTR()/2) - 5); 
		String CON = Integer.toString(theSheet.getCON()), con_mod = Integer.toString( (theSheet.getCON()/2) - 5);
		String DEX = Integer.toString(theSheet.getDEX()), dex_mod = Integer.toString( (theSheet.getDEX()/2) - 5);
		String INT = Integer.toString(theSheet.getINT()), int_mod = Integer.toString( (theSheet.getINT()/2) - 5);
		String WIS = Integer.toString(theSheet.getWIS()), wis_mod = Integer.toString( (theSheet.getWIS()/2) - 5);
		String CHA = Integer.toString(theSheet.getCHAR()),cha_mod = Integer.toString( (theSheet.getCHAR()/2) - 5);
		String tooltip = "<html>";
		tooltip += creat_l10n.getString("STR_entity")+" "+STR+" ("+str_mod+")"+
				
				creat_l10n.getString("CON_entity")+" "+CON+" ("+con_mod+")"+
				
				creat_l10n.getString("DEX_entity")+" "+DEX+" ("+dex_mod+")<br>"+
				
				creat_l10n.getString("INT_entity")+" "+INT+" ("+int_mod+")"+
				
				creat_l10n.getString("WIS_entity")+" "+WIS+" ("+wis_mod+")"+
				
				creat_l10n.getString("CHA_entity")+" "+CHA+" ("+cha_mod+")<br>";
		tooltip += creat_l10n.getString("AC_entity")+" "+AC+" "+creat_l10n.getString("FORT_entity")+" "+FORT+" "+creat_l10n.getString("REF_entity")+" "+REF+" "+creat_l10n.getString("WILL_entity")+" "+WILL+"<br>";
		List<Attack> attacks = theSheet.getAttacks();
		for (int array_index=0; array_index<attacks.size(); array_index++) {
			/*
			 * [Name] ([Action], [Use]) * [Accessory]
			 * [Abl] vs. [Def]; [hit]
			 */
			String attack_summary = new String();
			attack_summary = attacks.get(array_index).getAttackName();
			switch (attacks.get(array_index).getAction()) {
			case standard:
				attack_summary+= creat_l10n.getString("Standard_attack"); break;
			case move:
				attack_summary+= creat_l10n.getString("Move_attack"); break;
			case minor:
				attack_summary+= creat_l10n.getString("Minor_attack"); break;
			case free:
				attack_summary+= creat_l10n.getString("Free_attack"); break;
							}
			attack_summary += "; ";
			switch (attacks.get(array_index).getUseType()) {
			case atWill:
				attack_summary += creat_l10n.getString("AtWill_attack"); break;
			case encounter:
				attack_summary += creat_l10n.getString("Encounter_attack"); break;
			case daily:
				attack_summary += creat_l10n.getString("Daily_attack"); break;
			case recharge:
				attack_summary += creat_l10n.getString("Recharge_attack"); break;
			}
			attack_summary += ") * " + attacks.get(array_index).getAccessories() + "<br>";
			switch (attacks.get(array_index).getAbility()) {
			case STR:
				attack_summary += creat_l10n.getString("STR_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getSTR()/2) - 5) +")"; break;
			case CON:
				attack_summary += creat_l10n.getString("CON_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getCON()/2) - 5) +")"; break;
			case INT:
				attack_summary += creat_l10n.getString("INT_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getINT()/2) - 5) +")"; break;
			case DEX:
				attack_summary += creat_l10n.getString("DEX_attack"); 
				attack_summary += " (" + Integer.toString( (theSheet.getDEX()/2) - 5) +")"; break;
			case WIS:
				attack_summary += creat_l10n.getString("WIS_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getWIS()/2) - 5) +")";  break;
			case CHAR:
				attack_summary += creat_l10n.getString("CHA_attack");
				attack_summary += " (" + Integer.toString( (theSheet.getCHAR()/2) - 5) +")"; break;
			}
			attack_summary += " vs. ";
			switch (attacks.get(array_index).getDefense()) {
			case AC:
				attack_summary += creat_l10n.getString("AC_attack"); break;
			case REF:
				attack_summary += creat_l10n.getString("REF_attack"); break;
			case FORT:
				attack_summary += creat_l10n.getString("FORT_attack"); break;
			case WILL:
				attack_summary += creat_l10n.getString("WILL_attack"); break;
			}
			attack_summary += "; "+attacks.get(array_index).getHit();
			
			tooltip += attack_summary;
			
		}
		aCreature_panel.setToolTipText(tooltip+"</html>");
		return aCreature_panel;
	}
	
}
