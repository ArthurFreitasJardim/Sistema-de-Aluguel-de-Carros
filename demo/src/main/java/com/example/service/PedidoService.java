package com.example.service;

import com.example.model.Pedido;
import com.example.model.StatusPedido;
import com.example.repository.PedidoRepository;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setStatus(StatusPedido.EM_ANALISE);
        return repository.save(pedido);
    }

    public Iterable<Pedido> listar() {
        return repository.findAll();
    }

    public Iterable<Pedido> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Long id, String carro, Integer dias) {
        Pedido pedido = repository.findById(id).orElseThrow();

        if (pedido.getStatus() == StatusPedido.APROVADO ||
                pedido.getStatus() == StatusPedido.CONTRATO_GERADO ||
                pedido.getStatus() == StatusPedido.CANCELADO) {
            return;
        }

        pedido.setCarro(carro);
        pedido.setDias(dias);
        pedido.setStatus(StatusPedido.ALTERADO_PELO_CLIENTE);

        repository.update(pedido);
    }

    public void cancelar(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow();

        if (pedido.getStatus() == StatusPedido.APROVADO ||
                pedido.getStatus() == StatusPedido.CONTRATO_GERADO ||
                pedido.getStatus() == StatusPedido.CANCELADO) {
            return;
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        repository.update(pedido);
    }

    public void avaliar(Long id, boolean aprovado, String parecer, String agente) {
        Pedido pedido = repository.findById(id).orElseThrow();

        if (pedido.getStatus() == StatusPedido.CANCELADO ||
                pedido.getStatus() == StatusPedido.APROVADO ||
                pedido.getStatus() == StatusPedido.REPROVADO ||
                pedido.getStatus() == StatusPedido.CONTRATO_GERADO) {
            return;
        }

        pedido.setParecerAgente(parecer);
        pedido.setAgenteResponsavel(agente);
        pedido.setStatus(aprovado ? StatusPedido.APROVADO : StatusPedido.REPROVADO);

        repository.update(pedido);
    }

    public void marcarContratoGerado(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow();

        if (pedido.getStatus() != StatusPedido.APROVADO) {
            return;
        }

        pedido.setStatus(StatusPedido.CONTRATO_GERADO);
        repository.update(pedido);
    }
}