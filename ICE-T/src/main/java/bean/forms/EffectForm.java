package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import entity.Effect;
import entity.EntityM;
import entity.Resistance;

public class EffectForm implements FormBean {

	private Effect theEffect;
	private JPanel effectForm_Panel;
	private JSpinner effectValue_field;
	private JComboBox effectType_List;
	
	public EffectForm() {
		
		effectForm_Panel = new JPanel();
		effectValue_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		
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
		effectType_List = new JComboBox(resist);
		
		effectValue_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int effectValue = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theEffect.setDamage(effectValue);
			}
	});

		effectType_List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				//TODO JAMES
//				switch(index) {
//				case 0:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.acid); break;
//				case 1:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.cold); break;
//				case 2:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.fire); break;
//				case 3:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.force); break;
//				case 4:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.lightning); break;
//				case 5:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.necrotic); break;
//				case 6:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.poison); break;
//				case 7:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.psychic); break;
//				case 8:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.radiant); break;
//				case 9:
//					theEffect.setResistanceType(entity.EntityEnum.CS_Resistance_Type.thunder); break;
//				}
			}
			});
		JLabel effectType_label = new JLabel(entity_l10n.getString("EffectType_entity"));
		JLabel effectValue_label = new JLabel(entity_l10n.getString("EffectValue_entity"));
		
		GroupLayout effectForm_layout = new GroupLayout(effectForm_Panel);
		effectForm_layout.setAutoCreateGaps(true);
		effectForm_layout.setHorizontalGroup( effectForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(effectForm_layout.createSequentialGroup()
						.addComponent(effectType_label)
						.addComponent(effectType_List))
				.addGroup(effectForm_layout.createSequentialGroup()
						.addComponent(effectValue_label)
						.addComponent(effectValue_field))
				);
		effectForm_layout.setVerticalGroup( effectForm_layout.createSequentialGroup()
				.addGroup(effectForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(effectType_label)
					.addComponent(effectType_List))
				.addGroup(effectForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(effectValue_label)
					.addComponent(effectValue_field))
				);
		effectForm_layout.linkSize(SwingConstants.VERTICAL, effectValue_field, effectValue_label);
		effectForm_Panel.setLayout(effectForm_layout);
	}
	
	
	public JPanel createEntityPanel() {
		// TODO Auto-generated method stub
		theEffect = new Effect();
		
		createPanel();
		
		return effectForm_Panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		
		if (usingThis instanceof Effect) {
			theEffect = (Effect) usingThis;
		}
		
		createPanel();
		
		return effectForm_Panel;
	}

	public Object getEntity() {
		
		return theEffect;
	}
	
	private void createPanel() {
//TODO JAMES
//		if (theEffect.something() != null) {
//			effectType_List.setSelectedItem( theEffect.something );
//		} else {
//			effectType_List.setSelectedItem( theEffect.something );
//		}
//		
//		effectValue_field.setValue(theEffect.something);
		
	}

}
