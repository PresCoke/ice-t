package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.Attack;
import entity.Attack_Type;
import entity.CharacterSheet;
import entity.EntityEnum.A_Ability;
import entity.EntityEnum.A_Action;
import entity.EntityEnum.A_Defense;
import entity.EntityEnum.A_Effect_Type;
import entity.EntityEnum.A_Sustain;
import entity.EntityEnum.A_Use_Type;
import entity.EntityEnum.CS_Resistance_Type;

public class AttackDaoImpl implements AttackDao {
	
	private static final Logger logger = Logger.getLogger(AttackDaoImpl.class);

	public AttackDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public int saveAttack(String name, String primaryTarget,
			String secondaryTarget, String accessories, String powerSource,
			int frequency, String hit, String miss, boolean basic,
			String trigger, A_Effect_Type effectType, A_Ability ability,
			CS_Resistance_Type damageType, A_Defense defense,
			A_Sustain sustain, A_Action action, A_Use_Type useType,
			Attack_Type attack_type, int characterSheetId) {
    	logger.debug("Attack " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int attackId = -1;
        try {
            transaction = session.beginTransaction();
            logger.debug("Setting attack's attributes");
            Attack a = new Attack(name);
            //Set Attack's attributes
            a.setPrimaryTarget(primaryTarget);
            a.setSecondaryTarget(secondaryTarget);
            a.setAccessories(accessories);
            a.setPowerSource(powerSource);
            a.setFrequency(frequency);
            a.setHit(hit);
            a.setMiss(miss);
            a.setBasic(basic);
            a.setTrigger(trigger);
            a.setEffectType(effectType);
            a.setAbility(ability);
            a.setDamageType(damageType);
            a.setDefense(defense);
            a.setSustain(sustain);
            a.setAction(action);
            a.setUseType(useType);
            //Set Attack's type
            logger.debug("Setting attack's type");
            a.setAttackType(attack_type);
            attack_type.setAttack(a);
            //Set the characterSheet
            logger.debug("Setting characterSheet");
            CharacterSheet cs = (CharacterSheet) session.load(CharacterSheet.class, characterSheetId);
            a.setCharacterSheet(cs);
            //Save
            attackId = (Integer) session.save(a);          
            transaction.commit();
        	logger.info("Attack " + name + " was successfully saved in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Attack " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
        return attackId;
	}

	public void updateAttack(int attackId, String name, String primaryTarget,
			String secondaryTarget, String accessories, String powerSource,
			int frequency, String hit, String miss, boolean basic,
			String trigger, A_Effect_Type effectType, A_Ability ability,
			CS_Resistance_Type damageType, A_Defense defense,
			A_Sustain sustain, A_Action action, A_Use_Type useType,
			Attack_Type attack_type, int characterSheetId) {
    	logger.debug("Attack " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Attack a = (Attack) session.get(Attack.class, attackId);
            //Set Attack's attributes
            a.setAttackName(name);
            a.setPrimaryTarget(primaryTarget);
            a.setSecondaryTarget(secondaryTarget);
            a.setAccessories(accessories);
            a.setPowerSource(powerSource);
            a.setFrequency(frequency);
            a.setHit(hit);
            a.setMiss(miss);
            a.setBasic(basic);
            a.setTrigger(trigger);
            a.setEffectType(effectType);
            a.setAbility(ability);
            a.setDamageType(damageType);
            a.setDefense(defense);
            a.setSustain(sustain);
            a.setAction(action);
            a.setUseType(useType);
            //Set Attack's type
            //TODO
            
            //Set the characterSheet
            CharacterSheet cs = (CharacterSheet) session.load(CharacterSheet.class, characterSheetId);
            a.setCharacterSheet(cs);
            //Save
            attackId = (Integer) session.save(a);
            transaction.commit();
        	logger.info("Attack " + name + " was successfully updated in the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Attack " + name + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }
		
	}

	public void deleteAttack(int attackId) {
		logger.debug("Attack " + attackId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Attack a = (Attack) session.get(Attack.class, attackId);
            CharacterSheet cs = a.getCharacterSheet();
            logger.info("Deletion of Attack " + a.getAttackName() + "associated to the character sheet " + a.getCharacterSheet().getName());
            session.delete(a);
            logger.info("Update of the Character Sheet associated to the attack deleted.");
            session.saveOrUpdate(cs);
            transaction.commit();
        	logger.info("Attack " + attackId + " was successfully removed from the database.");
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Attack " + attackId + " in the database --- " + e.getMessage());
        } finally {
            session.close();
        }		
	}

}
