package com.hfm.fagsystem.service;

import com.hfm.fagsystem.model.Client;
import com.hfm.fagsystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    /*
    @Autowired
    ClientRepository clientRepository;

     */

    public Optional<Client> find(String id) {
        return Optional.empty();
        // return clientRepository.findById(id);
    }

    public Client update(Client client) {
        client.setId(UUID.randomUUID().toString());
        return client;
        // return clientRepository.save(client);
    }

    public void delete(String id) {
        // clientRepository.deleteById(id);
    }
}
