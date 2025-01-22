package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository {
    Calificacion obtener(Pedido pedido);

    void agregar(Calificacion calificacion);

    List<Calificacion> obtenerCalificacionesPorSucursal(Sucursal sucursal);

    List<Calificacion> obtenerCalificacionesPorCliente(Cliente cliente);
}
