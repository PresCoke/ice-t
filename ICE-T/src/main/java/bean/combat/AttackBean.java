package bean.combat;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.App_Root;

import java.awt.*;
import java.util.ResourceBundle;

import entity.*;

public class AttackBean extends Bean {

	private Attack theAttack;
	
	public AttackBean() {
		
	}
	
	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean cellHasFocus) {
		//TODO: add html code to make look good!!
		//name (action, use) * Effect Type
		//Attack Type, Ablility vs. Defense; damage string. Effect string
		//[if exists Miss: [miss string]
		//
		//alternatively
		//
		//[name] - [basic]
		//[use] * [PowerSource] [Damage Type] [Accessories]
		//[action] [attack type]
		//target: [primary target]
		//attack: [ability] vs. [defense]
		//Hit: [damage]
		//Miss: [Miss]
		//Secondary target: [secondary target] 
		//Secondary Attack: [TODO:this is missing]
		//Secondary Hit: [TODO: this is missing]
		//Effect: [Effect String]
		//Sustain [sustain use]: [//TODO missing string]
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle attack_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		Attack aAttack = null;
		if (theValue instanceof AttackBean) {
			aAttack = (Attack) ( (AttackBean) theValue ).getEntity();
		} else if (theValue instanceof Attack) {
			aAttack = (Attack) theValue;
		}
		String name = aAttack.getAttackName(), basic = "";
		String action = "", use = "", pwr_src = "", effect = "", damage = "", accessories = "";
		String attackType = "", ability = "", defense = "";
		String hit = "", miss = "";
		String sustain = "";
		
		JEditorPane theAttackBean = new JEditorPane();
		TitledBorder title = BorderFactory.createTitledBorder(name);
		title.setTitleJustification(TitledBorder.LEFT);
		theAttackBean.setBorder(title);
		if (isSelected) {
			theAttackBean.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		if (aAttack.isBasic()) {
			basic = "BASIC";
		} else {
			basic = "";
		}
		switch (aAttack.getAction()) {
		case standard:
			action = attack_l10n.getString("Standard_attack"); break;
		case move:
			action = attack_l10n.getString("Move_attack"); break;
		case minor:
			action = attack_l10n.getString("Minor_attack"); break;
		case free:
			action = attack_l10n.getString("Free_attack"); break;
		default:
			action = "";
		}
		switch (aAttack.getSustain()) {
		case standard:
			action = attack_l10n.getString("Standard_attack"); break;
		case move:
			action = attack_l10n.getString("Move_attack"); break;
		case minor:
			action = attack_l10n.getString("Minor_attack"); break;
		case NONE:
			action = ""; break;
		}
		switch (aAttack.getUseType()) {
		case atWill:
			use = attack_l10n.getString("AtWill_attack"); break;
		case encounter:
			use = attack_l10n.getString("Encounter_attack"); break;
		case daily:
			use = attack_l10n.getString("Daily_attack"); break;
		case recharge:
			use = attack_l10n.getString("Recharge_attack"); break;
		default:
			use = "";
		}
		pwr_src = aAttack.getPowerSource();
		switch (aAttack.getEffectType()) {
		case charm:
			effect = attack_l10n.getString("Charm_attack"); break;
		case conjuration:
			effect = attack_l10n.getString("Conjuration_attack"); break;
		case fear:
			effect = attack_l10n.getString("Fear_attack"); break;
		case healing:
			effect = attack_l10n.getString("Healing_attack"); break;
		case illusion:
			effect = attack_l10n.getString("Illusion_attack"); break;
		case poison:
			effect = attack_l10n.getString("Poison_attack"); break;
		case polymorph:
			effect = attack_l10n.getString("Polymorph_attack"); break;
		case reliable:
			effect = attack_l10n.getString("Reliable_attack"); break;
		case sleep:
			effect = attack_l10n.getString("Sleep_attack"); break;
		case stance:
			effect = attack_l10n.getString("Stance_attack"); break;
		case teleportation:
			effect = attack_l10n.getString("Teleportation_attack"); break;
		case zone:
			effect = attack_l10n.getString("Zone_attack"); break;
		default:
			effect = ""; break;
				
		}
		switch (aAttack.getDamageType()) {
		case acid:
			damage = attack_l10n.getString("Acid_resist"); break;
		case cold:
			damage = attack_l10n.getString("Cold_resist"); break;
		case fire:
			damage = attack_l10n.getString("Fire_resist"); break;
		case force:
			damage = attack_l10n.getString("Force_resist"); break;
		case lightning:
			damage = attack_l10n.getString("Lightning_resist"); break;
		case necrotic:
			damage = attack_l10n.getString("Necrotic_resist"); break;
		case poison:
			damage = attack_l10n.getString("Poison_resist"); break;
		case psychic:
			damage = attack_l10n.getString("Psychic_resist"); break;
		case radiant:
			damage = attack_l10n.getString("Radiant_resist"); break;
		case thunder:
			damage = attack_l10n.getString("Thunder_resist"); break;
		default:
			damage = "";
		}
		accessories = aAttack.getAccessories();
		Attack_Type attack_type = aAttack.getAttackType();
		if (attack_type instanceof A_Range) {
			A_Range range = (A_Range) attack_type;
			attackType = attack_l10n.getString("Ranged_attack")+range.getS_range()+" - "+range.getL_range();
		} else if (attack_type instanceof A_Melee) {
			A_Melee melee = (A_Melee) attack_type;
			attackType = attack_l10n.getString("Melee_attack");
			if (melee.getReach() > 0) {
				attackType += Integer.toString(melee.getReach());
			}
		} else if (attack_type instanceof A_Area) {
			A_Area area = (A_Area) attack_type;
			attackType = attack_l10n.getString("Area_attack");
			switch (area.getArea_type()) {
			case burst:
				attackType += attack_l10n.getString("Burst_area"); break;
			case wall:
				attackType += attack_l10n.getString("Wall_area"); break;
			}
			attackType += Integer.toString( area.getArea_size() );
			attackType += " within "+ Integer.toString( area.getArea_range() );
		} else if (attack_type instanceof A_Close) {
			A_Close close = (A_Close) attack_type;
			attackType = attack_l10n.getString("Close_attack");
			//TODO FIX that : 
			//Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
			//at bean.combat.AttackBean.getListCellRendererComponent(AttackBean.java:165)
			switch (close.getCloseType()) {
			case blast:
				attackType += attack_l10n.getString("Blast_close"); break;
			case burst:
				attackType += attack_l10n.getString("Burst_close"); break;
			}
			attackType += Integer.toString( close.getSize() );
		} else {
			attackType = attack_l10n.getString("Personal_attack");
		}
		
		switch (aAttack.getAbility()) {
		case STR:
			ability = attack_l10n.getString("STR_attack"); break;
		case CON:
			ability = attack_l10n.getString("CON_attack"); break;
		case DEX:
			ability = attack_l10n.getString("DEX_attack"); break;
		case INT:
			ability = attack_l10n.getString("INT_attack"); break;
		case WIS:
			ability = attack_l10n.getString("WIS_attack"); break;
		case CHAR:
			ability = attack_l10n.getString("CHA_attack"); break;
		}
		switch (aAttack.getDefense()) {
		case AC:
			defense = attack_l10n.getString("AC_attack"); break;
		case REF:
			defense = attack_l10n.getString("REF_attack"); break;
		case FORT:
			defense = attack_l10n.getString("FORT_attack"); break;
		case WILL:
			defense = attack_l10n.getString("WILL_attack"); break;
		}
		hit = aAttack.getHit();
		miss = aAttack.getMiss();
		String fullText = name+" - "+basic+"\n"+
				use + " * "+pwr_src+" "+effect+" "+damage+" "+accessories+"\n"+
				action+attackType+ "\n"+
				attack_l10n.getString("Primary_attack") + aAttack.getPrimaryTarget()+"\n"+ 
				attack_l10n.getString("Attack_attack") + ability + " v. "+ defense+"\n"+
				attack_l10n.getString("Hit_attack")+hit+"\n"+attack_l10n.getString("Miss_attack") +miss;
		//Need to do something about missing fields
		if (!aAttack.getSecondaryTarget().equals("")) {
			fullText+= attack_l10n.getString("Secondary_attack")+aAttack.getSecondaryTarget();
		}
		if (!aAttack.getTrigger().equals("")) {
			fullText+= attack_l10n.getString("Trigger_attack")+aAttack.getTrigger();
		}
		if (aAttack.getSustain() != EntityEnum.A_Sustain.NONE ) {
			fullText+= attack_l10n.getString("Sustain_attack") + sustain;
		}
				
		theAttackBean.setText(fullText);
		
		return theAttackBean;
	}

	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof Attack) {
			theAttack = (Attack) thisEntity;
		} else {
			theAttack = new Attack();
		}
	}

	public Object getEntity() {
		// TODO Auto-generated method stub
		return theAttack;
	}

}
