package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository {

    Cliente buscarCliente(String email, String password);

    public Cliente buscarPorEmail(String email);

    void guardar(Cliente cliente);

    Cliente obtenerCliente(Long id);

    void modificar(Cliente cliente);
}
