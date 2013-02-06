package bean.combat;

import java.awt.*;
import java.util.ResourceBundle;

import javax.swing.*;

import entity.Resistance;

public class ResistanceBean implements Bean {
	
	/*
	 * NOW DOES WORK
	 * But must colour selected*/
	Resistance theResistance;

	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof Resistance) {
			theResistance = (Resistance) thisEntity;
		} else {
			theResistance = new Resistance();
		}
	}

	public Resistance getEntity() {
		return theResistance;
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		theResistance = (Resistance) value;
		JLabel resistanceType_label;
		switch (theResistance.getResistanceType()) {
		case acid:
			resistanceType_label = new JLabel(entity_l10n.getString("Acid_resist")); break;
		case cold:
			resistanceType_label = new JLabel(entity_l10n.getString("Cold_resist")); break;
		case fire:
			resistanceType_label = new JLabel(entity_l10n.getString("Fire_resist")); break;
		case force:
			resistanceType_label = new JLabel(entity_l10n.getString("Force_resist")); break;
		case lightning:
			resistanceType_label = new JLabel(entity_l10n.getString("Lightning_resist")); break;
		case necrotic:
			resistanceType_label = new JLabel(entity_l10n.getString("Necrotic_resist")); break;
		case poison:
			resistanceType_label = new JLabel(entity_l10n.getString("Poison_resist")); break;
		case psychic:
			resistanceType_label = new JLabel(entity_l10n.getString("Psychic_resist")); break;
		case radiant:
			resistanceType_label = new JLabel(entity_l10n.getString("Radiant_resist")); break;
		case thunder:
			resistanceType_label = new JLabel(entity_l10n.getString("Thunder_resist")); break;
		default:
			resistanceType_label = new JLabel();
		}
		
		JLabel resistanceValue_label = new JLabel( Integer.toString(theResistance.getResistanceValue()) );
		
		JPanel resistance_panel = new JPanel();
		if (isSelected) {
			resistance_panel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		resistance_panel.setLayout( new BoxLayout(resistance_panel, BoxLayout.LINE_AXIS) );
		resistance_panel.add(Box.createHorizontalGlue());
		resistance_panel.add(resistanceType_label);
		resistance_panel.add(Box.createHorizontalGlue());
		resistance_panel.add(resistanceValue_label);
		resistance_panel.add(Box.createHorizontalGlue());
		
		
		return resistance_panel;
	}

}
