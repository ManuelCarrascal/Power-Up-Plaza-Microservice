package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.infrastructure.exceptionhandler.ErrorResponse;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiRestaurantRestControllerConstants;
import com.pragma.powerup.infrastructure.utils.constants.openapi.ResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
@Tag(name = OpenApiRestaurantRestControllerConstants.TAG_NAME, description = OpenApiRestaurantRestControllerConstants.TAG_DESCRIPTION)
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = OpenApiRestaurantRestControllerConstants.OPERATION_SUMMARY,
            description = OpenApiRestaurantRestControllerConstants.OPERATION_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK, description = OpenApiRestaurantRestControllerConstants.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST, description = OpenApiRestaurantRestControllerConstants.RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND, description = OpenApiRestaurantRestControllerConstants.RESPONSE_404_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping
    public void createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
         restaurantHandler.createRestaurant(restaurantRequestDto);
    }


    @PostMapping("/create-employee")
    public ResponseEntity<Object> createEmployee(@RequestParam Long userId, @RequestParam Long restaurantId) {
        restaurantHandler.createEmployee(userId, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/is-owner")
    public ResponseEntity<Boolean> isOwnerOfRestaurant(@RequestParam Long ownerId, @RequestParam Long restaurantId) {
        boolean isOwner = restaurantHandler.isOwnerOfRestaurant(ownerId, restaurantId);
        return ResponseEntity.ok(isOwner);
    }



}
