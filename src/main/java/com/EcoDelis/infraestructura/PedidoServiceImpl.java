package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("PedidoService")
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado){
        List<Pedido> pedidos = new ArrayList<Pedido>();
        return pedidos = clienteRepository.obtenerPedidosPorCliente(clienteLogueado);
    }

    @Override
    public List<Pedido> obtenerPedidosPorSucursal(Sucursal sucursal) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        return pedidos = sucursalRepository.obtenerPedidos(sucursal);
    }

    @Override
    public void agregarPedido(Pedido pedido) {
        pedidoRepository.agregar(pedido);
    }

    @Override
    public void actualizar(Pedido pedido) {
        pedidoRepository.actualizar(pedido);
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedidoRepository.cancelar(pedido);
    }

    @Override
    public Pedido buscar(Long pedidoId) {
        return pedidoRepository.buscarPorId(pedidoId);
    }

    @Override
    public List<Promocion> obtenerPromocionesPorPedido(Long pedidoId) {
        return pedidoRepository.obtenerPromocionesPorPedido(pedidoId);
    }
}
