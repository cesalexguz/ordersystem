package com.ordersystem.ordersystem.controllers;

import com.ordersystem.ordersystem.entities.Cart;
import com.ordersystem.ordersystem.entities.Client;
import com.ordersystem.ordersystem.entities.Order;
import com.ordersystem.ordersystem.services.ArticleServiceImpl;
import com.ordersystem.ordersystem.services.CartServiceImpl;
import com.ordersystem.ordersystem.services.ClientServiceImpl;
import com.ordersystem.ordersystem.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Order entities.
 * Provides endpoints for creating, updating, retrieving, and deleting orders.
 */
@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:4200/")
public class OrderController {

    /**
     * Service implementation for managing Order entities.
     */
    @Autowired
    OrderServiceImpl orderServiceImpl;

    /**
     * Service implementation for managing Article entities.
     */
    @Autowired
    ArticleServiceImpl articleServiceImpl;

    /**
     * Service implementation for managing Cart entities.
     */
    @Autowired
    CartServiceImpl cartServiceImpl;

    /**
     * Service implementation for managing Client entities.
     */
    @Autowired
    ClientServiceImpl clientServiceImpl;

    /**
     * Endpoint for saving a new order.
     * @param order the order to save
     * @return the saved order or an error message
     */
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody Order order) {
        try {
            if(order.getArticlesCart() == null || order.getArticlesCart().isEmpty()) {
                return new ResponseEntity<>("Articles cart can't be null or empty.", HttpStatus.BAD_REQUEST);
            } else {
                Optional<Client> client = clientServiceImpl.getClient(order.getClient().getId());
                if (client.isEmpty()) {
                    return new ResponseEntity<>("Client is not registered.", HttpStatus.BAD_REQUEST);
                } else {
                    List<Cart> validArticles = getValidArticles(order.getArticlesCart());
                    if(validArticles.size() != order.getArticlesCart().size()) {
                        return new ResponseEntity<>("There are no-registered articles in your order.", HttpStatus.BAD_REQUEST);
                    } else {
                        order.setTotalValueOrder(getTotalValueOrder(order.getArticlesCart()));
                        List<Cart> cartList =  cartServiceImpl.saveCarts(order.getArticlesCart());
                        order.setArticlesCart(cartList);
                        Order savedOrder = orderServiceImpl.saveOrder(order);
                        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
                    }
                }
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for updating an existing order.
     * @param order the order to update
     * @return the updated order or an error message
     */
    @PutMapping
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            if(order.getArticlesCart() == null || order.getArticlesCart().isEmpty()) {
                return new ResponseEntity<>("Articles list can't be null or empty.", HttpStatus.BAD_REQUEST);
            } else {
                Optional<Client> client = clientServiceImpl.getClient(order.getClient().getId());
                if (client.isEmpty()) {
                    return new ResponseEntity<>("Client is not registered.", HttpStatus.BAD_REQUEST);
                } else {
                    List<Cart> validArticles = getValidArticles(order.getArticlesCart());
                    if(validArticles.size() != order.getArticlesCart().size()) {
                        return new ResponseEntity<>("There are no-registered articles in your order.", HttpStatus.BAD_REQUEST);
                    } else {
                        order.setTotalValueOrder(getTotalValueOrder(order.getArticlesCart()));
                        List<Cart> cartList =  cartServiceImpl.saveCarts(order.getArticlesCart());
                        order.setArticlesCart(cartList);
                        Order updatedOrder = orderServiceImpl.updateOrder(order);
                        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
                    }
                }
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for retrieving all orders.
     * @return a list of all orders
     */
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderServiceImpl.getOrders(), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving an order by its code.
     * @param code the code of the order to retrieve
     * @return the order if found, or a 404 status if not found
     */
    @GetMapping("/{code}")
    public ResponseEntity<Order> getOrderById(@PathVariable String code) {
        Optional<Order> order = orderServiceImpl.getOrder(code);
        if(order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for deleting an order by its code.
     * @param code the code of the order to delete
     * @return a 200 status if the order was deleted, or a 404 status if not found
     */
    @DeleteMapping("/{code}")
    public ResponseEntity<Order> deleteOrder(@PathVariable String code) {
        Optional<Order> order = orderServiceImpl.getOrder(code);
        if(order.isPresent()) {
            orderServiceImpl.deleteOrder(order.get().getCode());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Validates the list of articles in cart in the order checking if each article is registered.
     * @param articlesCart the list of articles to validate
     * @return a list of valid articles in cart
     */
    public List<Cart> getValidArticles(List<Cart> articlesCart){
        List<Cart> validArticlesCart = new ArrayList<>();
        List<String> allArticleCodes = articleServiceImpl.getAllArticleCodes();
        for (Cart cart : articlesCart) {
            if(cart != null && allArticleCodes.contains(cart.getArticle().getCode())) {
                validArticlesCart.add(cart);
            }
        }
        return validArticlesCart;
    }

    /**
     * Calculates the total sum of the list of articles in cart in the order.
     * @param articlesCart the list of articles in cart to validate
     * @return a double with the total sum of the order
     */
    public Double getTotalValueOrder(List<Cart> articlesCart) {
        Double totalValueOrder = 0.0;
        for (Cart cart : articlesCart) {
            if(cart != null) {
                totalValueOrder += cart.getArticle().getUnitPrice() * cart.getQuantity();
            }
        }
        return totalValueOrder;
    }
}
