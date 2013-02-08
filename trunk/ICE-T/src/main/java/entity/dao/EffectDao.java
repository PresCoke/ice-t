package entity.dao;

import java.util.List;

import entity.Creature;
import entity.Effect;
import entity.EntityEnum.E_Duration;

/**
 * DAO of an effect
 * @author TimHP
 *
 */
public interface EffectDao {

	public List<Effect> readAllEffects();
	public List<Integer> saveEffect(String name, String changes, String metrics, E_Duration duration, List<Creature> creatures);
	public void deleteEffects(List<Integer> effectsIds);
	public void deleteEffect(int effectId);

}
