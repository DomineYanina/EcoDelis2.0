package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.EcoDelis.dominio.EstadoPedido.*;

@Service("CalificacionService")
@Transactional
public class CalificacionServiceImpl implements CalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public Calificacion obtener(Pedido pedido) {
        return calificacionRepository.obtener(pedido);
    }

    @Override
    public void nueva(Calificacion calificacion) {
        calificacionRepository.agregar(calificacion);
        calificacion.getPedido().setEstado(Calificado);
        pedidoRepository.actualizar(calificacion.getPedido());
    }

    @Override
    public List<Calificacion> obtenerCalificacionesPorSucursal(Sucursal sucursal) {
        return calificacionRepository.obtenerCalificacionesPorSucursal(sucursal);
    }

    @Override
    public List<Calificacion> obtenerCalificacionesPorCliente(Cliente cliente) {
        return calificacionRepository.obtenerCalificacionesPorCliente(cliente);
    }
}
