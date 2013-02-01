package entity.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resource.HibernateUtil;
import entity.CharacterSheet;
import entity.EntityEnum.CS_Resistance_Type;
import entity.Resistance;

public class ResistanceDaoImpl implements ResistanceDao {
	
	private static final Logger logger = Logger.getLogger(ResistanceDaoImpl.class);

	public ResistanceDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public int saveResistance(String name, CS_Resistance_Type resistanceType,
			int resistanceValue, int characterSheetId) {
    	logger.debug("Resistance " + name + " is about to be created in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        int resistanceID = -1;
        try {
            transaction = session.beginTransaction();
            Resistance r = new Resistance(name);
            r.setResistanceType(resistanceType);
            r.setResistanceValue(resistanceValue);
            logger.debug("Association with a character sheet");
            CharacterSheet cs = (CharacterSheet) session.load(CharacterSheet.class, characterSheetId);
            logger.debug("CharacterSheet name = " + cs.getName());
        	r.setCharacterSheet(cs);
            resistanceID = (Integer) session.save(r);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while saving Resistance " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Resistance" + name + " was successfully saved in the database.");
            session.close();
        }
        return resistanceID;
	}

	public void updateResistance(int resistanceId, String name,
			CS_Resistance_Type resistanceType, int resistanceValue,
			int characterSheetId) {
    	logger.debug("Resistance " + name + " is about to be updated in the database.");
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Resistance r = (Resistance) session.get(Resistance.class, resistanceId);
            r.setName(name);
            r.setResistanceType(resistanceType);
            r.setResistanceValue(resistanceValue);
            logger.debug("Association with a character sheet");
            CharacterSheet cs = (CharacterSheet) session.load(CharacterSheet.class, characterSheetId);
            logger.debug("CharacterSheet name = " + cs.getName());
        	r.setCharacterSheet(cs);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while updating Resistance " + name + " in the database", e.getCause());
        } finally {
        	logger.info("Resistance " + name + " was successfully updated in the database.");
            session.close();
        }
		
	}

	public void deleteResistance(int resistanceId) {
		logger.debug("Resistance " + resistanceId + " is about to be deleted from the database.");
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Resistance r = (Resistance) session.get(Resistance.class, resistanceId);
            logger.info("Deletion of resistance " + r.getName() + " associated to the character sheet " + r.getCharacterSheet().getName());
            session.delete(r);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.fatal("Error while deleting Resistance " + resistanceId + " in the database", e.getCause());
        } finally {
        	logger.info("Resistance " + resistanceId + " was successfully removed from the database.");
            session.close();
        }			
	}

}