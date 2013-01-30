package entity.dao;

import entity.EntityEnum.CS_Resistance_Type;

public interface ResistanceDao {
	public int saveResistance(String name, CS_Resistance_Type resistanceType, int resistanceValue, int characterSheetId);
	public void updateResistance(int resistanceId, String name, CS_Resistance_Type resistanceType, int resistanceValue, int characterSheetId);
	public void deleteResistance(int resistanceId);
}

