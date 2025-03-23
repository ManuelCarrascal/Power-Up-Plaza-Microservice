package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishListRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishListResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishUpdateRequestMapper;
import com.pragma.powerup.application.mapper.IPaginationDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.model.Pagination;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishUpdateRequestMapper dishUpdateRequestMapper;
    private final IPaginationDishResponseMapper paginationDishResponseMapper;

    @Override
    public void createDish(DishRequestDto dishRequestDto) {
        dishServicePort.createDish(dishRequestMapper.toDish(dishRequestDto));
    }

    @Override
    public void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        dishServicePort.updateDish(id,dishUpdateRequestMapper.toDish(dishUpdateRequestDto));
    }

    @Override
    public void changeDishStatus(DishStatusDto dishStatusDto) {
        dishServicePort.changeDishStatus(dishStatusDto.getDishId(),dishStatusDto.getStatus());
    }

    @Override
    public Pagination<DishListResponseDto> listDishes(DishListRequestDto requestDto) {
        return paginationDishResponseMapper.toPaginationResponse(
                dishServicePort.listDishes(
                        requestDto.getIdRestaurant(),
                        requestDto.getIdCategory(),
                        requestDto.getActive(),
                        requestDto.getOrderDirection(),
                        requestDto.getCurrentPage(),
                        requestDto.getLimitForPage()
                )
        );
    }

}
