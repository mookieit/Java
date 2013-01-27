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

package com.fdm.tradingplatform.adminModule.control;


import org.apache.log4j.Logger;

import com.fdm.tradingplatform.adminModule.view.ConsoleView.iView;
import com.fdm.tradingplatform.model.UserClientToken;
import com.fdm.tradingplatform.model.UserLoginMap;
import com.fdm.tradingplatform.model.UserServerToken;

public class Helper {

	private static Logger logger = Logger.getLogger("log");
	private iView displayManager;
	private UserLoginMap userLoginMap = UserLoginMap.getInstance();
	private UserServerToken userServerToken;

	public Helper(iView displayManager) {
		this.displayManager = displayManager;
	}

	public boolean checkPermission(UserClientToken userClientToken, String functionName) {

		logger.trace("Start checkPermission(" + userClientToken.getUsername() + "," + functionName);
		userServerToken = userLoginMap.getUserServerToken(userClientToken.getUsername());
		return (checkFunctionPermission(userServerToken, functionName) && checkIfHashCodeIsValid(userClientToken, userServerToken));

	}

	private boolean checkFunctionPermission(UserServerToken userServerToken,String functionName) {
		logger.trace("Attempting to run check permissions for:"+userServerToken.getUsername()+ ". Function name="+functionName+" Users permission for this is:"+userServerToken.getRole(functionName));
		if (functionName.equals("logout"))
			return true;

		if (userServerToken.getRole(functionName).equals("y")) {
			logger.info(userServerToken.getUsername()+ " has been granted access for function:" + functionName);
			return true;
		} else {
			logger.info(userServerToken.getUsername()+ " has been denied access for function:" + functionName);
			displayManager.displayMessage(System.getProperty("UNSUFFICIENT_PRIVLEDGE_MESSAGE"));
			return false;
		}

	}

	private boolean checkIfHashCodeIsValid(UserClientToken userClientToken, UserServerToken userServerToken) {

		if (userClientToken.getHashCode() != userServerToken.getHashCode()) {
			userLoginMap.removeUserFromLoginMap(userClientToken);
			displayManager.displayMessage(System.getProperty("HASHCODE_MISMATCH_MESSAGE"));
			logger.error(userClientToken.getUsername() + " has an Invalid has code.");
			return false;
		} else
			return true;
	}

}
