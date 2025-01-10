package com.EcoDelis.dominio;

public interface SucursalRepository {
    void guardar(Sucursal sucursal);

    Sucursal buscarPorNombre(String nombre);

    void modificar(Sucursal sucursal);
}
