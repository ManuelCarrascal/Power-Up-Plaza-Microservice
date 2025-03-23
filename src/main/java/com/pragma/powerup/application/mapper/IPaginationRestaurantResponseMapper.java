package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaginationRestaurantResponseMapper {
    Pagination<RestaurantListResponseDto> toPaginationResponse(Pagination<Restaurant> pagination);

}
