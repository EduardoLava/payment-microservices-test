package com.bank.clientservice.modules;

import com.bank.clientservice.modules.dto.ClientRequestDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class ClientTestHelper {

    public ClientRequestDTO crateClient() {
        return ClientRequestDTO.builder()
                .name("Test People")
                .birthday(LocalDate.of(1990,5,4))
                .document("85588059034")
                .build();
    }

}
