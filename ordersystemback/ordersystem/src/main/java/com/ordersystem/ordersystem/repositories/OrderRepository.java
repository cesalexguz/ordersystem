package com.ordersystem.ordersystem.repositories;

import com.ordersystem.ordersystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Order entities.
 * Provides CRUD operations for Order objects.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
