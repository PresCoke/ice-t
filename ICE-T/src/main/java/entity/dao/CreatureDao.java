package entity.dao;

import java.util.List;

import entity.CharacterSheet;
import entity.Creature;
import entity.Team;

public interface CreatureDao {

	/**
	 * Get a list containing the ids and names of all the creatures in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllCreatures();
	
	/**
	 * Get all the creatures in the database
	 * @return list of creatures which are in the dabase
	 */
	public List<Creature> getAllCreatures();
	
	/**
	 * Get the creatures which are in a given team
	 * @param id of the team
	 * @return list of the creatures in the team
	 */
	public List<Creature> getCreaturesInTeam(int teamId);
	
	/**
	 * Save a creature in the database
	 * @param playerName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 * @param characterSheet
	 * @return creature's id stored in the database
	 */
	public int saveCreature(String playerName, int currentHP, int currentHealSurges, int initiative,
			boolean secondWind, int tempHP, CharacterSheet characterSheet);
	
	/**
	 * Update a creature in the database
	 * @param creatureId
	 * @param playerName
	 * @param currentHP
	 * @param currentHealSurges
	 * @param initiative
	 * @param secondWind
	 * @param tempHP
	 */
	public void updateCreature(int creatureId, String playerName, int currentHP, int currentHealSurges,
			int initiative, boolean secondWind, int tempHP);
	
	/**
	 * Update a creature in the database and Associate this creature to a team
	 * @param creature
	 * @param team
	 */
	public void updateCreature(Creature creature, Team team);
	
	/**
	 * Delete a creature from the database
	 * @param creatureId
	 */
	public void deleteCreature(int creatureId);
	
}
