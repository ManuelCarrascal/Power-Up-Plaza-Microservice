package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.out.feign.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.repository.IEmployeeRepository;
import com.pragma.powerup.infrastructure.security.service.UserDetailImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserFeignClient userFeignClient;
    private final IEmployeeRepository employeeRepository;

    @Override
    public boolean isOwner(Long ownerId) {
        return  userFeignClient.findOwnerById(ownerId);
    }

    @Override
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImpl user = (UserDetailImpl) authentication.getPrincipal();
        return  user.getId();
    }

    @Override
    public boolean isEmployeeOfRestaurant(Long employeeId, Long restaurantId) {
        return employeeRepository.existsByUserIdAndRestaurantId(employeeId, restaurantId);
    }

    @Override
    public String getPhoneNumberById(Long userId) {
        return userFeignClient.getPhoneNumberById(userId);

    }

}
