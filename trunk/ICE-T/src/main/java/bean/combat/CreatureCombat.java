package bean.combat;

import java.awt.Component;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

import entity.Creature;

public class CreatureCombat implements Bean {

	private Creature theCreature;
	private JSpinner currentHealth_field;
	private JSpinner currentSurge_field;
	private JSpinner tempHP_field;
	private JSpinner currentInit_field;
	private JCheckBox secondWind_field;
	
	public CreatureCombat() {
		theCreature = new Creature();
		currentHealth_field = new JSpinner (new SpinnerNumberModel(
						theCreature.getCurrentHP(), //current
						-1 * theCreature.getCharacterSheet().getBloodied(),//min
						theCreature.getCharacterSheet().getMaxHP(),//max
						1//increment
				));
		currentHealth_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int value = (Integer) ((JSpinner) ce.getSource()).getValue();
				theCreature.setCurrentHP(value);
			}
		});
		((JSpinner.DefaultEditor) currentHealth_field.getEditor()).getTextField()
			.addKeyListener( new KeyListener() {
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
		currentSurge_field = new JSpinner (new SpinnerNumberModel(
						theCreature.getCurrentHealSurges(),
						0,
						theCreature.getCharacterSheet().getSurgesPerDay(),
						1
				));
		currentSurge_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int value = (Integer) ((JSpinner) ce.getSource()).getValue();
				theCreature.setCurrentHealSurges(value);
			}
		});
		currentSurge_field.addKeyListener( new KeyListener() {
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
		tempHP_field = new JSpinner (new SpinnerNumberModel(
						0, 
						0, 
						100, 
						1
				));
		tempHP_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int value = (Integer) ((JSpinner) ce.getSource()).getValue();
				theCreature.setTempHP(value);
			}
		});
		tempHP_field.addKeyListener( new KeyListener() {
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
		currentInit_field = new JSpinner( new SpinnerNumberModel(
						theCreature.getCharacterSheet().getInitiative(),
						0,
						100,
						1
				));
		currentInit_field.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				int value = (Integer) ((JSpinner) ce.getSource()).getValue();
				theCreature.setInitiative(value);
			}
		});
		currentInit_field.addKeyListener( new KeyListener() {
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
		
		secondWind_field = new JCheckBox();
		secondWind_field.setSelected(theCreature.isSecondWind());
		secondWind_field.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				theCreature.setSecondWind( ((JCheckBox) ae.getSource()).isSelected() );
			}
		});
		
	}
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle entity_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", controller.App_Root.language_locale);
		Creature aCreature = (Creature) value;
		
		JPanel creaturePanel = new JPanel();
		if (isSelected) {
			creaturePanel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		
		JLabel name_label = new JLabel(theCreature.getPlayerName() + " - " + theCreature.getCharacterSheet().getName());
		
		JLabel tempHP_label = new JLabel(entity_l10n.getString("tempHP_creature"));
		JLabel currentInit_label = new JLabel(entity_l10n.getString("Initiative_creature"));
		JLabel currentHealth_label = new JLabel(entity_l10n.getString("HP_creature"));
		JLabel currentSurge_label = new JLabel(entity_l10n.getString("Surge_creature"));
		JLabel secondWind_label = new JLabel(entity_l10n.getString("SecondWind_creature"));
		GroupLayout creature_layout = new GroupLayout(creaturePanel);
		creature_layout.setHorizontalGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(name_label)
				.addGroup( creature_layout.createSequentialGroup()
						.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(currentInit_label)
								.addComponent(currentInit_field))
						.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(currentHealth_label)
								.addComponent(currentHealth_field))
						.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(tempHP_label)
								.addComponent(tempHP_field))
						.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(currentSurge_label)
								.addComponent(currentSurge_field))
						)
				.addGroup( creature_layout.createSequentialGroup()
						.addComponent(secondWind_label)
						.addComponent(secondWind_field))
				);
		creature_layout.setVerticalGroup( creature_layout.createSequentialGroup()
				.addComponent(name_label)
				.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addGroup( creature_layout.createSequentialGroup()
								.addComponent(currentInit_label)
								.addComponent(currentInit_field))
						.addGroup( creature_layout.createSequentialGroup()
								.addComponent(currentHealth_label)
								.addComponent(currentHealth_field))
						.addGroup( creature_layout.createSequentialGroup()
								.addComponent(tempHP_label)
								.addComponent(tempHP_field))
						.addGroup( creature_layout.createSequentialGroup()
								.addComponent(currentSurge_label)
								.addComponent(currentSurge_field))
						)
				.addGroup( creature_layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(secondWind_label)
						.addComponent(secondWind_field))
				);
		
		return creaturePanel;
	}

	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof Creature) {
			theCreature = (Creature) thisEntity;
			currentHealth_field.setModel( new SpinnerNumberModel(
						theCreature.getCurrentHP(),
						-1 * theCreature.getCharacterSheet().getBloodied(),
						theCreature.getCharacterSheet().getMaxHP(),
						1
					));
			
			currentSurge_field.setModel( new SpinnerNumberModel(
						theCreature.getCurrentHealSurges(),
						0,
						theCreature.getCharacterSheet().getSurgesPerDay(),
						1
					));
			tempHP_field.setValue(theCreature.getTempHP());
			currentInit_field.setValue(theCreature.getInitiative());
			secondWind_field.setSelected(theCreature.isSecondWind());
		}
	}

	public Object getEntity() {
		// TODO Auto-generated method stub
		return theCreature;
	}

}
