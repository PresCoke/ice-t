package bean.forms;

import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;

import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;

import entity.A_Area;
import entity.A_Close;
import entity.A_Melee;
import entity.A_Range;
import entity.Attack;
import entity.Attack_Type;
import entity.EntityEnum;

public class AttackForm implements FormBean {

	private Attack theAttack;
	private Attack_Type theAttackType;
	private JPanel attack_panel;
	private JPanel attackType_panel;
	private JComboBox attackEffect_list;
	private JComboBox attackDamage_field;
	private JComboBox attackAbility_field;
	private JComboBox attackDefense_field;
	private JComboBox attackAction_field;
	private JComboBox attackSustain_field;
	private JComboBox attackUse_field;
	private JCheckBox attackBasic_field;
	private JTextField attackName_field;
	private JTextField attackPrimary_field;
	private JTextField attackSecondary_field;
	private JTextField attackAccessories_field;
	private JTextField attackPwr_field;
	private JTextField attackHit_field;
	private JTextField attackMiss_field;
	private JTextField attackTrigger_field;
	private JRadioButton attackTypeRange_button;
	private JRadioButton attackTypeMelee_button;
	private JRadioButton attackTypeClose_button;
	private JRadioButton attackTypeArea_button;
	private JRadioButton attackTypePrsnl_button;
	private AttackTypeForm attackType_form;
	
	
	public AttackForm() {
		
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		attack_panel = new JPanel();
		attackType_panel = new JPanel();
		
		JLabel attackName_label = new JLabel(entity_l10n.getString("Name_attack"));
		attackName_field = new JTextField();
		attackName_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getAttackName();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setAttackName(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getAttackName();
				current += ke.getKeyChar();
				theAttack.setAttackName(current);
			}
			
		});
		
		JLabel attackPrimary_label = new JLabel(entity_l10n.getString("Primary_attack"));
		attackPrimary_field = new JTextField();
		attackPrimary_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getPrimaryTarget();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setPrimaryTarget(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getPrimaryTarget();
				current += ke.getKeyChar();
				theAttack.setPrimaryTarget(current);
			}
			
		});
		
		JLabel attackSecondary_label = new JLabel(entity_l10n.getString("Secondary_attack"));
		attackSecondary_field = new JTextField();
		attackSecondary_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getSecondaryTarget();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setSecondaryTarget(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getSecondaryTarget();
				current += ke.getKeyChar();
				theAttack.setSecondaryTarget(current);
			}
			
		});
		
		JLabel attackAccessories_label = new JLabel(entity_l10n.getString("Accessories_attack"));
		attackAccessories_field = new JTextField();
		attackAccessories_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getAccessories();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setAccessories(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getAccessories();
				current += ke.getKeyChar();
				theAttack.setAccessories(current);
			}
			
		});
		
		JLabel attackPwr_label = new JLabel(entity_l10n.getString("Power_entity"));
		attackPwr_field = new JTextField();
		attackPwr_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getPowerSource();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setPowerSource(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getPowerSource();
				current += ke.getKeyChar();
				theAttack.setPowerSource(current);
			}
			
		});
		
		JLabel attackEffect_label = new JLabel(entity_l10n.getString("Effect_attack"));
		String[] attack_effect = { entity_l10n.getString("Charm_attack"),
								   entity_l10n.getString("Conjuration_attack"),
								   entity_l10n.getString("Fear_attack"),
								   entity_l10n.getString("Healing_attack"),
								   entity_l10n.getString("Illusion_attack"),
								   entity_l10n.getString("Poison_attack"),
								   entity_l10n.getString("Polymorph_attack"),
								   entity_l10n.getString("Reliable_attack"),
								   entity_l10n.getString("Sleep_attack"),
								   entity_l10n.getString("Stance_attack"),
								   entity_l10n.getString("Teleportation_attack"),
								   entity_l10n.getString("Zone_attack"),
								   entity_l10n.getString("None_attack")};
		attackEffect_list = new JComboBox(attack_effect);
		attackEffect_list.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.charm); break;
				case 1:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.conjuration); break;
				case 2:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.fear); break;
				case 3:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.healing); break;
				case 4:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.illusion); break;
				case 5:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.poison); break;
				case 6:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.polymorph); break;
				case 7:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.reliable); break;
				case 8:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.sleep); break;
				case 9:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.stance); break;
				case 10:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.teleportation); break;
				case 11:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.zone); break;
				default:
					theAttack.setEffectType(EntityEnum.A_Effect_Type.NONE); break;
				}
			}
		});
		
		JLabel attackDamage_label = new JLabel(entity_l10n.getString("Damage_attack"));
		String[] attackDamage = { entity_l10n.getString("Acid_resist"),
								  entity_l10n.getString("Cold_resist"),
								  entity_l10n.getString("Fire_resist"),
								  entity_l10n.getString("Force_resist"),
								  entity_l10n.getString("Lightning_resist"),
								  entity_l10n.getString("Necrotic_resist"),
								  entity_l10n.getString("Poison_resist"),
								  entity_l10n.getString("Psychic_resist"),
								  entity_l10n.getString("Radiant_resist"),
								  entity_l10n.getString("Thunder_resist"),
								  entity_l10n.getString("None_attack")};
		attackDamage_field = new JComboBox(attackDamage);
		attackDamage_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.acid); break;
				case 1:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.cold); break;
				case 2:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.fire); break;
				case 3:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.force); break;
				case 4:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.lightning); break;
				case 5:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.necrotic); break;
				case 6:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.poison); break;
				case 7:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.psychic); break;
				case 8:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.radiant); break;
				case 9:
					theAttack.setDamageType(entity.EntityEnum.CS_Resistance_Type.thunder); break;
				default:
					theAttack.setDamageType(EntityEnum.CS_Resistance_Type.NONE);						
				}
			}
		});
		
		JLabel attackAbility_label = new JLabel(entity_l10n.getString("Ability_attack"));
		String[] attackAbilities = { entity_l10n.getString("STR_attack"),
									 entity_l10n.getString("CON_attack"),
									 entity_l10n.getString("DEX_attack"),
									 entity_l10n.getString("INT_attack"),
									 entity_l10n.getString("WIS_attack"),
									 entity_l10n.getString("CHA_attack")};
		attackAbility_field = new JComboBox(attackAbilities);
		attackAbility_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theAttack.setAbility(EntityEnum.A_Ability.STR); break;
				case 1:
					theAttack.setAbility(EntityEnum.A_Ability.CON); break;
				case 2:
					theAttack.setAbility(EntityEnum.A_Ability.DEX); break;
				case 3:
					theAttack.setAbility(EntityEnum.A_Ability.INT); break;
				case 4:
					theAttack.setAbility(EntityEnum.A_Ability.WIS); break;
				case 5:
					theAttack.setAbility(EntityEnum.A_Ability.CHAR); break;
				}
			}
		});
		
		JLabel attackDefense_label = new JLabel(entity_l10n.getString("Defense_attack"));
		String[] attackDefense = { entity_l10n.getString("AC_attack"),
								   entity_l10n.getString("REF_attack"),
								   entity_l10n.getString("FORT_attack"),
								   entity_l10n.getString("WILL_attack")};
		attackDefense_field = new JComboBox(attackDefense);
		attackDefense_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theAttack.setDefense(EntityEnum.A_Defense.AC); break;
				case 1:
					theAttack.setDefense(EntityEnum.A_Defense.REF); break;
				case 2:
					theAttack.setDefense(EntityEnum.A_Defense.FORT); break;
				case 3:
					theAttack.setDefense(EntityEnum.A_Defense.WILL); break;
				}
			}
		});
		
		JLabel attackHit_label = new JLabel(entity_l10n.getString("Hit_attack"));
		attackHit_field = new JTextField();
		attackHit_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getHit();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setHit(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getHit();
				current += ke.getKeyChar();
				theAttack.setHit(current);
			}
			
		});
		
		JLabel attackMiss_label = new JLabel(entity_l10n.getString("Miss_attack"));
		attackMiss_field = new JTextField();
		attackMiss_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getMiss();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setMiss(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getMiss();
				current += ke.getKeyChar();
				theAttack.setMiss(current);
			}
			
		});
		
		JLabel attackAction_label = new JLabel(entity_l10n.getString("Action_attack"));
		String[] attackAction = { entity_l10n.getString("Standard_attack"),
								  entity_l10n.getString("Move_attack"),
								  entity_l10n.getString("Minor_attack"),
								  entity_l10n.getString("Free_attack")};
		attackAction_field = new JComboBox(attackAction);
		attackAction_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theAttack.setAction(EntityEnum.A_Action.standard); break;
				case 1:
					theAttack.setAction(EntityEnum.A_Action.move); break;
				case 2:
					theAttack.setAction(EntityEnum.A_Action.minor); break;
				case 3:
					theAttack.setAction(EntityEnum.A_Action.free); break;
				}
			}
		});
		
		JLabel attackSustain_label = new JLabel(entity_l10n.getString("Sustain_attack"));
		String[] sustainAction = { entity_l10n.getString("Standard_attack"),
				  entity_l10n.getString("Move_attack"),
				  entity_l10n.getString("Minor_attack"),
				  entity_l10n.getString("None_attack")};
		attackSustain_field = new JComboBox(sustainAction);
		attackSustain_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theAttack.setSustain(EntityEnum.A_Sustain.standard); break;
				case 1:
					theAttack.setSustain(EntityEnum.A_Sustain.move); break;
				case 2:
					theAttack.setSustain(EntityEnum.A_Sustain.minor); break;
				case 3:
					theAttack.setSustain(EntityEnum.A_Sustain.NONE); break;
				}
			}
		});
		
		JLabel attackBasic_label = new JLabel(entity_l10n.getString("Basic_attack"));
		attackBasic_field = new JCheckBox();
		attackBasic_field.setSelected(false);
		attackBasic_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if ( ((JCheckBox) ae.getSource()).isSelected() ) {
					theAttack.setBasic(true);
				} else {
					theAttack.setBasic(false);
				}
			}
		});
		
		JLabel attackUse_label = new JLabel(entity_l10n.getString("Use_attack"));
		String[] attackUse = { entity_l10n.getString("AtWill_attack"),
							   entity_l10n.getString("Encounter_attack"),
							   entity_l10n.getString("Daily_attack"),
							   entity_l10n.getString("Recharge_attack")};
		attackUse_field = new JComboBox(attackUse);
		attackUse_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theAttack.setUseType(EntityEnum.A_Use_Type.atWill); break;
				case 1:
					theAttack.setUseType(EntityEnum.A_Use_Type.encounter); break;
				case 2:
					theAttack.setUseType(EntityEnum.A_Use_Type.daily); break;
				case 3:
					theAttack.setUseType(EntityEnum.A_Use_Type.recharge); break;
				}
			}
		});
		
		JLabel attackTrigger_label = new JLabel(entity_l10n.getString("Trigger_attack"));
		attackTrigger_field = new JTextField();
		attackTrigger_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theAttack.getTrigger();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theAttack.setTrigger(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theAttack.getTrigger();
				current += ke.getKeyChar();
				theAttack.setTrigger(current);
			}
			
		});
		
		attackType_form = new AttackTypeForm();
		ButtonGroup attackType_buttongroup = new ButtonGroup();
		attackTypeRange_button = new JRadioButton(entity_l10n.getString("Ranged_attack"));
		attackTypeRange_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theAttackType = new A_Range();
				attackType_panel = attackType_form.createPanelFromExistingEntity(theAttackType);
			}
		});
		attackTypeMelee_button = new JRadioButton(entity_l10n.getString("Ranged_attack"));
		attackTypeMelee_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theAttackType = new A_Melee();
				attackType_panel = attackType_form.createPanelFromExistingEntity(theAttackType);
			}
		});
		attackTypeClose_button = new JRadioButton(entity_l10n.getString("Ranged_attack"));
		attackTypeClose_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theAttackType = new A_Close();
				attackType_panel = attackType_form.createPanelFromExistingEntity(theAttackType);
			}
		});
		attackTypeArea_button  = new JRadioButton(entity_l10n.getString("Ranged_attack"));
		attackTypeArea_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theAttackType = new A_Area();
				attackType_panel = attackType_form.createPanelFromExistingEntity(theAttackType);
			}
		});
		attackTypePrsnl_button = new JRadioButton(entity_l10n.getString(""));
		attackTypePrsnl_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theAttackType = new Attack_Type();
				attackType_panel = attackType_form.createPanelFromExistingEntity(theAttackType);
			}
		});
		attackTypePrsnl_button.setSelected(true);
		
		attackType_buttongroup.add(attackTypeRange_button);
		attackType_buttongroup.add(attackTypeMelee_button);
		attackType_buttongroup.add(attackTypeClose_button);
		attackType_buttongroup.add(attackTypeArea_button);
		attackType_buttongroup.add(attackTypePrsnl_button);
		
		GroupLayout attack_layout = new GroupLayout(attack_panel);
		
		attack_layout.setHorizontalGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( attack_layout.createSequentialGroup()
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackUse_label)
								.addComponent(attackUse_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackAbility_label)
								.addComponent(attackAbility_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackDefense_label)
								.addComponent(attackDefense_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackAction_label)
								.addComponent(attackAction_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackHit_label)
								.addComponent(attackHit_field))
						)
				.addGroup( attack_layout.createSequentialGroup()
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackName_label)
								.addComponent(attackName_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackPrimary_label)
								.addComponent(attackPrimary_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackSecondary_label)
								.addComponent(attackSecondary_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackSustain_label)
								.addComponent(attackSustain_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackMiss_label)
								.addComponent(attackMiss_field))
						
						)
				.addGroup( attack_layout.createSequentialGroup()
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackTrigger_label)
								.addComponent(attackTrigger_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackBasic_label)
								.addComponent(attackBasic_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackDamage_label)
								.addComponent(attackDamage_field))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackEffect_label)
								.addComponent(attackEffect_list))
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackPwr_label)
								.addComponent(attackPwr_field))
						)
				.addGroup( attack_layout.createSequentialGroup()
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(attackAccessories_label)
								.addComponent(attackAccessories_field))
						)
				.addGroup( attack_layout.createSequentialGroup()
						.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(attackTypePrsnl_button)
								.addComponent(attackTypeRange_button)
								.addComponent(attackTypeMelee_button)
								.addComponent(attackTypeArea_button)
								.addComponent(attackTypeClose_button))
						.addComponent(attackType_panel)		
						)
				);
		
		attack_layout.setVerticalGroup( attack_layout.createSequentialGroup()
				.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackUse_label)
								.addComponent(attackUse_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackAbility_label)
								.addComponent(attackAbility_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackDefense_label)
								.addComponent(attackDefense_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackAction_label)
								.addComponent(attackAction_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackHit_label)
								.addComponent(attackHit_field))
						)
				.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackName_label)
								.addComponent(attackName_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackPrimary_label)
								.addComponent(attackPrimary_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackSecondary_label)
								.addComponent(attackSecondary_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackSustain_label)
								.addComponent(attackSustain_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackMiss_label)
								.addComponent(attackMiss_field))
						
						)
				.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackTrigger_label)
								.addComponent(attackTrigger_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackBasic_label)
								.addComponent(attackBasic_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackDamage_label)
								.addComponent(attackDamage_field))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackEffect_label)
								.addComponent(attackEffect_list))
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackPwr_label)
								.addComponent(attackPwr_field))
						)
				.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackAccessories_label)
								.addComponent(attackAccessories_field))
						)
				.addGroup( attack_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addGroup( attack_layout.createSequentialGroup()
								.addComponent(attackTypePrsnl_button)
								.addComponent(attackTypeRange_button)
								.addComponent(attackTypeMelee_button)
								.addComponent(attackTypeArea_button)
								.addComponent(attackTypeClose_button))
						.addComponent(attackType_panel)		
						)
				);
		//TODO: link sizes
		attack_panel.setLayout(attack_layout);
	}
	
	public JPanel createEntityPanel() {
		theAttack = new Attack();
		createPanel();
		return attack_panel;

	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof Attack) {
			theAttack = (Attack) usingThis;
		} else {
			theAttack = new Attack();
		}
		createPanel();
		return attack_panel;
	}

	public Object getEntity() {
		theAttack.setAttackType(theAttackType);
		return theAttack;
	}
	
	private void createPanel() {
		
	}

}
