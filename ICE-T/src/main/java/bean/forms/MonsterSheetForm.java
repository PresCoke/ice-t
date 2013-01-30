package bean.forms;

import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;

import entity.CharacterSheet;
import entity.EntityM;

public class MonsterSheetForm implements FormBean, KeyListener {
	
	private CharacterSheet theMonster;
	private JPanel monsterForm_panel;
	private JTextField maxHP_field, bloodied_field, surgeValue_field;
	
	public JPanel createEntityPanel() {
		theMonster = new CharacterSheet();
		
		createPanel();
		
		return monsterForm_panel;
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof CharacterSheet) {
			theMonster = (CharacterSheet) usingThis;
		}
		
		createPanel();
		
		return monsterForm_panel;
	}

	public Object getEntity() {
		return theMonster;
	}
	
	private void createPanel() {
		
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
