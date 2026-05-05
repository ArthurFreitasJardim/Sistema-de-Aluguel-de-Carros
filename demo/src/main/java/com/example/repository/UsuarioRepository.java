package com.example.repository;

import com.example.model.Usuario;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByRg(String rg);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);
}