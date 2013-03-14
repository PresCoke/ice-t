package bean.combat;

import java.awt.*;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.App_Root;
import entity.Effect;

public class EffectCombat extends Bean {

	private Effect theEffect;
	
	public EffectCombat() {
		
	}
	
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// Name - Duration
		// Damage
		// Metrics
		UIDefaults system_defaults = javax.swing.UIManager.getDefaults();
		ResourceBundle effect_l10n = ResourceBundle.getBundle("filters.BeanGUI_l10n.Entity", App_Root.language_locale);
		
		Effect aEffect = null;
		if (value instanceof Effect) {
			aEffect = (Effect) value;
		} else {
			//Something else... I don't know... I'm tired...
		}
		
		String name = aEffect.getName();
		String duration = "";
		String damage = aEffect.getChanges();
		String metrics = aEffect.getMetrics();
		
		switch (aEffect.getDuration()) {
		case untilStartOfApplyingCreaturesNextTurn:
			duration = effect_l10n.getString("StartOfAppliersTurn_effect"); break;
		case untilEndOfApplyingCreaturesNextTurn:
			duration = effect_l10n.getString("EndOfAppliersTurn_effect"); break;
		case endOfTheEncounter:
			duration = effect_l10n.getString("EndOfEncounter_effect"); break;
		case saveEnds:
			duration = effect_l10n.getString("SaveEnds_effect"); break;
		}
		
		JEditorPane theEffectPanel = new JEditorPane();
		TitledBorder title = BorderFactory.createTitledBorder(name + " - " + duration);
		title.setTitleJustification(TitledBorder.LEFT);
		theEffectPanel.setBorder(title);
		if (isSelected) {
			theEffectPanel.setBackground( system_defaults.getColor("List.selectionBackground") );
		}
		
		String fullText = damage + "\n" +
						  metrics;
		theEffectPanel.setText(fullText);
		
		return theEffectPanel;
	}

	public void createPanelFrom(Object thisEntity) {
		if (thisEntity instanceof Effect) {
			theEffect = (Effect) thisEntity;
		} else {
			theEffect = new Effect();
		}
	}

	public Object getEntity() {
		return theEffect;
	}

}

