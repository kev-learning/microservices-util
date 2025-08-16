package com.microservices.core.util.http;

import lombok.extern.slf4j.Slf4j;
import com.microservices.core.util.exceptions.InvalidInputException;
import com.microservices.core.util.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody HttpErrorInfo handleNotFoundException(ServerHttpRequest request, NotFoundException nfe) {
        return buildHttpErrorInfo(HttpStatus.NOT_FOUND, request, nfe);
    }

    @ExceptionHandler(value = InvalidInputException.class, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody HttpErrorInfo handleInvalidInputException(ServerHttpRequest request, InvalidInputException iie) {
        return buildHttpErrorInfo(HttpStatus.BAD_REQUEST, request, iie);
    }

    private HttpErrorInfo buildHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception e) {
        final String path = request.getPath().pathWithinApplication().value();
        final String message = e.getMessage();

        log.debug("Returning HTTP status {} for path: {} with message: {}", httpStatus, path, message);
        return new HttpErrorInfo(ZonedDateTime.now(), httpStatus, message, path);
    }

    @ExceptionHandler(value = Exception.class, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody HttpErrorInfo handleException(ServerHttpRequest request, Exception ex) {
        return buildHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }
}
