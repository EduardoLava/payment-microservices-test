package com.bank.clientservice.modules.repository;

import com.bank.clientservice.modules.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
