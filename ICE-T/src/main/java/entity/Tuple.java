package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tuple Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Tuple")
public class Tuple {

	@Id
	@GeneratedValue
	@Column(name="name")
	private String name;
	
	@Column(name="value1")
	private int value1;
	
	@Column(name="value2")
	private int value2;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Tuple(String name) {
		this.name = name;
	}

	
	/**
	 * Getters & Setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}

	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}

	
	
}
