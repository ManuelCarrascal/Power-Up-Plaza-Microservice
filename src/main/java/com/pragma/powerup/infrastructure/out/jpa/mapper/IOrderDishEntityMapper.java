package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderDish;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishEntityMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dish.id", source = "dishId")
    @Mapping(target = "order", ignore = true)
    OrderDishEntity toEntity(OrderDish orderDish);

    List<OrderDishEntity> toEntityList(List<OrderDish> orderDishes);
}