package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Attack Range Class
 * @author TimHP
 *
 */
@Entity
@Table(name="A_Range")
public class A_Range extends Attack_Type {

    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="A_Range_id")
    private int id;
	
	@Column(name="L_range")
	private int L_range;
	
	@Column(name="S_range")
	private int S_range;
	
	/**
	 * Default constructor
	 */
	public A_Range() {
	}


	/**
	 * Getters & Setters
	 */
	public int getL_range() {
		return L_range;
	}

	public void setL_range(int l_range) {
		L_range = l_range;
	}

	public int getS_range() {
		return S_range;
	}

	public void setS_range(int s_range) {
		S_range = s_range;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
