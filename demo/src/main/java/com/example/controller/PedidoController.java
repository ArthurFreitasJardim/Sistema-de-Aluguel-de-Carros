package com.example.controller;

import com.example.model.Pedido;
import com.example.service.PedidoService;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @Get("/")
    @View("pedido-form")
    public Map<String, Object> form() {
        return new HashMap<>();
    }

    @Post(value = "/salvar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public MutableHttpResponse<Object> salvar(
            @Body("clienteNome") String clienteNome,
            @Body("carro") String carro,
            @Body("dias") Integer dias) {
        Pedido pedido = new Pedido();
        pedido.setClienteNome(clienteNome);
        pedido.setCarro(carro);
        pedido.setDias(dias);

        service.salvar(pedido);

        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }

    @Get("/lista")
    @View("pedido-lista")
    public Map<String, Object> listar() {
        Map<String, Object> model = new HashMap<>();
        model.put("pedidos", service.listar());
        return model;
    }

    @Get("/editar/{id}")
    @View("pedido-editar")
    public Map<String, Object> editarForm(Long id) {
        Map<String, Object> model = new HashMap<>();
        model.put("pedido", service.buscarPorId(id));
        return model;
    }

    @Post(value = "/atualizar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> atualizar(
            @Body("id") Long id,
            @Body("clienteNome") String clienteNome,
            @Body("carro") String carro,
            @Body("dias") Integer dias) {
        service.atualizar(id, clienteNome, carro, dias);
        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }

    @Post(value = "/cancelar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> cancelar(@Body("id") Long id) {
        service.cancelar(id);
        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }
}