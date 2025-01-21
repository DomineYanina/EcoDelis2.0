package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionSucursal;
import com.EcoDelis.dominio.DireccionSucursalRepository;
import com.EcoDelis.dominio.DireccionSucursalService;
import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.presentacion.DireccionSucursalViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DireccionSucursalServiceImpl implements DireccionSucursalService {

    @Autowired
    DireccionSucursalRepository direccionSucursalRepository;

    @Override
    public DireccionSucursal agregar(DireccionSucursal direccionSucursal) {
        direccionSucursalRepository.agregar(direccionSucursal);
        return direccionSucursal;
    }

    @Override
    public void modificar(DireccionSucursal direccionSucursal) {
        direccionSucursalRepository.modificar(direccionSucursal);
    }
}
