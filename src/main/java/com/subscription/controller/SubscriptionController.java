package com.subscription.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Enums;
import com.subscription.expception.ServiceException;
import com.subscription.payload.SubscriptionDetailsRequest;
import com.subscription.payload.SubscriptionDetailsResponse;
import com.subscription.service.SubscriptionService;
import com.subscription.service.impl.SubscriptionServiceImpl;
import com.subscription.util.SubscriptionErrorCode;
import com.subscription.util.SubscriptionType;
import com.subscription.util.SubscriptionUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Subscription Controller class
 */
@RestController
@RequestMapping("/api/v1/subscription")
@Api(value = "Subscription API")
public class SubscriptionController {
	
	private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private SubscriptionService subscriptionService;
    
    public SubscriptionController(SubscriptionService subscriptionService) {
    	this.subscriptionService = subscriptionService;
	}

    @ApiOperation(value = "Saves Subscription details",
            notes = "Saves Subscription details", tags = {})
    @PostMapping(value = "/create", produces = {"application/json"})
    public ResponseEntity<SubscriptionDetailsResponse> saveSubscriptionDetails(@RequestBody @Valid SubscriptionDetailsRequest request) throws ServiceException {
    	validateRequest(request.getInterval(), request.getSubscriptionType());
        SubscriptionDetailsResponse subscriptionDetailsResponse = this.subscriptionService.createSubscription(request);
        return new ResponseEntity<SubscriptionDetailsResponse>(subscriptionDetailsResponse, HttpStatus.CREATED);
    }
    
    private void validateRequest(String interval,String subscriptionType) throws ServiceException {
    	String errorMessage="";
    	
    	SubscriptionType subscriptionTypeOptional = Enums.getIfPresent(SubscriptionType.class, subscriptionType).orNull();
    	if(subscriptionTypeOptional==null) {
    		errorMessage="Unable to process subscription order. Please check Subscription Type";
      	  log.error(errorMessage);
      	  throw new ServiceException(SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(),errorMessage);
    	}
    	  switch (SubscriptionType.valueOf(subscriptionType)) {
          case MONTHLY:
        	  try {
        		  Integer.parseInt(interval);
        	  } catch (NumberFormatException e) {
        		  errorMessage="Unable to process subscription order. Please check Monthly interaval";
        		  log.error(errorMessage);
				throw new ServiceException(SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(),errorMessage);
			}
              break;
          case WEEKLY:
              if(!SubscriptionUtil.validateWeeklyDay(interval)) {
            	  errorMessage="Unable to process subscription order. Please check Weekly interaval";
            	  log.error(errorMessage);
            	  throw new ServiceException(SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(), errorMessage);
              }
              break;
          case DAILY:
        	  break;
    	  }
    }
}