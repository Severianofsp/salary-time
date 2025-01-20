package br.com.instivo.salarytime.service;

import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponseDTO findById(String id);

    void save(EmployeeRequestDTO employeeRequestDTO);

    PageDto<EmployeeResponseDTO> findAll(EmployeeFilterDTO filter, Pageable pageable);
}
