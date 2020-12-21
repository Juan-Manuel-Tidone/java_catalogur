package com.cataloguesample.cataloguecrud.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
    Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse onNoHandlerFound(NoHandlerFoundException exception, WebRequest request) {
        log.error(String.format("Handler %s not found", request.getDescription(false)));

        ErrorResponse response = new ErrorResponse();
        response.getErrors().add(
                new Error(
                        ErrorCodes.ERR_HANDLER_NOT_FOUND,
                        "Handler not found",
                        String.format("Handler %s not found",request.getDescription(false))));

        return response;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse onResourceFound(ResourceNotFoundException exception, WebRequest request) {
        log.error(String.format("No resource found exception occurred: %s ", exception.getMessage()));

        ErrorResponse response = new ErrorResponse();
        response.getErrors().add(
                new Error(
                        ErrorCodes.ERR_RESOURCE_NOT_FOUND,
                        "Resource not found",
                        exception.getMessage()));

        return response;
    }

}
