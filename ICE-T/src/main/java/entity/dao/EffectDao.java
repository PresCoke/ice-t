package entity.dao;

import java.util.List;

import entity.Player;
import entity.EntityEnum.E_Duration;

/**
 * DAO of an effect
 * @author TimHP
 *
 */
public interface EffectDao {

	/**
	 * Get a list containing the ids and names of all the effects in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllEffects();
	
	/**
	 * Save an effect in the database
	 * @param name
	 * @param changes
	 * @param metrics
	 * @param duration
	 * @param creatures
	 * @return effect's id stored in the database
	 */
	public List<Integer> saveEffect(String name, String changes, String metrics, E_Duration duration, List<Player> creatures);
	
	/**
	 * Delete a list of effect from the database
	 * @param list of effects' ids
	 */
	public void deleteEffects(List<Integer> effectsIds);
	
	/**
	 * Delete an effect from the database
	 * @param effectId
	 */
	public void deleteEffect(int effectId);

}
