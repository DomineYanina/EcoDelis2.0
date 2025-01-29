package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Telefono {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numero;
    private TipoTelefono tipo;

    public Telefono(long numero, TipoTelefono tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public Telefono() {

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
}
