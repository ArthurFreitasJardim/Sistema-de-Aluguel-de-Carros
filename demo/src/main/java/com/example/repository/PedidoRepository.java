package com.example.repository;

import com.example.model.Pedido;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Iterable<Pedido> findByUsuarioId(Long usuarioId);
}