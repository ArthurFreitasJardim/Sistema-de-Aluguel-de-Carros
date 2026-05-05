package com.example.controller;

import com.example.model.Usuario;
import com.example.service.UsuarioService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/conta")
public class ContaController {

    private final UsuarioService usuarioService;

    public ContaController(UsuarioService usuarioService) {
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

    @Get("/dados")
    @View("dados-conta")
    public Object dados(HttpRequest<?> request) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return HttpResponse.redirect(URI.create("/login"));
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId.get()).orElseThrow();

        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("perfil", getPerfil(request));
        model.put("usuarioNome", usuario.getNome());

        return model;
    }
}