package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Localidad;
import com.EcoDelis.dominio.Provincia;
import com.EcoDelis.dominio.Sucursal;

public class DireccionSucursalViewModel {

    private long id;
    private String calle;
    private long numero;
    private Localidad localidad;
    private Provincia provincia;
    private Sucursal sucursal;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
