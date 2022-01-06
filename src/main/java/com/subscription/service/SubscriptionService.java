package com.subscription.service;


import com.subscription.expception.ServiceException;
import com.subscription.payload.SubscriptionDetailsRequest;
import com.subscription.payload.SubscriptionDetailsResponse;

/**
 * Subscription Service class
 */
public interface SubscriptionService {

    /**
     * Creates a subscription
     *
     * @param subscriptionDetailsRequest
     *     request object
     * @return subscription response object
     * @throws ServiceException
     *exception thrown
     */
    SubscriptionDetailsResponse createSubscription(SubscriptionDetailsRequest subscriptionDetailsRequest) throws ServiceException;
}
