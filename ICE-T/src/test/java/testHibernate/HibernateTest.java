package testHibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import presentation.New_Tab;

import entity.A_Area;
import entity.A_Close;
import entity.A_Melee;
import entity.A_Range;
import entity.Attack;
import entity.Attack_Type;
import entity.CharacterSheet;
import entity.Creature;
import entity.EntityEnum;
import entity.EntityEnum.CS_Monster_Origin;
import entity.EntityEnum.CS_Monster_Type;
import entity.EntityEnum.CS_Role;
import entity.EntityEnum.CS_Size;
import entity.EntityEnum.E_Duration;
import entity.EntityEnum.T_CounterMeasureSkill;
import entity.EntityEnum.T_Role;
import entity.EntityEnum.T_Type;
import entity.Resistance;
import entity.Rewards;
import entity.Tally;
import entity.Team;
import entity.TrapHazard;
import entity.Tuple;
import entity.dao.AttackDao;
import entity.dao.AttackDaoImpl;
import entity.dao.Attack_TypeDao;
import entity.dao.Attack_TypeDaoImpl;
import entity.dao.CharacterSheetDao;
import entity.dao.CharacterSheetDaoImpl;
import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;
import entity.dao.EffectDao;
import entity.dao.EffectDaoImpl;
import entity.dao.ResistanceDao;
import entity.dao.ResistanceDaoImpl;
import entity.dao.TallyDao;
import entity.dao.TallyDaoImpl;
import entity.dao.TeamDao;
import entity.dao.TeamDaoImpl;
import entity.dao.TrapHazardDao;
import entity.dao.TrapHazardDaoImpl;
//TODO Drop table trapHazard and re create it

public class HibernateTest {
	
	private static final Logger logger = Logger.getLogger(HibernateTest.class);
	
	public static void main(String[] args) throws HibernateException {

		List<Resistance> resistances = new ArrayList<Resistance>();
		Resistance r1 = new Resistance(EntityEnum.CS_Resistance_Type.cold, 100);
		Resistance r2 = new Resistance(EntityEnum.CS_Resistance_Type.acid, 100);
		resistances.add(r1);
		resistances.add(r2);
		
		List<Attack_Type> attacksTypes = new ArrayList<Attack_Type>();
//		A_Area t = new A_Area();
//		t.setPersonal(true);
//		t.setArea_range(100);
//		t.setArea_size(100);
//		t.setArea_type(EntityEnum.A_Area_Type.burst);
//		attacksTypes.add(t);
//		A_Area t2 = new A_Area();
//		t2.setPersonal(true);
//		t2.setArea_range(0);
//		t2.setArea_size(0);
//		t2.setArea_type(EntityEnum.A_Area_Type.wall);
//		attacksTypes.add(t2);
//		A_Close t = new A_Close();
//		t.setPersonal(false);
//		t.setCloseType(EntityEnum.A_Close_Type.burst);
//		t.setSize(100);
//		attacksTypes.add(t);
//		A_Close t2 = new A_Close();
//		t2.setPersonal(true);
//		t2.setCloseType(EntityEnum.A_Close_Type.blast);
//		t2.setSize(1);
//		attacksTypes.add(t2);
		A_Melee t = new A_Melee();
		t.setPersonal(true);
		t.setReach(100);
		attacksTypes.add(t);
		A_Range t2 = new A_Range();
		t2.setPersonal(true);
		t2.setL_range(50);
		t2.setS_range(10);
		attacksTypes.add(t2);
		
		List<Attack> attacks = new ArrayList<Attack>();
		Attack a1 = new Attack("AttackT3");
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
		Attack a2 = new Attack("AttackT4");
		a2.setPrimaryTarget("Bill");
		a2.setSecondaryTarget("Jack");
		a2.setAccessories("Gun");
		a2.setPowerSource("Gun");
		a2.setFrequency(10);
		a2.setHit("2D16");
		a2.setMiss("2D4");
		a2.setBasic(true);
		a2.setTrigger("The player can use that attack whenever");
		a2.setEffectType(EntityEnum.A_Effect_Type.fear);
		a2.setAbility(EntityEnum.A_Ability.STR);
		a2.setDamageType(EntityEnum.CS_Resistance_Type.thunder);
		a2.setDefense(EntityEnum.A_Defense.REF);
		a2.setSustain(EntityEnum.A_Sustain.standard);
		a2.setAction(EntityEnum.A_Action.minor);
		a2.setUseType(EntityEnum.A_Use_Type.atWill);
		attacks.add(a2);
	
		CharacterSheetDao csDao = new CharacterSheetDaoImpl();

//		csDao.saveCharacterSheet("Terminator", 10, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances, attacks, attacksTypes);
//		
//		
//		csDao.updateCharacterSheet(1, "T-1000", 20, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances, attacks, attacksTypes);	
//		
//		
		csDao.deleteCharacterSheet(1);
//		csDao.deleteCharacterSheet(6);
//		
//		ResistanceDao resistanceDao = new ResistanceDaoImpl();
//		resistanceDao.deleteResistance(1);
//		resistanceDao.deleteResistance(2);
//		
//		AttackDao attackDao = new AttackDaoImpl();
//		attackDao.deleteAttack(1);
//		
//		Attack_TypeDao attacktypeDao = new Attack_TypeDaoImpl();
//		attacktypeDao.deleteAttackType(2);
//		
//		TrapHazardDao thDao = new TrapHazardDaoImpl();
//		thDao.saveTrapHazard("BigTrap", 5, 10, T_CounterMeasureSkill.bluff , "When a creature steps on it", 6, 18, 
//				"none", EntityEnum.T_Type.trap, EntityEnum.T_Role.obstacle, EntityEnum.T_CounterMeasureSkill.heal,
//				a1, t);
//		thDao.saveTrapHazard("BigTrap2", 5, 10, T_CounterMeasureSkill.bluff , "When a creature steps on it", 6, 18, 
//				"none", EntityEnum.T_Type.trap, EntityEnum.T_Role.obstacle, EntityEnum.T_CounterMeasureSkill.heal,
//				a1, t);
//		thDao.updateTrapHazard(3, "BigTrap", 5, 10, T_CounterMeasureSkill.bluff , "When a creature steps on it", 6, 18, 
//				"none", EntityEnum.T_Type.trap, EntityEnum.T_Role.obstacle, EntityEnum.T_CounterMeasureSkill.heal,
//				a1, t);
//		thDao.deleteTrapHazard(3);
//		

//
//		creatures.remove(0);
//		for(Creature c : creatures){
//			logger.debug("Creature's name = " + c.getPlayerName() + " - Creature's CS = " + c.getCharacterSheet().getName());
//		}
//		
//		CharacterSheet cs = csDao.getCharacterSheets(1);
		
//		CreatureDao cDao = new CreatureDaoImpl();
//		cDao.saveCreature("Tim", 100, 10, 1, true, 10, cs);
//		cDao.saveCreature("James", 100, 10, 1, true, 10, cs);

//		List<Creature> creatures = new ArrayList<Creature>();
//		creatures = cDao.readAllCreatures(0);
		
//		TeamDao teamDao = new TeamDaoImpl();
//		teamDao.saveTeam("IceTeam", creatures);
//		teamDao.updateTeam(1, "IceTeam", creatures);
//		teamDao.deleteTeam(1);
		
//		EffectDao effectDao = new EffectDaoImpl();
//		effectDao.saveEffect("EffectForTimAndJames", "Fuck that shit", "Fuck that man", E_Duration.endOfTheEncounter, creatures);
//		
//		effectDao.deleteEffect(2);
//		
//		List<Integer> effectsIds = new ArrayList<Integer>();
//		effectsIds.add(1);
//		effectsIds.add(2);
//		
//		effectDao.deleteEffects(effectsIds);
//		
//		
//		List<Rewards> rewards = new ArrayList<Rewards>();
//		Rewards rew1 = new Rewards(20, "Gold");
//		Rewards rew2 = new Rewards(50, "Gold & Silver");
//		Rewards rew3 = new Rewards(110, "Sword");
//		rewards.add(rew1);
//		rewards.add(rew2);
//		rewards.add(rew3);
//
//		Tally tally = new Tally("Score");
//		List<Tuple> tuples = new ArrayList<Tuple>();
//		Tuple tuple1 = new Tuple("tuple4", 5, 6);
//		Tuple tuple2 = new Tuple("tuple2", 15, 8);
//		tuples.add(tuple1);
//		tuples.add(tuple2);
//
//		CreatureDao cDao = new CreatureDaoImpl();
//		List<Creature> creatures = new ArrayList<Creature>();
//		creatures = cDao.readAllCreatures(0);
//		creatures.remove(0);
//		
//		teamDao.saveTeam("IceTeam1", creatures);
//		teamDao.saveTeam("IceTeam2", creatures);
//		
//		List<Team> teams = new ArrayList<Team>();
//		TeamDao teamDao = new TeamDaoImpl();
//		Team team1 = teamDao.getTeam(1);
//		Team team2 = teamDao.getTeam(2);
//		teams.add(team1);
//		teams.add(team2);
//		List<TrapHazard> traphazards = new ArrayList<TrapHazard>();
//		TrapHazardDao thDao = new TrapHazardDaoImpl();
//		TrapHazard th1 = thDao.getTrapHazard(1);
//		TrapHazard th2 = thDao.getTrapHazard(2);
//		traphazards.add(th1);
//		traphazards.add(th2);
//
//		CombatEncounterDao ceDao = new CombatEncounterDaoImpl();
//		ceDao.saveCombatEncounter("CE1", "Game almost over", 1, rewards, tally, tuples, teams, traphazards);
//		ceDao.updateCombatEncounter(1,"CE2", "Game almost over", 2, rewards, tally, tuples, teams, traphazards);
//		ceDao.deleteCombatEncounter(1);
//		ceDao.readAllCombatEncounters();
//		
//		TallyDao tallyDao = new TallyDaoImpl();
//		tallyDao.deleteTally(1);
		

	}
	
}
