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

import com.fdm.tradingplatform.adminModule.model.AdminManager;
import com.fdm.tradingplatform.adminModule.view.ConsoleView.UserInputVO;
import com.fdm.tradingplatform.adminModule.view.ConsoleView.iView;
import com.fdm.tradingplatform.model.UserClientToken;
import com.fdm.tradingplatform.model.exception.TradingPlatformException;

public class UpdateUserPasswordAction extends AbstractAdminCommand implements iAdminCommand{

	public UpdateUserPasswordAction() {
		super(System.getProperty("UPDATE_USER_DETAILS"));
	}



	@Override
	public void execute(UserClientToken userClientToken, UserInputVO userInputVO, AdminManager adminManager, iView displayManager) throws TradingPlatformException {
		
		adminManager.updateUserPassword(userInputVO);
		displayManager.displayMessage("\n" + userInputVO.getUsername() + " password has successfully been updated.\n");

		logger.info(userClientToken.getUsername() + " has updated its password.");
	}

}
