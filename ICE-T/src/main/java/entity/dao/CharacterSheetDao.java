package entity.dao;

import java.util.List;

import entity.Attack;
import entity.Attack_Type;
import entity.CharacterSheet;
import entity.EntityEnum;
import entity.Resistance;

/**
 * DAO of a character sheet
 * @author TimHP
 *
 */
public interface CharacterSheetDao {
	
	/**
	 * Get a list containing the ids and names of all the character sheets in the database
	 * @return a list of Object array, each array containing an int (id) and a String (name)
	 */
	public List<Object[]> readAllCharacterSheets();
	
	/**
	 * Get a list of all character sheets in database
	 * @return list of character sheets
	 */
	public List<CharacterSheet> getAllCharacterSheets();
	
	/**
	 * Get a specific character sheet in the database
	 * @param id of the character sheet
	 * @return character sheet associated to the id
	 */
	public CharacterSheet getCharacterSheet(int characterSheetId);
	
	/**
	 * Save a character sheet in the database
	 * @param name
	 * @param acrobatics
	 * @param athletics
	 * @param arcana
	 * @param bluff
	 * @param diplomacy
	 * @param dungeoneering
	 * @param endurance
	 * @param heal
	 * @param history
	 * @param insight
	 * @param intimidate
	 * @param nature
	 * @param perception
	 * @param religion
	 * @param stealth
	 * @param streetwise
	 * @param thievery
	 * @param AC
	 * @param REF
	 * @param FORT
	 * @param WILL
	 * @param maxHP
	 * @param surgesPerDay
	 * @param STR
	 * @param CON
	 * @param INT
	 * @param DEX
	 * @param WIS
	 * @param CHAR
	 * @param level
	 * @param XP
	 * @param raceFeatures
	 * @param speed
	 * @param initiative
	 * @param languages
	 * @param misc
	 * @param keywords
	 * @param powerSource
	 * @param role
	 * @param size
	 * @param monsterOrigin
	 * @param monsterType
	 * @param resistances
	 * @param attacks
	 * @param attacksTypes
	 * @param isNPC
	 * @return character sheet's id stored in the database
	 */
	public int saveCharacterSheet(String name, int acrobatics, int athletics, int arcana, int bluff, int diplomacy, int dungeoneering,
			int endurance, int heal, int history, int insight, int intimidate, int nature, int perception, int religion, int stealth,
			int streetwise, int thievery, int AC, int REF, int FORT, int WILL, int maxHP, int surgesPerDay, int STR, int CON, int INT,
			int DEX, int WIS, int CHAR, int level, int XP, String raceFeatures, int speed, int initiative, String languages, String misc,
			String keywords, String powerSource, EntityEnum.CS_Role role, EntityEnum.CS_Size size, EntityEnum.CS_Monster_Origin monsterOrigin, 
			EntityEnum.CS_Monster_Type monsterType, List<Resistance> resistances, List<Attack> attacks, List<Attack_Type> attacksTypes,
			boolean isNPC);
	
	/**
	 * Update/Modify a character sheet in the database
	 * @param characterSheetId
	 * @param name
	 * @param acrobatics
	 * @param athletics
	 * @param arcana
	 * @param bluff
	 * @param diplomacy
	 * @param dungeoneering
	 * @param endurance
	 * @param heal
	 * @param history
	 * @param insight
	 * @param intimidate
	 * @param nature
	 * @param perception
	 * @param religion
	 * @param stealth
	 * @param streetwise
	 * @param thievery
	 * @param AC
	 * @param REF
	 * @param FORT
	 * @param WILL
	 * @param maxHP
	 * @param surgesPerDay
	 * @param STR
	 * @param CON
	 * @param INT
	 * @param DEX
	 * @param WIS
	 * @param CHAR
	 * @param level
	 * @param XP
	 * @param raceFeatures
	 * @param speed
	 * @param initiative
	 * @param languages
	 * @param misc
	 * @param keywords
	 * @param powerSource
	 * @param role
	 * @param size
	 * @param monsterOrigin
	 * @param monsterType
	 * @param resistances
	 * @param attacks
	 * @param attacksTypes
	 * @param isNPC
	 */
	public void updateCharacterSheet(int characterSheetId, String name, int acrobatics, int athletics, int arcana, int bluff, int diplomacy,
			int dungeoneering, int endurance, int heal, int history, int insight, int intimidate, int nature, int perception, int religion,
			int stealth, int streetwise, int thievery, int AC, int REF, int FORT, int WILL, int maxHP, int surgesPerDay, int STR, int CON, 
			int INT, int DEX, int WIS, int CHAR, int level, int XP, String raceFeatures, int speed, int initiative, String languages, String misc,
			String keywords, String powerSource, EntityEnum.CS_Role role, EntityEnum.CS_Size size, EntityEnum.CS_Monster_Origin monsterOrigin,
			EntityEnum.CS_Monster_Type monsterType, List<Resistance> resistances, List<Attack> attacks, List<Attack_Type> attacksTypes,
			boolean isNPC);
	
	/**
	 * Delete a character sheet from the database
	 * @param character sheet's id 
	 */
	public void deleteCharacterSheet(int characterSheetId);
	
}
