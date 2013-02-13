package bean.combat;

import javax.swing.*;

import java.awt.*;

import entity.*;

public class AttackBean implements Bean {

	private Attack theAttack;
	
	public AttackBean() {
		
	}
	
	public Component getListCellRendererComponent(JList theList, Object theValue,
			int index, boolean isSelected, boolean cellHasFocus) {
		//TODO: add html code to make look good!!
		//name (action, use) * Effect Type
		//Attack Type, Ablility vs. Defense; damage string. Effect string
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
		Attack aAttack = null;
		if (theValue instanceof AttackBean) {
			aAttack = (Attack) ( (AttackBean) theValue ).getEntity();
		} else if (theValue instanceof Attack) {
			aAttack = (Attack) theValue;
		}
		String name = "", basic = "";
		String action = "", use = "", pwr_src = "", effect = "", damage = "", accessories = "";
		String attackType = "", ability = "", defense = "";
		String hit = "", miss = "";
		String sustain = "";
		
		JEditorPane theAttackBean = new JEditorPane();
		if (isSelected) {
			theAttackBean.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		name = aAttack.getAttackName();
		if (aAttack.isBasic()) {
			basic = "BASIC";
		} else {
			basic = "";
		}
		switch (aAttack.getAction()) {
		case standard:
			action = "Standard"; break;
		case move:
			action = "Move"; break;
		case minor:
			action = "Minor"; break;
		case free:
			action = "Free"; break;
		default:
			action = "";
		}
		switch (aAttack.getSustain()) {
		case standard:
			action = "Standard"; break;
		case move:
			action = "Move"; break;
		case minor:
			action = "Minor"; break;
		case NONE:
			action = ""; break;
		}
		switch (aAttack.getUseType()) {
		case atWill:
			use = "At Will"; break;
		case encounter:
			use = "Encounter"; break;
		case daily:
			use = "Daily"; break;
		case recharge:
			use = "Recharge"; break;
		default:
			use = "";
		}
		pwr_src = aAttack.getPowerSource();
		switch (aAttack.getEffectType()) {
		case charm:
			effect = "Charm"; break;
		case conjuration:
			effect = "Conjuration"; break;
		case fear:
			effect = "Fear"; break;
		case healing:
			effect = "Healing"; break;
		case illusion:
			effect = "Illusion"; break;
		case poison:
			effect = "Poison"; break;
		case polymorph:
			effect = "Polymorph"; break;
		case reliable:
			effect = "Reliable"; break;
		case sleep:
			effect = "Sleep"; break;
		case stance:
			effect = "Stance"; break;
		case teleportation:
			effect = "Teleportation"; break;
		case zone:
			effect = "Zone"; break;
		default:
			effect = ""; break;
				
		}
		switch (aAttack.getDamageType()) {
		case acid:
			damage = "Acid"; break;
		case cold:
			damage = "Cold"; break;
		case fire:
			damage = "Fire"; break;
		case force:
			damage = "Force"; break;
		case lightning:
			damage = "Lightning"; break;
		case necrotic:
			damage = "Necrotic"; break;
		case poison:
			damage = "Poison"; break;
		case psychic:
			damage = "Psychic"; break;
		case radiant:
			damage = "Radiant"; break;
		case thunder:
			damage = "Thunder"; break;
		default:
			damage = "";
		}
		accessories = aAttack.getAccessories();
		Attack_Type attack_type = aAttack.getAttackType();
		if (attack_type instanceof A_Range) {
			A_Range range = (A_Range) attack_type;
			attackType = "Ranged "+range.getS_range()+" - "+range.getL_range();
		} else if (attack_type instanceof A_Melee) {
			A_Melee melee = (A_Melee) attack_type;
			attackType = "Melee ";
			if (melee.getReach() > 0) {
				attackType += Integer.toString(melee.getReach());
			}
		} else if (attack_type instanceof A_Area) {
			A_Area area = (A_Area) attack_type;
			attackType = "Area ";
			switch (area.getArea_type()) {
			case burst:
				attackType += "Burst "; break;
			case wall:
				attackType += "Wall "; break;
			}
			attackType += Integer.toString( area.getArea_size() );
			attackType += " within "+ Integer.toString( area.getArea_range() );
		} else if (attack_type instanceof A_Close) {
			A_Close close = (A_Close) attack_type;
			attackType = "Close ";
			//TODO FIX that : 
			//Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
			//at bean.combat.AttackBean.getListCellRendererComponent(AttackBean.java:165)
			switch (close.getCloseType()) {
			case blast:
				attackType += "Blast "; break;
			case burst:
				attackType += "Burst "; break;
			}
			attackType += Integer.toString( close.getSize() );
		} else {
			attackType = "Personal";
		}
		
		switch (aAttack.getAbility()) {
		case STR:
			ability = "Strength"; break;
		case CON:
			ability = "Constitution"; break;
		case DEX:
			ability = "Dexterity"; break;
		case INT:
			ability = "Intelligence"; break;
		case WIS:
			ability = "Wisdom"; break;
		case CHAR:
			ability = "Charisma"; break;
		}
		switch (aAttack.getDefense()) {
		case AC:
			defense = "Armour Class"; break;
		case REF:
			defense = "Reflex"; break;
		case FORT:
			defense = "Fortitude"; break;
		case WILL:
			defense = "Will"; break;
		}
		hit = aAttack.getHit();
		miss = aAttack.getMiss();
		String fullText = name+" - "+basic+"\n"+
				use + " * "+pwr_src+" "+effect+" "+damage+" "+accessories+"\n"+
				action+attackType+ "\n"+
				"Target: " + aAttack.getPrimaryTarget()+"\n"+ 
				"Attack: " + ability + " vs. "+ defense+"\n"+
				"Hit: "+hit+"\nMiss: "+miss;
		//Need to do something about missing fields
		if (aAttack.getSecondaryTarget() != "") {
			fullText+= "Secondary Target: "+aAttack.getSecondaryTarget();
		}
		if (aAttack.getTrigger() != "") {
			fullText+= "Trigger: "+aAttack.getTrigger();
		}
		if (aAttack.getSustain() != EntityEnum.A_Sustain.NONE ) {
			fullText+= "Sustain " + sustain;
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
