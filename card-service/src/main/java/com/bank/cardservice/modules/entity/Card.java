package com.bank.cardservice.modules.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "card_id_generator", initialValue = 1)
    private Integer id;

    @NotNull
    @Length(min = 16, max = 16)
    private String number;
    @NotNull
    private LocalDate expiration;
    @NotBlank
    private String holderName;
    @NotBlank
    @Length(min = 3, max = 4)
    private String securityCode;
    @NotNull
    @Min(0)
    private Integer clientId;
}
