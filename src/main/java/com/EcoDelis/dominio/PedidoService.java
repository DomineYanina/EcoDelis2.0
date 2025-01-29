package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PedidoService {

    List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado);

    List<Pedido> obtenerPedidosPorSucursal(Sucursal sucursal);

    void agregarPedido(Pedido pedido);

    void actualizar(Pedido pedido);

    void cancelar(Pedido pedido);

    Pedido buscar(Long pedidoId);

    List<Promocion> obtenerPromocionesPorPedido(Long pedidoId);
}
