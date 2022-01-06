package com.subscription.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subscription.expception.ServiceException;
import com.subscription.payload.SubscriptionDetailsRequest;
import com.subscription.payload.SubscriptionDetailsResponse;
import com.subscription.service.SubscriptionService;
import com.subscription.util.SubscriptionErrorCode;
import com.subscription.util.SubscriptionUtil;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	
	@Value("${sub.max.month}")
	private String maxMonth;
	
	   private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);


    @Override
    public SubscriptionDetailsResponse createSubscription(SubscriptionDetailsRequest subscriptionDetailsRequest) throws ServiceException {
    	// Maximum subscription 3 months
        if (SubscriptionUtil.validateFromAndToDateRange(subscriptionDetailsRequest.getStartDate(), subscriptionDetailsRequest.getEndDate(), maxMonth)) {
            List<String> subscriptionDates = SubscriptionUtil.getSubscriptionDates(subscriptionDetailsRequest.getStartDate(),
                    subscriptionDetailsRequest.getEndDate(), subscriptionDetailsRequest.getSubscriptionType(),
                    subscriptionDetailsRequest.getInterval());
            if (subscriptionDates.size() > 0) {
                try {
                    return new SubscriptionDetailsResponse(subscriptionDetailsRequest.getAmount(), subscriptionDetailsRequest.getSubscriptionType(), subscriptionDates);
                } catch (Exception e) {
                    log.error(e.getMessage());
                    throw new ServiceException(SubscriptionErrorCode.REPOSITORY_ERROR.getCode(), e.getMessage());
                }
            } else {
                String message = "Unable to process subscription. Unable to find any subscription dates";
                log.error(message);
                throw new ServiceException(SubscriptionErrorCode.INTERNAL_SERVICE_ERROR.getCode(),  message);
            }
        } else {
            String message = "Unable to process subscription order. Please check from and to dates";
            log.error(message);
            throw new ServiceException(SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(), message);
        }
    }
}
