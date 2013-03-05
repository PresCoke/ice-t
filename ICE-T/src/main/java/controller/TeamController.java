package controller;

import entity.dao.*;
import entity.*;
import java.util.List;

public class TeamController {
	private static int last_number;
	
	public static Object[][] getFirstPage(boolean isNPCTeam, boolean getTraps, int columnNumber) {
		last_number = 0;
		Object[][] theTable = new Object[columnNumber][columnNumber];
		
		if (!isNPCTeam && !getTraps) {
			PlayerDao pDAO = new PlayerDaoImpl();
			List<Player> playerList = pDAO.getAllPlayers();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>playerList.size()) {
				playerList = playerList.subList(last_number, playerList.size());
				last_number = playerList.size();
			} else {
				playerList = playerList.subList(last_number, last_index);
				last_number = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_number; lindex++) {
				theTable[yindex][xindex] = playerList.get(lindex);
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			
		} else if (isNPCTeam && !getTraps){
			CharacterSheetDao mDAO = new CharacterSheetDaoImpl();
			List<CharacterSheet> monsterList = mDAO.getAllNPCCharacterSheets();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>monsterList.size()) {
				monsterList = monsterList.subList(last_number, monsterList.size());
				last_number = monsterList.size();
			} else {
				monsterList = monsterList.subList(last_number, last_index);
				last_number = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_number; lindex++) {
				theTable[yindex][xindex] = new Monster(monsterList.get(lindex));
				if (xindex<2) { //TODO: make this creature table dimension 
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			
		} else if (getTraps) {
			TrapHazardDao tDAO = new TrapHazardDaoImpl();
			List<TrapHazard> trapList = tDAO.getAllTrapHazards();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>trapList.size()) {
				trapList = trapList.subList(last_number, trapList.size());
				last_number = trapList.size();
			} else {
				trapList = trapList.subList(last_number, last_index);
				last_number = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_number; lindex++) {
				theTable[yindex][xindex] = trapList.get(lindex);
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
		}
		
		
		return theTable;
	}
	public static Object[][] getNextPage(boolean isNPCTeam, boolean getTraps, int columnNumber) {
		Object[][] theTable = new Object[columnNumber][columnNumber];
		
		if (!isNPCTeam && !getTraps) {
			PlayerDao pDAO = new PlayerDaoImpl();
			List<Player> playerList = pDAO.getAllPlayers();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>playerList.size()) {
				playerList = playerList.subList(last_number, playerList.size());
				last_index = playerList.size();
			} else {
				playerList = playerList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = playerList.get(lindex);
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number+= last_index;
			
		} else if (isNPCTeam && !getTraps){
			CharacterSheetDao mDAO = new CharacterSheetDaoImpl();
			List<CharacterSheet> monsterList = mDAO.getAllNPCCharacterSheets();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>monsterList.size()) {
				monsterList = monsterList.subList(last_number, monsterList.size());
				last_index = monsterList.size();
			} else {
				monsterList = monsterList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = new Monster(monsterList.get(lindex));
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number+= last_index;
		} else if (getTraps) {
			TrapHazardDao tDAO = new TrapHazardDaoImpl();
			List<TrapHazard> trapList = tDAO.getAllTrapHazards();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>trapList.size()) {
				trapList = trapList.subList(last_number, trapList.size());
				last_index = trapList.size();
			} else {
				trapList = trapList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = trapList.get(lindex);
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number+= last_index;
		}
		
		return theTable;
	}

	public static Object[][] getPreviousPage(boolean isNPCTeam, boolean getTraps, int columnNumber) {
		Object[][] theTable = new Object[columnNumber][columnNumber];
		
		if (!isNPCTeam && !getTraps) {
			PlayerDao pDAO = new PlayerDaoImpl();
			List<Player> playerList = pDAO.getAllPlayers();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>playerList.size()) {
				playerList = playerList.subList(last_number, playerList.size());
				last_index = playerList.size();
			} else {
				playerList = playerList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = playerList.get(lindex);
				if (xindex<columnNumber) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number -= last_index;
			if (last_number < 0) {
				last_number = 0;
			}
		} else if (isNPCTeam && !getTraps){
			CharacterSheetDao mDAO = new CharacterSheetDaoImpl();
			List<CharacterSheet> monsterList = mDAO.getAllNPCCharacterSheets();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>monsterList.size()) {
				monsterList = monsterList.subList(last_number, monsterList.size());
				last_index = monsterList.size();
			} else {
				monsterList = monsterList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = new Monster(monsterList.get(lindex));
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number -= last_index;
			if (last_number < 0) {
				last_number = 0;
			}
		} else if (getTraps) {
			TrapHazardDao tDAO = new TrapHazardDaoImpl();
			List<TrapHazard> trapList = tDAO.getAllTrapHazards();
			int last_index = last_number+columnNumber*columnNumber;
			if (last_index>trapList.size()) {
				trapList = trapList.subList(last_number, trapList.size());
				last_index = trapList.size();
			} else {
				trapList = trapList.subList(last_number, last_index);
				last_index = columnNumber*columnNumber;
			}
			for (int yindex=0, xindex=0, lindex = 0; lindex < last_index; lindex++) {
				theTable[yindex][xindex] = trapList.get(lindex);
				if (xindex<5) {
					xindex++;
				} else {
					yindex++;
					xindex = 0;
				}
			}
			last_number -= last_index;
			if (last_number < 0) {
				last_number = 0;
			}
		}
	
		return theTable;
	}
}
