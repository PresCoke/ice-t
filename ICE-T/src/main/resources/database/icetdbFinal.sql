CREATE TABLE CombatEncounter (
	CombatEncounter_id INTEGER NOT NULL,
	CombatEncounter_name VARCHAR(50) NOT NULL UNIQUE,
	notes VARCHAR(5000),
	currentCreatureId INTEGER,
	CONSTRAINT PK_CombatEncounter PRIMARY KEY (CombatEncounter_id)
)

CREATE TABLE CharacterSheet (
	CharacterSheet_id INTEGER NOT NULL,
	CharacterSheet_name VARCHAR(50) NOT NULL UNIQUE, 
	acrobatics INTEGER,
	athletics INTEGER,
	arcana INTEGER,
	bluff INTEGER,
	diplomacy INTEGER,
	dungeoneering INTEGER,
	endurance INTEGER,
	heal INTEGER,
	history INTEGER,
	insight INTEGER,
	intimidate INTEGER,
	nature INTEGER,
	perception INTEGER,
	religion INTEGER,
	stealth INTEGER,
	streetwise INTEGER,
	thievery INTEGER,
	AC INTEGER NOT NULL,
	RE INTEGER NOT NULL,
	FORT INTEGER NOT NULL,
	WILL INTEGER NOT NULL,
	maxHP INTEGER NOT NULL,
	bloodied INTEGER NOT NULL,
	surgesValue INTEGER,
	surgesPerDay INTEGER,
	STR INTEGER NOT NULL,
	CON INTEGER NOT NULL,
	INTE INTEGER NOT NULL,
	DEX INTEGER NOT NULL,
	WIS INTEGER NOT NULL,
	CHARA INTEGER NOT NULL,
	levelCS INTEGER NOT NULL,
	XP INTEGER NOT NULL,
	raceFeatures VARCHAR(1000),
	speed INTEGER NOT NULL,
	initiative INTEGER NOT NULL,
	languages VARCHAR(100),
	misc VARCHAR(1000),
	keywords VARCHAR(1000),
	powerSource VARCHAR(100),
	roleCS INTEGER,
	sizeCS INTEGER NOT NULL,
	monsterOriginCS INTEGER,
	monsterTypeCS INTEGER,
	CONSTRAINT PK_CharacterSheet PRIMARY KEY (CharacterSheet_id)
)

CREATE TABLE Resistance (
	Resistance_id INTEGER NOT NULL,
	CharacterSheet_id INTEGER NOT NULL,
	resistanceType INTEGER NOT NULL,
	resistanceValue INTEGER NOT NULL,
	CONSTRAINT PK_Resistance PRIMARY KEY (Resistance_id),
	CONSTRAINT FK_Resistance_CharacterSheet FOREIGN KEY (CharacterSheet_id) REFERENCES CharacterSheet (CharacterSheet_id)
)

CREATE TABLE Team (
	Team_id INTEGER NOT NULL,
	Team_name VARCHAR(50) NOT NULL UNIQUE,
	CombatEncounter_id INTEGER,
	CONSTRAINT PK_Team PRIMARY KEY (Team_id),
	CONSTRAINT FK_Team_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id)
)

CREATE TABLE TrapHazard (
	TrapHazard_id INTEGER NOT NULL,
	TrapHazard_name VARCHAR(50) NOT NULL UNIQUE,
	CombatEncounter_id INTEGER,
	avoidance INTEGER,
	levelTH INTEGER NOT NULL,
	avoidanceSkill INTEGER,
	triggers VARCHAR(500),
	xp INTEGER NOT NULL,
	counterMeasuredifficultyLevel INTEGER,
	counterMeasureDescription VARCHAR(500),
	typeTH INTEGER NOT NULL,
	roleTH INTEGER,
	counterMeasureSkillTH INTEGER,
	CONSTRAINT PK_TrapHazard PRIMARY KEY (TrapHazard_id),
	CONSTRAINT FK_TrapHazard_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id)
)

CREATE TABLE Creature (
	Creature_id INTEGER NOT NULL,
	player_name VARCHAR(50) NOT NULL UNIQUE,
	CharacterSheet_id INTEGER NOT NULL,
	Team_id INTEGER,
	currentHP INTEGER NOT NULL,
	currentHealSurges INTEGER NOT NULL,
	currentLevel INTEGER NOT NULL,
	secondWind BOOLEAN NOT NULL,
	tempHP INTEGER,
	CONSTRAINT PK_Creature PRIMARY KEY (Creature_id),
	CONSTRAINT FK_Creature_CharacterSheet FOREIGN KEY (CharacterSheet_id) REFERENCES CharacterSheet (CharacterSheet_id),
	CONSTRAINT FK_Creature_Team FOREIGN KEY (Team_id) REFERENCES Team (Team_id)
)

CREATE TABLE Stats (
	Stats_id INTEGER NOT NULL PRIMARY KEY,
	Creature_id INTEGER NOT NULL,
	kills INTEGER NOT NULL,
	deaths INTEGER NOT NULL,
	hits INTEGER NOT NULL,
	misses INTEGER NOT NULL,
	assists INTEGER NOT NULL,
	CONSTRAINT FK_Stats_Creature FOREIGN KEY (Creature_id) REFERENCES Creature (Creature_id)
)

CREATE TABLE Effect (
	Effect_id INTEGER NOT NULL,
	Effect_name VARCHAR(50) NOT NULL,
	Creature_id INTEGER NOT NULL,
	changes VARCHAR(5000),
	metrics VARCHAR(5000),
	duration INTEGER,
	CONSTRAINT PK_Effect PRIMARY KEY (Effect_id),
	CONSTRAINT FK_Effect_Creature FOREIGN KEY (Creature_id) REFERENCES Creature (Creature_id)
)

CREATE TABLE Attack (
	Attack_id INTEGER NOT NULL,
	Attack_name VARCHAR(50) NOT NULL UNIQUE,
	CharacterSheet_id INTEGER,
	TrapHazard_id INTEGER,
	primaryTarget VARCHAR(1000) NOT NULL,
	secondaryTarget VARCHAR(1000),
	accessories VARCHAR(100),
	powerSource VARCHAR(50),
	frequency INTEGER,
	hit VARCHAR(5000) NOT NULL,
	miss VARCHAR(5000),
	basic BOOLEAN NOT NULL,
	triggers VARCHAR(5000),
	effectType INTEGER,
	ability INTEGER NOT NULL,
	damageType INTEGER,
	defense INTEGER NOT NULL,
	sustain INTEGER,
	action INTEGER NOT NULL,
	useType INTEGER NOT NULL,
	CONSTRAINT PK_Attack PRIMARY KEY (Attack_id),
	CONSTRAINT FK_Attack_CharacterSheet FOREIGN KEY (CharacterSheet_id) REFERENCES CharacterSheet (CharacterSheet_id),
	CONSTRAINT FK_Attack_TrapHazard FOREIGN KEY (TrapHazard_id) REFERENCES TrapHazard (TrapHazard_id)
)

CREATE TABLE Attack_Type (
	Attack_Type_id INTEGER NOT NULL PRIMARY KEY,
	Attack_id INTEGER NOT NULL UNIQUE,
	isPersonal BOOLEAN NOT NULL,
	CONSTRAINT FK_Attack_Type_Attack FOREIGN KEY (Attack_id) REFERENCES Attack (Attack_id)
)

CREATE TABLE A_Area (
	A_Area_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	area_range INTEGER NOT NULL,
	area_size INTEGER NOT NULL,
	area_type INTEGER NOT NULL
)

CREATE TABLE A_Close (
	A_Close_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	close_size INTEGER NOT NULL,
	closeType INTEGER NOT NULL
)

CREATE TABLE A_Melee (
	A_Melee_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	melee_reach INTEGER NOT NULL,
)

CREATE TABLE A_Range (
	A_Range_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	L_range INTEGER NOT NULL,
	S_range INTEGER NOT NULL
)

CREATE TABLE Rewards (
	Rewards_id INTEGER NOT NULL PRIMARY KEY,
	CombatEncounter_id INTEGER NOT NULL,
	XP INTEGER,
	treasure VARCHAR(2000),
	CONSTRAINT FK_Rewards_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id) 
)

CREATE TABLE Tally (
	Tally_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL UNIQUE,
	CombatEncounter_id INTEGER NOT NULL UNIQUE,
	CONSTRAINT PK_Tally PRIMARY KEY (Tally_id),
	CONSTRAINT FK_Tally_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id) 
)

CREATE TABLE Tuple (
	Tuple_id INTEGER NOT NULL,
	Tuple_name VARCHAR(50) NOT NULL UNIQUE,
	Tally_id INTEGER NOT NULL,
	value1 INTEGER NOT NULL,
	value2 INTEGER NOT NULL,
	CONSTRAINT PK_Tuple PRIMARY KEY (Tuple_id),
	CONSTRAINT FK_Tuple_Tally FOREIGN KEY (Tally_id) REFERENCES Tally (Tally_id)
)