package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.model.Restaurant;
import com.pragma.powerup.domain.spi.IRestaurantPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.EmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IEmployeeRepository;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantRepository;
import com.pragma.powerup.infrastructure.utils.constants.RestaurantJpaAdapterConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Pagination<Restaurant> listRestaurants(String orderDirection, Integer currentPage, Integer limitForPage) {
        Sort sort = Sort.by(Sort.Direction.valueOf(orderDirection), RestaurantJpaAdapterConstants.RESTAURANT_NAME_FIELD);
        Pageable pageable = PageRequest.of(currentPage, limitForPage, sort);

        Page<RestaurantEntity> restaurantPage = restaurantRepository.findAll(pageable);

        List<Restaurant> restaurants = restaurantPage.getContent()
                .stream()
                .map(restaurantEntityMapper::toModel)
                .collect(Collectors.toList());

        return new Pagination<>(
                orderDirection.equals(RestaurantJpaAdapterConstants.ORDER_ASCENDING),
                restaurantPage.getNumber(),
                restaurantPage.getTotalPages(),
                restaurantPage.getTotalElements(),
                restaurants
        );
    }

}
