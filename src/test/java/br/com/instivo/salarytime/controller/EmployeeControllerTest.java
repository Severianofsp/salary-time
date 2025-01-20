package br.com.instivo.salarytime.controller;

import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static br.com.instivo.salarytime.builder.EmployeeFilterDTOBuilder.buildEmployeeResponseDTO;
import static br.com.instivo.salarytime.builder.EmployeeRequestDTOBuilder.buildEmployeeRequestDTODefault;
import static br.com.instivo.salarytime.builder.EmployeeResponseDTOBuilder.buildEmployeeResponseDTODefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    @Test
    void findAll_shouldReturnPageOfEmployees() {
        // Data
        EmployeeFilterDTO filter = buildEmployeeResponseDTO().build();
        Pageable pageable = PageRequest.of(0, 20);
        PageDto<EmployeeResponseDTO> pageDto = buildEmployeeResponseDTODefault().toPageDto();

        when(employeeService.findAll(filter, pageable)).thenReturn(pageDto);

        // Action
        ResponseEntity<PageDto<EmployeeResponseDTO>> response = employeeController.findAll(filter, pageable);

        // Result
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(pageDto);
        verify(employeeService).findAll(filter, pageable);
    }

    @Test
    void findById_shouldReturnEmployeeResponseDTO() {
        // Data
        String id = "123";
        EmployeeResponseDTO employeeResponseDTO = buildEmployeeResponseDTODefault().build();

        when(employeeService.findById(id)).thenReturn(employeeResponseDTO);

        // Action
        ResponseEntity<EmployeeResponseDTO> response = employeeController.findById(id);

        // Result
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(employeeResponseDTO);
        verify(employeeService).findById(id);
    }

    @Test
    void register_shouldReturnCreatedStatus() {
        // Data
        EmployeeRequestDTO employeeRequestDTO = buildEmployeeRequestDTODefault().build();

        // Action
        ResponseEntity<Void> response = employeeController.register(employeeRequestDTO);

        // Result
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        verify(employeeService).save(employeeRequestDTO);
    }

}
