package entity.dao;

import entity.Attack;
import entity.Attack_Type;
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
	public int saveTrapHazard(String name, int avoidance, int level, T_CounterMeasureSkill skill, String triggers,
			int xp, int difficultyLevel, String counterMeasureDescription, T_Type type, T_Role role, 
			T_CounterMeasureSkill counterMeasureSkill, Attack attack, Attack_Type atype);
	public void updateTrapHazard(int trapHazardId, String name, int avoidance, int level, T_CounterMeasureSkill skill, 
			String triggers, int xp, int difficultyLevel, String counterMeasureDescription, T_Type type, T_Role role,
			T_CounterMeasureSkill counterMeasureSkill, Attack attack, Attack_Type atype);
	public void deleteTrapHazard(int trapHazardId);
	
}
