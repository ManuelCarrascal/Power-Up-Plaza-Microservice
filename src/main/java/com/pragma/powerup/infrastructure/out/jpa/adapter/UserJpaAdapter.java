package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.feign.IUserFeignClient;

public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;

    public UserJpaAdapter(IUserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public boolean isOwner(Long ownerId) {
        return  userFeignClient.findOwnerById(ownerId);
    }

}
