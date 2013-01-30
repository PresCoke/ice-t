package bean.forms;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;

import entity.*;

public class AttackTypeForm implements FormBean {

	private Attack_Type theAttackType; 
	private JPanel attackType_panel;
	private JTextField shortRange_field, longRange_field;
	private JTextField reach_field;
	private JTextField areaRange_field;
	private JTextField areaSize_field;
	private JComboBox areaType_field;
	private JTextField closeSize_field;
	private JComboBox closeType_field;

	public AttackTypeForm() {
		theAttackType = new Attack_Type();
		attackType_panel = new JPanel();
	}
	
	public JPanel createEntityPanel() {
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		shortRange_field = new JTextField();
		shortRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int range = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					range = ((A_Range) theAttackType).getS_range();
					String deleted_string = Integer.toString(range);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						range = Integer.parseInt(deleted_string);
						((A_Range) theAttackType).setS_range(range);
					} else {
						((A_Range) theAttackType).setS_range(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range >= 0 && range <= 9) { // ensure health is an integer
					range += ((A_Range) theAttackType).getS_range() * 10;
					((A_Range) theAttackType).setS_range(range);
				} else {
					ke.consume();
				}
			}
			
		});

		longRange_field = new JTextField();
		longRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int range = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					range = ((A_Range) theAttackType).getL_range();
					String deleted_string = Integer.toString(range);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						range = Integer.parseInt(deleted_string);
						((A_Range) theAttackType).setL_range(range);
					} else {
						((A_Range) theAttackType).setL_range(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range >= 0 && range <= 9) { // ensure health is an integer
					range += ((A_Range) theAttackType).getL_range() * 10;
					((A_Range) theAttackType).setL_range(range);
				} else {
					ke.consume();
				}
			}
			
		});

		reach_field = new JTextField();
		reach_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int range = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					range = ((A_Melee) theAttackType).getReach();
					String deleted_string = Integer.toString(range);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						range = Integer.parseInt(deleted_string);
						((A_Melee) theAttackType).setReach(range);
					} else {
						((A_Melee) theAttackType).setReach(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range >= 0 && range <= 9) { // ensure health is an integer
					range += ((A_Melee) theAttackType).getReach() * 10;
					((A_Melee) theAttackType).setReach(range);
				} else {
					ke.consume();
				}
			}
			
		});
		
		areaRange_field = new JTextField();
		areaRange_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int range = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					range = ((A_Area) theAttackType).getArea_range();
					String deleted_string = Integer.toString(range);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						range = Integer.parseInt(deleted_string);
						((A_Area) theAttackType).setArea_range(range);
					} else {
						((A_Area) theAttackType).setArea_range(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int range = ke.getKeyChar() - '0';
				if (range >= 0 && range <= 9) { // ensure health is an integer
					range += ((A_Area) theAttackType).getArea_range() * 10;
					((A_Area) theAttackType).setArea_range(range);
				} else {
					ke.consume();
				}
			}
			
		});
		
		areaSize_field = new JTextField();
		areaSize_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int size = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					size = ((A_Area) theAttackType).getArea_size();
					String deleted_string = Integer.toString(size);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						size = Integer.parseInt(deleted_string);
						((A_Area) theAttackType).setArea_size(size);
					} else {
						((A_Area) theAttackType).setArea_size(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int size = ke.getKeyChar() - '0';
				if (size >= 0 && size <= 9) { // ensure health is an integer
					size += ((A_Area) theAttackType).getArea_size() * 10;
					((A_Area) theAttackType).setArea_size(size);
				} else {
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
					((A_Area) theAttackType).setArea_type(EntityEnum.A_Area_Type.burst); break;
				case 1:
					((A_Area) theAttackType).setArea_type(EntityEnum.A_Area_Type.wall); break;
				}
			}
		});
		
		closeSize_field = new JTextField();
		closeSize_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int size = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					size = ((A_Close) theAttackType).getSize();
					String deleted_string = Integer.toString(size);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						size = Integer.parseInt(deleted_string);
						((A_Close) theAttackType).setSize(size);
					} else {
						((A_Close) theAttackType).setSize(0);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				int size = ke.getKeyChar() - '0';
				if (size >= 0 && size <= 9) { // ensure health is an integer
					size += ((A_Close) theAttackType).getSize() * 10;
					((A_Close) theAttackType).setSize(size);
				} else {
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
					((A_Close) theAttackType).setCloseType(EntityEnum.A_Close_Type.blast); break;
				case 1:
					((A_Close) theAttackType).setCloseType(EntityEnum.A_Close_Type.burst); break;
				}
			}
		});
		
		createPersonalPanel();
		return attackType_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof A_Range) {
			theAttackType = new A_Range();
			createRangedPanel();
		} else if (usingThis instanceof A_Melee) {
			theAttackType = new A_Melee();
			createMeleePanel();
		} else if (usingThis instanceof A_Close) {
			theAttackType = new A_Close();
			createClosePanel();
		} else if (usingThis instanceof A_Area) {
			theAttackType = new A_Area();
			createAreaPanel();
		} else {
			theAttackType = new Attack_Type();
			createPersonalPanel();
		}
		return attackType_panel;
	}

	public Object getEntity() {
		return theAttackType;
	}

	private void createRangedPanel() {
		theAttackType.setPersonal(false);
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		attackType_panel.removeAll();
		
		JLabel shortRange_label = new JLabel(entity_l10n.getString("Short_range"));
		JLabel longRange_label = new JLabel(entity_l10n.getString("Long_range"));
		A_Range range_attack = (A_Range) theAttackType;
		shortRange_field.setText( Integer.toString(range_attack.getS_range()) );
		longRange_field.setText( Integer.toString(range_attack.getL_range()) );
		
		GroupLayout range_layout = new GroupLayout(attackType_panel);
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
		attackType_panel.setLayout(range_layout);
	}

	private void createMeleePanel() {
		theAttackType.setPersonal(false);
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		attackType_panel.removeAll();
		
		JLabel reach_label = new JLabel(entity_l10n.getString("Reach_melee"));
		A_Melee melee_attack = (A_Melee) theAttackType;
		reach_field.setText( Integer.toString(melee_attack.getReach()) );
		
		GroupLayout melee_layout= new GroupLayout(attackType_panel);
		melee_layout.setHorizontalGroup( melee_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(reach_label)
				.addComponent(reach_field)
				);
		melee_layout.setVerticalGroup( melee_layout.createSequentialGroup()
				.addComponent(reach_label)
				.addComponent(reach_field)
				);
		attackType_panel.setLayout(melee_layout);
		
	}

	private void createClosePanel() {
		theAttackType.setPersonal(false);
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		attackType_panel.removeAll();
		
		JLabel closeSize_label = new JLabel(entity_l10n.getString("Size_close"));
		JLabel closeType_label = new JLabel(entity_l10n.getString("Type_close"));
		A_Close close_attack = (A_Close) theAttackType;
		closeType_field.setSelectedItem( close_attack.getCloseType() );
		closeSize_field.setText( Integer.toString(close_attack.getSize()) );
				
		GroupLayout close_layout = new GroupLayout(attackType_panel);
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
		attackType_panel.setLayout(close_layout);
		
	}

	private void createAreaPanel() {
		theAttackType.setPersonal(false);
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		attackType_panel.removeAll();
		
		JLabel areaRange_label = new JLabel(entity_l10n.getString("Range_area"));
		JLabel areaSize_label = new JLabel(entity_l10n.getString("Size_area"));
		JLabel areaType_label = new JLabel(entity_l10n.getString("Type_area"));
		A_Area area_attack = (A_Area) theAttackType;
		
		areaType_field.setSelectedItem( area_attack.getArea_type() );
		areaRange_field.setText( Integer.toString(area_attack.getArea_range()) );
		areaSize_field.setText( Integer.toString(area_attack.getArea_size()) );
		
		GroupLayout area_layout = new GroupLayout(attackType_panel);
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
		
		attackType_panel.setLayout(area_layout);
		
	}
	
	private void createPersonalPanel() {
		theAttackType.setPersonal(true);
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		attackType_panel.removeAll();
	}

}
