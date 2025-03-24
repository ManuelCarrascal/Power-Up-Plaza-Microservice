package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.application.mapper.IPaginationDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.usecase.DishUseCase;
import com.pragma.powerup.domain.usecase.OrderUseCase;
import com.pragma.powerup.domain.usecase.RestaurantUseCase;
import com.pragma.powerup.domain.utils.validators.DishValidator;
import com.pragma.powerup.domain.utils.validators.RestaurantValidator;
import com.pragma.powerup.infrastructure.out.feign.IUserFeignClient;
import com.pragma.powerup.infrastructure.out.jpa.adapter.*;
import com.pragma.powerup.infrastructure.out.jpa.mapper.*;
import com.pragma.powerup.infrastructure.out.jpa.repository.*;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IEmployeeRepository employeeRepository;
    private final IUserFeignClient userFeignClient;
    private final IOrderRepository orderRepository;
    private final IOrderDishRepository orderDishRepository;
    private final IOrderEntityMapper orderEntityMapper;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Bean
    public DishValidator dishValidator() {
        return new DishValidator();
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort(), categoryPersistencePort(), dishValidator(), restaurantPersistencePort(), userPersistencePort());
    }

    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderJpaAdapter(orderEntityMapper, orderRepository, dishRepository, orderDishRepository, orderDishEntityMapper);
    }
    @Bean
    public IOrderServicePort orderServicePort() {
        return new OrderUseCase(
                orderPersistencePort(),
                restaurantPersistencePort(),
                dishPersistencePort(),
                userPersistencePort()
        );
    }


    @Bean
    public IPaginationDishResponseMapper paginationDishResponseMapper() {
        return Mappers.getMapper(IPaginationDishResponseMapper.class);
    }

    @Bean
    public IOrderDishEntityMapper orderDishEntityMapper() {
        return Mappers.getMapper(IOrderDishEntityMapper.class);
    }

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userFeignClient);
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, employeeRepository, restaurantEntityMapper);
    }

    @Bean
    public RestaurantValidator restaurantValidator() {
        return new RestaurantValidator();
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort(), restaurantValidator(), userPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }
}