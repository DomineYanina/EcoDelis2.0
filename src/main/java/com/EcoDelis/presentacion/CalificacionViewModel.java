package com.EcoDelis.presentacion;

import com.EcoDelis.dominio.Pedido;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

public class CalificacionViewModel {
    private long id;
    private Date fecha;
    private int puntaje;
    private String comentarios;
    private Pedido pedido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
