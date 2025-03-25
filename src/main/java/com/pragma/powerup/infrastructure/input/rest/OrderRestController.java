package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.AssignEmployeeRequestDto;
import com.pragma.powerup.application.dto.request.OrderListRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderListResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.infrastructure.exceptionhandler.ErrorResponse;
import com.pragma.powerup.infrastructure.utils.constants.openapi.OpenApiOrderRestController;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = OpenApiOrderRestController.TAG_NAME,
        description = OpenApiOrderRestController.TAG_DESCRIPTION)
public class OrderRestController {
    private final IOrderHandler orderHandler;

    @Operation(summary = OpenApiOrderRestController.CREATE_ORDER_SUMMARY,
            description = OpenApiOrderRestController.CREATE_ORDER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_CREATED,
                    description = OpenApiOrderRestController.CREATE_ORDER_201_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiOrderRestController.CREATE_ORDER_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_UNAUTHORIZED,
                    description = OpenApiOrderRestController.CREATE_ORDER_401_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_NOT_FOUND,
                    description = OpenApiOrderRestController.CREATE_ORDER_404_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        orderHandler.saveOrder(orderRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OpenApiOrderRestController.ORDER_CREATED_SUCCESSFULLY);
    }

    @Operation(summary = OpenApiOrderRestController.LIST_ORDERS_SUMMARY,
            description = OpenApiOrderRestController.LIST_ORDERS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiOrderRestController.LIST_ORDERS_200_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = Pagination.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiOrderRestController.LIST_ORDERS_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_UNAUTHORIZED,
                    description = OpenApiOrderRestController.LIST_ORDERS_401_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_FORBIDDEN,
                    description = OpenApiOrderRestController.LIST_ORDERS_403_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<Pagination<OrderListResponseDto>> listOrders(@Valid OrderListRequestDto orderListRequestDto) {
        return ResponseEntity.ok(orderHandler.orderList(orderListRequestDto));
    }

    @Operation(summary = OpenApiOrderRestController.ASSIGN_EMPLOYEE_SUMMARY,
            description = OpenApiOrderRestController.ASSIGN_EMPLOYEE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_OK,
                    description = OpenApiOrderRestController.ASSIGN_EMPLOYEE_200_DESCRIPTION),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_BAD_REQUEST,
                    description = OpenApiOrderRestController.ASSIGN_EMPLOYEE_400_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_UNAUTHORIZED,
                    description = OpenApiOrderRestController.ASSIGN_EMPLOYEE_401_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = ResponseCodes.RESPONSE_CODE_FORBIDDEN,
                    description = OpenApiOrderRestController.ASSIGN_EMPLOYEE_403_DESCRIPTION,
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/assign")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<String> assignEmployeeToOrder(@Valid @RequestBody AssignEmployeeRequestDto assignEmployeeRequestDto) {
        orderHandler.assignEmployee(assignEmployeeRequestDto);
        return ResponseEntity.ok(OpenApiOrderRestController.EMPLOYEE_ASSIGNED_SUCCESSFULLY);
    }

    @PostMapping("/ready")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<String> orderReady(@RequestBody AssignEmployeeRequestDto assignEmployeeRequestDto) {
        orderHandler.orderReady(assignEmployeeRequestDto);
        return ResponseEntity.ok("Order is ready");
    }
}