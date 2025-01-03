package com.EcoDelis.dominio;

import javax.persistence.*;

@Entity
public class TelefonoResponsable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long numero;
    private TipoTelefono tipo;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Responsable responsable;

    public TelefonoResponsable(long numero, TipoTelefono tipo, Responsable responsable) {
        this.numero = numero;
        this.tipo = tipo;
        this.responsable = responsable;
    }

    public TelefonoResponsable() {

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

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
