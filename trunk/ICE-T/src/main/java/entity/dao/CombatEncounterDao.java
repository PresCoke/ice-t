package entity.dao;

import java.util.List;

import entity.CombatEncounter;
import entity.Rewards;
import entity.Tally;
import entity.Team;
import entity.Tuple;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public interface CombatEncounterDao {

	/**
	 * Get a list containing the ids and names of all the combat encounters in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllCombatEncounters();
	
	/**
	 * Save a combat encounter in the database
	 * @param name
	 * @param notes
	 * @param currentCreatureId
	 * @param rewards
	 * @param tally
	 * @param tuples
	 * @param teams
	 * @param trapHazards
	 * @return combat encounter's id stored in the database
	 */
	public int saveCombatEncounter(String name, String notes, int currentCreatureId, List<Rewards> rewards,
			Tally tally, List<Tuple> tuples, List<Team> teams);
	
	/**
	 * Update a combat encounter in the database
	 * @param combatEncounterId
	 * @param name
	 * @param notes
	 * @param currentCreatureId
	 */
	public void updateCombatEncounter(int combatEncounterId, String name, String notes, int currentCreatureId);
	
	/**
	 * Delete a Combat Encounter from the database
	 * @param combatEncounterId
	 */
	public void deleteCombatEncounter(int combatEncounterId);
	
	/**
	 * Get a specific combat encounter from the database
	 * @param combatEncounterId
	 * @return combat encounter associated to the id given
	 */
	public CombatEncounter getCombatEncounter(int combatEncounterId);

}
