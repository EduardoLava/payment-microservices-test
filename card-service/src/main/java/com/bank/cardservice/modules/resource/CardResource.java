package com.bank.cardservice.modules.resource;

import com.bank.cardservice.modules.dto.CardRequestDTO;
import com.bank.cardservice.modules.dto.CardResponseDTO;
import com.bank.cardservice.modules.entity.Card;
import com.bank.cardservice.modules.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards")
public class CardResource {

    @Autowired
    public CardService cardService;

    @PostMapping
    public CardResponseDTO save(CardRequestDTO cardRequestDTO) {
        Card card =
    }

}
