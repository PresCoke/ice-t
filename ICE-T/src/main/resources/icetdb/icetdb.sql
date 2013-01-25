CREATE TABLE CombatEncounter (
	CombatEncounter_id INTEGER NOT NULL,
	CEname VARCHAR(50) NOT NULL,
	notes VARCHAR(1000),
	CONSTRAINT PK_CombatEncounter PRIMARY KEY (CombatEncounter_id, CEname),
)

INSERT INTO CombatEncounter VALUES (1,'CE1', 'Game almost over'); 
INSERT INTO CombatEncounter VALUES (2,'CE2', 'Game has just started'); 

CREATE TABLE EntityM (
	EntityM_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	CombatEncounter_id INTEGER,
	CEname VARCHAR(50),
	CONSTRAINT PK_EntityM PRIMARY KEY (EntityM_id, name)
	CONSTRAINT FK_CombatEncounter FOREIGN KEY (CombatEncounter_id, CEname) REFERENCES CombatEncounter (CombatEncounter_id, CEname)
)

INSERT INTO EntityM VALUES (1, 'Effect1', 1, 'CE1'); 
INSERT INTO EntityM VALUES (2, 'Effect2', 2, 'CE2'); 
INSERT INTO EntityM VALUES (3, 'Team1', 2, 'CE2'); 
INSERT INTO EntityM VALUES (4, 'Team2', 1, 'CE1');
INSERT INTO EntityM VALUES (5, 'TrapHazard1', 1, 'CE1'); 
INSERT INTO EntityM VALUES (6, 'TrapHazard2', 1, 'CE1'); 
INSERT INTO EntityM VALUES (7, 'CharacterSheet1', 2, 'CE2'); 
INSERT INTO EntityM VALUES (8, 'CharacterSheet2', 1, 'CE1');
INSERT INTO EntityM VALUES (9, 'CharacterSheet3', 1, 'CE1'); 

CREATE TABLE CharacterSheet (
	CharacterSheet_id INTEGER NOT NULL PRIMARY KEY,
	EntityM_id INTEGER UNIQUE,
	name VARCHAR(50) UNIQUE,
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
	CONSTRAINT FK_EntityM FOREIGN KEY (EntityM_id, name) REFERENCES EntityM (EntityM_id, name)
)

INSERT INTO CharacterSheet VALUES (1, 7, 'CharacterSheet1', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'big', 1, 2, 3, 'french, english', 'misc', 'hi, hello', 'earth'); 
INSERT INTO CharacterSheet VALUES (2, 8, 'CharacterSheet2', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'spiky', 1, 2, 3, 'japonese, chinese', 'misc', 'hey', 'fire'); 
INSERT INTO CharacterSheet VALUES (3, 9, 'CharacterSheet3', 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 'small', 1, 2, 3, 'no language', 'misc', 'lol', 'wind'); 

CREATE TABLE Effect (
	Effect_id INTEGER NOT NULL PRIMARY KEY,
	EntityM_id INTEGER,
	name VARCHAR(50),
	Creature_id INTEGER,
	player_name VARCHAR(50),
	changes VARCHAR(500),
	damage INTEGER,
	metrics VARCHAR(500),
	CONSTRAINT FK_EntityM FOREIGN KEY (EntityM_id, name) REFERENCES EntityM (EntityM_id, name),
	CONSTRAINT FK_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Effect VALUES (1, 1, 'Effect1', 1, 'Tim', 'Damage value rises for every player by 1', 10, 'cool'); 
INSERT INTO Effect VALUES (2, 2, 'Effect2', 3, 'Bill', 'The effect lasts 10 turns', 15, 'not so long');
INSERT INTO Effect VALUES (3, 2, 'Effect2', 2, 'James', 'The effect lasts 10 turns', 15, 'not so long');

CREATE TABLE Team (
	Team_id INTEGER NOT NULL PRIMARY KEY,
	EntityM_id INTEGER UNIQUE,
	name VARCHAR(50) UNIQUE,
	CONSTRAINT FK_EntityM FOREIGN KEY (EntityM_id, name) REFERENCES EntityM (EntityM_id, name)
)

INSERT INTO Team VALUES (1, 3, 'Team1'); 
INSERT INTO Team VALUES (2, 4, 'Team2');

CREATE TABLE TrapHazard (
	TrapHazard_id INTEGER NOT NULL PRIMARY KEY,
	EntityM_id INTEGER UNIQUE,
	name VARCHAR(50) UNIQUE,
	avoidance INTEGER,
	levelTH INTEGER,
	skill VARCHAR(500),
	triggers VARCHAR(500),
	valueTH INTEGER,
	xp INTEGER,
	difficultyLevel INTEGER,
	counterMeasureDescription VARCHAR(500),
	CONSTRAINT FK_EntityM FOREIGN KEY (EntityM_id, name) REFERENCES EntityM (EntityM_id, name)
)

INSERT INTO TrapHazard VALUES (1, 5, 'TrapHazard1', 12, 20 'Damage value rises by 1 every turn', 'player', 12, 36, 11, 'no counter measure'); 
INSERT INTO TrapHazard VALUES (2, 6, 'TrapHazard2', 5, 30, 'The trap only lasts 2 hours', 'player', 5, 89, 50, 'no counter measure');

CREATE TABLE Creature (
	Creature_id INTEGER NOT NULL,
	player_name VARCHAR(50) NOT NULL,
	CharacterSheet_id INTEGER FOREIGN KEY REFERENCES CharacterSheet (CharacterSheet_id),
	Team_id INTEGER FOREIGN KEY REFERENCES Team (Team_id),
	currentHP INTEGER,
	currentHealSurges INTEGER,
	currentLevel INTEGER,
	secondWind BOOLEAN,
	tempHP INTEGER,
	CONSTRAINT PK_Creature PRIMARY KEY (Creature_id, player_name)
)

INSERT INTO Creature VALUES (1, 'Tim', 1, 1, 30, 5, 5, TRUE, 10); 
INSERT INTO Creature VALUES (2, 'James', 2, 1, 10, 8, 2, FALSE, 18); 
INSERT INTO Creature VALUES (3, 'Bill', 3, 2, 12, 10, 1, TRUE, 29);  
INSERT INTO Creature VALUES (4, 'Tim', 1, 2, 30, 5, 5, TRUE, 10);

CREATE TABLE Attack (
	Attack_id INTEGER NOT NULL PRIMARY KEY,
	Creature_id INTEGER,
	player_name VARCHAR(50)
	primaryTarget VARCHAR(50),
	secondaryTarget VARCHAR(50),
	accessories VARCHAR(1000),
	powerSource VARCHAR(50),
	frequency INTEGER,
	hit VARCHAR(50),
	miss VARCHAR(50),
	basic BOOLEAN DEFAULT TRUE,
	triggers VARCHAR(50),
	CONSTRAINT FK_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Attack VALUES (1, 1, 'Tim', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (2, 2, 'James', 'Bill', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (3, 2, 'James', 'Tim', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (4, 3, 'Bill', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (5, 1, 'Tim', 'Bill', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (6, 3, 'Bill', 'Tim', 'James', 'MachineGun', 'martial', 1000, '2D16 & 2D6', '1D32 & 1D6', FALSE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (7, 1, 'Tim', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (8, 2, 'James', 'Bill', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');
INSERT INTO Attack VALUES (9, 3, 'Bill', 'James', 'Tim', 'knife & gun', 'arcane', 2, '2D16', '1D32', TRUE, 'the player cannot use that attack on his/her first turn');


CREATE TABLE Attack_Type (
	Attack_Type_id INTEGER NOT NULL PRIMARY KEY,
	Attack_id INTEGER FOREIGN KEY REFERENCES Attack (Attack_id) UNIQUE,
	isPersonal BOOLEAN DEFAULT TRUE
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
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	area_range INTEGER,
	area_size INTEGER
)

INSERT INTO A_Area VALUES (1, 1, 10, 5);
INSERT INTO A_Area VALUES (2, 2, 1, 2);

CREATE TABLE A_Close (
	A_Close_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	close_size INTEGER
)

INSERT INTO A_Close VALUES (1, 3, 5);
INSERT INTO A_Close VALUES (2, 4, 2);

CREATE TABLE A_Melee (
	A_Melee_id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	melee_reach INTEGER
)

INSERT INTO A_Melee VALUES (1, 5, 5);
INSERT INTO A_Melee VALUES (2, 6, 3);
INSERT INTO A_Melee VALUES (3, 9, 3);


CREATE TABLE A_Range (
	id INTEGER NOT NULL PRIMARY KEY,
	Attack_Type_id INTEGER FOREIGN KEY REFERENCES Attack_Type (Attack_Type_id),
	L_range INTEGER,
	S_range INTEGER
)

INSERT INTO A_Range VALUES (1, 7, 10, 5);
INSERT INTO A_Range VALUES (2, 8, 15, 2);

CREATE TABLE Rewards (
	Rewards_id INTEGER NOT NULL PRIMARY KEY,
	CombatEncounter_id INTEGER,
	CEname VARCHAR(50),
	XP INTEGER,
	treasure VARCHAR(200),
	CONSTRAINT FK_CombatEncounter FOREIGN KEY (CombatEncounter_id, CEname) REFERENCES CombatEncounter (CombatEncounter_id, CEname) 
)

INSERT INTO Rewards VALUES (1, 2, 'CE1', 500, 'Gold'); 
INSERT INTO Rewards VALUES (2, 1, 'CE2', 1, 'Pen'); 

CREATE TABLE Tally (
	Tally_id INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	CombatEncounter_id INTEGER UNIQUE,
	CEname VARCHAR(50) UNIQUE,
	CONSTRAINT PK_Tally PRIMARY KEY (Tally_id, name),
	CONSTRAINT FK_CombatEncounter FOREIGN KEY (CombatEncounter_id, CEname) REFERENCES CombatEncounter (CombatEncounter_id, CEname) 
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
	CONSTRAINT FK_Tally FOREIGN KEY (Tally_id, name) REFERENCES Tally (Tally_id, name)
)

INSERT INTO Tuple VALUES (1,'Tuple1', 1, 'Tally1', 5, 8); 
INSERT INTO Tuple VALUES (2,'Tuple2', 1, 'Tally1', 9, 14); 
INSERT INTO Tuple VALUES (3,'Tuple3', 2, 'Tally2', 35, 2); 
INSERT INTO Tuple VALUES (4,'Tuple4', 2, 'Tally2', 49, 4); 

CREATE TABLE Stats (
	Stats_id INTEGER NOT NULL PRIMARY KEY,
	CombatEncounter_id INTEGER,
	CEname VARCHAR(50),
	Creature_id INTEGER,
	player_name VARCHAR(50),
	kills INTEGER,
	deaths INTEGER,
	hits INTEGER,
	misses INTEGER,
	assists INTEGER,
	CONSTRAINT FK_CombatEncounter FOREIGN KEY (CombatEncounter_id, CEname) REFERENCES CombatEncounter (CombatEncounter_id, CEname),
	CONSTRAINT FK_Creature FOREIGN KEY (Creature_id, player_name) REFERENCES Creature (Creature_id, player_name)
)

INSERT INTO Stats VALUES (1, 1, 'CE1', 1, 'Tim', 5, 8, 10, 12, 19); 
INSERT INTO Stats VALUES (2, 1, 'CE1', 2, 'James', 9, 14, 1, 5, 8); 
INSERT INTO Stats VALUES (3, 2, 'CE2', 3, 'Bill', 7, 22, 10, 6, 0); 
INSERT INTO Stats VALUES (4, 2, 'CE2', 4, 'Tim', 49, 0, 5, 1, 0); 
