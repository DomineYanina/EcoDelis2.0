package com.EcoDelis.infraestructura;

import com.EcoDelis.dominio.TelefonoSucursal;
import com.EcoDelis.dominio.TelefonoSucursalRepository;
import com.EcoDelis.dominio.TelefonoSucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("TelefonoSucursalService")
@Transactional
public class TelefonoSucursalServiceImpl implements TelefonoSucursalService {

    @Autowired
    TelefonoSucursalRepository telefonoSucursalRepository;

    @Override
    public void agregar(TelefonoSucursal telefonoSucursal) {
        telefonoSucursalRepository.agregar(telefonoSucursal);
    }
}
