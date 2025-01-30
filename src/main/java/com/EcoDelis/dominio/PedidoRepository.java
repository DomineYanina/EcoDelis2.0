package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository {
    void agregar(Pedido pedido);

    void actualizar(Pedido pedido);

    void cancelar(Pedido pedido);

    Pedido buscarPorId(Long pedidoId);

    List<Promocion> obtenerPromocionesPorPedido(Long pedidoId);
}
