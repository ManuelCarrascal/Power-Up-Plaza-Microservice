package com.pragma.powerup.infrastructure.out.feign;

import com.pragma.powerup.application.dto.request.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.request.NotificationRequestDto;
import com.pragma.powerup.infrastructure.feign.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name= "power-up-notification-messaging-microservice",
        url= "http://localhost:8083",
        configuration = FeignConfig.class
)
public interface INotificationFeignClient {
    @PostMapping("api/v1/notification")
    void sendNotification(@RequestBody NotificationRequestDto notificationRequestDto);

    @PostMapping("/api/v1/notification/deliver-order")
    ResponseEntity<Void> validatePin(@RequestBody DeliverOrderRequestDto request);


}
