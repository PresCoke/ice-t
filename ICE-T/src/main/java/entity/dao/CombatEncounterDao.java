package entity.dao;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public interface CombatEncounterDao {

	public void readAllCombatEncounters();
	public int saveCombatEncounter(String name, String notes);
	public void updateCombatEncounter(int combatEncounterId, String name, String notes);
	public void deleteCombatEncounter(int combatEncounterId);

}
