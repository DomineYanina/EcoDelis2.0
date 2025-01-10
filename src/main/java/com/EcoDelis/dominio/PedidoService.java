package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface PedidoService {

    List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado);
}
