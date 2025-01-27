package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository {
    void agregar(Pedido pedido);

    void actualizar(Pedido pedido);

    void cancelar(Pedido pedido);

}
