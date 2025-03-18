package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "idRestaurant", source = "dishRequestDto.idRestaurant")
    @Mapping(target = "categories", expression = "java(categoriesToList(dishRequestDto.getCategories()))")
    @Mapping(target = "active", ignore = true)
    Dish toDish(DishRequestDto dishRequestDto);

    default List<Category> categoriesToList(List<String> categoryNames) {
        if (categoryNames == null) return new ArrayList<>();
        return categoryNames.stream()
                .map(name -> {
                    Category category = new Category();
                    category.setName(name);
                    return category;
                })
                .collect(Collectors.toList());
    }
}