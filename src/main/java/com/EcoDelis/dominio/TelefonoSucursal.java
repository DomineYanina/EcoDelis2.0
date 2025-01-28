package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class TelefonoSucursal extends Telefono {

    @ManyToOne
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    public TelefonoSucursal(long numero, TipoTelefono tipo, Sucursal sucursal) {
        super(numero, tipo);
        this.sucursal = sucursal;
    }

    public TelefonoSucursal() {
        super();
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

}
