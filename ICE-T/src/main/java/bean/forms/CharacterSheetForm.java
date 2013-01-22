package bean.forms;

import javax.swing.JPanel;
import entity.CharacterSheet;

public class CharacterSheetForm extends JPanel {
	
	private CharacterSheet theCharacter;
	private JPanel characterForm_panel;
	
	public JPanel createEmptySheet() {
		theCharacter = new CharacterSheet("SomeName!");//TODO: answer this question: Why does an entity need a name at construction?
		
		createPanel();
		
		return characterForm_panel;
	}
	
	public JPanel createFromExistingEntity(CharacterSheet usingThis) {
		theCharacter = usingThis;
		
		createPanel();
		
		return characterForm_panel;
	}
	
	public CharacterSheet getCharacterEntity() {
		return theCharacter;
	}
	
	private void createPanel() {
		
	}

}
