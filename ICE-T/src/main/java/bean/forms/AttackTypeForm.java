package bean.forms;

import javax.swing.JPanel;

import entity.*;

public class AttackTypeForm implements FormBean {

	private Attack_Type theAttackType; 
	private JPanel attackType_panel;

	public JPanel createEntityPanel() {
		theAttackType = new Attack_Type();
		createPersonalPanel();
		return null;
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

	public void createRangedPanel() {

	}

	public void createMeleePanel() {

	}

	public void createClosePanel() {

	}

	public void createAreaPanel() {

	}
	
	public void createPersonalPanel() {
		attackType_panel = new JPanel();
	}

}
