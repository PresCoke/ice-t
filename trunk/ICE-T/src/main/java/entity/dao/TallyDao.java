package entity.dao;

import entity.Tally;
import java.util.List;

public interface TallyDao {
	
	/**
	 * Delete a tally from the database
	 * @param tally's id
	 */
	public void deleteTally(int tallyId);
	
	/**
	 * Save a tally in the database
	 * @param tally
	 * @param combatEncounterId
	 * @return tally's id stored in the database
	 */
	public int saveTally (String name, int combatEncounterId);
	
	public List<Tally> getTally(int combatEncounterID);
}
