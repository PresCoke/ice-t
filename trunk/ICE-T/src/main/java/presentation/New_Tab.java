package presentation;
/**
 * New Tab
 * @author jamesbegg
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;

import controller.App_Root;
import controller.NewEntity;

public class New_Tab implements ActionListener/*, ListSelectionListener*/ {
/*TODO: make window close with cmd+w*/
	private JFrame newEntity_window;
	private JPanel newEntity_panel;
	private JButton save_button, cancel_button;
	private JPanel entityCreation_pane;
	//private JList item_list;
	private NewEntity controller_reference;
	private String entityType;
	
	public New_Tab(NewEntity new_controller, String entityTypeName) {
		controller_reference = new_controller;
		entityType = entityTypeName;
	}

	public void showFrame() {
		
		this.createPanel(entityType);
		JScrollPane scrollable = new JScrollPane(newEntity_panel);
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		newEntity_window = new JFrame(entityType);
		
		newEntity_window.setContentPane(scrollable);
		newEntity_window.pack();
		newEntity_window.validate();
		newEntity_window.setVisible(true);
	}

	private void createPanel(String entityTypeName) {
		
		newEntity_panel = new JPanel();
		GroupLayout newEntity_layout = new GroupLayout(newEntity_panel); 
		newEntity_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) ); // a little less on the top
		   																			 // and a little more on the bottom
		
		ResourceBundle new_tab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.NewEditTab", App_Root.language_locale);
		
		save_button = new JButton();
		cancel_button = new JButton();
		
		save_button.setText(new_tab_l10n.getString("Save_Button"));
		save_button.addActionListener(this);
		
		cancel_button.setText(new_tab_l10n.getString("Cancel_Button"));
		cancel_button.addActionListener(this);
		
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.LINE_AXIS) );
		button_panel.add(Box.createHorizontalGlue());
		button_panel.add(save_button);
		button_panel.add(cancel_button);
		
		entityCreation_pane = new JPanel();
		entityCreation_pane.setAutoscrolls(true);
		entityCreation_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createLineBorder(Color.GRAY) )
				);
		getEntityFrameOfType(entityType);
		newEntity_layout.setHorizontalGroup( newEntity_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(button_panel)
				.addComponent(entityCreation_pane)
				);
		newEntity_layout.setVerticalGroup( newEntity_layout.createSequentialGroup()
				.addComponent(button_panel)
				.addComponent(entityCreation_pane)
				);
		newEntity_panel.setLayout(newEntity_layout);
		
	}

	private void getEntityFrameOfType(String entityType) {
		JPanel form_panel = ( controller_reference.getEmptyEntityBeanOfType(entityType) ).createEntityPanel();

		entityCreation_pane.add(form_panel);
		
	}

	public void actionPerformed(ActionEvent evt) {
		if ( (JButton) evt.getSource() == save_button ) {
			saveNewEntity();
		} else if ( (JButton) evt.getSource() == cancel_button ) {
			newEntity_window.setVisible(false);
			newEntity_window.dispose();
		}
	}
	
	private void saveNewEntity() {
		controller_reference.saveEntity();
		
		newEntity_window.setVisible(false);
		newEntity_window.dispose();
	}
	
}
