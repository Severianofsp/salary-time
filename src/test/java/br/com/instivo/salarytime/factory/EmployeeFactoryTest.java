package br.com.instivo.salarytime.factory;


import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.SalaryCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

import static br.com.instivo.salarytime.builder.EmployeeBuilder.buildEmployeeDefault;
import static br.com.instivo.salarytime.builder.EmployeeRequestDTOBuilder.buildEmployeeRequestDTODefault;
import static br.com.instivo.salarytime.builder.EmployeeResponseDTOBuilder.buildEmployeeResponseDTODefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeFactoryTest {

    @InjectMocks
    private EmployeeFactory employeeFactory;

    @Mock
    private SalaryCalculatorService salaryCalculatorService;

    @Test
    void buildEmployeeResponsePageDto_shouldReturnPageDto() {
        // Data
        Page<Employee> employeesPage = buildEmployeeDefault().toPage();
        PageDto<EmployeeResponseDTO> expectedPageDto = buildEmployeeResponseDTODefault().toPageDto();

        when(salaryCalculatorService.calculatePercentage(employeesPage.getContent().get(0).getGrossSalary()))
                .thenReturn(BigDecimal.valueOf(350));

        // Action
        PageDto<EmployeeResponseDTO> result = employeeFactory.buildEmployeeResponsePageDto(employeesPage);

        // Result
        assertThat(result).isEqualTo(expectedPageDto);
    }

    @Test
    void buildEmployeeResponseDTO_shouldReturnEmployeeResponseDTO() {
        // Data
        Employee employee = buildEmployeeDefault().build();
        EmployeeResponseDTO expectedEmployeeResponseDTO = buildEmployeeResponseDTODefault().build();

        when(salaryCalculatorService.calculatePercentage(employee.getGrossSalary())).thenReturn(BigDecimal.valueOf(350));

        // Action
        EmployeeResponseDTO result = employeeFactory.buildEmployeeResponseDTO(employee);

        // Result
        assertThat(result).isEqualTo(expectedEmployeeResponseDTO);
    }

    @Test
    void buildEmployee_shouldReturnEmployee() {
        // Data
        EmployeeRequestDTO employeeRequestDTO = buildEmployeeRequestDTODefault().build();
        Employee expectedEmployee = buildEmployeeDefault().withoutId().build();

        // Action
        Employee result = employeeFactory.buildEmployee(employeeRequestDTO);

        // Result
        assertThat(result).isEqualTo(expectedEmployee);
    }
}
