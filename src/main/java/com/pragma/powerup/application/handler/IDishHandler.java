package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishListRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishListResponseDto;
import com.pragma.powerup.domain.model.Pagination;

public interface IDishHandler {
    void createDish(DishRequestDto dishRequestDto);
    void updateDish(Long id,DishUpdateRequestDto dishUpdateRequestDto);
    void changeDishStatus(DishStatusDto dishStatusDto);
    Pagination<DishListResponseDto> listDishes(DishListRequestDto dishListRequestDto);
}
