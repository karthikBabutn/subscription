package com.subscription.expception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.subscription.util.ErrorDetails;
import com.subscription.util.SubscriptionErrorCode;

/**
 * Class to handle exceptions gracefully and construct error model
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handler used when {@link ServiceException} is  thrown
     *
     * @param e          service exception
     * @param webRequest web request interceptor to fetch metadata of request
     * @return {@link ResponseEntity} of type {@link ErrorDetails}
     */
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> serviceExceptionHandler(ServiceException e, WebRequest webRequest) {
        if (e.getCode().equalsIgnoreCase(SubscriptionErrorCode.REPOSITORY_ERROR.getCode())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(),
                    SubscriptionErrorCode.REPOSITORY_ERROR.getMessage(), e.getMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_MODIFIED);
        } else if (e.getCode().equalsIgnoreCase(SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode())) {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(),
                    e.getMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
        } else {
            ErrorDetails errorDetails = new ErrorDetails(new Date(), SubscriptionErrorCode.UNEXPECTED_ERROR.getCode(),
                    e.getMessage());
            return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Handler used when {@link Exception} is  thrown
     *
     * @param e          exception
     * @param webRequest web request interceptor to fetch metadata of request
     * @return {@link ResponseEntity} of type {@link ErrorDetails}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception e, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), SubscriptionErrorCode.INTERNAL_SERVICE_ERROR.getMessage(), SubscriptionErrorCode.INTERNAL_SERVICE_ERROR.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> globalMethodArgExpHandler(MethodArgumentNotValidException e, WebRequest webRequest) {
    	List<String> errors = new ArrayList<>();
    	e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + " : " + errorMessage);
    	 });
    	String message = errors.stream().collect(Collectors.joining(","));
        ErrorDetails errorDetails = new ErrorDetails(new Date(), SubscriptionErrorCode.UNABLE_TO_PROCESS_SUBSCRIPTION_ORDER.getCode(), message);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
