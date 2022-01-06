package com.subscription.payload;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "SubscriptionDetailsResponse")
public class SubscriptionDetailsResponse {

    @ApiModelProperty(notes = "Amount")
    private String amount;

    @ApiModelProperty(notes = "Subscription Type")
    private String subscriptionType;

    @ApiModelProperty(notes = "Invoice dates")
    private List<String> invoiceDates;
    
    public SubscriptionDetailsResponse() {
	}

	public SubscriptionDetailsResponse(String amount, String subscriptionType, List<String> invoiceDates) {
		super();
		this.amount = amount;
		this.subscriptionType = subscriptionType;
		this.invoiceDates = invoiceDates;
	}

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

	public List<String> getInvoiceDates() {
		return invoiceDates;
	}

	public void setInvoiceDates(List<String> invoiceDates) {
		this.invoiceDates = invoiceDates;
	}
    
	
	
    
    
    
    
}
