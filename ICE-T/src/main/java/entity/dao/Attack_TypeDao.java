package entity.dao;

import entity.Attack_Type;

public interface Attack_TypeDao {

	public int saveAttackType(Attack_Type type);
	public void deleteAttackType(int attackTypeId);
}
