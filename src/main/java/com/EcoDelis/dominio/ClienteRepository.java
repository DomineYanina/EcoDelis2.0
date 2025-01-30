package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository {

    Cliente buscarCliente(String email, String password);

    public Cliente buscarPorEmail(String email);

    void guardar(Cliente cliente);

    Cliente obtenerCliente(Long id);

    void modificar(Cliente cliente);

    List<Pedido> obtenerPedidosPorCliente(Cliente clienteLogueado);

    List<Calificacion> obtenerCalificacionesDadasPorCliente(Cliente cliente);
}
