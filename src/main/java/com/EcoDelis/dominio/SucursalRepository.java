package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository {
    void guardar(Sucursal sucursal);

    Sucursal buscarPorNombre(String nombre);

    void modificar(Sucursal sucursal);
}
