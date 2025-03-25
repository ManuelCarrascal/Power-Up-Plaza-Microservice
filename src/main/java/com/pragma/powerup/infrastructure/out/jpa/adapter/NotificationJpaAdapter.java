package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.NotificationRequestDto;
import com.pragma.powerup.domain.spi.INotificationPersistencePort;
import com.pragma.powerup.infrastructure.out.feign.INotificationFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationJpaAdapter implements INotificationPersistencePort {
    private final INotificationFeignClient notificationFeignClient;

    @Override
    public void saveNotification(Long idOrder, String phoneNumber) {
        notificationFeignClient.sendNotification(new NotificationRequestDto(idOrder, phoneNumber));
    }
}
