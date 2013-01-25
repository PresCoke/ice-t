package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Attack_Type Class
 * @author TimHP
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Attack_Type")
public abstract class Attack_Type {
	
    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Attack_Type_id")
    private int id;

	@Column(name="isPersonal")
	private boolean isPersonal;
	
	public Attack_Type() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getters & Setters
	 */
	public boolean isPersonal() {
		return isPersonal;
	}

	public void setPersonal(boolean isPersonal) {
		this.isPersonal = isPersonal;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
