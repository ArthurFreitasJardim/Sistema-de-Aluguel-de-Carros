package com.example.model;

import java.util.List;

public class CarroDisponivel {

    private String codigo;
    private String marca;
    private String modelo;
    private Integer ano;
    private String km;
    private Double precoDiaria;
    private String imagemUrl;
    private List<String> imagensUrl;
    private String descricao;

    private String matricula;
    private String placa;
    private TipoProprietario proprietarioTipo;

    public CarroDisponivel(String codigo, String marca, String modelo, Integer ano, String km,
                           Double precoDiaria, List<String> imagensUrl, String descricao,
                           String matricula, String placa, TipoProprietario proprietarioTipo) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.km = km;
        this.precoDiaria = precoDiaria;
        this.imagensUrl = imagensUrl;
        this.imagemUrl = imagensUrl != null && !imagensUrl.isEmpty() ? imagensUrl.get(0) : "";
        this.descricao = descricao;
        this.matricula = matricula;
        this.placa = placa;
        this.proprietarioTipo = proprietarioTipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getKm() {
        return km;
    }

    public Double getPrecoDiaria() {
        return precoDiaria;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public List<String> getImagensUrl() {
        return imagensUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPlaca() {
        return placa;
    }

    public TipoProprietario getProprietarioTipo() {
        return proprietarioTipo;
    }

    public String getNomeCompleto() {
        return marca + " - " + modelo;
    }
}