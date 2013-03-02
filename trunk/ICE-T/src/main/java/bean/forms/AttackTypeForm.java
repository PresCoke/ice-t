package bean.forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;

import controller.App_Root;
import entity.*;

public class AttackTypeForm implements FormBean {
 
	private JPanel attackType_panel;
	private GroupLayout attackType_layout;
	private JPanel cardPanel;
	private JComboBox attackType_selection;
	
	private JPanel prsnl_panel;
	private Attack_Type theAttackType;
	
	private JPanel range_panel;
	private JTextField shortRange_field, longRange_field;
	private A_Range range_hidden;
	
	private JPanel melee_panel;
	private JTextField reach_field;
	private A_Melee melee_hidden;
	
	private JPanel area_panel;
	private JTextField areaRange_field;
	private JTextField areaSize_field;
	private JComboBox areaType_field;
	private A_Area area_hidden;
	
	private JPanel close_panel;
	private JTextField closeSize_field;
	private JComboBox closeType_field;
	private A_Close close_hidden;

	public AttackTypeForm() {
		theAttackType = new Attack_Type();
		attackType_panel = new JPanel();
		attackType_layout = new GroupLayout(attackType_panel);
		
		prsnl_panel = new JPanel(); theAttackType = new Attack_Type();
		range_panel = new JPanel(); range_hidden = new A_Range();
		melee_panel = new JPanel(); melee_hidden = new A_Melee();
		area_panel = new JPanel();  area_hidden  = new A_Area();
		close_panel = new JPanel(); close_hidden = new A_Close();
		
		theAttackType.setPersonal(true);
		range_hidden.setL_range(1); range_hidden.setS_range(1); range_hidden.setPersonal(false); 
		melee_hidden.setReach(1); melee_hidden.setPersonal(false);
		area_hidden.setArea_range(1); area_hidden.setArea_size(1); area_hidden.setArea_type(EntityEnum.A_Area_Type.burst); area_hidden.setPersonal(false);
		close_hidden.setSize(1); close_hidden.setCloseType(EntityEnum.A_Close_Type.blast); close_hidden.setPersonal(false);
		
		final ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		JLabel shortRange_label = new JLabel(entity_l10n.getString("Short_range"));
		JLabel longRange_label = new JLabel(entity_l10n.getString("Long_range"));
		shortRange_field = new JTextField();
		shortRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range < 0 || range > 9) {
					ke.consume();
				}
			}
		});

		longRange_field = new JTextField();
		longRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range < 0 || range > 9) {
					ke.consume();
				}
			}
		});

		GroupLayout range_layout = new GroupLayout(range_panel);
		range_layout.setHorizontalGroup( range_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup( range_layout.createSequentialGroup()
							.addComponent(shortRange_label)
							.addComponent(shortRange_field))
					.addGroup( range_layout.createSequentialGroup()
							.addComponent(longRange_label)
							.addComponent(longRange_field))
				);
		range_layout.setVerticalGroup( range_layout.createSequentialGroup()
				.addGroup( range_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(shortRange_label)
						.addComponent(shortRange_field))
				.addGroup( range_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(longRange_label)
						.addComponent(longRange_field))
				);
		range_panel.setLayout(range_layout);
		
		JLabel reach_label = new JLabel(entity_l10n.getString("Reach_melee"));
		reach_field = new JTextField();
		reach_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range < 0 || range > 9) {
					ke.consume();
				}
			}
		});
		
		GroupLayout melee_layout= new GroupLayout(melee_panel);
		melee_layout.setHorizontalGroup( melee_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(reach_label)
				.addComponent(reach_field)
				);
		melee_layout.setVerticalGroup( melee_layout.createSequentialGroup()
				.addComponent(reach_label)
				.addComponent(reach_field)
				);
		melee_panel.setLayout(melee_layout);
		
		JLabel areaRange_label = new JLabel(entity_l10n.getString("Range_area"));
		JLabel areaSize_label = new JLabel(entity_l10n.getString("Size_area"));
		JLabel areaType_label = new JLabel(entity_l10n.getString("Type_area"));
		areaRange_field = new JTextField();
		areaRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range < 0 || range > 9) {
					ke.consume();
				}
			}
		});
		
		areaSize_field = new JTextField();
		areaSize_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int size = ke.getKeyChar() - '0';
				if (size < 0 || size > 9) {
					ke.consume();
				}
			}
		});
		
		String[] areaType = { entity_l10n.getString("Burst_area"),
							  entity_l10n.getString("Wall_area") };
		areaType_field = new JComboBox(areaType);
		areaType_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					area_hidden.setArea_type(EntityEnum.A_Area_Type.burst); break;
				case 1:
					area_hidden.setArea_type(EntityEnum.A_Area_Type.wall); break;
				}
			}
		});
		
		GroupLayout area_layout = new GroupLayout(area_panel);
		area_layout.setHorizontalGroup( area_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( area_layout.createSequentialGroup()
						.addComponent(areaType_label)
						.addComponent(areaType_field))
				.addGroup( area_layout.createSequentialGroup()
						.addComponent(areaRange_label)
						.addComponent(areaRange_field))
				.addGroup( area_layout.createSequentialGroup()
						.addComponent(areaSize_label)
						.addComponent(areaSize_field))
				);
		area_layout.setVerticalGroup( area_layout.createSequentialGroup()
				.addGroup( area_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(areaType_label)
						.addComponent(areaType_field))
				.addGroup( area_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(areaRange_label)
						.addComponent(areaRange_field))
				.addGroup( area_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(areaSize_label)
						.addComponent(areaSize_field))
				);
		area_panel.setLayout(area_layout);
		
		JLabel closeSize_label = new JLabel(entity_l10n.getString("Size_close"));
		JLabel closeType_label = new JLabel(entity_l10n.getString("Type_close"));
		closeSize_field = new JTextField();
		closeSize_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
				int size = ke.getKeyChar() - '0';
				if (size < 0 || size > 9) {
					ke.consume();
				}
			}
		});
		
		String[] closeType = { entity_l10n.getString("Blast_close"),
				  			   entity_l10n.getString("Burst_close") };
		closeType_field = new JComboBox(closeType);
		closeType_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					close_hidden.setCloseType(EntityEnum.A_Close_Type.blast); break;
				case 1:
					close_hidden.setCloseType(EntityEnum.A_Close_Type.burst); break;
				}
			}
		});
		
		GroupLayout close_layout = new GroupLayout(close_panel);
		close_layout.setHorizontalGroup( close_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( close_layout.createSequentialGroup()
						.addComponent(closeType_label)
						.addComponent(closeType_field))				
				.addGroup( close_layout.createSequentialGroup()
						.addComponent(closeSize_label)
						.addComponent(closeSize_field))
				);
		close_layout.setVerticalGroup( close_layout.createSequentialGroup()
				.addGroup( close_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(closeType_label)
						.addComponent(closeType_field))				
				.addGroup( close_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(closeSize_label)
						.addComponent(closeSize_field))
				);
		close_panel.setLayout(close_layout);
		
		String[] attackTypes = { entity_l10n.getString("Personal_attack"),
								 entity_l10n.getString("Ranged_attack"),
								 entity_l10n.getString("Melee_attack"),
								 entity_l10n.getString("Area_attack"),
								 entity_l10n.getString("Close_attack")};
		cardPanel = new JPanel( new CardLayout() );
		cardPanel.add(prsnl_panel, attackTypes[0]);
		cardPanel.add(range_panel, attackTypes[1]);
		cardPanel.add(melee_panel, attackTypes[2]);
		cardPanel.add( area_panel, attackTypes[3]);
		cardPanel.add(close_panel, attackTypes[4]);
		
		
		attackType_selection = new JComboBox(attackTypes);
		attackType_selection.setEditable(false);
		attackType_selection.addItemListener( new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				CardLayout cl = (CardLayout) cardPanel.getLayout();
				String selection = (String) ie.getItem();
				if ( selection.equals(entity_l10n.getString("Personal_attack")) ) {
					createPersonalPanel();
				} else if ( selection.equals(entity_l10n.getString("Ranged_attack")) ) {
					createRangedPanel();
				} else if ( selection.equals(entity_l10n.getString("Melee_attack")) ) {
					createMeleePanel();
				} else if ( selection.equals(entity_l10n.getString("Area_attack")) ) {
					createAreaPanel();
				} else if ( selection.equals(entity_l10n.getString("Close_attack")) ) {
					createClosePanel();
				}
				cl.show( cardPanel, selection);
				
			}
		});
		
		attackType_layout.setHorizontalGroup( attackType_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(attackType_selection)
				.addComponent(cardPanel)
				);
		attackType_layout.setVerticalGroup( attackType_layout.createSequentialGroup()
				.addComponent(attackType_selection)
				.addComponent(cardPanel)
				);
		
		attackType_panel.setLayout(attackType_layout);
	}
	
	public JPanel createEntityPanel() {
		theAttackType = new Attack_Type();
		range_hidden = new A_Range();
		melee_hidden = new A_Melee();
		close_hidden = new A_Close();
		area_hidden = new A_Area();
		createPersonalPanel();
		return attackType_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		if (usingThis instanceof A_Range) {
			range_hidden = (A_Range) usingThis;
			melee_hidden = new A_Melee();
			close_hidden = new A_Close();
			area_hidden = new A_Area();
			attackType_selection.setSelectedItem( entity_l10n.getString("Ranged_attack") );
			createRangedPanel();
		} else if (usingThis instanceof A_Melee) {
			melee_hidden = (A_Melee) usingThis;
			melee_hidden = new A_Melee();
			range_hidden = new A_Range();
			close_hidden = new A_Close();
			area_hidden = new A_Area();
			attackType_selection.setSelectedItem( entity_l10n.getString("Melee_attack") );
			createMeleePanel();
		} else if (usingThis instanceof A_Close) {
			close_hidden = (A_Close) usingThis;
			melee_hidden = new A_Melee();
			range_hidden = new A_Range();
			area_hidden = new A_Area();
			attackType_selection.setSelectedItem( entity_l10n.getString("Close_attack") );
			createClosePanel();
		} else if (usingThis instanceof A_Area) {
			area_hidden = (A_Area) usingThis;
			melee_hidden = new A_Melee();
			range_hidden = new A_Range();
			close_hidden = new A_Close();
			attackType_selection.setSelectedItem( entity_l10n.getString("Area_attack") );
			createAreaPanel();
		} else {
			theAttackType = (Attack_Type) usingThis;
			melee_hidden = new A_Melee();
			range_hidden = new A_Range();
			close_hidden = new A_Close();
			area_hidden = new A_Area();
			attackType_selection.setSelectedItem( entity_l10n.getString("Personal_attack") );
			createPersonalPanel();
		}
		
		return attackType_panel;
	}

	public Object getEntity() {
		String selection = (String) attackType_selection.getSelectedItem();
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		if ( selection.equals(entity_l10n.getString("Ranged_attack")) ) {
			range_hidden.setL_range( Integer.parseInt(this.longRange_field.getText()) );
			range_hidden.setS_range( Integer.parseInt(this.shortRange_field.getText()) );
			return range_hidden;
		} else if ( selection.equals(entity_l10n.getString("Melee_attack")) ) {
			melee_hidden.setReach( Integer.parseInt(this.reach_field.getText()) );
			return melee_hidden;
		} else if ( selection.equals(entity_l10n.getString("Area_attack")) ) {
			area_hidden.setArea_range( Integer.parseInt(this.areaRange_field.getText()) );
			area_hidden.setArea_size( Integer.parseInt(this.areaSize_field.getText()) );
			return area_hidden;
		} else if ( selection.equals(entity_l10n.getString("Close_attack")) ) {
			close_hidden.setSize( Integer.parseInt(this.closeSize_field.getText()) );
			return close_hidden;
		} else {
			return theAttackType;
		}
	}

	private void createRangedPanel() {
		theAttackType.setPersonal(false);
		shortRange_field.setText( Integer.toString(range_hidden.getS_range()) );
		longRange_field.setText( Integer.toString(range_hidden.getL_range()) );
	}

	private void createMeleePanel() {
		theAttackType.setPersonal(false);
		reach_field.setText( Integer.toString(melee_hidden.getReach()) );
	}

	private void createClosePanel() {
		theAttackType.setPersonal(false);
		closeType_field.setSelectedIndex( close_hidden.getCloseType().ordinal() );
		closeSize_field.setText( Integer.toString(close_hidden.getSize()) );
	}

	private void createAreaPanel() {
		theAttackType.setPersonal(false);
		areaType_field.setSelectedIndex( area_hidden.getArea_type().ordinal() );
		areaRange_field.setText( Integer.toString(area_hidden.getArea_range()) );
		areaSize_field.setText( Integer.toString(area_hidden.getArea_size()) );
	}
	
	private void createPersonalPanel() {
		theAttackType.setPersonal(true);
	}

	public boolean validateEntity() {
		boolean isValidForm = true;
		String invalidFieldString = "";
		String window_title = "";
		String selection = (String) attackType_selection.getSelectedItem();
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		if ( selection.equals(entity_l10n.getString("Ranged_attack")) ) {
			window_title = entity_l10n.getString("Ranged_attack");
			if (this.longRange_field.getText().equals("")) {
				isValidForm = false;
				invalidFieldString += "Long Range field is absent.\n";
			}
			if (this.shortRange_field.getText() != "") {
				isValidForm = false;
				invalidFieldString += "Short Range field is absent.\n";
			}
		} else if ( selection.equals(entity_l10n.getString("Melee_attack")) ) {
			window_title = entity_l10n.getString("Melee_attack");
			if (this.reach_field.getText() != "") {
				isValidForm = false;
				invalidFieldString += "Reach field is absent.\n";
			}
		} else if ( selection.equals(entity_l10n.getString("Area_attack")) ) {
			window_title = entity_l10n.getString("Area_attack");
			if (this.areaRange_field.getText() != "") {
				isValidForm = false;
				invalidFieldString += "Area Range field is absent.\n";
			}
			if (this.areaSize_field.getText() != "") {
				isValidForm = false;
				invalidFieldString += "Area Size field is absent.\n";
			}
		} else if ( selection.equals(entity_l10n.getString("Close_attack")) ) {
			window_title = entity_l10n.getString("Close_attack");
			if (this.closeSize_field.getText() != "") {
				isValidForm = false;
				invalidFieldString += "Close Size field is absent.\n";
			}
		}
		
		if (!isValidForm) {
			JOptionPane.showMessageDialog(attackType_panel,
										  invalidFieldString,
										  window_title,
										  JOptionPane.WARNING_MESSAGE);
		}
		
		return isValidForm;
	}
}
