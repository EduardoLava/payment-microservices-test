package com.bank.cardservice.modules.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDTO {
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
