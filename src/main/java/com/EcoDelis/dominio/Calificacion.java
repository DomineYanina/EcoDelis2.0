package com.EcoDelis.dominio;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Calificacion {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date fecha;
    private int puntaje;
    private String comentarios;

    @OneToOne
    @JoinColumn(name = "calificacion")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Calificacion(Date fecha, int puntaje, String comentarios, Pedido pedido, Cliente cliente) {
        this.fecha = fecha;
        this.puntaje = puntaje;
        this.comentarios = comentarios;
        this.pedido = pedido;
        this.cliente = cliente;
    }

    public Calificacion() {

    }

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
