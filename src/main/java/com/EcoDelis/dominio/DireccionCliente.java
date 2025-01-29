package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class DireccionCliente extends Direccion {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public DireccionCliente() {
        super();
    }

    public DireccionCliente(String calle, long numero, long codigopostal, String localidad, String provincia, Cliente cliente) {
        super(calle, numero, codigopostal, localidad, provincia);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
