package com.example.repository;

import com.example.model.Cliente;
import jakarta.inject.Singleton;

import java.util.*;

@Singleton
public class ClienteRepository {

    private final Map<Long, Cliente> banco = new HashMap<>();
    private Long sequence = 1L;

    public Cliente salvar(Cliente cliente) {
        cliente.setId(sequence++);
        banco.put(cliente.getId(), cliente);
        return cliente;
    }

    public List<Cliente> listar() {
        return new ArrayList<>(banco.values());
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return Optional.ofNullable(banco.get(id));
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        cliente.setId(id);
        banco.put(id, cliente);
        return cliente;
    }

    public void deletar(Long id) {
        banco.remove(id);
    }
}