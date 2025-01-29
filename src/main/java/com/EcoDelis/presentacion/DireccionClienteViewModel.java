package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Cliente;

public class DireccionClienteViewModel extends DireccionViewModel {

    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
