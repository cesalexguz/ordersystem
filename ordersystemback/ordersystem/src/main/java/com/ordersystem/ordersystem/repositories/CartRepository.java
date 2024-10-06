package com.ordersystem.ordersystem.repositories;

import com.ordersystem.ordersystem.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Cart entities.
 * Provides CRUD operations for Article objects.
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
