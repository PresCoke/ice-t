package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Attack Area Class
 * @author TimHP
 *
 */

@Entity
@Table(name="A_ARea")
public class A_Area extends Attack_Type {

	@Id
	@GeneratedValue
	@Column(name="areaType")
	private EntityEnum.A_Area_Type areaType;
	
	@Column(name="range")
	private int range;	

	@Column(name="size")
	private int size;
	
	/**
	 * Default constructor
	 */
	public A_Area() {
	}
	
	/**
	 * Getters and Setters
	 */
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public EntityEnum.A_Area_Type getAreaType() {
		return areaType;
	}

	public void setAreaType(EntityEnum.A_Area_Type areaType) {
		this.areaType = areaType;
	}
}
