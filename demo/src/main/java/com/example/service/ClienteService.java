package com.example.service;

import com.example.model.Cliente;
import com.example.repository.ClienteRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class ClienteService {

    @Inject
    ClienteRepository repository;

    public Cliente criar(Cliente cliente) {
        return repository.salvar(cliente);
    }

    public List<Cliente> listar() {
        return repository.listar();
    }

    public Cliente buscar(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        return repository.atualizar(id, cliente);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}