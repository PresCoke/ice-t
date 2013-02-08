package entity.dao;

import java.util.List;

import entity.CharacterSheet;
import entity.Creature;
import entity.Team;

public interface CreatureDao {

	public List<Creature> readAllCreatures();
	
	public int saveCreature(String playerName, int currentHP, int currentHealSurges, int currentLevel,
			boolean secondWind, int tempHP, CharacterSheet characterSheet);
	
	public void updateCreature(int creatureId, String playerName, int currentHP, int currentHealSurges,
			int currentLevel, boolean secondWind, int tempHP);
	
	public void updateCreature(Creature creature, Team team);
	
	public void deleteCreature(int creatureId);
	
}
