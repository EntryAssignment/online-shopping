package org.example.onlineshopping.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "OrderID")
    private Order order;

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
}
