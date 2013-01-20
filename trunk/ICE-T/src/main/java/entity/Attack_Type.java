package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Attack_Type Class
 * @author TimHP
 *
 */
@Entity
@Table(name="Attack_Type")
public abstract class Attack_Type {

	@Id
	@GeneratedValue
	@Column(name="attack_type")
	private String attack_type;

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
	
	public String getAttack_type() {
		return attack_type;
	}

	public void setAttack_type(String attack_type) {
		this.attack_type = attack_type;
	}
}
