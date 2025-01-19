package br.com.instivo.salarytime.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
public class EmployeeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 839758888691697159L;

    private final HttpStatus status;
    private final String statusCode;
    private final String message;

    public EmployeeNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
        this.message = message;
        this.statusCode = String.valueOf(status.value());
    }
}
