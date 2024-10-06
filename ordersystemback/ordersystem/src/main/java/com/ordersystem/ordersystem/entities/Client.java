package com.ordersystem.ordersystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a client in the order system.
 * Each client has a unique ID, a name, and a lastname.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name="clients")
public class Client {

    /**
     * The unique identifier for the client.
     * This value is automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the client.
     * Must not be blank.
     */
    @NotBlank
    private String name;

    /**
     * The last name of the client.
     * Must not be blank.
     */
    @NotBlank
    private String lastname;

}
