# 🚗 Sistema de Aluguel de Carros - Lab 02 (Sprint 1)

[cite_start]Este repositório contém a modelagem inicial e o desenvolvimento de um sistema web para gestão de aluguéis de automóveis[cite: 6]. [cite_start]O projeto segue a arquitetura **MVC** e é desenvolvido em **Java**[cite: 24].

------------------------------------------------------------------------

## 📋 Histórias de Usuário (User Stories)

| ID | Ator | Descrição | Critério de Aceitação |
| :--- | :--- | :--- | :--- |
| **US01** | Cliente | [cite_start]Como cliente, quero me cadastrar informando meus dados pessoais e rendimentos para acessar o sistema[cite: 9, 10]. | [cite_start]Validar obrigatoriedade de RG, CPF, Nome, Endereço e Profissão [cite: 13][cite_start]; permitir o cadastro de no máximo 3 fontes de rendimento[cite: 13]. |
| **US02** | Cliente | [cite_start]Como cliente, quero solicitar o aluguel de um automóvel através da Internet[cite: 6, 10]. | [cite_start]O pedido deve registrar matrícula, ano, marca, modelo e placa do veículo[cite: 15]. |
| **US03** | Agente | [cite_start]Como agente (banco/empresa), quero avaliar financeiramente os pedidos de aluguel[cite: 11, 12]. | [cite_start]O contrato só deve ser executado para consideração após parecer positivo do agente[cite: 12]. |
| **US04** | Banco | [cite_start]Como banco agente, quero associar um contrato de crédito a um pedido de aluguel[cite: 16]. | [cite_start]O sistema deve permitir vincular o crédito concedido especificamente por um banco agente ao aluguel[cite: 16]. |
| **US05** | Cliente | [cite_start]Como cliente, quero modificar ou cancelar meus pedidos de aluguel pendentes[cite: 10]. | [cite_start]Permitir alterações ou cancelamentos apenas em pedidos que ainda não foram convertidos em contratos executados[cite: 6]. |
| **US06** | Agente | [cite_start]Como agente, quero modificar pedidos de aluguel[cite: 11]. | [cite_start]O sistema deve permitir que empresas e bancos alterem detalhes do pedido durante a fase de avaliação[cite: 11]. |
| **US07** | Sistema | [cite_start]Como sistema, quero registrar a propriedade do automóvel conforme o tipo de contrato[cite: 14]. | [cite_start]Dependendo do contrato, o veículo deve ser registrado como propriedade do cliente, empresa ou banco[cite: 14]. |

------------------------------------------------------------------------
