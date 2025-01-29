package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Sucursal;

public class DireccionSucursalViewModel extends DireccionViewModel {

    private Sucursal sucursal;

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
