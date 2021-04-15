package com.recordshop.catalog.web.core.exception;

import com.recordshop.catalog.domain.record.InvalidRecordFilterException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice("com.recordshop.catalog.web")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidRecordFilterException.class)
    protected ResponseEntity<Object> handleInvalidRecordFIlter(
            InvalidRecordFilterException ex
    ) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
