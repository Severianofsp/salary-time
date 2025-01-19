package br.com.instivo.salarytime.service;

import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    PageDto<EmployeeResponseDTO> findall(Pageable pageable);

    EmployeeResponseDTO findbyId(String id);

    void save(EmployeeRequestDTO employeeRequestDTO);
}
