package entity.dao;

public interface TupleDao {

	/**
	 * Save a tuple in database
	 * @param name
	 * @param value1
	 * @param value2
	 * @param tallyId
	 * @return tuple's id stored in the database
	 */
	public int saveTuple(String name, int value1, int value2, int tallyId);
	
}
