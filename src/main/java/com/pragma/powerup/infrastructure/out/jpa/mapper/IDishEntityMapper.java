package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IDishEntityMapper {

    @Mapping(target = "restaurant", source = "idRestaurant", qualifiedByName = "idToRestaurant")
    @Mapping(target = "categories", source = "categories")
    @Mapping(target = "price", expression = "java((int) dish.getPrice())")
    DishEntity toEntity(Dish dish);

    @Mapping(target = "idRestaurant", source = "restaurant.id")
    @Mapping(target = "price", expression = "java((double) dishEntity.getPrice())")
    Dish toDomain(DishEntity dishEntity);

    @Named("idToRestaurant")
    default RestaurantEntity idToRestaurant(Long idRestaurant) {
        if (idRestaurant == null) {
            return null;
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(idRestaurant);
        return restaurant;
    }
}


