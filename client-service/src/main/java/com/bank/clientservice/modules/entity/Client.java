package com.bank.clientservice.modules.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Table
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_client_id")
    private Integer id;

    @NotBlank
    @Column(nullable = false)
    private String name;
    @CPF
    @NotBlank
    @Column(nullable = false)
    private String document;
    @NotNull
    @Column(nullable = false)
    private LocalDate birthday;
}
