package com.example.service;

import com.example.model.PerfilUsuario;
import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import jakarta.inject.Singleton;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Singleton
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarCliente(
            String nome,
            String email,
            String senha,
            String cpf,
            String rg,
            String endereco,
            String profissao,
            String empregador1,
            Double rendimento1,
            String empregador2,
            Double rendimento2,
            String empregador3,
            Double rendimento3
    ) {
        if (repository.existsByEmail(email)) {
            throw new RuntimeException("Já existe uma conta cadastrada com este e-mail.");
        }

        if (repository.existsByCpf(cpf)) {
            throw new RuntimeException("Já existe uma conta cadastrada com este CPF.");
        }

        if (repository.existsByRg(rg)) {
            throw new RuntimeException("Já existe uma conta cadastrada com este RG.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(encoder.encode(senha));
        usuario.setPerfil(PerfilUsuario.CLIENTE);

        usuario.setCpf(cpf);
        usuario.setRg(rg);
        usuario.setEndereco(endereco);
        usuario.setProfissao(profissao);

        usuario.setEmpregador1(empregador1);
        usuario.setRendimento1(rendimento1);
        usuario.setEmpregador2(empregador2);
        usuario.setRendimento2(rendimento2);
        usuario.setEmpregador3(empregador3);
        usuario.setRendimento3(rendimento3);

        return repository.save(usuario);
    }

    public Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuario = repository.findByEmail(email);

        if (usuario.isPresent() && encoder.matches(senha, usuario.get().getSenha())) {
            return usuario;
        }

        return Optional.empty();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }
}