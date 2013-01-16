package presentation;

/**
 * Edit Tab
 * @author jamesbegg
 *
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
//TODO: enable tab switching between lists... dat classy.
public class Edit_Tab implements ListSelectionListener, ActionListener {

	private JPanel editEntity_panel;
	private JList entityType_list, entityName_list;
	private DefaultListModel type_list, name_list;
	private JEditorPane entityEdit_pane;
	private JButton save_button, remove_button, cancel_button;
	
	public Component getPanel() {
		/* Start Hack
		 * - this behaviour may be better implemented by inheriting from component
		 *   or creating a custom class with a super getPanel() method.
		 */
		
//		editEntity_panel = new JPanel();
//		editEntity_panel.add( new JTextField("Edit Entity") );
		
		this.createPanel();
		
		return editEntity_panel;
	}

	private void createPanel() {
		
		editEntity_panel = new JPanel();
		editEntity_panel.setLayout( new GridBagLayout() );
		editEntity_panel.setBorder( BorderFactory.createEmptyBorder(0, 10, 15, 10) );
		
		type_list = new DefaultListModel();
		populateEntityTypeList();
		entityType_list = new JList(type_list);
		entityType_list.addListSelectionListener(this);
		JScrollPane type_pane = new JScrollPane(entityType_list);
		type_pane.setBorder( BorderFactory.createEtchedBorder() );
		type_pane.setPreferredSize( new Dimension(150, 0) );
		type_pane.setMinimumSize( new Dimension(150, 0) );
		
		name_list = new DefaultListModel();
		entityName_list = new JList(name_list);
		entityName_list.addListSelectionListener(this);
		JScrollPane name_pane = new JScrollPane(entityName_list);
		name_pane.setBorder( BorderFactory.createEtchedBorder() );
		name_pane.setPreferredSize( new Dimension(150, 0) ); //TODO: find better way then specifying exact sizes
		name_pane.setMinimumSize( new Dimension(150, 0) );
		
		JPanel listSelection_panel = new JPanel();
		listSelection_panel.setLayout( new BoxLayout(listSelection_panel, BoxLayout.LINE_AXIS) );
		listSelection_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		listSelection_panel.add(type_pane);
		listSelection_panel.add( Box.createRigidArea( new Dimension(5, 0) ) );
		listSelection_panel.add(name_pane);
		
		save_button = new JButton("Save");
		save_button.addActionListener(this);
		remove_button = new JButton("Remove");
		remove_button.addActionListener(this);
		cancel_button = new JButton("Cancel");
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
		
		GridBagConstraints list_constraints = new GridBagConstraints();
		list_constraints.fill = GridBagConstraints.BOTH;
		list_constraints.gridx = 0;
		list_constraints.gridy = 0;
		list_constraints.gridheight = 2;
		list_constraints.weightx = 0.5;
		list_constraints.weighty = 1.0;
		editEntity_panel.add(listSelection_panel, list_constraints);
		
		GridBagConstraints edit_constraints = new GridBagConstraints();
		edit_constraints.fill = GridBagConstraints.BOTH;
		edit_constraints.gridx = 1;
		edit_constraints.gridy = 0;
		edit_constraints.gridheight = 2;
		edit_constraints.gridwidth = 2;
		edit_constraints.weightx = 0.5;
		edit_constraints.weighty = 1.0;
		editEntity_panel.add(entityEdit_pane, edit_constraints);
		
		GridBagConstraints button_constraints = new GridBagConstraints();
		button_constraints.gridx = 2;
		button_constraints.gridy = 2;
		button_constraints.anchor = GridBagConstraints.SOUTHEAST;
		editEntity_panel.add(button_panel, button_constraints);
		
	}

	public void actionPerformed(ActionEvent evt) {
		if ( (JButton) evt.getSource() == this.save_button ) {
			this.saveChanges();
		} else if ( (JButton) evt.getSource() == this.remove_button ) {
			this.removeSelectedEntity();
		} else if ( (JButton) evt.getSource() == this.cancel_button ) {
			this.cancelChanges();
		}
		
	}

	public void valueChanged(ListSelectionEvent evt) {
		if (evt.getValueIsAdjusting() == false) {
			if ( (JList) evt.getSource() == this.entityType_list && this.entityType_list.getSelectedIndex() != -1) {
				this.getEntityNamesOfType( (String) this.entityType_list.getSelectedValue() );
//				entityName_list.repaint();
				
			} else if ( (JList) evt.getSource() == this.entityName_list && this.entityName_list.getSelectedIndex() != -1) {
				this.getEntityInfoFor( (String) this.entityName_list.getSelectedValue() );
			}
		}
	}
	
	
	private void populateEntityTypeList() {
		//TODO: implement this further
		
		type_list.addElement("Creature");
		type_list.addElement("Trap");
		type_list.addElement("etc...");
	}
	
	private void getEntityInfoFor(String selectedEntityName) {
		// TODO Auto-generated method stub
		this.entityEdit_pane.setText(selectedEntityName);
		
		TitledBorder temp_border = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY) );
		temp_border.setTitle(selectedEntityName);
		temp_border.setTitleJustification(TitledBorder.CENTER);
		
		entityEdit_pane.setBorder(temp_border);
//		entityEdit_pane.repaint();
	}

	private void getEntityNamesOfType(String selectedEntityType) {
		// TODO implement correctly
		
		if (selectedEntityType == "Creature") {
			name_list.removeAllElements();
			name_list.addElement("Jaks");
			name_list.addElement("Garthorp");
			name_list.addElement("Tevish");
			
		} else if (selectedEntityType == "Trap") {
			name_list.removeAllElements();
			name_list.addElement("Crossbow");
			name_list.addElement("Trap Door");
			name_list.addElement("Giant Rolling Rock");			
			
		} else if (selectedEntityType == "etc...") {
			name_list.removeAllElements();
			name_list.addElement("etc...");
		}
	}

	private boolean removeSelectedEntity() {
		//TODO: implement
		return true;
	}
	
	private boolean saveChanges() {
		//TODO: implement
		return true;
	}
	
	private boolean cancelChanges() {
		//TODO: implement
		return true;
	}

}
