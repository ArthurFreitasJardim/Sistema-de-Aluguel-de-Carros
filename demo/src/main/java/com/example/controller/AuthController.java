package com.example.controller;

import com.example.model.Usuario;
import com.example.service.UsuarioService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.views.View;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    private Cookie apagarCookie(String nome) {
        return Cookie.of(nome, "")
                .path("/")
                .maxAge(0);
    }

    @Get("/")
    public HttpResponse<?> index() {
        return HttpResponse.redirect(URI.create("/pedidos/"))
                .cookie(apagarCookie("usuarioId"))
                .cookie(apagarCookie("perfil"));
    }

    @Get("/login")
    @View("login")
    public Map<String, Object> login(@QueryValue(value = "erro", defaultValue = "") String erro) {
        Map<String, Object> model = new HashMap<>();
        model.put("erro", erro);
        return model;
    }

    @Post(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> autenticar(
            @Body("email") String email,
            @Body("senha") String senha) {
        if ("admin123@email.com".equals(email) && "123456".equals(senha)) {
            return HttpResponse.redirect(URI.create("/agente/pedidos"))
                    .cookie(apagarCookie("usuarioId"))
                    .cookie(Cookie.of("perfil", "ADMIN").path("/"));
        }

        Optional<Usuario> usuario = usuarioService.autenticar(email, senha);

        if (usuario.isEmpty()) {
            return HttpResponse.redirect(URI.create("/login?erro=E-mail ou senha inválidos."));
        }

        return HttpResponse.redirect(URI.create("/pedidos/lista"))
                .cookie(Cookie.of("usuarioId", usuario.get().getId().toString()).path("/"))
                .cookie(Cookie.of("perfil", "CLIENTE").path("/"));
    }

    @Get("/cadastro")
    @View("cadastro")
    public Map<String, Object> cadastro(@QueryValue(value = "erro", defaultValue = "") String erro) {
        Map<String, Object> model = new HashMap<>();
        model.put("erro", erro);
        return model;
    }

    @Post(value = "/cadastro", consumes = MediaType.APPLICATION_FORM_URLENCODED)
    public HttpResponse<?> cadastrar(
            @Body("nome") String nome,
            @Body("email") String email,
            @Body("senha") String senha,
            @Body("cpf") String cpf,
            @Body("rg") String rg,
            @Body("endereco") String endereco,
            @Body("profissao") String profissao,
            @Body("empregador1") String empregador1,
            @Body("rendimento1") Double rendimento1,
            @Body("empregador2") String empregador2,
            @Body("rendimento2") Double rendimento2,
            @Body("empregador3") String empregador3,
            @Body("rendimento3") Double rendimento3) {
        try {
            usuarioService.cadastrarCliente(
                    nome,
                    email,
                    senha,
                    cpf,
                    rg,
                    endereco,
                    profissao,
                    empregador1,
                    rendimento1,
                    empregador2,
                    rendimento2,
                    empregador3,
                    rendimento3);

            return HttpResponse.redirect(URI.create("/login"));
        } catch (RuntimeException e) {
            return HttpResponse.redirect(URI.create("/cadastro?erro=" + e.getMessage().replace(" ", "%20")));
        }
    }

    @Get("/sair")
    @Produces(MediaType.TEXT_HTML)
    public HttpResponse<String> sair() {
        return forcarLogout();
    }

    @Get("/logout")
    @Produces(MediaType.TEXT_HTML)
    public HttpResponse<String> logout() {
        return forcarLogout();
    }

    private HttpResponse<String> forcarLogout() {
        String html = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>Saindo...</title>
                </head>
                <body>
                    <script>
                        document.cookie = "usuarioId=; Path=/; Max-Age=0; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                        document.cookie = "perfil=; Path=/; Max-Age=0; expires=Thu, 01 Jan 1970 00:00:00 GMT";

                        document.cookie = "usuarioId=; Path=/pedidos; Max-Age=0; expires=Thu, 01 Jan 1970 00:00:00 GMT";
                        document.cookie = "perfil=; Path=/pedidos; Max-Age=0; expires=Thu, 01 Jan 1970 00:00:00 GMT";

                        localStorage.clear();
                        sessionStorage.clear();

                        window.location.replace("/pedidos/");
                    </script>
                </body>
                </html>
                """;

        return HttpResponse.ok(html)
                .contentType(MediaType.TEXT_HTML)
                .header("Set-Cookie", "usuarioId=; Path=/; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:00 GMT")
                .header("Set-Cookie", "perfil=; Path=/; Max-Age=0; Expires=Thu, 01 Jan 1970 00:00:00 GMT")
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .header("Pragma", "no-cache")
                .header("Expires", "0");
    }

}
