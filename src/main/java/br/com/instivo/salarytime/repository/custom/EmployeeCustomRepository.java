package br.com.instivo.salarytime.repository.custom;

import br.com.instivo.salarytime.domain.Employee;
import br.com.instivo.salarytime.model.dto.EmployeeFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeCustomRepository {

    Page<Employee> findAllWithFilters(EmployeeFilterDTO filter, Pageable pageable);
}
