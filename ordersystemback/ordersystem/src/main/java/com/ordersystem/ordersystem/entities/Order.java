package com.ordersystem.ordersystem.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents an order in the order system.
 * Each order has a unique code, an order date, a list of articles, and a client.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    /**
     * The unique code of the order.
     * Must follow the format "OC-" followed by 6 digits.
     */
    @Id
    @Size(min = 9, max = 9)
    @Pattern(regexp = "^OC-\\d{6}$", message = "The correct format code is OC- followed by 6 digits")
    private String code;

    /**
     * The date when the order was placed.
     * Must not be null and follows the format "yyyy-MM-dd".
     */
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    /**
     * The list of articles in cart included in the order.
     * This is a many-to-many relationship with the Article entity.
     */
    @ManyToMany
    @JoinTable(
            name = "order_carts",
            joinColumns = @JoinColumn(name = "order_code"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> articlesCart;

    /**
     * The client who placed the order.
     * This is a many-to-one relationship with the Client entity.
     */
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    /**
     * The total sum of the order.
     * Must not be null.
     */
    @NotNull
    private Double totalValueOrder;
}
