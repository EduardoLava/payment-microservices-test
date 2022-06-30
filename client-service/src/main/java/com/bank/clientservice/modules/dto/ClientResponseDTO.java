package com.bank.clientservice.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private Integer id;
    private String name;
    private String document;
    private LocalDate birthday;
}
