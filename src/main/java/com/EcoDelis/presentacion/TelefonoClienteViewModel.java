package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;
import com.EcoDelis.dominio.TipoTelefono;

public class TelefonoClienteViewModel {
    private long id;
    private long numero;
    private TipoTelefono tipo;
    private Cliente cliente;

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
