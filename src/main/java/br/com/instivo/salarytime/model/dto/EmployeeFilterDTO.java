package br.com.instivo.salarytime.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Filter object for searching employees")
public class EmployeeFilterDTO {

    @Schema(description = "Employee's name for filtering", example = "John Doe")
    private String name;

    @Schema(description = "Start date for the admission period filter", example = "2023-01-01")
    private LocalDate startAdmissionPeriodDate;

    @Schema(description = "End date for the admission period filter", example = "2023-12-31")
    private LocalDate endAdmissionPeriodDate;

    @Schema(description = "Minimum gross salary for filtering", example = "3000.00")
    private BigDecimal grossSalaryGreaterThan;

    @Schema(description = "Maximum gross salary for filtering", example = "10000.00")
    private BigDecimal grossSalaryLessThan;
}
