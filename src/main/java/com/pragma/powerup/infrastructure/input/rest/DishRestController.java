package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishListRequestDto;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishStatusDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.response.DishListResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiDishRestControllerConstants;
import com.pragma.powerup.infrastructure.utils.constants.openapi.ResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public void createDish(@Valid @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.createDish(dishRequestDto);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    @Operation(
            summary = OpenApiDishRestControllerConstants.UPDATE_DISH_SUMMARY,
            description = OpenApiDishRestControllerConstants.UPDATE_DISH_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK, description = OpenApiDishRestControllerConstants.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST, description = OpenApiDishRestControllerConstants.RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND, description = OpenApiDishRestControllerConstants.RESPONSE_404_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR, description = OpenApiDishRestControllerConstants.RESPONSE_500_DESCRIPTION)
    })

    public void updateDish(@PathVariable Long id, @Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto) {
        dishHandler.updateDish(id, dishUpdateRequestDto);
    }

    @PatchMapping("/status")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    @Operation(
            summary = OpenApiDishRestControllerConstants.CHANGE_DISH_STATUS_SUMMARY,
            description = OpenApiDishRestControllerConstants.CHANGE_DISH_STATUS_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK, description = OpenApiDishRestControllerConstants.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST, description = OpenApiDishRestControllerConstants.RESPONSE_400_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND, description = OpenApiDishRestControllerConstants.RESPONSE_404_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR, description = OpenApiDishRestControllerConstants.RESPONSE_500_DESCRIPTION)
    })
    public void changeDishStatus(@Valid @RequestBody DishStatusDto dishStatusDto) {
        dishHandler.changeDishStatus(dishStatusDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @Operation(
            summary = OpenApiDishRestControllerConstants.LIST_DISHES_SUMMARY,
            description = OpenApiDishRestControllerConstants.LIST_DISHES_DESCRIPTION
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiDishRestControllerConstants.LIST_DISHES_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiDishRestControllerConstants.LIST_DISHES_400_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                    description = OpenApiDishRestControllerConstants.RESPONSE_500_DESCRIPTION)
    })
    public ResponseEntity<Pagination<DishListResponseDto>> dishesList(@Valid DishListRequestDto dishListRequestDto) {
        Pagination<DishListResponseDto> response = dishHandler.listDishes(dishListRequestDto);
        return ResponseEntity.ok(response);
    }


}