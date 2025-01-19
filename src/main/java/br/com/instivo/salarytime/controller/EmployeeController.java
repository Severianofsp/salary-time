package br.com.instivo.salarytime.controller;

import br.com.instivo.salarytime.controller.swagger.EmployeeSwagger;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeSwagger {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<PageDto<EmployeeResponseDTO>> findAll(@Parameter @PageableDefault(size = 20) Pageable pageable) {
        PageDto<EmployeeResponseDTO> employeeResponseDTOS = employeeService.findall(pageable);
        return new ResponseEntity<>(employeeResponseDTOS, OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> findById(@PathVariable("id") String id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.findById(id);
        return new ResponseEntity<>(employeeResponseDTO, OK);
    }

    @PostMapping
    public ResponseEntity<Void> register(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        employeeService.save(employeeRequestDTO);
        return new ResponseEntity<>(CREATED);
    }
}
