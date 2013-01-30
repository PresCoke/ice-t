package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import bean.combat.ResistanceBean;

import java.awt.*;
import java.awt.event.*;

import entity.CharacterSheet;
import entity.Resistance;


/*
 * TODO: going to newTab, entering data, switching to another tab and then returning to the newTab causes the Bean to tile ugily
 * TODO: I'm really starting to think it was better to split up resistance form and resistance list display
 * 		the reason being if the add button is outside the character sheet form then how the hell am I going to know when to 
 * 		add resistances and get them out. 
 */
public class CharacterSheetForm implements FormBean, KeyListener {
	
	private CharacterSheet theCharacter;
	private JPanel characterForm_panel;
	private JTextField maxHP_field, bloodied_field, surgeValue_field;
	private ResistanceForm resistanceForm_bean;
	private JPanel resistanceForm_panel;
	private JList resistance_list;
	DefaultListModel resistance_list_model;
	//JScrollPane resistance_pane;
	
	public JPanel createEntityPanel() {
		theCharacter = new CharacterSheet();
		
		createPanel();
		
		return characterForm_panel;
	}
	
	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof CharacterSheet) {
			theCharacter = (CharacterSheet) usingThis;
		}
		
		createPanel();
		
		return characterForm_panel;
	}

	public Object getEntity() {
	
		return theCharacter;
	}
	
	private void createPanel() {
		characterForm_panel = new JPanel();
		GroupLayout characterForm_layout = new GroupLayout(characterForm_panel); 
		
		final ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.beanGUI_l10n.Entity", controller.App_Root.language_locale);
		
		/*
		 * 
		 * General Info
		 * 
		 */
		JPanel generalInfo_panel = new JPanel();
		generalInfo_panel.setBorder( BorderFactory.createLineBorder(Color.GRAY) );
		//Character Name
		JLabel name_label = new JLabel( entity_l10n.getString("Name_entity") );
		name_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField name_field = new JTextField();
		name_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getName();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setName(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getName();
				current += ke.getKeyChar();
				theCharacter.setName(current);
			}
			
		});
		name_field.setText(theCharacter.getName());
		
		//Player Name
		JLabel playerName_label = new JLabel( entity_l10n.getString("PlayerName_entity") );
		playerName_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField playerName_field = new JTextField();
		playerName_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getPlayerName();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setPlayerName(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getPlayerName();
				current += ke.getKeyChar();
				theCharacter.setPlayerName(current);
			}
			
		});
		playerName_field.setText(theCharacter.getPlayerName());		
		//level
		JLabel lvl_label = new JLabel(entity_l10n.getString("LVL_entity"));
		SpinnerNumberModel lvl_model = new SpinnerNumberModel(0, 0, 30, 1);
		JSpinner lvl_field = new JSpinner(lvl_model);
		lvl_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int lvl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setLevel(lvl);
			}
		});
		//surge_num
		JLabel xp_label = new JLabel(entity_l10n.getString("XP_entity") );
		xp_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField xp_field = new JTextField();
		xp_field.addKeyListener( new KeyListener(){
			public void keyPressed(KeyEvent ke) {
				int xp = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					xp = theCharacter.getXP();
					String deleted_string = Integer.toString(xp);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						xp = Integer.parseInt(deleted_string);
						theCharacter.setXP(xp);
					} else {
						theCharacter.setXP(0);
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
					int xp = ke.getKeyChar() - '0';
					if (xp >= 0 && xp <= 9) { // ensure health is an integer
						xp += theCharacter.getXP() * 10;
						theCharacter.setXP(xp);
					} else {
						ke.consume();
					}
				
			}
		});
		xp_field.setText( Integer.toString(theCharacter.getSurgesPerDay()) );		
		//speed
		JLabel speed_label = new JLabel(entity_l10n.getString("Speed_entity"));
		speed_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField speed_field = new JTextField();
		speed_field.addKeyListener( new KeyListener(){
			public void keyPressed(KeyEvent ke) {
				int speed = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					speed = theCharacter.getSpeed();
					String deleted_string = Integer.toString(speed);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						speed = Integer.parseInt(deleted_string);
						theCharacter.setSpeed(speed);
					} else {
						theCharacter.setSpeed(0);
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
					int speed = ke.getKeyChar() - '0';
					if (speed >= 0 && speed <= 9) { // ensure health is an integer
						speed += theCharacter.getSpeed() * 10;
						theCharacter.setSpeed(speed);
					} else {
						ke.consume();
					}
				
			}
		});
		speed_field.setText( Integer.toString(theCharacter.getSpeed()) );
		//initiative
		JLabel init_label = new JLabel(entity_l10n.getString("Init_entity"));
		init_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField init_field = new JTextField();
		init_field.addKeyListener( new KeyListener(){
			public void keyPressed(KeyEvent ke) {
				int initiative = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					initiative = theCharacter.getInitiative();
					String deleted_string = Integer.toString(initiative);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						initiative = Integer.parseInt(deleted_string);
						theCharacter.setInitiative(initiative);
					} else {
						theCharacter.setInitiative(0);
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
					int initiative = ke.getKeyChar() - '0';
					if (initiative >= 0 && initiative <= 9) { // ensure health is an integer
						initiative += theCharacter.getInitiative() * 10;
						theCharacter.setInitiative(initiative);
					} else {
						ke.consume();
					}
				
			}
		});
		init_field.setText( Integer.toString(theCharacter.getInitiative()) );
		//role
		JLabel role_label = new JLabel(entity_l10n.getString("Role_entity"));
		role_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		//TODO: remove hardcoding - separate file iterate through all keys
		String[] roles = {  entity_l10n.getString("Striker_role"),
							entity_l10n.getString("Controller_role"),
							entity_l10n.getString("Defender_role"),
							entity_l10n.getString("Leader_role"),
							entity_l10n.getString("Artillery_role"),
							entity_l10n.getString("Brute_role"),
							entity_l10n.getString("Lurker_role"),
							entity_l10n.getString("Skirmisher_role"),
							entity_l10n.getString("Soldier_role")};
		JComboBox role_list = new JComboBox(roles);
		role_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theCharacter.setRole(entity.EntityEnum.CS_Role.striker); break;
				case 1:
					theCharacter.setRole(entity.EntityEnum.CS_Role.controller); break;
				case 2:
					theCharacter.setRole(entity.EntityEnum.CS_Role.defender); break;
				case 3:
					theCharacter.setRole(entity.EntityEnum.CS_Role.leader); break;
				case 4:
					theCharacter.setRole(entity.EntityEnum.CS_Role.artillery); break;
				case 5:
					theCharacter.setRole(entity.EntityEnum.CS_Role.brute); break;
				case 6:
					theCharacter.setRole(entity.EntityEnum.CS_Role.lurker); break;
				case 7:
					theCharacter.setRole(entity.EntityEnum.CS_Role.skirmisher); break;
				case 8:
					theCharacter.setRole(entity.EntityEnum.CS_Role.soldier); break;
				}
			}
		});
		if (theCharacter.getRole() != null) {
			role_list.setSelectedItem( theCharacter.getRole() );
		}
		//size
		JLabel size_label = new JLabel(entity_l10n.getString("Size_entity"));
		size_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		//TODO: remove hard-coding - separate file (iterate through all keys)
		String[] sizes = {  entity_l10n.getString("Tiny_size"),
							entity_l10n.getString("Small_size"),
							entity_l10n.getString("Medium_size"),
							entity_l10n.getString("Large_size"),
							entity_l10n.getString("Huge_size"),
							entity_l10n.getString("Gargantuan_size") };
		JComboBox size_list = new JComboBox(sizes);
		size_list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = ((JComboBox) arg0.getSource()).getSelectedIndex();
				switch(index) {
				case 0:
					theCharacter.setSize(entity.EntityEnum.CS_Size.tiny); break;
				case 1:
					theCharacter.setSize(entity.EntityEnum.CS_Size.small); break;
				case 2:
					theCharacter.setSize(entity.EntityEnum.CS_Size.medium); break;
				case 3:
					theCharacter.setSize(entity.EntityEnum.CS_Size.large); break;
				case 4:
					theCharacter.setSize(entity.EntityEnum.CS_Size.huge); break;
				case 5:
					theCharacter.setSize(entity.EntityEnum.CS_Size.gargantuan); break;
				}
			}
		});
		if (theCharacter.getSize() != null) {
			size_list.setSelectedItem( theCharacter.getSize() );
		}
		//power source
		JLabel pwr_label = new JLabel(entity_l10n.getString("Power_entity"));
		pwr_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		JTextField pwr_field = new JTextField();
		pwr_field.addKeyListener( new KeyListener() {

			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getPowerSource();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setPowerSource(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getPowerSource();
				current += ke.getKeyChar();
				theCharacter.setPowerSource(current);
			}
			
		});
		pwr_field.setText(theCharacter.getPowerSource());
		
		GroupLayout generalInfo_layout = new GroupLayout(generalInfo_panel);
		generalInfo_layout.setAutoCreateGaps(true);
		generalInfo_layout.setHorizontalGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup( generalInfo_layout.createSequentialGroup()
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(name_label)
							.addComponent(name_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(playerName_label)
							.addComponent(playerName_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(lvl_label)
							.addComponent(lvl_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(xp_label)
							.addComponent(xp_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(speed_label)
							.addComponent(speed_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(init_label)
							.addComponent(init_field))
					.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(pwr_label)
							.addComponent(pwr_field))
					)
				.addGroup( generalInfo_layout.createSequentialGroup()
						.addComponent(size_label)
						.addComponent(size_list)
						.addGap(15)
						.addComponent(role_label)
						.addComponent(role_list))
						
				);
		generalInfo_layout.setVerticalGroup( generalInfo_layout.createSequentialGroup()
				.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(name_label)
								.addComponent(name_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(playerName_label)
								.addComponent(playerName_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(lvl_label)
								.addComponent(lvl_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(xp_label)
								.addComponent(xp_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(speed_label)
								.addComponent(speed_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(init_label)
								.addComponent(init_field))
						.addGroup( generalInfo_layout.createSequentialGroup()
								.addComponent(pwr_label)
								.addComponent(pwr_field))
						)
				.addGroup( generalInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(size_label)
						.addComponent(size_list)
						.addGap(15)
						.addComponent(role_label)
						.addComponent(role_list))
				);
		
		
		generalInfo_panel.setLayout(generalInfo_layout);
		
		/*
		 * 
		 * Ability Scores
		 * 
		 */
		JPanel ability_panel = new JPanel();
		GroupLayout ability_layout = new GroupLayout(ability_panel);
		ability_panel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), entity_l10n.getString("Ability_title")) );
		//STR
		JLabel str_label = new JLabel(entity_l10n.getString("STR_entity"));
		JSpinner str_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		str_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setSTR(abl);
			}
		});
		str_field.setValue(theCharacter.getSTR());
		
		//CON
		JLabel con_label = new JLabel(entity_l10n.getString("CON_entity"));
		JSpinner con_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		con_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setCON(abl);
			}
		});
		con_field.setValue(theCharacter.getCON());
		
		//INT
		JLabel int_label = new JLabel(entity_l10n.getString("INT_entity"));
		JSpinner int_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		int_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setINT(abl);
			}
		});
		int_field.setValue(theCharacter.getINT());
		
		//DEX
		JLabel dex_label = new JLabel(entity_l10n.getString("DEX_entity"));
		JSpinner dex_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		dex_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDEX(abl);
			}
		});
		dex_field.setValue(theCharacter.getDEX());
		
		//WIS
		JLabel wis_label = new JLabel(entity_l10n.getString("WIS_entity"));
		JSpinner wis_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		wis_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setWIS(abl);
			}
		});
		wis_field.setValue(theCharacter.getWIS());
		
		//CHA
		JLabel cha_label = new JLabel(entity_l10n.getString("CHA_entity"));
		JSpinner cha_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		cha_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setCHAR(abl);
			}
		});
		cha_field.setValue(theCharacter.getCHAR());
		
		ability_layout.setAutoCreateGaps(true);
		ability_layout.setHorizontalGroup( ability_layout.createSequentialGroup()
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(str_label)
						.addComponent(str_field))
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(con_label)
						.addComponent(con_field))
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(dex_label)
						.addComponent(dex_field))
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(int_label)
						.addComponent(int_field))
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(wis_label)
						.addComponent(wis_field))
				.addGroup( ability_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(cha_label)
						.addComponent(cha_field))
				);
		ability_layout.setVerticalGroup(ability_layout.createParallelGroup()
				.addGroup( ability_layout.createSequentialGroup()
						.addComponent(str_label)
						.addComponent(str_field))
				.addGroup( ability_layout.createSequentialGroup()
						.addComponent(con_label)
						.addComponent(con_field))
				.addGroup( ability_layout.createSequentialGroup()
						.addComponent(dex_label)
						.addComponent(dex_field))
				.addGroup( ability_layout.createSequentialGroup()
						.addComponent(int_label)
						.addComponent(int_field))
				.addGroup( ability_layout.createSequentialGroup()
						.addComponent(wis_label)
						.addComponent(wis_field))
				.addGroup( ability_layout.createSequentialGroup()
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
		 * 
		 */
		JPanel defense_panel = new JPanel();
		GroupLayout defense_layout = new GroupLayout(defense_panel);
		defense_panel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), entity_l10n.getString("Defense_title")) );
		//AC
		JLabel ac_label = new JLabel(entity_l10n.getString("AC_entity"));
		JSpinner ac_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		ac_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAC(def);
			}
		});
		ac_field.setValue(theCharacter.getAC());

		//REF
		JLabel ref_label = new JLabel(entity_l10n.getString("REF_entity"));
		JSpinner ref_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		ref_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setREF(def);
			}
		});
		ref_field.setValue(theCharacter.getREF());
		
		//FORT
		JLabel fort_label = new JLabel(entity_l10n.getString("FORT_entity"));
		JSpinner fort_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		fort_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setFORT(def);
			}
		});
		fort_field.setValue(theCharacter.getFORT());
		
		//WILL
		JLabel will_label = new JLabel(entity_l10n.getString("WILL_entity"));
		JSpinner will_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		will_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setWILL(def);
			}
		});
		will_field.setValue(theCharacter.getWILL());
		
		defense_layout.setAutoCreateGaps(true);
		defense_layout.setHorizontalGroup( defense_layout.createSequentialGroup()
				.addGroup( defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(ac_label)
						.addComponent(ac_field))
				.addGroup( defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(ref_label)
						.addComponent(ref_field))
				.addGroup( defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(fort_label)
						.addComponent(fort_field))
				.addGroup( defense_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(will_label)
						.addComponent(will_field))
				);
		defense_layout.setVerticalGroup( defense_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup( defense_layout.createSequentialGroup()
						.addComponent(ac_label)
						.addComponent(ac_field))
				.addGroup( defense_layout.createSequentialGroup()
						.addComponent(ref_label)
						.addComponent(ref_field))
				.addGroup( defense_layout.createSequentialGroup()
						.addComponent(fort_label)
						.addComponent(fort_field))
				.addGroup( defense_layout.createSequentialGroup()
						.addComponent(will_label)
						.addComponent(will_field))
				);
		defense_layout.linkSize(SwingConstants.VERTICAL, ac_field, ac_label);
		//defense_layout.linkSize(SwingConstants.HORIZONTAL, ac_field, ac_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, ref_field, ref_label);
		//defense_layout.linkSize(SwingConstants.HORIZONTAL, ref_field, ref_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, fort_field, fort_label);
		//defense_layout.linkSize(SwingConstants.HORIZONTAL, fort_field, fort_label);
		defense_layout.linkSize(SwingConstants.VERTICAL, will_field, will_label);
		//defense_layout.linkSize(SwingConstants.HORIZONTAL, will_field, will_label);
		
		defense_panel.setLayout(defense_layout);
		
		/*
		 * 
		 * Skills
		 * 
		 */
		JPanel skill_panel = new JPanel();
		skill_panel.setLayout( new GridLayout(0, 2) );
		skill_panel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), entity_l10n.getString("Skill_title")) );
		//acrobatics
		JLabel acro_label = new JLabel(entity_l10n.getString("ACRO_entity"));
		JSpinner acro_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		acro_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAcrobatics(skill);
			}
		});
		acro_field.setValue(theCharacter.getAcrobatics());
		skill_panel.add(acro_label); skill_panel.add(acro_field);
		//athletics
		JLabel athl_label = new JLabel(entity_l10n.getString("ATHL_entity"));
		JSpinner athl_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		athl_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAthletics(skill);
			}
		});
		athl_field.setValue(theCharacter.getAthletics());
		skill_panel.add(athl_label); skill_panel.add(athl_field);
		//arcana
		JLabel arca_label = new JLabel(entity_l10n.getString("ARCA_entity"));
		JSpinner arca_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		arca_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setArcana(skill);
			}
		});
		arca_field.setValue(theCharacter.getArcana());
		skill_panel.add(arca_label); skill_panel.add(arca_field);
		//bluff
		JLabel bluf_label = new JLabel(entity_l10n.getString("BLUF_entity"));
		JSpinner bluf_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		bluf_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setBluff(skill);
			}
		});
		bluf_field.setValue(theCharacter.getBluff());
		skill_panel.add(bluf_label); skill_panel.add(bluf_field);
		//diplomacy
		JLabel dipl_label = new JLabel(entity_l10n.getString("DIPL_entity"));
		JSpinner dipl_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		dipl_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDiplomacy(skill);
			}
		});
		dipl_field.setValue(theCharacter.getDiplomacy());
		skill_panel.add(dipl_label); skill_panel.add(dipl_field);
		//dungeoneering
		JLabel dung_label = new JLabel(entity_l10n.getString("DUNG_entity"));
		JSpinner dung_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		dung_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDungeoneering(skill);
			}
		});
		dung_field.setValue(theCharacter.getDungeoneering());
		skill_panel.add(dung_label); skill_panel.add(dung_field);
		//endurance
		JLabel endu_label = new JLabel(entity_l10n.getString("ENDU_entity"));
		JSpinner endu_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		endu_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setEndurance(skill);
			}
		});
		endu_field.setValue(theCharacter.getEndurance());
		skill_panel.add(endu_label); skill_panel.add(endu_field);
		//heal
		JLabel heal_label = new JLabel(entity_l10n.getString("HEAL_entity"));
		JSpinner heal_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		heal_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setHeal(skill);
			}
		});
		heal_field.setValue(theCharacter.getHeal());
		skill_panel.add(heal_label); skill_panel.add(heal_field);
		//history
		JLabel hist_label = new JLabel(entity_l10n.getString("HIST_entity"));
		JSpinner hist_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		hist_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setHistory(skill);
			}
		});
		hist_field.setValue(theCharacter.getHistory());
		skill_panel.add(hist_label); skill_panel.add(hist_field);
		//insight
		JLabel insi_label = new JLabel(entity_l10n.getString("INSI_entity"));
		JSpinner insi_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		insi_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setInsight(skill);
			}
		});
		insi_field.setValue(theCharacter.getInsight());
		skill_panel.add(insi_label); skill_panel.add(insi_field);
		//intimidate
		JLabel inti_label = new JLabel(entity_l10n.getString("INTI_entity"));
		JSpinner inti_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		inti_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setIntimidate(skill);
			}
		});
		inti_field.setValue(theCharacter.getIntimidate());
		skill_panel.add(inti_label); skill_panel.add(inti_field);
		//nature
		JLabel natu_label = new JLabel(entity_l10n.getString("NATU_entity"));
		JSpinner natu_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		natu_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setNature(skill);
			}
		});
		natu_field.setValue(theCharacter.getNature());
		skill_panel.add(natu_label); skill_panel.add(natu_field);
		//perception
		JLabel perc_label = new JLabel(entity_l10n.getString("PERC_entity"));
		JSpinner perc_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		perc_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setPerception(skill);
			}
		});
		perc_field.setValue(theCharacter.getPerception());
		skill_panel.add(perc_label); skill_panel.add(perc_field);
		//religion
		JLabel reli_label = new JLabel(entity_l10n.getString("RELI_entity"));
		JSpinner reli_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		reli_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setReligion(skill);
			}
		});
		reli_field.setValue(theCharacter.getReligion());
		skill_panel.add(reli_label); skill_panel.add(reli_field);
		//stealth
		JLabel stea_label = new JLabel(entity_l10n.getString("STEA_entity"));
		JSpinner stea_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		stea_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setStealth(skill);
			}
		});
		stea_field.setValue(theCharacter.getStealth());
		skill_panel.add(stea_label); skill_panel.add(stea_field);
		//streetwise
		JLabel stre_label = new JLabel(entity_l10n.getString("STRE_entity"));
		JSpinner stre_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		stre_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setStreetwise(skill);
			}
		});
		stre_field.setValue(theCharacter.getStreetwise());
		skill_panel.add(stre_label); skill_panel.add(stre_field);
		//theivery
		JLabel thie_label = new JLabel(entity_l10n.getString("THIE_entity"));
		JSpinner thie_field = new JSpinner( new SpinnerNumberModel(0, 0, 100, 1) );
		thie_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setThievery(skill);
			}
		});
		thie_field.setValue(theCharacter.getThievery());
		skill_panel.add(thie_label); skill_panel.add(thie_field);
		
		/*
		 * 
		 * Health+Surges/Day
		 * 
		 */
		JPanel health_panel = new JPanel();
		GroupLayout health_layout = new GroupLayout(health_panel);
		//health_panel.setBorder( BorderFactory.createLineBorder(Color.GRAY) );
		//maxHP
		JLabel maxHP_label = new JLabel(entity_l10n.getString("MAXHP_entity"));
		maxHP_field = new JTextField();
		maxHP_field.addKeyListener(this);
		maxHP_field.setText( Integer.toString(theCharacter.getMaxHP()) );
		//bloodied
		JLabel bloodied_label = new JLabel(entity_l10n.getString("Bloodied_entity"));
		bloodied_field = new JTextField(); bloodied_field.setEditable(false);
		bloodied_field.setText( Integer.toString(theCharacter.getBloodied()) );
		//surge value
		JLabel surgeValue_label = new JLabel(entity_l10n.getString("SurgeValue_entity"));
		surgeValue_field = new JTextField(); surgeValue_field.setEditable(false);
		surgeValue_field.setText( Integer.toString(theCharacter.getSurgesValue()) );
		//surges/day
		JLabel surgeNum_label = new JLabel(entity_l10n.getString("SurgeNum_entity"));
		JTextField surgeNum_field = new JTextField();
		surgeNum_field.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int surge_num = 0;
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					surge_num = theCharacter.getSurgesPerDay();
					String deleted_string = Integer.toString(surge_num);
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						surge_num = Integer.parseInt(deleted_string);
						theCharacter.setSurgesPerDay(surge_num);
					} else {
						theCharacter.setSurgesPerDay(0);
					}
				}
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
					int surge_num = ke.getKeyChar() - '0';
					if (surge_num >= 0 && surge_num <= 9) { // ensure health is an integer
						surge_num += theCharacter.getSurgesPerDay() * 10;
						theCharacter.setSurgesPerDay(surge_num);
					} else {
						ke.consume();
					}
				
			}
		});
		surgeNum_field.setText( Integer.toString(theCharacter.getSurgesPerDay()) );
		
		health_layout.setAutoCreateGaps(true);
		health_layout.setHorizontalGroup( health_layout.createSequentialGroup()
				.addGroup( health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(maxHP_label)
						.addComponent(maxHP_field))
				.addGroup( health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(bloodied_label)
						.addComponent(bloodied_field))
				.addGroup( health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(surgeValue_label)
						.addComponent(surgeValue_field))
				.addGroup( health_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(surgeNum_label)
						.addComponent(surgeNum_field))
				);
		health_layout.setVerticalGroup( health_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				.addGroup( health_layout.createSequentialGroup()
						.addComponent(maxHP_label)
						.addComponent(maxHP_field))
				.addGroup( health_layout.createSequentialGroup()
						.addComponent(bloodied_label)
						.addComponent(bloodied_field))
				.addGroup( health_layout.createSequentialGroup()
						.addComponent(surgeValue_label)
						.addComponent(surgeValue_field))
				.addGroup( health_layout.createSequentialGroup()
						.addComponent(surgeNum_label)
						.addComponent(surgeNum_field))		
				);
		health_layout.linkSize(SwingConstants.VERTICAL, maxHP_field, maxHP_label);
		health_layout.linkSize(SwingConstants.VERTICAL, bloodied_field, bloodied_label);
		health_layout.linkSize(SwingConstants.VERTICAL, surgeValue_field, surgeValue_label);
		health_layout.linkSize(SwingConstants.VERTICAL, surgeNum_field, surgeNum_label);
		
		health_panel.setLayout(health_layout);
		
		
		/*
		 * 
		 * Other
		 * 
		 */
		JPanel otherInfo_panel = new JPanel();
		GroupLayout otherInfo_layout = new GroupLayout(otherInfo_panel);
		//race features
		JLabel racef_label = new JLabel(entity_l10n.getString("RaceF_entity"));
		JEditorPane racef_field = new JEditorPane();
		//TODO: remove hard-coding
		//TODO: line in JTextField begins in middle of the box
		racef_field.setPreferredSize( new Dimension(50, 50) );
		racef_field.addKeyListener( new KeyListener() {

			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getRaceFeatures();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setRaceFeatures(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getRaceFeatures();
				current += ke.getKeyChar();
				theCharacter.setRaceFeatures(current);
			}
			
		});
		racef_field.setText( theCharacter.getRaceFeatures() );
		//resistances
		resistanceForm_bean = new ResistanceForm();
		resistanceForm_panel = resistanceForm_bean.createEntityPanel();
		JButton addResist_button = new JButton(entity_l10n.getString("Add_button"));
		addResist_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Resistance addThis = (Resistance) resistanceForm_bean.getEntity();
				if (addThis.getResistanceType() != null && addThis.getResistanceValue() != 0) {
					theCharacter.addResistance(addThis);
					resistance_list_model.addElement(addThis);
					//resistanceForm_bean = new ResistanceForm();
					resistanceForm_panel = resistanceForm_bean.createEntityPanel();
					/*if (resistance_list.getSelectedIndex() != -1) {
						resistance_list.remove( resistance_list.getSelectedIndex() );
					}*/
				}		
			}
		});
		
		JButton removeResist_button = new JButton(entity_l10n.getString("Remove_button"));
		removeResist_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int selected = resistance_list.getSelectedIndex();
				if (selected != -1) {
					resistance_list_model.remove(selected);
					theCharacter.removeResistanceAt(selected);
				}
				characterForm_panel.repaint();
			}
		});
		resistance_list_model = new DefaultListModel();
		for (int index = 0; index < theCharacter.getNumberOfResistances(); index++) {
			ResistanceBean temp_bean = new ResistanceBean();
			temp_bean.createPanelFrom(theCharacter.getResistanceAt(index));
			resistance_list_model.addElement(temp_bean);
		}
		
		resistance_list = new JList(resistance_list_model);
		resistance_list.setLayoutOrientation(JList.VERTICAL);
		resistance_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		resistance_list.setCellRenderer(new ResistanceBean());
	/*	resistance_list.addListSelectionListener( new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				if (lse.getValueIsAdjusting() == false && resistance_list.getSelectedIndex() != -1) {
					//Object forTesting = resistance_list.getSelectedValue();
					Resistance createFrom = (Resistance) resistance_list.getSelectedValue();
					//resistanceForm_bean = new ResistanceForm();
					resistanceForm_panel = resistanceForm_bean.createPanelFromExistingEntity(createFrom);
					characterForm_panel.repaint();
				}
			}
		});*/
		
		JScrollPane resistance_pane = new JScrollPane(resistance_list);
		/*resistance_pane.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if ( key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE && ((JScrollPane) ke.getSource()).hasFocus() ) {
					int index = resistance_list.getSelectedIndex();
					if (index != -1) {
						Resistance temp_resistance = ( (Resistance) ( (ResistanceBean) resistance_list_model.getElementAt(index) ).getEntity() );
						resistance_list_model.removeElementAt(index);
						theCharacter.removeResistance(temp_resistance);						
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				
			}

			public void keyTyped(KeyEvent ke) {
				ke.consume();
			}
		});*/
		
		//languages
		JLabel lang_label = new JLabel(entity_l10n.getString("Lang_entity"));
		JTextField lang_field = new JTextField();
		lang_field.setPreferredSize( new Dimension(50, 50) );
		lang_field.addKeyListener( new KeyListener() {

			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getLanguages();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setLanguages(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getLanguages();
				current += ke.getKeyChar();
				theCharacter.setLanguages(current);
			}
			
		});
		lang_field.setText( theCharacter.getLanguages() );		
		
		JLabel misc_label = new JLabel(entity_l10n.getString("Misc_entity"));
		JEditorPane misc_field = new JEditorPane();
		misc_field.setPreferredSize( new Dimension(50, 50) );
		misc_field.addKeyListener( new KeyListener() {

			public void keyPressed(KeyEvent ke) {
				int key_press = ke.getKeyCode();
				if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
					String deleted_string = theCharacter.getMisc();
					if (deleted_string.length() > 1) {
						deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
						theCharacter.setMisc(deleted_string);
					}
				}
			}

			public void keyReleased(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent ke) {
				String current = theCharacter.getMisc();
				current += ke.getKeyChar();
				theCharacter.setMisc(current);
			}
			
		});
		misc_field.setText( theCharacter.getMisc() );
		
		otherInfo_layout.setAutoCreateGaps(true);
		otherInfo_layout.setHorizontalGroup( otherInfo_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(racef_label)
				.addComponent(racef_field)
				.addComponent(lang_label)
				.addComponent(lang_field)
				.addComponent(misc_label)
				.addComponent(misc_field)
				.addGroup( otherInfo_layout.createSequentialGroup()
						.addComponent(resistanceForm_panel)
						.addGroup( otherInfo_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(addResist_button)
							.addComponent(removeResist_button))
						)
				.addComponent(resistance_pane)
				);
		otherInfo_layout.setVerticalGroup( otherInfo_layout.createSequentialGroup()
				.addComponent(racef_label)
				.addComponent(racef_field)
				.addComponent(lang_label)
				.addComponent(lang_field)
				.addComponent(misc_label)
				.addComponent(misc_field)
				.addGroup( otherInfo_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(resistanceForm_panel)
						.addGroup( otherInfo_layout.createSequentialGroup()
							.addComponent(addResist_button)
							.addComponent(removeResist_button))
						)
				.addComponent(resistance_pane)
				);
		otherInfo_panel.setLayout(otherInfo_layout);
		
		characterForm_layout.setAutoCreateGaps(true);
		characterForm_layout.setHorizontalGroup( characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(generalInfo_panel)
				.addGroup( characterForm_layout.createSequentialGroup()
						.addGroup( characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(ability_panel)
								.addComponent(defense_panel)
								.addComponent(health_panel)
								.addComponent(otherInfo_panel))
						.addComponent(skill_panel)
						)
				);
		characterForm_layout.setVerticalGroup( characterForm_layout.createSequentialGroup()
				.addComponent(generalInfo_panel)
				.addGroup( characterForm_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup( characterForm_layout.createSequentialGroup()
								.addComponent(ability_panel)
								.addComponent(defense_panel)
								.addComponent(health_panel)
								.addComponent(otherInfo_panel))
						.addComponent(skill_panel)
						)
				);
		
		characterForm_panel.setLayout(characterForm_layout);
		characterForm_panel.setAutoscrolls(true);
				
	}

	public void keyPressed(KeyEvent ke) {
		int health = 0;
		int key_press = ke.getKeyCode();
		if (key_press == KeyEvent.VK_BACK_SPACE || key_press == KeyEvent.VK_DELETE) {
			health = theCharacter.getMaxHP();
			String deleted_string = Integer.toString(health);
			if (deleted_string.length() > 1) {
				deleted_string = deleted_string.substring(0, deleted_string.length() - 1);
				health = Integer.parseInt(deleted_string);
				theCharacter.setMaxHP(health);
				bloodied_field.setText( ((Integer) theCharacter.getBloodied()).toString() );
				surgeValue_field.setText( ((Integer) theCharacter.getSurgesValue()).toString() );
			} else {
				theCharacter.setMaxHP(0);
				bloodied_field.setText( ((Integer) theCharacter.getBloodied()).toString() );
				surgeValue_field.setText( ((Integer) theCharacter.getSurgesValue()).toString() );
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent ke) {
			int health = ke.getKeyChar() - '0';
			if (health >= 0 && health <= 9) { // ensure health is an integer
				health += theCharacter.getMaxHP() * 10;
				theCharacter.setMaxHP(health);
				bloodied_field.setText( ((Integer) theCharacter.getBloodied()).toString() );
				surgeValue_field.setText( ((Integer) theCharacter.getSurgesValue()).toString() );
			} else {
				ke.consume();
			}
		
	}

}
