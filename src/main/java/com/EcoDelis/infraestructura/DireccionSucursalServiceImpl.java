package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionSucursal;
import com.EcoDelis.dominio.DireccionSucursalRepository;
import com.EcoDelis.dominio.DireccionSucursalService;
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
    public DireccionSucursal agregar(DireccionSucursalViewModel direccionSucursalViewModel) {
        DireccionSucursal direccionSucursal = new DireccionSucursal();
        direccionSucursal.setNumero(direccionSucursalViewModel.getNumero());
        direccionSucursal.setProvincia(direccionSucursalViewModel.getProvincia());
        direccionSucursal.setLocalidad(direccionSucursalViewModel.getLocalidad());
        direccionSucursal.setCalle(direccionSucursalViewModel.getCalle());
        direccionSucursalRepository.agregar(direccionSucursal);
        return direccionSucursal;
    }
}
