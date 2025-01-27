package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoClienteRepository {
    void agregar(TelefonoCliente telefonoCliente);

    void modificar(TelefonoCliente telefonoCliente);

    void eliminar(TelefonoCliente telefonoCliente);
}
