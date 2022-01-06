//package com.subscription.repository.entity;
//
//import java.sql.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import lombok.Data;
//
//@Entity
//@Table(name = "SUBSCRIPTION_DETAILS")
//@Data
//public class SubscriptionDetailsEntity {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private String id;
//
//    @Column(name = "subscription_id")
//    private String subscriptionId;
//
//    @Column(name = "start_date")
//    private Date startDate;
//
//    @Column(name = "end_date")
//    private Date endDate;
//
//    @Column(name = "subscription_dates")
//    private String subscriptionDates;
//
//    @Column(name = "subscription_type")
//    private String subscriptionType;
//
//    @Column(name = "interval")
//    private String interval;
//
//    @Column(name = "interval_count")
//    private String intervalCount;
//
//    @Column(name = "amount")
//    private String amount;
//
//}
