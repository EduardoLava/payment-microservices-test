package com.bank.cardservice.modules.repository;

import com.bank.cardservice.modules.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    List<Card> listCardByClientId(Integer clientId);

}
