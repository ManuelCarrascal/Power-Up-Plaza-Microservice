package com.pragma.powerup.domain.spi;

public interface IUserPersistencePort {
    boolean isOwner(Long ownerId);
}
