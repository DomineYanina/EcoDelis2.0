package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class TelefonoCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numero;
    private TipoTelefono tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public TelefonoCliente(long numero, TipoTelefono tipo, Cliente cliente) {
        this.numero = numero;
        this.tipo = tipo;
        this.cliente = cliente;
    }

    public TelefonoCliente() {

    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
