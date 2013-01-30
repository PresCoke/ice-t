package entity.dao;

import entity.EntityEnum.T_CounterMeasureSkill;
import entity.EntityEnum.T_Role;
import entity.EntityEnum.T_Type;

/**
 * DAO of a trap or a hazard
 * @author TimHP
 *
 */
public interface TrapHazardDao {

	public void readAllTrapHazards();
	public int saveTrapHazard(String name, int avoidance, int level, String skill, String triggers, int value,
			int xp, int difficultyLevel, String counterMeasureDescription, T_Type type, T_Role role, 
			T_CounterMeasureSkill counterMeasureSkill);
	public void updateTrapHazard(int trapHazardId, String name, int avoidance, int level, String skill, 
			String triggers, int value, int xp, int difficultyLevel, String counterMeasureDescription, 
			T_Type type, T_Role role, T_CounterMeasureSkill counterMeasureSkill);
	public void deleteTrapHazard(int trapHazardId);
	
}
