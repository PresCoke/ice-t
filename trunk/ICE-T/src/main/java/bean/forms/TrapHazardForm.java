package bean.forms;

import java.util.ResourceBundle;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import bean.combat.AttackBean;

import entity.Attack;
import entity.EntityEnum;
import entity.EntityM;
import entity.Resistance;
import entity.TrapHazard;

//TODO: value gets fucked up in GUI probably from typing in it!
//TODO: cannot delete fields LOL
public class TrapHazardForm implements FormBean {
	
	private TrapHazard theTrap;
	private JPanel trapForm_panel;
	
	private JTextField trapName_field;
	private JSpinner trapLevel_field;
	
	private JSpinner trapAvoidValue_field;
	private JComboBox trapAvoidSkill_field;
	
	private JComboBox trapCounterSkill_field;
	private JSpinner trapCounterDifficulty_field;
	private JEditorPane trapCounterText_field;
	
	private JComboBox trapType_list;
	private JComboBox trapRole_list;
	
	private JTextField trapTriggers_field;
	private JTextField trapXP_field;
	
	private AttackForm attackForm_bean;
	private JPanel attackForm_panel;
	
	
	public TrapHazardForm() {
		
		trapForm_panel = new JPanel();
		
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		
		JLabel trapName_label = new JLabel(entity_l10n.getString("Name_trap"));
		trapName_field = new JTextField();
		trapName_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
					String delete = trapName_field.getText();
					if (delete.length() > 0) {
						delete = delete.substring(0, delete.length() -1);
						trapName_field.setText(delete);
						theTrap.setName(delete);
						ke.consume();
					}
				}
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				char key = ke.getKeyChar();
				String current = theTrap.getName();
				current+=key;
				theTrap.setName(current);
				trapName_field.setText(current);
				ke.consume();
			}
		});
		
		JLabel trapLevel_label = new JLabel(entity_l10n.getString("Level_trap"));
		trapLevel_field = new JSpinner( new SpinnerNumberModel(0, 0, 30, 1) );
		trapLevel_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int level = (Integer) ((JSpinner) ce.getSource()).getValue();
				theTrap.setLevel(level);
			}
		});
		
		JLabel trapRole_label = new JLabel(entity_l10n.getString("Role_trap"));
		String[] trapRole = { entity_l10n.getString("Blaster_trap"),
							  entity_l10n.getString("Lurker_trap"),
							  entity_l10n.getString("Obstacle_trap"),
							  entity_l10n.getString("Warder_trap"),
							  entity_l10n.getString("Solo_trap"),
							  entity_l10n.getString("Elite_trap")}; 

		trapRole_list = new JComboBox(trapRole);
		trapRole_list.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theTrap.setRole(EntityEnum.T_Role.blaster); break;
				case 1:
					theTrap.setRole(EntityEnum.T_Role.lurker); break;
				case 2:
					theTrap.setRole(EntityEnum.T_Role.obstacle); break;
				case 3:
					theTrap.setRole(EntityEnum.T_Role.warder); break;
				case 4:
					theTrap.setRole(EntityEnum.T_Role.solo); break;
				case 5:
					theTrap.setRole(EntityEnum.T_Role.elite); break;
				}
			}
		});
		
		String[] trapType = { entity_l10n.getString("Trap_trap"),
							  entity_l10n.getString("Hazard_trap") };
		
		JLabel trapType_label = new JLabel(entity_l10n.getString("Type_trap"));
		trapType_list = new JComboBox(trapType);
		trapType_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theTrap.setType(EntityEnum.T_Type.trap); break;
				case 1:
					theTrap.setType(EntityEnum.T_Type.hazard); break;
				}
			}
		});
		
		JLabel trapAvoidSkill_label = new JLabel(entity_l10n.getString("AvoidSkill_trap"));
		String[] trapAvoidSkills = { entity_l10n.getString("ACRO_trap"),
									 entity_l10n.getString("ATHL_trap"),
									 entity_l10n.getString("ARCA_trap"),
									 entity_l10n.getString("BLUF_trap"),
									 entity_l10n.getString("DIPL_trap"),
									 entity_l10n.getString("DUNG_trap"),
									 entity_l10n.getString("ENDU_trap"),
									 entity_l10n.getString("HEAL_trap"),
									 entity_l10n.getString("HIST_trap"),
									 entity_l10n.getString("INSI_trap"),
									 entity_l10n.getString("INTI_trap"),
									 entity_l10n.getString("NATU_trap"),
									 entity_l10n.getString("PERC_trap"),
									 entity_l10n.getString("RELI_trap"),
									 entity_l10n.getString("STEA_trap"),
									 entity_l10n.getString("STRE_trap"),
									 entity_l10n.getString("THIE_trap") };
		trapAvoidSkill_field = new JComboBox(trapAvoidSkills);
		trapAvoidSkill_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.acrobatics); break;
				case 1:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.athletics); break;
				case 2:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.arcana); break;
				case 3:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.bluff); break;
				case 4:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.diplomacy); break;
				case 5:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.dungeoneering); break;
				case 6:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.endurance); break;
				case 7:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.heal); break;
				case 8:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.history); break;
				case 9:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.insight); break;
				case 10:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.intimidate); break;
				case 11:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.nature); break;
				case 12:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.perception); break;
				case 13:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.religion); break;
				case 14:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.stealth); break;
				case 15:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.streetwise); break;
				case 16:
					theTrap.setSkill(EntityEnum.T_CounterMeasureSkill.thievery); break;		
				}
			}
		});
	
		JLabel trapAvoidValue_label = new JLabel(entity_l10n.getString("AvoidValue_trap"));
		trapAvoidValue_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		trapAvoidValue_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int avoidValue = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theTrap.setAvoidance(avoidValue);
			}
		});
		
		JLabel trapCounterSkill_label = new JLabel(entity_l10n.getString("CounterSkill_trap"));
		trapCounterSkill_field = new JComboBox(trapAvoidSkills);
		trapCounterSkill_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.acrobatics); break;
				case 1:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.athletics); break;
				case 2:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.arcana); break;
				case 3:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.bluff); break;
				case 4:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.diplomacy); break;
				case 5:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.dungeoneering); break;
				case 6:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.endurance); break;
				case 7:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.heal); break;
				case 8:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.history); break;
				case 9:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.insight); break;
				case 10:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.intimidate); break;
				case 11:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.nature); break;
				case 12:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.perception); break;
				case 13:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.religion); break;
				case 14:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.stealth); break;
				case 15:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.streetwise); break;
				case 16:
					theTrap.setCounterMeasureSkill(EntityEnum.T_CounterMeasureSkill.thievery); break;		
				}
			}
		});
		JLabel trapCounterDifficulty_label = new JLabel(entity_l10n.getString("CounterDifficulty_trap"));
		trapCounterDifficulty_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		trapCounterDifficulty_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int difficulty = (Integer) ((JSpinner) ce.getSource()).getValue();
				theTrap.setDifficultyLevel(difficulty);
			}
		});
		JLabel trapCounterText_label = new JLabel(entity_l10n.getString("CounterText_trap"));
		trapCounterText_field = new JEditorPane();
		trapCounterText_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
					String delete = trapCounterText_field.getText();
					if (delete.length() > 0) {
						delete = delete.substring(0, delete.length() -1);
						trapCounterText_field.setText(delete);
						theTrap.setCounterMeasureDescription(delete);
						ke.consume();
					}
				}
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				char key = ke.getKeyChar();
				String current = theTrap.getCounterMeasureDescription();
				current+=key;
				theTrap.setCounterMeasureDescription(current);
				trapCounterText_field.setText(current);
				ke.consume();
			}
		});
		JLabel trapTriggers_label = new JLabel(entity_l10n.getString("Trigger_trap"));
		trapTriggers_field = new JTextField();
		trapTriggers_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
					String deleting = trapTriggers_field.getText();
					if (deleting.length() >= 1) {
						deleting = deleting.substring(0, deleting.length() - 1);
						trapTriggers_field.setText(deleting);
						theTrap.setTriggers(deleting);
						ke.consume();
					}
				}
				
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
				char key = ke.getKeyChar();
				String current = theTrap.getTriggers();
				current += key;
				trapTriggers_field.setText(current);
				theTrap.setTriggers(current);
				ke.consume();
			}
		});
		JLabel trapXP_label = new JLabel(entity_l10n.getString("XP_entity"));
		trapXP_field = new JTextField();
		trapXP_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				char key = ke.getKeyChar();
				if (key == KeyEvent.VK_BACK_SPACE || key == KeyEvent.VK_DELETE) {
					String deleting = trapXP_field.getText();
					if (deleting.length() >= 1) {
						deleting = deleting.substring(0, deleting.length() - 1);
						trapXP_field.setText(deleting);
						theTrap.setXp( Integer.parseInt(deleting) ); 
						ke.consume();
					}
				}
			}
			public void keyReleased(KeyEvent ke) {
				
			}
			public void keyTyped(KeyEvent ke) {
				int key = ke.getKeyChar() - '0';
				if (key >= 0 && key <= 9) {
					key += 10 * theTrap.getXp();
					trapXP_field.setText( Integer.toString(key) );
					theTrap.setXp(key);
					ke.consume();
				}
			}
		});
		
		attackForm_bean = new AttackForm();
		attackForm_panel = attackForm_bean.createEntityPanel();
		
		GroupLayout trapForm_layout = new GroupLayout(trapForm_panel);
		trapForm_layout.setAutoCreateGaps(true);
		trapForm_layout.setHorizontalGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( trapForm_layout.createSequentialGroup()
						.addComponent(trapName_label)
						.addComponent(trapName_field)
						.addComponent(trapLevel_label)
						.addComponent(trapLevel_field)
						.addComponent(trapRole_label)
						.addComponent(trapRole_list)
						)
				.addGroup( trapForm_layout.createSequentialGroup()
						.addComponent(trapType_label)
						.addComponent(trapType_list)
						.addComponent(trapXP_label)
						.addComponent(trapXP_field))
				.addGroup( trapForm_layout.createSequentialGroup() 
						.addComponent(trapAvoidSkill_label)
						.addComponent(trapAvoidSkill_field)
						.addComponent(trapAvoidValue_label)
						.addComponent(trapAvoidValue_field))
				.addGroup( trapForm_layout.createSequentialGroup()
						.addComponent(trapTriggers_label)
						.addComponent(trapTriggers_field))
				.addGroup( trapForm_layout.createSequentialGroup()
						.addComponent(trapCounterSkill_label)
						.addComponent(trapCounterSkill_field)
						.addComponent(trapCounterDifficulty_label)
						.addComponent(trapCounterDifficulty_field))
				.addComponent(trapCounterText_label)
				.addComponent(trapCounterText_field)
				.addComponent(attackForm_panel)
				);
		trapForm_layout.setVerticalGroup( trapForm_layout.createSequentialGroup()
				.addGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(trapName_label)
						.addComponent(trapName_field)
						.addComponent(trapLevel_label)
						.addComponent(trapLevel_field)
						.addComponent(trapRole_label)
						.addComponent(trapRole_list)
						)
				.addGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(trapType_label)
						.addComponent(trapType_list)
						.addComponent(trapXP_label)
						.addComponent(trapXP_field))
				.addGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(trapAvoidSkill_label)
						.addComponent(trapAvoidSkill_field)
						.addComponent(trapAvoidValue_label)
						.addComponent(trapAvoidValue_field))
				.addGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(trapTriggers_label)
						.addComponent(trapTriggers_field))
				.addGroup( trapForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(trapCounterSkill_label)
						.addComponent(trapCounterSkill_field)
						.addComponent(trapCounterDifficulty_label)
						.addComponent(trapCounterDifficulty_field))
				.addComponent(trapCounterText_label)
				.addComponent(trapCounterText_field)
				.addComponent(attackForm_panel)
				);
		/*traphazardForm_layout.linkSize(SwingConstants.VERTICAL, traphazardValue_field, traphazardValue_label);*/
		// what's the issue here?
		trapForm_panel.setLayout(trapForm_layout);
	}

	public JPanel createEntityPanel() {
		theTrap = new TrapHazard();
		
		createPanel();
		
		return trapForm_panel;
	}
	
	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof Resistance) {
			theTrap = (TrapHazard) usingThis;
		}
		createPanel();
		
		return trapForm_panel;
	}

	public Object getEntity() {
		
		Attack aAttack = (Attack) attackForm_bean.getEntity();
		theTrap.setAttack(aAttack);
		return theTrap;
	}
	
	private void createPanel() {

		trapName_field.setText( theTrap.getName() );
		trapLevel_field.setValue( theTrap.getLevel() );
		
		trapAvoidValue_field.setValue( theTrap.getAvoidance() );
		trapAvoidSkill_field.setSelectedIndex( theTrap.getSkill().ordinal() );
		
		trapCounterSkill_field.setSelectedIndex( theTrap.getCounterMeasureSkill().ordinal() );
		trapCounterDifficulty_field.setValue( theTrap.getDifficultyLevel() );
		trapCounterText_field.setText( theTrap.getCounterMeasureDescription() );
		
		trapType_list.setSelectedIndex( theTrap.getType().ordinal() );
		trapRole_list.setSelectedIndex( theTrap.getRole().ordinal() );
		
		trapTriggers_field.setText( theTrap.getTriggers() );
		trapXP_field.setText( Integer.toString(theTrap.getXp()) );
		
		attackForm_bean.createPanelFromExistingEntity( theTrap.getAttack() );
		
	}

}
