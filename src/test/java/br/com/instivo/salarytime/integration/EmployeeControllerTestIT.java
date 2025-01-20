package br.com.instivo.salarytime.integration;

import br.com.instivo.salarytime.controller.EmployeeController;
import br.com.instivo.salarytime.model.dto.EmployeeRequestDTO;
import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import br.com.instivo.salarytime.service.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static br.com.instivo.salarytime.builder.EmployeeRequestDTOBuilder.buildEmployeeRequestDTODefault;
import static br.com.instivo.salarytime.builder.EmployeeResponseDTOBuilder.buildEmployeeResponseDTODefault;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private EmployeeService employeeService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void findAll_shouldReturnPageOfEmployees() throws Exception {
        // Data
        PageDto<EmployeeResponseDTO> pageDto = buildEmployeeResponseDTODefault().toPageDto();

        when(employeeService.findAll(any(), any())).thenReturn(pageDto);

        // Action & Result
        mockMvc.perform(get("/api/v1/employees")
                        .param("page", "0")
                        .param("size", "20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    void findById_shouldReturnEmployeeResponseDTO() throws Exception {
        // Data
        String id = "123";
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
        when(employeeService.findById(id)).thenReturn(employeeResponseDTO);

        // Action & Result
        mockMvc.perform(get("/api/v1/employees/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    void register_shouldReturnCreatedStatus() throws Exception {
        // Data
        EmployeeRequestDTO employeeRequestDTO = buildEmployeeRequestDTODefault().build();

        // Action & Result
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString(employeeRequestDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void register_shouldReturnErrorResponse() throws Exception {
        // Action & Result
        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValueAsString(new EmployeeRequestDTO())))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.code").value("422"))
                .andExpect(jsonPath("$.errors").isArray());
    }

    private String writeValueAsString(Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(value);
    }
}
