package com.bank.clientservice.modules.service;

import com.bank.clientservice.modules.repository.ClientRepository;
import com.bank.clientservice.modules.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Client findById(Integer id){
        return this.clientRepository.findById(id).orElse(null);
    }
}
