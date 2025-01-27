package com.EcoDelis.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoSucursalRepository {

    void agregar(TelefonoSucursal telefonoSucursal);

    void modificar(TelefonoSucursal telefonoSucursal);

    void eliminar(TelefonoSucursal telefonoSucursal);
}
