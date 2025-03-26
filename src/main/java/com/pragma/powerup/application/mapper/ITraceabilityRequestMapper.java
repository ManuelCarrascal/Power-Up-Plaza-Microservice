package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderDishRequest;
import com.pragma.powerup.domain.model.OrderDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ITraceabilityRequestMapper {
    @Mapping(target = "idDish", source = "dishId")
    OrderDishRequest toOrderDishRequest(OrderDish orderDish);

    List<OrderDishRequest> toOrderDishRequestList(List<OrderDish> orderDishes);
}