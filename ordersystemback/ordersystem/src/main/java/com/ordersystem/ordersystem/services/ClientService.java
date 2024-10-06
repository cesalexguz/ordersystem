package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Client;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing Client entity.
 * Provides methods for saving, updating, retrieving, and deleting clients.
 */
public interface ClientService {

    /**
     * Saves a new client.
     * @param client the client to save
     * @return the saved client
     */
    Client saveClient(Client client);

    /**
     * Updates an existing client.
     * @param client the client to update
     * @return the updated client
     */
    Client updateClient(Client client);

    /**
     * Retrieves all clients.
     * @return a list of all clients
     */
    List<Client> getClients();

    /**
     * Retrieves a client by its ID.
     * @param id the ID of the client to retrieve
     * @return an Optional containing the client if found, or empty if not found
     */
    Optional<Client> getClient(Long id);

    /**
     * Deletes a client by its ID.
     * @param id the ID of the client to delete
     */
    void deleteClient(Long id);
}
