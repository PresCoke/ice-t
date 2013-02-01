package entity.dao;

import entity.Attack_Type;
import entity.EntityEnum.A_Ability;
import entity.EntityEnum.A_Action;
import entity.EntityEnum.A_Defense;
import entity.EntityEnum.A_Effect_Type;
import entity.EntityEnum.A_Sustain;
import entity.EntityEnum.A_Use_Type;
import entity.EntityEnum.CS_Resistance_Type;

public interface AttackDao {
	public int saveAttack(String name, String primaryTarget, String secondaryTarget, String accessories, 
			String powerSource, int frequency, String hit, String miss, boolean basic, String trigger,
			A_Effect_Type effectType, A_Ability ability, CS_Resistance_Type damageType, A_Defense defense,
			A_Sustain sustain, A_Action action, A_Use_Type useType, Attack_Type attack_type, int characterSheetId);
	public void updateAttack(int attackId, String name, String primaryTarget, String secondaryTarget, 
			String accessories, String powerSource, int frequency, String hit, String miss, boolean basic,
			String trigger, A_Effect_Type effectType, A_Ability ability, CS_Resistance_Type damageType,
			A_Defense defense, A_Sustain sustain, A_Action action, A_Use_Type useType, Attack_Type attack_type,
			int characterSheetId);
	public void deleteAttack(int attackId);
}

