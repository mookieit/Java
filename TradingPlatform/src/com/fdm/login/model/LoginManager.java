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

package com.fdm.login.model;


import com.fdm.tradingplatform.model.exception.TradingPlatformException;
import com.fdm.tradingplatform.model.storage.iDao;
import com.fdm.tradingplatform.model.PasswordService;
import com.fdm.tradingplatform.model.UserClientToken;
import com.fdm.tradingplatform.model.UserVO;

public class LoginManager {

	private iDao userDao;
	private iDao roleDao;
	private PasswordService passwordService;


	public LoginManager(iDao userDao,iDao roleDao, PasswordService passwordService) {
		this.userDao=userDao;
		this.roleDao=roleDao;
		this.passwordService=passwordService;
	}

	public UserClientToken authenticator(UserVO userInput, UserVO userStoredAccount)throws TradingPlatformException {
				
		if (userStoredAccount == null || userInput.getPassword() == null || (!passwordService.encrypt(userInput.getPassword()).equals(userStoredAccount.getPassword())))
			throw new TradingPlatformException(System.getProperty("USERNAME_OR_PASSWORD_ERROR_MESSAGE"));
		
		if (userStoredAccount.getStatus().equalsIgnoreCase("inActive"))
			throw new TradingPlatformException(System.getProperty("USER_IS_BANNED_MESSAGE"));
		
		return new UserClientToken(userInput.getUsername(), userDao,roleDao);
	
	}


	public UserVO getUserAccount(String username)throws TradingPlatformException {
		
		return (UserVO) userDao.select(username);
	}

}
