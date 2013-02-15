package entity.dao;

import java.util.List;

import entity.CharacterSheet;
import entity.Player;
import entity.Team;

public interface PlayerDao {

	/**
	 * Get a list containing the ids and names of all the players in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllPlayers();
	
	/**
	 * Get all the players in the database
	 * @return list of players which are in the dabase
	 */
	public List<Player> getAllPlayers();
	
	/**
	 * Get the players which are in a given team
	 * @param id of the team
	 * @return list of the players in the team
	 */
	public List<Player> getPlayersInTeam(int teamId);
	
	/**
	 * Save a player in the database
	 * @param playerName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 * @param characterSheet
	 * @return player's id stored in the database
	 */
	public int savePlayer(String playerName, int currentHP, int currentHealSurges, int initiative,
			boolean secondWind, int tempHP, CharacterSheet characterSheet);
	
	/**
	 * Update a player in the database
	 * @param playerId
	 * @param playerName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 */
	public void updatePlayer(int playerId, String playerName, int currentHP, int currentHealSurges,
			int initiative, boolean secondWind, int tempHP);
	
	/**
	 * Update a player in the database and Associate this player to a team
	 * This function does not update the character sheet
	 * @param player
	 * @param team
	 */
	public void updatePlayer(Player player, Team team);
	
	/**
	 * Delete a player from the database
	 * @param playerId
	 */
	public void deletePlayer(int playerId);
	
}
