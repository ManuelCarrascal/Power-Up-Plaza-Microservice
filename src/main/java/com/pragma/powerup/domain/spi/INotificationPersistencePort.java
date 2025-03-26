package com.pragma.powerup.domain.spi;


public interface INotificationPersistencePort {
    void saveNotification(Long idOrder, String phoneNumber);
    void validatePin(Long idOrder, String phoneNumber, String pin);

}
