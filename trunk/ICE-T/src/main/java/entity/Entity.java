package entity;

/**
 * Entity Class
 * @author TimHP and James Begg
 *
 */
public abstract class Entity {

	//Name
	private String name;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Entity(String name){
		this.name = name;
	}
	
	/**
	 * Save an entity
	 */
	public abstract void save();
	
	/**
	 * Edit an entity
	 */
	public abstract void edit();
	
	/**
	 * Remove an entity
	 */
	public abstract void remove();

	//Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
