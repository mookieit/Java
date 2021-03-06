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

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fdm.tradingplatform.model.storage.Databean;

public class RoleVO implements Databean {
	private static Logger logger=Logger.getLogger("log");

	private String name;
	private Map<String,String> roles;
	
	public RoleVO(String name){
		this.name=name;
		roles=new HashMap<String,String>();
	}
	
	public RoleVO(String name,Map<String,String> roles){
		if(name==null)
			throw new NullPointerException(System.getProperty("NULLPOINTER_ERROR_MESSAGE"));
		
		this.name=name;
		this.roles=roles;
		
		logger.debug("new roleVO("+name+")");
	}
	
	public String getName() { return name; }

	public Map<String,String> getRoles() { return roles; }
	
	public String getAttribute(String functionName){ return roles.get(functionName); }
	
	public void setRole(String function,String value){
		logger.debug("putting Values to Roles:"+function+","+value);
		roles.put(function, value);
	}



}
