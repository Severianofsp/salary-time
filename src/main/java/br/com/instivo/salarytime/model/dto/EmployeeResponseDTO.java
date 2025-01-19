package br.com.instivo.salarytime.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Detailed representation of an employee.")
public class EmployeeResponseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6876807542360424104L;

    @Schema(description = "Years passed since the employee's admission.", example = "3")
    private int yearsPassed;

    @Schema(description = "Months passed since the employee's admission.", example = "5")
    private int monthsPassed;

    @Schema(description = "Days passed since the employee's admission.", example = "20")
    private int daysPassed;

    @Schema(description = "Employee's gross salary.", example = "5000.00")
    private BigDecimal grossSalary;

    @Schema(description = "35% of the employee's gross salary.", example = "1750.00")
    private BigDecimal calculatedPercentage;
}
