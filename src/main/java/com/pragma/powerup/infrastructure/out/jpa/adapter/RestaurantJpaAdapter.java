package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IEmployeeRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.utils.constants.RestaurantJpaAdapterConstants;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IEmployeeRepository employeeRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void createRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantEntityMapper.toEntity(restaurant);
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public Optional<Restaurant> findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .map(restaurantEntityMapper::toModel);
    }


    @Override
    public void addEmployeeToRestaurant(Long userId, Long restaurantId) {
        RestaurantEntity restaurantEntity = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException(RestaurantJpaAdapterConstants.RESTAURANT_NOT_FOUND));

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUserId(userId);
        employeeEntity.setRestaurant(restaurantEntity);

        employeeRepository.save(employeeEntity);
    }

    @Override
    public boolean isOwnerOfRestaurant(Long ownerId, Long restaurantId) {
        return restaurantRepository.existsByIdAndIdOwner(restaurantId, ownerId);
    }


}
