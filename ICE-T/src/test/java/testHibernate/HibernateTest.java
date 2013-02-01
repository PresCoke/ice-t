package testHibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import entity.A_Area;
import entity.A_Close;
import entity.Attack;
import entity.Attack_Type;
import entity.EntityEnum;
import entity.EntityEnum.CS_Monster_Origin;
import entity.EntityEnum.CS_Monster_Type;
import entity.EntityEnum.CS_Role;
import entity.EntityEnum.CS_Size;
import entity.Resistance;
import entity.dao.AttackDao;
import entity.dao.AttackDaoImpl;
import entity.dao.Attack_TypeDAO;
import entity.dao.Attack_TypeDAOImpl;
import entity.dao.CharacterSheetDao;
import entity.dao.CharacterSheetDaoImpl;
import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;
import entity.dao.ResistanceDao;
import entity.dao.ResistanceDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;


public class HibernateTest {
	
	public static void main(String[] args) throws HibernateException {
//		CreatureDao cDao = new CreatureDaoImpl();
//		cDao.readAllCreatures();
//		
//		CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
//		ceDao.readAllCombatEncounters();
//		
//		TrapHazardDao thDao = new TrapHazardDaoImpl();
//		thDao.saveTrapHazard("BigTrap", 5, 10, "hole", "When a creature steps on it", 6, 18, 16, 
//				"none", EntityEnum.T_Type.trap, EntityEnum.T_Role.obstacle, EntityEnum.T_CounterMeasureSkill.heal);
//		thDao.updateTrapHazard(3,"BigBigTrap", 5, 10, "hole", "When a creature steps on it", 6, 18, 16, 
//				"none", EntityEnum.T_Type.trap, EntityEnum.T_Role.obstacle, EntityEnum.T_CounterMeasureSkill.heal);
//		thDao.deleteTrapHazard(2);
		

		List<Resistance> resistances = new ArrayList<Resistance>();
//		Resistance r1 = new Resistance("ResistanceT1", EntityEnum.CS_Resistance_Type.cold, 0);
//		Resistance r2 = new Resistance("ResistanceT3", EntityEnum.CS_Resistance_Type.lightning, 28);
//		resistances.add(r1);
//		resistances.add(r2);
		
		List<Attack_Type> attacksTypes = new ArrayList<Attack_Type>();
//		A_Area t = new A_Area();
//		t.setPersonal(true);
//		t.setArea_range(100);
//		t.setArea_size(100);
//		t.setArea_type(EntityEnum.A_Area_Type.wall);
//		attacksTypes.add(t);
		A_Close t = new A_Close();
		t.setPersonal(false);
		t.setCloseType(EntityEnum.A_Close_Type.blast);
		t.setSize(15);
		attacksTypes.add(t);
//		A_Area t2 = new A_Area();
//		t2.setPersonal(false);
//		t2.setArea_range(0);
//		t2.setArea_size(0);
//		t2.setArea_type(EntityEnum.A_Area_Type.wall);
//		attacksTypes.add(t2);
		
		List<Attack> attacks = new ArrayList<Attack>();
		Attack a1 = new Attack("AttackT1");
		a1.setPrimaryTarget("Bill");
		a1.setSecondaryTarget("Jack");
		a1.setAccessories("Gun");
		a1.setPowerSource("Gun");
		a1.setFrequency(10);
		a1.setHit("2D16");
		a1.setMiss("2D4");
		a1.setBasic(true);
		a1.setTrigger("The player can use that attack whenever");
		a1.setEffectType(EntityEnum.A_Effect_Type.fear);
		a1.setAbility(EntityEnum.A_Ability.STR);
		a1.setDamageType(EntityEnum.CS_Resistance_Type.thunder);
		a1.setDefense(EntityEnum.A_Defense.REF);
		a1.setSustain(EntityEnum.A_Sustain.standard);
		a1.setAction(EntityEnum.A_Action.minor);
		a1.setUseType(EntityEnum.A_Use_Type.atWill);
		attacks.add(a1);
//		Attack a2 = new Attack("AttackT2");
//		a2.setPrimaryTarget("Bill");
//		a2.setSecondaryTarget("Jack");
//		a2.setAccessories("Gun");
//		a2.setPowerSource("Gun");
//		a2.setFrequency(10);
//		a2.setHit("2D16");
//		a2.setMiss("2D4");
//		a2.setBasic(true);
//		a2.setTrigger("The player can use that attack whenever");
//		a2.setEffectType(EntityEnum.A_Effect_Type.fear);
//		a2.setAbility(EntityEnum.A_Ability.STR);
//		a2.setDamageType(EntityEnum.CS_Resistance_Type.thunder);
//		a2.setDefense(EntityEnum.A_Defense.REF);
//		a2.setSustain(EntityEnum.A_Sustain.standard);
//		a2.setAction(EntityEnum.A_Action.minor);
//		a2.setUseType(EntityEnum.A_Use_Type.atWill);
//		attacks.add(a2);
		

		CharacterSheetDao csDao = new CharacterSheetDaoImpl();

//		csDao.saveCharacterSheet("Terminator", 10, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances, attacks, attacksTypes);
//		
		
//		csDao.updateCharacterSheet(4, "T-1000", 20, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances, attacks, attacksTypes);	
		
		
//		csDao.deleteCharacterSheet(4);
		
//		ResistanceDao resistanceDao = new ResistanceDaoImpl();
//		resistanceDao.deleteResistance(5);
		
//		AttackDao attackDao = new AttackDaoImpl();
//		attackDao.deleteAttack(10);
		
//		Attack_TypeDAO attacktypeDao = new Attack_TypeDAOImpl();
//		attacktypeDao.deleteAttackType(1);

	}
	
}
