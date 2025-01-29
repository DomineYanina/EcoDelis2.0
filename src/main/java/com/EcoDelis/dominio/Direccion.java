package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String calle;
    private long numero;
    private long codigopostal;
    private String localidad;
    private String provincia;

    public Direccion() {
    }

    public Direccion(String calle, long numero, long codigopostal, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.codigopostal = codigopostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }

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

    public long getCodigopostal() {
        return codigopostal;
    }

    public void setCodigopostal(long codigopostal) {
        this.codigopostal = codigopostal;
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
}
