package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderEntityMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "idEmployee", source = "idEmployee")
    Order toOrder(OrderEntity orderEntity);

    @Mapping(target = "restaurant.id", source = "restaurantId")
    @Mapping(target = "idEmployee", source = "idEmployee")
    OrderEntity toEntity(Order order);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "idEmployee", source = "idEmployee")
    @Mapping(target = "dishes", source = "orderDishes")
    List<Order> toOrderList(List<OrderEntity> orderEntities);
}