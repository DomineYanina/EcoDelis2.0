package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface DireccionClienteRepository {

    void agregar(DireccionCliente direccionCliente);

    void modificar(DireccionCliente direccionCliente);

    void eliminar(DireccionCliente direccionCliente);
}
