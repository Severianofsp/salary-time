package br.com.instivo.salarytime.builder;

import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeRequestDTOBuilder {

    private EmployeeRequestDTO employeeRequestDTO;

    public static EmployeeRequestDTOBuilder buildEmployeeRequestDTODefault() {
        EmployeeRequestDTOBuilder builder = new EmployeeRequestDTOBuilder();
        builder.employeeRequestDTO = EmployeeRequestDTO.builder()
                .name("John")
                .grossSalary(BigDecimal.valueOf(10000))
                .admissionDate(LocalDate.now())
                .build();
        return builder;
    }

    public EmployeeRequestDTO build() {
        return employeeRequestDTO;
    }
}
