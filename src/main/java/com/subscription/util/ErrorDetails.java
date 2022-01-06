package com.subscription.util;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model Class for Error Object to be returned during response
 */
@ApiModel(description = "Response model for error returned")
public class ErrorDetails {

    /**
     * Time stamp at which error occurred
     */
    @ApiModelProperty(notes = "timestamp at which the error occurred")
    private Date timeStamp;
    /**
     * message to be given as response
     */
    @ApiModelProperty(notes = "error message")
    private String code;
    /**
     * Details of the error message like status code, uri
     */
    @ApiModelProperty(notes = "Details of the error message ")
    private String details;
    
    
    public ErrorDetails() {
	}
    
	public ErrorDetails(Date timeStamp, String code, String details) {
		super();
		this.timeStamp = timeStamp;
		this.code = code;
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
    
	
	
    

}
