package br.com.instivo.salarytime.controller.swagger;

import br.com.instivo.salarytime.model.dto.EmployeeResponseDTO;
import br.com.instivo.salarytime.model.dto.PageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;

@Tag(name = "Employee Controller", description = "Endpoints for managing employees.")
public interface EmployeeSwagger {

    @Operation(
            summary = "Retrieve a paginated list of employees",
            description = "This endpoint returns a paginated list of employees, including details such as admission date, gross salary, percentage calculation, and time passed since admission.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Paginated list of employees retrieved successfully.",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = EmployeeResponseDTO.class))
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request. Check query parameters.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal server error.",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )})
    ResponseEntity<PageDto<EmployeeResponseDTO>> findAll(Pageable pageable);
}

