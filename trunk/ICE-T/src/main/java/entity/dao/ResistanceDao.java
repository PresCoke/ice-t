package entity.dao;

import entity.EntityEnum.CS_Resistance_Type;

public interface ResistanceDao {
	
	/**
	 * Save a resistance in the database
	 * @param resistanceType
	 * @param resistanceValue
	 * @param characterSheetId
	 * @return resistance's id stored in the database
	 */
	public int saveResistance(CS_Resistance_Type resistanceType, int resistanceValue, int characterSheetId);
	
	/**
	 * Update a resistance in the database
	 * @param resistanceId
	 * @param resistanceType
	 * @param resistanceValue
	 * @param characterSheetId
	 */
	public void updateResistance(int resistanceId, CS_Resistance_Type resistanceType, int resistanceValue, int characterSheetId);
	
	/**
	 * Delete a resistance from the database
	 * @param resistanceId
	 */
	public void deleteResistance(int resistanceId);
}

