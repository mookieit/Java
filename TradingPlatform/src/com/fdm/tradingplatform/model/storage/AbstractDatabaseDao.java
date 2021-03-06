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

package com.fdm.tradingplatform.model.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.fdm.tradingplatform.model.JDBCConnectionPool;

public abstract class AbstractDatabaseDao implements iDao{
	protected static Logger logger = Logger.getLogger("log");
	protected JDBCConnectionPool connectionPool;
	protected PreparedStatement statement;
	protected Connection connection;

	protected AbstractDatabaseDao(JDBCConnectionPool connectionPool) {

		this.connectionPool = connectionPool;
	}



}
