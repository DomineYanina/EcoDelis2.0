package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.ClienteRepository;
import com.EcoDelis.dominio.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PedidoService")
public class PedidoServiceImpl {

    @Autowired
    private ClienteRepository clienteRepository;

    List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        return pedidos = clienteRepository.obtenerPedidosPorCliente(clienteLogueado);
    }
}
