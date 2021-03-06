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

import com.fdm.tradingplatform.adminModule.model.AdminManager;
import com.fdm.tradingplatform.adminModule.view.ConsoleView.UserInputVO;
import com.fdm.tradingplatform.adminModule.view.ConsoleView.iView;
import com.fdm.tradingplatform.model.UserClientToken;
import com.fdm.tradingplatform.model.exception.TradingPlatformException;

public class UpdateUserDetailsAction extends AbstractAdminCommand implements iAdminCommand {
	UpdateUserDetailsAction() {
		super("a");
		// TODO Auto-generated constructor stub
	}

	private static Logger logger = Logger.getLogger("log");

	private static final int UPDATE_PASSWORD_OPTION = 1;
	private static final int UPDATE_ROLE_OPTION = 2;

	@Override
	public void execute(UserClientToken userClientToken, UserInputVO userInputVO, AdminManager adminManager, iView displayManager) throws TradingPlatformException {
		logger.trace("Start execute");

		if (userInputVO.getSecondLevelInputtype() == UPDATE_PASSWORD_OPTION) {

				adminManager.updateUserPassword(userInputVO);
				displayManager.displayMessage("\n" + userInputVO.getUsername() + " password has successfully been updated.\n");

			logger.info(userClientToken.getUsername() + " has updated its password.");

		} else if (userInputVO.getSecondLevelInputtype() == UPDATE_ROLE_OPTION) {

				adminManager.updateUserRoleName(userInputVO);
				displayManager.displayMessage("\n" + userInputVO.getUsername() + " role has successfully been updated to " + userInputVO.getRoleName());
		
			logger.info(userClientToken.getUsername() + " has updated its rolename" + userInputVO.getRoleName());
		}

		logger.trace("End execute");
	}

}
