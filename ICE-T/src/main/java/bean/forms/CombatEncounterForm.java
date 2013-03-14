package bean.forms;

import javax.swing.JPanel;

public class CombatEncounterForm implements FormBean {
	private entity.CombatEncounter theCombatEncounter;
	public JPanel createEntityPanel() {
		theCombatEncounter = new entity.CombatEncounter();
		return new JPanel();
	}

	public JPanel createPanelFromExistingEntity(Object usingThis) {
		if (usingThis instanceof entity.CombatEncounter) {
			theCombatEncounter = (entity.CombatEncounter) usingThis;
		}
		return new JPanel();
	}

	public Object getEntity() {
		return theCombatEncounter;
	}

	public boolean validateEntity() {
		return true;
	}

}
