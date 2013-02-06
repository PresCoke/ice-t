package presentation;

/**
 * Edit Tab
 * @author jamesbegg
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import controller.*;
//TODO: enable tab switching between lists... dat classy.
public class Edit_Tab implements ListSelectionListener, ActionListener {

	private JFrame editEntity_window;
	private String entityName, entityType;
	private JPanel editEntity_panel;
	private JList entityName_list;
	private DefaultListModel name_list;
	private JEditorPane entityEdit_pane;
	private JButton save_button, remove_button, cancel_button;
	private EditEntity controller_reference;
	
	public Edit_Tab(EditEntity edit_controller, String entity_type) {
		controller_reference = edit_controller;
	}

	public void showFrame() {
		
		this.createPanel();
		
		JScrollPane scrollable = new JScrollPane(editEntity_panel);
		scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		editEntity_window = new JFrame(entityName);
		
		editEntity_window.setContentPane(scrollable);
		editEntity_window.pack();
		editEntity_window.validate();
		editEntity_window.setVisible(true);
		
	}

	private void createPanel() {
		
		editEntity_panel = new JPanel();
		editEntity_panel.setLayout( new GridBagLayout() );
		editEntity_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) );
		
		ResourceBundle editTab_l10n = ResourceBundle.getBundle("filters.mainGUI_l10n.NewEditTab", App_Root.language_locale);
		
		this.getEntityNamesOfType(entityType);
		entityName_list = new JList(name_list);
		entityName_list.addListSelectionListener(this);
		JScrollPane name_pane = new JScrollPane(entityName_list);
		name_pane.setBorder( BorderFactory.createEtchedBorder() );
		name_pane.setPreferredSize( new Dimension(150, 0) ); //TODO: find better way then specifying exact sizes
		name_pane.setMinimumSize( new Dimension(150, 0) );
		
		JPanel listSelection_panel = new JPanel();
		listSelection_panel.setLayout( new BoxLayout(listSelection_panel, BoxLayout.LINE_AXIS) );
		listSelection_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		listSelection_panel.add(name_pane);
		
		save_button = new JButton(editTab_l10n.getString("Save_Button"));
		save_button.addActionListener(this);
		remove_button = new JButton(editTab_l10n.getString("Remove_Button"));
		remove_button.addActionListener(this);
		cancel_button = new JButton(editTab_l10n.getString("Cancel_Button"));
		cancel_button.addActionListener(this);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.LINE_AXIS) );
		button_panel.add( Box.createHorizontalGlue() );
		button_panel.add(save_button);
		button_panel.add(remove_button);
		button_panel.add(cancel_button);
		
		entityEdit_pane = new JEditorPane();
		entityEdit_pane.setOpaque(false);
		entityEdit_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createLineBorder(Color.GRAY) )
				);
	}

	public void actionPerformed(ActionEvent evt) {
		if ( (JButton) evt.getSource() == this.save_button ) {
			this.saveChanges();
		} else if ( (JButton) evt.getSource() == this.remove_button ) {
			this.removeSelectedEntity();
		} else if ( (JButton) evt.getSource() == this.cancel_button ) {
			this.editEntity_window.setVisible(false);
			this.editEntity_window.dispose();
		}
		
	}

	public void valueChanged(ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting() == false) {
			if ( (JList) evt.getSource() == this.entityName_list && this.entityName_list.getSelectedIndex() != -1) {
				this.getEntityInfoFor( (String) this.entityName_list.getSelectedValue() );
			}
		}
	}
	
	private void getEntityInfoFor(String selectedEntityName) {
		this.entityEdit_pane.setText(selectedEntityName);
		
		JPanel formPanel = this.controller_reference.getEntityPanelOfName(selectedEntityName);
		entityEdit_pane.add(formPanel);
		
		TitledBorder temp_border = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY) );
		temp_border.setTitle(selectedEntityName);
		temp_border.setTitleJustification(TitledBorder.CENTER);
		
		entityEdit_pane.setBorder(temp_border);
	}

	private void getEntityNamesOfType(String selectedEntityType) {
		this.name_list = new DefaultListModel();
		String[] entityNames = this.controller_reference.getEntityNamesOfType(selectedEntityType);
		for (int index = 0; index < entityNames.length; index++) {
			this.name_list.addElement(entityNames[index]);
		}
	}

	private boolean removeSelectedEntity() {
		this.controller_reference.removeEntity();
		return true;
	}
	
	private boolean saveChanges() {
		this.controller_reference.saveEntity();
		return true;
	}

}
