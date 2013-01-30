package entity.dao;

import entity.EntityEnum.E_Duration;

/**
 * DAO of an effect
 * @author TimHP
 *
 */
public interface EffectDao {

	public void readAllEffects();
	public int saveEffect(String name, String changes, int damage, String metrics, E_Duration duration);
	public void updateTrapHazard(int effectId, String name,String changes, int damage, String metrics, E_Duration duration);
	public void deleteTrapHazard(int effectId);

}
