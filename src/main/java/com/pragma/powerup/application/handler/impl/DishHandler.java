package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishUpdateRequestMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DishHandler implements IDishHandler {

    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishUpdateRequestMapper dishUpdateRequestMapper;

    @Override
    public void createDish(DishRequestDto dishRequestDto) {
        dishServicePort.createDish(dishRequestMapper.toDish(dishRequestDto));
    }

    @Override
    public void updateDish(Long id, DishUpdateRequestDto dishUpdateRequestDto) {
        dishServicePort.updateDish(id,dishUpdateRequestMapper.toDish(dishUpdateRequestDto));
    }

}
