package com.EcoDelis.dominio;

import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

public interface DireccionSucursalService {

    DireccionSucursal agregar(DireccionSucursalViewModel direccionSucursalViewModel);
}
