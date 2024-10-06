package com.ordersystem.ordersystem.controllers;

import com.ordersystem.ordersystem.entities.Client;
import com.ordersystem.ordersystem.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Client entity.
 * Provides endpoints for creating, updating, retrieving, and deleting clients.
 */
@RestController
@RequestMapping("/client")
@CrossOrigin("http://localhost:4200/")
public class ClientController {

    /**
     * Service implementation for managing Client entities.
     */
    @Autowired
    ClientServiceImpl clientServiceImpl;

    /**
     * Endpoint for saving a new client.
     * @param client the client to save
     * @return the saved client or an error message
     */
    @PostMapping
    public ResponseEntity<?> saveClient(@RequestBody Client client) {
        try {
            Client savedClient = clientServiceImpl.saveClient(client);
            return new ResponseEntity<>(savedClient, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for updating an existing client.
     * @param client the client to update
     * @return the updated client or an error message
     */
    @PutMapping
    public ResponseEntity<?> updateClient(@RequestBody Client client) {
        try {
            Client updatedClient = clientServiceImpl.updateClient(client);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);

        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Data integrity violation: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TransactionSystemException e) {
            return new ResponseEntity<>("Transaction system exception: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint for retrieving all clients.
     * @return a list of all clients
     */
    @GetMapping
    public ResponseEntity<List<Client>> getClients() {
        return new ResponseEntity<>(clientServiceImpl.getClients(), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a client by its ID.
     * @param id the ID of the client to retrieve
     * @return the client if found, or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientServiceImpl.getClient(id);
        if(client.isPresent()) {
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint for deleting a client by its ID.
     * @param id the ID of the client to delete
     * @return a 200 status if the client was deleted, or a 404 status if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        Optional<Client> client = clientServiceImpl.getClient(id);
        if(client.isPresent()) {
            clientServiceImpl.deleteClient(client.get().getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
