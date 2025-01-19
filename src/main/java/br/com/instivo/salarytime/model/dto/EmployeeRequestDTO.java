package br.com.instivo.salarytime.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 8016415132600200219L;

    @NotNull(message = "Admission date is required.")
    @PastOrPresent(message = "Admission date must be today or in the past.")
    private LocalDate admissionDate;

    @NotNull(message = "Gross salary is required.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Gross salary must be greater than zero.")
    private BigDecimal grossSalary;
}
