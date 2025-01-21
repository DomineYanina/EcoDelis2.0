package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository {
    Local buscarLocal(String email, String password);

    Local buscarPorEmail(String email);

    void guardar(Local local);

    Sucursal buscarSucursalPorNombre(String nombre);

    void eliminarSucursal(Sucursal sucursal);

    void modificar(Local localExistente);
}
