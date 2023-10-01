package com.smartcode.ecommerce.exception;


import com.smartcode.ecommerce.util.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicationException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> handleDuplicationException(HttpServletRequest request, DuplicationException exception) {
        logError(request, exception);
        return buildResponse(HttpStatus.CONFLICT, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleResourceNotFoundException(HttpServletRequest request, DuplicationException exception) {
        logError(request, exception);
        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleVerificationException(HttpServletRequest request, DuplicationException exception) {
        logError(request, exception);
        return buildResponse(HttpStatus.UNAUTHORIZED, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(VerificationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleValidationException(HttpServletRequest request, DuplicationException exception) {
        logError(request, exception);
        return buildResponse(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<ApiError> buildResponse(HttpStatus status, String message, String requestURI) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", message);
        ApiError apiError = new ApiError(status.value(), requestURI, errors);
        return ResponseEntity.status(status).body(apiError);
    }

    private void logError(HttpServletRequest request, Exception exception) {
        log.error(exception.getMessage());
        log.error("RequestURI {}", request.getRequestURI());
        exception.printStackTrace();
    }


}