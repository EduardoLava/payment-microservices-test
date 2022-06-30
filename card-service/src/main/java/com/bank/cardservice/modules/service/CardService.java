package com.bank.cardservice.modules.service;

import com.bank.cardservice.modules.entity.Card;
import com.bank.cardservice.modules.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card save(Card card){
        return cardRepository.save(card);
    }

    public Card findOne(Integer id){
        return this.cardRepository.findById(id).orElseThrow(() -> {
            throw new  IllegalArgumentException("Card not found by id "+ id);
        });
    }

    public List<Card> findByClientId(Integer clientId){
        return this.cardRepository.listCardByClientId(clientId);
    }
}
