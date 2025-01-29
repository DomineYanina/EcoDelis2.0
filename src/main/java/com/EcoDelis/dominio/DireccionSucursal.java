package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class DireccionSucursal extends Direccion {

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    public DireccionSucursal() {
        super();
    }

    public DireccionSucursal(String calle, long numero, long codigopostal, String localidad, String provincia, Sucursal sucursal) {
        super(calle, numero, codigopostal, localidad, provincia);
        this.sucursal = sucursal;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

}
