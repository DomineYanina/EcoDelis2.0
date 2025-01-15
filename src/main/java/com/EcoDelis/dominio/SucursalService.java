package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import com.EcoDelis.presentacion.ResponsableViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface SucursalService {
    Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                       ResponsableViewModel responsableViewModel,
                       DireccionSucursalViewModel direccionSucursalViewModel);

    boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel);

    void modificar(Sucursal sucursal);

}
