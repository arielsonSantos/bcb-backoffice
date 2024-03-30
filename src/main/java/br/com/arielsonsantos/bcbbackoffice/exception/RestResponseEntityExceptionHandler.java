package br.com.arielsonsantos.bcbbackoffice.exception;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(final ResourceNotFoundException ex, final WebRequest request) {
        final HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        final ErrorInfo errorInfo = new ErrorInfo(httpStatus, request, "Resource not found", Set.of(ex.getMessage()));

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value = InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(final InsufficientFundsException ex, final WebRequest request) {
        final Set<String> errors = new HashSet<>();

        errors.add("Balance: " + ex.getAmount());
        errors.add("Quota: " + ex.getQuota());
        errors.add("Transaction type: " + ex.getType());
        errors.add("Transaction value: " + ex.getValue());

        final HttpStatus httpStatus = HttpStatus.CONFLICT;
        final ErrorInfo errorInfo = new ErrorInfo(httpStatus, request, "Insufficient Funds", errors);

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), httpStatus, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        return handleValidationExceptions(ex, headers, status, request);
    }

    public ResponseEntity<Object> handleValidationExceptions(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        final Set<String> errors = new HashSet<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ": " + errorMessage);
        });

        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        final ErrorInfo errorInfo = new ErrorInfo(httpStatus, request, "Input validation error", errors);

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value = UniqueIdentifierViolationException.class)
    protected ResponseEntity<Object> handleUniqueIdentifierViolationException(final UniqueIdentifierViolationException ex, final WebRequest request) {
        final HttpStatus httpStatus = HttpStatus.CONFLICT;
        final ErrorInfo errorInfo = new ErrorInfo(httpStatus, request, "Data integrity violated", Set.of(ex.getMessage()));

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorInfo errorInfo = new ErrorInfo(httpStatus, request, "Contact support!", Set.of(ex.getMessage()));

        return handleExceptionInternal(ex, errorInfo, new HttpHeaders(), httpStatus, request);
    }
}