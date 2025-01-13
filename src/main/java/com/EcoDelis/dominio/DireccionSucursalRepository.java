package com.EcoDelis.dominio;

import org.springframework.stereotype.Repository;

@Repository
public interface DireccionSucursalRepository {

    void agregar(DireccionSucursal direccionSucursal);
}
