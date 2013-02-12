package entity.dao;

import entity.Tally;

public interface TallyDao {
	public void deleteTally(int tallyId);
	public int saveTally (Tally tally);
}
