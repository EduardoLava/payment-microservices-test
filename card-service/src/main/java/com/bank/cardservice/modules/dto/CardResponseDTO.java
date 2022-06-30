package com.bank.cardservice.modules.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardResponseDTO {
    private static final Integer FOUR = 4;

    private String number;
    private LocalDate expiration;
    private String holderName;

    public void setNumber(String number) {
        this.number = number;
        showOnlyLastFour();
    }

    private void showOnlyLastFour(){
        int length = number.length();
        var lastFour = number.substring(length - FOUR, length);
        this.number =  "**** **** **** "+lastFour;
    }
}
