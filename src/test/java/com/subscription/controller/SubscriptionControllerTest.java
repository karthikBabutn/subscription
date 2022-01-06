package com.subscription.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscription.payload.SubscriptionDetailsRequest;
import com.subscription.payload.SubscriptionDetailsResponse;
import com.subscription.util.ErrorDetails;
import com.subscription.util.SubscriptionType;

@RunWith(SpringRunner.class) 
@AutoConfigureMockMvc
@SpringBootTest
public class SubscriptionControllerTest {
	
	  @Autowired
	    protected MockMvc mockMvc;

	    @Autowired
	    private WebApplicationContext webApplicationContext;

	    @Before
	    public void init() throws Exception {
	        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	    }

	    
	    @Test
	    public void createSubscriptionDailySuccess() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("DAILY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("09/01/2022");
	        subscriptionDetailsRequest.setInterval("1");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        SubscriptionDetailsResponse subscriptionDetailsResponse = objectMapper.readValue(response.getContentAsString(), SubscriptionDetailsResponse.class);
	        Assert.assertEquals("201 http status returned", 201, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(subscriptionDetailsResponse.getSubscriptionType(), SubscriptionType.DAILY.name());
	        Assert.assertEquals(subscriptionDetailsResponse.getAmount(), subscriptionDetailsRequest.getAmount());
	        Assert.assertEquals(subscriptionDetailsResponse.getInvoiceDates(),new ArrayList<String>(Arrays.asList("05/01/2022","06/01/2022","07/01/2022","08/01/2022")));
	    }
	    
	    @Test
	    public void createSubscriptionWeeklySuccess() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("WEEKLY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("04/02/2022");
	        subscriptionDetailsRequest.setInterval("MONDAY");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        SubscriptionDetailsResponse subscriptionDetailsResponse = objectMapper.readValue(response.getContentAsString(), SubscriptionDetailsResponse.class);
	        Assert.assertEquals("201 http status returned", 201, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(subscriptionDetailsResponse.getSubscriptionType(), SubscriptionType.WEEKLY.name());
	        Assert.assertEquals(subscriptionDetailsResponse.getAmount(), subscriptionDetailsRequest.getAmount());
	        Assert.assertEquals(subscriptionDetailsResponse.getInvoiceDates(),new ArrayList<String>(Arrays.asList("10/01/2022","17/01/2022","24/01/2022","31/01/2022")));
	    }
	    
	    @Test
	    public void createSubscriptionMonthlySuccess() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("MONTHLY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("25/03/2022");
	        subscriptionDetailsRequest.setInterval("20");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        SubscriptionDetailsResponse subscriptionDetailsResponse = objectMapper.readValue(response.getContentAsString(), SubscriptionDetailsResponse.class);
	        Assert.assertEquals("201 http status returned", 201, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(subscriptionDetailsResponse.getSubscriptionType(), SubscriptionType.MONTHLY.name());
	        Assert.assertEquals(subscriptionDetailsResponse.getAmount(), subscriptionDetailsRequest.getAmount());
	        Assert.assertEquals(subscriptionDetailsResponse.getInvoiceDates(),new ArrayList<String>(Arrays.asList("20/01/2022","20/02/2022","20/03/2022")));
	    }
	    
	    @Test
	    public void createSubscriptionEmptyError() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("");
	        subscriptionDetailsRequest.setSubscriptionType("");
	        subscriptionDetailsRequest.setStartDate("");
	        subscriptionDetailsRequest.setEndDate("");
	        subscriptionDetailsRequest.setInterval("");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        ErrorDetails errorDetails = objectMapper.readValue(response.getContentAsString(), ErrorDetails.class);
	        Assert.assertEquals("400 http status returned", 400, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(errorDetails.getCode(), "SP2001");
	        Assert.assertNotNull(errorDetails.getDetails());
	    }
	    
	    @Test
	    public void createSubscriptionWeeklyError() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("WEEKLY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("09/01/2022");
	        subscriptionDetailsRequest.setInterval("SDF");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        ErrorDetails errorDetails = objectMapper.readValue(response.getContentAsString(), ErrorDetails.class);
	        Assert.assertEquals("400 http status returned", 400, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(errorDetails.getCode(), "SP2001");
	        Assert.assertEquals(errorDetails.getDetails(), "Unable to process subscription order. Please check Weekly interaval");
	    }
	    
	    @Test
	    public void createSubscriptionMontlyIntervalError() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("MONTHLY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("09/01/2022");
	        subscriptionDetailsRequest.setInterval("SDF");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        ErrorDetails errorDetails = objectMapper.readValue(response.getContentAsString(), ErrorDetails.class);
	        Assert.assertEquals("400 http status returned", 400, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(errorDetails.getCode(), "SP2001");
	        Assert.assertEquals(errorDetails.getDetails(), "Unable to process subscription order. Please check Monthly interaval");
	    }
	    
	    @Test
	    public void createSubscriptionSubcriptionTypeError() throws Exception {
	        ObjectMapper objectMapper = new ObjectMapper();
	        SubscriptionDetailsRequest subscriptionDetailsRequest = new SubscriptionDetailsRequest();
	        subscriptionDetailsRequest.setAmount("10");
	        subscriptionDetailsRequest.setSubscriptionType("MONTLY");
	        subscriptionDetailsRequest.setStartDate("05/01/2022");
	        subscriptionDetailsRequest.setEndDate("09/01/2022");
	        subscriptionDetailsRequest.setInterval("20");
	        String requestString = objectMapper.writeValueAsString(subscriptionDetailsRequest);
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
	            .post("/api/v1/subscription/create")
	            .accept(MediaType.APPLICATION_JSON).content(requestString)
	            .contentType(MediaType.APPLICATION_JSON);
	        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	        MockHttpServletResponse response = result.getResponse();
	        int status = response.getStatus();
	        ErrorDetails errorDetails = objectMapper.readValue(response.getContentAsString(), ErrorDetails.class);
	        Assert.assertEquals("400 http status returned", 400, status);
	        Assert.assertNotNull(response);
	        Assert.assertEquals(errorDetails.getCode(), "SP2001");
	        Assert.assertEquals(errorDetails.getDetails(), "Unable to process subscription order. Please check Subscription Type");
	    }

}
