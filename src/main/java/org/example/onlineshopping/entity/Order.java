package org.example.onlineshopping.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CustomerId")
    private Customer customer;

    private Date orderDate;

    public Order(Customer customer, Date date) {
        this.customer = customer;
        this.orderDate = date;
    }
}
