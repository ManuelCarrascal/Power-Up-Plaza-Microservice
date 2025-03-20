package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.feign.IUserFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;
    @Override
    public boolean isOwner(Long ownerId) {
        return  userFeignClient.findOwnerById(ownerId);
    }

}
