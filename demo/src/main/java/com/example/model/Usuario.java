package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;

    private String cpf;
    private String rg;
    private String endereco;
    private String profissao;

    private String empregador1;
    private Double rendimento1;

    private String empregador2;
    private Double rendimento2;

    private String empregador3;
    private Double rendimento3;
}