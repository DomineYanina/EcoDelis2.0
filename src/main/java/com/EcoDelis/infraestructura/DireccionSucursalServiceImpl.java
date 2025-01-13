package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.DireccionSucursal;
import com.EcoDelis.dominio.DireccionSucursalRepository;
import com.EcoDelis.dominio.DireccionSucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("DireccionSucursalService")
@Transactional
public class DireccionSucursalServiceImpl implements DireccionSucursalService {

    @Autowired
    DireccionSucursalRepository direccionSucursalRepository;

    @Override
    public void agregar(DireccionSucursal direccionSucursal) {
        direccionSucursalRepository.agregar(direccionSucursal);
    }
}
