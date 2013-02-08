package entity.dao;

import java.util.List;

import entity.Creature;
import entity.Team;


/**
 * DAO of a team
 * @author TimHP
 *
 */
public interface TeamDao {
	public List<Team> readAllTeams();
	public int saveTeam(String name, List<Creature> creatures);
	public void updateTeam(int teamId, String name, List<Creature> creatures);
	public void deleteTeam(int teamId);
}
