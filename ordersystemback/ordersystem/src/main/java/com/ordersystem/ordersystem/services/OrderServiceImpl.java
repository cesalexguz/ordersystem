package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Order;
import com.ordersystem.ordersystem.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the OrderService interface.
 * Provides methods for managing Order entity.
 */
@Service
public class OrderServiceImpl implements OrderService{

    /**
     * Repository for performing CRUD operations on Order entities.
     */
    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrder(String code) {
        return orderRepository.findById(code);
    }

    @Transactional
    @Override
    public void deleteOrder(String code) {
        orderRepository.deleteById(code);
    }
}
