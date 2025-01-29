package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class TelefonoCliente extends Telefono {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public TelefonoCliente(long numero, TipoTelefono tipo, Cliente cliente) {
        super(numero, tipo);
        this.cliente = cliente;
    }

    public TelefonoCliente() {
        super();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
