package entity.dao;

import java.util.List;

import entity.CharacterSheet;
import entity.Monster;
import entity.Team;

public interface MonsterDao {
	/**
	 * Get a list containing the ids and names of all the monsters in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllMonsters();
	
	/**
	 * Get all the monsters in the database
	 * @return list of monsters which are in the database
	 */
	public List<Monster> getAllMonsters();
	
	/**
	 * Get the monsters which are in a given team
	 * @param id of the team
	 * @return list of the monsters in the team
	 */
	public List<Monster> getMonstersInTeam(int teamId);
	
	/**
	 * Save a monster in the database
	 * @param monsterName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 * @param characterSheet
	 * @return monster's id stored in the database
	 */
	public int saveMonster(String monsterName, int currentHP, int currentHealSurges, int initiative,
			boolean secondWind, int tempHP, CharacterSheet characterSheet);
	
	/**
	 * Update a monster in the database
	 * @param monsterId
	 * @param monsterName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 */
	public void updateMonster(int monsterId, String monsterName, int currentHP, int currentHealSurges,
			int initiative, boolean secondWind, int tempHP);
	
	/**
	 * Update a monster in the database and Associate this monster to a team
	 * @param monster
	 * @param team
	 */
	public void updateMonster(Monster monster, Team team);
	
	/**
	 * Delete a player from the database
	 * @param monsterId
	 */
	public void deleteMonster(int monsterId);
}
