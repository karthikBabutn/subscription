package com.subscription.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.subscription.expception.ServiceException;
import com.subscription.payload.SubscriptionDetailsRequest;
import com.subscription.payload.SubscriptionDetailsResponse;
import com.subscription.service.impl.SubscriptionServiceImpl;
import com.subscription.util.SubscriptionType;

@RunWith(SpringRunner.class) 
@SpringBootTest
public class SubscriptionServiceTest {
	
	 @InjectMocks
	 private SubscriptionServiceImpl subscriptionService;

	 @Test
	    public void testCreateSubscription_Success() throws ServiceException {
	        SubscriptionDetailsRequest subscriptionDetailsRequest = createSubcriptionRequestObject();

	        SubscriptionDetailsResponse subscriptionDetailsResponse =
	                this.subscriptionService.createSubscription(subscriptionDetailsRequest);
	        Assert.assertEquals(subscriptionDetailsResponse.getSubscriptionType(), SubscriptionType.DAILY.name());
	        Assert.assertEquals(subscriptionDetailsResponse.getAmount(), subscriptionDetailsRequest.getAmount());
	        Assert.assertEquals(subscriptionDetailsResponse.getInvoiceDates(),new ArrayList<String>(Arrays.asList("05/01/2022","06/01/2022","07/01/2022","08/01/2022")));
	    }


	    @Test(expected = ServiceException.class)
	    public void testCreateSubscription_EmptySubscriptionDates() throws ServiceException {
	        SubscriptionDetailsRequest subscriptionDetailsRequest = createSubcriptionRequestObject();
	        subscriptionDetailsRequest.setStartDate("");
	        subscriptionDetailsRequest.setEndDate("");
	        this.subscriptionService.createSubscription(subscriptionDetailsRequest);
	    }

	    @Test(expected = ServiceException.class)
	    public void testCreateSubscription_ValidateDatesException() throws ServiceException {
	        SubscriptionDetailsRequest subscriptionDetailsRequest = createSubcriptionRequestObject();
	        subscriptionDetailsRequest.setStartDate("10/01/2022");
	        subscriptionDetailsRequest.setEndDate("10/08/2022");
	        this.subscriptionService.createSubscription(subscriptionDetailsRequest);

	    }

	    private SubscriptionDetailsRequest createSubcriptionRequestObject() {
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("DAILY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("09/01/2022");
	        subscriptionDetailsRequest.setInterval("1");
	        return subscriptionDetailsRequest;
	    }
}
