package entity.dao;

import entity.Rewards;
import java.util.List;

public interface RewardsDao {

	/**
	 * Save rewards in the database
	 * @param XP
	 * @param treasure
	 * @param combatEncounterID
	 * @return rewards' id stored in the database
	 */
	public int saveRewards(int XP, String treasure, int combatEncounterID);
	
	/**
	 * Delete rewards in the database
	 * @param rewardsId
	 */
	public void deleteRewards (int rewardsId);
	
	public List<Rewards> getRewards(int combatEncounterId);
}
