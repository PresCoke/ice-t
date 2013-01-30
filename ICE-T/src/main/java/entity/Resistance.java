package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Resistance")
public class Resistance {
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
    @Column(name="Resistance_id")
    private int id;	
    
	@Column(name="Resistance_name")
	private String name;
	
	@Column(name="resistanceType")
	private EntityEnum.CS_Resistance_Type resistanceType;
	
	@Column(name="resistanceValue")
	private int resistanceValue;
	
	//Associations
	@ManyToOne
	@JoinColumn (name="CharacterSheet_id")
	private CharacterSheet characterSheet;
	
	/**
	 * Constructors
	 */
	public Resistance() {
	}
	
	public Resistance(String name) {
		this.name=name;
	}
	
	/**
	 * Getters & Setters
	 */
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
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CharacterSheet getCharacterSheet() {
		return characterSheet;
	}

	public void setCharacterSheet(CharacterSheet characterSheet) {
		this.characterSheet = characterSheet;
	}
}
