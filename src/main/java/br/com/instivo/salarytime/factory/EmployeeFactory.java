package br.com.instivo.salarytime.factory;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.model.DateDifference;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.SalaryCalculatorService;
import br.com.instivo.salarytime.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class EmployeeFactory {

    private final SalaryCalculatorService salaryCalculatorService;

    public PageDto<EmployeeResponseDTO> buildEmployeeResponsePageDto(Page<Employee> employee) {
        return PageDto.<EmployeeResponseDTO>builder()
                .content(employee.map(this::buildEmployeeResponseDTO).toList())
                .totalPages(employee.getTotalPages())
                .currentPageNumber(employee.getNumber())
                .numberOfElements(employee.getNumberOfElements())
                .totalElements(employee.getTotalElements())
                .build();
    }

    public EmployeeResponseDTO buildEmployeeResponseDTO(Employee employee) {
        DateDifference dateDifference = DateUtils.calculateDifference(employee.getAdmissionDate());
        BigDecimal calculatedPercentage = salaryCalculatorService.calculatePercentage(employee.getGrossSalary());

        return EmployeeResponseDTO.builder()
                .id(employee.getId().toString())
                .name(employee.getName())
                .admissionDate(employee.getAdmissionDate())
                .yearsPassed(dateDifference.getYears())
                .monthsPassed(dateDifference.getMonths())
                .daysPassed(dateDifference.getDays())
                .grossSalary(employee.getGrossSalary())
                .calculatedPercentage(calculatedPercentage)
                .build();
    }

    public Employee buildEmployee(EmployeeRequestDTO employeeRequestDTO) {
        return Employee.builder()
                .name(employeeRequestDTO.getName())
                .admissionDate(employeeRequestDTO.getAdmissionDate())
                .grossSalary(employeeRequestDTO.getGrossSalary())
                .build();
    }
}
