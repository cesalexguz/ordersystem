package com.ordersystem.ordersystem.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an article in the order system.
 * Each article has a unique code, a name, and a unit price.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="articles")
public class Article {

    /**
     * The unique code of the article.
     * This value is used as the primary key.
     */
    @Id
    private String code;

    /**
     * The name of the article.
     * Must not be blank.
     */
    @NotBlank
    private String name;

    /**
     * The unit price of the article.
     * Must not be null.
     */
    @NotNull
    private Double unitPrice;

}
