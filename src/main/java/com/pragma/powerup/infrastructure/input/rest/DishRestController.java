package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @PostMapping
    public void createDish( @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.createDish(dishRequestDto);
    }
}
