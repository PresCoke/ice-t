package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.DefaultListModel;

import org.apache.log4j.Logger;

import bean.combat.CreatureBeanShallow;

import entity.*;

public class Combat {
	private static final Logger logger = Logger.getLogger(Combat.class);
	private CombatEncounter theEncounter;
	private List<Object> creaturesInTheCE;
	private int currentCreature;
	
	public Combat() {
		creaturesInTheCE = new ArrayList<Object>(0);
	}

	public CombatEncounter getCombatEncounter() {
		return theEncounter;
	}

	public void saveOpenCombatEncounter(String name) {
		theEncounter.setName(name);
		theEncounter.setCreaturesInCe(creaturesInTheCE);
		int CE_id = theEncounter.save();
		theEncounter.setId(CE_id);
		App_Root.changeLastOpenEncounter(CE_id);
	}

	public void updateOpenCombatEncounter() {
		theEncounter.setCreaturesInCe(creaturesInTheCE);
		int CE_id = theEncounter.edit();
		App_Root.changeLastOpenEncounter(CE_id);
	}

	public int getID() {
		return theEncounter.getId();
	}
	public void setCombatEncounter(CombatEncounter openEncounter) {
		theEncounter = openEncounter;
		theEncounter.organizeCreaturesIntoRespectiveLists();
		creaturesInTheCE = theEncounter.getCreaturesInCe();
		setCurrentCreature(theEncounter.getCurrentCreatureId());
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
	 * @param creature_model 
	 * @return list of creatures sorted
	 */
	public javax.swing.DefaultListModel organizeCreaturesByInitiative(DefaultListModel creature_model){	
		//Retrieving all the creatures in the combat encounter and sort them out
		//Sort the creatures to get them the way they were last time
		
		/* TODO:
    	 * Sort DefaultListModel while moving traps to the back.
    	 * Sort CreaturesInCe in the combat encounter to maintain synchronicity with gui
    	 * 
    	 * for j=1 to A.length
    	 * 	key=A[j]
    	 * 	i=j-1
    	 * 	while (i > 0 and A[i] > key)
    	 * 		A[i+1] = A[i]
    	 * 		i = i - 1;
    	 * 	A[i+1] = key
    	 */
		creaturesInTheCE = theEncounter.getCreaturesInCe();
		Object key;
		for (int j = 1, i = 0; j < creaturesInTheCE.size(); j++) {
			key = creaturesInTheCE.get(j);
			if (key instanceof Player) {
				i = j - 1;
				while (i >= 0) {
					if (creaturesInTheCE.get(i) instanceof Player) {
						if ( ((Player) creaturesInTheCE.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					} else if (creaturesInTheCE.get(i) instanceof Monster) {
						if ( ((Monster) creaturesInTheCE.get(i)).getInitiative() > ((Player) key).getInitiative() ) {
							break;
						}
					}
					creaturesInTheCE.set(i+1, creaturesInTheCE.get(i));
					i = i - 1;
				}
				creaturesInTheCE.set(i+1, key);
			} else if (key instanceof Monster) {
				i = j - 1;
				while (i >= 0) {
					if (creaturesInTheCE.get(i) instanceof Player) {
						if ( ((Player) creaturesInTheCE.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					} else if (creaturesInTheCE.get(i) instanceof Monster) {
						if ( ((Monster) creaturesInTheCE.get(i)).getInitiative() > ((Monster) key).getInitiative() ) {
							break;
						}
					}
					creaturesInTheCE.set(i+1, creaturesInTheCE.get(i));
					i = i - 1;
				}
				creaturesInTheCE.set(i+1, key);
			}
		}
		theEncounter.setCreaturesInCe(creaturesInTheCE);
		
		creature_model.removeAllElements();
		for (int index = 0; index < creaturesInTheCE.size(); index++) {
			CreatureBeanShallow tempBean = new CreatureBeanShallow();
			tempBean.createPanelFrom(creaturesInTheCE.get(index));
			creature_model.addElement(tempBean);
		}
//		List<TrapHazard> traps = theEncounter.getTrapsInCE();
		if (theEncounter.getCurrentCreatureId() == -1) {
	    	logger.info("The currentCreatureId is null so this is a new game.");
	    	Object first_creature = creaturesInTheCE.get(0);
	    	if (first_creature instanceof Player) {
	    		theEncounter.setCurrentCreatureId( 0 );/*((entity.Player) first_creature).getId() );*/
	    	} else if (first_creature instanceof Monster) {
	    		theEncounter.setCurrentCreatureId( 0 );/*((entity.Monster) first_creature).getId() );*/
	    	} 
	    	this.setCurrentCreature(theEncounter.getCurrentCreatureId());
		} else {
			logger.info("The currentCreatureId is non-null so set the current creature to the saved one.");
			this.setCurrentCreature(theEncounter.getCurrentCreatureId());
	    }
		
		return creature_model;
	}

	/**
	 * Take the first creature in the list of creatures playing and put it at the end of the list
	 * If the creature is a monster, all the monsters are put at the end of the list
	 */
	public int finishTurn() {
		/*
		 * Going to have to take a careful look at this...
		 */
		currentCreature++;
		if (currentCreature < creaturesInTheCE.size()) {
			theEncounter.setCurrentCreatureId(currentCreature);
			return currentCreature;
		} else {
			currentCreature = 0;
			theEncounter.setCurrentCreatureId(currentCreature);
			return currentCreature;
		}
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
    	currentCreature = currentCreatureId;
		theEncounter.setCurrentCreatureId(currentCreature);
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

	public void addTheseTeamsToCE(List<Team> addingTheseTeams) {
		/*
		 * Must add team to combat encounter which includes:
		 * 	-adding any new creatures, monsters and traps to their respective lists
		 * 
		 */
		
		for (int index = 0; index < addingTheseTeams.size(); index++) {
			theEncounter.addTeams( addingTheseTeams.get(index) );
		}
		theEncounter.organizeCreaturesIntoRespectiveLists();
	}

	public DefaultListModel updateCreaturesInCE(DefaultListModel creature_model) {
		List<Object> creatures = theEncounter.getCreaturesInCe();
		creature_model.removeAllElements();
		for (int index = 0; index < creatures.size(); index++) {
			CreatureBeanShallow aBean = new CreatureBeanShallow();
			aBean.createPanelFrom(creatures.get(index));
			creature_model.addElement(aBean);
		}
		
		return creature_model;
	}

	public void setStoryNotes(String newStory) {
		theEncounter.setNotes(newStory);
	}

	public void changeTupleName(int tupleChanged, String name) {
		theEncounter.getTally().getTuples().get(tupleChanged).setName(name);
	}

	public void changeTupleValue1(int row, int value1) {
		theEncounter.getTally().getTuples().get(row).setValue1(value1);
	}

	public void changeTupleValue2(int row, int value2) {
		theEncounter.getTally().getTuples().get(row).setValue2(value2);
	}

	public Object[] addTuple() {
		Tuple addingThisTuple = new Tuple();
		theEncounter.getTally().getTuples().add(addingThisTuple);
		Object[] return_data = new Object[3];
		return_data[0] = addingThisTuple.getName();
		return_data[1] = addingThisTuple.getValue1();
		return_data[2] = addingThisTuple.getValue2();
		return return_data;
	}

	public void removeTuples(int[] theseTuples) {
		List<Tuple> removingTheseTuples = theEncounter.getTally().getTuples();
		for (int index=0; index < theseTuples.length; index++) {
			removingTheseTuples.remove(index);
		}
		theEncounter.getTally().setTuples(removingTheseTuples);
	}

	public int getCurrentCreatureId() {
		return theEncounter.getCurrentCreatureId();
	}

	public int resetCurrentCreatureTurn() {
		theEncounter.setCurrentCreatureId(0);
		return 0;
	}

	public boolean isEmpty() {
		return theEncounter.isEmpty();
	}
	
}
