package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import entity.Resistance;
import entity.EntityM;

public class ResistanceForm implements FormBean {

	/*TODO: this only works once user selects initial resistanceType!*/
	private Resistance theResistance;
	private JPanel resistanceForm_panel;
	private JSpinner resistValue_field;
	private JComboBox resistType_list;
	
	public ResistanceForm() {
		
		resistanceForm_panel = new JPanel();
		resistValue_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		
		String[] resist = { entity_l10n.getString("Acid_resist"),
				entity_l10n.getString("Cold_resist"),
				entity_l10n.getString("Fire_resist"),
				entity_l10n.getString("Force_resist"),
				entity_l10n.getString("Lightning_resist"),
				entity_l10n.getString("Necrotic_resist"),
				entity_l10n.getString("Poison_resist"),
				entity_l10n.getString("Psychic_resist"),
				entity_l10n.getString("Radiant_resist"),
				entity_l10n.getString("Thunder_resist")};
		resistType_list = new JComboBox(resist);
		
		resistValue_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int resistValue = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theResistance.setResistanceValue(resistValue);
			}
		});
		
		resistType_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.acid); break;
				case 1:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.cold); break;
				case 2:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.fire); break;
				case 3:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.force); break;
				case 4:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.lightning); break;
				case 5:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.necrotic); break;
				case 6:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.poison); break;
				case 7:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.psychic); break;
				case 8:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.radiant); break;
				case 9:
					theResistance.setResistanceType(entity.EntityEnum.CS_Resistance_Type.thunder); break;
				}
			}
			});
		JLabel resistType_label = new JLabel(entity_l10n.getString("ResistType_entity"));
		JLabel resistValue_label = new JLabel(entity_l10n.getString("ResistValue_entity"));
		
		GroupLayout resistanceForm_layout = new GroupLayout(resistanceForm_panel);
		resistanceForm_layout.setAutoCreateGaps(true);
		resistanceForm_layout.setHorizontalGroup( resistanceForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(resistanceForm_layout.createSequentialGroup()
						.addComponent(resistType_label)
						.addComponent(resistType_list))
				.addGroup(resistanceForm_layout.createSequentialGroup()
						.addComponent(resistValue_label)
						.addComponent(resistValue_field))
				);
		resistanceForm_layout.setVerticalGroup( resistanceForm_layout.createSequentialGroup()
				.addGroup(resistanceForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(resistType_label)
					.addComponent(resistType_list))
				.addGroup(resistanceForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(resistValue_label)
					.addComponent(resistValue_field))
				);
		resistanceForm_layout.linkSize(SwingConstants.VERTICAL, resistValue_field, resistValue_label);
		resistanceForm_panel.setLayout(resistanceForm_layout);
	}
	
	public JPanel createEntityPanel() {
		theResistance = new Resistance();
		
		createPanel();
		
		return resistanceForm_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof Resistance) {
			theResistance = (Resistance) usingThis;
		}
		createPanel();
		
		return resistanceForm_panel;
	}

	public Resistance getEntity() {
		
		return theResistance;
	}
	
	/*
	 * TODO: I'm thinking this form will be embedded inside a character sheet and when the "add" button is pressed this form will return
	 * 		 the resistance which can be stored inside the character and rendered by another resistance class.
	 */
	private void createPanel() {

		if (theResistance.getResistanceType() != null) {
			resistType_list.setSelectedItem( theResistance.getResistanceType() );
		} else {
			resistType_list.setSelectedItem( theResistance.getResistanceType() );
		}
		
		resistValue_field.setValue(theResistance.getResistanceValue());
		
	}

}
