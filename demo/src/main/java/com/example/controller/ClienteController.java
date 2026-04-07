package com.example.controller;

import com.example.model.Cliente;
import com.example.service.ClienteService;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;

@Controller("/clientes")
public class ClienteController {

    @Inject
    ClienteService service;

    @Post
    public Cliente criar(@Body Cliente cliente) {
        return service.criar(cliente);
    }

    @Get
    public List<Cliente> listar() {
        return service.listar();
    }

    @Get("/{id}")
    public Cliente buscar(Long id) {
        return service.buscar(id);
    }

    @Put("/{id}")
    public Cliente atualizar(Long id, @Body Cliente cliente) {
        return service.atualizar(id, cliente);
    }

    @Delete("/{id}")
    public void deletar(Long id) {
        service.deletar(id);
    }
}