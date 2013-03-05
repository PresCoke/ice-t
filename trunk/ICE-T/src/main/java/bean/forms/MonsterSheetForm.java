package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import controller.App_Root;

import bean.combat.AttackBean;
import bean.combat.ResistanceBean;

import java.awt.*;
import java.awt.event.*;

import entity.Attack;
import entity.CharacterSheet;
import entity.Resistance;

/*
 * TODO: going to newTab, entering data, switching to another tab and then returning to the newTab causes the Bean to tile ugily
 * TODO: I'm really starting to think it was better to split up resistance form and resistance list display
 * 		the reason being if the add button is outside the character sheet form then how the hell am I going to know when to 
 * 		add resistances and get them out. 
 */
public class MonsterSheetForm implements FormBean, KeyListener, ActionListener {
	private JPanel totalBean_panel;
	GroupLayout totalBean_layout;
	JButton next_button;

	private CharacterSheet theMonster;
	private JPanel monsterForm_panel;
	private JTextField maxHP_field, bloodied_field, surgeValue_field, surgeNum_field;
	private JTextField name_field;
	private JComboBox role_list, size_list;
	private JTextField xp_field;
	private JSpinner lvl_field;
	private JTextField speed_field;
	private JTextField init_field;
	private JSpinner str_field, con_field, dex_field,
					 int_field, wis_field, cha_field;
	private JSpinner ac_field, ref_field, fort_field, will_field;
	private JSpinner acro_field, athl_field, arca_field, bluf_field,
					 dipl_field, dung_field, endu_field, heal_field, 
					 hist_field, insi_field, inti_field, natu_field, 
					 perc_field, reli_field, stea_field, stre_field,
					 thie_field;
	private JEditorPane lang_field, racef_field, misc_field;

	private JComboBox monsterType_field, monsterOrigin_field;

	private ResistanceForm resistanceForm_bean;
	private JPanel resistanceForm_panel;
	private JList resistance_list;
	DefaultListModel resistance_list_model;

	private AttackForm attackForm_bean;
	private JPanel attackForm_panel;
	private JPanel attackForm_majorpanel;
	private JList attack_list;
	DefaultListModel attack_list_model;

	public MonsterSheetForm() {
		name_field = new JTextField();
		xp_field = new JTextField();
		speed_field = new JTextField();
		init_field = new JTextField();
		maxHP_field = new JTextField();
		bloodied_field = new JTextField();
		bloodied_field.setEditable(false);
		surgeValue_field = new JTextField();
		surgeValue_field.setEditable(false);
		surgeNum_field = new JTextField();
		lang_field = new JEditorPane();
		racef_field = new JEditorPane();
		misc_field = new JEditorPane();
	}

	public JPanel createEntityPanel() {
		theMonster = new CharacterSheet();
		theMonster.setNPC(true);
		createPanel();

		return totalBean_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof CharacterSheet) {
			theMonster = (CharacterSheet) usingThis;
			if (!theMonster.isNPC()) {
				theMonster = new CharacterSheet();
			}
		}

		createPanel();

		return totalBean_panel;
	}

	public Object getEntity() {
		theMonster.setName(name_field.getText());
		theMonster.setXP(Integer.parseInt(xp_field.getText()));
		theMonster.setSpeed(Integer.parseInt(speed_field.getText()));
		theMonster.setInitiative(Integer.parseInt(init_field.getText()));
		theMonster.setMaxHP(Integer.parseInt(maxHP_field.getText()));
		theMonster.setSurgesPerDay(Integer.parseInt(surgeNum_field.getText()));
		theMonster.setLanguages(lang_field.getText());
		theMonster.setRaceFeatures(racef_field.getText());
		theMonster.setMisc(misc_field.getText());
		theMonster.setNPC(true);

		return theMonster;
	}

	private void createPanel() {
		monsterForm_panel = new JPanel();
		GroupLayout characterForm_layout = new GroupLayout(monsterForm_panel);

		final ResourceBundle entity_l10n = ResourceBundle.getBundle(
				"filters.beanGUI_l10n.Entity",
				controller.App_Root.language_locale);

		/*
		 * 
		 * General Info
		 */
		JPanel generalInfo_panel = new JPanel();
		generalInfo_panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		// Character Name
		JLabel name_label = new JLabel(entity_l10n.getString("Name_entity"));
		name_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		name_field.setText(theMonster.getName());

		// level
		JLabel lvl_label = new JLabel(entity_l10n.getString("LVL_entity"));
		SpinnerNumberModel lvl_model = new SpinnerNumberModel(1, 1, 30, 1);
		lvl_field = new JSpinner(lvl_model);
		lvl_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int lvl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setLevel(lvl);
			}
		});
		((JSpinner.DefaultEditor) lvl_field.getEditor()).getTextField()
			.addKeyListener(new KeyListener() {
				public void keyPressed(KeyEvent ke) {
				
				}

				public void keyReleased(KeyEvent ke) {

				}

				public void keyTyped(KeyEvent ke) {
					int key = ke.getKeyChar() - '0';
					if (key < 0 || key > 9) {
						ke.consume();
					}
				}
			});
		lvl_field.setValue(theMonster.getLevel());
		// XP
		JLabel xp_label = new JLabel(entity_l10n.getString("XP_entity"));
		xp_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		xp_field.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent ke) {
				int xp = ke.getKeyChar() - '0';
				if (xp < 0 || xp > 9) { // ensure health is an integer
					ke.consume();
				}
			}
		});
		xp_field.setText(Integer.toString(theMonster.getXP()));
		// speed
		JLabel speed_label = new JLabel(entity_l10n.getString("Speed_entity"));
		speed_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		speed_field.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent ke) {
				int speed = ke.getKeyChar() - '0';
				if (speed < 0 || speed > 9) { // ensure health is an integer
					ke.consume();
				}
			}
		});
		speed_field.setText(Integer.toString(theMonster.getSpeed()));
		// initiative
		JLabel init_label = new JLabel(entity_l10n.getString("Init_entity"));
		init_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		init_field.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent ke) {
				int initiative = ke.getKeyChar() - '0';
				if (initiative < 0 || initiative > 9) { // ensure health is an
														// integer
					ke.consume();
				}
			}
		});
		init_field.setText(Integer.toString(theMonster.getInitiative()));
		// role
		JLabel role_label = new JLabel(entity_l10n.getString("Role_entity"));
		role_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		// TODO: remove hardcoding - separate file iterate through all keys
		String[] roles = { entity_l10n.getString("Striker_role"),
						   entity_l10n.getString("Controller_role"),
						   entity_l10n.getString("Defender_role"),
						   entity_l10n.getString("Leader_role"),
						   entity_l10n.getString("Artillery_role"),
						   entity_l10n.getString("Brute_role"),
						   entity_l10n.getString("Lurker_role"),
						   entity_l10n.getString("Skirmisher_role"),
						   entity_l10n.getString("Soldier_role") };
		role_list = new JComboBox(roles);
		role_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theMonster.setRole(entity.EntityEnum.CS_Role.striker);
					break;
				case 1:
					theMonster.setRole(entity.EntityEnum.CS_Role.controller);
					break;
				case 2:
					theMonster.setRole(entity.EntityEnum.CS_Role.defender);
					break;
				case 3:
					theMonster.setRole(entity.EntityEnum.CS_Role.leader);
					break;
				case 4:
					theMonster.setRole(entity.EntityEnum.CS_Role.artillery);
					break;
				case 5:
					theMonster.setRole(entity.EntityEnum.CS_Role.brute);
					break;
				case 6:
					theMonster.setRole(entity.EntityEnum.CS_Role.lurker);
					break;
				case 7:
					theMonster.setRole(entity.EntityEnum.CS_Role.skirmisher);
					break;
				case 8:
					theMonster.setRole(entity.EntityEnum.CS_Role.soldier);
					break;
				}
			}
		});
		if (theMonster.getRole() != null) {
			role_list.setSelectedIndex(theMonster.getRole().ordinal());
		} else {
			role_list.setSelectedIndex(0);
		}
		// size
		JLabel size_label = new JLabel(entity_l10n.getString("Size_entity"));
		size_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		// TODO: remove hard-coding - separate file (iterate through all keys)
		String[] sizes = { entity_l10n.getString("Tiny_size"),
						   entity_l10n.getString("Small_size"),
						   entity_l10n.getString("Medium_size"),
						   entity_l10n.getString("Large_size"),
						   entity_l10n.getString("Huge_size"),
						   entity_l10n.getString("Gargantuan_size") };
		size_list = new JComboBox(sizes);
		size_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theMonster.setSize(entity.EntityEnum.CS_Size.tiny);
					break;
				case 1:
					theMonster.setSize(entity.EntityEnum.CS_Size.small);
					break;
				case 2:
					theMonster.setSize(entity.EntityEnum.CS_Size.medium);
					break;
				case 3:
					theMonster.setSize(entity.EntityEnum.CS_Size.large);
					break;
				case 4:
					theMonster.setSize(entity.EntityEnum.CS_Size.huge);
					break;
				case 5:
					theMonster.setSize(entity.EntityEnum.CS_Size.gargantuan);
					break;
				}
			}
		});
		if (theMonster.getSize() != null) {
			size_list.setSelectedIndex(theMonster.getSize().ordinal());
		} else {
			size_list.setSelectedIndex(0);
		}

		JLabel monsterOrigin_label = new JLabel(entity_l10n.getString("MonsterOrigin_label"));
		String[] origins = { entity_l10n.getString("Aberrant_origin"),
							 entity_l10n.getString("Elemental_origin"),
							 entity_l10n.getString("Fey_origin"),
							 entity_l10n.getString("Immortal_origin"),
							 entity_l10n.getString("Shadow_origin") };
		monsterOrigin_field = new JComboBox(origins);
		monsterOrigin_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theMonster.setMonsterOrigin(entity.EntityEnum.CS_Monster_Origin.aberrant);
					break;
				case 1:
					theMonster.setMonsterOrigin(entity.EntityEnum.CS_Monster_Origin.elemental);
					break;
				case 2:
					theMonster.setMonsterOrigin(entity.EntityEnum.CS_Monster_Origin.fey);
					break;
				case 3:
					theMonster.setMonsterOrigin(entity.EntityEnum.CS_Monster_Origin.immortal);
					break;
				case 4:
					theMonster.setMonsterOrigin(entity.EntityEnum.CS_Monster_Origin.shadow);
					break;
				}
			}
		});
		if (theMonster.getMonsterOrigin() != null) {
			monsterOrigin_field.setSelectedIndex(theMonster.getMonsterOrigin().ordinal());
		} else {
			monsterOrigin_field.setSelectedIndex(0);
		}

		JLabel monsterType_label = new JLabel(entity_l10n.getString("MonsterType_label"));
		String[] monsterTypes = { entity_l10n.getString("Animate_monster"),
								  entity_l10n.getString("Beast_monster"),
								  entity_l10n.getString("Humanoid_monster"),
								  entity_l10n.getString("MagicalBeast_monster") };
		monsterType_field = new JComboBox(monsterTypes);
		monsterType_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int index = ((JComboBox) ae.getSource()).getSelectedIndex();
				switch (index) {
				case 0:
					theMonster.setMonsterType(entity.EntityEnum.CS_Monster_Type.animate);
					break;
				case 1:
					theMonster.setMonsterType(entity.EntityEnum.CS_Monster_Type.beast);
					break;
				case 2:
					theMonster.setMonsterType(entity.EntityEnum.CS_Monster_Type.humanoid);
					break;
				case 3:
					theMonster.setMonsterType(entity.EntityEnum.CS_Monster_Type.magicalBeast);
					break;
				}
			}
		});
		if (theMonster.getMonsterType() != null) {
			monsterType_field.setSelectedIndex(theMonster.getMonsterType().ordinal());
		} else {
			monsterType_field.setSelectedIndex(0);
		}

		GroupLayout generalInfo_layout = new GroupLayout(generalInfo_panel);
		generalInfo_layout.setAutoCreateGaps(true);
		generalInfo_layout.setHorizontalGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(generalInfo_layout.createSequentialGroup()
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(name_label)
														.addComponent(name_field))
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(lvl_label)
														.addComponent(lvl_field))
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(xp_label)
														.addComponent(xp_field))
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(speed_label)
														.addComponent(speed_field))
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(init_label)
														.addComponent(init_field))
										.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
														.addComponent(monsterOrigin_label)
														.addComponent(monsterOrigin_field)))
						.addGroup(generalInfo_layout.createSequentialGroup()
										.addComponent(size_label)
										.addComponent(size_list)
										.addComponent(role_label)
										.addComponent(role_list)
										.addComponent(monsterType_label)
										.addComponent(monsterType_field))

				);
		generalInfo_layout.setVerticalGroup(generalInfo_layout.createSequentialGroup()
				.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(name_label)
												.addComponent(name_field))
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(lvl_label)
												.addComponent(lvl_field))
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(xp_label)
												.addComponent(xp_field))
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(speed_label)
												.addComponent(speed_field))
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(init_label)
												.addComponent(init_field))
								.addGroup(generalInfo_layout.createSequentialGroup()
												.addComponent(monsterOrigin_label)
												.addComponent(monsterOrigin_field)))
				.addGroup(generalInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(size_label)
								.addComponent(size_list)
								.addComponent(role_label)
								.addComponent(role_list)
								.addComponent(monsterType_label)
								.addComponent(monsterType_field)));

		generalInfo_panel.setLayout(generalInfo_layout);

		/*
		 * 
		 * Ability Scores
		 */
		JPanel ability_panel = new JPanel();
		GroupLayout ability_layout = new GroupLayout(ability_panel);
		ability_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				entity_l10n.getString("Ability_title")));
		// STR
		JLabel str_label = new JLabel(entity_l10n.getString("STR_entity"));
		str_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		str_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setSTR(abl);
			}
		});
		((JSpinner.DefaultEditor) str_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		str_field.setValue(theMonster.getSTR());

		// CON
		JLabel con_label = new JLabel(entity_l10n.getString("CON_entity"));
		con_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		con_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setCON(abl);
			}
		});
		((JSpinner.DefaultEditor) con_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		con_field.setValue(theMonster.getCON());

		// INT
		JLabel int_label = new JLabel(entity_l10n.getString("INT_entity"));
		int_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		int_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setINT(abl);
			}
		});
		((JSpinner.DefaultEditor) int_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		int_field.setValue(theMonster.getINT());

		// DEX
		JLabel dex_label = new JLabel(entity_l10n.getString("DEX_entity"));
		dex_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		dex_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setDEX(abl);
			}
		});
		((JSpinner.DefaultEditor) dex_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		dex_field.setValue(theMonster.getDEX());

		// WIS
		JLabel wis_label = new JLabel(entity_l10n.getString("WIS_entity"));
		wis_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		wis_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setWIS(abl);
			}
		});
		((JSpinner.DefaultEditor) wis_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		wis_field.setValue(theMonster.getWIS());

		// CHA
		JLabel cha_label = new JLabel(entity_l10n.getString("CHA_entity"));
		cha_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		cha_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setCHAR(abl);
			}
		});
		((JSpinner.DefaultEditor) cha_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		cha_field.setValue(theMonster.getCHAR());

		ability_layout.setAutoCreateGaps(true);
		ability_layout.setHorizontalGroup(ability_layout.createSequentialGroup()
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(str_label)
								.addComponent(str_field))
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(con_label)
								.addComponent(con_field))
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(dex_label)
								.addComponent(dex_field))
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(int_label)
								.addComponent(int_field))
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(wis_label)
								.addComponent(wis_field))
				.addGroup(ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(cha_label)
								.addComponent(cha_field)));
		ability_layout.setVerticalGroup(ability_layout.createParallelGroup()
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(str_label)
								.addComponent(str_field))
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(con_label)
								.addComponent(con_field))
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(dex_label)
								.addComponent(dex_field))
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(int_label)
								.addComponent(int_field))
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(wis_label)
								.addComponent(wis_field))
				.addGroup(ability_layout.createSequentialGroup()
								.addComponent(cha_label)
								.addComponent(cha_field))

		);
		ability_layout.linkSize(SwingConstants.VERTICAL, str_field, str_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, str_field, str_label);

		ability_layout.linkSize(SwingConstants.VERTICAL, con_field, con_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, con_field, con_label);

		ability_layout.linkSize(SwingConstants.VERTICAL, dex_field, dex_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, dex_field, dex_label);

		ability_layout.linkSize(SwingConstants.VERTICAL, int_field, int_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, int_field, int_label);

		ability_layout.linkSize(SwingConstants.VERTICAL, wis_field, wis_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, wis_field, wis_label);

		ability_layout.linkSize(SwingConstants.VERTICAL, cha_field, cha_label);
		ability_layout.linkSize(SwingConstants.HORIZONTAL, cha_field, cha_label);

		ability_panel.setLayout(ability_layout);

		/*
		 * 
		 * Defenses
		 */
		JPanel defense_panel = new JPanel();
		GroupLayout defense_layout = new GroupLayout(defense_panel);
		defense_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				entity_l10n.getString("Defense_title")));
		// AC
		JLabel ac_label = new JLabel(entity_l10n.getString("AC_entity"));
		ac_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		ac_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setAC(def);
			}
		});
		((JSpinner.DefaultEditor) ac_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		ac_field.setValue(theMonster.getAC());

		// REF
		JLabel ref_label = new JLabel(entity_l10n.getString("REF_entity"));
		ref_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		ref_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setREF(def);
			}
		});
		((JSpinner.DefaultEditor) ref_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		ref_field.setValue(theMonster.getREF());

		// FORT
		JLabel fort_label = new JLabel(entity_l10n.getString("FORT_entity"));
		fort_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		fort_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setFORT(def);
			}
		});
		((JSpinner.DefaultEditor) fort_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		fort_field.setValue(theMonster.getFORT());

		// WILL
		JLabel will_label = new JLabel(entity_l10n.getString("WILL_entity"));
		will_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		will_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setWILL(def);
			}
		});
		((JSpinner.DefaultEditor) will_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		will_field.setValue(theMonster.getWILL());

		defense_layout.setAutoCreateGaps(true);
		defense_layout.setHorizontalGroup(defense_layout.createSequentialGroup()
				.addGroup(defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(ac_label)
								.addComponent(ac_field))
				.addGroup(defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(ref_label)
								.addComponent(ref_field))
				.addGroup(defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(fort_label)
								.addComponent(fort_field))
				.addGroup(defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(will_label)
								.addComponent(will_field)));
		defense_layout.setVerticalGroup(defense_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(defense_layout.createSequentialGroup()
								.addComponent(ac_label)
								.addComponent(ac_field))
				.addGroup(defense_layout.createSequentialGroup()
								.addComponent(ref_label)
								.addComponent(ref_field))
				.addGroup(defense_layout.createSequentialGroup()
								.addComponent(fort_label)
								.addComponent(fort_field))
				.addGroup(defense_layout.createSequentialGroup()
								.addComponent(will_label)
								.addComponent(will_field))
				);
		defense_layout.linkSize(SwingConstants.VERTICAL, ac_field, ac_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, ref_field, ref_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, fort_field, fort_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, will_field, will_label);

		defense_panel.setLayout(defense_layout);
		/*
		 * 
		 * Skills
		 */
		JPanel skill_panel = new JPanel();
		skill_panel.setLayout(new GridLayout(0, 2));
		skill_panel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.GRAY),
				entity_l10n.getString("Skill_title")));
		// acrobatics
		JLabel acro_label = new JLabel(entity_l10n.getString("ACRO_entity"));
		acro_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		acro_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setAcrobatics(skill);
			}
		});
		((JSpinner.DefaultEditor) acro_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		acro_field.setValue(theMonster.getAcrobatics());
		skill_panel.add(acro_label);
		skill_panel.add(acro_field);
		// athletics
		JLabel athl_label = new JLabel(entity_l10n.getString("ATHL_entity"));
		athl_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		athl_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setAthletics(skill);
			}
		});
		((JSpinner.DefaultEditor) athl_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		athl_field.setValue(theMonster.getAthletics());
		skill_panel.add(athl_label);
		skill_panel.add(athl_field);
		// arcana
		JLabel arca_label = new JLabel(entity_l10n.getString("ARCA_entity"));
		arca_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		arca_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setArcana(skill);
			}
		});
		((JSpinner.DefaultEditor) arca_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		arca_field.setValue(theMonster.getArcana());
		skill_panel.add(arca_label);
		skill_panel.add(arca_field);
		// bluff
		JLabel bluf_label = new JLabel(entity_l10n.getString("BLUF_entity"));
		bluf_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		bluf_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setBluff(skill);
			}
		});
		((JSpinner.DefaultEditor) bluf_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		bluf_field.setValue(theMonster.getBluff());
		skill_panel.add(bluf_label);
		skill_panel.add(bluf_field);
		// diplomacy
		JLabel dipl_label = new JLabel(entity_l10n.getString("DIPL_entity"));
		dipl_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		dipl_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setDiplomacy(skill);
			}
		});
		((JSpinner.DefaultEditor) dipl_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		dipl_field.setValue(theMonster.getDiplomacy());
		skill_panel.add(dipl_label);
		skill_panel.add(dipl_field);
		// dungeoneering
		JLabel dung_label = new JLabel(entity_l10n.getString("DUNG_entity"));
		dung_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		dung_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setDungeoneering(skill);
			}
		});
		((JSpinner.DefaultEditor) dung_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		dung_field.setValue(theMonster.getDungeoneering());
		skill_panel.add(dung_label);
		skill_panel.add(dung_field);
		// endurance
		JLabel endu_label = new JLabel(entity_l10n.getString("ENDU_entity"));
		endu_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		endu_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setEndurance(skill);
			}
		});
		((JSpinner.DefaultEditor) endu_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		endu_field.setValue(theMonster.getEndurance());
		skill_panel.add(endu_label);
		skill_panel.add(endu_field);
		// heal
		JLabel heal_label = new JLabel(entity_l10n.getString("HEAL_entity"));
		heal_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		heal_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setHeal(skill);
			}
		});
		((JSpinner.DefaultEditor) heal_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		heal_field.setValue(theMonster.getHeal());
		skill_panel.add(heal_label);
		skill_panel.add(heal_field);
		// history
		JLabel hist_label = new JLabel(entity_l10n.getString("HIST_entity"));
		hist_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		hist_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setHistory(skill);
			}
		});
		((JSpinner.DefaultEditor) hist_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		hist_field.setValue(theMonster.getHistory());
		skill_panel.add(hist_label);
		skill_panel.add(hist_field);
		// insight
		JLabel insi_label = new JLabel(entity_l10n.getString("INSI_entity"));
		insi_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		insi_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setInsight(skill);
			}
		});
		((JSpinner.DefaultEditor) insi_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		insi_field.setValue(theMonster.getInsight());
		skill_panel.add(insi_label);
		skill_panel.add(insi_field);
		// intimidate
		JLabel inti_label = new JLabel(entity_l10n.getString("INTI_entity"));
		inti_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		inti_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setIntimidate(skill);
			}
		});
		((JSpinner.DefaultEditor) inti_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		inti_field.setValue(theMonster.getIntimidate());
		skill_panel.add(inti_label);
		skill_panel.add(inti_field);
		// nature
		JLabel natu_label = new JLabel(entity_l10n.getString("NATU_entity"));
		natu_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		natu_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setNature(skill);
			}
		});
		((JSpinner.DefaultEditor) natu_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		natu_field.setValue(theMonster.getNature());
		skill_panel.add(natu_label);
		skill_panel.add(natu_field);
		// perception
		JLabel perc_label = new JLabel(entity_l10n.getString("PERC_entity"));
		perc_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		perc_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setPerception(skill);
			}
		});
		((JSpinner.DefaultEditor) perc_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		perc_field.setValue(theMonster.getPerception());
		skill_panel.add(perc_label);
		skill_panel.add(perc_field);
		// religion
		JLabel reli_label = new JLabel(entity_l10n.getString("RELI_entity"));
		reli_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		reli_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setReligion(skill);
			}
		});
		((JSpinner.DefaultEditor) reli_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		reli_field.setValue(theMonster.getReligion());
		skill_panel.add(reli_label);
		skill_panel.add(reli_field);
		// stealth
		JLabel stea_label = new JLabel(entity_l10n.getString("STEA_entity"));
		stea_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		stea_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setStealth(skill);
			}
		});
		((JSpinner.DefaultEditor) stea_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		stea_field.setValue(theMonster.getStealth());
		skill_panel.add(stea_label);
		skill_panel.add(stea_field);
		// streetwise
		JLabel stre_label = new JLabel(entity_l10n.getString("STRE_entity"));
		stre_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		stre_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setStreetwise(skill);
			}
		});
		((JSpinner.DefaultEditor) stre_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		stre_field.setValue(theMonster.getStreetwise());
		skill_panel.add(stre_label);
		skill_panel.add(stre_field);
		// theivery
		JLabel thie_label = new JLabel(entity_l10n.getString("THIE_entity"));
		thie_field = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		thie_field.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theMonster.setThievery(skill);
			}
		});
		((JSpinner.DefaultEditor) thie_field.getEditor()).getTextField()
				.addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent ke) {
					}

					public void keyReleased(KeyEvent ke) {

					}

					public void keyTyped(KeyEvent ke) {
						int key = ke.getKeyChar() - '0';
						if (key < 0 || key > 9) {
							ke.consume();
						}
					}
				});
		thie_field.setValue(theMonster.getThievery());
		skill_panel.add(thie_label);
		skill_panel.add(thie_field);

		/*
		 * 
		 * Health+Surges/Day
		 */
		JPanel health_panel = new JPanel();
		GroupLayout health_layout = new GroupLayout(health_panel);
		// maxHP
		JLabel maxHP_label = new JLabel(entity_l10n.getString("MAXHP_entity"));
		maxHP_field.addKeyListener(this);
		maxHP_field.setText(Integer.toString(theMonster.getMaxHP()));
		// bloodied
		JLabel bloodied_label = new JLabel(entity_l10n.getString("Bloodied_entity"));
		bloodied_field.setText(Integer.toString(theMonster.getBloodied()));
		// surge value
		JLabel surgeValue_label = new JLabel(entity_l10n.getString("SurgeValue_entity"));
		surgeValue_field.setText(Integer.toString(theMonster.getSurgesValue()));
		// surges/day
		JLabel surgeNum_label = new JLabel(entity_l10n.getString("SurgeNum_entity"));
		surgeNum_field.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent ke) {
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent ke) {
				int surge_num = ke.getKeyChar() - '0';
				if (surge_num < 0 || surge_num > 9) {
					ke.consume();
				}
			}
		});
		surgeNum_field.setText(Integer.toString(theMonster.getSurgesPerDay()));

		health_layout.setAutoCreateGaps(true);
		health_layout.setHorizontalGroup(health_layout.createSequentialGroup()
				.addGroup(health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(maxHP_label)
								.addComponent(maxHP_field))
				.addGroup(health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(bloodied_label)
								.addComponent(bloodied_field))
				.addGroup(health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(surgeValue_label)
								.addComponent(surgeValue_field))
				.addGroup(health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(surgeNum_label)
								.addComponent(surgeNum_field)));
		health_layout.setVerticalGroup(health_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup(health_layout.createSequentialGroup()
								.addComponent(maxHP_label)
								.addComponent(maxHP_field))
				.addGroup(health_layout.createSequentialGroup()
								.addComponent(bloodied_label)
								.addComponent(bloodied_field))
				.addGroup(health_layout.createSequentialGroup()
								.addComponent(surgeValue_label)
								.addComponent(surgeValue_field))
				.addGroup(health_layout.createSequentialGroup()
								.addComponent(surgeNum_label)
								.addComponent(surgeNum_field)));
		health_layout.linkSize(SwingConstants.VERTICAL, maxHP_field, maxHP_label);
		health_layout.linkSize(SwingConstants.VERTICAL, bloodied_field, bloodied_label);
		health_layout.linkSize(SwingConstants.VERTICAL, surgeValue_field, surgeValue_label);
		health_layout.linkSize(SwingConstants.VERTICAL, surgeNum_field, surgeNum_label);

		health_panel.setLayout(health_layout);

		/*
		 * 
		 * Other
		 */
		JPanel otherInfo_panel = new JPanel();
		GroupLayout otherInfo_layout = new GroupLayout(otherInfo_panel);
		// race features
		JLabel racef_label = new JLabel(entity_l10n.getString("RaceF_entity"));
		racef_field.setPreferredSize(new Dimension(50, 50));
		racef_field.setText(theMonster.getRaceFeatures());
		// resistances
		resistanceForm_bean = new ResistanceForm();
		resistanceForm_panel = resistanceForm_bean.createEntityPanel();
		JButton addResist_button = new JButton(entity_l10n.getString("Add_button"));
		addResist_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Resistance addThis = (Resistance) resistanceForm_bean.getEntity();
				if (addThis.getResistanceType() != null && addThis.getResistanceValue() != 0) {
					theMonster.addResistance(addThis);
					resistance_list_model.addElement(addThis);
					resistanceForm_panel = resistanceForm_bean.createEntityPanel();
				}
			}
		});

		JButton removeResist_button = new JButton(entity_l10n.getString("Remove_button"));
		removeResist_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int selected = resistance_list.getSelectedIndex();
				if (selected != -1) {
					resistance_list_model.remove(selected);
					theMonster.removeResistanceAt(selected);
				}
				monsterForm_panel.repaint();
			}
		});
		resistance_list_model = new DefaultListModel();
		for (int index = 0; index < theMonster.getNumberOfResistances(); index++) {
			ResistanceBean temp_bean = new ResistanceBean();
			temp_bean.createPanelFrom(theMonster.getResistanceAt(index));
			resistance_list_model.addElement(temp_bean);
		}

		resistance_list = new JList(resistance_list_model);
		resistance_list.setLayoutOrientation(JList.VERTICAL);
		resistance_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resistance_list.setCellRenderer(new ResistanceBean());
		JScrollPane resistance_pane = new JScrollPane(resistance_list);

		// languages
		JLabel lang_label = new JLabel(entity_l10n.getString("Lang_entity"));
		lang_field.setPreferredSize(new Dimension(50, 50));
		lang_field.setText(theMonster.getLanguages());

		JLabel misc_label = new JLabel(entity_l10n.getString("Misc_entity"));
		// TODO: for easy finding
		misc_field.setPreferredSize(new Dimension(50, 50));
		misc_field.setText(theMonster.getMisc());

		otherInfo_layout.setAutoCreateGaps(true);
		otherInfo_layout.setHorizontalGroup(otherInfo_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(racef_label)
				.addComponent(racef_field)
				.addComponent(lang_label)
				.addComponent(lang_field)
				.addComponent(misc_label)
				.addComponent(misc_field)
				.addGroup(otherInfo_layout.createSequentialGroup()
					.addComponent(resistanceForm_panel)
					.addGroup(otherInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(addResist_button)
						.addComponent(removeResist_button))
					)
					.addComponent(resistance_pane)
				);
		otherInfo_layout.setVerticalGroup(otherInfo_layout.createSequentialGroup()
				.addComponent(racef_label)
				.addComponent(racef_field)
				.addComponent(lang_label)
				.addComponent(lang_field)
				.addComponent(misc_label)
				.addComponent(misc_field)
				.addGroup(otherInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(resistanceForm_panel)
					.addGroup(otherInfo_layout.createSequentialGroup()
						.addComponent(addResist_button)
						.addComponent(removeResist_button))
						)
				.addComponent(resistance_pane)
				);
		otherInfo_panel.setLayout(otherInfo_layout);

		attackForm_bean = new AttackForm();
		attackForm_panel = attackForm_bean.createEntityPanel();
		JButton addAttack_button = new JButton(entity_l10n.getString("Add_button"));
		addAttack_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Attack addThis = (Attack) attackForm_bean.getEntity();
				theMonster.addAttack(addThis);
				attackForm_panel = attackForm_bean.createEntityPanel();
				attack_list_model.addElement(addThis);
			}
		});
		JButton removeAttack_button = new JButton(entity_l10n.getString("Remove_button"));
		removeAttack_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int selected = attack_list.getSelectedIndex();
				if (selected != -1) {
					attack_list_model.remove(selected);
					theMonster.removeAttackAt(selected);
				}
				monsterForm_panel.repaint();
			}
		});

		attack_list_model = new DefaultListModel();
		for (int index = 0; index < theMonster.getNumberOfAttacks(); index++) {
			AttackBean temp_bean = new AttackBean();
			temp_bean.createPanelFrom(theMonster.getAttackAt(index));
			attack_list_model.addElement(temp_bean);
		}

		attack_list = new JList(attack_list_model);
		attack_list.setLayoutOrientation(JList.VERTICAL);
		attack_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		attack_list.setCellRenderer(new AttackBean());

		JScrollPane attack_pane = new JScrollPane(attack_list);

		attackForm_majorpanel = new JPanel();
		GroupLayout attackForm_layout = new GroupLayout(attackForm_majorpanel);
		attackForm_layout.setAutoCreateGaps(true);
		attackForm_layout.setHorizontalGroup(attackForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(attackForm_panel)
				.addGroup(attackForm_layout.createSequentialGroup()
						.addComponent(attack_pane)
						.addGroup(attackForm_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(addAttack_button)
							.addComponent(removeAttack_button))
						)
				);

		attackForm_layout.setVerticalGroup(attackForm_layout.createSequentialGroup()
				.addComponent(attackForm_panel)
				.addGroup(attackForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(attack_pane)
						.addGroup(attackForm_layout.createSequentialGroup()
							.addComponent(addAttack_button)
							.addComponent(removeAttack_button))
						)
				);
		attackForm_majorpanel.setLayout(attackForm_layout);

		characterForm_layout.setAutoCreateGaps(true);
		characterForm_layout.setHorizontalGroup(characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(generalInfo_panel)
				.addGroup(characterForm_layout.createSequentialGroup()
						.addGroup(characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(ability_panel)
								.addComponent(defense_panel)
								.addComponent(health_panel)
								.addComponent(otherInfo_panel))
						.addComponent(skill_panel))
				);
		characterForm_layout.setVerticalGroup(characterForm_layout.createSequentialGroup()
				.addComponent(generalInfo_panel)
				.addGroup(characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(characterForm_layout.createSequentialGroup()
								.addComponent(ability_panel)
								.addComponent(defense_panel)
								.addComponent(health_panel)
								.addComponent(otherInfo_panel))
						.addComponent(skill_panel))
				);

		monsterForm_panel.setLayout(characterForm_layout);
		monsterForm_panel.setAutoscrolls(true);

		next_button = new JButton(entity_l10n.getString("Next_button"));
		next_button.addActionListener(this);

		totalBean_panel = new JPanel();
		totalBean_layout = new GroupLayout(totalBean_panel);
		totalBean_layout.setHorizontalGroup(totalBean_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
				.addComponent(next_button)
				.addComponent(monsterForm_panel));
		totalBean_layout.setVerticalGroup(totalBean_layout.createSequentialGroup()
				.addComponent(next_button)
				.addComponent(monsterForm_panel));
		totalBean_panel.setLayout(totalBean_layout);
	}

	public void keyPressed(KeyEvent ke) {
		// TODO: something has to be done about this.
		int health = 0;
		int key_press = ke.getKeyCode();
		if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
			health = theMonster.getMaxHP();
			String deleted_string = Integer.toString(health);
			if (deleted_string.length() > 1) {
				deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
				health = Integer.parseInt(deleted_string);
				theMonster.setMaxHP(health);
				bloodied_field.setText(((Integer) theMonster.getBloodied()).toString());
				surgeValue_field.setText(((Integer) theMonster.getSurgesValue()).toString());
			} else {
				theMonster.setMaxHP(0);
				bloodied_field.setText(((Integer) theMonster.getBloodied()).toString());
				surgeValue_field.setText(((Integer) theMonster.getSurgesValue()).toString());
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Something has to be done about this
	}

	public void keyTyped(KeyEvent ke) {
		// TODO Something has to be done about this
		int health = ke.getKeyChar() - '0';
		if (health >= 0 && health <= 9) { // ensure health is an integer
			health += theMonster.getMaxHP() * 10;
			theMonster.setMaxHP(health);
			bloodied_field.setText(((Integer) theMonster.getBloodied()).toString());
			surgeValue_field.setText(((Integer) theMonster.getSurgesValue()).toString());
		} else {
			ke.consume();
		}
	}

	public void actionPerformed(ActionEvent e) {
		ResourceBundle entity_l10n = ResourceBundle.getBundle(
				"filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		JButton theButton = (JButton) e.getSource();
		if (theButton.getText().equals(entity_l10n.getString("Next_button"))) {
			theButton.setText(entity_l10n.getString("Back_button"));
			totalBean_layout.removeLayoutComponent(monsterForm_panel);
			monsterForm_panel.setVisible(false);
			attackForm_panel.setVisible(true);
			totalBean_layout.setHorizontalGroup(totalBean_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(next_button)
					.addComponent(attackForm_majorpanel));
			totalBean_layout.setVerticalGroup(totalBean_layout.createSequentialGroup()
					.addComponent(next_button)
					.addComponent(attackForm_majorpanel));
			totalBean_panel.setLayout(totalBean_layout);

		} else if (theButton.getText().equals(
				entity_l10n.getString("Back_button"))) {
			theButton.setText(entity_l10n.getString("Next_button"));
			totalBean_layout.removeLayoutComponent(attackForm_panel);
			monsterForm_panel.setVisible(true);
			attackForm_panel.setVisible(false);
			totalBean_layout.setHorizontalGroup(totalBean_layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
					.addComponent(next_button)
					.addComponent(monsterForm_panel));
			totalBean_layout.setVerticalGroup(totalBean_layout.createSequentialGroup()
					.addComponent(next_button)
					.addComponent(monsterForm_panel));
			totalBean_panel.setLayout(totalBean_layout);
		}

	}

	public boolean validateEntity() {
		boolean isValidForm = true;
		String invalidFieldString = "";
		
		if (name_field.getText().equals("")) {
			isValidForm = false;
			invalidFieldString += "The name field is absent.\n";
		}
		if (xp_field.getText().equals("") || xp_field.getText().equals("0")) {
			isValidForm = false;
			invalidFieldString += "The xp field is zero or absent.\n";
		}
		if (speed_field.getText().equals("") || speed_field.getText().equals("0")) {
			isValidForm = false;
			invalidFieldString += "The speed field is zero or absent.\n";
		}
		if (init_field.getText().equals("")) {
			isValidForm = false;
			invalidFieldString += "The initiative field is zero or absent.\n";
		}
		
		if (maxHP_field.getText().equals("") || maxHP_field.getText().equals("0")) {
			isValidForm = false;
			invalidFieldString += "The Maximum HP field is zero or absent.\n";
		}
		
		if(attack_list.getModel().getSize() == 0){
			isValidForm = false;
			invalidFieldString += "The monster musts have at least one attack.\n";
		}
		
		if (!isValidForm) {
			JOptionPane.showMessageDialog(monsterForm_panel,
										  invalidFieldString,
										  "Monster Sheet",
										  JOptionPane.WARNING_MESSAGE);
		}
		
		return isValidForm;
	}

}
