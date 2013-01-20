package entity.dao;

public interface CreatureDao {

	public void readAllCreatures();
	public Long saveCreature(String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP);
	public void updateCity(Long creatureId, String playerName, int kills, int currentHP, int currentHealSurges, int currentLevel, boolean secondWind, int tempHP);
	public void deleteCity(Long creatureId);
	
}
