package br.com.instivo.salarytime.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents an error response returned by the API.")
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -1492597573294903677L;

    @Schema(description = "HTTP status code of the error.", example = "400")
    private int code;

    @Schema(description = "HTTP status text for the error.", example = "Bad Request")
    private String status;

    @Schema(description = "Detailed error message explaining the issue.", example = "Invalid request parameters.")
    private String message;

    @Schema(description = "Path of the API endpoint that caused the error.", example = "/api/v1/employees")
    private String path;

    @Schema(description = "List of validation errors or field-specific messages.")
    List<FieldMessage> errors;
}