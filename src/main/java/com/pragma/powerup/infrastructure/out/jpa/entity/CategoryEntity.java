package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.infrastructure.utils.constants.CategoryEntityConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = CategoryEntityConstants.CATEGORIES_TABLE_NAME)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = CategoryEntityConstants.CATEGORIES_MAPPED_BY)
    private List<DishEntity> dishes;
}
