package com.ordersystem.ordersystem.controllers;

import com.ordersystem.ordersystem.entities.Article;
import com.ordersystem.ordersystem.entities.Cart;
import com.ordersystem.ordersystem.services.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Article entity.
 * Provides endpoints for creating, updating, retrieving, and deleting articles.
 */
@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4200/")
public class CartController {

    /**
     * Service implementation for managing Article entities.
     */
    @Autowired
    CartServiceImpl cartServiceImpl;

    /**
     * Endpoint for saving a new cart.
     * @param cart the article to save
     * @return the saved cart or an error message
     */
    @PostMapping
    public ResponseEntity<?> saveCart(@RequestBody Cart cart) {
        try {
            Cart savedCart = cartServiceImpl.saveCart(cart);
            return new ResponseEntity<>(savedCart, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for updating an existing cart.
     * @param cart the article to update
     * @return the updated cart or an error message
     */
    @PutMapping
    public ResponseEntity<?> updateCart(@RequestBody Cart cart) {
        try {
            Cart updatedCart = cartServiceImpl.updateCart(cart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for retrieving all carts.
     * @return a list of all carts
     */
    @GetMapping
    public ResponseEntity<List<Cart>> getCarts() {
        return new ResponseEntity<>(cartServiceImpl.getCarts(), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving an cart by its id.
     * @param id the code of the cart to retrieve
     * @return the article if found, or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
        Optional<Cart> cart = cartServiceImpl.getCart(id);
        if(cart.isPresent()) {
            return new ResponseEntity<>(cart.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for deleting an cart by its id.
     * @param id the code of the cart to delete
     * @return a 200 status if the cart was deleted, or a 404 status if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable Long id) {
        Optional<Cart> cart = cartServiceImpl.getCart(id);
        if(cart.isPresent()) {
            cartServiceImpl.deleteCart(cart.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
