package com.example.controller;

import com.example.service.CarroDisponivelService;
import com.example.service.UsuarioService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/carros")
public class CarroController {

    private final CarroDisponivelService carroService;
    private final UsuarioService usuarioService;

    public CarroController(CarroDisponivelService carroService, UsuarioService usuarioService) {
        this.carroService = carroService;
        this.usuarioService = usuarioService;
    }

    private Optional<Long> getUsuarioId(HttpRequest<?> request) {
        return request.getCookies()
                .findCookie("usuarioId")
                .map(Cookie::getValue)
                .map(Long::valueOf);
    }

    private String getPerfil(HttpRequest<?> request) {
        return request.getCookies()
                .findCookie("perfil")
                .map(Cookie::getValue)
                .orElse("VISITANTE");
    }

    private void preencherMenu(Map<String, Object> model, HttpRequest<?> request) {
        String perfil = getPerfil(request);
        model.put("perfil", perfil);

        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isPresent()) {
            usuarioService.buscarPorId(usuarioId.get())
                    .ifPresent(usuario -> model.put("usuarioNome", usuario.getNome()));
        }

        if ("ADMIN".equals(perfil)) {
            model.put("usuarioNome", "Administrador");
        }
    }

    @Get("/detalhes/{codigo}")
    @View("carro-detalhes")
    public Object detalhes(String codigo, HttpRequest<?> request) {
        var carroOpt = carroService.buscarPorCodigo(codigo);

        if (carroOpt.isEmpty()) {
            return HttpResponse.redirect(URI.create("/pedidos/"));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("carro", carroOpt.get());
        preencherMenu(model, request);

        return model;
    }
}