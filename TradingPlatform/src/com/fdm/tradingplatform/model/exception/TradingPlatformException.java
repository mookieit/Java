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

package com.fdm.tradingplatform.model.exception;

public class TradingPlatformException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String CONTACT_ADMINISTRATOR_MESSAGE="There has been an error, please try again or contact the Systems Administrator.\n";
	public static final String USER_NULL_VALUE="A Unknown value has tried to be persisted into storage, please try again or contact the Systems Administrator.";
	
	
	
	protected Exception e;
		
	public TradingPlatformException(Exception e){
		super(CONTACT_ADMINISTRATOR_MESSAGE);
		this.e=e;
	}
	
	public TradingPlatformException(String message){
		
		super(message);
		
	}

}
