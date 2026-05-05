package com.example.controller;

import com.example.model.Usuario;
import com.example.service.ContratoService;
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

@Controller("/contratos")
public class ContratoController {

    private final ContratoService service;
    private final UsuarioService usuarioService;

    public ContratoController(ContratoService service, UsuarioService usuarioService) {
        this.service = service;
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

    @Get("/lista")
    @View("contrato-lista")
    public Object listar(HttpRequest<?> request) {
        String perfil = getPerfil(request);

        Map<String, Object> model = new HashMap<>();
        model.put("perfil", perfil);

        if ("ADMIN".equals(perfil)) {
            model.put("contratos", service.listar());
            model.put("usuarioNome", "Administrador");
            return model;
        }

        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return HttpResponse.redirect(URI.create("/login"));
        }

        Usuario usuario = usuarioService.buscarPorId(usuarioId.get()).orElseThrow();

        model.put("usuarioNome", usuario.getNome());
        model.put("contratos", service.listarPorClienteCpf(usuario.getCpf()));

        return model;
    }
}