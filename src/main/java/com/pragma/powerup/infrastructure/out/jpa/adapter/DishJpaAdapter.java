package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.Dish;
import com.pragma.powerup.domain.model.Pagination;
import com.pragma.powerup.domain.spi.IDishPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public void createDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }

    @Override
    public Dish findDishById(Long id) {
        Optional<DishEntity> dishEntity = dishRepository.findById(id);
        return dishEntity.map(dishEntityMapper::toDomain).orElse(null);
    }

    @Override
    public void updateDish(Dish dish) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }

    @Override
    public void changeDishStatus(Dish dish, Long idOwner) {
        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }

    @Override
    public Pagination<Dish> listDishes(Long idRestaurant, Long categoryId, Boolean active,
                                       String orderDirection, Integer currentPage, Integer limitForPage) {
        Sort sort = Sort.by(Sort.Direction.fromString(orderDirection), "name");
        Pageable pageable = PageRequest.of(currentPage, limitForPage, sort);

        Page<DishEntity> dishPage;
        if (categoryId != null && active != null) {
            dishPage = dishRepository.findByRestaurantIdAndCategoryIdAndActive(
                    idRestaurant, categoryId, active, pageable);
        } else if (categoryId != null) {
            dishPage = dishRepository.findByRestaurantIdAndCategoryId(
                    idRestaurant, categoryId, pageable);
        } else if (active != null) {
            dishPage = dishRepository.findByRestaurantIdAndActive(
                    idRestaurant, active, pageable);
        } else {
            dishPage = dishRepository.findByRestaurantId(idRestaurant, pageable);
        }

        List<Dish> dishes = dishPage.getContent().stream()
                .map(dishEntityMapper::toDomain)
                .collect(Collectors.toList());

        return new Pagination<>(
                orderDirection.equalsIgnoreCase("ASC"),
                currentPage,
                dishPage.getTotalPages(),
                dishPage.getTotalElements(),
                dishes
        );
    }

    @Override
    public Long getRestaurantIdByDishId(Long dishId) {
        DishEntity dishEntity = dishRepository.findById(dishId).orElse(new DishEntity());
        if(dishEntity.getRestaurant() != null) {
            return dishEntity.getRestaurant().getId();
        }
        return null;
    }
}
