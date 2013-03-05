package entity.dao;

import java.util.List;

import entity.Monster;
import entity.Player;
import entity.Team;
import entity.TrapHazard;
import entity.CombatEncounter;


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
	 * Get a list containing the players' teams attached to a combat encounter 
	 * @return a list of Teams
	 */
	public List<Team> getAllPlayerTeamsIn(int thisCombatEncounter);
	
	/**
	 * Save a team in the database
	 * @param name
	 * @param creatures
	 * @return team's id stored in the database
	 */
	public int saveTeam(String name, List<Player> players);
	
	/**
	 * Update a team in the database
	 * @param teamId
	 * @param name
	 * @param creatures
	 */
	public void updateTeam(int teamId, String name, List<Player> players);
	
	/**
	 * Save a NPC team in the database
	 * @param name
	 * @param monsters
	 * @param traphazards
	 * @return team's id stored in the database
	 */
	public int saveNPCteam(String name, List<Monster> monsters, List<TrapHazard> traphazards);
	
	/**
	 * Update a NPC team in the database
	 * @param teamId
	 * @param name
	 * @param monsters
	 * @param traphazards
	 */
	public void updateNPCteam(int teamId, String name, List<Monster> monsters, List<TrapHazard> traphazards);
		
	/**
	 * Delete a NPC team from the database
	 * @param team's id
	 */
	public void deleteNPCTeam(int teamId);
	
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
	
	public void updateTeam(int teamId, CombatEncounter encounter, String name, List<Player> players);
	
	public void updateNPCteam(int teamId, CombatEncounter encounter, String name, List<Monster> monsters, List<TrapHazard> traphazards);
}
