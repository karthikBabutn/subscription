package com.subscription.payload;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "SubscriptionDetailsRequest Model")
public class SubscriptionDetailsRequest {

    @ApiModelProperty(notes = "Subscription Amount")
    @NotEmpty(message = "Amount must not be empty")
    private String amount;

    @ApiModelProperty(notes = "Subscription Type")
    @NotEmpty(message = "Subscription Type must not be empty")
    private String subscriptionType;

    @ApiModelProperty(notes = "Subscription Interval")
    private String interval;

    @ApiModelProperty(notes = "Subscription Start Date in the format of dd-MM-YYYY")
    @NotEmpty(message = "Subscription Start Date must not be empty")
    private String startDate;

    @ApiModelProperty(notes = "Subscription End Date in the format of dd-MM-YYYY")
    @NotEmpty(message = "Subscription End Date must not be empty")
    private String endDate;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
    
    
}
