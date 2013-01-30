package entity.dao;

import java.util.List;

import entity.EntityEnum;

/**
 * DAO of a character sheet
 * @author TimHP
 *
 */
public interface CharacterSheetDao {
	
	public void readAllCharacterSheets();
	
	public int saveCharacterSheet(String name, int acrobatics, int athletics, int arcana, int bluff, int diplomacy, int dungeoneering,
			int endurance, int heal, int history, int insight, int intimidate, int nature, int perception, int religion, int stealth,
			int streetwise, int thievery, int AC, int REF, int FORT, int WILL, int maxHP, int surgesPerDay, int STR, int CON, int INT,
			int DEX, int WIS, int CHAR, int level, int XP, String raceFeatures, int speed, int initiative, String languages, String misc,
			String keywords, String powerSource, EntityEnum.CS_Role role, EntityEnum.CS_Size size, EntityEnum.CS_Monster_Origin monsterOrigin, 
			EntityEnum.CS_Monster_Type monsterType, List<Integer> character_resistances_id);
	
	public void updateCharacterSheet(int characterSheetId, String name, int acrobatics, int athletics, int arcana, int bluff, int diplomacy,
			int dungeoneering, int endurance, int heal, int history, int insight, int intimidate, int nature, int perception, int religion,
			int stealth, int streetwise, int thievery, int AC, int REF, int FORT, int WILL, int maxHP, int surgesPerDay, int STR, int CON, 
			int INT, int DEX, int WIS, int CHAR, int level, int XP, String raceFeatures, int speed, int initiative, String languages, String misc,
			String keywords, String powerSource, EntityEnum.CS_Role role, EntityEnum.CS_Size size, EntityEnum.CS_Monster_Origin monsterOrigin,
			EntityEnum.CS_Monster_Type monsterType, List<Integer> character_resistances_id);
	
	public void deleteCharacterSheet(int characterSheetId);
	
}
