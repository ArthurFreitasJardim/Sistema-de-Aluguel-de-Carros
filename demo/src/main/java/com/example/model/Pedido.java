package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String clienteNome;
    private String clienteCpf;
    private String clienteRg;
    private String clienteEndereco;
    private String clienteProfissao;

    private String empregador1;
    private Double rendimento1;

    private String empregador2;
    private Double rendimento2;

    private String empregador3;
    private Double rendimento3;

    private String carro;
    private String marca;
    private String modelo;
    private Integer ano;
    private String matricula;
    private String placa;

    @Enumerated(EnumType.STRING)
    private TipoProprietario proprietarioTipo;

    private Integer dias;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private String parecerAgente;
    private String agenteResponsavel;
}