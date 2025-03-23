package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {
    Page<DishEntity> findByRestaurantId(Long restaurantId, Pageable pageable);

    @Query("SELECT d FROM DishEntity d JOIN d.categories c WHERE d.restaurant.id = :restaurantId AND c.id = :categoryId")
    Page<DishEntity> findByRestaurantIdAndCategoryId(@Param("restaurantId") Long restaurantId, @Param("categoryId") Long categoryId, Pageable pageable);

    Page<DishEntity> findByRestaurantIdAndActive(Long restaurantId, Boolean active, Pageable pageable);

    @Query("SELECT d FROM DishEntity d JOIN d.categories c WHERE d.restaurant.id = :restaurantId AND c.id = :categoryId AND d.active = :active")
    Page<DishEntity> findByRestaurantIdAndCategoryIdAndActive(
            @Param("restaurantId") Long restaurantId,
            @Param("categoryId") Long categoryId,
            @Param("active") Boolean active,
            Pageable pageable);
}
