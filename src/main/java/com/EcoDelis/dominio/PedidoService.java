package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PedidoService {

    List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado);

    void agregarPedido(Pedido pedido);

    void actualizar(Pedido pedido);

    void cancelar(Pedido pedido);
}
