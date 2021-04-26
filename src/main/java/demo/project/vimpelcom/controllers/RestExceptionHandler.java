package demo.project.vimpelcom.controllers;

import demo.project.vimpelcom.controllers.models.ErrorResponse;
import demo.project.vimpelcom.exceptions.VimpelcomErrorTypes;
import demo.project.vimpelcom.exceptions.VimpelcomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> unhandledException(Exception ex) {
        log.error(ex.getLocalizedMessage(), ex);
        ErrorResponse restError = new ErrorResponse(
                VimpelcomErrorTypes.UNKNOWN_ERROR.getErrorCode(),
                VimpelcomErrorTypes.UNKNOWN_ERROR.getDescr(),
                ex.getClass().getName(),
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restError);
    }

    @ExceptionHandler(VimpelcomException.class)
    protected ResponseEntity<Object> vimpelcomException(VimpelcomException ex) {
        log.error(ex.getLocalizedMessage(), ex);
        ErrorResponse restError = new ErrorResponse(
                ex.getErrorType().getErrorCode(),
                ex.getErrorType().getDescr(),
                ex.getClass().getName(),
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restError);
    }
}