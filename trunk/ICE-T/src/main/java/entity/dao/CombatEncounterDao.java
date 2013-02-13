package entity.dao;

import java.util.List;

import entity.Rewards;
import entity.Tally;
import entity.Team;
import entity.TrapHazard;
import entity.Tuple;

/**
 * DAO of a combat encounter
 * @author TimHP
 *
 */
public interface CombatEncounterDao {

	public List<Object[]> readAllCombatEncounters();
	public int saveCombatEncounter(String name, String notes, int currentCreatureId, List<Rewards> rewards,
			Tally tally, List<Tuple> tuples, List<Team> teams, List<TrapHazard> trapHazards);
	public void updateCombatEncounter(int combatEncounterId, String name, String notes, int currentCreatureId,
			List<Rewards> rewards, Tally tally, List<Tuple> tuples, List<Team> teams, List<TrapHazard> trapHazards);
	public void deleteCombatEncounter(int combatEncounterId);

}
