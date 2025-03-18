package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.infrastructure.utils.constants.DishEntityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = DishEntityConstants.DISHES_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String urlImage;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = DishEntityConstants.RESTAURANT_JOIN_COLUMN, nullable = false)
    private RestaurantEntity restaurant;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<CategoryEntity>  categories;


}
