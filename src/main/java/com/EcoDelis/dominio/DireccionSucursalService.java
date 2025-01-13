package com.EcoDelis.dominio;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface DireccionSucursalService {
    void agregar(DireccionSucursal direccionSucursal);
}
