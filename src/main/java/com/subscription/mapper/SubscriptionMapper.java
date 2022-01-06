//package com.subscription.mapper;
//
//import java.sql.Date;
//
//import com.subscription.payload.SubscriptionDetailsRequest;
//import com.subscription.repository.entity.SubscriptionDetailsEntity;
//import com.subscription.util.SubscriptionType;
//
///**
// * Class that enables to map from request to dto object
// */
//public class SubscriptionMapper {
//
//    /**
//     * Converts request to dto object
//     *
//     * @param request
//     *     request object
//     * @param subscriptionId
//     *     subscription id
//     * @param subscriptionStatus
//     *     subscription status
//     * @return dto object
//     */
//    public static SubscriptionDetailsEntity convertSubscriptionRequestToDto(SubscriptionDetailsRequest request,
//                                                                         String subscriptionId) {
////        SubscriptionDetailsEntity subscriptionDetailsEntity = new SubscriptionDetailsEntity();
////        subscriptionDetailsEntity.setAmount(request.getAmount());
////        subscriptionDetailsEntity.setSubscriptionType(SubscriptionType.valueOf(request.getSubscriptionType()));
////        subscriptionDetailsEntity.setSubscriptionDetailsDto(subscriptionDetailsDto);
////        subscriptionDetailsEntity.setStatus(subscriptionStatus.name());
////        subscriptionDetailsEntity.setInterval(request.getInterval());
////        subscriptionDetailsEntity.setSubscriptionId(subscriptionId);
////        subscriptionDetailsEntity.setStartDate(Date.valueOf(parseDateFromString(request.getStartDate())));
////        subscriptionDetailsEntity.setEndDate(Date.valueOf(parseDateFromString(request.getEndDate())));
////        subscriptionDetailsEntity.setPriceDetailsDto(priceDetailsDto);
//        return subscriptionDetailsEntity;
//    }
//}
