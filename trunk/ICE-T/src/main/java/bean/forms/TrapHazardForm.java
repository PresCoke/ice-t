package bean.forms;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import entity.EntityM;
import entity.Resistance;
import entity.TrapHazard;

public class TrapHazardForm implements FormBean {
	
	private TrapHazard theTrapHazard;
	private JPanel traphazardForm_panel;
	private JSpinner traphazardValue_field;
	private JComboBox traphazardType_list;
	
	public TrapHazardForm() {
		
		traphazardForm_panel = new JPanel();
		traphazardValue_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		
		String[] traphazard = { entity_l10n.getString("Blaster_trap"),
				entity_l10n.getString("Lurker_trap"),
				entity_l10n.getString("Obstacle_trap"),
				entity_l10n.getString("Warder_trap"),
				entity_l10n.getString("Solo_trap"),
				entity_l10n.getString("Elite_trap"),}; 

		traphazardType_list = new JComboBox(traphazard);
		
		traphazardValue_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int resistValue = (Integer) ((JSpinner) arg0.getSource()).getValue();
				/*theTrapHazard.setValue(traphazardValue);*/
			}
		});
		
		/*traphazardType_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theTrapHazard.setTrapHazardType(entity.EntityEnum.T_Type.trap); break;
				case 1:
					theTrapHazard.setResistanceType(entity.EntityEnum.T_Type.hazard); break;
				}
			}
			});*/
	
		JLabel traphazardType_label = new JLabel(entity_l10n.getString("TrapHazardType_entity"));
		JLabel traphazardValue_label = new JLabel(entity_l10n.getString("TrapHazardValue_entity"));
		
		GroupLayout resistanceForm_layout = new GroupLayout(traphazardForm_panel);
		resistanceForm_layout.setAutoCreateGaps(true);
		resistanceForm_layout.setHorizontalGroup( resistanceForm_layout.createSequentialGroup()
				.addComponent(traphazardType_label)
				.addComponent(traphazardType_list)
				.addComponent(traphazardValue_label)
				.addComponent(traphazardValue_field)
				);
		resistanceForm_layout.setVerticalGroup( resistanceForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addComponent(traphazardType_label)
				.addComponent(traphazardType_list)
				.addComponent(traphazardValue_label)
				.addComponent(traphazardValue_field)
				);
		/*traphazardForm_layout.linkSize(SwingConstants.VERTICAL, traphazardValue_field, traphazardValue_label);*/
		// what's the issue here?
		traphazardForm_panel.setLayout(resistanceForm_layout);
	}

	public JPanel createEntityPanel() {
		theTrapHazard = new TrapHazard();
		
		createPanel();
		
		return traphazardForm_panel;
	}
	
	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof Resistance) {
			theTrapHazard = (TrapHazard) usingThis;
		}
		createPanel();
		
		return traphazardForm_panel;
	}

	public Object getEntity() {
		
		return theTrapHazard;
	}
	
	private void createPanel() {

		if (theTrapHazard.getType() != null) {
			traphazardType_list.setSelectedItem( theTrapHazard.getType() );
		} else {
			traphazardType_list.setSelectedItem( theTrapHazard.getType() );
		}
		
		traphazardValue_field.setValue(theTrapHazard.getValue());
		
	}

}
