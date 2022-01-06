package com.subscription.util;

public enum SubscriptionErrorCode {

    UNEXPECTED_ERROR(                       "SP1001", "Unexpected Error Occurred"),
    REPOSITORY_ERROR(                       "SP1002", "Repository Exception Occurred"),
    UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER(   "SP2001", "Unable to Process Subscription Order"),
    INTERNAL_SERVICE_ERROR(                 "SP1003", "Internal Service Error");
	
	private SubscriptionErrorCode( String code, String message) {
		this.code = code;
		this.message = message;
	}

    private String code;
    private String message;
    
    public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}


