package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;

public interface SucursalService {
    Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                       DireccionSucursalViewModel direccionSucursalViewModel);

    boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel);

    void modificar(Sucursal sucursal);

}
