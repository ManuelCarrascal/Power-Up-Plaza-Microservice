package com.pragma.powerup.infrastructure.out.feign;


import com.pragma.powerup.application.dto.request.OrderTraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.OrderTraceabilityResponse;
import com.pragma.powerup.infrastructure.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="power-up-traceability-microservice", url = "http://localhost:8084", configuration = FeignConfig.class)
public interface ITraceabilityFeignClient {
    @PostMapping("/api/v1/traceability")
    ResponseEntity<Void> createOrderTraceability(@RequestBody OrderTraceabilityRequestDto orderTraceabilityRequestDto);

    @GetMapping("/api/v1/traceability/client/history")
    ResponseEntity<List<OrderTraceabilityResponse>> getClientOrderHistory();
}