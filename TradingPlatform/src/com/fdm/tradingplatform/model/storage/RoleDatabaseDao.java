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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.fdm.tradingplatform.model.JDBCConnectionPool;
import com.fdm.tradingplatform.model.RoleVO;
import com.fdm.tradingplatform.model.exception.TradingPlatformException;


public class RoleDatabaseDao extends AbstractDatabaseDao{

	public RoleDatabaseDao(JDBCConnectionPool connectionPool)  {
		super(connectionPool);
		
	}

	@Override
	public Databean select(String primaryKey) throws TradingPlatformException {
		Map<String,String> roleMap = new HashMap<String,String>();
		String roleName;
		try {
			connection = connectionPool.checkOut();
			statement = connection.prepareStatement(System.getProperty("SELECT_A_SINGLE_ROLE"));
			statement.setString(1, primaryKey);
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			
			roleName=rs.getString(1);
			
			roleMap.put("addUser",rs.getString(2));
			roleMap.put("removeUser",rs.getString(3));
			roleMap.put("changePermision",rs.getString(4));
			roleMap.put("userRegistration",rs.getString(5));
			roleMap.put("banUser",rs.getString(6));
			roleMap.put("updateUserDetails",rs.getString(7));
			roleMap.put("adminScreen",rs.getString(8));
			roleMap.put("unBanUser",rs.getString(9));
			roleMap.put("viewUserRequest",rs.getString(10));
			roleMap.put("assignUserRequest",rs.getString(11));
			roleMap.put("markAsCompleteUserRequest",rs.getString(12));
			
			RoleVO roleVO = new RoleVO(roleName,roleMap);
		
			return (Databean) roleVO;
			
		} catch (SQLException e) {
			throw new TradingPlatformException(System.getProperty("SQL_ERROR_MESSAGE"));

		}
		finally{
			connectionPool.checkIn(connection);
		}
	}

	@Override
	public void insert(Databean bean) throws TradingPlatformException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Databean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String primaryKey) throws TradingPlatformException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Map<String,Databean> selectAll() throws TradingPlatformException {
		logger.info("Attempting to select all roles. ");
		
		Map<String,Databean> allRoles = new HashMap<String,Databean>();
		RoleVO roleVO;
		
	
		try {
			
			connection = connectionPool.checkOut();
			logger.info("Is connection closed?:"+connection.isClosed());
			statement = connection.prepareStatement(System.getProperty("SELECT_ALL_ROLES"));
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()){
				roleVO = new RoleVO(rs.getString(1));
				
				roleVO.setRole("addUser", rs.getString(2));
				roleVO.setRole("removeUser", rs.getString(3));
				roleVO.setRole("changePermission", rs.getString(4));
				roleVO.setRole("UserRegistration", rs.getString(5));
				roleVO.setRole("banUser", rs.getString(6));
				roleVO.setRole("updateUserDetails", rs.getString(7));
				roleVO.setRole("adminScreen", rs.getString(8));
				roleVO.setRole("unBanUser", rs.getString(9));
				roleVO.setRole("viewUserRequest", rs.getString(10));
				roleVO.setRole("assignUserRequest", rs.getString(11));
				roleVO.setRole("markAsCompleteUserRequest", rs.getString(12));
				
				allRoles.put(rs.getString(1), roleVO);
				
			}
		} catch (SQLException e) {
			logger.error(System.getProperty("SQL_ERROR_MESSAGE"));
			throw new TradingPlatformException(System.getProperty("SQL_ERROR_MESSAGE"));
		}
		finally{
			connectionPool.checkIn(connection);
		}
		logger.info("Returning all roles, Quantity of roles="+allRoles.size());

		return allRoles;
	}
	
}
