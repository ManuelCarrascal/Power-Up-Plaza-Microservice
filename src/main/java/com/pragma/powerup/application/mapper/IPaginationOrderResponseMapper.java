package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.domain.model.Order;
import com.pragma.powerup.domain.model.Pagination;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPaginationOrderResponseMapper {
    OrderListResponseDto toResponseDto(Order order);
    List<OrderListResponseDto> toResponseDtoList(List<Order> orders);
    Pagination<OrderListResponseDto> toPaginationResponse(Pagination<Order> pagination);
}