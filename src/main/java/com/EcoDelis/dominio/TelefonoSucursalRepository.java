package com.EcoDelis.dominio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoSucursalRepository {

    @Autowired


    void agregar(TelefonoSucursal telefonoSucursal);
}
