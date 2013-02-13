CREATE TABLE CombatEncounter (
	CombatEncounter_id INTEGER NOT NULL,
	CombatEncounter_name VARCHAR(50) NOT NULL UNIQUE,
	notes VARCHAR(5000),
	currentCreatureId INTEGER,
	CONSTRAINT PK_CombatEncounter PRIMARY KEY (CombatEncounter_id)
)

INSERT INTO CombatEncounter VALUES (1,'CE1', 1, 'Game almost over'); 
INSERT INTO CombatEncounter VALUES (2,'CE2', 1, 'Game has just started'); 

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

INSERT INTO CharacterSheet VALUES (1, 'Nemo', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'big', 1, 3, 'french, english', 'misc', 'hi, hello', 'earth', 0, 1, 0, 2); 
INSERT INTO CharacterSheet VALUES (2, 'Ratatouille', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'spiky', 1, 3, 'japonese, chinese', 'misc', 'hey', 'fire', 5, 4, 1, 0); 
INSERT INTO CharacterSheet VALUES (3, 'JCVD', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'small', 1, 3, 'no language', 'misc', 'lol', 'wind', 8, 5, 2, 3); 

CREATE TABLE Resistance (
	Resistance_id INTEGER NOT NULL,
	CharacterSheet_id INTEGER NOT NULL,
	resistanceType INTEGER NOT NULL,
	resistanceValue INTEGER NOT NULL,
	CONSTRAINT PK_Resistance PRIMARY KEY (Resistance_id),
	CONSTRAINT FK_Resistance_CharacterSheet FOREIGN KEY (CharacterSheet_id) REFERENCES CharacterSheet (CharacterSheet_id)
)

INSERT INTO Resistance VALUES (1, 1, 0, 8); 
INSERT INTO Resistance VALUES (2, 2, 2, 14); 
INSERT INTO Resistance VALUES (3, 3, 4, 22); 
INSERT INTO Resistance VALUES (4, 2, 6, 5); 

CREATE TABLE Team (
	Team_id INTEGER NOT NULL,
	Team_name VARCHAR(50) NOT NULL UNIQUE,
	CombatEncounter_id INTEGER,
	CONSTRAINT PK_Team PRIMARY KEY (Team_id),
	CONSTRAINT FK_Team_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id)
)

INSERT INTO Team VALUES (1, 'Team1', 1); 
INSERT INTO Team VALUES (2, 'Team2', 2);

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

INSERT INTO TrapHazard VALUES (1, 'TrapHazard1', 1, 2, 20, 0, 12, 36, 11, 'no counter measure', 0, 5, 3);
INSERT INTO TrapHazard VALUES (2, 'TrapHazard2', 2, 5, 30, 0, 5, 89, 50, 'no counter measure', 1, 2, 4);

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

INSERT INTO Creature VALUES (1, 'Tim', 1, 1, 30, 5, 5, TRUE, 10); 
INSERT INTO Creature VALUES (2, 'James', 2, 1, 10, 8, 2, FALSE, 18); 
INSERT INTO Creature VALUES (3, 'Bill', 3, 2, 12, 10, 1, TRUE, 29);  
INSERT INTO Creature VALUES (4, 'Jack', 1, 2, 5, 18, 14, FALSE, 35);  

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

INSERT INTO Stats VALUES (1, 1, 5, 8, 10, 12, 19); 
INSERT INTO Stats VALUES (2, 2, 9, 14, 1, 5, 8); 
INSERT INTO Stats VALUES (3, 3, 7, 22, 10, 6, 0); 
INSERT INTO Stats VALUES (4, 4, 49, 0, 5, 1, 0); 

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

INSERT INTO Effect VALUES (1, 'Effect1', 1, 'Damage value rises for every player by 1', 'cool', 0); 
INSERT INTO Effect VALUES (2, 'Effect2', 3, 'The effect lasts 10 turns', 'not so long', 1);
INSERT INTO Effect VALUES (3, 'Effect3', 2, 'The effect lasts 10 turns', 'long', 2);
INSERT INTO Effect VALUES (4, 'Effect4', 1, 'The effect lasts 10 turns', 'not so long', 3);
INSERT INTO Effect VALUES (5, 'Effect5', 4, 'The effect lasts as long as the GM decides', 26, 'good for the GM', 2);

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

INSERT INTO Attack VALUES (1, 'Attack1', 1, null, 'James', 'Bill', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 0, 0, 10, 0, 3, 2, 0);
INSERT INTO Attack VALUES (2, 'Attack2', 2, null, 'Bill', 'Tim', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 1, 1, 0, 1, 2, 0, 2);
INSERT INTO Attack VALUES (3, 'Attack3', 2, null, 'Tim', 'Jack', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 2, 2, 1, 3, 1, 1, 3);
INSERT INTO Attack VALUES (4, 'Attack4', 3, null, 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 3, 3, 2, 2, 2, 3, 1);
INSERT INTO Attack VALUES (5, 'Attack5', 1, null, 'Bill', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 4, 4, 3, 2, 3, 1, 3);
INSERT INTO Attack VALUES (6, 'Attack6', 3, null, 'Tim', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 5, 5, 4, 2, 2, 0, 2);
INSERT INTO Attack VALUES (7, 'Attack7', 1, null, 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 6, 2, 5, 3, 0, 2, 0);
INSERT INTO Attack VALUES (8, 'Attack8', 2, null, 'Bill', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 7, 0, 8, 0, 1, 3, 1);
INSERT INTO Attack VALUES (9, 'Attack9', 3, null, 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 8, 3, 7, 3, 1, 1, 0);

CREATE TABLE Attack_Type (
	Attack_Type_id INTEGER NOT NULL PRIMARY KEY,
	Attack_id INTEGER NOT NULL UNIQUE,
	isPersonal BOOLEAN NOT NULL,
	CONSTRAINT FK_Attack_Type_Attack FOREIGN KEY (Attack_id) REFERENCES Attack (Attack_id)
)

INSERT INTO Attack_Type VALUES (1, 1, TRUE);
INSERT INTO Attack_Type VALUES (2, 2, FALSE); 
INSERT INTO Attack_Type VALUES (3, 3, TRUE);
INSERT INTO Attack_Type VALUES (4, 4, TRUE);
INSERT INTO Attack_Type VALUES (5, 5, FALSE);
INSERT INTO Attack_Type VALUES (6, 6, TRUE);
INSERT INTO Attack_Type VALUES (7, 7, FALSE);
INSERT INTO Attack_Type VALUES (8, 8, FALSE);
INSERT INTO Attack_Type VALUES (9, 9, TRUE);

CREATE TABLE A_Area (
	A_Area_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	area_range INTEGER NOT NULL,
	area_size INTEGER NOT NULL,
	area_type INTEGER NOT NULL
)

INSERT INTO A_Area VALUES (1, 1, 10, 5, 0);
INSERT INTO A_Area VALUES (2, 2, 1, 2, 1);

CREATE TABLE A_Close (
	A_Close_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	close_size INTEGER NOT NULL,
	closeType INTEGER NOT NULL
)

INSERT INTO A_Close VALUES (1, 3, 5, 0);
INSERT INTO A_Close VALUES (2, 4, 2, 1);

CREATE TABLE A_Melee (
	A_Melee_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	melee_reach INTEGER NOT NULL,
)

INSERT INTO A_Melee VALUES (1, 5, 5);
INSERT INTO A_Melee VALUES (2, 6, 3);
INSERT INTO A_Melee VALUES (3, 9, 3);


CREATE TABLE A_Range (
	A_Range_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER NOT NULL FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	L_range INTEGER NOT NULL,
	S_range INTEGER NOT NULL
)

INSERT INTO A_Range VALUES (1, 7, 10, 5);
INSERT INTO A_Range VALUES (2, 8, 15, 2);

CREATE TABLE Rewards (
	Rewards_id INTEGER NOT NULL PRIMARY KEY,
	CombatEncounter_id INTEGER NOT NULL,
	XP INTEGER,
	treasure VARCHAR(2000),
	CONSTRAINT FK_Rewards_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id) 
)

INSERT INTO Rewards VALUES (1, 2, 500, 'Gold'); 
INSERT INTO Rewards VALUES (2, 1, 1, 'Pen'); 

CREATE TABLE Tally (
	Tally_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL UNIQUE,
	CombatEncounter_id INTEGER NOT NULL UNIQUE,
	CONSTRAINT PK_Tally PRIMARY KEY (Tally_id),
	CONSTRAINT FK_Tally_CombatEncounter FOREIGN KEY (CombatEncounter_id) REFERENCES CombatEncounter (CombatEncounter_id) 
)

INSERT INTO Tally VALUES (1,'Tally1', 1); 
INSERT INTO Tally VALUES (2,'Tally2', 2); 

CREATE TABLE Tuple (
	Tuple_id INTEGER NOT NULL,
	Tuple_name VARCHAR(50) NOT NULL UNIQUE,
	Tally_id INTEGER NOT NULL,
	value1 INTEGER NOT NULL,
	value2 INTEGER NOT NULL,
	CONSTRAINT PK_Tuple PRIMARY KEY (Tuple_id),
	CONSTRAINT FK_Tuple_Tally FOREIGN KEY (Tally_id) REFERENCES Tally (Tally_id)
)

INSERT INTO Tuple VALUES (1,'Tuple1', 1, 5, 8); 
INSERT INTO Tuple VALUES (2,'Tuple2', 1, 9, 14); 
INSERT INTO Tuple VALUES (3,'Tuple3', 2, 35, 2); 
INSERT INTO Tuple VALUES (4,'Tuple4', 2, 49, 4); 