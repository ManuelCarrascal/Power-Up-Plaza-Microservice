package com.pragma.powerup.application.utils.constants.openapi;

public class OpenApiPaginationResponseDto {
    private OpenApiPaginationResponseDto() {}

    public static final String DTO_NAME = "PaginationResponseDto";
    public static final String DTO_DESCRIPTION = "DTO for pagination information";

    public static final String CURRENT_PAGE_DESCRIPTION = "Current page number (zero-based)";
    public static final String TOTAL_PAGES_DESCRIPTION = "Total number of pages";
    public static final String TOTAL_ELEMENTS_DESCRIPTION = "Total number of elements";
    public static final String ELEMENTS_PER_PAGE_DESCRIPTION = "Number of elements per page";
    public static final String CONTENT_DESCRIPTION = "Content of the paginated data";

}