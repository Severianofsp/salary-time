package br.com.instivo.salarytime.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Page response wrapper")
public class PageDto<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3797019036360981578L;

    @Schema(description = "Content of the page")
    private List<T> content;

    @Schema(description = "Number of elements in this page", example = "10")
    private int numberOfElements;

    @Schema(description = "Total number of elements", example = "100")
    private long totalElements;

    @Schema(description = "Total number of pages", example = "10")
    private int totalPages;

    @Schema(description = "Current page number", example = "1")
    private int currentPageNumber;
}
