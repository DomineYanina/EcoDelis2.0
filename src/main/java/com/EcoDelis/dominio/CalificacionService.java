package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalificacionService {

    Calificacion obtener(Pedido pedido);

    void nueva(Calificacion calificacion);

    List<Calificacion> obtenerCalificacionesPorSucursal(Sucursal sucursal);

    List<Calificacion> obtenerCalificacionesPorCliente(Cliente cliente);
}
