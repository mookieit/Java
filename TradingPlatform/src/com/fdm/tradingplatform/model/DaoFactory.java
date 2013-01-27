/*
 * @author Rami Stefanidis
 * 
 **********************************************************************
 * This code and its derivatives belong to FDM Group PLC and may not be
 * copied,reproduced, amended or used in any way without permission
 * from FDM group PLC
 **********************************************************************
 * Current Version
 * ===============
 * Revision:  1.0
 * Date/time: 26/01/2013
 **********************************************************************
 */

package com.fdm.tradingplatform.model;


import com.fdm.tradingplatform.model.storage.RoleDatabaseDao;
import com.fdm.tradingplatform.model.storage.RoleXmlDao;
import com.fdm.tradingplatform.model.storage.UserDatabaseDao;
import com.fdm.tradingplatform.model.storage.UserRequestDatabaseDao;
import com.fdm.tradingplatform.model.storage.UserRequestXmlDao;
import com.fdm.tradingplatform.model.storage.UserXmlDao;
import com.fdm.tradingplatform.model.storage.iDao;

public class DaoFactory {

	public enum TypeOfInstance {
		USER, REQUEST, ROLE
	}

	private iConnectionPool connectionPool;

	public DaoFactory(Context context) {
		connectionPool = (iConnectionPool) context.getName("CONNECTION_POOL");
	}

	public iDao getInstance(TypeOfInstance typeOfInstance) {

		switch (typeOfInstance) {

		case USER:
			return makeUserDao(connectionPool.getConnection("USER"));
		case REQUEST:
			return makeUserRequestDao(connectionPool.getConnection("REQUEST"));
		case ROLE:
			return makeRoleDao(connectionPool.getConnection("ROLE"));

		}

		return null;
	}

	private iDao makeUserDao(Object connection) {

		if (System.getProperty("storageMedium").equals("XML")) {
			return UserXmlDao.getInstance((String) connection);
		} else if (System.getProperty("storageMedium").equals("DATABASE")) {
			return new UserDatabaseDao((JDBCConnectionPool) connection);
		}

		return null;

	}

	private iDao makeUserRequestDao(Object connection) {

		if (System.getProperty("storageMedium").equals("XML")) {
			return UserRequestXmlDao.getInstance((String) connection);
		} else if (System.getProperty("storageMedium").equals("DATABASE")) {
			return new UserRequestDatabaseDao((JDBCConnectionPool) connection);
		}
		return null;
	}

	private iDao makeRoleDao(Object connection) {

		if (System.getProperty("storageMedium").equals("XML")) {
			return RoleXmlDao.getInstance((String) connection);
		} else if (System.getProperty("storageMedium").equals("DATABASE")) {

			return new RoleDatabaseDao((JDBCConnectionPool) connection);
		} else
			return null;

	}

}
