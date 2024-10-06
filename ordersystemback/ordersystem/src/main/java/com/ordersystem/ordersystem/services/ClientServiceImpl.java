package com.ordersystem.ordersystem.services;

import com.ordersystem.ordersystem.entities.Client;
import com.ordersystem.ordersystem.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ClientService interface.
 * Provides methods for managing Client entity.
 */
@Service
public class ClientServiceImpl implements ClientService{

    /**
     * Repository for performing CRUD operations on Client entities.
     */
    @Autowired
    ClientRepository clientRepository;

    @Transactional
    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClient(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
