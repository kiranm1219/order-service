package com.food.model;


import com.food.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String customerId;
    private String restaurantId;
    private String details;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FoodItem> foodItem;
    private Status status;
    private Double totalOrderPrice;
}
