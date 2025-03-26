package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.application.dto.request.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.request.NotificationRequestDto;
import com.pragma.powerup.domain.exception.CustomValidationException;
import com.pragma.powerup.domain.spi.INotificationPersistencePort;
import com.pragma.powerup.domain.utils.constants.OrderUseCaseConstants;
import com.pragma.powerup.infrastructure.out.feign.INotificationFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationJpaAdapter implements INotificationPersistencePort {
    private final INotificationFeignClient notificationFeignClient;

    @Override
    public void saveNotification(Long idOrder, String phoneNumber) {
        notificationFeignClient.sendNotification(new NotificationRequestDto(idOrder, phoneNumber));
    }

    @Override
    public void validatePin(Long idOrder, String phoneNumber, String pin) {
        DeliverOrderRequestDto requestDto = new DeliverOrderRequestDto();
        requestDto.setIdOrder(idOrder);
        requestDto.setPhoneNumber(phoneNumber);
        requestDto.setPin(pin);
        try {
            notificationFeignClient.validatePin(requestDto);
        } catch (Exception e) {
            throw new CustomValidationException(OrderUseCaseConstants.INVALID_PIN);
        }
    }
}
