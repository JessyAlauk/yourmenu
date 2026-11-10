package com.br.yourmenu.entrypoint.controller.handler;

import com.br.yourmenu.core.exception.CustomerNotFoundException;
import com.br.yourmenu.core.exception.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String GENERIC_TITLE = "Bad Request. Check the documentation.";
    private static final String TITLE = "Customer not found. Check the documentation.";
    private static final String METHOD_ARGUMENT_NOT_VALID_TITLE = "Campos obrigatórios nao cumpriram o requisito";

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerCustomerNotFoundException(CustomerNotFoundException exception){
        var status = HttpStatus.NOT_FOUND;
        var response = mountErrorResponse(TITLE, status, exception);

        LOG.error("m=handlerCustomerNotFoundException, status={}, e={}, msg={}", status, exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGenericException(Exception exception) {
        var status = HttpStatus.BAD_REQUEST;
        var response = mountErrorResponse(GENERIC_TITLE, status, exception);

        LOG.error("m=handlerGenericException, status={}, e={}, msg={}", status, exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        var currentFields = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ErrorResponse.FieldErrorDetails(
                        f.getField(),
                        f.getDefaultMessage()
                ))
                .toList();
        var status = HttpStatus.BAD_REQUEST;
        var response = mountErrorResponse(METHOD_ARGUMENT_NOT_VALID_TITLE,status , currentFields, exception);

        LOG.error("m=handleMethodArgumentNotValid, status={}, e={}, msg={}", status, exception.getClass().getSimpleName(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ErrorResponse mountErrorResponse(String title,
                                             HttpStatus status,
                                             Exception ex) {
        return mountErrorResponse(
                title,
                status,
                List.of(),
                ex
        );
    }

    private ErrorResponse mountErrorResponse(String title,
                                             HttpStatus status,
                                             List<ErrorResponse.FieldErrorDetails> errorDetails,
                                             Exception ex) {
        return new ErrorResponse(
                title,
                status.value(),
                ex.getMessage(),
                errorDetails,
                LocalDateTime.now()
        );
    }
}
