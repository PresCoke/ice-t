CREATE TABLE CombatEncounter (
	CombatEncounter_id INTEGER NOT NULL,
	CombatEncounter_name VARCHAR(50) NOT NULL,
	notes VARCHAR(1000),
	CONSTRAINT PK_CombatEncounter PRIMARY KEY (CombatEncounter_id, CombatEncounter_name)
)

INSERT INTO CombatEncounter VALUES (1,'CE1', 'Game almost over'); 
INSERT INTO CombatEncounter VALUES (2,'CE2', 'Game has just started'); 

CREATE TABLE CharacterSheet (
	CharacterSheet_id INTEGER NOT NULL,
	CharacterSheet_name VARCHAR(50) NOT NULL, 
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
	AC INTEGER,
	RE INTEGER,
	FORT INTEGER,
	WILL INTEGER,
	maxHP INTEGER,
	bloodied INTEGER,
	surgesValue INTEGER,
	surgesPerDay INTEGER,
	STR INTEGER,
	CON INTEGER,
	INTE INTEGER,
	DEX INTEGER,
	WIS INTEGER,
	CHARA INTEGER,
	levelCS INTEGER,
	XP INTEGER,
	raceFeatures VARCHAR(1000),
	speed INTEGER,
	resistanceValue INTEGER,
	initiative INTEGER,
	languages VARCHAR(1000),
	misc VARCHAR(1000),
	keywords VARCHAR(1000),
	powerSource VARCHAR(1000),
	roleCS INTEGER,
	sizeCS INTEGER,
	monsterOriginCS INTEGER,
	monsterTypeCS INTEGER,
	CONSTRAINT PK_CharacterSheet PRIMARY KEY (CharacterSheet_id, CharacterSheet_name)
)

INSERT INTO CharacterSheet VALUES (1, 'Nemo', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'big', 1, 2, 3, 'french, english', 'misc', 'hi, hello', 'earth', 0, 1, 0, 2); 
INSERT INTO CharacterSheet VALUES (2, 'Ratatouille', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'spiky', 1, 2, 3, 'japonese, chinese', 'misc', 'hey', 'fire', 5, 4, 1, 0); 
INSERT INTO CharacterSheet VALUES (3, 'JCVD', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'small', 1, 2, 3, 'no language', 'misc', 'lol', 'wind', 8, 5, 2, 3); 

CREATE TABLE Resistance (
	Resistance_id INTEGER NOT NULL,
	Resistance_name VARCHAR(50),
	CharacterSheet_id INTEGER,
	CharacterSheet_name VARCHAR(50),
	resistanceType INTEGER,
	resistanceValue INTEGER,
	CONSTRAINT PK_Resistance PRIMARY KEY (Resistance_id, Resistance_name),
	CONSTRAINT FK_Resistance_CharacterSheet FOREIGN KEY (CharacterSheet_id, CharacterSheet_name) REFERENCES CharacterSheet (CharacterSheet_id, CharacterSheet_name)
)

INSERT INTO Resistance VALUES (1, 'Resistance1', 1, 'Nemo', 0, 8); 
INSERT INTO Resistance VALUES (2, 'Resistance2', 2, 'Ratatouille', 2, 14); 
INSERT INTO Resistance VALUES (3, 'Resistance3', 3, 'JCVD', 4, 22); 
INSERT INTO Resistance VALUES (4, 'Resistance4', 2, 'Ratatouille', 6, 5); 

CREATE TABLE Team (
	Team_id INTEGER NOT NULL,
	Team_name VARCHAR(50) NOT NULL,
	CombatEncounter_id INTEGER,
	CombatEncounter_name VARCHAR(50),
	CONSTRAINT PK_Team PRIMARY KEY (Team_id, Team_name),
	CONSTRAINT FK_Team_CombatEncounter FOREIGN KEY (CombatEncounter_id, CombatEncounter_name) REFERENCES CombatEncounter (CombatEncounter_id, CombatEncounter_name)
)

INSERT INTO Team VALUES (1, 'Team1', 1, 'CE1'); 
INSERT INTO Team VALUES (2, 'Team2', 2, 'CE2');

CREATE TABLE TrapHazard (
	TrapHazard_id INTEGER NOT NULL,
	TrapHazard_name VARCHAR(50) NOT NULL,
	CombatEncounter_id INTEGER,
	CombatEncounter_name VARCHAR(50),
	avoidance INTEGER,
	levelTH INTEGER,
	skill VARCHAR(500),
	triggers VARCHAR(500),
	valueTH INTEGER,
	xp INTEGER,
	difficultyLevel INTEGER,
	counterMeasureDescription VARCHAR(500),
	typeTH INTEGER,
	roleTH INTEGER,
	counterMeasureSkillTH INTEGER,
	CONSTRAINT PK_TrapHazard PRIMARY KEY (TrapHazard_id, TrapHazard_name),
	CONSTRAINT FK_TrapHazard_CombatEncounter FOREIGN KEY (CombatEncounter_id, CombatEncounter_name) REFERENCES CombatEncounter (CombatEncounter_id, CombatEncounter_name)
)

INSERT INTO TrapHazard VALUES (1, 'TrapHazard1', 1, 'CE1', 2, 20, 'Damage value rises by 1 every turn', 'player', 12, 36, 11, 'no counter measure', 0, 5, 3);
INSERT INTO TrapHazard VALUES (2, 'TrapHazard2', 2, 'CE2', 5, 30, 'The trap only lasts 2 hours', 'player', 5, 89, 50, 'no counter measure', 1, 2, 4);

CREATE TABLE Creature (
	Creature_id INTEGER NOT NULL,
	player_name VARCHAR(50) NOT NULL,
	CharacterSheet_id INTEGER,
	CharacterSheet_name VARCHAR(50),
	Team_id INTEGER,
	Team_name VARCHAR(50),
	currentHP INTEGER,
	currentHealSurges INTEGER,
	currentLevel INTEGER,
	secondWind BOOLEAN DEFAULT TRUE,
	tempHP INTEGER,
	CONSTRAINT PK_Creature PRIMARY KEY (Creature_id, player_name),
	CONSTRAINT FK_Creature_CharacterSheet FOREIGN KEY (CharacterSheet_id, CharacterSheet_name) REFERENCES CharacterSheet (CharacterSheet_id, CharacterSheet_name),
	CONSTRAINT FK_Creature_Team FOREIGN KEY (Team_id, Team_name) REFERENCES Team (Team_id, Team_name)
)

INSERT INTO Creature VALUES (1, 'Tim', 1, 'Nemo', 1, 'Team1', 30, 5, 5, TRUE, 10); 
INSERT INTO Creature VALUES (2, 'James', 2, 'Ratatouille', 1, 'Team1', 10, 8, 2, FALSE, 18); 
INSERT INTO Creature VALUES (3, 'Bill', 3, 'JCVD', 2, 'Team2', 12, 10, 1, TRUE, 29);  
INSERT INTO Creature VALUES (4, 'Jack', 1, 'Nemo', 2, 'Team2', 5, 18, 14, FALSE, 35);  

CREATE TABLE Stats (
	Stats_id INTEGER NOT NULL PRIMARY KEY,
	Creature_id INTEGER,
	player_name VARCHAR(50),
	kills INTEGER,
	deaths INTEGER,
	hits INTEGER,
	misses INTEGER,
	assists INTEGER,
	CONSTRAINT FK_Stats_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Stats VALUES (1, 1, 'Tim', 5, 8, 10, 12, 19); 
INSERT INTO Stats VALUES (2, 2, 'James', 9, 14, 1, 5, 8); 
INSERT INTO Stats VALUES (3, 3, 'Bill', 7, 22, 10, 6, 0); 
INSERT INTO Stats VALUES (4, 4, 'Jack', 49, 0, 5, 1, 0); 

CREATE TABLE Effect (
	Effect_id INTEGER NOT NULL,
	Effect_name VARCHAR(50) NOT NULL,
	Creature_id INTEGER,
	player_name VARCHAR(50),
	changes VARCHAR(500),
	damage INTEGER,
	metrics VARCHAR(500),
	duration INTEGER,
	CONSTRAINT PK_Effect PRIMARY KEY (Effect_id, Effect_name),
	CONSTRAINT FK_Effect_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Effect VALUES (1, 'Effect1', 1, 'Tim', 'Damage value rises for every player by 1', 10, 'cool', 0); 
INSERT INTO Effect VALUES (2, 'Effect2', 3, 'Bill', 'The effect lasts 10 turns', 15, 'not so long', 1);
INSERT INTO Effect VALUES (3, 'Effect3', 2, 'James', 'The effect lasts 10 turns', 15, 'long', 2);
INSERT INTO Effect VALUES (4, 'Effect4', 1, 'Tim', 'The effect lasts 10 turns', 15, 'not so long', 3);
INSERT INTO Effect VALUES (5, 'Effect5', 4, 'Jack', 'The effect lasts as long as the GM decides', 26, 'good for the GM', 2);

CREATE TABLE Attack (
	Attack_id INTEGER NOT NULL,
	Attack_name VARCHAR(50) NOT NULL,
	Creature_id INTEGER,
	player_name VARCHAR(50),
	primaryTarget VARCHAR(50),
	secondaryTarget VARCHAR(50),
	accessories VARCHAR(1000),
	powerSource VARCHAR(50),
	frequency INTEGER,
	hit VARCHAR(50),
	miss VARCHAR(50),
	basic BOOLEAN DEFAULT TRUE,
	triggers VARCHAR(1000),
	effectType INTEGER,
	ability INTEGER,
	damageType INTEGER,
	defense INTEGER,
	sustain INTEGER,
	action INTEGER,
	useType INTEGER,
	CONSTRAINT PK_Attack PRIMARY KEY (Attack_id, Attack_name),
	CONSTRAINT FK_Attack_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Attack VALUES (1, 'Attack1', 1, 'Tim', 'James', 'Bill', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 0, 0, 10, 0, 3, 2, 0);
INSERT INTO Attack VALUES (2, 'Attack2', 2, 'James', 'Bill', 'Tim', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 1, 1, 0, 1, 2, 0, 2);
INSERT INTO Attack VALUES (3, 'Attack3', 2, 'James', 'Tim', 'Jack', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 2, 2, 1, 3, 1, 1, 3);
INSERT INTO Attack VALUES (4, 'Attack4', 3, 'Bill', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 3, 3, 2, 2, 2, 3, 1);
INSERT INTO Attack VALUES (5, 'Attack5', 1, 'Tim', 'Bill', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 4, 4, 3, 2, 3, 1, 3);
INSERT INTO Attack VALUES (6, 'Attack6', 3, 'Bill', 'Tim', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn', 5, 5, 4, 2, 2, 0, 2);
INSERT INTO Attack VALUES (7, 'Attack7', 1, 'Tim', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 6, 2, 5, 3, 0, 2, 0);
INSERT INTO Attack VALUES (8, 'Attack8', 2, 'James', 'Bill', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 7, 0, 8, 0, 1, 3, 1);
INSERT INTO Attack VALUES (9, 'Attack9', 3, 'Bill', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn', 8, 3, 7, 3, 1, 1, 0);

CREATE TABLE Attack_Type (
	Attack_Type_id INTEGER NOT NULL PRIMARY KEY,
	Attack_id INTEGER,
	Attack_name VARCHAR(50),
	isPersonal BOOLEAN DEFAULT TRUE,
	CONSTRAINT FK_Attack_Type_Attack FOREIGN KEY (Attack_id, Attack_name) REFERENCES Attack (Attack_id, Attack_name)
)

INSERT INTO Attack_Type VALUES (1, 1, 'Attack1', TRUE);
INSERT INTO Attack_Type VALUES (2, 2, 'Attack2', FALSE); 
INSERT INTO Attack_Type VALUES (3, 3, 'Attack3', TRUE);
INSERT INTO Attack_Type VALUES (4, 4, 'Attack4', TRUE);
INSERT INTO Attack_Type VALUES (5, 5, 'Attack5', FALSE);
INSERT INTO Attack_Type VALUES (6, 6, 'Attack6', TRUE);
INSERT INTO Attack_Type VALUES (7, 7, 'Attack7', FALSE);
INSERT INTO Attack_Type VALUES (8, 8, 'Attack8', FALSE);
INSERT INTO Attack_Type VALUES (9, 9, 'Attack9', TRUE);

CREATE TABLE A_Area (
	A_Area_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	area_range INTEGER,
	area_size INTEGER,
	area_type INTEGER
)

INSERT INTO A_Area VALUES (1, 1, 10, 5, 0);
INSERT INTO A_Area VALUES (2, 2, 1, 2, 1);

CREATE TABLE A_Close (
	A_Close_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	close_size INTEGER,
	closeType INTEGER
)

INSERT INTO A_Close VALUES (1, 3, 5, 0);
INSERT INTO A_Close VALUES (2, 4, 2, 1);

CREATE TABLE A_Melee (
	A_Melee_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	melee_reach INTEGER
)

INSERT INTO A_Melee VALUES (1, 5, 5);
INSERT INTO A_Melee VALUES (2, 6, 3);
INSERT INTO A_Melee VALUES (3, 9, 3);


CREATE TABLE A_Range (
	A_Range_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	L_range INTEGER,
	S_range INTEGER
)

INSERT INTO A_Range VALUES (1, 7, 10, 5);
INSERT INTO A_Range VALUES (2, 8, 15, 2);

CREATE TABLE Rewards (
	Rewards_id INTEGER NOT NULL PRIMARY KEY,
	CombatEncounter_id INTEGER,
	CombatEncounter_name VARCHAR(50),
	XP INTEGER,
	treasure VARCHAR(200),
	CONSTRAINT FK_Rewards_CombatEncounter FOREIGN KEY (CombatEncounter_id, CombatEncounter_name) REFERENCES CombatEncounter (CombatEncounter_id, CombatEncounter_name) 
)

INSERT INTO Rewards VALUES (1, 2, 'CE2', 500, 'Gold'); 
INSERT INTO Rewards VALUES (2, 1, 'CE1', 1, 'Pen'); 

CREATE TABLE Tally (
	Tally_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	CombatEncounter_id INTEGER UNIQUE,
	CombatEncounter_name VARCHAR(50) UNIQUE,
	CONSTRAINT PK_Tally PRIMARY KEY (Tally_id, name),
	CONSTRAINT FK_Tally_CombatEncounter FOREIGN KEY (CombatEncounter_id, CombatEncounter_name) REFERENCES CombatEncounter (CombatEncounter_id, CombatEncounter_name) 
)

INSERT INTO Tally VALUES (1,'Tally1', 1, 'CE1'); 
INSERT INTO Tally VALUES (2,'Tally2', 2, 'CE2'); 

CREATE TABLE Tuple (
	Tuple_id INTEGER NOT NULL,
	Tuple_name VARCHAR(50) NOT NULL,
	Tally_id INTEGER,
	name VARCHAR(50),
	value1 INTEGER,
	value2 INTEGER,
	CONSTRAINT PK_Tuple PRIMARY KEY (Tuple_id, Tuple_name),
	CONSTRAINT FK_Tuple_Tally FOREIGN KEY (Tally_id, name) REFERENCES Tally (Tally_id, name)
)

INSERT INTO Tuple VALUES (1,'Tuple1', 1, 'Tally1', 5, 8); 
INSERT INTO Tuple VALUES (2,'Tuple2', 1, 'Tally1', 9, 14); 
INSERT INTO Tuple VALUES (3,'Tuple3', 2, 'Tally2', 35, 2); 
INSERT INTO Tuple VALUES (4,'Tuple4', 2, 'Tally2', 49, 4); 