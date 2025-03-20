package com.pragma.powerup.infrastructure.out.jpa.entity;

import com.pragma.powerup.infrastructure.utils.constants.RestaurantEntityConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = RestaurantEntityConstants.RESTAURANT_TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String phone;
    private String urlLogo;
    private Long idOwner;

    @OneToMany(mappedBy = RestaurantEntityConstants.RESTAURANT_MAPPED_BY, fetch = FetchType.LAZY)
    private List<DishEntity> dishes;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EmployeeEntity> employees = new ArrayList<>();

}
