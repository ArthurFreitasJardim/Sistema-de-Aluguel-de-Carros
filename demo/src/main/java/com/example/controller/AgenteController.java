package com.example.controller;

import com.example.model.Pedido;
import com.example.model.StatusPedido;
import com.example.service.ContratoService;
import com.example.service.PedidoService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Controller("/agente")
public class AgenteController {

    private final PedidoService pedidoService;
    private final ContratoService contratoService;

    public AgenteController(PedidoService pedidoService, ContratoService contratoService) {
        this.pedidoService = pedidoService;
        this.contratoService = contratoService;
    }

    private boolean isAdmin(HttpRequest<?> request) {
        return request.getCookies()
                .findCookie("perfil")
                .map(cookie -> cookie.getValue().equals("ADMIN"))
                .orElse(false);
    }

    private String getPerfil(HttpRequest<?> request) {
        return request.getCookies()
                .findCookie("perfil")
                .map(cookie -> cookie.getValue())
                .orElse("VISITANTE");
    }

    @Get("/pedidos")
    @View("agente-pedidos")
    public Object listarPedidos(HttpRequest<?> request) {
        if (!isAdmin(request)) {
            return HttpResponse.redirect(URI.create("/login"));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("pedidos", pedidoService.listar());
        model.put("perfil", getPerfil(request));

        return model;
    }

    @Get("/avaliar/{id}")
    @View("agente-avaliar")
    public Object avaliarForm(Long id, HttpRequest<?> request) {
        if (!isAdmin(request)) {
            return HttpResponse.redirect(URI.create("/login"));
        }

        Pedido pedido = pedidoService.buscarPorId(id).orElseThrow();

        // Bloqueia acesso se já finalizado
        if (pedido.getStatus() == StatusPedido.CANCELADO ||
            pedido.getStatus() == StatusPedido.APROVADO ||
            pedido.getStatus() == StatusPedido.REPROVADO ||
            pedido.getStatus() == StatusPedido.CONTRATO_GERADO) {
            return HttpResponse.redirect(URI.create("/agente/pedidos"));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("pedido", pedido);
        model.put("perfil", getPerfil(request));

        return model;
    }

    @Post(value = "/avaliar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> avaliar(
            HttpRequest<?> request,
            @Body("id") Long id,
            @Body("aprovado") String aprovado,
            @Body("parecer") String parecer,
            @Body("agente") String agente,
            @Body("possuiCredito") String possuiCredito,
            @Body("bancoAgente") String bancoAgente,
            @Body("valorCredito") Double valorCredito
    ) {
        if (!isAdmin(request)) {
            return HttpResponse.redirect(URI.create("/login"));
        }

        Pedido pedidoAtual = pedidoService.buscarPorId(id).orElseThrow();

        if (pedidoAtual.getStatus() == StatusPedido.CANCELADO ||
            pedidoAtual.getStatus() == StatusPedido.APROVADO ||
            pedidoAtual.getStatus() == StatusPedido.REPROVADO ||
            pedidoAtual.getStatus() == StatusPedido.CONTRATO_GERADO) {
            return HttpResponse.redirect(URI.create("/agente/pedidos"));
        }

        boolean foiAprovado = "true".equals(aprovado);

        pedidoService.avaliar(id, foiAprovado, parecer, agente);

        if (foiAprovado) {
            Pedido pedido = pedidoService.buscarPorId(id).orElseThrow();

            boolean credito = "true".equals(possuiCredito);

            contratoService.gerarContrato(
                    pedido,
                    credito,
                    credito ? bancoAgente : null,
                    credito ? valorCredito : null
            );

            pedidoService.marcarContratoGerado(id);
        }

        return HttpResponse.redirect(URI.create("/agente/pedidos"));
    }
}