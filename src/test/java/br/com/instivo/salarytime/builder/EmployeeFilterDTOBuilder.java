package br.com.instivo.salarytime.builder;

import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;

public class EmployeeFilterDTOBuilder {

    private EmployeeFilterDTO employeeFilterDTO;

    public static EmployeeFilterDTOBuilder buildEmployeeResponseDTO() {
        EmployeeFilterDTOBuilder builder = new EmployeeFilterDTOBuilder();
        builder.employeeFilterDTO = EmployeeFilterDTO.builder()
                .name("John")
                .build();
        return builder;
    }

    public EmployeeFilterDTO build() {
        return employeeFilterDTO;
    }
}
