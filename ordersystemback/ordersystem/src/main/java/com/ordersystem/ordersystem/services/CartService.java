package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Cart;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Cart entity.
 * Provides methods for saving, updating, retrieving, and deleting carts.
 */
public interface CartService {

    /**
     * Saves a new cart.
     * @param cart the cart to save
     * @return the saved cart
     */
    Cart saveCart(Cart cart);

    /**
     * Saves a new cart list.
     * @param carts the cart list to save
     * @return the saved cart list
     */
    List<Cart> saveCarts(List<Cart> carts);

    /**
     * Updates an existing cart.
     * @param cart the cart to update
     * @return the updated cart
     */
    Cart updateCart(Cart cart);

    /**
     * Retrieves all carts.
     * @return a list of all carts
     */
    List<Cart> getCarts();

    /**
     * Retrieves an cart by its id.
     * @param id the id of the cart to retrieve
     * @return an Optional containing the cart if found, or empty if not found
     */
    Optional<Cart> getCart(Long id);

    /**
     * Deletes an cart by its id.
     * @param id the id of the cart to delete
     */
    void deleteCart(Long id);

}
