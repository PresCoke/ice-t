package testHibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import entity.EntityEnum;
import entity.EntityEnum.CS_Monster_Origin;
import entity.EntityEnum.CS_Monster_Type;
import entity.EntityEnum.CS_Role;
import entity.EntityEnum.CS_Size;
import entity.dao.CharacterSheetDao;
import entity.dao.CharacterSheetDaoImpl;
import entity.dao.CombatEncounterDao;
import entity.dao.CombatEncounterDaoImpl;
import entity.dao.CreatureDao;
import entity.dao.CreatureDaoImpl;
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
//		List<Integer> resistances_id = new ArrayList<Integer>();
//		resistances_id.add(1);
//		CharacterSheetDao csDao = new CharacterSheetDaoImpl();
//		csDao.saveCharacterSheet("Terminator", 10, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances_id);
//		csDao.updateCharacterSheet(4, "T-1000", 20, 15, 10, 8, 16, 9, 18, 2, 5, 7, 1, 3, 5, 4, 19, 17, 4, 
//			6, 4, 2, 7, 50, 5, 8, 2, 2, 2, 2, 2, 1, 0, "made with metal", 10, 2, "all of them", "misc",
//			"T-600", "uranium", CS_Role.brute, CS_Size.huge, CS_Monster_Origin.immortal, CS_Monster_Type.humanoid,
//			resistances_id);	
//		csDao.deleteCharacterSheet(4);
	}
	
}
