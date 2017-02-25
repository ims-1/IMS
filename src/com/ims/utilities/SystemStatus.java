package com.ims.utilities;

public enum SystemStatus {
	ok,
	committed,
	exception,
	unauthorized,
	unauthenticated,
	norecord,
	notmatched;
	
	String key;
	
	SystemStatus(String key){
		this.key = key;
	}
	
	SystemStatus(){}
	
	SystemStatus getValues(String key){
		if("committed".equals(key)){
			return committed;
		}
		else if("exception".equals(key)){
			return exception;
		}
		else if("unauthorized".equals(key)){
			return unauthorized;
		}
		else if("unauthenticated".equals(key)){
			return unauthenticated;
		}
		return null;
	}
}
