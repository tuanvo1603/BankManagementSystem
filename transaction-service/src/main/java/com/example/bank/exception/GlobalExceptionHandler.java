//package com.example.transactionservice.exception;
//
//import com.example.moneyandaccountmanagementservice.response.ApiResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(AppException.class)
//    public ResponseEntity<ApiResponse> handleAppException(AppException exception) {
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(exception.getErrorCode().getCode());
//        apiResponse.setMessage(exception.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }
//}
