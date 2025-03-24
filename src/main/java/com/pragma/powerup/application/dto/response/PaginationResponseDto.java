package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.application.utils.constants.openapi.OpenApiPaginationResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = OpenApiPaginationResponseDto.DTO_NAME,
        description = OpenApiPaginationResponseDto.DTO_DESCRIPTION)
public class PaginationResponseDto<T> {
    @Schema(description = OpenApiPaginationResponseDto.CURRENT_PAGE_DESCRIPTION)
    private Integer currentPage;

    @Schema(description = OpenApiPaginationResponseDto.TOTAL_PAGES_DESCRIPTION)
    private Integer totalPages;

    @Schema(description = OpenApiPaginationResponseDto.TOTAL_ELEMENTS_DESCRIPTION)
    private Long totalElements;

    @Schema(description = OpenApiPaginationResponseDto.ELEMENTS_PER_PAGE_DESCRIPTION)
    private Integer elementsPerPage;

    @Schema(description = OpenApiPaginationResponseDto.CONTENT_DESCRIPTION)
    private List<T> content;
}