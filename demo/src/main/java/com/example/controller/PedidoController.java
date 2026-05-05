package com.example.controller;

import com.example.model.Pedido;
import com.example.model.Usuario;
import com.example.service.CarroDisponivelService;
import com.example.service.PedidoService;
import com.example.service.UsuarioService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller("/pedidos")
public class PedidoController {

    private final PedidoService service;
    private final UsuarioService usuarioService;
    private final CarroDisponivelService carroService;

    public PedidoController(PedidoService service,
                            UsuarioService usuarioService,
                            CarroDisponivelService carroService) {
        this.service = service;
        this.usuarioService = usuarioService;
        this.carroService = carroService;
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
        model.put("perfil", getPerfil(request));

        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isPresent()) {
            usuarioService.buscarPorId(usuarioId.get())
                    .ifPresent(usuario -> model.put("usuarioNome", usuario.getNome()));
        }
    }

    private HttpResponse<?> redirecionarLogin() {
        return HttpResponse.redirect(URI.create("/login"));
    }

    @Get("/")
    @View("pedido-form")
    public Object form(HttpRequest<?> request) {
        Map<String, Object> model = new HashMap<>();
        model.put("carros", carroService.listar());
        preencherMenu(model, request);
        return model;
    }

    @Post(value = "/salvar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> salvar(
            HttpRequest<?> request,
            @Body("carroCodigo") String carroCodigo,
            @Body("dias") Integer dias
    ) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return redirecionarLogin();
        }

        if (dias == null || dias <= 0) {
            return HttpResponse.redirect(URI.create("/pedidos/"));
        }

        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(usuarioId.get());

        if (usuarioOpt.isEmpty()) {
            return HttpResponse.redirect(URI.create("/logout"));
        }

        var carroOpt = carroService.buscarPorCodigo(carroCodigo);

        if (carroOpt.isEmpty()) {
            return HttpResponse.redirect(URI.create("/pedidos/"));
        }

        Usuario usuario = usuarioOpt.get();
        var carroDisponivel = carroOpt.get();

        Pedido pedido = new Pedido();

        pedido.setUsuarioId(usuario.getId());
        pedido.setClienteNome(usuario.getNome());

        pedido.setClienteCpf(usuario.getCpf());
        pedido.setClienteRg(usuario.getRg());
        pedido.setClienteEndereco(usuario.getEndereco());
        pedido.setClienteProfissao(usuario.getProfissao());

        pedido.setEmpregador1(usuario.getEmpregador1());
        pedido.setRendimento1(usuario.getRendimento1());
        pedido.setEmpregador2(usuario.getEmpregador2());
        pedido.setRendimento2(usuario.getRendimento2());
        pedido.setEmpregador3(usuario.getEmpregador3());
        pedido.setRendimento3(usuario.getRendimento3());

        pedido.setMarca(carroDisponivel.getMarca());
        pedido.setModelo(carroDisponivel.getModelo());
        pedido.setCarro(carroDisponivel.getNomeCompleto());
        pedido.setAno(carroDisponivel.getAno());
        pedido.setMatricula(carroDisponivel.getMatricula());
        pedido.setPlaca(carroDisponivel.getPlaca());
        pedido.setProprietarioTipo(carroDisponivel.getProprietarioTipo());

        pedido.setDias(dias);

        service.salvar(pedido);

        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }

    @Get("/lista")
    @View("pedido-lista")
    public Object listar(HttpRequest<?> request) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return redirecionarLogin();
        }

        Map<String, Object> model = new HashMap<>();
        model.put("pedidos", service.listarPorUsuario(usuarioId.get()));
        preencherMenu(model, request);

        return model;
    }

    @Get("/editar/{id}")
    @View("pedido-editar")
    public Object editarForm(Long id, HttpRequest<?> request) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return redirecionarLogin();
        }

        Pedido pedido = service.buscarPorId(id).orElseThrow();

        if (pedido.getUsuarioId() == null || !pedido.getUsuarioId().equals(usuarioId.get())) {
            return HttpResponse.redirect(URI.create("/pedidos/lista"));
        }

        if (pedido.getStatus().name().equals("APROVADO") ||
                pedido.getStatus().name().equals("CONTRATO_GERADO") ||
                pedido.getStatus().name().equals("CANCELADO")) {
            return HttpResponse.redirect(URI.create("/pedidos/lista"));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("pedido", Optional.of(pedido));
        preencherMenu(model, request);

        return model;
    }

    @Post(value = "/atualizar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> atualizar(
            HttpRequest<?> request,
            @Body("id") Long id,
            @Body("carro") String carro,
            @Body("dias") Integer dias
    ) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return redirecionarLogin();
        }

        if (dias == null || dias <= 0) {
            return HttpResponse.redirect(URI.create("/pedidos/editar/" + id));
        }

        Pedido pedido = service.buscarPorId(id).orElseThrow();

        if (pedido.getUsuarioId() == null || !pedido.getUsuarioId().equals(usuarioId.get())) {
            return HttpResponse.redirect(URI.create("/pedidos/lista"));
        }

        service.atualizar(id, carro, dias);

        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }

    @Post(value = "/cancelar", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> cancelar(HttpRequest<?> request, @Body("id") Long id) {
        Optional<Long> usuarioId = getUsuarioId(request);

        if (usuarioId.isEmpty()) {
            return redirecionarLogin();
        }

        Pedido pedido = service.buscarPorId(id).orElseThrow();

        if (pedido.getUsuarioId() == null || !pedido.getUsuarioId().equals(usuarioId.get())) {
            return HttpResponse.redirect(URI.create("/pedidos/lista"));
        }

        service.cancelar(id);

        return HttpResponse.redirect(URI.create("/pedidos/lista"));
    }
}