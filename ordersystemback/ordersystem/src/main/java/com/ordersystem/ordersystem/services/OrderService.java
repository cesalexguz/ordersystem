package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Order;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Order entity.
 * Provides methods for saving, updating, retrieving, and deleting orders.
 */
public interface OrderService {

    /**
     * Saves a new order.
     * @param order the order to save
     * @return the saved order
     */
    Order saveOrder(Order order);

    /**
     * Updates an existing order.
     * @param order the order to update
     * @return the updated order
     */
    Order updateOrder(Order order);

    /**
     * Retrieves all orders.
     * @return a list of all orders
     */
    List<Order> getOrders();

    /**
     * Retrieves an order by its code.
     * @param code the code of the order to retrieve
     * @return an Optional containing the order if found, or empty if not found
     */
    Optional<Order> getOrder(String code);

    /**
     * Deletes an order by its code.
     * @param code the code of the order to delete
     */
    void deleteOrder(String code);

}
