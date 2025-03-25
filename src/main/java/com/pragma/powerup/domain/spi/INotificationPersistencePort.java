package com.pragma.powerup.domain.spi;


public interface INotificationPersistencePort {
    void saveNotification(Long idOrder, String phoneNumber);
}
