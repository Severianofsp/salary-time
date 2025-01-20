package br.com.instivo.salarytime.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request of an employee.")
public class EmployeeRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8016415132600200219L;

    @NotBlank(message = "Name is required.")
    @Size(max = 150, message = "Name must not exceed 150 characters.")
    private String name;

    @NotNull(message = "Admission date is required.")
    @PastOrPresent(message = "Admission date must be today or in the past.")
    private LocalDate admissionDate;

    @NotNull(message = "Gross salary is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross salary must be greater than zero.")
    private BigDecimal grossSalary;
}
