package br.com.instivo.salarytime.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Object> employeeNotFoundException(EmployeeNotFoundException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = buildErrorResponse(request, ex.getStatus(), ex.getMessage(), null);

        return ResponseEntity.status(ex.getStatus()).contentType(APPLICATION_JSON).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        HttpStatus httpStatus = UNPROCESSABLE_ENTITY;

        List<FieldMessage> fieldMessageStream = ex.getFieldErrors()
                .stream()
                .map(error -> FieldMessage.builder()
                        .fieldName(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = buildErrorResponse(request, httpStatus, "Invalid request parameters.", fieldMessageStream);

        return ResponseEntity.status(httpStatus).contentType(APPLICATION_JSON).body(errorResponse);
    }

    private ErrorResponse buildErrorResponse(HttpServletRequest request, HttpStatus httpStatus, String message, List<FieldMessage> fieldMessageStream) {
        return ErrorResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.name())
                .message(message)
                .path(request.getRequestURI())
                .errors(fieldMessageStream)
                .build();
    }
}
