package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import com.EcoDelis.presentacion.RegistroSucursalViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface SucursalService {
    Sucursal registrar(RegistroSucursalViewModel registroSucursalViewModel,
                       DireccionSucursal direccionSucursal, Local local);

    boolean nombreDeSucursalYaExiste(RegistroSucursalViewModel registroSucursalViewModel);

    void modificar(Sucursal sucursal);

    void eliminar(Sucursal sucursal);

}
