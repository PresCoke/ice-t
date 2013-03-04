package controller;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import entity.*;

public class Combat {
	private static final Logger logger = Logger.getLogger(Combat.class);
	private CombatEncounter theEncounter;
	
	public Combat() {
		
	}

	public CombatEncounter getCombatEncounter() {
		return theEncounter;
	}

	public void saveOpenCombatEncounter() {
		int CE_id = theEncounter.save();
		App_Root.changeLastOpenEncounter(CE_id);
	}

	public void updateOpenCombatEncounter() {
		int CE_id = theEncounter.edit();
		App_Root.changeLastOpenEncounter(CE_id);
	}

	public int getID() {
		return theEncounter.getId();
	}
	public void setCombatEncounter(CombatEncounter openEncounter) {
		theEncounter = openEncounter;
		theEncounter.organizeCreaturesIntoRespectiveLists();
	}
	/**
	 * Allow the game master to roll a D20 automatically
	 * @return
	 */
	public int autoRoll(){
		Random random = new Random();
		int d = random.nextInt(21);
		return d;
	}

	/**
	 * Organize the creatures in the way they were the last time 
	 * the CE was played
	 * @return list of creatures sorted
	 */
	public javax.swing.DefaultListModel organizeCreaturesByInitiative(){	
		//Retrieving all the creatures in the combat encounter and sort them out
		//Sort the creatures to get them the way they were last time
		
		/* TODO:
    	 * Sort DefaultListModel while moving traps to the back.
    	 * Sort CreaturesInCe in the combat encounter to maintain synchronicity with gui
    	 * 
    	 * for j=1 to A.length
    	 * 	key=A[j]
    	 * 	i=j-1
    	 * 	while (i > 0 and A[i] > key
    	 * 		A[i+1] = A[i]
    	 * 		i = i - 1;
    	 * 	A[i+1] = key
    	 */
		List<Object> creatures = theEncounter.getCreaturesInCe();
		Object key;
		for (int j = 1, i = 0; j < creatures.size(); j++) {
			key = creatures.get(j);
			if (key instanceof Player) {
				i = j - 1;
				while (i > 0) {
					if (creatures.get(i) instanceof Player) {
						if ( ((Player) creatures.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					} else if (creatures.get(i) instanceof Monster) {
						if ( ((Monster) creatures.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					}
					creatures.set(i+1, creatures.get(i));
					i = i - 1;
				}
				creatures.set(i+1, key);
			} else if (key instanceof Monster) {
				i = j - 1;
				while (i > 0) {
					if (creatures.get(i) instanceof Player) {
						if ( ((Player) creatures.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					} else if (creatures.get(i) instanceof Monster) {
						if ( ((Monster) creatures.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					}
					creatures.set(i+1, creatures.get(i));
					i = i - 1;
				}
				creatures.set(i+1, key);
			}
		}
		javax.swing.DefaultListModel sorted_list = new javax.swing.DefaultListModel();
		for (int index = 0; index < creatures.size(); index++) {
			sorted_list.addElement(creatures.get(index));
		}
		
		theEncounter.setCreaturesInCe(creatures);
		
		if (theEncounter.getCurrentCreatureId() == -1) {
	    	logger.info("The currentCreatureId is null so this is a new game.");
	    	theEncounter.setCurrentCreatureId(0);
	    	this.setCurrentCreature(theEncounter.getCurrentCreatureId());
		} else {
			logger.info("The currentCreatureId is non-null so set the current creature to the saved one.");
			this.setCurrentCreature(theEncounter.getCurrentCreatureId());
	    }
		
		
		return sorted_list;
	}

	/**
	 * Take the first creature in the list of creatures playing and put it at the end of the list
	 * If the creature is a monster, all the monsters are put at the end of the list
	 */
	public int finishTurn() {
		return theEncounter.nextCreature();
//		Object current = creaturesInCe.get(0);
//		if (current instanceof Player){
//			creaturesInCe.remove(0);
//			creaturesInCe.add(current);	
//		} else {
//			Object currentMonster = creaturesInCe.get(0);
//			while(currentMonster instanceof Monster){
//				creaturesInCe.remove(0);
//				creaturesInCe.add(currentMonster);
//				currentMonster = creaturesInCe.get(0);
//			}
//		}
	}
	
	/**
	 * Generate a random encounter, that is to say a random team of monsters
	 * @return list of creatures and traps
	 */
	/*public List<Object> generateRandomEncounter(){
		
		//Retrieving all the creatures' level in the combat encounter
    	logger.info("Retrieving all the creatures in the CE");
		List<Integer> levels = new ArrayList<Integer>();
		for (Player c : playersInCe){
			levels.add(c.getCharacterSheet().getId());
		}
		
		//Choosing Encounter level
    	logger.info("Choosing a level for the random encounter");
		int creaturesNumber = levels.size();
		int sum = 0;
		for (int level : levels){
			sum += level;
		}
		Random random = new Random();
		int r = random.nextInt(3) - random.nextInt(3);
		int levelEncounter = sum/creaturesNumber + r;
		if (levelEncounter < 1){
			levelEncounter = 1;
		}
		XPbudget = levelEncounter*250;

		//Getting all NPC creatures and traps in database that would suit the XPbudget
		List<Object> npcs = new ArrayList<Object>();
    	logger.info("Getting all NPC creatures that would suit the XP budget");
		MonsterDao mDao = new MonsterDaoImpl();
		List<Monster> monsters = new ArrayList<Monster>();
		List<Monster> allMonsters = mDao.getAllMonsters();
		for (Monster m : allMonsters){
			if(m.getCharacterSheet().getXP()<=XPbudget){
				monsters.add(m);
			}
		}
    	logger.info("Getting all traps that would suit the XP budget");
		TrapHazardDao thDao = new TrapHazardDaoImpl();
		List<TrapHazard> trapHazards = new ArrayList<TrapHazard>();
		List<TrapHazard> trapHazardsAll = thDao.getAllTrapHazards();
		for (TrapHazard th : trapHazardsAll){
			if(th.getXp()<=XPbudget){
				trapHazards.add(th);
			}
		}
		
		//Creating the list
    	logger.info("Adding the NPC creatures and the traps in the list");
    	for (Monster c : monsters){
    		npcs.add(c);
    	}
    	for (TrapHazard th : trapHazards){
    		npcs.add(th);
    	}				
		return npcs;
	}*/
	
	private void setCurrentCreature(int currentCreatureId) {
		theEncounter.setCurrentCreatureId(currentCreatureId);
	}

	public void removeTheseCreatures(List<Object> removed_creatures) {
		 for (int index = 0; index<removed_creatures.size(); index++) {
			 Object value = removed_creatures.get(index);
			 if (value instanceof Player) {
				 theEncounter.removePlayer((Player) value);
			 } else if (value instanceof Monster) {
				 theEncounter.removeMonster((Monster) value);
			 } else if (value instanceof TrapHazard) {
				 theEncounter.removeTrapHazard((TrapHazard) value);
			 }
		 }
	}
	
}
