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



public class Selected {
	
	public static enum Option{
			ADD_USER(1), 
			REMOVE_USER(2), 
			BAN_USER(3), 
			UNBAN_USER(4), 
			UPDATE_USER_PASSWORD(5), 
			UPDATE_USER_ROLE(6),
			VIEW_OUTSTANDING_REQUESTS(7), 
			VIEW_ASSIGNED_REQUESTS(8), 
			VIEW_COMPLETED_REQUESTS(9), 
			ASSIGN_USER_REQUEST(10), 
			MARK_AS_COMPLETE(11), 
			LOGOUT(12);
			
			
			private final int value;
			
			private Option(int value){
				this.value=value;
			}
			
			public int getValue(){
				return this.value;
			}
			
			public static Option getOption(int value){
				Option returnOption=null;
				for(Option option : Option.values()){
					if(option.value==value)
						returnOption=option;
				}
				
				return returnOption;
				
			}
		
		

	}

}
