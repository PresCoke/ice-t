package entity.dao;

import java.util.List;

import entity.Attack;
import entity.Attack_Type;
import entity.EntityEnum.T_CounterMeasureSkill;
import entity.EntityEnum.T_Role;
import entity.EntityEnum.T_Type;
import entity.TrapHazard;

/**
 * DAO of a trap or a hazard
 * @author TimHP
 *
 */
public interface TrapHazardDao {

	/**
	 * Get a list containing the ids and names of all the traps/hazards in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllTrapHazards();
	
	/**
	 * Get all traps/hazards from the database
	 * @return list of Traps/Hazards
	 */
	public List<TrapHazard> getAllTrapHazards();
	
	/**
	 * Save a trap/hazard in the database
	 * @param name
	 * @param avoidance
	 * @param level
	 * @param skill
	 * @param triggers
	 * @param xp
	 * @param difficultyLevel
	 * @param counterMeasureDescription
	 * @param type
	 * @param role
	 * @param counterMeasureSkill
	 * @param attack
	 * @param atype
	 * @return trap/hazard's id stored in the database
	 */
	public int saveTrapHazard(String name, int avoidance, int level, T_CounterMeasureSkill skill, String triggers,
			int xp, int difficultyLevel, String counterMeasureDescription, T_Type type, T_Role role, 
			T_CounterMeasureSkill counterMeasureSkill, Attack attack, Attack_Type atype);
	
	/**
	 * Update a trap/hazard in the database
	 * @param trapHazardId
	 * @param name
	 * @param avoidance
	 * @param level
	 * @param skill
	 * @param triggers
	 * @param xp
	 * @param difficultyLevel
	 * @param counterMeasureDescription
	 * @param type
	 * @param role
	 * @param counterMeasureSkill
	 * @param attack
	 * @param atype
	 */
	public void updateTrapHazard(int trapHazardId, String name, int avoidance, int level, T_CounterMeasureSkill skill, 
			String triggers, int xp, int difficultyLevel, String counterMeasureDescription, T_Type type, T_Role role,
			T_CounterMeasureSkill counterMeasureSkill, Attack attack, Attack_Type atype);
	
	/**
	 * Delete a trap/hazard from the database
	 * @param trap/hazard's id
	 */
	public void deleteTrapHazard(int trapHazardId);
	
	/**
	 * Get a specific trap/hazard from the database
	 * @param trapHazard's id
	 * @return trap/hazard's id associated to the given id
	 */
	public TrapHazard getTrapHazard(int trapHazardId);

	public List<TrapHazard> getAllTrapHazardsInTeam(int teamId);
	
}
