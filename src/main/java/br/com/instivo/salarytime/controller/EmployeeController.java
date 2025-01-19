package br.com.instivo.salarytime.controller;

import br.com.instivo.salarytime.controller.swagger.EmployeeSwagger;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeSwagger {

    private final EmployeeService employeeService;

    public ResponseEntity<PageDto<EmployeeResponseDTO>> findAll(@Parameter @PageableDefault(size = 20) Pageable pageable) {
        PageDto<EmployeeResponseDTO> employeeResponseDTOS = employeeService.findall(pageable);
        return ResponseEntity.ok(employeeResponseDTOS);
    }
}
