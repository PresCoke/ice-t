package bean.combat;

import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import entity.*;

import controller.App_Root;

public class CreatureBean extends Bean implements TableCellRenderer {
	
	private Player thePlayer; //can be null;
	private Monster theMonster; //can be null;
	private String tool_tip;

	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean hasFocus) {
		/* TODO: Have tooltip that shows more info... and mods
		 * [Name] - [LVL] [XP] XP
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * STR: [STR] CON: [CON] DEX: [DEX]
		 * INT: [INT] WIS: [WIS] CHA: [CHA]
		 * 
		 * HP: [HP] Bloodied: [Bloodied]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle creat_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		CharacterSheet theSheet = null;
		if (theValue instanceof Player) {
			theSheet = ((Player) theValue).getCharacterSheet();
		} else if (theValue instanceof Monster) {
			theSheet = ((Monster) theValue).getCharacterSheet();
		} else if (theValue instanceof CreatureBean) {
			Object aCreature = ( (CreatureBean) theValue ).getEntity();
			if (aCreature instanceof Player) {
				theSheet = ((Player) aCreature).getCharacterSheet();
			} else if (aCreature instanceof Monster) {
				theSheet = ((Monster) aCreature).getCharacterSheet();
			}
		}
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
		String tooltip = creat_l10n.getString("STR_entity")+" "+STR+" ("+str_mod+")"+
				
				creat_l10n.getString("CON_entity")+" "+CON+" ("+con_mod+")"+
				
				creat_l10n.getString("DEX_entity")+" "+DEX+" ("+dex_mod+")\n"+
				
				creat_l10n.getString("INT_entity")+" "+INT+" ("+int_mod+")"+
				
				creat_l10n.getString("WIS_entity")+" "+WIS+" ("+wis_mod+")"+
				
				creat_l10n.getString("CHA_entity")+" "+CHA+" ("+cha_mod+")\n";
		tooltip += creat_l10n.getString("AC_entity")+" "+AC+" "+creat_l10n.getString("FORT_entity")+" "+FORT+" "+creat_l10n.getString("REF_entity")+" "+REF+" "+creat_l10n.getString("WILL_entity")+" "+WILL+"\n";
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
			attack_summary += ") * " + attacks.get(array_index).getAccessories() + "\n";
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
		if (isSelected) {
			aCreature_panel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		aCreature_panel.setToolTipText(tooltip+"</html>");
		return aCreature_panel;
	}
	
	public Component getTableCellRendererComponent(JTable theTable, Object theValue,
			boolean isSelected, boolean hasFocus, int row, int column) {
		/* TODO: Have tooltip that shows more info... and mods
		 * [Name] - [LVL] [XP] XP
		 * AC: [AC] FORT: [FORT] REF: [REF] WILL: [WILL]
		 * STR: [STR] CON: [CON] DEX: [DEX]
		 * INT: [INT] WIS: [WIS] CHA: [CHA]
		 * 
		 * HP: [HP] Bloodied: [Bloodied]
		 * Speed: [Speed] Initiative: [Initiative]
		 */
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle creat_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		CharacterSheet theSheet = null;
		if (theValue instanceof Player) {
			theSheet = ((Player) theValue).getCharacterSheet();
		} else if (theValue instanceof Monster) {
			theSheet = ((Monster) theValue).getCharacterSheet();
		} else if (theValue instanceof CreatureBean) {
			Object aCreature = ( (CreatureBean) theValue ).getEntity();
			if (aCreature instanceof Player) {
				theSheet = ((Player) aCreature).getCharacterSheet();
			} else if (aCreature instanceof Monster) {
				theSheet = ((Monster) aCreature).getCharacterSheet();
			}
		} else if (theValue == null) {
			return new JPanel();
		} else {
			return new JPanel();
		}
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
			
			tooltip += attack_summary+"<br>";
			
		}
		if (isSelected) {
			aCreature_panel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		aCreature_panel.setToolTipText(tooltip+"</html>");
		return aCreature_panel;
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

}
