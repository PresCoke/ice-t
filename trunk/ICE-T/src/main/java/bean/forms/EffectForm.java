package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import entity.Effect;
import entity.EntityEnum;
import entity.EntityM;
import entity.Resistance;

public class EffectForm implements FormBean {

	private Effect theEffect;
	private JPanel effectForm_Panel;
	
	private JTextField effectName_field;
	private JComboBox effectDuration_list;
	private JTextField effectChanges_field;
	private JEditorPane effectMetrics_field;
	
	public EffectForm() {
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		
		effectForm_Panel = new JPanel();
		
		JLabel effectName_label = new JLabel(entity_l10n.getString("Name_effect"));
		effectName_field = new JTextField();
		JLabel effectMetrics_label = new JLabel(entity_l10n.getString("Metrics_effect"));
		effectMetrics_field = new JEditorPane();
		JLabel effectDamage_label = new JLabel(entity_l10n.getString("Damage_effect"));
		effectChanges_field = new JTextField();
		JLabel effectDuration_label = new JLabel(entity_l10n.getString("Duration_effect"));
		String[] duration = { entity_l10n.getString("StartOfAppliersTurn_effect"),
							  entity_l10n.getString("EndOfAppliersTurn_effect"),
							  entity_l10n.getString("EndOfEncounter_effect"),
							  entity_l10n.getString("SaveEnds_effect") };
		effectDuration_list = new JComboBox(duration);
		effectDuration_list.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theEffect.setDuration(EntityEnum.E_Duration.untilStartOfApplyingCreaturesNextTurn); break;
				case 1:
					theEffect.setDuration(EntityEnum.E_Duration.untilEndOfApplyingCreaturesNextTurn); break;
				case 2:
					theEffect.setDuration(EntityEnum.E_Duration.endOfTheEncounter); break;
				case 3:
					theEffect.setDuration(EntityEnum.E_Duration.saveEnds); break;
				}
			}
		});
		
		GroupLayout effectForm_layout = new GroupLayout(effectForm_Panel);
		effectForm_layout.setAutoCreateGaps(true);
		effectForm_layout.setHorizontalGroup(effectForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(effectForm_layout.createSequentialGroup()
							.addComponent(effectName_label)
							.addComponent(effectName_field)
							.addComponent(effectDamage_label)
							.addComponent(effectChanges_field))
						.addComponent(effectDuration_label)
						.addComponent(effectDuration_list)
						.addComponent(effectMetrics_label)
						.addComponent(effectMetrics_field)
				);
		effectForm_layout.setVerticalGroup( effectForm_layout.createSequentialGroup()
				.addGroup(effectForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(effectName_label)
					.addComponent(effectName_field)
					.addComponent(effectDamage_label)
					.addComponent(effectChanges_field))
				.addComponent(effectDuration_label)
				.addComponent(effectDuration_list)
				.addComponent(effectMetrics_label)
				.addComponent(effectMetrics_field)
				);
		effectForm_layout.linkSize(SwingConstants.VERTICAL, effectName_field, effectName_label);
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
		} else {
			theEffect = new Effect();
		}
		
		createPanel();
		
		return effectForm_Panel;
	}

	public Object getEntity() {
		
		return theEffect;
	}
	
	private void createPanel() {
		effectName_field.setText( theEffect.getName() );
		effectChanges_field.setText( theEffect.getChanges() );
		effectDuration_list.setSelectedIndex( theEffect.getDuration().ordinal() );
		effectMetrics_field.setText( theEffect.getMetrics() );
		
	}

}
