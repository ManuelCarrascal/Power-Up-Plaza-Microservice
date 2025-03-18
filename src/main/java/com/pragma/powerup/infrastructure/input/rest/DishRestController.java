package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiDishRestControllerConstants;
import com.pragma.powerup.infrastructure.utils.constants.openapi.ResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Tag( name = OpenApiDishRestControllerConstants.CONTROLLER_TAG , description = OpenApiDishRestControllerConstants.CONTROLLER_DESCRIPTION)
public class DishRestController {

    private final IDishHandler dishHandler;

    @PostMapping
    @Operation(summary = OpenApiDishRestControllerConstants.CREATE_DISH_SUMMARY,
            description = OpenApiDishRestControllerConstants.CREATE_DISH_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK, description = OpenApiDishRestControllerConstants.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST, description = OpenApiDishRestControllerConstants.RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR, description = OpenApiDishRestControllerConstants.RESPONSE_500_DESCRIPTION)
    })
    public void createDish(@Valid @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.createDish(dishRequestDto);
    }
}