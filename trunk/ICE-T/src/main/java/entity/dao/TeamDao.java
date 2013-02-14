package entity.dao;

import java.util.List;

import entity.Player;
import entity.Team;
import entity.TrapHazard;


/**
 * DAO of a team
 * @author TimHP
 *
 */
public interface TeamDao {
	
	/**
	 * Get a list containing the ids and names of all the teams in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllTeams();
	
	/**
	 * Save a team in the database
	 * @param name
	 * @param creatures
	 * @return team's id stored in the database
	 */
	public int saveTeam(String name, List<Player> creatures);
	
	/**
	 * Update a team in the database
	 * @param teamId
	 * @param name
	 * @param creatures
	 */
	public void updateTeam(int teamId, String name, List<Player> creatures);
	
	/**
	 * Save a team in the database
	 * @param name
	 * @param creatures
	 * @param traphazards
	 * @return team's id stored in the database
	 */
	public int saveNPCteam(String name, List<Player> creatures, List<TrapHazard> traphazards);
	
	/**
	 * Update a team in the database
	 * @param teamId
	 * @param name
	 * @param creatures
	 * @param traphazards
	 */
	public void updateNPCteam(int teamId, String name, List<Player> creatures, List<TrapHazard> traphazards);
		
	/**
	 * Delete a team from the database
	 * @param team's id
	 */
	public void deleteTeam(int teamId);
	
	/**
	 * Get a specific team from the database
	 * @param team's id
	 * @return team associated to the team's id
	 */
	public Team getTeam(int teamId);
}
