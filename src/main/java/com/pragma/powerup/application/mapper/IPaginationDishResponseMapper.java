package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.DishListResponseDto;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaginationDishResponseMapper {
    Pagination<DishListResponseDto> toPaginationResponse(Pagination<Dish> pagination);

    @Mapping(source = "categories", target = "categories", qualifiedByName = "categoriesToStrings")
    DishListResponseDto toDishListResponseDto(Dish dish);

    @Named("categoriesToStrings")
    default List<String> categoriesToStrings(List<Category> categories) {
        if (categories == null) {
            return Collections.emptyList();
        }
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toList());
    }
}
