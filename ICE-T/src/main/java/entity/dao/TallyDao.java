package entity.dao;

import entity.Tally;

public interface TallyDao {
	
	/**
	 * Delete a tally from the database
	 * @param tally's id
	 */
	public void deleteTally(int tallyId);
	
	/**
	 * Save a tally in the database
	 * @param tally
	 * @return tally's id stored in the database
	 */
	public int saveTally (Tally tally);
}
