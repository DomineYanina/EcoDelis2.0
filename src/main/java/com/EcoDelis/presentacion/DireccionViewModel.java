package com.EcoDelis.presentacion;

public abstract class DireccionViewModel {
    private long id;
    private String calle;
    private long numero;
    private long codigopostal;
    private String localidad;
    private String provincia;

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

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public long getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(long codigopostal) {
        this.codigopostal = codigopostal;
    }
}
