package entity.dao;

/**
 * DAO of a team
 * @author TimHP
 *
 */
public interface TeamDao {
	public void readAllTeams();
	public int saveTeam(String name);
	public void updateTeam(int teamId, String name);
	public void deleteTeam(int teamId);
}
