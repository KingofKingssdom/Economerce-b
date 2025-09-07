package com.caNhan.E_conomy.GlobalExeption;

import com.caNhan.E_conomy.GlobalExeption.Exception.CustomerAlreadyExistsException;
import com.caNhan.E_conomy.GlobalExeption.Exception.NoSuchCustomerExistsException;
import com.caNhan.E_conomy.Response.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = NoSuchCustomerExistsException.class)
    @ResponseBody
    public ResponseError handleNoExistsExeption(NoSuchCustomerExistsException ex) {
        return new ResponseError(HttpStatus.NOT_FOUND.value(),ex.getMessage());
    }
//    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
//    @ResponseBody
//    public ResponseError handleAlreadyExistsExeption(CustomerAlreadyExistsException ex) {
//        return  new ResponseError(HttpStatus.NOT_FOUND.value(),ex.getMessage());
//    }
}
