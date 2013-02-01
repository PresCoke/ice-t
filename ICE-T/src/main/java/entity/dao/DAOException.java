package entity.dao;

import java.util.List;

import org.apache.log4j.Logger;

public class DAOException extends Exception {

	private static final long serialVersionUID = 8358041037569440113L;
	
	private static final Logger logger = Logger.getLogger(AttackDaoImpl.class);

	/**
	 * Constructors
	 */
	public DAOException() {
	}

	public DAOException(String arg0) {
		super(arg0);
	}

	public DAOException(Throwable arg0) {
		super(arg0);
	}

	public DAOException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DAOException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
}
