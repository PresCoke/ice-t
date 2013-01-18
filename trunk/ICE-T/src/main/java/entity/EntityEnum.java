package entity;

public final class EntityEnum {
	
	//Resistance type of a creature
	public enum CS_Resistance_Type {
		acid,
		cold,
		fire,
		force,
		lightning,
		nerotic,
		poison,
		psychic,
		radiant,
		thunder,
		NONE;
	}
	
	//Jobs of the creatures
	public enum CS_Role {
		striker,
		controller,
		defender,
		leader,
		artillery,
		brute,
		lurker,
		skirmisher,
		soldier;
	}
	
	//Size of a creature
	public enum CS_Size {
		tiny,
		small,
		medium,
		large,
		huge,
		gargantuan;
	}
	
	//Type of the monster
	public enum CS_Monster_Type{
		animate,
		beast,
		humanoid,
		magicalBeast;
	}
	
	//Origin of the monster
	public enum CS_Monster_Origin{
		aberrant,
		elemental,
		fey,
		immortal,
		shadow;
	}
	
	//Types of a trap
	public enum T_Type {
		trap,
		hazard;
	}
	
	//Roles of the traps and hazards
	public enum T_Role {
		blaster,
		lurker,
		obstacle,
		warder,
		solo,
		elite;	
	}
	
	//Skill required to use against the trap
	public enum T_CounterMeasureSkill{
		acrobatics,
		athletics,
		arcana,
		bluff,
		diplomacy,
		dungeoneering,
		endurance,
		heal,
		history,
		insight,
		intimidate,
		nature,
		perception,
		religion,
		stealth,
		streetwise,
		thievery;
	}
	
	//Duration of an effect
	public enum E_Duration{
		untilStartOfApplyingCreaturesNextTurn,
		untilEndOfApplyingCreaturesNextTurn,
		endOfTheEncounter,
		saveEnds;
	}
	
	//Effect produced by an attack
	public enum A_Effect_Type{
		charm,
		conjuration,
		fear,
		healing,
		illusion,
		poison,
		polymorph,
		reliable,
		sleep,
		stance,
		teleportation,
		zone,
		NONE;
	}
	
	//Ability of the attack
	public enum A_Ability{
		STR,
		CON,
		INT,
		DEX,
		WIS,
		CHAR;
	}
	
	//Defense of the attack
	public enum A_Defense{
		AC,
		REF,
		FORT,
		WILL;
	}
	
	//Sustain of the attack
	public enum A_Sustain{
		standard,
		move,
		minor,
		NONE;
	}
	
	//Action of the attack
	public enum A_Action{
		standard,
		move,
		minor,
		free;
	}
	
	//Use type of the attack
	public enum A_Use_Type{
		atWill,
		encounter,
		daily,
		recharge;
	}
	
	//Close type of the attack
	public enum A_Close_Type{
		blast,
		burst;
	}
	
	//Area type of the attack
	public enum A_Area_Type{
		wall,
		burst;
	}
}