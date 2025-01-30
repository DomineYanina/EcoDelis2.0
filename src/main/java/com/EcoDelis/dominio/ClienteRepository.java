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

    List<DireccionCliente> obtenerDireccionesPorCliente(Cliente cliente);

    List<TelefonoCliente> obtenerTelefonosPorCliente(Cliente cliente);

    DireccionCliente chequearDireccionYaExistente(DireccionCliente direccionCliente, Cliente clienteLogueado);

    TelefonoCliente chequearTelefonoYaExistente(TelefonoCliente telefonoCliente);
}
