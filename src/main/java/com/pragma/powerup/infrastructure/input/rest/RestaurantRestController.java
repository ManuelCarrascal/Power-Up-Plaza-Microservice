package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantListRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.PaginationResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantListResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.infrastructure.exceptionhandler.ErrorResponse;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiRestaurantRestControllerConstants;
import com.pragma.powerup.infrastructure.utils.constants.openapi.ResponseCodes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
@Tag(name = OpenApiRestaurantRestControllerConstants.TAG_NAME,
        description = OpenApiRestaurantRestControllerConstants.TAG_DESCRIPTION)
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = OpenApiRestaurantRestControllerConstants.OPERATION_SUMMARY,
            description = OpenApiRestaurantRestControllerConstants.OPERATION_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiRestaurantRestControllerConstants.RESPONSE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiRestaurantRestControllerConstants.RESPONSE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND,
                    description = OpenApiRestaurantRestControllerConstants.RESPONSE_404_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.createRestaurant(restaurantRequestDto);
    }

    @Operation(summary = OpenApiRestaurantRestControllerConstants.CREATE_EMPLOYEE_SUMMARY,
            description = OpenApiRestaurantRestControllerConstants.CREATE_EMPLOYEE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_CREATED,
                    description = OpenApiRestaurantRestControllerConstants.CREATE_EMPLOYEE_201_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiRestaurantRestControllerConstants.CREATE_EMPLOYEE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND,
                    description = OpenApiRestaurantRestControllerConstants.CREATE_EMPLOYEE_404_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_UNAUTHORIZED,
                    description = OpenApiRestaurantRestControllerConstants.RESPONSE_401_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/create-employee")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<Object> createEmployee(
            @Parameter(description = OpenApiRestaurantRestControllerConstants.PARAM_USER_ID_DESCRIPTION)
            @RequestParam Long userId,
            @Parameter(description = OpenApiRestaurantRestControllerConstants.PARAM_RESTAURANT_ID_DESCRIPTION)
            @RequestParam Long restaurantId) {
        restaurantHandler.createEmployee(userId, restaurantId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = OpenApiRestaurantRestControllerConstants.LIST_RESTAURANTS_SUMMARY,
            description = OpenApiRestaurantRestControllerConstants.LIST_RESTAURANTS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiRestaurantRestControllerConstants.LIST_RESTAURANTS_200_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = PaginationResponseDto.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiRestaurantRestControllerConstants.LIST_RESTAURANTS_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<Pagination<RestaurantListResponseDto>> restaurantList(
            @Valid RestaurantListRequestDto restaurantListRequestDto) {
        Pagination<RestaurantListResponseDto> response = restaurantHandler.restaurantList(restaurantListRequestDto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = OpenApiRestaurantRestControllerConstants.IS_OWNER_SUMMARY,
            description = OpenApiRestaurantRestControllerConstants.IS_OWNER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiRestaurantRestControllerConstants.IS_OWNER_200_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiRestaurantRestControllerConstants.IS_OWNER_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/is-owner")
    public ResponseEntity<Boolean> isOwnerOfRestaurant(
            @Parameter(description = OpenApiRestaurantRestControllerConstants.PARAM_OWNER_ID_DESCRIPTION)
            @RequestParam Long ownerId,
            @Parameter(description = OpenApiRestaurantRestControllerConstants.PARAM_RESTAURANT_CHECK_DESCRIPTION)
            @RequestParam Long restaurantId) {
        boolean isOwner = restaurantHandler.isOwnerOfRestaurant(ownerId, restaurantId);
        return ResponseEntity.ok(isOwner);
    }
}