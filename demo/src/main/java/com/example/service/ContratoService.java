package com.example.service;

import com.example.model.Contrato;
import com.example.model.Pedido;
import com.example.repository.ContratoRepository;
import jakarta.inject.Singleton;

import java.time.LocalDate;

@Singleton
public class ContratoService {

    private final ContratoRepository repository;

    public ContratoService(ContratoRepository repository) {
        this.repository = repository;
    }

    public Contrato gerarContrato(Pedido pedido, Boolean possuiCredito, String bancoAgente, Double valorCredito) {

        if (repository.existsByPedidoId(pedido.getId())) {
            return repository.findByPedidoId(pedido.getId()).orElseThrow();
        }

        Contrato contrato = new Contrato();

        contrato.setPedidoId(pedido.getId());
        contrato.setClienteNome(pedido.getClienteNome());
        contrato.setClienteCpf(pedido.getClienteCpf());

        contrato.setCarro(pedido.getCarro());
        contrato.setPlaca(pedido.getPlaca());
        contrato.setMatricula(pedido.getMatricula());

        contrato.setDias(pedido.getDias());
        contrato.setDataInicio(LocalDate.now());
        contrato.setDataFim(LocalDate.now().plusDays(pedido.getDias()));

        contrato.setPossuiCredito(possuiCredito);
        contrato.setBancoAgente(bancoAgente);
        contrato.setValorCredito(valorCredito);

        contrato.setStatusContrato("ATIVO");

        return repository.save(contrato);
    }

    public Iterable<Contrato> listar() {
        return repository.findAll();
    }

    public Iterable<Contrato> listarPorClienteCpf(String cpf) {
    return repository.findByClienteCpf(cpf);
}
}