package entity.dao;

public interface CreatureDao {

	public void readAllCreatures();
	public int saveCreature(String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP);
	public void updateCreature(int creatureId, String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP);
	public void deleteCreature(int creatureId);
	
}
