package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * Attack Area Class
 * @author TimHP
 *
 */

@Entity
@Table(name="A_ARea")
public class A_Area extends Attack_Type {

    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    @Column(name="A_ARea_id")
    private int id;

	@Column(name="area_range")
	private int area_range;	

	@Column(name="area_size")
	private int area_size;
	
	//Enum
	@Column(name="area_type")
	private EntityEnum.A_Area_Type area_type;
	
	/**
	 * Default constructor
	 */
	public A_Area() {
	}

	
	/**
	 * Getters and Setters
	 */
	public EntityEnum.A_Area_Type getArea_type() {
		return area_type;
	}

	public void setArea_type(EntityEnum.A_Area_Type area_type) {
		this.area_type = area_type;
	}

	public int getArea_range() {
		return area_range;
	}

	public void setArea_range(int area_range) {
		this.area_range = area_range;
	}

	public int getArea_size() {
		return area_size;
	}

	public void setArea_size(int area_size) {
		this.area_size = area_size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
