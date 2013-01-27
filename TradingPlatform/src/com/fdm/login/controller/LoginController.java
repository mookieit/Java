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

package com.fdm.login.controller;

import org.apache.log4j.Logger;

import com.fdm.login.model.LoginManager;
import com.fdm.login.view.iViewLogin;
import com.fdm.tradingplatform.model.UserClientToken;
import com.fdm.tradingplatform.model.UserVO;
import com.fdm.tradingplatform.model.exception.TradingPlatformException;

public class LoginController {
	private static Logger logger = Logger.getLogger("log");

	private iViewLogin displayManager;
	private LoginManager loginManager;
	private iLoginCommand command;
	private UserClientToken userToken;
	private boolean isRunning;
	private int loginAttempts=0;

	public LoginController(iViewLogin displayManager, LoginManager loginManager) {
		this.loginManager = loginManager;
		this.displayManager = displayManager;
	}

	public UserClientToken setup() {
		logger.trace("Start setup()");

		command = new AuthenticateAction();
		isRunning = true;
		runApp();
		return userToken;
	}

	public void runApp() {

		while (isRunning) {
			loginAttempts++;
			actionUserInput(displayManager.displayLoginFormAndGetInput());		
		}
	}

	

	public void actionUserInput(UserVO userInputVO) {

		try {
			userToken = command.execute(userInputVO, loginManager, displayManager);
		} catch (TradingPlatformException e) {
			displayManager.displayMessageAndWaitForEnter(e.getMessage());
			userToken = null;
		} catch (Exception e) {
			displayManager.displayMessageAndWaitForEnter(e.getMessage());
		}

		if (userToken != null) {
			displayManager.displayMessageAndWaitForEnter(System.getProperty("LOGIN_SUCCESS"));
			isRunning = false;
		}
		
		if (loginAttempts>3){
			displayManager.displayMessageAndWaitForEnter(System.getProperty("THREE_STRIKES_LOGIN_ATTEMPT"));
			isRunning = false;
		}
	}

}
