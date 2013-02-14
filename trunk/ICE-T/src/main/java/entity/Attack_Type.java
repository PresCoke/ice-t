package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
public class Attack_Type {
	
    @Id
    @GenericGenerator(name="generator", strategy="increment")
    @GeneratedValue(generator="generator")
    @Column(name="Attack_Type_id")
    private int id;

	@Column(name="isPersonal")
	private boolean isPersonal;
	
	//Associations
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn (name="Attack_id")
	private Attack attack;
	
	public Attack_Type() {
		isPersonal = true;
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

	public Attack getAttack() {
		return attack;
	}

	public void setAttack(Attack attack) {
		this.attack = attack;
	}
}
