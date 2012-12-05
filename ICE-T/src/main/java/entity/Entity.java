package entity;

/**
 * Entity Class
 * @author TimHP
 *
 */
public class Entity {

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
	public void save(){
		//TODO
	}
	
	/**
	 * Edit an entity
	 */
	public void edit(){
		//TODO
	}
	
	/**
	 * Remove an entity
	 */
	public void remove(){
		//TODO
	}

	//Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
