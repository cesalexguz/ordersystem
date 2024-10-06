package com.ordersystem.ordersystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an article selected in shopping cart with its quantity in the order system.
 * Each article has a unique id, a quantity, and an article.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="carts")
public class Cart {

    /**
     * The unique identifier for the cart.
     * This value is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The quantity selected of the article.
     * Must not be null.
     */
    @NotNull
    private Long quantity;

    /**
     * The article in cart included in the order.
     * This is a many-to-one relationship with the Article entity.
     */
    @ManyToOne
    @JoinColumn(name = "article_code")
    private Article article;

}
