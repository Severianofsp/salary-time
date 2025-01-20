package br.com.instivo.salarytime.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a specific validation error for a field.")
public class FieldMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 4274898168763744870L;

    @Schema(description = "Name of the field that caused the validation error.", example = "field")
    private String fieldName;

    @Schema(description = "Error message describing the issue with the field.", example = "message")
    private String message;
}
