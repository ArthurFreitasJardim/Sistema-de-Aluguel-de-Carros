package com.example.service;

import java.util.Optional;

import com.example.model.Pedido;
import com.example.repository.PedidoRepository;
import jakarta.inject.Singleton;

@Singleton
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setStatus("EM ANALISE");
        return repository.save(pedido);
    }

    public Iterable<Pedido> listar() {
        return repository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Long id, String clienteNome, String carro, Integer dias) {
        Pedido pedido = repository.findById(id).orElseThrow();

        pedido.setClienteNome(clienteNome);
        pedido.setCarro(carro);
        pedido.setDias(dias);
        pedido.setStatus("ALTERADO PELO CLIENTE");

        repository.update(pedido);
    }

    public void cancelar(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow();
        pedido.setStatus("CANCELADO");
        repository.update(pedido);
    }
}