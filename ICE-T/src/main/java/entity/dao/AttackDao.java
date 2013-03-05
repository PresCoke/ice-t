package entity.dao;

import entity.Attack;
import entity.Attack_Type;
import entity.EntityEnum.A_Ability;
import entity.EntityEnum.A_Action;
import entity.EntityEnum.A_Defense;
import entity.EntityEnum.A_Effect_Type;
import entity.EntityEnum.A_Sustain;
import entity.EntityEnum.A_Use_Type;
import entity.EntityEnum.CS_Resistance_Type;

public interface AttackDao {
	
	/**
	 * Save an attack in the database
	 * @param name
	 * @param primaryTarget
	 * @param secondaryTarget
	 * @param accessories
	 * @param powerSource
	 * @param frequency
	 * @param hit
	 * @param miss
	 * @param basic
	 * @param trigger
	 * @param effectType
	 * @param ability
	 * @param damageType
	 * @param defense
	 * @param sustain
	 * @param action
	 * @param useType
	 * @param characterSheetId
	 * @return attack's id stored in the database
	 */
	public int saveAttack(String name, String primaryTarget, String secondaryTarget, String accessories, 
			String powerSource, int frequency, String hit, String miss, boolean basic, String trigger,
			A_Effect_Type effectType, A_Ability ability, CS_Resistance_Type damageType, A_Defense defense,
			A_Sustain sustain, A_Action action, A_Use_Type useType, int characterSheetId);
	
	/**
	 * Save an attack in the database
	 * @param name
	 * @param primaryTarget
	 * @param secondaryTarget
	 * @param accessories
	 * @param powerSource
	 * @param frequency
	 * @param hit
	 * @param miss
	 * @param basic
	 * @param trigger
	 * @param effectType
	 * @param ability
	 * @param damageType
	 * @param defense
	 * @param sustain
	 * @param action
	 * @param useType
	 * @param trapHazardId
	 * @return attack's id stored in the database
	 */
	public int saveAttackForTrap(String name, String primaryTarget, String secondaryTarget, String accessories, 
			String powerSource, int frequency, String hit, String miss, boolean basic, String trigger,
			A_Effect_Type effectType, A_Ability ability, CS_Resistance_Type damageType, A_Defense defense,
			A_Sustain sustain, A_Action action, A_Use_Type useType, int trapHazardId);
	
	/**
	 * Save an attack in the database
	 * @param attack
	 * @param characterSheetId
	 * @return attack's id stored in the database
	 */
	public int saveAttack(Attack attack, int characterSheetId);
	
	/**
	 * Update/Modify an attack stored in the database
	 * @param attackId
	 * @param name
	 * @param primaryTarget
	 * @param secondaryTarget
	 * @param accessories
	 * @param powerSource
	 * @param frequency
	 * @param hit
	 * @param miss
	 * @param basic
	 * @param trigger
	 * @param effectType
	 * @param ability
	 * @param damageType
	 * @param defense
	 * @param sustain
	 * @param action
	 * @param useType
	 * @param attack_type
	 * @param characterSheetId
	 */
	public void updateAttack(int attackId, String name, String primaryTarget, String secondaryTarget, 
			String accessories, String powerSource, int frequency, String hit, String miss, boolean basic,
			String trigger, A_Effect_Type effectType, A_Ability ability, CS_Resistance_Type damageType,
			A_Defense defense, A_Sustain sustain, A_Action action, A_Use_Type useType, Attack_Type attack_type,
			int characterSheetId);
	
	/**
	 * Delete a character's attack from the database
	 * @param attackId
	 */
	public void deleteAttack(int attackId);
	
	/**
	 * Delete a trap's attack from the database
	 * @param attackId
	 */
	public void deleteAttackFromTrap(int attackId);
}

