package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Sucursal;
import com.EcoDelis.dominio.TipoTelefono;

public class TelefonoSucursalViewModel {
    private long id;
    private long numero;
    private TipoTelefono tipo;
    private Sucursal sucursal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public TipoTelefono getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefono tipo) {
        this.tipo = tipo;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
