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
import javax.swing.border.TitledBorder;
import javax.swing.event.*;

import controller.App_Root;
import controller.NewEntity;
import entity.*;

public class New_Tab implements ActionListener/*, ListSelectionListener*/ {
/*TODO: make window close with cmd+w*/
	JFrame newEntity_window;
	private JPanel newEntity_panel;
	private JButton next_button, save_button, cancel_button;
	private JPanel entityCreation_pane;
	//private JList item_list;
	private NewEntity controller_reference;
	private String entityType;
	
	public New_Tab(NewEntity new_controller, String entityTypeName) {
		// TODO Auto-generated constructor stub
		controller_reference = new_controller;
		entityType = entityTypeName;
	}

	public void showFrame() {
		/* Start Hack
		 * - this behaviour may be better implemented by inheriting from component
		 *   or creating a custom class with a super getPanel() method.
		 */
		
//		newEntity_panel = new JPanel();
//		newEntity_panel.add( new JTextField("NEW ENTITY") );
		
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
		
		next_button = new JButton();
		save_button = new JButton();
		cancel_button = new JButton();
		
		save_button.setText(new_tab_l10n.getString("Save_Button"));
		save_button.addActionListener(this);
		
		cancel_button.setText(new_tab_l10n.getString("Cancel_Button"));
		cancel_button.addActionListener(this);
		
//		next_button.setText(new_tab_l10n.getString("Next_button"));
//		next_button.addActionListener(this);
		
		JPanel button_panel = new JPanel();
		button_panel.setLayout( new BoxLayout(button_panel, BoxLayout.LINE_AXIS) );
		button_panel.add(Box.createHorizontalGlue());
		button_panel.add(save_button);
		button_panel.add(cancel_button);
		
		/*item_list = new JList( this.populateEntityList() );
		item_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		item_list.setLayoutOrientation(JList.VERTICAL);
		item_list.addListSelectionListener(this);
		JScrollPane itemList_pane = new JScrollPane(item_list);
		itemList_pane.setBorder( BorderFactory.createEtchedBorder() );
		itemList_pane.setPreferredSize( new Dimension(150, 0) ); // TODO: find better way then to use absolute values
		itemList_pane.setMinimumSize( new Dimension(150, 0) );
		
		JPanel list_panel = new JPanel();
		list_panel.setLayout( new BoxLayout(list_panel, BoxLayout.LINE_AXIS) );
		list_panel.setBorder( BorderFactory.createEmptyBorder(7, 0, 0, 5) );
		list_panel.add(itemList_pane);*/
		
		entityCreation_pane = new JPanel();
		//entityCreation_pane.setOpaque(false);
		entityCreation_pane.setAutoscrolls(true);
		entityCreation_pane.setBorder( BorderFactory.createCompoundBorder( 
				BorderFactory.createEmptyBorder(7, 0, 0, 0),
				BorderFactory.createLineBorder(Color.GRAY) )
				);
		getEntityFrameOfType(entityType);
		
		/*GridBagConstraints list_constraints = new GridBagConstraints();
		list_constraints.fill = GridBagConstraints.BOTH;
		list_constraints.gridx = 0;
		list_constraints.gridy = 0;
		list_constraints.gridheight = 2;
		list_constraints.weightx = 0.5;
		list_constraints.weighty = 1.0;
		newEntity_panel.add(list_panel, list_constraints);
		
		GridBagConstraints creation_constraints = new GridBagConstraints();
		creation_constraints.fill = GridBagConstraints.BOTH;
		creation_constraints.gridx = 1;
		creation_constraints.gridy = 0;
		creation_constraints.gridheight = 2;
		creation_constraints.gridwidth = 2;
		creation_constraints.weightx = 0.5;
		creation_constraints.weighty = 1.0;
		newEntity_panel.add(entityCreation_pane, creation_constraints);
		
		GridBagConstraints button_constraints = new GridBagConstraints();
		button_constraints.gridx = 2;
		button_constraints.gridy = 2;
		button_constraints.anchor = GridBagConstraints.SOUTHEAST;
		newEntity_panel.add(button_panel, button_constraints);*/
		
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
	
	/*private DefaultListModel populateEntityList() {
		DefaultListModel entity_list = new DefaultListModel();
		
		String[] entityTypeNames = controller_reference.getEntityTypeNames();
		
		for (int index = 0; index < entityTypeNames.length; index++) {
			entity_list.addElement(entityTypeNames[index]);
		}
		
		return entity_list;
	}*/

	/*public void valueChanged(ListSelectionEvent e) {
		// TODO implement this to set editor pane to appropriate entity creation page
		if (e.getValueIsAdjusting() == false && item_list.getSelectedIndex() != -1) {
				//TODO: must get appropriate info from DB/Bean
				this.getEntityFrameOfType( (String) item_list.getSelectedValue() );
				
		}
	}*/

	private void getEntityFrameOfType(String entityType) {
		//TODO: implement further
		JPanel form_panel = ( controller_reference.getEmptyEntityBeanOfType(entityType) ).createEntityPanel();
//		
//		TitledBorder temp_border = BorderFactory.createTitledBorder( BorderFactory.createLineBorder(Color.GRAY) );
//		temp_border.setTitle(entityType);
//		temp_border.setTitleJustification(TitledBorder.CENTER);
//		
//		entityCreation_pane.setBorder(temp_border);
		entityCreation_pane.add(form_panel);
		//entityCreation_pane.repaint();
		
	}

	public void actionPerformed(ActionEvent evt) {
		if ( (JButton) evt.getSource() == save_button ) {
			saveNewEntity();
		} else if ( (JButton) evt.getSource() == cancel_button ) {
			newEntity_window.setVisible(false);
			newEntity_window.dispose();
		}/* else if ( (JButton) evt.getSource() == next_button) {
			
		}*/
	}
	
	private void saveNewEntity() {
		controller_reference.saveEntity();
		
	}

	private boolean wipeEditorPane() {
		//TODO: wipe editor pane
		entityCreation_pane.removeAll();
		return true;
	}
	
}
