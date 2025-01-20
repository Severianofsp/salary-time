package br.com.instivo.salarytime.service.impl;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.exception.EmployeeNotFoundException;
import br.com.instivo.salarytime.factory.EmployeeFactory;
import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.repository.EmployeeRepository;
import br.com.instivo.salarytime.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeFactory employeeFactory;
    private final EmployeeRepository employeeRepository;

    @Override
    public PageDto<EmployeeResponseDTO> findAll(EmployeeFilterDTO filter, Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllWithFilters(filter, pageable);
        return employeeFactory.buildEmployeeResponsePageDto(employees);
    }

    @Override
    public EmployeeResponseDTO findById(String id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found", NOT_FOUND));

        return employeeFactory.buildEmployeeResponseDTO(employee);
    }

    @Override
    public void save(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = employeeFactory.buildEmployee(employeeRequestDTO);
        employeeRepository.save(employee);
    }
}
