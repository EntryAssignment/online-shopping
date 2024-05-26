package org.example.onlineshopping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "OrderID")
    private Order order;

    private String status;

    public Delivery(Order order, String status) {
        this.order = order;
        this.status = status;
    }
}
