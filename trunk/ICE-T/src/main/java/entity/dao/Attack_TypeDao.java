package entity.dao;

import entity.Attack_Type;

public interface Attack_TypeDao {

	/**
	 * Save an attack type in database
	 * @param type of the attack
	 * @return type's id stored in the database
	 */
	public int saveAttackType(Attack_Type type);
	
	/**
	 * Delete an attack type from the database
	 * @param attack type's id
	 */
	public void deleteAttackType(int attackTypeId);
}
