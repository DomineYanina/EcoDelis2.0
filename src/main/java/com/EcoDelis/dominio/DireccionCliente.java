package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class DireccionCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String calle;
    private long numero;
    private Localidad localidad;
    private Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public DireccionCliente(String calle, long numero, Localidad localidad, Provincia provincia, Cliente cliente) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.cliente = cliente;
    }

    public DireccionCliente() {

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
