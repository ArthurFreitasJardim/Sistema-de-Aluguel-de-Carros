package com.example.repository;

import com.example.model.Contrato;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface ContratoRepository extends CrudRepository<Contrato, Long> {
    Iterable<Contrato> findByClienteCpf(String clienteCpf);

    Optional<Contrato> findByPedidoId(Long pedidoId);

    boolean existsByPedidoId(Long pedidoId);
    
}