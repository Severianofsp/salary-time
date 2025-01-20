package br.com.instivo.salarytime.service;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.exception.EmployeeNotFoundException;
import br.com.instivo.salarytime.factory.EmployeeFactory;
import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.repository.EmployeeRepository;
import br.com.instivo.salarytime.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static br.com.instivo.salarytime.builder.EmployeeBuilder.buildEmployeeDefault;
import static br.com.instivo.salarytime.builder.EmployeeFilterDTOBuilder.buildEmployeeResponseDTO;
import static br.com.instivo.salarytime.builder.EmployeeRequestDTOBuilder.buildEmployeeRequestDTODefault;
import static br.com.instivo.salarytime.builder.EmployeeResponseDTOBuilder.buildEmployeeResponseDTODefault;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeFactory employeeFactory;

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    void findAll_shouldReturnPageOfEmployees() {
        // Data
        EmployeeFilterDTO filter = buildEmployeeResponseDTO().build();
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> employees = buildEmployeeDefault().toPage();
        PageDto<EmployeeResponseDTO> expectedPageDto = buildEmployeeResponseDTODefault().toPageDto();

        when(employeeRepository.findAllWithFilters(filter, pageable)).thenReturn(employees);
        when(employeeFactory.buildEmployeeResponsePageDto(employees)).thenReturn(expectedPageDto);

        // Action
        PageDto<EmployeeResponseDTO> result = employeeService.findAll(filter, pageable);

        // Result
        assertEquals(expectedPageDto, result);
        verify(employeeRepository).findAllWithFilters(filter, pageable);
        verify(employeeFactory).buildEmployeeResponsePageDto(employees);
    }

    @Test
    void findById_shouldReturnEmployeeResponseDTO() {
        // Data
        String employeeId = "123";
        Employee employee = buildEmployeeDefault().build();
        EmployeeResponseDTO expectedDto = new EmployeeResponseDTO();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(employeeFactory.buildEmployeeResponseDTO(employee)).thenReturn(expectedDto);

        // Action
        EmployeeResponseDTO result = employeeService.findById(employeeId);

        // Result
        assertEquals(expectedDto, result);
        verify(employeeRepository).findById(employeeId);
        verify(employeeFactory).buildEmployeeResponseDTO(employee);
    }

    @Test
    void findById_shouldThrowEmployeeNotFoundException() {
        // Data
        String employeeId = "123";

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        // Action e Result
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findById(employeeId));
    }

    @Test
    void save_shouldSaveEmployee() {
        // Data
        EmployeeRequestDTO employeeRequestDTO = buildEmployeeRequestDTODefault().build();
        Employee employee = buildEmployeeDefault().build();

        when(employeeFactory.buildEmployee(employeeRequestDTO)).thenReturn(employee);

        // Action
        employeeService.save(employeeRequestDTO);

        // Result
        verify(employeeRepository).save(employee);
    }
}
