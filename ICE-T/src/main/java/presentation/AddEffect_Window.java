package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

import entity.*;
import controller.App_Root;
import controller.Combat;
import bean.forms.EffectForm;

public class AddEffect_Window implements ActionListener {
	private JFrame addEffect_window;
	private JPanel addEffect_panel;
	private EffectForm theEffectForm;
	private JButton addEffectToCreatures_button;
	private Combat controller_reference;
	private Combat_Tab parent_window;
	
	public AddEffect_Window (Combat combat_controller, Combat_Tab parent) {
		controller_reference = combat_controller;
		parent_window = parent;
		theEffectForm = new EffectForm();
	}
	
	public void showFrame() {
		this.createPanel();
		addEffect_window = new JFrame();
		addEffect_window.setContentPane(addEffect_panel);
		addEffect_window.pack();
		addEffect_window.validate();
		addEffect_window.setVisible(true);
	}

	private void createPanel() {
		ResourceBundle effect_l10n = ResourceBundle.getBundle("filters.MainGUI_l10n.CombatTab", App_Root.language_locale);
		addEffectToCreatures_button = new JButton(effect_l10n.getString("AddEffect_Button"));
		addEffectToCreatures_button.addActionListener(this);
		
		addEffect_panel = new JPanel();
		addEffect_panel.setBorder( BorderFactory.createEtchedBorder() );
		JPanel effectForm_panel = theEffectForm.createEntityPanel();
		GroupLayout addEffect_layout = new GroupLayout(addEffect_panel);
		addEffect_layout.setHorizontalGroup( addEffect_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(effectForm_panel)
				.addComponent(addEffectToCreatures_button)
				);
		addEffect_layout.setVerticalGroup( addEffect_layout.createSequentialGroup()
				.addComponent(effectForm_panel)
				.addComponent(addEffectToCreatures_button)
				);
		addEffect_panel.setLayout(addEffect_layout);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if ( theEffectForm.validateEntity() ) {
			parent_window.addThisEffectToSelectedCreatures(theEffectForm.getEntity());
			addEffect_window.setVisible(false);
			addEffect_window.dispose();
		} else {
			return;
		}
	}
}
