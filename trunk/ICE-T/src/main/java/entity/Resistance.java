package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Resistance")
public class Resistance extends EntityM {
	
	@Column(name="resistanceType")
	private EntityEnum.CS_Resistance_Type resistanceType;
	@Column(name="resistanceValue")
	private int resistanceValue;
	
	public Resistance() {
	}
	
	public Resistance(String name) {
		super(name);
	}
	
	public EntityEnum.CS_Resistance_Type getResistanceType() {
		return resistanceType;
	}

	public void setResistanceType(EntityEnum.CS_Resistance_Type resistanceType) {
		this.resistanceType = resistanceType;
	}
	
	public int getResistanceValue() {
		return resistanceValue;
	}

	public void setResistanceValue(int resistanceValue) {
		this.resistanceValue = resistanceValue;
	}
	
	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

}
