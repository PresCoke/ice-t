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
		theAttack = (Attack) theValue;
			
		
		String name = "", basic = "";
		String action = "", use = "", pwr_src = "", effect = "", damage = "", accessories = "";
		String attackType = "", ability = "", defense = "";
		String hit = "", miss = "";
		
		JEditorPane theAttackBean = new JEditorPane();
		name = theAttack.getAttackName();
		if (theAttack.isBasic()) {
			basic = "BASIC";
		} else {
			basic = "";
		}
		switch (theAttack.getAction()) {
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
		switch (theAttack.getUseType()) {
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
		pwr_src = theAttack.getPowerSource();
		switch (theAttack.getEffectType()) {
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
		switch (theAttack.getDamageType()) {
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
		accessories = theAttack.getAccessories();
		Attack_Type attack_type = theAttack.getAttackType();
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
		
		switch (theAttack.getAbility()) {
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
		switch (theAttack.getDefense()) {
		case AC:
			defense = "Armour Class"; break;
		case REF:
			defense = "Reflex"; break;
		case FORT:
			defense = "Fortitude"; break;
		case WILL:
			defense = "Will"; break;
		}
		hit = theAttack.getHit();
		miss = theAttack.getMiss();
		
		theAttackBean.setText(
				name+" - "+basic+"\n"+
				"("+action+", "+use+") "+pwr_src+" "+effect+" "+damage+" "+accessories+"\n"+
				attackType+ " "+ ability + " vs. "+ defense+"\n"+
				hit+"\n"+miss
				);
		
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
