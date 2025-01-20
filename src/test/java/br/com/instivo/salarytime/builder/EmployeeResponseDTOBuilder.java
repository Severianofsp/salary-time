package br.com.instivo.salarytime.builder;

import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeResponseDTOBuilder {

    private EmployeeResponseDTO employeeResponseDTO;

    public static EmployeeResponseDTOBuilder buildEmployeeResponseDTODefault() {
        EmployeeResponseDTOBuilder builder = new EmployeeResponseDTOBuilder();
        builder.employeeResponseDTO = EmployeeResponseDTO.builder()
                .id("6ccaafd2e95b90f6baaa3f9e")
                .yearsPassed(0)
                .monthsPassed(0)
                .daysPassed(0)
                .grossSalary(BigDecimal.valueOf(10000))
                .calculatedPercentage(BigDecimal.valueOf(350))
                .build();
        return builder;
    }

    public PageDto<EmployeeResponseDTO> toPageDto() {
        return PageDto.<EmployeeResponseDTO>builder()
                .content(List.of(employeeResponseDTO))
                .numberOfElements(1)
                .totalElements(1)
                .totalPages(1)
                .build();
    }

    public EmployeeResponseDTO build() {
        return employeeResponseDTO;
    }
}
