package com.ordersystem.ordersystem.repositories;

import com.ordersystem.ordersystem.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Client entities.
 * Provides CRUD operations for Client objects.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
