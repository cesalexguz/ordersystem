package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Cart;
import com.ordersystem.ordersystem.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the CartService interface.
 * Provides methods for managing Cart entity.
 */
@Service
public class CartServiceImpl implements CartService {

    /**
     * Repository for performing CRUD operations on Cart entities.
     */
    @Autowired
    CartRepository cartRepository;

    @Transactional
    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    @Override
    public List<Cart> saveCarts(List<Cart> carts) {
        return cartRepository.saveAll(carts);
    }

    @Transactional
    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> getCart(Long id) {
        return cartRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}
