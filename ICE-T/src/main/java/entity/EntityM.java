package entity;

import java.util.List;


/**
 * EntityM Interface
 * @author TimHP and James Begg
 *
 */
public interface EntityM {
	/**
	 * Save an entity
	 */
	public int save();
	
	/**
	 * Edit an entity
	 */
	public int edit();
	
	/**
	 * Remove an entity
	 */
	public abstract void remove();
	
	/**
	 * Read all entities of one type in DB
	 */
	public abstract List<Object[]> getAll();
}
