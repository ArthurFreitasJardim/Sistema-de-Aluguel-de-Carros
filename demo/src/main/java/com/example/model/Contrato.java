package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private String clienteNome;
    private String clienteCpf;

    private String carro;
    private String placa;
    private String matricula;

    private Integer dias;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    private Boolean possuiCredito;
    private String bancoAgente;
    private Double valorCredito;

    private String statusContrato;
}