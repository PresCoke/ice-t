package bean.forms;

import java.beans.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import entity.CharacterSheet;
import entity.EntityM;

public class CharacterSheetForm extends FormBean implements ActionListener {
	
	private CharacterSheet theCharacter;
	private JPanel characterForm_panel;
	private JTextField maxHP_field, bloodied_field, surgeValue_field;
	
	@Override
	public JPanel createEntityPanel() {
		theCharacter = new CharacterSheet();
		
		createPanel();
		
		return characterForm_panel;
	}
	
	@Override
	public JPanel createPanelFromExistingEntity(EntityM usingThis) {
		if (usingThis instanceof CharacterSheet) {
			theCharacter = (CharacterSheet) usingThis;
		}
		
		createPanel();
		
		return characterForm_panel;
	}

	@Override
	public EntityM getEntity() {
	
		return theCharacter;
	}
	
	private void createPanel() {
		characterForm_panel = new JPanel();
		characterForm_panel.setLayout( new GridBagLayout() );
		
		final ResourceBundle entity_l10n = ResourceBundle.getBundle("Entity", controller.App_Root.language_locale);
		
		/*
		 * 
		 * General Info
		 * 
		 */
		JPanel generalInfo_panel = new JPanel();
		generalInfo_panel.setLayout( new GridLayout(2, 8) );
		generalInfo_panel.setBorder( BorderFactory.createLineBorder(Color.GRAY) );
		//Character Name
		JLabel name_label = new JLabel( entity_l10n.getString("Name_entity") );
		JTextField name_field = new JTextField();
		name_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				theCharacter.setName( ((JTextField) arg0.getSource()).getText() );
			}	
		});
		generalInfo_panel.add(name_label); generalInfo_panel.add(name_field);
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

		generalInfo_panel.add(lvl_label); generalInfo_panel.add(lvl_field);
		//XP
		JLabel xp_label = new JLabel(entity_l10n.getString("XP_entity") );
		JTextField xp_field = new JTextField();
		xp_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int xp = Integer.parseInt( ((JTextField) arg0.getSource()).getText() );
					if (xp >= 0) {
						theCharacter.setXP(xp);
					} else {
						((JTextField) arg0.getSource()).setText(entity_l10n.getString("Zero_warning"));
					}
				} catch (NumberFormatException nfe) {
					((JTextField) arg0.getSource()).setText(entity_l10n.getString("Integer_warning"));
				}
			}	
		});
		generalInfo_panel.add(xp_label); generalInfo_panel.add(xp_field);		
		//speed
		JLabel speed_label = new JLabel(entity_l10n.getString("Speed_entity"));
		JTextField speed_field = new JTextField();
		speed_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int speed = Integer.parseInt( ((JTextField) arg0.getSource()).getText() );
					if (speed > 0) {
						theCharacter.setSpeed(speed);
					} else {
						((JTextField) arg0.getSource()).setText(entity_l10n.getString("Zero_warning"));
					}
				} catch (NumberFormatException nfe) {
					((JTextField) arg0.getSource()).setText(entity_l10n.getString("Integer_warning"));
				}
			}	
		});
		generalInfo_panel.add(speed_label); generalInfo_panel.add(speed_field);
		//initiative
		JLabel init_label = new JLabel(entity_l10n.getString("Init_entity"));
		JTextField init_field = new JTextField();
		init_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int init = Integer.parseInt( ((JTextField) arg0.getSource()).getText() );
					if (init >= 0) {
						theCharacter.setInitiative(init);
					} else {
						((JTextField) arg0.getSource()).setText(entity_l10n.getString("Zero_warning"));
					}
				} catch (NumberFormatException nfe) {
					((JTextField) arg0.getSource()).setText(entity_l10n.getString("Integer_warning"));
				}
			}	
		});
		generalInfo_panel.add(init_label); generalInfo_panel.add(init_field);
		//role
		JLabel role_label = new JLabel(entity_l10n.getString("Role_entity"));
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
		generalInfo_panel.add(role_label); generalInfo_panel.add(role_list);
		//size
		JLabel size_label = new JLabel(entity_l10n.getString("Size_entity"));
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
		generalInfo_panel.add(size_label); generalInfo_panel.add(size_list);
		//power source
		JLabel pwr_label = new JLabel(entity_l10n.getString("Power_entity"));
		JTextField pwr_field = new JTextField();
		pwr_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theCharacter.setPowerSource( ((JTextField) ae.getSource()).getText() );
			}
		});
		generalInfo_panel.add(pwr_label); generalInfo_panel.add(pwr_field);
		
		/*
		 * 
		 * Ability Scores
		 * 
		 */
		JPanel ability_panel = new JPanel();
		ability_panel.setLayout( new GridLayout(0, 1) );
		ability_panel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), entity_l10n.getString("Ability_title")) );
		SpinnerNumberModel abl_model = new SpinnerNumberModel(0, 0, 100, 1);
		//STR
		JLabel str_label = new JLabel(entity_l10n.getString("STR_entity"));
		JSpinner str_field = new JSpinner(abl_model);
		str_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setSTR(abl);
			}
		});
		ability_panel.add(str_label); ability_panel.add(str_field);
		//CON
		JLabel con_label = new JLabel(entity_l10n.getString("CON_entity"));
		JSpinner con_field = new JSpinner(abl_model);
		con_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setCON(abl);
			}
		});
		ability_panel.add(con_label); ability_panel.add(con_field);
		//INT
		JLabel int_label = new JLabel(entity_l10n.getString("INT_entity"));
		JSpinner int_field = new JSpinner(abl_model);
		int_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setINT(abl);
			}
		});
		ability_panel.add(int_label); ability_panel.add(int_field);
		//DEX
		JLabel dex_label = new JLabel(entity_l10n.getString("DEX_entity"));
		JSpinner dex_field = new JSpinner(abl_model);
		dex_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDEX(abl);
			}
		});
		ability_panel.add(dex_label); ability_panel.add(dex_field);
		//WIS
		JLabel wis_label = new JLabel(entity_l10n.getString("WIS_entity"));
		JSpinner wis_field = new JSpinner(abl_model);
		wis_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setWIS(abl);
			}
		});
		ability_panel.add(wis_label); ability_panel.add(wis_field);
		//CHA
		JLabel cha_label = new JLabel(entity_l10n.getString("CHA_entity"));
		JSpinner cha_field = new JSpinner(abl_model);
		cha_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int abl = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setCHAR(abl);
			}
		});
		ability_panel.add(cha_label); ability_panel.add(cha_field);
		
		/*
		 * 
		 * Defenses
		 * 
		 */
		JPanel defense_panel = new JPanel();
		defense_panel.setLayout( new GridLayout(1, 0) );
		defense_panel.setBorder( BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), entity_l10n.getString("Defense_title")) );
		//AC
		JLabel ac_label = new JLabel(entity_l10n.getString("AC_entity"));
		JSpinner ac_field = new JSpinner(abl_model);
		ac_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAC(def);
			}
		});
		defense_panel.add(ac_label); defense_panel.add(ac_field);
		//REF
		JLabel ref_label = new JLabel(entity_l10n.getString("REF_entity"));
		JSpinner ref_field = new JSpinner(abl_model);
		ref_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setREF(def);
			}
		});
		defense_panel.add(ref_label); defense_panel.add(ref_field);
		//FORT
		JLabel fort_label = new JLabel(entity_l10n.getString("FORT_entity"));
		JSpinner fort_field = new JSpinner(abl_model);
		fort_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setFORT(def);
			}
		});
		defense_panel.add(fort_label); defense_panel.add(fort_field);
		//WILL
		JLabel will_label = new JLabel(entity_l10n.getString("WILL_entity"));
		JSpinner will_field = new JSpinner(abl_model);
		will_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int def = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setWILL(def);
			}
		});
		defense_panel.add(will_label); defense_panel.add(will_field);
		
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
		JSpinner acro_field = new JSpinner(abl_model);
		acro_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAcrobatics(skill);
			}
		});
		skill_panel.add(acro_label); skill_panel.add(acro_field);
		//athletics
		JLabel athl_label = new JLabel(entity_l10n.getString("ATHL_entity"));
		JSpinner athl_field = new JSpinner(abl_model);
		athl_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setAthletics(skill);
			}
		});
		skill_panel.add(athl_label); skill_panel.add(athl_field);
		//arcana
		JLabel arca_label = new JLabel(entity_l10n.getString("arca_entity"));
		JSpinner arca_field = new JSpinner(abl_model);
		arca_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setArcana(skill);
			}
		});
		skill_panel.add(arca_label); skill_panel.add(arca_field);
		//bluff
		JLabel bluf_label = new JLabel(entity_l10n.getString("BLUF_entity"));
		JSpinner bluf_field = new JSpinner(abl_model);
		bluf_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setBluff(skill);
			}
		});
		skill_panel.add(bluf_label); skill_panel.add(bluf_field);
		//diplomacy
		JLabel dipl_label = new JLabel(entity_l10n.getString("dipl_entity"));
		JSpinner dipl_field = new JSpinner(abl_model);
		acro_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDiplomacy(skill);
			}
		});
		skill_panel.add(dipl_label); skill_panel.add(dipl_field);
		//dungeoneering
		JLabel dung_label = new JLabel(entity_l10n.getString("DUNG_entity"));
		JSpinner dung_field = new JSpinner(abl_model);
		dung_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setDungeoneering(skill);
			}
		});
		skill_panel.add(dung_label); skill_panel.add(dung_field);
		//endurance
		JLabel endu_label = new JLabel(entity_l10n.getString("ENDU_entity"));
		JSpinner endu_field = new JSpinner(abl_model);
		endu_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setEndurance(skill);
			}
		});
		skill_panel.add(endu_label); skill_panel.add(endu_field);
		//heal
		JLabel heal_label = new JLabel(entity_l10n.getString("HEAL_entity"));
		JSpinner heal_field = new JSpinner(abl_model);
		heal_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setHeal(skill);
			}
		});
		skill_panel.add(heal_label); skill_panel.add(heal_field);
		//history
		JLabel hist_label = new JLabel(entity_l10n.getString("HIST_entity"));
		JSpinner hist_field = new JSpinner(abl_model);
		hist_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setHistory(skill);
			}
		});
		skill_panel.add(hist_label); skill_panel.add(hist_field);
		//insight
		JLabel insi_label = new JLabel(entity_l10n.getString("INSI_entity"));
		JSpinner insi_field = new JSpinner(abl_model);
		insi_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setInsight(skill);
			}
		});
		skill_panel.add(insi_label); skill_panel.add(insi_field);
		//intimidate
		JLabel inti_label = new JLabel(entity_l10n.getString("INTI_entity"));
		JSpinner inti_field = new JSpinner(abl_model);
		inti_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setIntimidate(skill);
			}
		});
		skill_panel.add(inti_label); skill_panel.add(inti_field);
		//nature
		JLabel natu_label = new JLabel(entity_l10n.getString("NATU_entity"));
		JSpinner natu_field = new JSpinner(abl_model);
		natu_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setNature(skill);
			}
		});
		skill_panel.add(natu_label); skill_panel.add(natu_field);
		//perception
		JLabel perc_label = new JLabel(entity_l10n.getString("PERC_entity"));
		JSpinner perc_field = new JSpinner(abl_model);
		perc_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setPerception(skill);
			}
		});
		skill_panel.add(perc_label); skill_panel.add(perc_field);
		//religion
		JLabel reli_label = new JLabel(entity_l10n.getString("RELI_entity"));
		JSpinner reli_field = new JSpinner(abl_model);
		reli_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setReligion(skill);
			}
		});
		skill_panel.add(reli_label); skill_panel.add(reli_field);
		//stealth
		JLabel stea_label = new JLabel(entity_l10n.getString("STEA_entity"));
		JSpinner stea_field = new JSpinner(abl_model);
		stea_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setStealth(skill);
			}
		});
		skill_panel.add(stea_label); skill_panel.add(stea_field);
		//streetwise
		JLabel stre_label = new JLabel(entity_l10n.getString("STRE_entity"));
		JSpinner stre_field = new JSpinner(abl_model);
		stre_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setStreetwise(skill);
			}
		});
		skill_panel.add(stre_label); skill_panel.add(stre_field);
		//theivery
		JLabel thie_label = new JLabel(entity_l10n.getString("THIE_entity"));
		JSpinner thie_field = new JSpinner(abl_model);
		thie_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int skill = (Integer) ((JSpinner) arg0.getSource()).getValue();
				theCharacter.setThievery(skill);
			}
		});
		skill_panel.add(thie_label); skill_panel.add(thie_field);
		
		/*
		 * 
		 * Health+Surges/Day
		 * 
		 */
		JPanel health_panel = new JPanel();
		health_panel.setLayout( new GridLayout(1, 0) );
		health_panel.setBorder( BorderFactory.createLineBorder(Color.GRAY) );
		//maxHP
		JLabel maxHP_label = new JLabel(entity_l10n.getString("MAXHP_entity"));
		maxHP_field = new JTextField();
		maxHP_field.addActionListener(this);
		health_panel.add(maxHP_label); health_panel.add(maxHP_field);
		
		JLabel bloodied_label = new JLabel(entity_l10n.getString("Bloodied_entity"));
		bloodied_field = new JTextField(); bloodied_field.setEditable(false);
		health_panel.add(bloodied_label); health_panel.add(bloodied_field);
		
		JLabel surgeValue_label = new JLabel(entity_l10n.getString("SurgeValue_entity"));
		surgeValue_field = new JTextField(); surgeValue_field.setEditable(false);
		health_panel.add(surgeValue_label); health_panel.add(surgeValue_field);
		
		//surges/day
		JLabel surgeNum_label = new JLabel(entity_l10n.getString("SurgeNum_entity"));
		JTextField surgeNum_field = new JTextField();
		surgeNum_field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int surge = Integer.parseInt( ((JTextField) arg0.getSource()).getText() );
					if (surge >= 0) {
						theCharacter.setSurgesPerDay(surge);
					} else {
						((JTextField) arg0.getSource()).setText(entity_l10n.getString("Zero_warning"));
					}
				} catch (NumberFormatException nfe) {
					((JTextField) arg0.getSource()).setText(entity_l10n.getString("Integer_warning"));
				}
			}	
		});
		health_panel.add(surgeNum_label); health_panel.add(surgeNum_field);
		
		/*
		 * 
		 * Other
		 * 
		 */
		JPanel otherInfo_panel = new JPanel();
		otherInfo_panel.setLayout( new GridLayout(1, 0) );
		//race features
		JLabel racef_label = new JLabel(entity_l10n.getString("RaceF_entity"));
		JTextField racef_field = new JTextField();
		racef_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theCharacter.setRaceFeatures( ((JTextField) ae.getSource()).toString() );
			}
		});
		otherInfo_panel.add(racef_label); otherInfo_panel.add(racef_field);
		//resistances
		//TODO: implement resistances better
		//languages
		JLabel lang_label = new JLabel(entity_l10n.getString("Lang_entity"));
		JTextField lang_field = new JTextField();
		lang_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theCharacter.setLanguages( ((JTextField) ae.getSource()).toString() );
			}
		});
		otherInfo_panel.add(lang_label); otherInfo_panel.add(lang_field);
		//misc
		JLabel misc_label = new JLabel(entity_l10n.getString("Misc_entity"));
		JTextField misc_field = new JTextField();
		misc_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				theCharacter.setMisc( ((JTextField) ae.getSource()).toString() );
			}
		});
		otherInfo_panel.add(misc_label); otherInfo_panel.add(misc_field);
		
		GridBagConstraints generalInfo_constraints = new GridBagConstraints();
		//TODO: set constraints
		characterForm_panel.add(generalInfo_panel, generalInfo_constraints);
		
		GridBagConstraints ability_constraints = new GridBagConstraints();
		//TODO: set constraints
		characterForm_panel.add(ability_panel, ability_constraints);
		
		GridBagConstraints skill_constraints = new GridBagConstraints();
		//TODO: set constraints
		characterForm_panel.add(skill_panel, skill_constraints);
		
		GridBagConstraints health_constraints = new GridBagConstraints();
		//TODO: set constraints
		characterForm_panel.add(health_panel, health_constraints);
		
		GridBagConstraints otherInfo_constraints = new GridBagConstraints();
		//TODO: set constraints
		characterForm_panel.add(otherInfo_panel, otherInfo_constraints);
	}

	public void actionPerformed(ActionEvent ae) {
		try {
			int health = Integer.parseInt( ((JTextField) ae.getSource()).getText() );
			if (health > 0) {
				theCharacter.setMaxHP(health);
				bloodied_field.setText( ((Integer) theCharacter.getBloodied()).toString() );
				surgeValue_field.setText( ((Integer) theCharacter.getSurgesValue()).toString() );
			} else {
				ResourceBundle entity_l10n = ResourceBundle.getBundle("Entity", controller.App_Root.language_locale);
				((JTextField) ae.getSource()).setText(entity_l10n.getString("Zero_warning"));
			}
		} catch (NumberFormatException nfe) {
			ResourceBundle entity_l10n = ResourceBundle.getBundle("Entity", controller.App_Root.language_locale);
			((JTextField) ae.getSource()).setText(entity_l10n.getString("Integer_warning"));
		}
		
	}

}
